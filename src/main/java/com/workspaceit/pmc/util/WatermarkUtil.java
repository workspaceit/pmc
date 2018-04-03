package com.workspaceit.pmc.util;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.watermark.Placement;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WATERMARK_ATTR;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.helper.ImageHelper;
import com.workspaceit.pmc.constant.watermark.ImagePosition;
import com.workspaceit.pmc.helper.watermark.WatermarkHelper;
import com.workspaceit.pmc.helper.watermark.WatermarkLogoPositioning;
import com.workspaceit.pmc.helper.watermark.WatermarkTextPositioning;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;


@Component
public class WatermarkUtil {
    String _DEFAUL_FONT_NAME = "Arial";

    ImageHelper imageHelper;
    WatermarkTextPositioning watermarkTextPositioning;
    Environment environment;
    int watermarkWidthPadding = 20;
    int watermarkHeightPadding = 20;

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

    public BufferedImage addWatermarkText(String sourceImagePath, Map<WATERMARK_ATTR,Object> data) throws IOException {

        String text =(String) data.get(WATERMARK_ATTR._TEXT);
        String fontColorCode = (String) data.get(WATERMARK_ATTR._COLOR);
        float fadeVal = (float)data.get(WATERMARK_ATTR._FADE);
        float alpha = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);

        String fontBackgroundColorCode = (String) data.get(WATERMARK_ATTR._TEXT_BG_COLOR);
        Integer fontSize = (data.get(WATERMARK_ATTR._FONT_SIZE)==null)?0:(Integer) data.get(WATERMARK_ATTR._FONT_SIZE);
        System.out.println(fontSize);
        File sourceImageFile = new File(sourceImagePath);
        com.workspaceit.pmc.entity.Font font = (com.workspaceit.pmc.entity.Font) data.get(WATERMARK_ATTR._FONT);

        String fontName = (font!=null)?font.getIdentifier():_DEFAUL_FONT_NAME;
        // Water mark text don't have fade value in UI
        // although Max value is 5

        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // initializes necessary graphic properties
        // alpha represent fade value

