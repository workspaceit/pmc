package com.workspaceit.pmc.util.image.helper;

import com.workspaceit.pmc.util.image.cosntant.IMAGE_PLACEMENT;
import com.workspaceit.pmc.util.image.cosntant.ImagePosition;

public class ImagePositioning {
    private int WIDTH_PADDING=5;
    private int TOP_HEIGHT_PADDING =50;
    private int BOTTOM_HEIGHT_PADDING =20;
    private int TEXT_SIZE_THRASHOLD=10;
    public ImagePosition getPosition(IMAGE_PLACEMENT placement,int height,int width,int textHeight,int textWidth){
        ImagePosition position = new ImagePosition();
        int x=0,y=0;
        switch (placement){
            case TOP_LEFT:
                x = WIDTH_PADDING;
                y = TOP_HEIGHT_PADDING;
                break;
            case TOP_RIGHT:
                x = width - (textWidth+WIDTH_PADDING);
                y = TOP_HEIGHT_PADDING;
                break;
            case TOP_CENTER:
                x = ( width - (textWidth+WIDTH_PADDING) ) / 2;
                y = TOP_HEIGHT_PADDING;
                break;
            case BOTTOM_LEFT:
                x = WIDTH_PADDING;
                y = height - BOTTOM_HEIGHT_PADDING;
                break;
            case BOTTOM_RIGHT:
                x = width - (textWidth+WIDTH_PADDING);
                y = height - BOTTOM_HEIGHT_PADDING;
                break;
            case BOTTOM_CENTER:
                x = ( width - (textWidth+WIDTH_PADDING) ) / 2;
                y = height - BOTTOM_HEIGHT_PADDING;
                break;
        }
        position.setX(x);
        position.setY(y);
        return position;
    }
    public int getRelativeFontSize(int height){
        int textHeight = 42;
        System.out.println("txtHeight "+height);
        if(height<200){
            textHeight = 12;
        }
        return textHeight;//txtHeight;
    }
}