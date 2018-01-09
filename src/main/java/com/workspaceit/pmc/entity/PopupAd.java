package com.workspaceit.pmc.entity;

import com.workspaceit.pmc.constant.advertisement.PopupAdType;
import com.workspaceit.pmc.constant.advertisement.AdType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "popup_ads")
public class PopupAd {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PopupAdType type;


    @Column(name = "duration")
    private int duration;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "video")
    private String video;

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

    @OneToMany
    @JoinColumn(name = "popup_ad_id", referencedColumnName = "id", nullable = true)
    Set<PopupBannerImage> popupBannerImages = new HashSet<PopupBannerImage>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public PopupAdType getType() {
        return type;
    }

    public void setType(PopupAdType type) {
        this.type = type;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Set<PopupBannerImage> getPopupBannerImages() {
        return popupBannerImages;
    }

    public void setPopupBannerImages(Set<PopupBannerImage> popupBannerImages) {
        this.popupBannerImages = popupBannerImages;
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