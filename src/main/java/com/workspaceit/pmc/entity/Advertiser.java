package com.workspaceit.pmc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "advertisers")
public class Advertiser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;



    @Column(name = "all_locations")
    private boolean isAllLocationSelected;

    @Column(name = "all_events")
    private boolean isAllEventSelected;

    @Column(name = "other_image")
    private String otherImage;

    @Column(name = "runtime_starts")
    private Date runtimeStarts;

    @Column(name = "runtime_ends")
    private Date runtimeEnds;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;




    @ManyToMany
    @JoinTable(name = "event_advertisers", joinColumns = {
            @JoinColumn(name = "advertiser_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "event_id",
                    nullable = false, updatable = false) })
    private Set<Event> events;


    @ManyToMany
    @JoinTable(name = "location_advertisers", joinColumns = {
            @JoinColumn(name = "advertiser_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "location_id",
                    nullable = false) })
    private Set<Location> locations;

    @OneToMany
    @JoinColumn(name = "advertiser_id")
    private Set<AdvertisersOtherImage> otherImages;

    @Column(name = "active")
    private Boolean active;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOtherImage() {
        return otherImage;
    }

    public void setOtherImage(String otherImage) {
        this.otherImage = otherImage;
    }

    public Date getRuntimeStarts() {
        return runtimeStarts;
    }

    public void setRuntimeStarts(Date runtimeStarts) {
        this.runtimeStarts = runtimeStarts;
    }

    public Date getRuntimeEnds() {
        return runtimeEnds;
    }

    public void setRuntimeEnds(Date runtimeEnds) {
        this.runtimeEnds = runtimeEnds;
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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public boolean getIsAllLocationSelected() {
        return isAllLocationSelected;
    }

    public void setIsAllLocationSelected(boolean isAllLocationSelected) {
        this.isAllLocationSelected = isAllLocationSelected;
    }

    public boolean getIsAllEventSelected() {
        return isAllEventSelected;
    }

    public void setAllEventSelected(boolean allEventSelected) {
        this.isAllEventSelected = allEventSelected;
    }

    public Set<AdvertisersOtherImage> getOtherImages() {
        return otherImages;
    }

    public void setOtherImages(Set<AdvertisersOtherImage> otherImages) {
        this.otherImages = otherImages;
    }

    public boolean isAllLocationSelected() {
        return isAllLocationSelected;
    }

    public void setAllLocationSelected(boolean allLocationSelected) {
        isAllLocationSelected = allLocationSelected;
    }

    public boolean isAllEventSelected() {
        return isAllEventSelected;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertiser that = (Advertiser) o;

        return (id == that.id);
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}