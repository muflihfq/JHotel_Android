package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText emailInput;
    EditText namaInput;
    EditText passInput;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailInput = (EditText) findViewById(R.id.EmailRgs);
        namaInput = (EditText) findViewById(R.id.NamaRgs);
        passInput = (EditText) findViewById(R.id.PassRgs);
        registerBtn = (Button) findViewById(R.id.RgsBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = namaInput.getText().toString();
                final String pass = passInput.getText().toString();
                final String email = emailInput.getText().toString();
                System.out.println(name+pass);
                Response.Listener<String> responseListener = new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.print("try");
                            JSONObject jsonResponse = new JSONObject(response);
                            System.out.println(response);
                            if (jsonResponse!= null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Success")
                                        .create()
                                        .show();
                                System.out.println("5");
                                Intent regisInt = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(regisInt);

                            }
                        }
                        catch (JSONException e) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Registration Failed.")
                                    .create()
                                    .show();
                            System.out.println("6");
                        }


                    }
                };
                System.out.println("abistry");
                RegisterRequest registerRequest = new RegisterRequest(name, email,pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue
                        (RegisterActivity.this);
                queue.add(registerRequest);


            }
        });

    }
}
