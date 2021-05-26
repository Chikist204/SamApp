package com.example.samsapp.ui.about;

import java.util.HashMap;

public class Company {
    private String name;
    private String date;
    private String place;
    private String schedule;
    private String contact;
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String[] toArr(){
        String[] s = {this.name, this.date, this.place, this.schedule, this.contact, this.address};
        return s;
    }
}
