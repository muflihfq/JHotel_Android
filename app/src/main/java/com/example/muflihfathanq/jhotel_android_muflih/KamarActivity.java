package com.example.muflihfathanq.jhotel_android_muflih;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class KamarActivity extends AppCompatActivity {
    ArrayList<Room> roomArrayList = new ArrayList<>();
    ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    int j = 0, banyakRow = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);

        refreshList();
        // if(j !=0) {
        //createTable();
        //}

    }

    public void refreshList() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    banyakRow = jsonResponse.length();
                    System.out.println(jsonResponse.length());
                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject e = jsonResponse.getJSONObject(i).getJSONObject("hotel");
                        JSONObject lokasi = e.getJSONObject("lokasi");
                        JSONObject room = jsonResponse.getJSONObject(i);
                        Hotel h = new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
                                e.getInt("bintang"), e.getInt("id"));

                        hotelArrayList.add(h);
                        Room room1 = new Room(room.getString("nomorKamar"), room.getString("statusKamar"),
                                room.getDouble("dailyTariff"), room.getString("tipeKamar"));
                        room1.setHotel(h);
                        roomArrayList.add(room1);
                        System.out.println(room1.getRoomNumber());
                        System.out.println(roomArrayList.size());
                        j++;
                        System.out.println(j);
                    }

                    if (j == banyakRow) {
                        createTable();
                    } else {
                        System.out.println("ntah");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        KamarRequest kamarRequest = new KamarRequest(responseListener);
        RequestQueue queue = newRequestQueue(KamarActivity.this);
        queue.add(kamarRequest);

    }

    public void createTable() {

        ScrollView layout = (ScrollView) findViewById(R.id.tableLayout1);
        TableLayout table = new TableLayout(this);
        for (int i = 0; i <= banyakRow; i++) {
            TableRow tr = new TableRow(this);
            System.out.print(i + "iiii");
            if (i == 0) {
                for (int j = 1; j < 5; j++) {
                    TextView judul = new android.support.v7.widget.AppCompatTextView(this) {
                        @Override
                        protected void onDraw(Canvas canvas) {
                            super.onDraw(canvas);
                            Rect rect = new Rect();
                            Paint paint = new Paint();
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setColor(Color.BLACK);
                            paint.setStrokeWidth(2);
                            getLocalVisibleRect(rect);
                            canvas.drawRect(rect, paint);
                        }
                    };
                    switch (j) {
                        case 1:
                            judul.setText("Nama Hotel");
                            break;

                        case 2:
                            judul.setText("Nomor Kamar");
                            break;

                        case 3:
                            judul.setText("Harga/Malam");
                            break;

                        case 4:
                            judul.setText("Tipe Kamar");
                            break;

                    }
                    judul.setPadding(6, 4, 6, 4);
                    tr.addView(judul);
                }
                table.addView(tr);
                System.out.println("\n\n\nsesudah if\n\n\n\n");

            } else {
                System.out.println("\n\n\nsebelum insert\n\n\n\n");
                for (int j = 1; j < 5; j++) {
                    System.out.println("\n\n\nmasuk insert");
                    TextView judul = new android.support.v7.widget.AppCompatTextView(this) {
                        @Override
                        protected void onDraw(Canvas canvas) {
                            super.onDraw(canvas);
                            Rect rect = new Rect();
                            Paint paint = new Paint();
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setColor(Color.BLACK);
                            paint.setStrokeWidth(2);
                            getLocalVisibleRect(rect);
                            canvas.drawRect(rect, paint);
                        }
                    };
                    switch (j) {
                        case 1:
                            judul.setText(roomArrayList.get(i-1).getHotel().getNama());
                            break;

                        case 2:
                            judul.setText(roomArrayList.get(i-1).getRoomNumber());
                            break;

                        case 3:
                            judul.setText(String.valueOf(roomArrayList.get(i-1).getDailytariff()));
                            break;

                        case 4:
                            judul.setText(roomArrayList.get(i-1).getTipekamar());
                            break;

                    }
                    judul.setPadding(6, 4, 6, 4);
                    tr.addView(judul);
                }
                table.addView(tr);
            }
        }
        layout.addView(table);
    }
}

