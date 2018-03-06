package com.workspaceit.pmc.entity.advertisement.galleryads;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsBottomBannerImage;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsTopBannerImage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;


/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "gallery_ads")
public class GalleryAd {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Column(name = "logo")
    private String logo;

    @Column(name = "background_image")
    private String backgroundImage;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "gallery_ad_id", referencedColumnName = "id", nullable = false,insertable=false, updatable=false)
    private Set<GalleryAdsBottomBannerImage> bottomBanners;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "gallery_ad_id", referencedColumnName = "id", nullable = false,insertable=false, updatable=false)
    private Set<GalleryAdsTopBannerImage> adsTopBanners;


    @Column(name = "top_banner_expiry_date")
    private Date topBannerExpiryDate;

    @Column(name = "bottom_banner_expiry_date")
    private Date bottomBannerExpiryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "top_banner_rotation_settings")
    private ADVERTISEMENT_ROTATION_SETTINGS topBannerRotate;

    @Enumerated(EnumType.STRING)
    @Column(name = "bottom_banner_rotation_settings")
    private ADVERTISEMENT_ROTATION_SETTINGS bottomBannerRotate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "gallery_ad_id",referencedColumnName = "id")
    @MapKey(name = "adType")
    private Map<GalleryAdsConstant,GalleryAdQuantityPrice> galleryQuantityPrice;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Set<GalleryAdsBottomBannerImage> getBottomBanners() {
        return bottomBanners;
    }

    public void setBottomBanners(Set<GalleryAdsBottomBannerImage> bottomBanners) {
        this.bottomBanners = bottomBanners;
    }

    public Set<GalleryAdsTopBannerImage> getAdsTopBanners() {
        return adsTopBanners;
    }

    public void setAdsTopBanners(Set<GalleryAdsTopBannerImage> adsTopBanners) {
        this.adsTopBanners = adsTopBanners;
    }

    public Date getTopBannerExpiryDate() {
        return topBannerExpiryDate;
    }

    public void setTopBannerExpiryDate(Date topBannerExpiryDate) {
        this.topBannerExpiryDate = topBannerExpiryDate;
    }

    public Date getBottomBannerExpiryDate() {
        return bottomBannerExpiryDate;
    }

    public void setBottomBannerExpiryDate(Date bottomBannerExpiryDate) {
        this.bottomBannerExpiryDate = bottomBannerExpiryDate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getBottomBannerRotate() {
        return bottomBannerRotate;
    }

    public void setBottomBannerRotate(ADVERTISEMENT_ROTATION_SETTINGS bottomBannerRotate) {
        this.bottomBannerRotate = bottomBannerRotate;
    }

    public ADVERTISEMENT_ROTATION_SETTINGS getTopBannerRotate() {
        return topBannerRotate;
    }

    public void setTopBannerRotate(ADVERTISEMENT_ROTATION_SETTINGS topBannerRotate) {
        this.topBannerRotate = topBannerRotate;
    }

    public Map<GalleryAdsConstant, GalleryAdQuantityPrice> getGalleryQuantityPrice() {
        return galleryQuantityPrice;
    }

    public void setGalleryQuantityPrice(Map<GalleryAdsConstant, GalleryAdQuantityPrice> galleryQuantityPrice) {
        this.galleryQuantityPrice = galleryQuantityPrice;
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

        GalleryAd galleryAd = (GalleryAd) o;


        return (id == galleryAd.id);
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
