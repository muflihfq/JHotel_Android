package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel,ArrayList<Room>> childMapping = new HashMap<>();
    private int currentUserid;
    ExpandableListView listExpView;
    ExpandableListAdapter listAdapter;


    HashMap<String, Hotel> hotelHashMap = new HashMap<>();
    HashMap<String, ArrayList<Room>> roomsMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listExpView = (ExpandableListView) findViewById(R.id.lvExp);
        final Button pesanBtn = (Button) findViewById(R.id.psnBtn);
        currentUserid = getIntent().getIntExtra("id_customer",0);
        refreshList();
        listExpView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Room selected = childMapping.get(listHotel.get(groupPosition)).get(childPosition);

                Intent regisInt = new Intent(MainActivity.this, BuatPesananActivity.class);
                regisInt.putExtra("id_customer",currentUserid);
                regisInt.putExtra("id_hotel",listHotel.get(0).getID());
                regisInt.putExtra("room_number",selected.getRoomNumber());
                MainActivity.this.startActivity(regisInt);
                return true;
            }
        });

        pesanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                regisInt.putExtra("id_customer",String.valueOf(currentUserid));
                MainActivity.this.startActivity(regisInt);
            }
        });

    }

    public void refreshList()
    {
        /*
        Intent regisInt = new Intent(MainActivity.this, RegisterRequest.class);
        MainActivity.this.startActivity(regisInt);*/
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try{
                    JSONArray jsonResponse = new JSONArray(response);
                for (int i = 0; i < jsonResponse.length(); i++) {
                    JSONObject e = jsonResponse.getJSONObject(i).getJSONObject("hotel");
                    JSONObject lokasi = e.getJSONObject("lokasi");
                    JSONObject room = jsonResponse.getJSONObject(i);
                    Hotel h = new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
                            e.getInt("bintang"), e.getInt("id"));
                    hotelHashMap.put(h.getNama(), h);
                    Room room1 = new Room(room.getString("nomorKamar"), room.getString("statusKamar"), room.getDouble("dailyTariff"), room.getString("tipeKamar"));

                    if (!roomsMap.containsKey(h.getNama())) {
                        ArrayList<Room> rooms = new ArrayList<>();
                        rooms.add(room1);
                        roomsMap.put(h.getNama(), rooms);
                    } else {
                        ArrayList<Room> rooms = roomsMap.get(h.getNama());
                        rooms.add(room1);
                        roomsMap.put(h.getNama(), rooms);
                    }
                }

                for (String key : hotelHashMap.keySet()) {
                    listHotel.add(hotelHashMap.get(key));

                    childMapping.put(hotelHashMap.get(key), roomsMap.get(key));
                }
                listAdapter = new MenuListAdapter(MainActivity.this, listHotel, childMapping);
                listExpView.setAdapter(listAdapter);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }




    }



