package com.workspaceit.pmc.validation.event;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EventForm {
    @NotNull(message = "Event name is required")
    @NotEmpty(message = "Event name is required")
    private String eventName;

    @NotNull(message = "Start date and time are required")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date startDate;

    @NotNull(message = "End date and time are required")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date endDate;

    private Integer venueId;

    @NotNull(message = "Please select a location")
    private Integer locationId;

    private Integer[] photographerIds = new Integer[]{};
    private Integer[] advertiserIds = new Integer[]{};
    private Integer[] watermarkIds = new Integer[]{};
    private Integer imageToken;

    @NotNull(message = "Visibility required")
    private Boolean isPrivate;

    private boolean isAllAdvertiserSelected;


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer[] getPhotographerIds() {
        return photographerIds;
    }

    public void setPhotographerIds(Integer[] photographerIds) {
        this.photographerIds = photographerIds;
    }

    public Integer[] getAdvertiserIds() {
        return advertiserIds;
    }

    public void setAdvertiserIds(Integer[] advertiserIds) {
        this.advertiserIds = advertiserIds;
    }

    public Integer[] getWatermarkIds() {
        return watermarkIds;
    }

    public void setWatermarkIds(Integer[] watermarkIds) {
        this.watermarkIds = watermarkIds;
    }

    public Integer getImageToken() {
        return imageToken;
    }

    public void setImageToken(Integer imageToken) {
        this.imageToken = imageToken;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean getIsAllAdvertiserSelected() {
        return isAllAdvertiserSelected;
    }

    public void setIsAllAdvertiserSelected(Boolean allAdvertiser) {
        isAllAdvertiserSelected = allAdvertiser;
    }
}
