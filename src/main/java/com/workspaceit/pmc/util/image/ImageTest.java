package com.workspaceit.pmc.util.image;

import com.workspaceit.pmc.util.image.cosntant.IMAGE_PLACEMENT;
import com.workspaceit.pmc.util.image.cosntant.ImagePosition;
import com.workspaceit.pmc.util.image.helper.ImagePositioning;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    static void addTextWatermark(String text, File sourceImageFile, File destImageFile,IMAGE_PLACEMENT placement) {
        try {
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            // initializes necessary graphic properties
            // alpha represent fade value
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            ImagePositioning imagePositioning =   new ImagePositioning();
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.YELLOW);
            g2d.setFont(new Font("Arial", Font.BOLD, imagePositioning.getRelativeFontSize(sourceImage.getHeight())));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

            // calculates the coordinate where the String is painted
            ImagePosition ip =  imagePositioning.getPosition(placement,
                                                                    sourceImage.getHeight(),
                                                                    sourceImage.getWidth(),
                                                                    (int) rect.getHeight(),
                                                                    (int) rect.getWidth());
            int centerX = ip.getX();
            int centerY =ip.getY();



            // paints the textual watermark
            g2d.drawString(text, centerX, centerY);

            ImageIO.write(sourceImage, "png", destImageFile);
            g2d.dispose();

            System.out.println("The tex watermark is added to the image.");

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        for(IMAGE_PLACEMENT ip:IMAGE_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output_"+ip.name()+".png");

            addTextWatermark("SETUP MASTER",sF,dF,ip);
        }
        for(IMAGE_PLACEMENT ip:IMAGE_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample1.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output1_"+ip.name()+".png");

            addTextWatermark("SETUP MASTER",sF,dF,ip);
        }
        for(IMAGE_PLACEMENT ip:IMAGE_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample2.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output2_"+ip.name()+".png");

            addTextWatermark("SETUP MASTER",sF,dF,ip);
        }

    }

}
