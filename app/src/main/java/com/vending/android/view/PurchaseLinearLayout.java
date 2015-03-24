package com.vending.android.view;

import com.vending.android.R;
import com.vending.android.controller.listener.PurchaseListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This class should be pretty dumb and simply manage the UI,  without doing any business logic.
 */
public class PurchaseLinearLayout extends LinearLayout implements PurchaseView,
        View.OnClickListener {

    private TextView mAmountInsertedText;
    private int mCurrentAmountInPennies;
    private PurchaseListener mPurchaseListener = PurchaseListener.NO_OP;

    public PurchaseLinearLayout(Context context) {
        super(context);
    }

    public PurchaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PurchaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R.id.ok_button).setOnClickListener(this);
        findViewById(R.id.one_penny_button).setOnClickListener(this);
        findViewById(R.id.two_penny_button).setOnClickListener(this);
        findViewById(R.id.five_penny_button).setOnClickListener(this);
        findViewById(R.id.ten_penny_button).setOnClickListener(this);
        findViewById(R.id.twenty_penny_button).setOnClickListener(this);
        findViewById(R.id.fifty_penny_button).setOnClickListener(this);
        findViewById(R.id.one_pound_button).setOnClickListener(this);
        findViewById(R.id.two_pounds_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.purchase_prompt)).setText(getContext().getString(
                R.string.purchase_instructions,
                getResources().getInteger(R.integer.item_cost_in_pennies) / 100f));
        mAmountInsertedText = (TextView)findViewById(R.id.amount_inserted_text);
        mAmountInsertedText.setText(getContext().getString(R.string.amount_inserted, 0f));
    }

    @Override
    public void setListener(PurchaseListener purchaseListener) {
        mPurchaseListener = purchaseListener;
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
                mPurchaseListener.onPurchaseItemRequested(mCurrentAmountInPennies);
                break;
        }
    }

    private void addPennies(int pennies) {
        mCurrentAmountInPennies += pennies;
        mAmountInsertedText.setText(
                getContext().getString(R.string.amount_inserted, mCurrentAmountInPennies / 100f));
    }
}
