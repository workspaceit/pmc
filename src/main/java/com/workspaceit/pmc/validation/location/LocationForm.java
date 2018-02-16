package com.workspaceit.pmc.validation.location;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by mi_rafi on 1/1/18.
 */
public class LocationForm {
    @NotBlank(message = "Name is required")
    @Length(max = 100,message = "Value too large")
    private String name;

    @NotBlank(message = "Address is required")
    @Length(max = 200,message = "Value too large")
    private String address;

    @NotNull(message = "State is required")
    @Min(value = 1,message = "State Id is required")
    private Integer stateId;

    @NotNull(message = "City is required")
    @Min(value = 1,message = "City Id is required")
    private Integer cityId;

    @NotBlank(message = "Zip is required")
    @Length(max = 10,message = "Value too large")
    private String zip;

    @NotBlank(message = "Phone is required")
    @Length(max = 50,message = "Value too large")
    private String phone;

    @NotNull(message = "Has Slideshow is required")
    private Boolean hasSlideshow;


    private Double durationSpeed;

    private Double breakTime;

    private Double fadeInTime;

    private Double fadeOutTime;

    private int[] bgTokens;

    private Integer logoImgToken;

    /**
     * For Update only
     * */

    private Integer[] removeBgIds;

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

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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




    public Boolean getHasSlideshow() {
        return hasSlideshow;
    }

    public void setHasSlideshow(Boolean hasSlideshow) {
        this.hasSlideshow = hasSlideshow;
    }

    public Double getDurationSpeed() {
        return durationSpeed;
    }

    public void setDurationSpeed(Double durationSpeed) {
        this.durationSpeed = durationSpeed;
    }

    public Double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Double breakTime) {
        this.breakTime = breakTime;
    }

    public Double getFadeInTime() {
        return fadeInTime;
    }

    public void setFadeInTime(Double fadeInTime) {
        this.fadeInTime = fadeInTime;
    }

    public Double getFadeOutTime() {
        return fadeOutTime;
    }

    public void setFadeOutTime(Double fadeOutTime) {
        this.fadeOutTime = fadeOutTime;
    }

    public int[] getBgTokens() {
        return bgTokens;
    }

    public void setBgTokens(int[] bgTokens) {
        this.bgTokens = bgTokens;
    }

    public Integer getLogoImgToken() {
        return logoImgToken;
    }

    public void setLogoImgToken(Integer logoImgToken) {
        this.logoImgToken = logoImgToken;
    }


    public Integer[] getRemoveBgIds() {
        return removeBgIds;
    }

    public void setRemoveBgIds(Integer[] removeBgIds) {
        this.removeBgIds = removeBgIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationForm that = (LocationForm) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (stateId != null ? !stateId.equals(that.stateId) : that.stateId != null) return false;
        if (cityId != null ? !cityId.equals(that.stateId) : that.cityId != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (hasSlideshow != null ? !hasSlideshow.equals(that.hasSlideshow) : that.hasSlideshow != null) return false;
        if (durationSpeed != null ? !durationSpeed.equals(that.durationSpeed) : that.durationSpeed != null)
            return false;
        if (breakTime != null ? !breakTime.equals(that.breakTime) : that.breakTime != null) return false;
        if (fadeInTime != null ? !fadeInTime.equals(that.fadeInTime) : that.fadeInTime != null) return false;
        if (fadeOutTime != null ? !fadeOutTime.equals(that.fadeOutTime) : that.fadeOutTime != null) return false;
        if (!Arrays.equals(bgTokens, that.bgTokens)) return false;
        if (logoImgToken != null ? !logoImgToken.equals(that.logoImgToken) : that.logoImgToken != null) return false;
        return Arrays.equals(removeBgIds, that.removeBgIds);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (hasSlideshow != null ? hasSlideshow.hashCode() : 0);
        result = 31 * result + (durationSpeed != null ? durationSpeed.hashCode() : 0);
        result = 31 * result + (breakTime != null ? breakTime.hashCode() : 0);
        result = 31 * result + (fadeInTime != null ? fadeInTime.hashCode() : 0);
        result = 31 * result + (fadeOutTime != null ? fadeOutTime.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(bgTokens);
        result = 31 * result + (logoImgToken != null ? logoImgToken.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(removeBgIds);
        return result;
    }

    @Override
    public String toString() {
        return "LocationForm{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", stateId=" + stateId +
                ", cityId=" + cityId +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", hasSlideshow=" + hasSlideshow +
                ", durationSpeed=" + durationSpeed +
                ", breakTime=" + breakTime +
                ", fadeInTime=" + fadeInTime +
                ", fadeOutTime=" + fadeOutTime +
                ", bgTokens=" + Arrays.toString(bgTokens) +
                ", logoImgToken=" + logoImgToken +
                ", removeBgIds=" + Arrays.toString(removeBgIds) +
                '}';
    }
}