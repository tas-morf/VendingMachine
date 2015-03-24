package com.vending.android.controller.listener;

public interface PurchaseListener {

    PurchaseListener NO_OP = new PurchaseListener() {
        @Override
        public void onPurchaseItemRequested(int currentAmountInPennies) {

        }
    };

    void onPurchaseItemRequested(int currentAmountInPennies);
}
