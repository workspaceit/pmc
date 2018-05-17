package com.workspaceit.pmc.validation.state;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by anik on 5/17/18.
 */
public class StateForm {

    @NotBlank(message = "State name is required")
    @Length(max = 100,message = "Value too large")
    private String stateName;

    @NotBlank(message = "Code name is required")
    @Length(max = 10,message = "Value too large")
    private String code;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
