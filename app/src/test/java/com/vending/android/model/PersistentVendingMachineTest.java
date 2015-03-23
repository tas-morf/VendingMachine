package com.vending.android.model;

import com.vending.android.fake.FakeSharedPreferences;
import com.vending.android.model.bean.VendingItem;
import com.vending.android.model.bean.VendingItemStock;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class PersistentVendingMachineTest {

    //Fake
    private FakeSharedPreferences fakeSharedPreferences = new FakeSharedPreferences();


    private PersistentVendingMachine sut;

    @Before
    public void setup() {
        sut = new PersistentVendingMachine(fakeSharedPreferences);
    }

    @Test
    public void returnsEmptyListWhenPreferencesEmpty() {
        //when
        List<VendingItemStock> stockLevels = sut.getStockLevels();
        //then
        assertThat(stockLevels, hasSize(0));
    }
    
    @Test
    public void usesTheSharedPreferencesToGetStockLevels() {
        //given
        fakeSharedPreferences.put(VendingItem.Coke.toString(), 5);
        fakeSharedPreferences.put(VendingItem.Diet_Coke.toString(), 6);
        //when
        List<VendingItemStock> stockLevels = sut.getStockLevels();
        //then
        assertThat(stockLevels, hasSize(2));
        assertThat(stockLevels.get(0).getStockSize(), equalTo(5));
        assertThat(stockLevels.get(1).getStockSize(), equalTo(6));
        assertThat(stockLevels.get(0).getVendingItem(), equalTo(VendingItem.Coke));
        assertThat(stockLevels.get(1).getVendingItem(), equalTo(VendingItem.Diet_Coke));
    }


    @Test
    public void omitsZeroStockItems() {
        //given
        fakeSharedPreferences.put(VendingItem.Coke.toString(), 5);
        fakeSharedPreferences.put(VendingItem.Diet_Coke.toString(), 0);
        //when
        List<VendingItemStock> stockLevels = sut.getStockLevels();
        //then
        assertThat(stockLevels, hasSize(1));
        assertThat(stockLevels.get(0).getStockSize(), equalTo(5));
        assertThat(stockLevels.get(0).getVendingItem(), equalTo(VendingItem.Coke));
    }
}
