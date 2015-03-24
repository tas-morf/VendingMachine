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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.widget.TextView;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void showsCorrectItemCost() throws Exception {
        ANDROID.assertThat((TextView)sut.findViewById(R.id.purchase_prompt))
                .hasText("Each item costs £2.30. Please enter the correct amount or higher.");
    }

    @Test
    public void showsZeroAmountInserted() throws Exception {
        ANDROID.assertThat((TextView)sut.findViewById(R.id.amount_inserted_text))
                .hasText("Amount inserted: £0.00");
    }

    @Test
    public void onClickOnePennyAddsOnePenny() throws Exception {
        //when
        sut.onClick(sut.findViewById(R.id.one_penny_button));
        //then
        ANDROID.assertThat((TextView)sut.findViewById(R.id.amount_inserted_text))
                .hasText("Amount inserted: £0.01");
    }
    
    //... Add tests for the rest of the amount buttons, though this is covered by instrumentation

    @Test
    public void onClickOkWithNoStockDisplaysInsufficientStockToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenThrow(new InsufficientStockException());
        //when
        sut.onClick(sut.findViewById(R.id.ok_button));
        //then
        verify(mockToaster).displayToast(R.string.insufficient_stock_failure);
    }

    @Test
    public void onClickOkWithIncorrectAmountDisplaysInsufficientFundsToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenReturn(-1);
        //when
        sut.onClick(sut.findViewById(R.id.ok_button));
        //then
        verify(mockToaster).displayToast(R.string.insufficient_funds_failure);
    }

    @Test
    public void onClickOkWithCorrectAmountDisplaysSuccessToast() throws Exception {
        //given
        when(mockVendingMachine.dispenseItem(anyInt())).thenReturn(1);
        //when
        sut.onClick(sut.findViewById(R.id.ok_button));
        //then
        verify(mockToaster).displayToast(eq(sut.getString(R.string.purchase_success, 0.01f)));
    }
}