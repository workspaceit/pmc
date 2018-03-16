package com.workspaceit.pmc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "event_images")
@JsonIgnoreProperties({"event","createdBy"})
@FilterDef(name = "activeImages")
@Filter(name = "activeImages", condition = "is_deleted = false AND is_active = true")
public class EventImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "watermark_id", referencedColumnName = "id")
    private Watermark watermark;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "in_slideshow")
    private Boolean inSlideshow;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
    private Photographer createdBy;

    @ManyToMany(mappedBy = "eventImages", fetch = FetchType.LAZY)
    private Set<SentSlideshow> sentSlideshows = new HashSet<SentSlideshow>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getInSlideshow() {
        return inSlideshow;
    }

    public void setInSlideshow(Boolean inSlideshow) {
        this.inSlideshow = inSlideshow;
    }

    public Set<SentSlideshow> getSentSlideshows() {
        return sentSlideshows;
    }

    public void setSentSlideshows(Set<SentSlideshow> sentSlideshows) {
        this.sentSlideshows = sentSlideshows;
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

    public Photographer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Photographer createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
