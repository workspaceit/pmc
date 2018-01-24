package com.workspaceit.pmc.entity.advertisement.popup;

import com.workspaceit.pmc.constant.advertisement.PopupAdConstant;
import com.workspaceit.pmc.entity.Admin;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "popup_ad_quantity_price")
public class PopupAdQuantityPrice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "popup_ad_id")
    private Integer popupAdId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ad_type")
    private PopupAdConstant adType;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "created_by", referencedColumnName = "id",nullable = true)
    private Admin createdBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPopupAdId() {
        return popupAdId;
    }

    public void setPopupAdId(Integer popupAdId) {
        this.popupAdId = popupAdId;
    }

    public PopupAdConstant getAdType() {
        return adType;
    }

    public void setAdType(PopupAdConstant adType) {
        this.adType = adType;
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

        PopupAdQuantityPrice that = (PopupAdQuantityPrice) o;
        if (id == null || that.id ==null) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "PopupAdQuantityPrice{" +
                "id=" + id +
                ", popupAdId=" + popupAdId +
                ", adType=" + adType +
                ", price=" + price +
                ", quantity=" + quantity +
                ", created_at=" + createdAt +
                ", created_by=" + createdBy +
                '}';
    }
}