package com.vending.android.model;

import com.vending.android.model.bean.VendingItem;
import com.vending.android.model.bean.VendingItemStock;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Persists the stocks for vending
 */
public class PersistentVendingMachine implements VendingMachine {

    private final SharedPreferences mSharedPreferences;

    public PersistentVendingMachine(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public List<VendingItemStock> getStockLevels() {
        List<VendingItemStock> result = new ArrayList<>();
        for (VendingItem item : VendingItem.values()) {
            int stock = mSharedPreferences.getInt(item.toString(), 0);
            if (stock != 0) {
                result.add(VendingItemStock.newBuilder()
                        .vendingItem(item)
                        .stockSize(stock)
                        .build());
            }
        }
        return result;
    }
}
