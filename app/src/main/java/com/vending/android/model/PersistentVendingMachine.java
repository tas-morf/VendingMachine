package com.vending.android.model;

import android.content.SharedPreferences;

/**
 * Persists the stocks for vending
 */
public class PersistentVendingMachine implements VendingMachine {

    static final String KEY_STOCK = "stock";
    static final String KEY_CASH = "cash";
    private static final int DEFAULT_CASH = 10000;

    private final SharedPreferences mSharedPreferences;

    public PersistentVendingMachine(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public int getStockLevel() {
        return mSharedPreferences.getInt(KEY_STOCK, 0);
    }

    @Override
    public float getStoredCash() {
        int cashInPennies = mSharedPreferences.getInt(KEY_CASH, DEFAULT_CASH);
        return cashInPennies / 100f;
    }

    @Override
    public void addStock(int value) {
        int existingStock = mSharedPreferences.getInt(KEY_STOCK, 0);
        mSharedPreferences.edit().putInt(KEY_STOCK, existingStock + value).apply();
    }
}
