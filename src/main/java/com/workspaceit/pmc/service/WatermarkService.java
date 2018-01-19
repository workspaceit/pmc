package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.LocationDao;
import com.workspaceit.pmc.dao.WatermarkDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.util.FileUtil;
import com.workspaceit.pmc.validation.form.WatermarkForm;
import com.workspaceit.pmc.validation.location.LocationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WatermarkService {

    private FileService fileService;
    private FileUtil fileUtil;

    @Autowired
    private WatermarkDao watermarkDao;
    @Autowired
    protected void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }




    @Transactional(rollbackFor = Exception.class)
    public List<Watermark> getAll(){
        return this.watermarkDao.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Watermark> getSuggestedWatermarks(String searchTerm){
        return this.watermarkDao.getSuggestedWatermarks(searchTerm);
    }

    @Transactional
    public List<Watermark> getAll(Integer[] ids){
        return this.watermarkDao.getAll(ids);
    }

    public Watermark getById(int id){
        return this.watermarkDao.getById(id);
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

        System.out.println("logoImgName"+logoImgName);
        watermark.setLogoImage(logoImgName);


        this.create(watermark);

        try{
          //  this.locationBackgroundImageService.createFromToken(location,bgTokens);
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return watermark;
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