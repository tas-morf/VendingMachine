package com.vending.android;

import com.vending.android.module.ApplicationModule;

import android.app.Application;

public class VendingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule.setApplication(this);
    }
}
