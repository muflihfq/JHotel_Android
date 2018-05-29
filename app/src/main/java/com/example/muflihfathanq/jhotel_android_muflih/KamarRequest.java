package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class KamarRequest extends StringRequest {

    private static final String Regis_URL = "http://192.168.1.101:8080/allroom" ;
    private Map<String,String> params;

    public KamarRequest( Response.Listener<String> listener)
    {
        super(Method.GET,Regis_URL,listener,null);
        params = new HashMap<>();
        System.out.print("kamar");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }}
