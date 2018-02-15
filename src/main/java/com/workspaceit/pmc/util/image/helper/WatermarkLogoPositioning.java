package com.workspaceit.pmc.util.image.helper;

import com.workspaceit.pmc.util.image.cosntant.ImagePosition;
import com.workspaceit.pmc.util.image.cosntant.WATERMARK_PLACEMENT;

public class WatermarkLogoPositioning {
    private final static int  WIDTH_PADDING=5;
    private final static int TOP_HEIGHT_PADDING =50;
    private final static int BOTTOM_HEIGHT_PADDING =10;

    public ImagePosition getPosition(WATERMARK_PLACEMENT placement, int srcImgHeight, int srcImgWidth, int logoHeight, int logoWidth){

        int x=0,y=0;
        switch (placement){
            case TOP_LEFT:
                x = WIDTH_PADDING;
                y = TOP_HEIGHT_PADDING;
                break;
            case TOP_RIGHT:
                x = srcImgWidth - (logoWidth+WIDTH_PADDING);
                y = TOP_HEIGHT_PADDING;
                break;
            case TOP_CENTER:
                x = ( srcImgWidth - (logoWidth+WIDTH_PADDING) ) / 2;
                y = TOP_HEIGHT_PADDING;
                break;
            case BOTTOM_LEFT:
                x = WIDTH_PADDING;
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight );
                break;
            case BOTTOM_RIGHT:
                x = srcImgWidth - (logoWidth+WIDTH_PADDING);
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight );
                break;
            case BOTTOM_CENTER:
                x = ( srcImgWidth - (logoWidth+WIDTH_PADDING) ) / 2;
                y = srcImgHeight - ( BOTTOM_HEIGHT_PADDING + logoHeight ) ;
                break;
        }

        return  new ImagePosition(x,y);
    }

}