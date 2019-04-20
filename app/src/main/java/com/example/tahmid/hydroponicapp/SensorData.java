package com.example.tahmid.hydroponicapp;

public class SensorData {
    String ph,ec,temp,light;

    public SensorData(String ph, String ec, String temp, String light) {
        this.ph = ph;
        this.ec = ec;
        this.temp = temp;
        this.light = light;
    }

    public SensorData() {
    }

    public String getPh() {
        return ph;
    }

    public String getEc() {
        return ec;
    }

    public String getTemp() {
        return temp;
    }

    public String getLight() {
        return light;
    }
}

