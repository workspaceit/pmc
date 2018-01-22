package com.workspaceit.pmc.validation.admin;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by mi_rafi on 12/28/17.
 */
public class AdminCreateForm extends AdminForm
{



    @Override
    @NotBlank(message="Full name required")
    @Length(max=50,message = "Value is too large")
    public String getFullName() {
        return fullName;
    }

    @Override
    @NotBlank(message="Username required")
    @Length(max=50,message = "Value is too large")
    public String getUserName() {
        return userName;
    }

    @Override
    @NotBlank(message="Password required")
    @Length.List({
            @Length(min=5,message = "At least 5 character required"),
            @Length(max=50,message = "Value is too large")

    })
    public String getPassword() {
        return password;
    }

    @Override
    @NotBlank(message="Email required")
    @Length(max=50,message = "Value is too large")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message="Email is not valid")
    public String getEmail() {
        return email;
    }

    @Override
    @NotBlank(message="Confirm password required")
    @Length(max=50,message = "Value is too large")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    @NotBlank(message="Phone number required")
    @Length(max=15,message = "Value is too large")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getProfilePictureToken() {
        return profilePictureToken;
    }




    @Override
    public String toString() {
        return "PhotographerForm{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePictureToken=" + profilePictureToken +
                '}';
    }
}