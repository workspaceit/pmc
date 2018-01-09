package com.workspaceit.pmc.validation.location;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.StateService;
import com.workspaceit.pmc.validation.form.PhotographerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;


/**
 * Created by mi_rafi on 12/28/17.
 */
@Component
public class LocationValidator implements Validator {

    private StateService stateService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LocationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        LocationForm locationForm = (LocationForm)obj;
        State state = stateService.getById(locationForm.getStateId());

        Boolean hasSlideshow = locationForm.getHasSlideshow();
        if(hasSlideshow!=null && hasSlideshow){
            this.rejectIfNull("durationSpeed","Duration Speed is required",locationForm.getDurationSpeed(),errors);
            this.rejectIfNull("breakTime","Break Time is required",locationForm.getBreakTime(),errors);
            this.rejectIfNull("fadeInTime","Fade In Time is required",locationForm.getFadeInTime(),errors);
            this.rejectIfNull("fadeOutTime","Fade Out Time is required",locationForm.getFadeOutTime(),errors);
        }


        if(state==null){
            errors.rejectValue("stateId","State not found with id : "+locationForm.getStateId());
        }

    }
    private void rejectIfNull(String param,String msg,Double val,Errors errors){
        if(val==null){
            errors.rejectValue(param,msg);
        }
    }
}