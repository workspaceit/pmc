package com.workspaceit.pmc.validation.checkout;

import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CheckoutValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        CheckoutForm checkoutForm = (CheckoutForm)o;



    }
}
