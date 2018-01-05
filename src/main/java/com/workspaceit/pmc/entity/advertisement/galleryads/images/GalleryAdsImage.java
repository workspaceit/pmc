package com.workspaceit.pmc.entity.advertisement.galleryads.images;

import com.workspaceit.pmc.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="image_type",discriminatorType = DiscriminatorType.STRING)
@Table(name = "gallery_banner_images")
public class GalleryAdsImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    String imageName;

    @Column(name = "gallery_ad_id")
    Integer galleryAdId;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getGalleryAdId() {
        return galleryAdId;
    }

    public void setGalleryAdId(Integer galleryAdId) {
        this.galleryAdId = galleryAdId;
    }
}
