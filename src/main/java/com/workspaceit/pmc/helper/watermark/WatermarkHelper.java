package com.workspaceit.pmc.helper.watermark;


public class WatermarkHelper {
    private final static int MAX_FADE = 50;


    public static float getNormalizedFadeValForAlpha(float fade){
        float nVal =  fade/MAX_FADE;
        if(nVal>1f){
            nVal = 1f;
        }

        nVal = 1-nVal;
        nVal = Float.valueOf(String.format("%.1f",nVal));
        return nVal; //
    }



}
