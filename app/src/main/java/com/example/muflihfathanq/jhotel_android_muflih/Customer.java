package com.example.muflihfathanq.jhotel_android_muflih;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String nama;
    private int id;
    private String email;
    private Date dob;

    public Customer(String nama,int id,String email,String date){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = format.parse(date);

            this.dob = date1;
            this.id = id;
            this.nama = nama;
            this.email = email;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
