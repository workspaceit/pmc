package com.workspaceit.pmc.validation.admin;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mi_rafi on 12/28/17.
 */
public class AdminEditForm extends AdminForm
{


    @NotBlank(message="Full name required")
    @Length(max=50,message = "Value is too large")
    public String getFullName() {
        return fullName.trim();
    }


    @NotBlank(message="Phone number required")
    @Length(max=15,message = "Value is too large")
    public String getPhoneNumber() {
        return phoneNumber;
    }


}