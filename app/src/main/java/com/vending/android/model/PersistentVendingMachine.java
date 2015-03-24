package com.vending.android.model;

import com.vending.android.model.exception.InsufficientStockException;

import android.content.SharedPreferences;

/**
 * Uses the SharedPreferences to save the stocks and cash for vending
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
    public int getStoredCash() {
        return mSharedPreferences.getInt(KEY_CASH, DEFAULT_CASH);
    }

    @Override
    public void addStock(int value) {
        mSharedPreferences.edit().putInt(KEY_STOCK, getStockLevel() + value).apply();
    }

    @Override
    public int dispenseItem(int penniesInserted) throws InsufficientStockException {
        int stockLevel = getStockLevel();
        int resultingChange = penniesInserted - mItemCostInPennies;
        if(stockLevel <= 0) {
            throw new InsufficientStockException();
        } else if (resultingChange > 0) {
            mSharedPreferences.edit()
                    .putInt(KEY_STOCK, stockLevel-1)
                    .putInt(KEY_CASH, getStoredCash() + mItemCostInPennies)
                    .apply();
        }
        return resultingChange;
        
    }
}
