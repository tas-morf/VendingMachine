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
    private final int mItemCostInPennies;

    public PersistentVendingMachine(SharedPreferences sharedPreferences, int itemCostInPennies) {
        mSharedPreferences = sharedPreferences;
        mItemCostInPennies = itemCostInPennies;
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
        mSharedPreferences.edit().putInt(KEY_STOCK, getStockLevel() + value).apply();
    }

    @Override
    public boolean dispenseItem() {
        int stockLevel = getStockLevel();
        int cashInPennies = mSharedPreferences.getInt(KEY_CASH, DEFAULT_CASH);
        if(stockLevel > 0) {
            mSharedPreferences.edit()
                    .putInt(KEY_STOCK, stockLevel-1)
                    .putInt(KEY_CASH, cashInPennies + mItemCostInPennies)
                    .apply();
            return true;
        } 
        return false;
    }
}
