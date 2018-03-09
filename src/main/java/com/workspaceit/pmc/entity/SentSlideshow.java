package com.workspaceit.pmc.entity;

import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "sent_slideshows")
public class SentSlideshow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "identifier")
    private String identifier;

    @Enumerated(EnumType.STRING)
    private PopupAdConstant type;

    @Column(name = "address")
    private String address;

    @Column(name = "message")
    private String message;

    @Column(name = "is_seen")
    private Boolean isSeen;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "sent_by", referencedColumnName = "id", nullable = true)
    private Photographer photographer;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "sent_slideshow_images",
            joinColumns = {@JoinColumn(name = "sent_slideshow_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_image_id")})
    Set<EventImage> eventImages = new HashSet<EventImage>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PopupAdConstant getType() {
        return type;
    }

    public void setType(PopupAdConstant type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public Set<EventImage> getEventImages() {
        return eventImages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEventImages(Set<EventImage> eventImages) {
        this.eventImages = eventImages;
    }
}
