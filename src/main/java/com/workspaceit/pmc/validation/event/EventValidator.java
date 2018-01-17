package com.workspaceit.pmc.validation.event;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by anik on 1/17/18.
 */
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

}
