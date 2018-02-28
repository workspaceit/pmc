package com.workspaceit.pmc.validation.watermark;

import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WatermarkType;
import com.workspaceit.pmc.entity.Font;
import com.workspaceit.pmc.service.FontService;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.awt.*;

@Component
public class WatermarkValidator implements Validator {

    private FontService fontService;

    @Autowired
    public void setFontService(FontService fontService) {
        this.fontService = fontService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return WatermarkForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors){
        WatermarkForm watermarkForm = (WatermarkForm) obj;
        ValidationUtils.rejectIfEmpty(errors,"name","Name is required");

        if(watermarkForm.getType()==null){
            errors.rejectValue("type","Type required");
            return;
        }

        if(watermarkForm.getType().equals(WatermarkType.image)){
            this.tokenCheck(watermarkForm.getLogoImgToken(),errors);
            this.commonValidationForTypeImage(watermarkForm,errors);
        }
        else if(watermarkForm.getType().equals(WatermarkType.text)){
            this.commonValidationForTypeText(watermarkForm,errors);
        }
    }
    public void validateForUpdate( WatermarkForm watermarkForm , Errors errors){
        ValidationUtils.rejectIfEmpty(errors,"name","Name is required");

        if(watermarkForm.getType()==null){
            errors.rejectValue("type","Type required");
            return;
        }

        if(watermarkForm.getType().equals(WatermarkType.image)){
            this.commonValidationForTypeImage(watermarkForm,errors);
        }
        else if(watermarkForm.getType().equals(WatermarkType.text)){
            this.commonValidationForTypeText(watermarkForm,errors);
        }
    }
    public void commonValidationForTypeImage(WatermarkForm watermarkForm , Errors errors){

        ValidationUtils.rejectIfEmpty(errors,"logoName","Logo name required");
        this.checkPlacement(watermarkForm.getPlacement(),errors);
        this.checkSize(watermarkForm.getSize(),errors);
        this.checkFade(watermarkForm.getFade(),errors);
    }
    public void commonValidationForTypeText(WatermarkForm watermarkForm , Errors errors){
        ValidationUtils.rejectIfEmpty(errors,"txtLogoName","Logo name required");
        ValidationUtils.rejectIfEmpty(errors,"watermarkText","Watermark required");
        this.checkColor(watermarkForm.getColor(),errors);
        this.checkFont(watermarkForm.getFontId(),errors);
    }
    public void validateForWatermarkPreview(WatermarkForm watermarkForm, Errors errors){

        this.tokenCheck(watermarkForm.getLogoImgToken(),errors);

    }
    private void tokenCheck(Integer logoToken,Errors errors){
        if(logoToken==null || logoToken==0){
            errors.rejectValue("logoImgToken","Logo required");
        }
    }
    private void checkPlacement(Placement placement,Errors errors){
        if(placement==null){
            errors.rejectValue("placement","Placement required");
        }
    }
    private void checkSize(Size size, Errors errors){
        if(size==null){
            errors.rejectValue("size","Size required");
        }
    }
    private void checkFade(Double fade,Errors errors){
        if(fade==null){
            errors.rejectValue("fade","Fade required");
            return;
        }else{
            if(fade<0 ){
                errors.rejectValue("fade","Fade can't be less then zero");
            }
        }
    }

    private void checkFont(Integer fontId,Errors errors){
        if(fontId==null || fontId==0){
            errors.rejectValue("fontId","Font required");
            return;
        }

        Font font = this.fontService.getById(fontId);

        if(font==null){
            errors.rejectValue("fontId","No font found by id :"+fontId);
        }
    }
    private void checkColor(String colorCode,Errors errors){
        ValidationUtils.rejectIfEmpty(errors,"color","Color required");

        if(errors.hasFieldErrors("color")){
            return;
        }

        try{
          Color.decode("#"+colorCode);
        }catch (NumberFormatException ex){
           errors.rejectValue("color","Invalid color code");
        }
    }

}
