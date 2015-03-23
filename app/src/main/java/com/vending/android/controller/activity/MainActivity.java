package com.vending.android.controller.activity;

import com.vending.android.R;
import com.vending.android.model.VendingMachine;
import com.vending.android.view.adapter.StockLevelsAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import static com.vending.android.module.model.VendingMachineModule.vendingMachine;

/**
 * Initial activity that shows the stock levels, and allows for purchasing or restocking
 */
public class MainActivity extends Activity implements View.OnClickListener {

    //injected
    private final VendingMachine mVendingMachine;
    
    //not injected
    private ListView mStockLevelsList;

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
        mStockLevelsList = (ListView)findViewById(R.id.stock_levels_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StockLevelsAdapter adapter = (StockLevelsAdapter) mStockLevelsList.getAdapter();
        if(adapter == null) {
            adapter = new StockLevelsAdapter(this, mVendingMachine.getStockLevels());
            mStockLevelsList.setAdapter(adapter);
        } else {
            adapter.setData(mVendingMachine.getStockLevels());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.purchase_button:
                break;
            case R.id.restock_button:
                break;
        }
    }
}
