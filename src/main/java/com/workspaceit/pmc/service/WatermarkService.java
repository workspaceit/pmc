package com.workspaceit.pmc.service;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.FILE;
import com.workspaceit.pmc.constant.watermark.Size;
import com.workspaceit.pmc.constant.watermark.WATERMARK_ATTR;
import com.workspaceit.pmc.constant.watermark.WatermarkType;
import com.workspaceit.pmc.dao.WatermarkDao;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.exception.ServiceException;
import com.workspaceit.pmc.helper.ImageHelper;
import com.workspaceit.pmc.helper.watermark.WatermarkHelper;
import com.workspaceit.pmc.util.FileUtil;
import com.workspaceit.pmc.util.ServiceResponse;
import com.workspaceit.pmc.util.WatermarkUtil;
import com.workspaceit.pmc.validation.watermark.WatermarkForm;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WatermarkService {


    private ServletContext servletContext;

    private Environment environment;

    private FileService fileService;
    private TempFileService tempFileService;
    private EventImageService eventImageService;
    private FileUtil fileUtil;
    private WatermarkUtil watermarkUtil;
    private WatermarkHelper watermarkHelper;
    private WatermarkDao watermarkDao;
    private ImageHelper imageHelper;
    private FontService fontService;

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
    public void setTempFileService(TempFileService tempFileService) {
        this.tempFileService = tempFileService;
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
    public void setWatermarkHelper(WatermarkHelper watermarkHelper) {
        this.watermarkHelper = watermarkHelper;
    }

    @Autowired
    public void setWatermarkDao(WatermarkDao watermarkDao) {
        this.watermarkDao = watermarkDao;
    }

    @Autowired
    public void setImageHelper(ImageHelper imageHelper) {
        this.imageHelper = imageHelper;
    }

    @Autowired
    public void setFontService(FontService fontService) {
        this.fontService = fontService;
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
        Watermark watermark = this.getWatermarkFromWatermarkForm(watermarkForm);
        Integer logoImgToken = watermarkForm.getLogoImgToken();
        Integer sampleImgToken = watermarkForm.getSampleImgToken();

        String logoImgName = "";
        String sampleImgName = "";


        if(logoImgToken!=null && logoImgToken>0){
            logoImgName = this.fileService.copyFile(logoImgToken);
        }
        if(sampleImgToken!=null && sampleImgToken>0){
            sampleImgName = this.fileService.copyFile(sampleImgToken);
        }else{
            Map<FILE,String> fileInfo =  this.fileService.makeNewFileFromDefaultWatermarkSampleImg();
            sampleImgName = fileInfo.get(FILE.NAME);
        }

        watermark.setLogoImage(logoImgName);
        watermark.setSampleImageName(sampleImgName);

        watermark.setActive(true);
        watermark.setDeleted(false);

        this.create(watermark);


        return watermark;
    }

    @Transactional
    public byte[] getImageWithWaterMark(WatermarkForm watermarkForm) throws IOException,EntityNotFound {
        byte[] watermarkedImgByte = null;
        WatermarkType watermarkType =  watermarkForm.getType();
        if(watermarkType==null){
            throw new EntityNotFound("Water mark type required");
        }
        if(watermarkForm.getType().equals(WatermarkType.image))
            watermarkedImgByte = this.getImageWithWaterMarkImage(watermarkForm);
        else if(watermarkForm.getType().equals(WatermarkType.text))
            watermarkedImgByte = this.getImageWithWaterMarkText(watermarkForm);

        return watermarkedImgByte;
    }
    @Transactional
    public byte[] getImageWithWaterMark(Watermark watermark) throws IOException,EntityNotFound {
        byte[] watermarkedImgByte = null;
        WatermarkType watermarkType =  watermark.getType();
        if(watermarkType==null){
            throw new EntityNotFound("Water mark type required");
        }

        if(watermark.getType().equals(WatermarkType.image))
            watermarkedImgByte = this.getImageWithWaterMarkImage(watermark);
        else if(watermark.getType().equals(WatermarkType.text))
            watermarkedImgByte = this.getImageWithWaterMarkText(watermark);

        return watermarkedImgByte;
    }
    @Transactional
    public byte[] getImageWithWaterMark(Watermark watermark,WatermarkForm watermarkForm) throws IOException,EntityNotFound {
        Map<WATERMARK_ATTR,Object> mergedData = this.watermarkHelper.convertToMap(watermark,watermarkForm);

        byte[] watermarkedImgByte = null;
        WatermarkType watermarkType = (WatermarkType) mergedData.get(WATERMARK_ATTR._TYPE);
        if(watermarkType==null){
            throw new EntityNotFound("Water mark type required");
        }

        if(watermarkType.equals(WatermarkType.image))
            watermarkedImgByte = this.getImageWithWaterMarkImage(mergedData);
        else if(watermarkType.equals(WatermarkType.text))
            watermarkedImgByte = this.getImageWithWaterMarkText(mergedData);

        return watermarkedImgByte;
    }
    @Transactional
    public byte[] getImageWithWaterMark(Watermark watermark,Size size) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] watermarkedImgByte  =  this.getImageWithWaterMark(watermark);
        BufferedImage resizedImage = this.imageHelper.resizeImage(watermarkedImgByte,size);


        if(resizedImage!=null){
            ImageIO.write( resizedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }



    @Transactional
    public byte[] getImageWithWaterMark(WatermarkForm watermarkForm,boolean useBothWatermark) throws IOException,EntityNotFound {
        byte[] watermarkedImgByte = null;

        if(useBothWatermark){
            watermarkedImgByte = this.getImageWithWaterMarkText(watermarkForm);
            watermarkedImgByte = this.getImageWithWaterMarkImage(watermarkForm,watermarkedImgByte);
        }else{
            watermarkedImgByte = this.getImageWithWaterMark(watermarkForm);
        }

        return watermarkedImgByte;
    }
    @Transactional
    public byte[] getImageWithWaterMark(Watermark watermark,boolean useBothWatermark) throws IOException,EntityNotFound {
        byte[] watermarkedImgByte = null;
        if(useBothWatermark){
            watermarkedImgByte = this.getImageWithWaterMarkText(watermark);
            watermarkedImgByte = this.getImageWithWaterMarkImage(watermark,watermarkedImgByte);
        }else{
            watermarkedImgByte = this.getImageWithWaterMark(watermark);
        }
        return watermarkedImgByte;
    }
    @Transactional
    public byte[] getImageWithWaterMarkImage(WatermarkForm watermarkForm) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Integer logoToken = watermarkForm.getLogoImgToken();
        Integer sampleToken =watermarkForm.getSampleImgToken();
        String originalImgAbsPath = this.fileService.getSampleImgPath(sampleToken);
        String logoImgAbsPath = "";
        BufferedImage watermarkedImage;
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermarkForm);
        if(logoToken!=null && logoToken>0){
            TempFile tempLogoFile =  this.tempFileService.getByToken(logoToken);

            if(tempLogoFile==null){
                throw new EntityNotFound("No logo file found with token : "+logoToken);
            }
            logoImgAbsPath = tempLogoFile.getPath();
        }
        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImgAbsPath,logoImgAbsPath,data);

        if(watermarkedImage!=null){
            BufferedImage smallerImage = Thumbnails.of(watermarkedImage)
                    .size(500, 500)
                    .asBufferedImage();
            ImageIO.write(smallerImage, "png", outputStream );
        }
        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkImage(Map<WATERMARK_ATTR,Object> data) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Integer tmpLogoToken = (Integer) data.get(WATERMARK_ATTR._LOGO_TOKEN);
        Integer tmpSampleToken = (Integer)data.get(WATERMARK_ATTR._SAMPLE_TOKEN);

        String logoImgAbsPath;
        String originalImgAbsPath;

        if(tmpLogoToken!=null && tmpLogoToken>0){
            TempFile tempFile =  this.tempFileService.getTempFile(tmpLogoToken);
            logoImgAbsPath = tempFile.getPath();
        }else{
            logoImgAbsPath = environment.getCommonFilePath()+"/"+((String)data.get(WATERMARK_ATTR._LOGO));
        }

        if(tmpSampleToken!=null && tmpSampleToken>0){
            TempFile tempFile =  this.tempFileService.getTempFile(tmpSampleToken);
            originalImgAbsPath = tempFile.getPath();
        }else{
            originalImgAbsPath = environment.getCommonFilePath()+"/"+((String)data.get(WATERMARK_ATTR._SAMPLE_IMG));
        }

        BufferedImage watermarkedImage;

        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImgAbsPath,logoImgAbsPath,data);

        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkImage(Watermark watermark) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String logoImgAbsPath = environment.getCommonFilePath()+"/"+watermark.getLogoImage();
        String originalImgAbsPath = environment.getCommonFilePath()+"/"+watermark.getSampleImageName();
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermark);
        BufferedImage watermarkedImage;


        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImgAbsPath,logoImgAbsPath,data);

        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkImage(WatermarkForm watermarkForm,byte[] originalImageByte) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Integer logoToken = watermarkForm.getLogoImgToken();
        Integer sampleToken = watermarkForm.getSampleImgToken();
        String originalImgAbsPath = this.fileService.getSampleImgPath(sampleToken);
        String logoImgAbsPath = "";
        BufferedImage watermarkedImage;
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermarkForm);

        InputStream in = new ByteArrayInputStream(originalImageByte);
        BufferedImage originalImg = ImageIO.read(in);


        if(logoToken!=null && logoToken>0){
            TempFile tempLogoFile =  this.tempFileService.getTempFile(logoToken);
            logoImgAbsPath = tempLogoFile.getPath();
        }

        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImg,logoImgAbsPath,data);

        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkImage(Watermark watermark,byte[] originalImageByte) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String originalImgAbsPath = watermark.getSampleImageName();
        String logoImgAbsPath = watermark.getLogoImage();
        BufferedImage watermarkedImage;
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermark);

        InputStream in = new ByteArrayInputStream(originalImageByte);
        BufferedImage originalImg = ImageIO.read(in);



        watermarkedImage =  this.watermarkUtil.addWatermarkLogo(originalImg,logoImgAbsPath,data);

        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkText(WatermarkForm watermarkForm) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Integer sampleToken =watermarkForm.getSampleImgToken();
        String originalImgAbsPath = this.fileService.getSampleImgPath(sampleToken);
        BufferedImage watermarkedImage;
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermarkForm);

        watermarkedImage =  this.watermarkUtil.addWatermarkText(originalImgAbsPath,data);

        if(watermarkedImage!=null){
            BufferedImage smallerImage = Thumbnails.of(watermarkedImage)
                    .size(500, 500)
                    .asBufferedImage();
            ImageIO.write(smallerImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkText(Watermark watermark) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String originalImgAbsPath = environment.getCommonFilePath()+"/"+watermark.getSampleImageName();
        BufferedImage watermarkedImage;
        Map<WATERMARK_ATTR,Object> data = this.watermarkHelper.convertToMap(watermark);

        watermarkedImage =  this.watermarkUtil.addWatermarkText(originalImgAbsPath,data);

        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "png", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }
    @Transactional
    public byte[] getImageWithWaterMarkText(Map<WATERMARK_ATTR,Object> data) throws IOException,EntityNotFound {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String originalImgAbsPath = "";
        BufferedImage watermarkedImage;

        Integer tmpSampleToken = (Integer)data.get(WATERMARK_ATTR._SAMPLE_TOKEN);


        if(tmpSampleToken!=null && tmpSampleToken>0){
            TempFile tempFile =  this.tempFileService.getTempFile(tmpSampleToken);
            originalImgAbsPath = tempFile.getPath();
        }else{
            originalImgAbsPath = environment.getCommonFilePath()+"/"+((String)data.get(WATERMARK_ATTR._SAMPLE_IMG));
        }


        watermarkedImage =  this.watermarkUtil.addWatermarkText(originalImgAbsPath,data);

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
        Map<WATERMARK_ATTR,Object>  data = this.watermarkHelper.convertToMap(watermark);
        if(watermarkType!=null && watermarkType.equals(WatermarkType.image)){

            watermarkedImage =  this.watermarkUtil.addWatermarkLogo(environment.getEventImagePath()+"/web/"+eventImage.getImage(),watermark);

        }else if(watermarkType!=null && watermarkType.equals(WatermarkType.text)){
            watermarkedImage =  this.watermarkUtil.addWatermarkText(environment.getEventImagePath()+"/web/"+eventImage.getImage(),data);
        }


        if(watermarkedImage!=null){
            ImageIO.write( watermarkedImage, "jpg", outputStream );
        }

        outputStream.flush();
        return outputStream.toByteArray();
    }

    public void create(Watermark watermark){
        this.watermarkDao.insert(watermark);
    }

    private Watermark getWatermarkFromWatermarkForm(WatermarkForm watermarkForm){
        Font font =  this.fontService.getById(watermarkForm.getFontId());

        Watermark watermark = new Watermark();

        watermark.setName(watermarkForm.getName());
        watermark.setType(watermarkForm.getType());
        watermark.setPlacement(watermarkForm.getPlacement());
        watermark.setSize(watermarkForm.getSize());
        watermark.setFontSize((watermarkForm.getFontSize()!=null)?watermarkForm.getFontSize():0);

        watermark.setFade(watermarkForm.getFade());
        watermark.setWatermarkText(watermarkForm.getWatermarkText());

        watermark.setFont(font);
        watermark.setColor(watermarkForm.getColor());

        return watermark;
    }

    public void update(Watermark watermark){
        this.watermarkDao.update(watermark);
    }

    public Watermark update(int id,WatermarkForm watermarkForm,Admin admin)throws EntityNotFound,ServiceException{
        Watermark watermark = this.getById(id);
        Integer logoImgToken = watermarkForm.getLogoImgToken();
        Integer sampleImgToken = watermarkForm.getSampleImgToken();

        if((watermark.getLogoImage()==null || watermark.getLogoImage().equals("")) &&
                (logoImgToken==null || logoImgToken==0)){
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("logoImgToken","Logo required");
            throw new ServiceException(serviceResponse.getFormError());
        }

        String logoImgName = "";
        if(logoImgToken!=null && logoImgToken>0){
            logoImgName = this.fileService.copyFile(logoImgToken);
            watermark.setLogoImage(logoImgName);
        }

        if(sampleImgToken!=null && sampleImgToken>0){
            String sampleImgName  = this.fileService.copyFile(sampleImgToken);
            watermark.setSampleImageName(sampleImgName);
        }


        this.populateWatermarkByWatermarkForm(watermark,watermarkForm);
        this.update(watermark);
        return watermark;
    }


    private void populateWatermarkByWatermarkForm(Watermark watermark, WatermarkForm watermarkForm){
        Font font =  this.fontService.getById(watermarkForm.getFontId());
        watermark.setName(watermarkForm.getName());
        watermark.setPlacement(watermarkForm.getPlacement());
        watermark.setSize(watermarkForm.getSize());
        watermark.setFontSize(watermarkForm.getFontSize());
        watermark.setFade(watermarkForm.getFade());
        watermark.setType(watermarkForm.getType());
        watermark.setWatermarkText(watermarkForm.getWatermarkText());
        watermark.setColor(watermarkForm.getColor());
        watermark.setFont(font);
    }

}