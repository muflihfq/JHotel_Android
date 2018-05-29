package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static com.android.volley.toolbox.Volley.newRequestQueue;

public class SelesaiPesananActivity extends AppCompatActivity {

    private int id_pesanan;
    private int biaya_pesanan;
    private int hari_pesanan;
    private String tgl_pesanan;
    private String idCust;
    private TextView idPesanan;
    private TextView biaya;
    private TextView jumlahHari;
    private TextView tglPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        final Handler handler = new Handler();
        final Button batalBtn = (Button) findViewById(R.id.btlBtn);
        final Button konfirmBtn = (Button) findViewById(R.id.krmBtn);

        idPesanan = (TextView) findViewById(R.id.id);
        biaya = (TextView) findViewById(R.id.biaya);
        jumlahHari = (TextView) findViewById(R.id.hari);
        tglPesan = (TextView) findViewById(R.id.tanggal);

        idCust = getIntent().getStringExtra("id_customer");
        System.out.println(idCust);
        fetchPesanan(idCust);

        batalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse!= null) {
                                System.out.println("3");
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan Berhasil Dibatalkan")
                                        .create()
                                        .show();
                                Intent regisInt = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                SelesaiPesananActivity.this.startActivity(regisInt);


                            }
                        }
                        catch (JSONException e) {

                            System.out.println("4");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Pesanan Gagal Dibatalkan")
                                    .create()
                                    .show();
                        }
                    }
                };

                System.out.println("5");
                PesananBatalRequest batalRequest = new PesananBatalRequest(String.valueOf(id_pesanan), responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);
            }
        });

        konfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse!= null) {
                                System.out.println("3");
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Pesanan Berhasil Diselesaikan")
                                        .create()
                                        .show();
                                Intent regisInt = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                SelesaiPesananActivity.this.startActivity(regisInt);


                            }
                        }
                        catch (JSONException e) {

                            System.out.println("4");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Pesanan Gagal Diselesaikan")
                                    .create()
                                    .show();
                        }
                    }
                };

                System.out.println("5");
                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(String.valueOf(id_pesanan),responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);

            }
        });
    }

    public void fetchPesanan( final String id_customer)
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {

                    JSONObject psn = new JSONObject(response);
                    id_pesanan = psn.getInt("id");
                    biaya_pesanan = psn.getInt("biaya");
                    hari_pesanan = psn.getInt("jumlahHari");
                    tgl_pesanan = psn.getString("tanggalPesan");
                    System.out.println("id_customer = " +id_customer);
                    System.out.println("id_pesanan = "+id_pesanan);
                    System.out.println("biaya_pesanan = "+biaya_pesanan);
                    System.out.println("hari_pesanan = " +hari_pesanan);
                    System.out.println("tgl_pesanan = " +tgl_pesanan);
                    idPesanan.setText(String.valueOf(id_pesanan));
                    biaya.setText(String.valueOf(biaya_pesanan));
                    jumlahHari.setText(String.valueOf(hari_pesanan));
                    tglPesan.setText(tgl_pesanan);



                    /*JSONArray pesananArray = new JSONArray(response);

                    //for(int i = 0; i < pesananArray.length();i++)
                   // {
                        JSONObject psn = pesananArray.getJSONObject(0);
                    //    JSONObject cust = pesananArray.getJSONObject(i).getJSONObject("pelanggan");
                      //  int idcustomer = cust.getInt("id");
                        //if(id_customer.equals(String.valueOf(idcustomer))) {
                          /*  id_pesanan = psn.getInt("id");
                            idPesanan.setText(id_pesanan);

                            biaya_pesanan = psn.getInt("biaya");
                            biaya.setText(biaya_pesanan);
                            hari_pesanan = psn.getInt("jumlahHari");
                            jumlahHari.setText(hari_pesanan);
                            tgl_pesanan = psn.getString("tanggalPesan");
                            tglPesan.setText(tgl_pesanan);*/


                   //         break;
                     //   }
                   // }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };
        PesananFetchRequest fetchRequest = new PesananFetchRequest(id_customer,responseListener);
        RequestQueue queue = newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);


    }
}
