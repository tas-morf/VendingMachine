package com.vending.android.tests;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.preference.PreferenceManager;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.vending.android.tests.action.CustomActions.setNumberPickerTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Tests the purchasing flows
 */
public class PurchaseTest  extends ActivityInstrumentationTestCase2<MainActivity> {


    private MainActivity mActivity;

    public PurchaseTest() {
        super(MainActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        //make sure the preferences are clear
        PreferenceManager.getDefaultSharedPreferences(mActivity).edit().clear().apply();
        

    }

    public void testAmountButtons() {
        onView(withId(R.id.purchase_button))
                .perform(click());

        onView(withId(R.id.one_penny_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.01")));

        onView(withId(R.id.two_penny_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.03")));

        onView(withId(R.id.five_penny_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.08")));

        onView(withId(R.id.ten_penny_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.18")));

        onView(withId(R.id.twenty_penny_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.38")));

        onView(withId(R.id.fifty_penny_button))
                .perform(click());
        
        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £0.88")));

        onView(withId(R.id.one_pound_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £1.88")));

        onView(withId(R.id.two_pounds_button))
                .perform(click());

        onView(withId(R.id.amount_inserted_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Amount inserted: £3.88")));
        
    }

    public void testOkButtonDisabled() {
        //when
        onView(withId(R.id.purchase_button))
                .perform(click());

        //then
        onView(withId(R.id.ok_button))
                .check(matches(isDisplayed()))
                .check(matches(not(isEnabled())));
    
    
    }
    
    public void testOkButtonNotEnabledBeforeCorrectAmount() {
        //given
        onView(withId(R.id.purchase_button))
                .perform(click());
        
        //when
        onView(withId(R.id.two_pounds_button))
                .perform(click());

        onView(withId(R.id.ten_penny_button))
                .perform(click(), click());
        
        //then
        onView(withId(R.id.ok_button))
                .check(matches(not(isEnabled())));
    }

    public void testOkButtonEnabledOnCorrectAmount() {
        //given
        onView(withId(R.id.purchase_button))
                .perform(click());
    
        //when
        onView(withId(R.id.two_pounds_button))
                .perform(click());
        onView(withId(R.id.ten_penny_button))
                .perform(click(), click(), click());

        //then
        onView(withId(R.id.ok_button))
                .check(matches(isEnabled()));

    }

    public void testPurchaseWithoutStock() {
        //given
        onView(withId(R.id.purchase_button))
                .perform(click());
        onView(withId(R.id.two_pounds_button))
                .perform(click());
        onView(withId(R.id.ten_penny_button))
                .perform(click(), click(), click());

        //when
        onView(withId(R.id.ok_button))
                .perform(click());

        //then
        //FIXME: toasts are unreliable, since they queue up, so on any test, the previous toast might 
        // be showing. If we did really want to test toasts, the next lines can be uncommented
//        onView(withText(R.string.purchase_failure))
//                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
        onView(withId(R.id.stored_cash_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stored Cash: £100.00")));
    }

    public void testPurchaseWithStock() {
        //given
        onView(withId(R.id.restock_button))
                .perform(click());
        onView(withId(R.id.number_picker))
                .perform(setNumberPickerTo(5));
        onView(withId(R.id.ok_button))
                .perform(click());
        
        onView(withId(R.id.purchase_button))
                .perform(click());
        onView(withId(R.id.two_pounds_button))
                .perform(click());
        onView(withId(R.id.ten_penny_button))
                .perform(click(), click(), click());

        //when
        onView(withId(R.id.ok_button))
                .perform(click());

        //then
        //FIXME: toasts are unreliable, since they queue up, so on any test, the previous toast might
        // be showing. If we did really want to test toasts, the next lines can be uncommented

//        onView(withText("Your purchase was successful. You\'ll receive £0.00 in change."))
//                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
        onView(withId(R.id.stored_cash_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stored Cash: £102.30")));
        onView(withId(R.id.stock_level_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stock Level: 4")));
    }

}
