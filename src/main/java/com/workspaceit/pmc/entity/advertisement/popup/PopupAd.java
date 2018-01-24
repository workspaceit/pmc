package com.workspaceit.pmc.entity.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
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
    private PopupAdConstant type;

    @Column(name = "duration")
    private int duration;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "video")
    private String video;


    @Column(name = "video_type")
    private String videoType;

    @Column(name = "ad_rotate")
    @Enumerated(EnumType.STRING)
    private AdvertiseRotationSettings adRotate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "popup_ad_id",referencedColumnName = "id")
    @MapKey(name = "adType")
    private Map<PopupAdConstant,PopupAdQuantityPrice> quantityPrice;

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

    public PopupAdConstant getType() {
        return type;
    }

    public void setType(PopupAdConstant type) {
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

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Set<PopupBannerImage> getPopupBannerImages() {
        return popupBannerImages;
    }

    public void setPopupBannerImages(Set<PopupBannerImage> popupBannerImages) {
        this.popupBannerImages = popupBannerImages;
    }

    public AdvertiseRotationSettings getAdRotate() {
        return adRotate;
    }

    public void setAdRotate(AdvertiseRotationSettings adRotate) {
        this.adRotate = adRotate;
    }


    public Map<PopupAdConstant, PopupAdQuantityPrice> getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(Map<PopupAdConstant, PopupAdQuantityPrice> quantityPrice) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopupAd popupAd = (PopupAd) o;
        return (id == popupAd.id);
    }

    @Override
    public int hashCode() {
        return id;
    }


}