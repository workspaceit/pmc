package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    @Column(name = "logo")
    private String logo;

    @Column(name = "background_image")
    private String backgroundImage;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "galleryAd", targetEntity = BottomBannerImage.class, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    Set<BottomBannerImage> bottomBannerImages = new HashSet<BottomBannerImage>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "galleryAd", targetEntity = TopBannerImage.class, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    Set<TopBannerImage> topBannerImages = new HashSet<TopBannerImage>();


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

    public Set<BottomBannerImage> getBottomBannerImages() {
        return bottomBannerImages;
    }

    public void setBottomBannerImages(Set<BottomBannerImage> bottomBannerImages) {
        this.bottomBannerImages = bottomBannerImages;
    }

    public Set<TopBannerImage> getTopBannerImages() {
        return topBannerImages;
    }

    public void setTopBannerImages(Set<TopBannerImage> topBannerImages) {
        this.topBannerImages = topBannerImages;
    }
}
