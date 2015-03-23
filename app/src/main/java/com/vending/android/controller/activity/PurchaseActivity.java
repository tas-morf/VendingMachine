package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;
import com.vending.android.view.Toaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.vending.android.module.model.VendingMachineModule.vendingMachine;
import static com.vending.android.module.view.ToasterModule.toaster;

/**
 * Handles purchasing
 */
public class PurchaseActivity extends Activity implements View.OnClickListener {

    private final VendingMachine mVendingMachine;
    private final Toaster mToaster;

    private TextView mAmountInsertedText;
    private View mOkButton;

    private int mCurrentAmountInPennies;
    private int mItemCost;

    public static Intent getPurchaseIntent(Context context) {
        return new Intent(context, PurchaseActivity.class);
    }
    
    public PurchaseActivity() {
        this(vendingMachine(), toaster());
    }

    public PurchaseActivity(VendingMachine vendingMachine, Toaster toaster) {
        mVendingMachine = vendingMachine;
        mToaster = toaster;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mOkButton = findViewById(R.id.ok_button);
        mOkButton.setEnabled(false);
        mOkButton.setOnClickListener(this);
        findViewById(R.id.one_penny_button).setOnClickListener(this);
        findViewById(R.id.two_penny_button).setOnClickListener(this);
        findViewById(R.id.five_penny_button).setOnClickListener(this);
        findViewById(R.id.ten_penny_button).setOnClickListener(this);
        findViewById(R.id.twenty_penny_button).setOnClickListener(this);
        findViewById(R.id.fifty_penny_button).setOnClickListener(this);
        findViewById(R.id.one_pound_button).setOnClickListener(this);
        findViewById(R.id.two_pounds_button).setOnClickListener(this);
        mItemCost = getResources().getInteger(R.integer.item_cost_in_pennies);
        ((TextView)findViewById(R.id.purchase_prompt)).setText(getString(R.string.purchase_instructions,
                mItemCost));
        mAmountInsertedText = (TextView)findViewById(R.id.amount_inserted_text);
        mAmountInsertedText.setText(getString(R.string.amount_inserted, 0f));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_penny_button:
                addPennies(1);
                break;
            case R.id.two_penny_button:
                addPennies(2);
                break;
            case R.id.five_penny_button:
                addPennies(5);
                break;
            case R.id.ten_penny_button:
                addPennies(10);
                break;
            case R.id.twenty_penny_button:
                addPennies(20);
                break;
            case R.id.fifty_penny_button:
                addPennies(50);
                break;
            case R.id.one_pound_button:
                addPennies(100);
                break;
            case R.id.two_pounds_button:
                addPennies(200);
                break;
            case R.id.ok_button:
                purchaseItem();
                break;
        }
    }

    private void purchaseItem() {
        
        boolean result = mVendingMachine.dispenseItem();
        if(result) {
            mToaster.displayToast(getString(R.string.purchase_success,
                    (mCurrentAmountInPennies - mItemCost) / 100f));
        } else {
            mToaster.displayToast(R.string.purchase_failure);
        }
        finish();
    }

    private void addPennies(int pennies) {
        mCurrentAmountInPennies +=pennies;
        mAmountInsertedText.setText(getString(R.string.amount_inserted, mCurrentAmountInPennies/100f));
        mOkButton.setEnabled(mCurrentAmountInPennies >= mItemCost);
    }
}
