package com.example.muflihfathanq.jhotel_android_muflih;

public class Room {

    private String roomnumber;
    private String statuskamar;
    private double dailytariff;
    private String tipekamar;
    private Hotel hotel;

    public Room(String roomnumber,String statuskamar,double dailytariff,String tipekamar)
    {
        this.dailytariff = dailytariff;
        this.roomnumber = roomnumber;
        this.statuskamar = statuskamar;
        this.tipekamar = tipekamar;
    }

    public String getRoomNumber()
    {
        return roomnumber;
    }

    public String getStatuskamar()
    {
        return statuskamar;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getTipekamar()
    {
        return tipekamar;
    }

    public double getDailytariff()
    {
        return dailytariff;
    }
    public void setRoomNumber(String roomnumber)
    {
        this.roomnumber = roomnumber;
    }

    public void setStatuskamar(String statuskamar)
    {
        this.statuskamar =  statuskamar;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setTipekamar(String tipekamar)
    {
        this.tipekamar = tipekamar;
    }

    public void setDailytariff(double dailytariff)
    {
        this.dailytariff =  dailytariff;
    }
}
