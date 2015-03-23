package com.vending.android.model;

import com.vending.android.fake.FakeSharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PersistentVendingMachineTest {

    //Fake
    private FakeSharedPreferences fakeSharedPreferences = new FakeSharedPreferences();


    private PersistentVendingMachine sut;

    @Before
    public void setup() {
        sut = new PersistentVendingMachine(fakeSharedPreferences);
    }

    @Test
    public void returnsZeroStockWhenPreferencesEmpty() {
        assertThat(sut.getStockLevel(), equalTo(0));
    }

    @Test
    public void returnsAHundredCashWhenPreferencesEmpty() {
        assertThat(sut.getStoredCash(), equalTo(100f));
    }
    
    @Test
    public void usesTheSharedPreferencesToGetStockLevel() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 5);
        //then
        assertThat(sut.getStockLevel(), equalTo(5));
    }

    @Test
    public void usesTheSharedPreferencesToGetCashInPennies() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_CASH, 50);
        //then
        assertThat(sut.getStoredCash(), equalTo(0.5f));
    }

    @Test
    public void addsStockToPreferences() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 5);
        //when
        sut.addStock(10);
        //then
        assertThat(sut.getStockLevel(), equalTo(15));
    }

    
}
