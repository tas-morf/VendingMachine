package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;

import org.fest.assertions.api.ANDROID;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.view.View;
import android.widget.TextView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Mock
    private VendingMachine mockVendingMachine;

    private MainActivity sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new MainActivity(mockVendingMachine);
        ActivityController.of(sut).attach().create();
    }

    @Test
    public void onStartDisplaysStockLevel() throws Exception {
        //given
        when(mockVendingMachine.getStockLevel()).thenReturn(10);
        //when
        sut.onStart();
        //then
        ANDROID.assertThat((TextView)sut.findViewById(R.id.stock_level_text))
                .hasText("Stock Level: 10");
    }

    @Test
    public void onStartDisplaysStoredCash() throws Exception {
        //given
        when(mockVendingMachine.getStoredCash()).thenReturn(75);
        //when
        sut.onStart();
        //then
        ANDROID.assertThat((TextView)sut.findViewById(R.id.stored_cash_text))
                .hasText("Stored Cash: £0.75");
    }

    @Test
    public void onClickPurchaseStartsPurchaseActivity() throws Exception {
        //when
        sut.onClick(sut.findViewById(R.id.purchase_button));
        //then
        assertThat(shadowOf(sut).getNextStartedActivity().getComponent().getShortClassName(),
                equalTo(".controller.activity.PurchaseActivity"));

    }


    @Test
    public void onClickRestockStartsRestockActivity() throws Exception {
        //when
        sut.onClick(sut.findViewById(R.id.restock_button));
        //then
        assertThat(shadowOf(sut).getNextStartedActivity().getComponent().getShortClassName(),
                equalTo(".controller.activity.RestockActivity"));
    }
}