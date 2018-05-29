package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginAdminRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.1.101:8080/loginadmin";
    private Map<String,String> params;

    public LoginAdminRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST,Regis_URL,listener, null);
        params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
        System.out.println("login");

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
