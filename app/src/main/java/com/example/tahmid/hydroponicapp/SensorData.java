package com.example.tahmid.hydroponicapp;

public class SensorData {
    double hum,light,ph,temp,wlevel,wtemp;

    public SensorData(double hum,double light,double ph,double temp,double wlevel,double wtemp) {
       this.hum=hum;
       this.light=light;
       this.ph=ph;
       this.temp=temp;
       this.wlevel=wlevel;
       this.wtemp=wtemp;

    }

    public SensorData() {
    }


    public double getHum() {
        return hum;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getWlevel() {
        return wlevel;
    }

    public void setWlevel(double wlevel) {
        this.wlevel = wlevel;
    }

    public double getWtemp() {
        return wtemp;
    }

    public void setWtemp(double wtemp) {
        this.wtemp = wtemp;
    }
}

