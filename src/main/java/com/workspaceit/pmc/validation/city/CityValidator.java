package com.workspaceit.pmc.validation.city;

import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by anik on 5/17/18.
 */

@Component
public class CityValidator implements Validator {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    private void checkStateExistence(int stateId, Errors errors){
        State state =  this.stateService.getById(stateId);
        if(state == null){
            errors.rejectValue("stateId","State is not found");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CityForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CityForm cityForm = (CityForm) o;
        this.checkStateExistence(cityForm.getCityStateId(), errors);
    }

}
