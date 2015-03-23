package com.vending.android.tests.action;

import org.hamcrest.Matcher;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPickerSetAction implements ViewAction {

    private final int mValue;

    public NumberPickerSetAction(int value) {
        mValue = value;
    }

    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isAssignableFrom(NumberPicker.class);
    }

    @Override
    public String getDescription() {
        return "Set the passed number into the NumberPicker";
    }

    @Override
    public void perform(UiController uiController, View view) {
        NumberPicker picker = (NumberPicker) view;
        picker.setValue(mValue);
    }
}
