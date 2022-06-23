package com.example.we_play.Recyclerview;

public class reserved_data {
    private String thumbnail_url;
    private String place;
    private String location;
    private String ticket_date;
    private String ticket_participant;
    private String ticket_price;

    public reserved_data(String thumbnail_url, String place, String location, String ticket_date, String ticket_participant, String ticket_price) {
        this.thumbnail_url = thumbnail_url;
        this.place = place;
        this.location = location;
        this.ticket_date = ticket_date;
        this.ticket_participant = ticket_participant;
        this.ticket_price = ticket_price;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTicket_date() {
        return ticket_date;
    }

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
    }

    public String getTicket_participant() {
        return ticket_participant;
    }

    public void setTicket_participant(String ticket_participant) {
        this.ticket_participant = ticket_participant;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }
}