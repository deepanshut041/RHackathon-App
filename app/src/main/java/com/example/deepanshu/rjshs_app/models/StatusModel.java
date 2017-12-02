package com.example.deepanshu.rjshs_app.models;

/**
 * Created by Deepanshu on 12/2/2017.
 */

public class StatusModel {
    private String carNumber;
    private String carStatus;
    private String carLat;
    private String carLon;
    private String carFuel;
    private String carTemp;

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public void setCarLat(String carLat) {
        this.carLat = carLat;
    }

    public void setCarLon(String carLon) {
        this.carLon = carLon;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public void setCarTemp(String carTemp) {
        this.carTemp = carTemp;
    }

    public String getCarFuel() {
        return carFuel;
    }

    public String getCarLat() {
        return carLat;
    }

    public String getCarLon() {
        return carLon;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public String getCarTemp() {
        return carTemp;
    }
}
