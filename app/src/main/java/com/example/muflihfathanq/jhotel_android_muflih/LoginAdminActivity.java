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

public class LoginAdminActivity extends AppCompatActivity {

    Button masukAdmin;
    EditText emailAdmin;
    EditText passwordAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        masukAdmin = (Button) findViewById(R.id.ButtonAdmin);
        emailAdmin = (EditText) findViewById(R.id.EmailAdmin);
        passwordAdmin = (EditText) findViewById(R.id.PasswordAdmin);

        masukAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("0.2");
                final String email = emailAdmin.getText().toString();
                final String pass = passwordAdmin.getText().toString();
                //System.out.print(email + pass);
                Response.Listener<String> responseListener = new Response.Listener<String> () {

                    @Override
                    public void onResponse(String response) {

                        //System.out.println("2");
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                System.out.println("3");
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdminActivity.this);
                                builder.setMessage("Login Success")
                                        .create()
                                        .show();

                                Intent regisInt = new Intent(LoginAdminActivity.this, AdminActivity.class);
                                //regisInt.putExtra("id_customer",id);
                                LoginAdminActivity.this.startActivity(regisInt);

                            }
                        }
                        catch (JSONException e) {

                            System.out.println("4");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdminActivity.this);
                            builder.setMessage("Login Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };

                System.out.println("5");
                LoginAdminRequest loginRequest = new LoginAdminRequest(email, pass, responseListener);
                System.out.println(responseListener.toString());
                RequestQueue queue = Volley.newRequestQueue(LoginAdminActivity.this);
                queue.add(loginRequest);


            }
        });
    }
}
