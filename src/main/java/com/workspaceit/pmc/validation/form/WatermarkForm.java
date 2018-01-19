package com.workspaceit.pmc.validation.form;

import com.workspaceit.pmc.entity.Font;
import com.workspaceit.pmc.entity.Placement;
import com.workspaceit.pmc.entity.Size;
import com.workspaceit.pmc.entity.WatermarkType;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by mi_rafi on 12/28/17.
 */
public class WatermarkForm
{

    @NotBlank(message="Name is required")
    private String name;
    private WatermarkType type;

    @NotBlank(message="Logo name can not be null")
    private String logoName;

    private Integer logoImgToken;

    private Size size;

    private Double fade;

    private String watermarkText;

    private Font font;

    private Placement placement;

    private String color;

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


    public Integer getLogoImgToken() {
        return logoImgToken;
    }

    public void setLogoImgToken(Integer logoImgToken) {
        this.logoImgToken = logoImgToken;
    }
}