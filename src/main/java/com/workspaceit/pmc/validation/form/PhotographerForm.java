package com.workspaceit.pmc.validation.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;

/*interface Cheap {}
interface Expensive {}

@GroupSequence(value={Cheap.class,Expensive.class}, scope=PER_TARGET)*/
/**
 * Created by mi_rafi on 12/28/17.
 */
public class PhotographerForm
{


    @NotBlank(message="Full name required")
    @Length(max=50,message = "Value is too large")
    private String fullName;


    @NotBlank(message="Username required")
    @Length(max=50,message = "Value is too large")
    private String userName;


    @NotBlank(message="Password required")
    @Length.List({
            @Length(min=5,message = "At least 5 character required"),
            @Length(max=50,message = "Value is too large")

    })
    private String password;

    @NotBlank(message="Email required")
    @Length(max=100,message = "Value is too large")
   // @Email(message = "Email is not valid")
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message="Email is not valid")
    private String email;


    @NotBlank(message="Confirm password required")
    @Length(max=50,message = "Value is too large")
    private String confirmPassword;

    @NotBlank(message="Phone number required")
    @Length(max=15,message = "Value is too large")
    private String phoneNumber;

    private Integer profilePictureToken;



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

    public String getFullName() {
        return fullName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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