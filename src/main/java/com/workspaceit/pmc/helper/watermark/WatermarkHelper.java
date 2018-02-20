package com.workspaceit.pmc.helper.watermark;


import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.URL;

@Component
public class WatermarkHelper {
    private final static int MAX_FADE = 50;
    private ServletContext servletContext;
    private Environment environment;

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
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


}
