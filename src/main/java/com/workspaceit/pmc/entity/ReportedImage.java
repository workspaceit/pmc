package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by anik on 3/19/18.
 */

@Entity
@Table(name = "reported_images")
public class ReportedImage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    private EventImage eventImage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reported_at")
    private Date reportedAt;

    @Column(name = "action_taken")
    private Boolean actionTaken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventImage getEventImage() {
        return eventImage;
    }

    public void setEventImage(EventImage eventImage) {
        this.eventImage = eventImage;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }

    public Boolean getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

}
