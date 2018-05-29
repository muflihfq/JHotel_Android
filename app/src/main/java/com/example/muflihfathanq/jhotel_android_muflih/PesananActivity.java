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

public class PesananActivity extends AppCompatActivity {
    ArrayList<Pesanan> pesananArrayList = new ArrayList<>();

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
                        JSONObject cust = jsonResponse.getJSONObject(i).getJSONObject("pelanggan");
                        JSONObject room = jsonResponse.getJSONObject(i).getJSONObject("room");
                        JSONObject hotel = room.getJSONObject("hotel");
                        JSONObject lokasi = hotel.getJSONObject("lokasi");

                        Customer customer = new Customer(cust.getString("nama"), cust.getInt("id"),
                                cust.getString("email"), cust.getString("dob"));
                        Hotel h = new Hotel(hotel.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
                                hotel.getInt("bintang"), hotel.getInt("id"));
                        Room kamar = new Room(room.getString("nomorKamar"), room.getString("statusKamar"),
                                room.getDouble("dailyTariff"), room.getString("tipeKamar"));

                        Pesanan pesan = new Pesanan(jsonResponse.getJSONObject(i).getInt("id"), customer, kamar);
                        pesan.setHotel(h);
                        pesan.setBiaya(jsonResponse.getJSONObject(i).getDouble("biaya"));
                        pesan.setJumlahHari(jsonResponse.getJSONObject(i).getDouble("jumlahHari"));
                        pesan.setAktif(jsonResponse.getJSONObject(i).getBoolean("statusAktif"));
                        pesan.setDiproses(jsonResponse.getJSONObject(i).getBoolean("statusDiproses"));
                        pesan.setSelesai(jsonResponse.getJSONObject(i).getBoolean("statusSelesai"));

                        pesananArrayList.add(pesan);
//                        Hotel h = new Hotel(e.getString("nama"), new Lokasi(lokasi.getInt("x"), lokasi.getInt("y"), lokasi.getString("deskripsi")),
//                                e.getInt("bintang"), e.getInt("id"));
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
        PesananRequest pesananRequest = new PesananRequest(responseListener);
        RequestQueue queue = newRequestQueue(PesananActivity.this);
        queue.add(pesananRequest);

    }

    public void createTable() {

        ScrollView layout = (ScrollView) findViewById(R.id.tableLayout1);
        TableLayout table = new TableLayout(this);
        for (int i = 0; i <= banyakRow; i++) {
            TableRow tr = new TableRow(this);
            System.out.print(i + "iiii");
            if (i == 0) {
                for (int j = 1; j < 8; j++) {
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
                            judul.setText("Pemesan");
                            break;

                        case 3:
                            judul.setText("Hotel");
                            break;

                        case 4:
                            judul.setText("Kamar");
                            break;
                        case 5:
                            judul.setText("Biaya");
                            break;
                        case 6:
                            judul.setText("Jumlah Hari");
                            break;
                        case 7:
                            judul.setText("Status");
                            break;

                    }
                    judul.setPadding(6, 4, 6, 4);
                    tr.addView(judul);
                }
                table.addView(tr);
                System.out.println("\n\n\nsesudah if\n\n\n\n");

            }else {
                System.out.println("\n\n\nsebelum insert\n\n\n\n");
                for (int j = 1; j < 8; j++) {
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
                            judul.setText(String.valueOf(pesananArrayList.get(i - 1).getId()));
                            break;

                        case 2:
                            judul.setText(pesananArrayList.get(i - 1).getPelanggan().getNama());
                            break;

                        case 3:
                            judul.setText(pesananArrayList.get(i - 1).getHotel().getNama());
                            break;

                        case 4:
                            judul.setText(pesananArrayList.get(i - 1).getKamar().getRoomNumber());
                            break;
                        case 5:
                            judul.setText(String.valueOf(pesananArrayList.get(i - 1).getBiaya()));
                            break;
                        case 6:
                            judul.setText(String.valueOf(pesananArrayList.get(i - 1).getJumlahHari()));
                            break;
                        case 7:
                            if (pesananArrayList.get(i - 1).isDiproses()) {
                                judul.setText("Diproses");
                                break;
                            } else if (pesananArrayList.get(i - 1).isSelesai()) {
                                judul.setText("Selesai");
                                break;

                            } else {
                                judul.setText("Aktif");
                                break;

                            }


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

