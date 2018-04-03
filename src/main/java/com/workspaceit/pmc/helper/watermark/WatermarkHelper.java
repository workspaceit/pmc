package com.workspaceit.pmc.helper.watermark;


import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WATERMARK_ATTR;
import com.workspaceit.pmc.constant.watermark.WatermarkType;
import com.workspaceit.pmc.entity.Font;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.service.FontService;
import com.workspaceit.pmc.validation.watermark.WatermarkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class WatermarkHelper {
    private final static int MAX_FADE = 50;
    private ServletContext servletContext;
    private Environment environment;
    private FontService fontService;

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setFontService(FontService fontService) {
        this.fontService = fontService;
    }

    public static float getNormalizedFadeValForAlpha(float fade){
        float nVal =  fade/MAX_FADE;
        if(nVal>1f){
            nVal = 1f;
        }

        nVal = 1-nVal;
        nVal = Float.valueOf(String.format("%.1f",nVal));
        return nVal; //
    }
    public String getDefaultWatermarkSampleImgPath() throws EntityNotFound, IOException {
        URL originalImgPath = this.servletContext.getResource("WEB-INF/resources"+environment.getWatermarkSamplePreviewImgUri());
        return originalImgPath.getPath();
    }
    public Map<WATERMARK_ATTR,Object> convertToMap(Watermark watermark){
        Map<WATERMARK_ATTR,Object> mergedData = new HashMap<>();
        WatermarkType watermarkType=watermark.getType();

        String color=watermark.getColor();
        float fadeVal=(watermark.getFade()!=null)?watermark.getFade().floatValue():0;
        Size size=watermark.getSize();
        Placement placement=watermark.getPlacement();
        String logoImageName=watermark.getLogoImage();
        String sampleImageName=watermark.getSampleImageName();
        Font font=watermark.getFont();
        String watermarkText=watermark.getWatermarkText();
        Integer logoToken=null;
        Integer sampleToken=null;



        mergedData.put(WATERMARK_ATTR._TYPE,watermarkType);
        mergedData.put(WATERMARK_ATTR._COLOR,color);
        mergedData.put(WATERMARK_ATTR._FADE,fadeVal);
        mergedData.put(WATERMARK_ATTR._SIZE,size);
        mergedData.put(WATERMARK_ATTR._PLACEMENT,placement);
        mergedData.put(WATERMARK_ATTR._LOGO,logoImageName);
        mergedData.put(WATERMARK_ATTR._SAMPLE_IMG,sampleImageName);
        mergedData.put(WATERMARK_ATTR._FONT,font);
        mergedData.put(WATERMARK_ATTR._TEXT,watermarkText);
        mergedData.put(WATERMARK_ATTR._LOGO_TOKEN,logoToken);
        mergedData.put(WATERMARK_ATTR._SAMPLE_TOKEN,sampleToken);

        return mergedData;
    }


    public Map<WATERMARK_ATTR,Object> convertToMap(WatermarkForm watermarkForm){
        Map<WATERMARK_ATTR,Object> mergedData = new HashMap<>();
        WatermarkType watermarkType=watermarkForm.getType();
        Font font =  this.fontService.getById(watermarkForm.getFontId());

        String color=watermarkForm.getColor();
        float fadeVal=(watermarkForm.getFade()!=null)?watermarkForm.getFade().floatValue():0;
        Size size=watermarkForm.getSize();
        Placement placement=watermarkForm.getPlacement();
        String logoImageName=null;
        String sampleImageName=null;
        String watermarkText=watermarkForm.getWatermarkText();
        Integer logoToken=watermarkForm.getLogoImgToken();
        Integer sampleToken=watermarkForm.getSampleImgToken();
        Integer fontSize = watermarkForm.getFontSize();
        String fontBackgroundColor = watermarkForm.getTextBackgroundColor();

        mergedData.put(WATERMARK_ATTR._TYPE,watermarkType);
        mergedData.put(WATERMARK_ATTR._COLOR,color);
        mergedData.put(WATERMARK_ATTR._FADE,fadeVal);
        mergedData.put(WATERMARK_ATTR._SIZE,size);
        mergedData.put(WATERMARK_ATTR._FONT_SIZE,fontSize);
        mergedData.put(WATERMARK_ATTR._PLACEMENT,placement);
        mergedData.put(WATERMARK_ATTR._LOGO,logoImageName);
        mergedData.put(WATERMARK_ATTR._SAMPLE_IMG,sampleImageName);
        mergedData.put(WATERMARK_ATTR._FONT,font);
        mergedData.put(WATERMARK_ATTR._TEXT,watermarkText);
        mergedData.put(WATERMARK_ATTR._LOGO_TOKEN,logoToken);
        mergedData.put(WATERMARK_ATTR._SAMPLE_TOKEN,sampleToken);
        mergedData.put(WATERMARK_ATTR._TEXT_BG_COLOR,fontBackgroundColor);

        return mergedData;
    }

    public Map<WATERMARK_ATTR,Object> convertToMap(Watermark watermark, WatermarkForm watermarkForm){
        Map<WATERMARK_ATTR,Object> mergedData = new HashMap<>();
        WatermarkType watermarkType=watermark.getType();

        String color=watermark.getColor();
        float fadeVal=(watermark.getFade()!=null)?watermark.getFade().floatValue():0;
        Size size=watermark.getSize();
        Placement placement=watermark.getPlacement();
        String logoImageName=watermark.getLogoImage();
        String sampleImageName=watermark.getSampleImageName();
        Font font=watermark.getFont();
        String watermarkText=watermark.getWatermarkText();
        Integer logoToken=watermarkForm.getLogoImgToken();
        Integer sampleToken=watermarkForm.getSampleImgToken();
        Integer fontSize = watermarkForm.getFontSize();
        String fontBackgroundColor = watermarkForm.getTextBackgroundColor();

        if(watermarkForm.getType()!=null){
            watermarkType = watermarkForm.getType();
        }
        if(watermarkForm.getColor()!=null && !watermarkForm.getColor().trim().equals("")){
            color = watermarkForm.getColor();
        }
        if(watermarkForm.getFade()!=null){
            fadeVal = watermarkForm.getFade().floatValue();
        }

        if(watermarkForm.getSize()!=null){
            size = watermarkForm.getSize();
        }

        if(watermarkForm.getWatermarkText()!=null && !watermarkForm.getWatermarkText().trim().equals("")){
            watermarkText = watermarkForm.getWatermarkText();
        }

        if(watermarkForm.getPlacement()!=null){
            placement = watermarkForm.getPlacement();
        }
        if(watermarkForm.getFontId()!=null && watermarkForm.getFontId()>0 ){
            font =  this.fontService.getById(watermarkForm.getFontId());
        }

        mergedData.put(WATERMARK_ATTR._TYPE,watermarkType);
        mergedData.put(WATERMARK_ATTR._COLOR,color);
        mergedData.put(WATERMARK_ATTR._FADE,fadeVal);
        mergedData.put(WATERMARK_ATTR._SIZE,size);
        mergedData.put(WATERMARK_ATTR._PLACEMENT,placement);
        mergedData.put(WATERMARK_ATTR._LOGO,logoImageName);
        mergedData.put(WATERMARK_ATTR._SAMPLE_IMG,sampleImageName);
        mergedData.put(WATERMARK_ATTR._FONT,font);
        mergedData.put(WATERMARK_ATTR._TEXT,watermarkText);
        mergedData.put(WATERMARK_ATTR._LOGO_TOKEN,logoToken);
        mergedData.put(WATERMARK_ATTR._SAMPLE_TOKEN,sampleToken);
        mergedData.put(WATERMARK_ATTR._FONT_SIZE,fontSize);
        mergedData.put(WATERMARK_ATTR._TEXT_BG_COLOR,fontBackgroundColor);

        return mergedData;
    }



}
