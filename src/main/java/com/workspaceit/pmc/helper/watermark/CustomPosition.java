package com.workspaceit.pmc.helper.watermark;

import net.coobird.thumbnailator.geometry.Position;

import java.awt.*;

public class CustomPosition implements Position {
    private int x,y;
    public CustomPosition(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point calculate(int enclosingWidth, int enclosingHeight,
                           int width, int height, int insetLeft, int insetRight,
                           int insetTop, int insetBottom) {

        /**
         * Use X and y
         * */
        return new Point(this.x,this.y);
    }
}
