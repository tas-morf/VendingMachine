package com.vending.android.tests;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
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
    
    public void testShowsEmptyStockLevel() {
        Espresso.onView(ViewMatchers.withId(R.id.stock_level_text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("Stock Level: 0")));
    }

    public void testShowsAHundredPoundsStoredCash() {
        Espresso.onView(ViewMatchers.withId(R.id.stored_cash_text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("Stored Cash: £100.00")));
    }

    public void testClickingRestockShowsRestockPrompt() {

        Espresso.onView(ViewMatchers.withId(R.id.restock_button))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText(R.string.how_many_items))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        
    }
}
