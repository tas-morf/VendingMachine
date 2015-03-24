package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.controller.listener.PurchaseListener;
import com.vending.android.model.VendingMachine;
import com.vending.android.model.exception.InsufficientStockException;
import com.vending.android.view.PurchaseView;
import com.vending.android.view.Toaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import static com.vending.android.module.model.VendingMachineModule.vendingMachine;
import static com.vending.android.module.view.ToasterModule.toaster;


/**
 * Handles purchasing
 * This is not a great example of separation between view and controller, since this example is too
 * simple, and there's no need to hold a reference to the view in order to display more information,
 * but it should suffice I guess. Notice how this class essentially only holds a reference to an interface
 * (PurchaseView), and that's the only way it should affect the UI. Additionally, it passes itself
 * as a listener to the PurchaseView, to listen for UI events and handle them accordingly.
 */
public class PurchaseActivity extends Activity implements PurchaseListener {

    private final VendingMachine mVendingMachine;
    private final Toaster mToaster;
    private PurchaseView mPurchaseView;

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
        mPurchaseView = (PurchaseView) findViewById(R.id.main_view);
        mPurchaseView.setListener(this);
    }

    @Override
    public void onPurchaseItemRequested(int currentAmountInPennies) {
        try {
            int resultingChange = mVendingMachine.dispenseItem(currentAmountInPennies);
            if(resultingChange > 0) {
                mToaster.displayToast(getString(R.string.purchase_success,
                        resultingChange / 100f));
                finish();
            } else {
                mToaster.displayToast(R.string.insufficient_funds_failure);
            }
        } catch (InsufficientStockException e) {
            mToaster.displayToast(R.string.insufficient_stock_failure);
        } 
    }
}
