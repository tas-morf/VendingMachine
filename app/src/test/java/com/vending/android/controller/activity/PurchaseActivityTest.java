package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;
import com.vending.android.model.exception.InsufficientStockException;
import com.vending.android.view.Toaster;

import org.fest.assertions.api.ANDROID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.widget.TextView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class PurchaseActivityTest {

    @Mock
    private VendingMachine mockVendingMachine;
    @Mock
    private Toaster mockToaster;

    private PurchaseActivity sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new PurchaseActivity(mockVendingMachine, mockToaster);
        ActivityController.of(sut).attach().create();
    }

    @Test
    public void onPurchaseWithNoStockDisplaysInsufficientStockToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenThrow(new InsufficientStockException());
        //when
        sut.onPurchaseItemRequested(10);
        //then
        verify(mockToaster).displayToast(R.string.insufficient_stock_failure);
    }

    @Test
    public void onPurchaseWithIncorrectAmountDisplaysInsufficientFundsToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenReturn(-1);
        //when
        sut.onPurchaseItemRequested(10);
        //then
        verify(mockToaster).displayToast(R.string.insufficient_funds_failure);
    }

    @Test
    public void onPurchaseWithCorrectAmountDisplaysSuccessToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenReturn(1);
        //when
        sut.onPurchaseItemRequested(10);
        //then
        verify(mockToaster).displayToast(eq(sut.getString(R.string.purchase_success, 0.01f)));
    }

    @Test
    public void onPurchaseWithCorrectAmountFinishesActivity() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenReturn(1);
        //when
        sut.onPurchaseItemRequested(10);
        //then
        assertThat(shadowOf(sut).isFinishing(), equalTo(true));
    }
}