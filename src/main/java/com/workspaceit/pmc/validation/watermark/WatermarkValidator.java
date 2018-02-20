package com.workspaceit.pmc.validation.watermark;

import com.workspaceit.pmc.validation.form.WatermarkForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WatermarkValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return WatermarkForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors){

    }

    public void validateForWatermarkPreview(WatermarkForm watermarkForm, Errors errors){
        Integer logoToken = watermarkForm.getLogoImgToken();
        if(logoToken==null || logoToken==0){
            errors.rejectValue("logoImgToken","Logo required");
        }
    }
}
