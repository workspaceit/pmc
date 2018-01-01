package com.workspaceit.pmc.validation.location;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by mi_rafi on 1/1/18.
 */
public class LocationForm {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Length(max = 100,message = "Value too large")
    private String name;

    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    @Length(max = 200,message = "Value too large")
    private String address;

    @NotNull(message = "State Id is required")
   // @Length(min = 1,message = "State Id is required")
    private Integer stateId;

    @NotNull(message = "Zip is required")
    @NotBlank(message = "Zip is required")
    @Length(max = 10,message = "Value too large")
    private String zip;

    @NotNull(message = "Phone is required")
    @NotBlank(message = "Phone is required")
    @Length(max = 50,message = "Value too large")
    private String phone;

    @NotNull(message = "Location Logo is required")
    @NotBlank(message = "Location Logo is required")
    private String locationLogo;

    @NotNull(message = "Has Slideshow is required")
    private Boolean hasSlideshow;

    @NotNull(message = "Duration Speed is required")
    @NotBlank(message = "Duration Speed is required")
    private String durationSpeed;

    @NotNull(message = "Break Time is required")
    private Double breakTime;

    @NotNull(message = "Fade In Time is required")
    private Double fadeInTime;

    @NotNull(message = "Fade Out Time is required")
    private Double fadeOutTime;

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

    public String getLocationLogo() {
        return locationLogo;
    }

    public void setLocationLogo(String locationLogo) {
        this.locationLogo = locationLogo;
    }

    public Boolean getHasSlideshow() {
        return hasSlideshow;
    }

    public void setHasSlideshow(Boolean hasSlideshow) {
        this.hasSlideshow = hasSlideshow;
    }

    public String getDurationSpeed() {
        return durationSpeed;
    }

    public void setDurationSpeed(String durationSpeed) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationForm that = (LocationForm) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (stateId != null ? !stateId.equals(that.stateId) : that.stateId != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (locationLogo != null ? !locationLogo.equals(that.locationLogo) : that.locationLogo != null) return false;
        if (hasSlideshow != null ? !hasSlideshow.equals(that.hasSlideshow) : that.hasSlideshow != null) return false;
        if (durationSpeed != null ? !durationSpeed.equals(that.durationSpeed) : that.durationSpeed != null)
            return false;
        if (breakTime != null ? !breakTime.equals(that.breakTime) : that.breakTime != null) return false;
        if (fadeInTime != null ? !fadeInTime.equals(that.fadeInTime) : that.fadeInTime != null) return false;
        return fadeOutTime != null ? fadeOutTime.equals(that.fadeOutTime) : that.fadeOutTime == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (locationLogo != null ? locationLogo.hashCode() : 0);
        result = 31 * result + (hasSlideshow != null ? hasSlideshow.hashCode() : 0);
        result = 31 * result + (durationSpeed != null ? durationSpeed.hashCode() : 0);
        result = 31 * result + (breakTime != null ? breakTime.hashCode() : 0);
        result = 31 * result + (fadeInTime != null ? fadeInTime.hashCode() : 0);
        result = 31 * result + (fadeOutTime != null ? fadeOutTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocationForm{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", stateId=" + stateId +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", locationLogo='" + locationLogo + '\'' +
                ", hasSlideshow=" + hasSlideshow +
                ", durationSpeed='" + durationSpeed + '\'' +
                ", breakTime=" + breakTime +
                ", fadeInTime=" + fadeInTime +
                ", fadeOutTime=" + fadeOutTime +
                '}';
    }
}