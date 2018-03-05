package com.workspaceit.pmc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@JsonIgnoreProperties({"advertisers", "createdBy", "createdAt", "updatedAt", "deleted" })
@FilterDef(name = "activeEvents")
@Filter(name = "activeEvents", condition = "deleted = false AND active = true")
@Table(name = "events")

public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "starts_at")
    private Date startsAt;

    @Column(name = "ends_at")
    private Date endsAt;

    @Column(name = "is_private")
    private Boolean eventPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", referencedColumnName = "id", nullable = true)
    private Venue venue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "event_photo")
    private String eventPhoto;

    @Column(name = "is_all_advertiser")
    private boolean isAllAdvertiser;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "event_photographers",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "photographer_id")})
    private Set<Photographer> photographers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "event_watermarks",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "watermark_id")})
    private Set<Watermark> watermarks = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "event_advertisers",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "advertiser_id")})
    private Set<Advertiser> advertisers = new HashSet<>();

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;



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

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Boolean getEventPrivate() {
        return eventPrivate;
    }

    public void setEventPrivate(Boolean eventPrivate) {
        this.eventPrivate = eventPrivate;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public boolean getIsAllAdvertiser() {
        return isAllAdvertiser;
    }

    public void setIsAllAdvertiser(boolean allAdvertiser) {
        isAllAdvertiser = allAdvertiser;
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

    public Set<Photographer> getPhotographers() { return photographers; }

    public void setPhotographers(Set<Photographer> photographers) { this.photographers = photographers; }

    public Set<Watermark> getWatermarks() {
        return this.watermarks;
    }

    public void setWatermarks(Set<Watermark> watermarks) {
        this.watermarks = watermarks;
    }

    public Set<Advertiser> getAdvertisers() {
        return advertisers;
    }

    public void setAdvertisers(Set<Advertiser> advertisers) {
        this.advertisers = advertisers;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        return name != null ? name.equals(event.name) : event.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
