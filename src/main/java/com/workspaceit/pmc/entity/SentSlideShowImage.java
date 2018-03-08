package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sent_slideshow_images")
public class SentSlideShowImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sent_slideshow_id", referencedColumnName = "id", nullable = false)
    private SentSlideshow sentSlideshow;

    @ManyToOne
    @JoinColumn(name = "event_image_id", referencedColumnName = "id", nullable = false)
    private EventImage eventImage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SentSlideshow getSentSlideshow() {
        return sentSlideshow;
    }

    public void setSentSlideshow(SentSlideshow sentSlideshow) {
        this.sentSlideshow = sentSlideshow;
    }

    public EventImage getEventImage() {
        return eventImage;
    }

    public void setEventImage(EventImage eventImage) {
        this.eventImage = eventImage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
