package com.workspaceit.pmc.helper;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageQualityReducer {

    public static void main(String[] args) throws Exception {
      //  compress("/home/mi/Pictures/compression/30mb.jpg","/home/mi/Pictures/compression/after-compress.jpeg",0.5f);
        File sf = new File("/home/mi/Pictures/compression/30mb.jpg");
        BufferedImage bi = ImageIO.read(sf );
        Thumbnails.of(sf)
                .size(1920, 1080)
                .outputQuality(0.5)
                .toFile(new File("/home/mi/Pictures/compression/after-compress1.jpg"));



    }

    public static void compress(String srcPath,String destPath,float quality) throws IOException {

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter)iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        iwp.setCompressionQuality(quality);

        File file = new File(destPath);
        FileImageOutputStream output = new FileImageOutputStream(file);
        writer.setOutput(output);

        FileInputStream inputStream = new FileInputStream(srcPath);
        BufferedImage originalImage = ImageIO.read(inputStream);
        System.out.println(getSize(originalImage));
        IIOImage image = new IIOImage(originalImage, null, null);
        writer.write(null, image, iwp);
        writer.dispose();

        System.out.println("DONE");
    }
    public static long getSize(BufferedImage bufImg){
        DataBuffer dataBuffer = bufImg.getData().getDataBuffer();

// Each bank element in the data buffer is a 32-bit integer
        long sizeBytes = ((long) dataBuffer.getSize()) * 4l;
        long sizeMB = sizeBytes / (1024l * 1024l);
        return sizeMB;
    }
    public static void compress(BufferedImage originalImage,String destPath,float quality) throws IOException {

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter)iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        iwp.setCompressionQuality(quality);

        File file = new File(destPath);
        FileImageOutputStream output = new FileImageOutputStream(file);
        writer.setOutput(output);


        IIOImage image = new IIOImage(originalImage, null, null);
        writer.write(null, image, iwp);
        writer.dispose();

        System.out.println("DONE");
    }
    public static byte[] compress(String srcPath,String destPath,float quality,int i) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter)iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        iwp.setCompressionQuality(quality);


        writer.setOutput(outputStream);

        FileInputStream inputStream = new FileInputStream(srcPath);
        BufferedImage originalImage = ImageIO.read(inputStream);

        IIOImage image = new IIOImage(originalImage, null, null);
        writer.write(null, image, iwp);
        writer.dispose();

        System.out.println("DONE");

        return outputStream.toByteArray();
    }
}
