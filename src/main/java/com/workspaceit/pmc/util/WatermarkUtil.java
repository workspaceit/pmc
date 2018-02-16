package com.workspaceit.pmc.util;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.helper.ImageHelper;
import com.workspaceit.pmc.constant.watermark.ImagePosition;
import com.workspaceit.pmc.helper.watermark.WatermarkHelper;
import com.workspaceit.pmc.helper.watermark.WatermarkLogoPositioning;
import com.workspaceit.pmc.helper.watermark.WatermarkTextPositioning;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Component
public class WatermarkUtil {
    ImageHelper imageHelper;
    WatermarkTextPositioning watermarkTextPositioning;
    Environment environment;

    @Autowired
    public void setImageHelper(ImageHelper imageHelper) {
        this.imageHelper = imageHelper;
    }

    @Autowired
    public void setWatermarkTextPositioning(WatermarkTextPositioning watermarkTextPositioning) {
        this.watermarkTextPositioning = watermarkTextPositioning;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public BufferedImage addWatermark(String sourceImagePath,
                                      Watermark watermark) throws IOException {


        File sourceImageFile = new File(sourceImagePath);
        String text = watermark.getWatermarkText();
        String colorCode = watermark.getColor();
        float fadeVal = 25; //
                            // Water mark text don't have fade value in UI
                            // although Max value is 50

        com.workspaceit.pmc.entity.Font font  = watermark.getFont();



        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // initializes necessary graphic properties
        // alpha represent fade value

        Color color = Color.RED; // Default color;
        try{
            color = Color.decode("#"+colorCode);
        }catch (NumberFormatException ex){
            System.out.println(ex.getClass().getName()+" : From WatermarkUtil - No color found  - "+ex.getMessage());

        }


        float alpha = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaChannel);
        g2d.setColor(color);
        g2d.setFont(new Font("Arial", Font.BOLD, watermarkTextPositioning.getRelativeFontSize(sourceImage.getHeight())));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

        // calculates the coordinate where the String is painted
        ImagePosition ip =  watermarkTextPositioning.getPosition(Placement.tc,
                                                                sourceImage.getHeight(),
                                                                sourceImage.getWidth(),
                                                                (int) rect.getHeight(),
                                                                (int) rect.getWidth());
        int centerX = ip.getX();
        int centerY =ip.getY();



        // paints the textual watermark
        g2d.drawString(text, centerX, centerY);

       // ImageIO.write(sourceImage, "png", destImageFile);

        g2d.dispose();

        return sourceImage;
    }


    private void addWatermarkLogo_OLD(Placement placement) throws IOException {
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
    public BufferedImage addWatermarkLogo(String originalImagePath,Watermark watermark) throws IOException {

        Placement placement =  ( watermark.getPlacement()==null) ?Placement.tc:watermark.getPlacement();
        Size size = ( watermark.getSize()==null) ?Size.thumb:watermark.getSize();
        float fadeVal = watermark.getFade()==null ?0:watermark.getFade().floatValue();
        float opacity = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);

        WatermarkTextPositioning watermarkTextPositioning = new WatermarkTextPositioning();
        BufferedImage originalImage = ImageIO.read(new File(originalImagePath));
        Positions position = watermarkTextPositioning.getPosition(placement);
        System.out.println(environment.getCommonFilePath()+"/"+watermark.getLogoImage());
        BufferedImage watermarkImage =imageHelper.resizeImage(environment.getCommonFilePath()+"/"+watermark.getLogoImage(),size);

        System.out.println("opacity "+opacity);
        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .size(originalImage.getWidth(), originalImage.getHeight())
                .watermark(position, watermarkImage, opacity) /* 0.0f to 1 */
                .asBufferedImage();
        return thumbnail;
    }
}
