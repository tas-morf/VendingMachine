package com.vending.android.module;

import android.content.res.Resources;

import static com.vending.android.module.ApplicationModule.applicationContext;

/**
 * Just like all other modules, provides an object ready for injection. This is the way i am used to
 * achieving DI in Android, but this could also be done with Dagger or Roboguice.
 */
public class ResourcesModule {

    public static Resources resources() {
        return applicationContext().getResources();
    }
}
