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


    @Column(name = "deleted")
    private boolean deleted;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertiser that = (Advertiser) o;

        if (id != that.id) return false;
        if (deleted != that.deleted) return false;
        if (isAllLocationSelected != that.isAllLocationSelected) return false;
        if (isAllEventSelected != that.isAllEventSelected) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (otherImage != null ? !otherImage.equals(that.otherImage) : that.otherImage != null) return false;
        if (runtimeStarts != null ? !runtimeStarts.equals(that.runtimeStarts) : that.runtimeStarts != null)
            return false;
        if (runtimeEnds != null ? !runtimeEnds.equals(that.runtimeEnds) : that.runtimeEnds != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (events != null ? !events.equals(that.events) : that.events != null) return false;
        if (locations != null ? !locations.equals(that.locations) : that.locations != null) return false;
        if (otherImages != null ? !otherImages.equals(that.otherImages) : that.otherImages != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + (isAllLocationSelected ? 1 : 0);
        result = 31 * result + (isAllEventSelected ? 1 : 0);
        result = 31 * result + (otherImage != null ? otherImage.hashCode() : 0);
        result = 31 * result + (runtimeStarts != null ? runtimeStarts.hashCode() : 0);
        result = 31 * result + (runtimeEnds != null ? runtimeEnds.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        result = 31 * result + (otherImages != null ? otherImages.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }
}