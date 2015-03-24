package com.vending.android.view;

import com.vending.android.controller.listener.PurchaseListener;

/**
 * Represents the View in the MVC way fo thinking for the Purchase flow
 */
public interface PurchaseView {

    /**
     * Set the listener for actions that happen in the UI
     * @param purchaseListener
     */
    void setListener(PurchaseListener purchaseListener);
}
