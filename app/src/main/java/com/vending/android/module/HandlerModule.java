package com.vending.android.module;

import android.os.Handler;

import static com.vending.android.module.ApplicationModule.applicationContext;

/**
 * Just like all other modules, provides an object ready for injection. This is the way i am used to
 * achieving DI in Android, but this could also be done with Dagger or Roboguice.
 */
public class HandlerModule {

    public static Handler mainThreadHandler() {
        return new Handler(applicationContext().getMainLooper());
    }
}
