package com.workspaceit.pmc.entity.advertisement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "advertisement_id",insertable = false,updatable = false)
    private Integer advertisementId;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "rotation")
    private AdvertiseRotationSettings rotation;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type")
    private SECTION_TYPE sectionType;

    @Column(name = "expire_date")
    private Date expireDate;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id",referencedColumnName = "id",nullable = false)
    private List<SectionResource> sectionResource;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AdvertiseRotationSettings getRotation() {
        return rotation;
    }

    public void setRotation(AdvertiseRotationSettings rotation) {
        this.rotation = rotation;
    }

    public SECTION_TYPE getSectionType() {
        return sectionType;
    }

    public void setSectionType(SECTION_TYPE sectionType) {
        this.sectionType = sectionType;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<SectionResource> getSectionResource() {
        return sectionResource;
    }

    public void setSectionResource(List<SectionResource> sectionResource) {
        this.sectionResource = sectionResource;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (id != section.id) return false;
        if (advertisementId != null ? !advertisementId.equals(section.advertisementId) : section.advertisementId != null)
            return false;
        if (price != null ? !price.equals(section.price) : section.price != null) return false;
        if (quantity != null ? !quantity.equals(section.quantity) : section.quantity != null) return false;
        if (rotation != section.rotation) return false;
        if (sectionType != section.sectionType) return false;
        if (expireDate != null ? !expireDate.equals(section.expireDate) : section.expireDate != null) return false;
        if (sectionResource != null ? !sectionResource.equals(section.sectionResource) : section.sectionResource != null)
            return false;
        if (createdAt != null ? !createdAt.equals(section.createdAt) : section.createdAt != null) return false;
        return createdBy != null ? createdBy.equals(section.createdBy) : section.createdBy == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (advertisementId != null ? advertisementId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (rotation != null ? rotation.hashCode() : 0);
        result = 31 * result + (sectionType != null ? sectionType.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (sectionResource != null ? sectionResource.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }
}
