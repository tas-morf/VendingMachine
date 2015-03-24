package com.vending.android.model;

import com.vending.android.model.exception.InsufficientStockException;

/**
 * The interface of the vending machine.
 */
public interface VendingMachine {
    
    int getStockLevel();

    int getStoredCash();

    void addStock(int value);

    int dispenseItem(int penniesInserted) throws InsufficientStockException;
}
