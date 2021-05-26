package com.example.samsapp.ui.schedule;

import org.json.JSONArray;

import java.util.HashMap;

public class Company {
    private String group;
    private String day;
    private JSONArray schedule;

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setSchedule(JSONArray schedule) {
        this.schedule = schedule;
    }

    public String getGroup() {
        return group;
    }

    public String getDay() {
        return day;
    }

    public JSONArray getSchedule() {
        return schedule;
    }
}
