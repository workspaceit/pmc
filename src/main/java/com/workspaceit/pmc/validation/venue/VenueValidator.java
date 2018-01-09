package com.workspaceit.pmc.validation.venue;

import com.workspaceit.pmc.validation.venue.VenueForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tomal on 1/8/2018.
 */
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
