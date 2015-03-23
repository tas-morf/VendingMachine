package com.vending.android.model;

/**
 * The interface of the vending machine.
 */
public interface VendingMachine {
    
    int getStockLevel();

    float getStoredCash();

    void addStock(int value);

    boolean dispenseItem();
}
