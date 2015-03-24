package com.vending.android.module.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.vending.android.module.ApplicationModule.applicationContext;

/**
 * Gets the shared preferences to be injected
 */
public class PreferencesModule {

    public static SharedPreferences sharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext());
    }
}
