package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;
import com.vending.android.view.Toaster;

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

import android.widget.NumberPicker;
import android.widget.TextView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class RestockActivityTest {

    @Mock
    private VendingMachine mockVendingMachine;

    private RestockActivity sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new RestockActivity(mockVendingMachine);
        ActivityController.of(sut).attach().create();
    }

    @Test
    public void onClickOkAddsTheNumberPickerValue() throws Exception {
        //given
        int selectedStockToAdd = 55;
        ((NumberPicker)sut.findViewById(R.id.number_picker)).setValue(selectedStockToAdd);
        //when
        sut.onClick(sut.findViewById(R.id.ok_button));
        //then
        verify(mockVendingMachine).addStock(selectedStockToAdd);
    }

    @Test
    public void onClickOkFinishesActivity() throws Exception {
        //when
        sut.onClick(sut.findViewById(R.id.ok_button));
        //then
        assertThat(shadowOf(sut).isFinishing(), equalTo(true));
    }
}