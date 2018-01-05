package com.workspaceit.pmc.validation.advertiser;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by mi_rafi on 1/4/18.
 */
public class AdvertiserForm {

    @NotBlank(message = "Name is required")
    @Length(max = 50,message = "Value too large")
    private String name;

    @NotBlank(message = "Address is required")
    @Length(max = 50,message = "Value too large")
    private String address;

    @NotNull(message = "City Id is required")
    private Integer cityId;

    @NotNull(message = "State required")
    private Integer stateId;

    @NotBlank(message = "Zip is required")
    private String zip;

    @NotBlank(message = "Phone is required")
    @Length(max = 50,message = "Value too large")
    private String phone;

    @NotBlank(message = "Website is required")
    @Length(max = 100,message = "Value too large")
    private String website;

    private int[] otherImage;

    @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date runtimeStarts;

    @NotNull(message = "End date is required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date runtimeEnds;


    private Integer[] locationIds;
    private Integer[] eventIds;

    private Boolean isAllLocationSelected =false;
    private Boolean isAllEventSelected =false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int[] getOtherImage() {
        return otherImage;
    }

    public void setOtherImage(int[] otherImage) {
        this.otherImage = otherImage;
    }

    public Date getRuntimeStarts() {
        return runtimeStarts;
    }

    public void setRuntimeStarts(Date runtimeStarts) {
        this.runtimeStarts = runtimeStarts;
    }

    public Date getRuntimeEnds() {
        return runtimeEnds;
    }

    public void setRuntimeEnds(Date runtimeEnds) {
        this.runtimeEnds = runtimeEnds;
    }

    public Integer[] getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(Integer[] locationIds) {
        this.locationIds = locationIds;
    }

    public Integer[] getEventIds() {
        return eventIds;
    }

    public void setEventIds(Integer[] eventIds) {
        this.eventIds = eventIds;
    }

    public Boolean getIsAllLocationSelected() {
        return isAllLocationSelected;
    }

    public void setIsAllLocationSelected(Boolean allLocationSelected) {
        isAllLocationSelected = allLocationSelected;
    }

    public Boolean getIsAllEventSelected() {
        return isAllEventSelected;
    }

    public void setIsAllEventSelected(Boolean allEventSelected) {
        isAllEventSelected = allEventSelected;
    }
}