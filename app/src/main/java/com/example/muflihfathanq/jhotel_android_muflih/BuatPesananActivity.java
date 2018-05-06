package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class BuatPesananActivity extends AppCompatActivity {

    private int currentUserid;
    private int banyakHari;
    private int idHotel;
    private double tariff;
    private String roomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        final Button hitungBtn = (Button) findViewById(R.id.hitung);
        final Button pesanBtn = (Button) findViewById(R.id.pesan);
        final TextView ttlBiaya = (TextView) findViewById(R.id.total_biaya);
        final TextView tarif = (TextView) findViewById(R.id.tarif);
        final TextView room_number = (TextView) findViewById(R.id.room_number);
        final EditText durasi = (EditText) findViewById(R.id.durasi);

        currentUserid = getIntent().getIntExtra("id_customer",0);
        roomNumber = getIntent().getStringExtra("room_number");
        idHotel = getIntent().getIntExtra("id_hotel",0);
        pesanBtn.setVisibility(View.INVISIBLE);
        room_number.setText(roomNumber);
        tarif.setText(String.valueOf(tariff));
        ttlBiaya.setText("0");



        hitungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banyakHari = Integer.valueOf(durasi.getText().toString());
                ttlBiaya.setText(String.valueOf(tariff*banyakHari));
                hitungBtn.setVisibility(View.INVISIBLE);
                pesanBtn.setVisibility(View.VISIBLE);
                System.out.println(banyakHari);
                System.out.println(currentUserid);
                System.out.println(idHotel);
                System.out.println(roomNumber);

                pesanBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    if (jsonResponse!= null) {

                                        System.out.println("3");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                                        builder.setMessage("Pesanan Berhasil diproses")
                                                .create()
                                                .show();



                                    }
                                }
                                catch (JSONException e) {

                                    System.out.println("4");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                                    builder.setMessage("Pesanan Gagal diproses")
                                            .create()
                                            .show();
                                }
                            }
                        };

                        System.out.println("5");
                        BuatPesananRequest pesananRequest = new BuatPesananRequest(banyakHari,currentUserid,idHotel,roomNumber,responseListener);
                        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(pesananRequest);
                    }
                });
            }
        });

    }
}
