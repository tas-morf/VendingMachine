package com.vending.android.model;

import com.vending.android.fake.FakeSharedPreferences;
import com.vending.android.model.exception.InsufficientStockException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.content.res.Resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PersistentVendingMachineTest {

    private static final int AN_ITEM_COST = 130;
    private static final int AMOUNT_OFFSET = 15;
    private static final int INSUFFICIENT_AMOUNT = AN_ITEM_COST - AMOUNT_OFFSET;
    private static final int SUFFICIENT_AMOUNT = AN_ITEM_COST + AMOUNT_OFFSET;
    
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
    public void returnsTenThousandCashWhenPreferencesEmpty() {
        assertThat(sut.getStoredCash(), equalTo(10000));
    }
    
    @Test
    public void usesTheSharedPreferencesToGetStockLevel() {
        //given
        int initialStock = 5;
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, initialStock);
        //then
        assertThat(sut.getStockLevel(), equalTo(initialStock));
    }

    @Test
    public void usesTheSharedPreferencesToGetCashInPennies() {
        //given
        int initialCash = 50;
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_CASH, initialCash);
        //then
        assertThat(sut.getStoredCash(), equalTo(initialCash));
    }

    @Test
    public void addsStockToPreferences() {
        //given
        int initialStock = 5;
        int extraStock = 10;
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, initialStock);
        //when
        sut.addStock(extraStock);
        //then
        assertThat(sut.getStockLevel(), equalTo(15));
    }

    @Test(expected = InsufficientStockException.class)
    public void onDispenseThrowsIfStockIsEmpty() throws InsufficientStockException {
        //then
       sut.dispenseItem(0);
    }

    @Test
    public void returnsNegativeIfStockNotEmptyAndInsufficientAmount()
            throws InsufficientStockException {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 1);
        //then
        assertThat(sut.dispenseItem(INSUFFICIENT_AMOUNT), lessThan(0));
    }

    @Test
    public void doesNotRemoveFromStockIfInsufficientAmount()
            throws InsufficientStockException {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 5);
        //when
        sut.dispenseItem(INSUFFICIENT_AMOUNT);
        //then
        assertThat(sut.getStockLevel(), equalTo(5));
    }

    @Test
    public void returnsCorrectChangeWhenAmountCorrect()
            throws InsufficientStockException {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 1);
        //then
        assertThat(sut.dispenseItem(SUFFICIENT_AMOUNT), equalTo(AMOUNT_OFFSET));
    }
    
    @Test
    public void dispensingRemovesFromStock() throws InsufficientStockException {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 5);
        //when
        sut.dispenseItem(SUFFICIENT_AMOUNT);
        //then
        assertThat(sut.getStockLevel(), equalTo(4));
    }
    
    @Test
    public void dispensingAddsMoney() throws InsufficientStockException {
        //given
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_STOCK, 1);
        fakeSharedPreferences.edit().putInt(PersistentVendingMachine.KEY_CASH, 500);
        //when
        sut.dispenseItem(SUFFICIENT_AMOUNT);
        //then
        assertThat(sut.getStoredCash(), equalTo(500 + AN_ITEM_COST));
    }
}
