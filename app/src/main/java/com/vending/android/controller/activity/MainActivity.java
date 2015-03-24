package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.vending.android.module.model.VendingMachineModule.vendingMachine;

/**
 * Initial activity that shows the stock levels, and allows for purchasing or restocking
 */
public class MainActivity extends Activity implements View.OnClickListener {

    //injected
    private final VendingMachine mVendingMachine;
    
    //not injected
    private TextView mStockLevelText;
    private TextView mStoredCashText;

    public MainActivity() {
        this(vendingMachine());   
    }

    public MainActivity(VendingMachine vendingMachine) {
        mVendingMachine = vendingMachine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.purchase_button).setOnClickListener(this);
        findViewById(R.id.restock_button).setOnClickListener(this);
        mStockLevelText = (TextView)findViewById(R.id.stock_level_text);
        mStoredCashText = (TextView)findViewById(R.id.stored_cash_text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStockLevelText.setText(getString(R.string.stock_level, mVendingMachine.getStockLevel()));
        mStoredCashText.setText(getString(R.string.stored_cash, mVendingMachine.getStoredCash()/100f));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.purchase_button:
                startActivity(PurchaseActivity.getPurchaseIntent(this));
                break;
            case R.id.restock_button:
                startActivity(RestockActivity.getRestockIntent(this));
                break;
        }
    }
}
