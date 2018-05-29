package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class    PesananFetchRequest extends StringRequest {

    final private static String Regis_URL = "http://192.168.1.107:8080/pesanancustomer/" ;
    private Map<String,String> params;

    public PesananFetchRequest( String id_customer,Response.Listener<String> listener)
    {
        super(Method.GET,Regis_URL+id_customer,listener,null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
