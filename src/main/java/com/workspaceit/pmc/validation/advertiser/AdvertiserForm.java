package com.workspaceit.pmc.validation.advertiser;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mi_rafi on 1/4/18.
 */
public class AdvertiserForm {

    protected String name;
    protected String address;
    protected Integer cityId;
    protected Integer stateId;
    protected String zip;
    protected String phone;
    protected String website;
    protected Integer[] otherImage;
    protected Date runtimeStarts;
    protected Date runtimeEnds;
    protected Integer[] locationIds;
    protected Integer[] eventIds;
    protected Boolean isAllLocationSelected =false;
    protected Boolean isAllEventSelected =false;

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

    public Integer[] getOtherImage() {
        return otherImage;
    }

    public void setOtherImage(Integer[] otherImage) {
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

    @Override
    public String toString() {
        return "AdvertiserForm{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cityId=" + cityId +
                ", stateId=" + stateId +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", otherImage=" + Arrays.toString(otherImage) +
                ", runtimeStarts=" + runtimeStarts +
                ", runtimeEnds=" + runtimeEnds +
                ", locationIds=" + Arrays.toString(locationIds) +
                ", eventIds=" + Arrays.toString(eventIds) +
                ", isAllLocationSelected=" + isAllLocationSelected +
                ", isAllEventSelected=" + isAllEventSelected +
                '}';
    }
}