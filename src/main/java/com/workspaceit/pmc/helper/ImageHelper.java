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
    public BufferedImage resizeImage(String filePath,Size size) throws IOException {
        ImageSize imageSize = this.getHeightWidth(size);
        return resizeImage(filePath,imageSize.getHeight(),imageSize.getWidth());

    }
    public BufferedImage resizeImage(String filePath,int height,int width) throws IOException {
        File srcFile =  new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        return Thumbnails.of(bufferedImage)
                .size(width,height )
                .asBufferedImage();

    }
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


}
