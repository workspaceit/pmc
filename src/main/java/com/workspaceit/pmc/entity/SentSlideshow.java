package com.workspaceit.pmc.entity;

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
@Table(name = "sent_slideshow")
public class SentSlideshow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "identifier")
    private String identifier;

    @Enumerated(EnumType.STRING)
    private SentSlideshowType type;

    @Column(name = "address")
    private String address;

    @Column(name = "is_seen")
    private Boolean isSeen;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "sent_by", referencedColumnName = "id", nullable = true)
    private Photographer photographer;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "sent_slideshow_images",
            joinColumns = {@JoinColumn(name = "sent_slideshow_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_image_id")})
    Set<EventImage> eventImages = new HashSet<EventImage>();


}

enum SentSlideshowType { email, sms }
