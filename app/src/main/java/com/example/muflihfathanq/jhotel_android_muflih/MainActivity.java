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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Hotel> listHotel = new ArrayList<>();
    private ArrayList<Room> listRoom = new ArrayList<>();
    private HashMap<Hotel,ArrayList<Room>> childMapping = new HashMap<>();
    ExpandableListView ListView;
    ExpandableListAdapter listAdapter;

    HashMap<String, Hotel> hotelHashMap = new HashMap<>();
    HashMap<String, ArrayList<Room>> roomsMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView = (ExpandableListView) findViewById(R.id.lvExp);
        refreshList();
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
                ListView.setAdapter(listAdapter);

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



