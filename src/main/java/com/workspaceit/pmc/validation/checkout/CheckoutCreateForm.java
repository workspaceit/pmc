package com.workspaceit.pmc.validation.checkout;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CheckoutCreateForm extends CheckoutForm {

    @Override
    @NotNull(message = "Discount required")
    @Min(value = 0,message = "Discount can't be less then zero")
    public Float getDiscount() {
        return super.getDiscount();
    }

    @Override
    @NotNull(message = "Paid amount required")
    @Min(value = 0,message = "Paid amount can't be less then zero")
    public Float getTotalPaidAmount() {
        return super.getTotalPaidAmount();
    }

    @Override
    public Float getTotalDue() {
        return super.getTotalDue();
    }
}