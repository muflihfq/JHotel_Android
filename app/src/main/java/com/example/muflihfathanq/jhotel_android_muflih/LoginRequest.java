package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String Regis_URL = "http://10.0.2.2:8080/logincust";
    private Map<String,String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST,Regis_URL,listener, null);
        params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
        System.out.println("login");

    }

    /*
    public void getID (Response.Listener<String> listener)
    {
        String url = "http://10.0.2.2:8080/getcustomer/param=%d,
        StringRequest str = new StringRequest(Method.GET,"http://10.0.2.2:8080/getcustomer/",listener,null);
    }*/
    @Override
    public Map<String, String> getParams() {
        return params;
    }

}