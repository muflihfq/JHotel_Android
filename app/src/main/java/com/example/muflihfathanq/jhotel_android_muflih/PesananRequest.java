package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananRequest extends StringRequest {

    private static final String Regis_URL = "http://192.168.1.101:8080/allpesanan" ;
    private Map<String,String> params;

    public PesananRequest( Response.Listener<String> listener)
    {
        super(Method.GET,Regis_URL,listener,null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }}
