package com.workspaceit.pmc.helper;

import com.workspaceit.pmc.constant.watermark.ImageSize;
import com.workspaceit.pmc.constant.watermark.Size;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Component
public class ImageHelper {

    public ImageSize getHeightWidth(Size size){
        int x=0,y=0;
        switch (size){
            case thumb:
                x=y=160;
                break;
            case small:
                x=y=360;
                break;
            case medium:
                x=y=460;
                break;
            case large:
                x=y=560;
                break;
            case x_large:
                x=y=660;
                break;
        }

        return new ImageSize(x,y);
    }
    public BufferedImage resizeImage(String filePath,Size size) throws IOException {
        ImageSize imageSize = this.getHeightWidth(size);
        return resizeImage(filePath,imageSize.getHeight(),imageSize.getWidth());

    }
    private BufferedImage resizeImage(String filePath,int height,int width) throws IOException {

        File srcFile =  new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(srcFile);

        return this.resizeImage(bufferedImage,height,width);

    }
    private BufferedImage filePathToBufferedImage(String filePath) throws IOException {
        File srcFile =  new File(filePath);
        return ImageIO.read(srcFile);
    }
    private BufferedImage resizeImage(BufferedImage bufferedImage,int height,int width) throws IOException {

        return Thumbnails.of(bufferedImage)
                .size(width,height )
                .asBufferedImage();

    }
    private BufferedImage compressImage(BufferedImage bufferedImage,float quality) throws IOException {

        return Thumbnails.of(bufferedImage)
                .outputQuality(quality)
                .asBufferedImage();

    }
    private BufferedImage compressImage(BufferedImage bufferedImage,float quality,int height,int width) throws IOException {

        return Thumbnails.of(bufferedImage)
                .size(width,height )
                .outputQuality(quality)
                .asBufferedImage();

    }
    private BufferedImage compressImage(String filePath,float quality) throws IOException {
        BufferedImage bufferedImage = this.filePathToBufferedImage(filePath);
        return Thumbnails.of(bufferedImage)
                .outputQuality(quality)
                .asBufferedImage();

    }
    public BufferedImage compressImage(String filePath,float quality,int height,int width) throws IOException {
        BufferedImage bufferedImage = this.filePathToBufferedImage(filePath);
        return Thumbnails.of(bufferedImage)
                .size(width,height )
                .outputQuality(quality)
                .asBufferedImage();

    }



    public BufferedImage resizeImage(byte[] imageByte,Size size) throws IOException {
        ImageSize imageSize = this.getHeightWidth(size);
        InputStream inputStream = new ByteArrayInputStream(imageByte);
        BufferedImage bufferedImage = ImageIO.read(inputStream);




        return this.resizeImage(bufferedImage,imageSize.getHeight(),imageSize.getWidth());
    }


    public byte[] bufferedImageToByte(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();




        if(bufferedImage!=null){
            ImageIO.write( bufferedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }

    public float getOptimizedImageQuality(long filesize){
        float quality = (float) 1.0;
        if(filesize>200000 && filesize<1000000){
            quality = (float) 0.30;
        }
        else if(filesize>1000000 && filesize<5000000){
            quality = (float) 0.35;
        }else if(filesize>5000000 && filesize<1000000){
            quality = (float) 0.60;
        }else if(filesize>1000000 && filesize<1500000){
            quality= (float) 0.65;
        }else if(filesize>1500000 && filesize<3000000){
            quality= (float) 0.75;
        }else if(filesize>3000000){
            quality= (float) 0.80;
        }
        return quality;
    }

}
