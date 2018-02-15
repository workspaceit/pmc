package com.workspaceit.pmc.util.image;

import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.util.image.cosntant.WATERMARK_PLACEMENT;
import com.workspaceit.pmc.util.image.cosntant.ImagePosition;
import com.workspaceit.pmc.util.image.helper.WatermarkLogoPositioning;
import com.workspaceit.pmc.util.image.helper.WatermarkTextPositioning;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatermarkUtil {

    static void addWatermark(String text,
                             File sourceImageFile,
                             File destImageFile,
                             WATERMARK_PLACEMENT placement,
                             Watermark watermark) {



        String colorCode = watermark.getColor();
        Double fadeVal = watermark.getFade();
        com.workspaceit.pmc.entity.Font font  = watermark.getFont();
        font.getName();
        try {
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            // initializes necessary graphic properties
            // alpha represent fade value
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            WatermarkTextPositioning imagePositioning =   new WatermarkTextPositioning();
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

    public static void addLogo(WATERMARK_PLACEMENT placement) throws IOException {
        File path = new File("/home/mi/Pictures/water mark"); // base path of the images

// load source images
        BufferedImage srcImg = ImageIO.read(new File(path, "sample2.jpg"));
        BufferedImage logo = ImageIO.read(new File(path, "logo.png"));

// create the new image, canvas size is the max. of both image sizes
        int w = Math.max(srcImg.getWidth(), logo.getWidth());
        int h = Math.max(srcImg.getHeight(), logo.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        ImagePosition logoPosition = new WatermarkLogoPositioning().getPosition(placement,
                srcImg.getHeight(),srcImg.getWidth(),
                logo.getHeight(),logo.getWidth());

        g.drawImage(srcImg, 0, 0, null);
        g.drawImage(logo, logoPosition.getX(), logoPosition.getY(), null);

// Save as new image
        ImageIO.write(combined, "PNG", new File(path, "combined"+placement.name()+".png"));
    }

    public static void main(String[] args) {
       /* for(WATERMARK_PLACEMENT ip:WATERMARK_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output_"+ip.name()+".png");

            addWatermark("SETUP MASTER",sF,dF,ip,new Watermark());
        }
        for(WATERMARK_PLACEMENT ip:WATERMARK_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample1.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output1_"+ip.name()+".png");

            addWatermark("SETUP MASTER",sF,dF,ip,new Watermark());
        }
        for(WATERMARK_PLACEMENT ip:WATERMARK_PLACEMENT.values()){
            File sF = new File("/home/mi/Pictures/water mark/sample2.jpg");
            File dF = new File("/home/mi/Pictures/water mark/output2_"+ip.name()+".png");

            addWatermark("SETUP MASTER",sF,dF,ip,new Watermark());
        }*/
        for (WATERMARK_PLACEMENT wp : WATERMARK_PLACEMENT.values()) {


            try {
                addLogo(wp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
