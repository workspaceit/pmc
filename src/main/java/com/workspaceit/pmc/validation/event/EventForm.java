package com.workspaceit.pmc.validation.event;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by anik on 1/17/18.
 */

public class EventForm {

    @NotNull(message = "Event name is required")
    String eventName;

    @NotNull(message = "Start date and time are required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date startDate;

    @NotNull(message = "End date and time are required")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    Date endDate;

    @NotNull(message = "Please select a value")
    Integer venueId;

    Integer[] photographerIds;
    Integer[] advertiserIds;
    Integer[] watermarkIds;
    @NotNull(message = "Image token required")
    String imageToken;
    @NotNull(message = "Visibility required")
    Boolean isPrivate;


}
