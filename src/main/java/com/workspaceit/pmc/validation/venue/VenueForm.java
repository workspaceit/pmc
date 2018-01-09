package com.workspaceit.pmc.validation.venue;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by Tomal on 1/8/2018.
 */
public class VenueForm {

    @NotBlank(message = "Name is required")
    @Length(max = 100,message = "Value too large")
    private String name;

    @NotNull(message = "Location is required")
    @Length(min = 1,message = "Location is required")
    private String location_id;

    private String userEmail;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String stateId) {
        this.location_id = stateId;
    }

    public String getUserEmail(){return this.userEmail;}

    public void setUserEmail(String userEmail){this.userEmail = userEmail;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VenueForm that = (VenueForm) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (location_id != null ? !location_id.equals(that.location_id) : that.location_id != null) return false;
        return true;
    }
}
