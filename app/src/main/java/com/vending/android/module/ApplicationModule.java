package com.vending.android.module;

import com.vending.android.VendingApplication;

import android.content.Context;

/**
 * Just like all other modules, provides an object ready for injection. This is the way i am used to
 * achieving DI in Android, but this could also be done with Dagger or Roboguice.
 */
public class ApplicationModule {

    //Notice how this is not a factory, but simply reuses a static instance
    private static VendingApplication mSVendingApplication;

    public static void setApplication(VendingApplication vendingApplication) {
        mSVendingApplication = vendingApplication;
    }
    
    public static Context applicationContext() {
        return mSVendingApplication;
    }
}
