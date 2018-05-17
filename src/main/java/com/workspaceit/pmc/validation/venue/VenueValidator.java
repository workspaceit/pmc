package com.workspaceit.pmc.validation.venue;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class VenueValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return VenueForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors){

    }
}
