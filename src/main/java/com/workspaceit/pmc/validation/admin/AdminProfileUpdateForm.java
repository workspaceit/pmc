package com.workspaceit.pmc.validation.admin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminProfileUpdateForm extends AdminForm {

    @Override
    @NotBlank(message="Full name required")
    @Length(max=50,message = "Value is too large")
    public String getFullName() {
        return super.fullName;
    }

    @Override
    @NotBlank(message="Phone number required")
    @Length(max=15,message = "Value is too large")
    public String getPhoneNumber() {
        return super.phoneNumber;
    }

}