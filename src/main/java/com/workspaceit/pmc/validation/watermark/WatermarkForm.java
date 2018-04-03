package com.workspaceit.pmc.validation.watermark;


import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WatermarkType;


/**
 * Created by mi_rafi on 12/28/17.
 */
public class WatermarkForm {

    private String name;
    private WatermarkType type;
    private Integer logoImgToken;
    private Integer sampleImgToken;
    private Size size;
    private Double fade;
    private String watermarkText;
    private Integer fontId;
    private Placement placement;
    private String color;
    private Integer fontSize;
    private String textBackgroundColor;

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

    public Integer getFontId() {
        return fontId;
    }

    public void setFontId(Integer fontId) {
        this.fontId = fontId;
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

    public Integer getSampleImgToken() {
        return sampleImgToken;
    }

    public void setSampleImgToken(Integer sampleImgToken) {
        this.sampleImgToken = sampleImgToken;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextBackgroundColor() {
        return textBackgroundColor;
    }

    public void setTextBackgroundColor(String textBackgroundColor) {
        this.textBackgroundColor = textBackgroundColor;
    }

    @Override
    public String toString() {
        return "WatermarkForm{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", logoImgToken=" + logoImgToken +
                ", sampleImgToken=" + sampleImgToken +
                ", size=" + size +
                ", fade=" + fade +
                ", watermarkText='" + watermarkText + '\'' +
                ", fontId=" + fontId +
                ", placement=" + placement +
                ", color='" + color + '\'' +
                ", fontSize=" + fontSize +
                ", textBackgroundColor='" + textBackgroundColor + '\'' +
                '}';
    }
}