        Color color = null; // Default color;
        Color fontBackgroundColor = null; // Default color;
        try{
            color = Color.decode("#"+fontColorCode);
            fontBackgroundColor = Color.decode("#"+fontBackgroundColorCode);
        }catch (NumberFormatException ex){
            System.out.println(ex.getClass().getName()+" : From WatermarkUtil - No color found  - "+ex.getMessage());
            color = fontBackgroundColor = Color.WHITE;

        }
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);

        if(fontBackgroundColor!=null){
           // g2d.setBackground(fontBackgroundColor);
        }
        //System.out.println("COLOR "+fontBackgroundColorCode);
       /// System.out.println(fontBackgroundColor.getRGB());
       /// System.out.println(g2d.getBackground().getRGB());

        g2d.setComposite(alphaChannel);
        g2d.setColor(color);

        // watermarkTextPositioning.getRelativeFontSize(sourceImage.getHeight())


        g2d.setFont(new Font(fontName, Font.PLAIN,fontSize ));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Placement placement =  (Placement)data.get(WATERMARK_ATTR._PLACEMENT);
        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

        /**
         * If text gets out image boundary
         * */
        while((rect.getWidth()>sourceImage.getWidth()) || (rect.getHeight()>sourceImage.getHeight())){
            g2d.setFont(new Font(fontName, Font.PLAIN,--fontSize ));
            fontMetrics = g2d.getFontMetrics();
            rect = fontMetrics.getStringBounds(text, g2d);
            if (fontSize <= 0) {
                break;
            }
        }


        // calculates the coordinate where the String is painted
        ImagePosition ip =  watermarkTextPositioning.getPosition(placement,
                sourceImage.getHeight(),
                sourceImage.getWidth(),
                (int) rect.getHeight(),
                (int) rect.getWidth());


        int centerX = ip.getX();
        int centerY = ip.getY();
        // paints the textual watermark
        if(placement.equals(Placement.tile)){
            this.makeTile(g2d,text,sourceImage.getWidth(),sourceImage.getHeight());
        }else{
            g2d.drawString(text, centerX, centerY);
        }

        g2d.dispose();

        return sourceImage;
    }
    public void makeTile(Graphics2D g2d,String text,int imageWidth,int imageHeight ){

        Rectangle2D rect = g2d.getFontMetrics().getStringBounds(text, g2d);
        int w = (int)rect.getWidth();
        int h = (int)rect.getHeight();
        int horizontalCellCount = imageWidth/w;
        int verticalCellCount = imageHeight/h;
        int centerX=0;
        int centerY=60;
        int wPadding = 30;
        int hPadding = 10;
        for(int i=0;i<verticalCellCount;i++){
            for(int j=0;j<horizontalCellCount;j++){
                g2d.drawString(text, centerX, centerY);
                centerX += w+wPadding;
            }
            centerX=0;
            centerY += h+hPadding;
        }

    }
    public BufferedImage addWatermarkLogo(String originalImagePath,String logoAbsPath, Map<WATERMARK_ATTR,Object> data) throws IOException {
        Placement tmpPlacement =  (Placement)data.get(WATERMARK_ATTR._PLACEMENT);

        Placement placement =  ( tmpPlacement==null) ?Placement.tc:tmpPlacement;
        float fadeVal = (float)data.get(WATERMARK_ATTR._FADE);
        float opacity = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);


        BufferedImage thumbnail = addWatermarkLogo(originalImagePath,
                placement,
                opacity,
                logoAbsPath);



        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(BufferedImage originalImage,String logoAbsPath,Map<WATERMARK_ATTR,Object> data) throws IOException {

        Placement tmpPlacement =  (Placement)data.get(WATERMARK_ATTR._PLACEMENT);
        Size tmpSize =  (Size)data.get(WATERMARK_ATTR._SIZE);


        Placement placement =  ( tmpPlacement==null) ?Placement.tc:tmpPlacement;
        Size size = ( tmpSize==null) ?Size.thumb:tmpSize;
        float fadeVal = (float)data.get(WATERMARK_ATTR._FADE);
        float opacity = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);

        BufferedImage thumbnail = this.addWatermarkLogo(originalImage,
                placement,
                size,
                opacity,
                logoAbsPath);



        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(String originalImagePath,Watermark watermark) throws IOException {

        Placement placement =  ( watermark.getPlacement()==null) ?Placement.tc:watermark.getPlacement();
        Size size = ( watermark.getSize()==null) ?Size.thumb:watermark.getSize();
        float fadeVal = watermark.getFade()==null ?0:watermark.getFade().floatValue();
        float opacity = WatermarkHelper.getNormalizedFadeValForAlpha(fadeVal);
        String logoAbsPath = environment.getCommonFilePath()+"/"+watermark.getLogoImage();

        BufferedImage thumbnail = addWatermarkLogo(originalImagePath,
                                                        placement,
                                                        opacity,
                                                        logoAbsPath);



        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(String originalImagePath,Placement placement,float opacity,String logoAbsPath) throws IOException {


        BufferedImage originalImage = ImageIO.read(new File(originalImagePath));
        BufferedImage thumbnail = this.addWatermarkLogo(originalImage,placement,opacity,logoAbsPath);
        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(String originalImagePath,Placement placement,Size size,float opacity,String logoAbsPath) throws IOException {


        BufferedImage originalImage = ImageIO.read(new File(originalImagePath));

        BufferedImage thumbnail = this.addWatermarkLogo(originalImage,placement,size,opacity,logoAbsPath);
        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(BufferedImage originalImage,Placement placement,Size size,float opacity,String logoAbsPath) throws IOException {

        Positions position = this.watermarkTextPositioning.getPosition(placement);
        BufferedImage watermarkImage = this.imageHelper.resizeImage(logoAbsPath,size);


        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .size(originalImage.getWidth(), originalImage.getHeight())
                .watermark(position, watermarkImage, opacity) /* 0.0f to 1 */
                .asBufferedImage();
        return thumbnail;
    }
    public BufferedImage addWatermarkLogo(BufferedImage originalImage,Placement placement,float opacity,String logoAbsPath) throws IOException {

        Positions position = this.watermarkTextPositioning.getPosition(placement);
        BufferedImage watermarkImage = this.imageHelper.filePathToBufferedImage(logoAbsPath);

        int expectedHeight = watermarkImage.getHeight();
        int expectedWidth = watermarkImage.getWidth();

        int wImH = watermarkImage.getHeight();
        int wImW = watermarkImage.getWidth();

        int orgImH = originalImage.getHeight();
        int orgImW = originalImage.getWidth();


        if(wImH>=orgImH){
            expectedHeight = orgImH - this.watermarkHeightPadding;
        }

        if(wImW>=orgImW){
            expectedWidth = orgImW -this.watermarkWidthPadding;
        }

        watermarkImage = this.imageHelper.resizeImage(logoAbsPath,expectedHeight,expectedWidth);


        watermarkImage.getHeight();
        watermarkImage.getWidth();

        originalImage.getWidth();
        originalImage.getHeight();


        BufferedImage thumbnail = Thumbnails.of(originalImage)
                .size(originalImage.getWidth(), originalImage.getHeight())
                .watermark(position, watermarkImage, opacity) /* 0.0f to 1 */
                .asBufferedImage();
        return thumbnail;
    }
    public static void main(String[] args) throws IOException {
        File saveFile = new File("/home/mi/Pictures/water mark/output.jpg");
        File img = new File("/home/mi/Pictures/water mark/logo.png");
        BufferedImage originalImage = ImageIO.read(img );

// Set up the caption properties
        String caption = "Hello World";
        Font font = new Font("Monospaced", Font.BOLD, 14);
        Color c = Color.WHITE;
        Position position = Positions.CENTER;
        int insetPixels = 0;

// Apply caption to the image
        Caption filter = new Caption(caption, font, c, position, insetPixels);
        BufferedImage captionedImage = filter.apply(originalImage);
        ImageIO.write(captionedImage, "jpg", saveFile);
    }



}