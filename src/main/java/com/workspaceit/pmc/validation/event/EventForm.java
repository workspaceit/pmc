package com.workspaceit.pmc.validation.event;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by anik on 1/17/18.
 */

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

    @NotNull(message = "Please select a venue")
    private Integer venueId;

    private Integer[] photographerIds = new Integer[]{};
    private Integer[] advertiserIds = new Integer[]{};
    private Integer[] watermarkIds = new Integer[]{};

    @NotNull(message = "Image token required")
    private Integer imageToken;
    @NotNull(message = "Visibility required")
    private Boolean isPrivate;


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

}