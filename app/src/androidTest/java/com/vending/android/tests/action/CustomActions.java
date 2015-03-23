package com.vending.android.tests.action;

import android.support.test.espresso.ViewAction;

public class CustomActions {

    public static ViewAction setNumberPickerTo(int number) {
        return new NumberPickerSetAction(number);
    }
}
