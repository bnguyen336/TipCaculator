package edu.orangecoastcollege.cs273.bnguyen336.tipcaculator;

/**
 * Created by binhn on 9/8/2016.
 */
public class RestaurantBill {
    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill(double amount, double tipPercent) {
        this.mAmount = amount;
        this.mTipPercent = tipPercent;
        recalculateAmounts();
    }

    public RestaurantBill() {
        mAmount = 0.0;
        mTipPercent = 0.0;
        mTipAmount = 0.0;
        mTotalAmount = 0.0;
    }



    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
        recalculateAmounts();
    }

    public double getTipPercent() {
        return mTipPercent;
    }

    public void setTipPercent(double tipPercent) {
        mTipPercent = tipPercent;
        recalculateAmounts();
    }

    public double getTipAmount() {
        return mTipAmount;
    }

    public double getTotalAmount() {
        return mTotalAmount;
    }

    private void recalculateAmounts() {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mAmount + mTipAmount;
    }
}
