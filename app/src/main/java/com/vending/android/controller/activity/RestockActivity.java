package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;
import com.vending.android.module.model.VendingMachineModule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import static com.vending.android.module.model.VendingMachineModule.vendingMachine;

/**
 * Handles restocking
 */
public class RestockActivity extends Activity implements View.OnClickListener {

    private static final int MAX_RESTOCK = 200;
    
    private final VendingMachine mVendingMachine;
    
    private NumberPicker mNumberPicker;

    public RestockActivity() {
        this(vendingMachine());
    }

    public RestockActivity(VendingMachine vendingMachine) {
        mVendingMachine = vendingMachine;
    }

    public static Intent getRestockIntent(Context context) {
        return new Intent(context, RestockActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        findViewById(R.id.ok_button).setOnClickListener(this);
        mNumberPicker = (NumberPicker)findViewById(R.id.number_picker);
        mNumberPicker.setMaxValue(MAX_RESTOCK);
        mNumberPicker.setMinValue(0);
    }

    @Override
    public void onClick(View v) {
        mVendingMachine.addStock(mNumberPicker.getValue());
        finish();
    }
}
