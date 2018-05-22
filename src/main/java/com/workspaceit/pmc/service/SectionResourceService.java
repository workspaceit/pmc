package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.SectionResourceDao;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsUpdateForm;
import com.workspaceit.pmc.validation.advertiser.AdvertiserAndAllCompositeUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class SectionResourceService {

    private SectionResourceDao sectionResourceDao;
    private FileService fileService;
    private SectionService sectionService;
    private AdvertisementService advertisementService;

    @Autowired
    public void setSectionResourceDao(SectionResourceDao sectionResourceDao) {
        this.sectionResourceDao = sectionResourceDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Transactional
    public List<SectionResource> getById(Integer[] id){
       return this.sectionResourceDao.getById(id);
    }

    @Transactional
    public List<SectionResource> getById(List<Integer> id){
        return this.sectionResourceDao.getById(id);
    }

    @Transactional
    public SectionResource getById(Integer id){
        return this.sectionResourceDao.getById(id);
    }


    @Transactional
    public SectionResource getSectionResource(Integer id)throws EntityNotFound{
        SectionResource sectionResource = this.getById(id);
        if(sectionResource==null) throw new EntityNotFound("Section resource not found by id :"+id);

        return sectionResource;
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer[] id){
        List<SectionResource> sectionResourceList = this.getById(id);
        this.sectionResourceDao.deleteAll(sectionResourceList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<SectionResource> sectionResources){
        this.sectionResourceDao.deleteAll(sectionResources);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<SectionResource> delete(List<SectionResource> sectionResources, FILE_TYPE fileType){
        if(sectionResources==null){
            return null;
        }
        List<SectionResource> deletedSectionResources = new LinkedList<>();

        for (SectionResource sr :sectionResources) {
            if(sr.getFileType().equals(fileType)){
                deletedSectionResources.add(sr);

            }
        }

        this.delete(deletedSectionResources);

        return deletedSectionResources;
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Collection<SectionResource> sectionResources){

       this.sectionResourceDao.insertAll(sectionResources);
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(AdvertiserAndAllCompositeUpdateForm updateForm){

        this.update(updateForm.getGalleryAds().getUpdateSectionResources());
        this.update(updateForm.getSlideShowAds().getUpdateSectionResources());
        this.update(updateForm.getPopupAds().getUpdateEmailSectionResources());
        this.update(updateForm.getPopupAds().getUpdateSmsSectionResources());
    }
    @Transactional(rollbackFor = Exception.class)
    public void update(Map<SECTION_TYPE,SectionResourceUpdateForm[]> updateForms){
        if(updateForms==null )return;

        Set<SECTION_TYPE> keys =  updateForms.keySet();
        for(SECTION_TYPE key :keys){
            this.update(updateForms.get(key));
        }
    }
    public void update(SectionResourceUpdateForm[] urlUpdateForm) {
        if(urlUpdateForm==null || urlUpdateForm.length==0)return;

        List<SectionResource> sectionResourceList = new ArrayList<>();

        for(SectionResourceUpdateForm form:urlUpdateForm){

            SectionResource sectionResource =   this.getById(form.getId());
            if(sectionResource==null)continue;
            sectionResource.setUrl(form.getUrl());
            sectionResource.setSelectedStatic(form.getSelectedStatic());

            sectionResourceList.add(sectionResource);
        }
        this.sectionResourceDao.updateAll(sectionResourceList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(SectionResource sectionResource){
        this.sectionResourceDao.delete(sectionResource);
    }
    // Default access
    List<SectionResource> prepareSectionResource(SectionResourceForm[] secResList, FILE_TYPE fileType){
        List<SectionResource> sectionResources = new LinkedList<>();
        if(secResList==null || secResList.length==0){
            return sectionResources;
        }
        for(SectionResourceForm secRes : secResList){
            SectionResource tmpSectionResources =  this.prepareSectionResource(secRes,fileType);
            sectionResources.add(tmpSectionResources);
        }

        return sectionResources;
    }
    // Default access
    SectionResource prepareSectionResource(SectionResourceForm secResForm, FILE_TYPE fileType){
        Integer token = secResForm.getToken();
        String url = secResForm.getUrl();
        Boolean staticSelected = secResForm.getSelectedStatic();
        staticSelected = (staticSelected==null)?false:staticSelected;

        String logoFileName = this.fileService.copyFile(token);
        String mimeType = this.fileService.getMimeTypeByToken(token);
        SectionResource sectionResource = new SectionResource();

        sectionResource.setFileType(fileType);
        sectionResource.setFileName(logoFileName);
        sectionResource.setMimeType(mimeType);
        sectionResource.setUrl(url);
        sectionResource.setSelectedStatic(staticSelected);

        return sectionResource;
    }
    @Transactional(rollbackFor = Exception.class)
    public void  removeSectionResource(AdvertiserAndAllCompositeUpdateForm updateForm) throws EntityNotFound{
        this.removeSectionResource(updateForm.getGalleryAds());
        this.removeSectionResource(updateForm.getSlideShowAds());
        this.removeSectionResource(updateForm.getPopupAds());
    }


    @Transactional(rollbackFor = Exception.class)
    public void  removeSectionResource(SlideShowAdsUpdateForm slideShowAdsUpdateForm) throws EntityNotFound{
        Integer[] bannerRmIds =  slideShowAdsUpdateForm.getRemoveBannerIds();
        List<Integer> rmSecResource = new ArrayList<>();

        if(bannerRmIds!=null){
            rmSecResource.addAll(Arrays.asList(bannerRmIds));
        }
        if(rmSecResource.size()>0){
            this.delete(rmSecResource.toArray(new Integer[rmSecResource.size()]));
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void  removeSectionResource(PopupAdsUpdateForm popupAdsUpdateForm) throws EntityNotFound{

        List<Integer> rmSecResource = new ArrayList<>();
        Integer[] smsBannerRmIds = popupAdsUpdateForm.getRemoveSmsBannerIds();
        Integer[] emailBannerRmIds = popupAdsUpdateForm.getRemoveEmailBannerIds();

        if(smsBannerRmIds!=null)rmSecResource.addAll(Arrays.asList(smsBannerRmIds));
        if(emailBannerRmIds!=null)rmSecResource.addAll(Arrays.asList(emailBannerRmIds));
        if(rmSecResource.size()>0){
            this.delete(rmSecResource.toArray(new Integer[rmSecResource.size()]));
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Integer>  removeSectionResource(GalleryAdsUpdateForm galleryAdsForm) throws EntityNotFound{

        /** Removed File */
        Integer logoRmId = galleryAdsForm.getRemoveLogoId();
        Integer[] backgroundRmIds = galleryAdsForm.getRemoveBackgroundIds();
        Integer[] tBannerRmIds = galleryAdsForm.getRemoveTopBannerIds();
        Integer[] bBannerRmIds = galleryAdsForm.getRemoveBottomBannerIds();


        List<Integer> rmSecResource = new ArrayList<>();

        if(logoRmId!=null)rmSecResource.add(logoRmId);
        if(backgroundRmIds!=null)rmSecResource.addAll(Arrays.asList(backgroundRmIds));
        if(tBannerRmIds!=null)rmSecResource.addAll(Arrays.asList(tBannerRmIds));
        if(bBannerRmIds!=null)rmSecResource.addAll(Arrays.asList(bBannerRmIds));

        if(rmSecResource.size()>0){
            this.delete(rmSecResource.toArray(new Integer[rmSecResource.size()]));
        }
        return rmSecResource;
    }
}