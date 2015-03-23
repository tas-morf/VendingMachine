package com.vending.android.view;

/**
 * Displays toasts
 */
public interface Toaster {

    void displayToast(String message);

    void displayToast(int stringId);
}
