package com.vending.android.model.bean;

public class VendingItemStock {

    private int stockSize;
    private VendingItem vendingItem;

    private VendingItemStock(Builder builder) {
        stockSize = builder.stockSize;
        vendingItem = builder.vendingItem;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getStockSize() {
        return stockSize;
    }

    public VendingItem getVendingItem() {
        return vendingItem;
    }


    public static final class Builder {

        private int stockSize;
        private VendingItem vendingItem;

        private Builder() {
        }

        public Builder stockSize(int stockSize) {
            this.stockSize = stockSize;
            return this;
        }

        public Builder vendingItem(VendingItem vendingItem) {
            this.vendingItem = vendingItem;
            return this;
        }

        public VendingItemStock build() {
            return new VendingItemStock(this);
        }
    }
}
