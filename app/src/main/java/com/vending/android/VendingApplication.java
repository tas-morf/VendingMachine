package com.vending.android;

import com.vending.android.module.ApplicationModule;

import android.app.Application;

public class VendingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //This is necessary in order to be able to do DI in the rest of the app. This is the only place
        //where you'd have a setter in a module, and should only be done at the very start of the app.
        ApplicationModule.setApplication(this);
    }
}
