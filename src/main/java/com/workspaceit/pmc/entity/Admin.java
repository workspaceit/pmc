package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

/**
 * Created by anik on 12/19/17.
 */

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @Column(name = "created_by")
    private String createdBy;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "admin_has_roles",
            joinColumns = {@JoinColumn(name = "admin_id")},
            inverseJoinColumns = {@JoinColumn(name = "admin_role_id")})
    private Set<AdminRole> adminRoles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Set<AdminRole> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(Set<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;
        if (name != null ? !name.equals(admin.name) : admin.name != null) return false;
        if (email != null ? !email.equals(admin.email) : admin.email != null) return false;
        if (userName != null ? !userName.equals(admin.userName) : admin.userName != null) return false;
        return phoneNumber != null ? phoneNumber.equals(admin.phoneNumber) : admin.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
