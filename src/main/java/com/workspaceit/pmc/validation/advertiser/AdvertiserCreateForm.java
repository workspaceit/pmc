package com.workspaceit.pmc.validation.advertiser;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by mi_rafi on 1/4/18.
 */
public class AdvertiserCreateForm extends AdvertiserForm{

    @NotBlank(message = "Name is required")
    @Length(max = 50,message = "Value too large")
    @Override
    public String getName() {
        return super.getName();
    }

    @NotBlank(message = "Address is required")
    @Length(max = 50,message = "Value too large")
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @NotNull(message = "City is required")
    @Override
    public Integer getCityId() {
        return super.getCityId();
    }
    @NotNull(message = "State required")
    @Override
    public Integer getStateId() {
        return super.getStateId();
    }


    @NotBlank(message = "Zip is required")
    @Override
    public String getZip() {
        return super.getZip();
    }


    @NotBlank(message = "Phone is required")
    @Length(max = 50,message = "Value too large")
    @Override
    public String getPhone() {
        return super.getPhone();
    }


    @NotBlank(message = "Website is required")
    @Length(max = 100,message = "Value too large")
    @Override
    public String getWebsite() {
        return super.getWebsite();
    }

    @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Override
    public Date getRuntimeStarts() {
        return super.getRuntimeStarts();
    }

    @NotNull(message = "End date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Override
    public Date getRuntimeEnds() {
        return super.getRuntimeEnds();
    }

    @Override
    public Integer[] getLocationIds() {
        return super.getLocationIds();
    }

    @Override
    public Integer[] getEventIds() {
        return super.getEventIds();
    }

    @Override
    public Boolean getIsAllLocationSelected() {
        return super.getIsAllLocationSelected();
    }

    @Override
    public Boolean getIsAllEventSelected() {
        return super.getIsAllEventSelected();
    }

    @Override
    public Integer[] getOtherImage() {
        return super.getOtherImage();
    }
}