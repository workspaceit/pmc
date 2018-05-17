package com.workspaceit.pmc.validation.city;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CityForm {

    @NotBlank(message = "City name is required")
    @Length(max = 100,message = "Value too large")
    private String cityName;

    @NotNull(message = "State is required")
    private Integer cityStateId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityStateId() {
        return cityStateId;
    }

    public void setCityStateId(Integer cityStateId) {
        this.cityStateId = cityStateId;
    }
}
