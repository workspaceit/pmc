package com.workspaceit.pmc.helper.watermark;

import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.ImagePosition;

public class WatermarkLogoPositioning {
    private final static int  WIDTH_PADDING=5;
    private final static int TOP_HEIGHT_PADDING =50;
    private final static int BOTTOM_HEIGHT_PADDING =10;

    public ImagePosition getPosition(Placement placement, int srcImgHeight, int srcImgWidth, int logoHeight, int logoWidth){

        int x=0,y=0;
        switch (placement){
            case tl:
                x = WIDTH_PADDING;
                y = TOP_HEIGHT_PADDING;
                break;
            case tr:
                x = srcImgWidth - (logoWidth+WIDTH_PADDING);
                y = TOP_HEIGHT_PADDING;
                break;
            case tc:
                x = ( srcImgWidth - (logoWidth+WIDTH_PADDING) ) / 2;
                y = TOP_HEIGHT_PADDING;
                break;
            case bl:
                x = WIDTH_PADDING;
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight );
                break;
            case br:
                x = srcImgWidth - (logoWidth+WIDTH_PADDING);
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight );
                break;
            case bc:
                x = ( srcImgWidth - (logoWidth+WIDTH_PADDING) ) / 2;
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight ) ;
                break;
        }

        return  new ImagePosition(x,y);
    }

}