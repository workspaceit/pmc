package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = true)
    private State state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location_logo")
    private String locationLogo;

    @Column(name = "has_slideshow")
    private Boolean hasSlideshow;

    @Column(name = "duration_speed")
    private String durationSpeed;

    @Column(name = "break_time")
    private Double breakTime;

    @Column(name = "fade_in_time")
    private Double fadeInTime;

    @Column(name = "fade_out_time")
    private Double fadeOutTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", targetEntity = LocationBackgroundImage.class, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    Set<LocationBackgroundImage> locationBackgroundImages = new HashSet<LocationBackgroundImage>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationLogo() {
        return locationLogo;
    }

    public void setLocationLogo(String locationLogo) {
        this.locationLogo = locationLogo;
    }

    public Boolean getHasSlideshow() {
        return hasSlideshow;
    }

    public void setHasSlideshow(Boolean hasSlideshow) {
        this.hasSlideshow = hasSlideshow;
    }

    public String getDurationSpeed() {
        return durationSpeed;
    }

    public void setDurationSpeed(String durationSpeed) {
        this.durationSpeed = durationSpeed;
    }

    public Double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Double breakTime) {
        this.breakTime = breakTime;
    }

    public Double getFadeInTime() {
        return fadeInTime;
    }

    public void setFadeInTime(Double fadeInTime) {
        this.fadeInTime = fadeInTime;
    }

    public Double getFadeOutTime() {
        return fadeOutTime;
    }

    public void setFadeOutTime(Double fadeOutTime) {
        this.fadeOutTime = fadeOutTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

}
