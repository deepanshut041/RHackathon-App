package com.example.deepanshu.rjshs_app.models;

/**
 * Created by Deepanshu on 12/2/2017.
 */

public class CarJourney {
    private String journeyStartLat;
    private String journeyStartLon;
    private String journeyEndLat;
    private String journeyEndLon;
    private String journeyStartTime;
    private String journeyEndTime;
    private String journeyDriverId;

    public void setJourneyEndLat(String journeyEndLat) {
        this.journeyEndLat = journeyEndLat;
    }

    public void setJourneyEndLon(String journeyEndLon) {
        this.journeyEndLon = journeyEndLon;
    }

    public void setJourneyEndTime(String journeyEndTime) {
        this.journeyEndTime = journeyEndTime;
    }

    public void setJourneyStartLat(String journeyStartLat) {
        this.journeyStartLat = journeyStartLat;
    }

    public void setJourneyStartLon(String journeyStartLon) {
        this.journeyStartLon = journeyStartLon;
    }

    public void setJourneyStartTime(String journeyStartTime) {
        this.journeyStartTime = journeyStartTime;
    }

    public String getJourneyEndLat() {
        return journeyEndLat;
    }

    public String getJourneyEndLon() {
        return journeyEndLon;
    }

    public String getJourneyEndTime() {
        return journeyEndTime;
    }

    public String getJourneyStartLat() {
        return journeyStartLat;
    }

    public String getJourneyStartLon() {
        return journeyStartLon;
    }

    public String getJourneyStartTime() {
        return journeyStartTime;
    }
}
