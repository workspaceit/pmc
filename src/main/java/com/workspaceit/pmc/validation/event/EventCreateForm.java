package com.workspaceit.pmc.validation.event;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by anik on 1/17/18.
 */

public class EventCreateForm extends EventForm{
//    @NotNull(message = "Image required")
//    @Min(value = 1,message = "Image required")
    @Override
    public Integer getImageToken() {
        return super.getImageToken();
    }
}
