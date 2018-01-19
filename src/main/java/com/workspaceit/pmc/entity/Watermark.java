package com.workspaceit.pmc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by anik on 12/20/17.
 */

@Entity
@Table(name = "watermarks")
public class Watermark {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private WatermarkType type;

    @Column(name = "logo_name")
    private String logoName;

    @Column(name = "logo_image")
    private String logoImage;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "fade")
    private Double fade;

    @Column(name = "watermark_text")
    private String watermarkText;

    @ManyToOne
    @JoinColumn(name = "font_id", referencedColumnName = "id", nullable = false)
    private Font font;

    @Enumerated(EnumType.STRING)
    private Placement placement;

    @Column(name = "color")
    private String color;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true)
    private Admin createdBy;

    @ManyToMany(mappedBy = "watermarks", fetch = FetchType.LAZY)
    private Set<Event> events = new HashSet<Event>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WatermarkType getType() {
        return type;
    }

    public void setType(WatermarkType type) {
        this.type = type;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Double getFade() {
        return fade;
    }

    public void setFade(Double fade) {
        this.fade = fade;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
