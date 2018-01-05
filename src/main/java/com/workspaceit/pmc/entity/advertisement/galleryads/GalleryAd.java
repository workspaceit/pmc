package com.workspaceit.pmc.entity.advertisement.galleryads;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.BottomBannerImage;
import com.workspaceit.pmc.entity.TopBannerImage;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsBottomBanner;
import com.workspaceit.pmc.entity.advertisement.galleryads.images.GalleryAdsTopBanner;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    @OneToOne
    @JoinColumn(name = "advertiser_id", referencedColumnName = "id", nullable = false)
    private Advertiser advertiser;

    @Column(name = "top_banner_expiry_date")
    private Date topBannerExpiryDate;

    @Column(name = "bottom_banner_expiry_date")
    private Date bottomBannerExpiryDate;

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

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn(name = "gallery_ad_id",referencedColumnName ="id")
    List<GalleryAdsBottomBanner> bottomBanners;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
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

    public List<GalleryAdsBottomBanner> getBottomBanners() {
        return bottomBanners;
    }

    public void setBottomBanners(List<GalleryAdsBottomBanner> bottomBanners) {
        this.bottomBanners = bottomBanners;
    }


}
