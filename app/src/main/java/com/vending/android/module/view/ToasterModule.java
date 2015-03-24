package com.vending.android.module.view;

import com.vending.android.view.AndroidToaster;
import com.vending.android.view.Toaster;

import static com.vending.android.module.ApplicationModule.applicationContext;

/**
 * Essentially a static factory for Toasters
 */
public class ToasterModule {

    public static Toaster toaster() {
        return new AndroidToaster(applicationContext());
    }
}
