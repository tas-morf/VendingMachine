package com.vending.android.module.model;

import com.vending.android.R;
import com.vending.android.model.PersistentVendingMachine;
import com.vending.android.model.VendingMachine;

import static com.vending.android.module.ResourcesModule.resources;
import static com.vending.android.module.model.PreferencesModule.sharedPreferences;

public class VendingMachineModule {

    public static VendingMachine vendingMachine() {
        return new
                PersistentVendingMachine(sharedPreferences(), resources().getInteger(R.integer.item_cost_in_pennies));
    }
}
