package com.vending.android.tests;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.vending.android.tests.action.CustomActions.setNumberPickerTo;

/**
 * Tests the restocking function
 */
public class RestockTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public RestockTest() {
        super(MainActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        MainActivity activity = getActivity();
        //make sure the preferences are clear
        PreferenceManager.getDefaultSharedPreferences(activity).edit().clear().apply();
        onView(withId(R.id.restock_button))
                .perform(click());

    }

    public void testAddingFiveItemsIsReflectedInTheMainActivity() {
        onView(withText(R.string.how_many_items))
                .check(matches(isDisplayed()));
        onView(withId(R.id.number_picker))
                .perform(setNumberPickerTo(5));
        onView(withId(R.id.ok_button))
                .perform(click());
        onView(withId(R.id.stock_level_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stock Level: 5")));
    }

    public void testCancelingFiveItemsIsReflectedInTheMainActivity() {
        onView(withText(R.string.how_many_items))
                .check(matches(isDisplayed()));
        onView(withId(R.id.number_picker))
                .perform(setNumberPickerTo(5))
                .perform(pressBack());
        onView(withId(R.id.stock_level_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stock Level: 0")));
    }
}
