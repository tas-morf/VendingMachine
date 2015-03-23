package com.vending.android.model;

import com.vending.android.fake.FakeSharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.res.Resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PersistentVendingMachineTest {

    private static final int AN_ITEM_COST = 130;
    //Fake
    private FakeSharedPreferences fakeSharedPreferences = new FakeSharedPreferences();


    private PersistentVendingMachine sut;

    @Before
    public void setup() {
        sut = new PersistentVendingMachine(fakeSharedPreferences, AN_ITEM_COST);
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

    @Test
    public void doesNotDispenseIfStockIsEmpty() {
        //then
        assertThat(sut.dispenseItem(), equalTo(false));
    }

    @Test
    public void dispensesIfStockNotEmpty() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 1);
        //then
        assertThat(sut.dispenseItem(), equalTo(true));
    }

    @Test
    public void dispensingRemovesFromStock() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 5);
        //when
        sut.dispenseItem();
        //then
        assertThat(sut.getStockLevel(), equalTo(4));
    }
    
    @Test
    public void dispensingAddsMoney() {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 1);
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_CASH, 500);
        //when
        sut.dispenseItem();
        //then
        assertThat(sut.getStoredCash(), equalTo(5f + AN_ITEM_COST/100f));
    }
}
