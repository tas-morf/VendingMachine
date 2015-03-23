package com.vending.android.module.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.vending.android.module.ApplicationModule.applicationContext;

/**
 * Just like all other modules, provides an object ready for injection. This is the way i am used to
 * achieving DI in Android, but this could also be done with Dagger or Roboguice.
 */
public class PreferencesModule {

    public static SharedPreferences sharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext());
    }
}
