package com.workspaceit.pmc.validation.checkout;

public class CheckoutForm {
    private Float discount;
    private Float totalPaidAmount;
    private Float totalDue;


    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(Float totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public Float getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Float totalDue) {
        this.totalDue = totalDue;
    }
}
