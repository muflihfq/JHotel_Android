package com.example.muflihfathanq.jhotel_android_muflih;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BuatPesananRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.1.107:8080/bookpesanan";
    private Map<String,String> params;
    //private Map<String,Integer> paramsInt;

    public BuatPesananRequest(int jumlah_hari, int id_customer, int id_hotel, String room_number,Response.Listener<String> listener) {
        super(Method.POST,Regis_URL,listener, null);
        params = new HashMap<>();
        //paramsInt = new HashMap<>();
        params.put("jumlah hari",String.valueOf(jumlah_hari));
        params.put("id_customer",String.valueOf(id_customer));
        params.put("id_hotel",String.valueOf(id_hotel));
        params.put("nomor kamar",room_number);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


    //public Map<String,Integer> getParamsInt(){ return paramsInt;}

}

