package com.workspaceit.pmc.entity.advertisement.galleryads.images;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.galleryads.GalleryAd;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "gallery_ads_top_banner_images")
public class GalleryAdsTopBannerImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "gallery_ad_id")
    private Integer galleryAd;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getGalleryAd() {
        return galleryAd;
    }

    public void setGalleryAd(Integer galleryAd) {
        this.galleryAd = galleryAd;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }
}
