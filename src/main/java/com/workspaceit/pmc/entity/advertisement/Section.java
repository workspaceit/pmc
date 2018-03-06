package com.workspaceit.pmc.entity.advertisement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    private Advertisement advertisement;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "rotation")
    private ADVERTISEMENT_ROTATION_SETTINGS rotation;

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

    @Column(name = "duration")
    private Integer duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
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

    public ADVERTISEMENT_ROTATION_SETTINGS getRotation() {
        return rotation;
    }

    public void setRotation(ADVERTISEMENT_ROTATION_SETTINGS rotation) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (id != section.id) return false;
        if (price != null ? !price.equals(section.price) : section.price != null) return false;
        if (quantity != null ? !quantity.equals(section.quantity) : section.quantity != null) return false;
        if (rotation != section.rotation) return false;
        if (sectionType != section.sectionType) return false;
        if (expireDate != null ? !expireDate.equals(section.expireDate) : section.expireDate != null) return false;
        if (sectionResource != null ? !sectionResource.equals(section.sectionResource) : section.sectionResource != null)
            return false;
        if (createdAt != null ? !createdAt.equals(section.createdAt) : section.createdAt != null) return false;
        return duration != null ? duration.equals(section.duration) : section.duration == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (rotation != null ? rotation.hashCode() : 0);
        result = 31 * result + (sectionType != null ? sectionType.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (sectionResource != null ? sectionResource.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", rotation=" + rotation +
                ", sectionType=" + sectionType +
                ", expireDate=" + expireDate +
                ", sectionResource=" + sectionResource +
                ", createdAt=" + createdAt +
                ", duration=" + duration +
                '}';
    }
}
