package com.example.samsapp;

import android.app.Application;

public class GlobalClass extends Application{

    private boolean authorized;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}