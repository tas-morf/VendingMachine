package com.vending.android.tests;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Checks only the visual elements in the main screen
 */
public class MainScreenTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainScreenTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MainActivity activity = getActivity();
        //make sure the preferences are clear
        PreferenceManager.getDefaultSharedPreferences(activity).edit().clear().apply();
    }

    public void testShowsPurchaseButton() {
        onView(withId(R.id.purchase_button))
                .check(matches(isDisplayed()))
                .check(matches(withText("Purchase")));
    }

    public void testShowsRestockButton() {
        onView(withId(R.id.restock_button))
                .check(matches(isDisplayed()))
                .check(matches(withText("Restock")));
    }
    
    public void testShowsEmptyStockLevel() {
        onView(withId(R.id.stock_level_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stock Level: 0")));
    }

    public void testShowsAHundredPoundsStoredCash() {
        onView(withId(R.id.stored_cash_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stored Cash: £100.00")));
    }

    public void testClickingRestockShowsRestockPrompt() {
        onView(withId(R.id.restock_button))
                .perform(click());
        onView(withText(R.string.how_many_items))
                .check(matches(isDisplayed()));
        
    }

    public void testClickingPurchaseShowsPurchasePrompt() {
        onView(withId(R.id.purchase_button))
                .perform(click());
        onView(withText("Each item costs £2.30. Please enter the correct amount or higher."))
                .check(matches(isDisplayed()));
    }
}
