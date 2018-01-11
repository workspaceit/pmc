package com.workspaceit.pmc.validation.admin;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mi_rafi on 12/28/17.
 */
public class AdminEditForm
{

    @NotBlank(message="Full name required")
    @Length(max=50,message = "Value is too large")
    private String fullName;

    @NotBlank(message="Phone number required")
    @Length(max=15,message = "Value is too large")
    private String phoneNumber;

    private Integer profilePictureToken;



    public void setFullName(String fullName) {
        this.fullName = (fullName!=null)?fullName.trim():fullName;
    }
    public String getFullName() {
        return fullName.trim();
    }

    public void setProfilePictureToken(Integer profilePictureToken) {
        this.profilePictureToken = profilePictureToken;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (phoneNumber!=null)?phoneNumber.trim():phoneNumber;
    }

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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePictureToken=" + profilePictureToken +
                '}';
    }
}