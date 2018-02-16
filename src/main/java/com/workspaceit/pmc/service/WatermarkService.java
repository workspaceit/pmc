package com.workspaceit.pmc.service;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.watermark.WatermarkType;
import com.workspaceit.pmc.dao.WatermarkDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.util.FileUtil;
import com.workspaceit.pmc.util.WatermarkUtil;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WatermarkService {


    private ServletContext servletContext;

    private Environment environment;

    private FileService fileService;
    private EventImageService eventImageService;
    private FileUtil fileUtil;
    private WatermarkUtil watermarkUtil;

    private WatermarkDao watermarkDao;

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    protected void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setEventImageService(EventImageService eventImageService) {
        this.eventImageService = eventImageService;
    }

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Autowired
    public void setWatermarkUtil(WatermarkUtil watermarkUtil) {
        this.watermarkUtil = watermarkUtil;
    }

    @Autowired
    public void setWatermarkDao(WatermarkDao watermarkDao) {
        this.watermarkDao = watermarkDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Watermark> getAll(){
        return this.watermarkDao.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Watermark> getActiveWatermarks(){
        return this.watermarkDao.getActiveWatermarks();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Watermark> getSuggestedWatermarks(String searchTerm, Boolean active){
        return this.watermarkDao.getSuggestedWatermarks(searchTerm, active);
    }

    @Transactional
    public List<Watermark> getAll(Integer[] ids){
        return this.watermarkDao.getAll(ids);
    }


    public Watermark getById(int id){
        return this.watermarkDao.getById(id);
    }

    @Transactional
    public Watermark getWatermark(int id) throws EntityNotFound{
        Watermark watermark = this.watermarkDao.getById(id);
        if(watermark==null){
            throw new EntityNotFound("Entity not found by id :"+id);
        }
        return watermark;
    }
    @Transactional(rollbackFor = Exception.class)
    public Watermark create(WatermarkForm watermarkForm){
        Watermark watermark = getWatermarkFromWatermarkForm(watermarkForm);
        Integer logoImgToken = watermarkForm.getLogoImgToken();
        System.out.println("logoImgToken"+logoImgToken);
        String logoImgName = "";
        if(logoImgToken!=null && logoImgToken>0){
            System.out.println("logoImgToken"+logoImgToken);
            logoImgName = this.fileService.copyFile(logoImgToken);
        }


        watermark.setLogoImage(logoImgName);
        watermark.setActive(true);
        watermark.setDeleted(false);

        this.create(watermark);

        try{
          //  this.locationBackgroundImageService.createFromToken(location,bgTokens);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return watermark;
    }

    @Transactional
    public byte[] getImageWithWaterMark(WatermarkForm watermarkForm) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        BufferedImage watermarkedImage = null;
        URL originalImgPath = this.servletContext.getResource("WEB-INF/resources"+environment.getWatermarkSamplePreviewImgUri());
        System.out.println("logoPath.getPath() "+originalImgPath.getPath());
        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImgPath.getPath(),"29723734117772.png",watermarkForm);
        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMark(int eventImageId,int watermarkId) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EventImage eventImage = this.eventImageService.getEventImage(eventImageId);
        Watermark watermark =  this.getWatermark(watermarkId);
        WatermarkType watermarkType = watermark.getType();

        BufferedImage watermarkedImage = null;

        if(watermarkType!=null && watermarkType.equals(WatermarkType.image)){
            watermarkedImage =  this.watermarkUtil.addWatermarkLogo(environment.getCommonFilePath()+"/"+eventImage.getImage(),watermark);

        }else if(watermarkType!=null && watermarkType.equals(WatermarkType.text)){
            watermarkedImage =  this.watermarkUtil.addWatermarkText(environment.getCommonFilePath()+"/"+eventImage.getImage(),watermark);
        }


        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }

    public void create(Watermark watermark){
        this.watermarkDao.insert(watermark);
    }

    private Watermark getWatermarkFromWatermarkForm(WatermarkForm watermarkForm){

        Watermark watermark = new Watermark();

        watermark.setName(watermarkForm.getName());
        watermark.setType(watermarkForm.getType());
        watermark.setLogoName(watermarkForm.getLogoName());
        watermark.setPlacement(watermarkForm.getPlacement());
        watermark.setSize(watermarkForm.getSize());
        watermark.setFade(watermarkForm.getFade());
        watermark.setWatermarkText(watermarkForm.getWatermarkText());
        watermark.setFont(watermarkForm.getFont());
        watermark.setColor(watermarkForm.getColor());

        return watermark;
    }

    public void update(Watermark watermark){
        this.watermarkDao.update(watermark);
    }

    public Watermark update(int id,WatermarkForm watermarkForm,Admin admin)throws EntityNotFound{
        Watermark watermark = this.getById(id);
        Integer logoImgToken = watermarkForm.getLogoImgToken();
        System.out.println("logoImgToken"+logoImgToken);
        String logoImgName = "";
        if(logoImgToken!=null && logoImgToken>0){
            System.out.println("logoImgToken"+logoImgToken);
            logoImgName = this.fileService.copyFile(logoImgToken);
            watermark.setLogoImage(logoImgName);
        }
        this.populateWatermarkByWatermarkForm(watermark,watermarkForm);
        this.update(watermark);
        return watermark;
    }


    private void populateWatermarkByWatermarkForm(Watermark watermark, WatermarkForm watermarkForm){
        watermark.setName(watermarkForm.getName());
        watermark.setLogoName(watermarkForm.getLogoName());
        watermark.setType(watermarkForm.getType());
        watermark.setPlacement(watermarkForm.getPlacement());
        watermark.setSize(watermarkForm.getSize());
        watermark.setFade(watermarkForm.getFade());
        watermark.setWatermarkText(watermarkForm.getWatermarkText());
        watermark.setFont(watermarkForm.getFont());
        watermark.setColor(watermarkForm.getColor());

        /**
         * Populating slid show settings
         * */
    }


}