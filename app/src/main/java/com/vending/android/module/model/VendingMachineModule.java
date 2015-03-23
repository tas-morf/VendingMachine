package com.vending.android.module.model;

import com.vending.android.model.InMemoryVendingMachine;
import com.vending.android.model.VendingMachine;

public class VendingMachineModule {

    public static VendingMachine vendingMachine() {
        return new InMemoryVendingMachine();
    }
}
