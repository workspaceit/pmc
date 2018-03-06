package com.workspaceit.pmc.entity.advertisement.slideshow;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant;
import com.workspaceit.pmc.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "slideshow_ads")
public class SlideshowAd {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Column(name = "video")
    private String video;

    @Column(name = "video_type")
    private String videoType;

    @Column(name = "video_duration")
    private int videoDuration;

    @Column(name = "banner_duration")
    private int bannerDuration;

    @Column(name = "video_expiry_date")
    private Date videoExpiryDate;

    @Column(name = "banner_expiry_date")
    private Date bannerExpiryDate;


    @Column(name = "banner_rotate")
    @Enumerated(EnumType.STRING)
    private ADVERTISEMENT_ROTATION_SETTINGS bannerRotate;

    @Column(name = "video_rotate")
    @Enumerated(EnumType.STRING)
    private ADVERTISEMENT_ROTATION_SETTINGS videoRotate;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "slideshow_ad_id",referencedColumnName = "id")
    @MapKey(name = "adType")
    Map<SlideshowAdsConstant,SlideshowQuantityPrice> quantityPrice;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "slideshow_ad_id", referencedColumnName = "id")
    Set<SlideshowBannerImage> slideshowBannerImages = new HashSet<>();


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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public int getBannerDuration() {
        return bannerDuration;
    }

    public void setBannerDuration(int bannerDuration) {
        this.bannerDuration = bannerDuration;
    }

    public Date getVideoExpiryDate() {
        return videoExpiryDate;
    }

    public void setVideoExpiryDate(Date videoExpiryDate) {
        this.videoExpiryDate = videoExpiryDate;
    }

    public Date getBannerExpiryDate() {
        return bannerExpiryDate;
    }

    public void setBannerExpiryDate(Date bannerExpiryDate) {
        this.bannerExpiryDate = bannerExpiryDate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getBannerRotate() {
        return bannerRotate;
    }

    public void setBannerRotate(ADVERTISEMENT_ROTATION_SETTINGS bannerRotate) {
        this.bannerRotate = bannerRotate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getVideoRotate() {
        return videoRotate;
    }

    public void setVideoRotate(ADVERTISEMENT_ROTATION_SETTINGS videoRotate) {
        this.videoRotate = videoRotate;
    }


    public Map<SlideshowAdsConstant, SlideshowQuantityPrice> getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(Map<SlideshowAdsConstant, SlideshowQuantityPrice> quantityPrice) {
        this.quantityPrice = quantityPrice;
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

    public Set<SlideshowBannerImage> getSlideshowBannerImages() {
        return slideshowBannerImages;
    }

    public void setSlideshowBannerImages(Set<SlideshowBannerImage> slideshowBannerImages) {
        this.slideshowBannerImages = slideshowBannerImages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlideshowAd that = (SlideshowAd) o;

        return (id == that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}