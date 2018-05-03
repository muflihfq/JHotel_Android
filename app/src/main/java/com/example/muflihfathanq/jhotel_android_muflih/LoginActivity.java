package com.example.muflihfathanq.jhotel_android_muflih;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    EditText emailInput;
    Button loginButton;
    EditText passInput;
    TextView registerClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerClickable = (TextView) findViewById(R.id.Register);
        emailInput = (EditText) findViewById(R.id.EmailRgs);
        passInput = (EditText) findViewById(R.id.PassRgs);
        loginButton = (Button) findViewById(R.id.LoginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String email = emailInput.getText().toString();
            final String pass = passInput.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(email+pass);
                Response.Listener<String> responseListener = new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {

                        System.out.println("2");
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse!= null) {

                                System.out.println("3");
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Success")
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {

                            System.out.println("4");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };

                System.out.println("5");
                LoginRequest loginRequest = new LoginRequest(email, pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        registerClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisInt = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regisInt);
            }
        });
    }


}
