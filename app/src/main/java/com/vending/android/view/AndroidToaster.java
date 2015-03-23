package com.vending.android.view;

import android.content.Context;
import android.widget.Toast;

public class AndroidToaster implements Toaster {

    private final Context mContext;

    public AndroidToaster(Context context) {
        mContext = context;
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayToast(int stringId) {
        Toast.makeText(mContext, stringId, Toast.LENGTH_LONG).show();
    }
}
