package com.workspaceit.pmc.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "advertiser_transaction")
public class AdvertiserTransaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "advertiser_id")
    private Integer advertiserId;

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "total")
    private float total;

    @Column(name = "total_paid")
    private float totalPaid;

    @Column(name = "total_due")
    private float totalDue;

    @Column(name = "discount")
    private float discount;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "advertiser_transaction_id", referencedColumnName = "id", nullable = false)
    private List<AdvertiserTransactionDetails> advertiserTransactionDetails;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


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

    public float getSubtotal() {
        return subtotal;
    }


    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public List<AdvertiserTransactionDetails> getAdvertiserTransactionDetails() {
        return advertiserTransactionDetails;
    }

    public void setAdvertiserTransactionDetails(List<AdvertiserTransactionDetails> advertiserTransactionDetails) {
        this.advertiserTransactionDetails = advertiserTransactionDetails;
    }

    public float getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
    }

    public float getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(float totalDue) {
        this.totalDue = totalDue;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertiserTransaction that = (AdvertiserTransaction) o;

        return (id == that.id) ;
    }

    @Override
    public int hashCode() {

        return id;
    }
}