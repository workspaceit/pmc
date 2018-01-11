package com.workspaceit.pmc.validation.advertisement.price;

import com.workspaceit.pmc.entity.AdvertisementPrices;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tomal on 1/10/2018.
 */
@Component
public class AdvertisementPricesValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AdvertisementPricesForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors){

    }
}
