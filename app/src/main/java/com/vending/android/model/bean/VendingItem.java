package com.vending.android.model.bean;

/**
 * Represents a particular vending item
 */
public enum VendingItem {
    Coke,
    Diet_Coke;

    @Override
    public String toString() {
        return super.toString().replace("_", " ");
    }
}
