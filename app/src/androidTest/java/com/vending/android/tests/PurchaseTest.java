package com.vending.android.tests;

import com.vending.android.R;
import com.vending.android.controller.activity.MainActivity;

import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.vending.android.tests.action.CustomActions.setNumberPickerTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Tests the purchasing flows
 */
public class PurchaseTest  extends ActivityInstrumentationTestCase2<MainActivity> {


    private static final long TOAST_DURATION = 5000;
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

    public void testPurchaseWithoutStock() throws InterruptedException {
        //given
        onView(withId(R.id.purchase_button))
                .perform(click());
        onView(withId(R.id.two_pounds_button))
                .perform(click());
        onView(withId(R.id.ten_penny_button))
                .perform(click(), click(), click());

        //Since we check toasts on this test, we need to wait first, in order to make sure no toasts
        //from previous tests are displayed.
        Thread.sleep(TOAST_DURATION);
        
        //when
        onView(withId(R.id.ok_button))
                .perform(click());

        //then
        onView(withText(R.string.insufficient_stock_failure))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    public void testPurchaseWithStockAndInsufficientFunds() throws InterruptedException {
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
                .perform(click(), click());
        
        //Since we check toasts on this test, we need to wait first, in order to make sure no toasts
        //from previous tests are displayed.
        Thread.sleep(TOAST_DURATION);
        
        //when
        onView(withId(R.id.ok_button))
                .perform(click());

        //then
        onView(withText(R.string.insufficient_funds_failure))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    public void testPurchaseWithStockAndSufficientFunds() throws InterruptedException {
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
        onView(withId(R.id.one_pound_button))
                .perform(click());

        //Since we check toasts on this test, we need to wait first, in order to make sure no toasts
        //from previous tests are displayed.
        Thread.sleep(TOAST_DURATION);
        
        //when
        onView(withId(R.id.ok_button))
                .perform(click());
        
        //then
        onView(withText("Your purchase was successful. You\'ll receive £0.70 in change."))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withId(R.id.stored_cash_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stored Cash: £102.30")));
        onView(withId(R.id.stock_level_text))
                .check(matches(isDisplayed()))
                .check(matches(withText("Stock Level: 4")));
    }

}
