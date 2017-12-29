package com.workspaceit.pmc.util;

//import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

/**
 * Created by mi on 8/1/16.
 */

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {

    private List<Map<String,String>> formError;
    private ServiceResponse() {
        formError = new ArrayList<>();
    }
    public static ServiceResponse getInstance(){
        return new ServiceResponse();
    }
    public static HashMap<String,String> getMsgInMap(String msg){
        return new HashMap<String, String>(){{put("msg",msg);}};
    }

    public boolean hasErrors(){
        return this.formError.size()>0;
    }
    public void bindValidationError(BindingResult result){
        if(result.hasErrors()) {

            for (ObjectError object : result.getAllErrors()) {


                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    String errorMsg = (fieldError.getDefaultMessage()==null)?fieldError.getCode():fieldError.getDefaultMessage();
                    this.setValidationError(fieldError.getField(), errorMsg);
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    //requestError.setParams(objectError.get());
                    //requestError.setMsg(objectError.getCode());
                    // requestError.setParams(objectError.getCode());
                }
            }
        }
    }
    public void bindValidationError(BindingResult result,String... parms){
        if(result.hasErrors()) {
            List<String> paramList = Arrays.asList(parms);
            for (ObjectError object : result.getAllErrors()) {


                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    if(!paramList.contains(fieldError.getField())){
                        continue;
                    }

                    String errorMsg = (fieldError.getDefaultMessage()==null)?fieldError.getCode():fieldError.getDefaultMessage();
                    this.setValidationError(fieldError.getField(), errorMsg);
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    //requestError.setParams(objectError.get());
                    //requestError.setMsg(objectError.getCode());
                    // requestError.setParams(objectError.getCode());
                }
            }
        }
    }
    public List<Map<String, String>> getFormError() {
        return formError;
    }

    public void setValidationError(String params, String msg){
        Map<String,String> errorObj = new HashMap<>();
        errorObj.put("params",params);
        errorObj.put("msg",msg);
     this.formError.add(errorObj);
    }


}