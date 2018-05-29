package com.example.muflihfathanq.jhotel_android_muflih;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class HotelActivity extends AppCompatActivity {
    ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    int j,banyakRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        refreshList();
    }

    public void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    banyakRow = jsonResponse.length();
                    System.out.println(jsonResponse.length());
                    for (int i = 0; i < jsonResponse.length(); i++) {

                        JSONObject e = jsonResponse.getJSONObject(i);
                        JSONObject lokasi = e.getJSONObject("lokasi");

                        Hotel h = new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
                                e.getInt("bintang"), e.getInt("id"));

                        hotelArrayList.add(h);
                        j++;

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
        HotelRequest hotelRequest = new HotelRequest(responseListener);
        RequestQueue queue = newRequestQueue(HotelActivity.this);
        queue.add(hotelRequest);

    }


    public void createTable(){
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
                            judul.setText("ID");
                            break;

                        case 2:
                            judul.setText("Nama Hotel");
                            break;

                        case 3:
                            judul.setText("Bintang");
                            break;

                        case 4:
                            judul.setText("Lokasi");
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
                            judul.setText(String.valueOf(hotelArrayList.get(i-1).getID()));
                            break;

                        case 2:
                            judul.setText(hotelArrayList.get(i-1).getNama());
                            break;

                        case 3:
                            judul.setText(String.valueOf(hotelArrayList.get(i-1).getBintang()));
                            break;

                        case 4:
                            judul.setText(hotelArrayList.get(i-1).getLokasi().getDeskripsi());
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
