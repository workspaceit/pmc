package com.workspaceit.pmc.validation.admin;

public class AdminForm {

    protected String fullName;
    protected String userName;
    protected String password;
    protected String email;
    protected String confirmPassword;
    protected String phoneNumber;
    protected Integer profilePictureToken;


    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getProfilePictureToken() {
        return profilePictureToken;
    }

    public void setFullName(String fullName) {
        this.fullName = (fullName!=null)?fullName.trim():fullName;
    }
    public void setUserName(String userName) {
        this.userName = (userName!=null)?userName.trim():userName;
    }

    public void setEmail(String email) {
        this.email = (email!=null)?email.trim():email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (email!=null)?phoneNumber.trim():phoneNumber;
    }


    public void setProfilePictureToken(Integer profilePictureToken) {
        this.profilePictureToken = profilePictureToken;
    }
}
