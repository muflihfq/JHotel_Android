package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminActivity extends AppCompatActivity {

    Button buttonPesanan, buttonCustomer, buttonHotel, buttonKamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        buttonPesanan = (Button) findViewById(R.id.PesananButton);
        buttonCustomer = (Button) findViewById(R.id.CustomerButton);
        buttonHotel = (Button) findViewById(R.id.HotelButton);
        buttonKamar = (Button) findViewById(R.id.KamarButton);

        buttonHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(AdminActivity.this, HotelActivity.class);
                AdminActivity.this.startActivity(regisInt);
            }
        });

        buttonKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(AdminActivity.this, KamarActivity.class);
                AdminActivity.this.startActivity(regisInt);
            }
        });
        buttonPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(AdminActivity.this, PesananActivity.class);
                AdminActivity.this.startActivity(regisInt);
            }
        });

        buttonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(AdminActivity.this, CustomerActivity.class);
                AdminActivity.this.startActivity(regisInt);
            }
        });
    }
}
