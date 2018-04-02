package com.workspaceit.pmc.helper.watermark;

import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.ImagePosition;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Component;

@Component
public class WatermarkTextPositioning {
    private int WIDTH_PADDING=5;
    private int TOP_HEIGHT_PADDING =50;
    private int BOTTOM_HEIGHT_PADDING =20;
    private int TEXT_SIZE_THRASHOLD=10;
    public ImagePosition getPosition(Placement placement, int srcImgHeight, int srcImgWidth, int textHeight, int textWidth){
        ImagePosition position = new ImagePosition();
        int x=0,y=0;
        switch (placement){
            case tl:
                x = WIDTH_PADDING;
                y = TOP_HEIGHT_PADDING;
                break;
            case tr:
                x = srcImgWidth - (textWidth+WIDTH_PADDING);
                y = TOP_HEIGHT_PADDING;
                break;
            case tc:
                x = ( srcImgWidth - (textWidth+WIDTH_PADDING) ) / 2;
                y = TOP_HEIGHT_PADDING;
                break;
            case cc:
                x = ( srcImgWidth - (textWidth+WIDTH_PADDING) ) / 2;
                y = ( srcImgHeight - (textHeight) ) / 2;
                break;
            case cl:
                x = WIDTH_PADDING;
                y = ( srcImgHeight - (textHeight) ) / 2;
                break;
            case cr:
                x = srcImgWidth - (textWidth+WIDTH_PADDING);
                y = ( srcImgHeight - (textHeight) ) / 2;
                break;
            case bl:
                x = WIDTH_PADDING;
                y = srcImgHeight - BOTTOM_HEIGHT_PADDING;
                break;
            case br:
                x = srcImgWidth - (textWidth+WIDTH_PADDING);
                y = srcImgHeight - BOTTOM_HEIGHT_PADDING;
                break;
            case bc:
                x = ( srcImgWidth - (textWidth+WIDTH_PADDING) ) / 2;
                y = srcImgHeight - BOTTOM_HEIGHT_PADDING;
                break;
            case tile:
                x = WIDTH_PADDING;
                y = TOP_HEIGHT_PADDING;
                break;
        }
        position.setX(x);
        position.setY(y);
        return position;
    }
    public Positions getPosition(Placement placement){
        Positions position = null;
        switch (placement){
            case tl:
                position = Positions.TOP_LEFT;
                break;
            case tr:
                position = Positions.TOP_RIGHT;
                break;
            case tc:
                position = Positions.TOP_CENTER;
                break;


            case cl:
                position = Positions.CENTER_LEFT;
                break;
            case cr:
                position = Positions.CENTER_RIGHT;
                break;
            case cc:
                position = Positions.CENTER;
                break;

            case bl:
                position = Positions.BOTTOM_LEFT;
                break;
            case br:
                position = Positions.BOTTOM_RIGHT;
                break;
            case bc:
                position = Positions.BOTTOM_CENTER;
                break;
        }
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