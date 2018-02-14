package com.workspaceit.pmc.validation.location;

import com.workspaceit.pmc.dao.PhotographerDao;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.service.CityService;
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
    private CityService cityService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCityService(CityService cityService){this.cityService = cityService;}

    @Override
    public boolean supports(Class<?> aClass) {
        return LocationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        LocationForm locationForm = (LocationForm)obj;
        State state = stateService.getById(locationForm.getStateId());

        City city = cityService.getById(locationForm.getCityId());

        Boolean hasSlideshow = locationForm.getHasSlideshow();
        if(hasSlideshow!=null && hasSlideshow){
            this.rejectIfNull("durationSpeed","Duration Speed is required",locationForm.getDurationSpeed(),errors);
            this.rejectIfNull("breakTime","Break Time is required",locationForm.getBreakTime(),errors);
            this.rejectIfNull("fadeInTime","Fade In Time is required",locationForm.getFadeInTime(),errors);
            this.rejectIfNull("fadeOutTime","Fade Out Time is required",locationForm.getFadeOutTime(),errors);

        }
        if(errors.getFieldErrorCount("durationSpeed")==0){
            this.checkDurationSpeed(locationForm.getDurationSpeed(),errors);
        }
        if(errors.getFieldErrorCount("breakTime")==0){
            this.checkAdBreakTime(locationForm.getBreakTime(),errors);
        }


        if(state==null){
            errors.rejectValue("stateId","State not found with id : "+locationForm.getStateId());
        }

        if(city==null){
            errors.rejectValue("cityId","City not found with id : "+locationForm.getCityId());
        }

    }
    private void rejectIfNull(String param,String msg,Double val,Errors errors){
        if(val==null){
            errors.rejectValue(param,msg);
        }
    }
    private void checkDurationSpeed(Double durationSpeed,Errors errors){
        if(durationSpeed<0){
            errors.rejectValue("durationSpeed","Duration speed positive value required");
        }

    }
    private void checkAdBreakTime(Double addBreakTime,Errors errors){
        if(addBreakTime<0) {
            errors.rejectValue("breakTime", "Break Time positive value required");
        }
    }
}