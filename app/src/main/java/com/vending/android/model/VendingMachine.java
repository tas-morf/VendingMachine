package com.vending.android.model;

import com.vending.android.model.bean.VendingItemStock;

import java.util.List;

/**
 * The interface of the vending machine.
 */
public interface VendingMachine {

    List<VendingItemStock> getStockLevels();
}
