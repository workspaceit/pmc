package com.workspaceit.pmc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "photographer_password_tokens")
public class PhotographerPasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = Photographer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "photographer_id")
    private Photographer photographer;

    @Column(name = "expire_at")
    private Date expiryDate;

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
