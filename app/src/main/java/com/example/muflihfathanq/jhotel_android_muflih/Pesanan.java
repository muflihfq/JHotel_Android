package com.example.muflihfathanq.jhotel_android_muflih;

import android.os.CpuUsageInfo;

import java.util.Date;

public class Pesanan {
    private int id;
    private double biaya;
    private double jumlahHari;
    private Customer pelanggan;
    private boolean isAktif;
    private boolean isDiproses;
    private boolean isSelesai;
    private Room kamar;
    private Date tanggalPesan;
    private Hotel hotel;

    //nama hotel, nomor kamar, pemesan, tanggal pesan, jumlah hari, biaya, status

    public Pesanan(int id, Customer pelanggan,Room kamar){
        this.id = id;
        this.kamar = kamar;
        this.pelanggan = pelanggan;

    }

    public int getId() {
        return id;
    }

    public Customer getPelanggan() {
        return pelanggan;
    }

    public Date getTanggalPesan() {
        return tanggalPesan;
    }

    public double getBiaya() {
        return biaya;
    }

    public double getJumlahHari() {
        return jumlahHari;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getKamar() {
        return kamar;
    }


    public boolean isSelesai() {
        return isSelesai;
    }

    public boolean isDiproses() {
        return isDiproses;
    }

    public boolean isAktif() {
        return isAktif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAktif(boolean aktif) {
        isAktif = aktif;
    }

    public void setBiaya(double biaya) {
        this.biaya = biaya;
    }

    public void setDiproses(boolean diproses) {
        isDiproses = diproses;
    }

    public void setJumlahHari(double jumlahHari) {
        this.jumlahHari = jumlahHari;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setKamar(Room kamar) {
        this.kamar = kamar;
    }

    public void setPelanggan(Customer pelanggan) {
        this.pelanggan = pelanggan;
    }

    public void setSelesai(boolean selesai) {
        isSelesai = selesai;
    }

    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

}

