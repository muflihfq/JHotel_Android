package com.example.muflihfathanq.jhotel_android_muflih;

public class Hotel {

    // instance variables - replace the example below with your own
    private String nama;
    private Lokasi lokasi;
    private int bintang;
    private int id;

    /**
     * konstruktor pada class hotel
     * @param nama, lokasi, bintang
     */
    public Hotel(String nama, Lokasi lokasi, int bintang, int id)
    {

        this.nama = nama;
        this.lokasi = lokasi;
        this.bintang = bintang;
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public int getBintang()
    {
        return bintang;
    }

    public String getNama()
    {
        return nama;
    }

    public Lokasi getLokasi()
    {
        return lokasi;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public void setLokasi (Lokasi lokasi)
    {
        this.lokasi = lokasi;
    }

    public void setBintang(int bintang)
    {
        this.bintang = bintang;
    }

}
