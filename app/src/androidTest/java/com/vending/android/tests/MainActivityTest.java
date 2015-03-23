package com.vending.android.tests;

import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    
    public MainActivityTest() {
        super(MainActivity.class);
    }

    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testShowsPurchaseButton() {
        Espresso.onView(ViewMatchers.withId(R.id.purchase_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("Purchase")));
    }

    public void testShowsRestockButton() {
        Espresso.onView(ViewMatchers.withId(R.id.restock_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("Restock")));
    }
    
    public void testShowsCokeStockLevels() {
        Espresso.onView(ViewMatchers.withId(R.id.stock_levels_list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Coke - 5"))));
    }
}
