package com.vending.android.view.adapter;

import com.vending.android.model.bean.VendingItemStock;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StockLevelsAdapter extends BaseAdapter {

    private final Context mContext;
    private List<VendingItemStock> mStockLevels;

    public StockLevelsAdapter(Context context, List<VendingItemStock> stockLevels) {
        mContext = context;
        mStockLevels = stockLevels;
    }

    @Override
    public int getCount() {
        return mStockLevels.size();
    }

    @Override
    public Object getItem(int position) {
        return mStockLevels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView result = (TextView)convertView;
        if(result == null) {
            result = new TextView(mContext);
        }
        VendingItemStock itemStock = mStockLevels.get(position);
        result.setText(itemStock.getVendingItem() + " - " + itemStock.getStockSize());
        return result;
    }

    public void setData(List<VendingItemStock> stockLevels) {
        mStockLevels = stockLevels;
        notifyDataSetChanged();
    }
}
