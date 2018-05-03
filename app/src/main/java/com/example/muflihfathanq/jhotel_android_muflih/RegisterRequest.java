package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String Regis_URL = "http://10.0.2.2/newcustomer" ;
    private Map<String,String> params;

    public RegisterRequest( String nama,String email, String password, Response.Listener<String> listener) {
        super(Method.POST,Regis_URL,listener, null);

        params = new HashMap<>();
        params.put("nama",nama);
        params.put("email",email);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    }

