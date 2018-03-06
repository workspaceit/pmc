package com.workspaceit.pmc.entity.advertisement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.pmc.constant.ENTITY_STATE;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ad_type")
    private ADVERTISEMENT_TYPE adType;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ENTITY_STATE state;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "advertisement")
    @MapKey(name = "sectionType")
    private Map<SECTION_TYPE,Section> sections;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private Admin createdBy;

    public int getId()
    {
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

    public ADVERTISEMENT_TYPE getAdType() {
        return adType;
    }

    public ENTITY_STATE getState() {
        return state;
    }

    public void setState(ENTITY_STATE state) {
        this.state = state;
    }

    public void setAdType(ADVERTISEMENT_TYPE adType) {
        this.adType = adType;
    }

    public Map<SECTION_TYPE, Section> getSections() {
        return sections;
    }

    public void setSections(Map<SECTION_TYPE, Section> sections) {
        this.sections = sections;
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

        Advertisement that = (Advertisement) o;

        if (id != that.id) return false;
        if (advertiserId != null ? !advertiserId.equals(that.advertiserId) : that.advertiserId != null) return false;
        if (adType != that.adType) return false;
        if (sections != null ? !sections.equals(that.sections) : that.sections != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return createdBy != null ? createdBy.equals(that.createdBy) : that.createdBy == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (advertiserId != null ? advertiserId.hashCode() : 0);
        result = 31 * result + (adType != null ? adType.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", advertiserId=" + advertiserId +
                ", adType=" + adType +
                ", state=" + state +
                ", sections=" + sections +
                ", createdAt=" + createdAt +
                '}';
    }
}