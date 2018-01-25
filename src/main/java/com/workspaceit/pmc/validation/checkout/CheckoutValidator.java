package com.workspaceit.pmc.validation.checkout;

import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Map;
@Component
public class CheckoutValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        CheckoutForm checkoutForm = (CheckoutForm)o;

    }
    public List<Map<String, String>> validatationForService(CheckoutForm checkoutForm,float total,float previouslyPaidAmount) {
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        float totalPayable = total- previouslyPaidAmount;
        float totalAfterDiscount = total - checkoutForm.getDiscount();

        if(checkoutForm.getDiscount()>totalPayable){
            serviceResponse.setValidationError("discount","Discount is can't be greater payable amount");
        }

        float balance = totalAfterDiscount - (checkoutForm.getTotalPaidAmount()+previouslyPaidAmount) ;

        if(balance<0){
            serviceResponse.setValidationError("totalPaidAmount","Payment is greater then payable amount");
        }
        return serviceResponse.getFormError();

    }
}
