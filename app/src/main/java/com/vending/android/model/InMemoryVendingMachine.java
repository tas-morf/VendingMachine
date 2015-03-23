package com.vending.android.model;

import com.vending.android.model.bean.VendingItem;
import com.vending.android.model.bean.VendingItemStock;

import java.util.Arrays;
import java.util.List;

/**
 * Uses a Map to store the stock information
 */
public class InMemoryVendingMachine implements VendingMachine {

    @Override
    public List<VendingItemStock> getStockLevels() {
        return Arrays.asList(VendingItemStock.newBuilder()
                .vendingItem(VendingItem.Coke)
                .stockSize(5)
                .build());
    }
}
