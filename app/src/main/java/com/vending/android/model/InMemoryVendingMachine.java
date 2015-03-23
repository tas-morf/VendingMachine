package com.vending.android.model;

/**
 * Uses a Map to store the stock information
 */
public class InMemoryVendingMachine implements VendingMachine {


    @Override
    public int getStockLevel() {
        return 0;
    }

    @Override
    public float getStoredCash() {
        return 0;
    }
}
