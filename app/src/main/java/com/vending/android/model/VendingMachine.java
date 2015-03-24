package com.vending.android.model;

import com.vending.android.model.exception.InsufficientStockException;

/**
 * The interface of the vending machine.
 */
public interface VendingMachine {

    /**
     * @return The current stock level of the vending machine
     */
    int getStockLevel();

    /**
     * @return the currently stored cash in pennies
     */
    int getStoredCash();

    /**
     * @param value The amount of stock to add
     */
    void addStock(int value);

    /**
     * Dispence an item
     * @param penniesInserted the amount inserted in the vending machine in pennies
     * @return the change to be inserted. If less that 0, an insufficient amount was inserted
     * @throws InsufficientStockException If there's not enough stock to dispense
     */
    int dispenseItem(int penniesInserted) throws InsufficientStockException;
}
