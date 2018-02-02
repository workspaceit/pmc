package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.SectionDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.util.FileToken;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class SectionService {

    private SectionDao sectionDao;

    private SectionResourceService sectionResourceService;
    private FileService fileService;



    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Autowired
    public void setSectionResourceService(SectionResourceService sectionResourceService) {
        this.sectionResourceService = sectionResourceService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }



    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement advertisement, GalleryAdsUpdateForm galleryAdsForm) throws EntityNotFound{
        /** New File */
        Integer logoToken =  galleryAdsForm.getLogoToken();
        Integer bgToken =  galleryAdsForm.getBgImgTokens();
        Integer[] tBannerNewToken = galleryAdsForm.getTopBannerImgTokens();
        Integer[] bBannerNewToken = galleryAdsForm.getBottomBannerImgTokens();

        if(tBannerNewToken==null)tBannerNewToken=new Integer[0];
        if(bBannerNewToken==null)bBannerNewToken=new Integer[0];

        FileToken logoNewFileToken =(logoToken!=null && logoToken>0)?new FileToken(logoToken,FILE_TYPE.IMAGE):null;
        FileToken bgNewFileToken = (bgToken!=null && bgToken>0)?new FileToken(bgToken,FILE_TYPE.IMAGE):null;
        List<FileToken> tBannerNewFileToken = new LinkedList<>();
        List<FileToken> bBannerNewFileToken = new LinkedList<>();


        /** Removed File */
        Integer[] tBannerRmIds = galleryAdsForm.getRemoveTopBannerIds();
        Integer[] bBannerRmIds = galleryAdsForm.getRemoveBottomBannerIds();


        /** All gallery section */
        List<Section> gallerySections = new LinkedList<>();
        Section logoSection =  advertisement.getSections().get(SECTION_TYPE.LOGO);
        Section bgSection = advertisement.getSections().get(SECTION_TYPE.BACKGROUND);
        Section tBannerSection = advertisement.getSections().get(SECTION_TYPE.TOP_BANNER);
        Section bBannerSection = advertisement.getSections().get(SECTION_TYPE.BOTTOM_BANNER);


        for(Integer token :tBannerNewToken){
            tBannerNewFileToken.add(new FileToken(token,FILE_TYPE.IMAGE));
        }

        for(Integer token :bBannerNewToken){
            bBannerNewFileToken.add(new FileToken(token,FILE_TYPE.IMAGE));
        }

        logoSection =  this.populateIfChange(logoSection,
                null,
                null,
                null,
                null,
                null,
                null,
                logoNewFileToken,
                null);

        bgSection =  this.populateIfChange(bgSection,
                null,
                null,
                null,
                null,
                null,
                null,
                bgNewFileToken,
                null);

        tBannerSection =  this.populateIfChange(tBannerSection,
                galleryAdsForm.getTopBannerPrice(),
                null,
                null,
                null,
                galleryAdsForm.getTopBannerRotation(),
                galleryAdsForm.getTopBannerExpiryDate(),
                null,
                tBannerNewFileToken);

        bBannerSection=  this.populateIfChange(bBannerSection,
                galleryAdsForm.getBottomBannerPrice(),
                null,
                null,
                null,
                galleryAdsForm.getBottomBannerRotation(),
                galleryAdsForm.getBottomBannerExpiryDate(),
                null,
                bBannerNewFileToken);

        gallerySections.add(logoSection);
        gallerySections.add(bgSection);
        gallerySections.add(tBannerSection);
        gallerySections.add(bBannerSection);

        this.update(gallerySections);

        List<Integer> tBrmIds = (tBannerRmIds!=null) ? Arrays.asList(tBannerRmIds):new ArrayList<>();
        List<Integer> bBrmIds = (bBannerRmIds!=null) ? Arrays.asList(bBannerRmIds):new ArrayList<>();

        List<SectionResource> rmSecResource = new ArrayList<>();
        List<SectionResource> tBannerSectionRes =  tBannerSection.getSectionResource();
        List<SectionResource> bBannerSectionRes =  bBannerSection.getSectionResource();

        System.out.println("tBannerSectionRes.size() "+tBannerSectionRes.size());
        for(SectionResource secRes:tBannerSectionRes){
            if(tBrmIds.contains(secRes.getId())){
                rmSecResource.add(secRes);
            }
        }

        /** Remove from  of oneToMany association  section first
         *  Cascade All re-save if delete object exist in List of section
         * */
        tBannerSectionRes.removeAll(rmSecResource);
        System.out.println("bBannerSectionRes.size() "+bBannerSectionRes.size());
        for(SectionResource secRes:bBannerSectionRes){
            if(bBrmIds.contains(secRes.getId())){
                rmSecResource.add(secRes);
            }
        }

        /** Remove from  of oneToMany association  section first
         *  Cascade All re-save if delete object exist in List of section
         * */
        bBannerSectionRes.removeAll(rmSecResource);
        System.out.println("rmSecResource.size() "+rmSecResource.size());
        if(rmSecResource.size()>0){
            this.sectionResourceService.delete(rmSecResource);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement advertisement, SlideShowAdsUpdateForm slideShowAdsForm) throws EntityNotFound{
        /** New File */
        Integer[] tBannerToken =  slideShowAdsForm.getSlideShowAdsBannerTokens();
        Integer bBannerVideoToken =  slideShowAdsForm.getSlideShowAdsVideoToken();

        if(tBannerToken==null)tBannerToken=new Integer[0];

        List<FileToken> tBannerNewFileToken = new LinkedList<>();
        FileToken bBannerVideoNewFileToken = (bBannerVideoToken!=null)? new FileToken(bBannerVideoToken,FILE_TYPE.VIDEO):null;

        /** Removed File */
        Integer[] tBannerRmIds = slideShowAdsForm.getRemoveBannerIds();


        /** All slide show section */
        List<Section> slideShowSections = new LinkedList<>();
        Section tBannerSection = advertisement.getSections().get(SECTION_TYPE.TOP_BANNER);
        Section bBannerSection = advertisement.getSections().get(SECTION_TYPE.BOTTOM_BANNER);


        for(Integer token :tBannerToken){
            tBannerNewFileToken.add(new FileToken(token,FILE_TYPE.IMAGE));
        }


        tBannerSection =  this.populateIfChange(tBannerSection,
                slideShowAdsForm.getBannerPrice(),
                null,
                slideShowAdsForm.getSlideShowBannerDuration(),
                null,
                slideShowAdsForm.getBannerRotation(),
                slideShowAdsForm.getBannerExpiryDate(),
                null,
                tBannerNewFileToken);

        bBannerSection=  this.populateIfChange(bBannerSection,
                slideShowAdsForm.getVideoPrice(),
                1,
                slideShowAdsForm.getSlideShowVideoDuration(),
                null,
                slideShowAdsForm.getVideoRotation(),
                slideShowAdsForm.getVideoExpiryDate(),
                bBannerVideoNewFileToken,
                null);

        slideShowSections.add(tBannerSection);
        slideShowSections.add(bBannerSection);

        this.update(slideShowSections);


        /** Remove section resource*/

        List<Integer> tBrmIds = (tBannerRmIds!=null) ? Arrays.asList(tBannerRmIds):new ArrayList<>();

        List<SectionResource> rmSecResource = new ArrayList<>();
        List<SectionResource> tBannerSectionRes =  tBannerSection.getSectionResource();

        for(SectionResource secRes:tBannerSectionRes){
            if(tBrmIds.contains(secRes.getId())){
                rmSecResource.add(secRes);
            }
        }

        /** Remove from  of oneToMany association  section first
         *  Cascade All re-save if delete object exist in List of section
         * */
        tBannerSectionRes.removeAll(rmSecResource);


        if(rmSecResource.size()>0){
            this.sectionResourceService.delete(rmSecResource);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement smsAdv,Advertisement emailAdv, PopupAdsUpdateForm popupAdsUpdateForm) throws EntityNotFound{
        /** New File */
        Integer[] smsBannerToken =  popupAdsUpdateForm.getSmsPopupBanner();
        Integer[] emailBannerToken =  popupAdsUpdateForm.getEmailPopupBanner();
        List<FileToken> smsBannerNewFileToken = new LinkedList<>();
        List<FileToken> emailBannerNewFileToken = new LinkedList<>();

        if(smsBannerToken==null)smsBannerToken=new Integer[0];
        if(emailBannerToken==null)emailBannerToken=new Integer[0];


        Integer smsVideoToken =  popupAdsUpdateForm.getSmsPopupVideo();
        Integer emailVideoToken =  popupAdsUpdateForm.getEmailPopupVideo();
        FileToken smsVideoNewFileToken = (smsVideoToken!=null)? new FileToken(smsVideoToken,FILE_TYPE.VIDEO):null;
        FileToken emailVideoNewFileToken = (emailVideoToken!=null)? new FileToken(emailVideoToken,FILE_TYPE.VIDEO):null;

        /** Removed File */
        Integer[] smsBannerRmIds = popupAdsUpdateForm.getRemoveSmsBannerIds();
        Integer[] emailBannerRmIds = popupAdsUpdateForm.getRemoveEmailBannerIds();

        /** All slide show section */
        List<Section> popupAdSections = new LinkedList<>();
        Section smsBannerSection = smsAdv.getSections().get(SECTION_TYPE.BANNER);
        Section emailBannerSection = emailAdv.getSections().get(SECTION_TYPE.BANNER);


        for(Integer token :smsBannerToken){
            smsBannerNewFileToken.add(new FileToken(token,FILE_TYPE.IMAGE));
        }
        for(Integer token :emailBannerToken){
            emailBannerNewFileToken.add(new FileToken(token,FILE_TYPE.IMAGE));
        }

        smsBannerSection =  this.populateIfChange(smsBannerSection,
                popupAdsUpdateForm.getSmsAdPrice(),
                null,
                popupAdsUpdateForm.getSmsPopupVideoDuration(),
                null,
                popupAdsUpdateForm.getSmsRotation(),
                popupAdsUpdateForm.getSmsExpiryDate(),
                smsVideoNewFileToken,
                smsBannerNewFileToken);

        emailBannerSection=  this.populateIfChange(emailBannerSection,
                popupAdsUpdateForm.getEmailAdPrice(),
                1,
                popupAdsUpdateForm.getSmsPopupVideoDuration(),
                null,
                popupAdsUpdateForm.getEmailRotation(),
                popupAdsUpdateForm.getEmailExpiryDate(),
                emailVideoNewFileToken,
                emailBannerNewFileToken);

        popupAdSections.add(smsBannerSection);
        popupAdSections.add(emailBannerSection);








        /** Update Section and remove section resource */

        this.update(popupAdSections);
        /** Single video or Multiple images*/


        if(smsVideoNewFileToken!=null){
            this.sectionResourceService.delete(smsBannerSection.getSectionResource());
        }
        if(emailVideoNewFileToken!=null){
            this.sectionResourceService.delete(emailBannerSection.getSectionResource());
        }



        if(smsBannerNewFileToken.size()>0){
            List<SectionResource> sectionResources = smsBannerSection.getSectionResource();
            if(sectionResources!=null){
                this.sectionResourceService.delete(sectionResources,FILE_TYPE.VIDEO);
            }

        }

        if(emailBannerNewFileToken.size()>0){
            List<SectionResource> sectionResources = emailBannerSection.getSectionResource();
            if(sectionResources!=null){
                this.sectionResourceService.delete(sectionResources,FILE_TYPE.VIDEO);
            }

        }


        if(smsBannerRmIds!=null && smsBannerRmIds.length>0){
            this.sectionResourceService.delete(smsBannerRmIds);
        }
        if(emailBannerRmIds!=null && emailBannerRmIds.length>0){
            this.sectionResourceService.delete(emailBannerRmIds);
        }


    }

    @Transactional(rollbackFor = Exception.class)
    public void  update(List<Section> sections) throws EntityNotFound{
        this.sectionDao.updateAll(sections);
    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(Section section) throws EntityNotFound{
        this.sectionDao.update(section);
    }
    @Transactional(rollbackFor = Exception.class)
    public void create(Advertisement advertisement,GalleryAdsForm galleryAdsForm,Admin admin){



        List<Section> sections = new ArrayList<>();
        List<SectionResource> logoSectionResources = new LinkedList<>();
        List<SectionResource> bgSectionResources = new LinkedList<>();
        List<SectionResource> topBannerSectionResources = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getTopBannerImgTokens(),FILE_TYPE.IMAGE);
        List<SectionResource> bottomBannerSectionResources = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getBottomBannerImgTokens(),FILE_TYPE.IMAGE);

        SectionResource tmpLogoSectionResource = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getLogoToken(),FILE_TYPE.IMAGE);
        SectionResource tmpBGSectionResource = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getBgImgTokens(),FILE_TYPE.IMAGE);


        logoSectionResources.add(tmpLogoSectionResource);
        bgSectionResources.add(tmpBGSectionResource);


        Section logoSection = this.getSection(advertisement.getId(),
                                            null,
                                            logoSectionResources.size(),
                                            null,
                                            SECTION_TYPE.LOGO,
                                            AdvertiseRotationSettings.STATIC,
                                            null,
                                            logoSectionResources,
                                            admin);

        Section bgSection = this.getSection(advertisement.getId(),
                                            galleryAdsForm.getBgPrice(),
                                            bgSectionResources.size(),
                                            null,
                                            SECTION_TYPE.BACKGROUND,
                                            AdvertiseRotationSettings.STATIC,
                                            null,
                                            bgSectionResources,
                                            admin);

        Section topBannerSection = this.getSection(
                                            advertisement.getId(),
                                            galleryAdsForm.getTopBannerPrice(),
                                            topBannerSectionResources.size(),
                                            null,
                                            SECTION_TYPE.TOP_BANNER,
                                            galleryAdsForm.getTopBannerRotation(),
                                            galleryAdsForm.getTopBannerExpiryDate(),
                                            topBannerSectionResources,
                                            admin);

        Section bottomBannerSection = this.getSection(
                                            advertisement.getId(),
                                            galleryAdsForm.getBottomBannerPrice(),
                                            bottomBannerSectionResources.size(),
                                            null,
                                            SECTION_TYPE.BOTTOM_BANNER,
                                            galleryAdsForm.getBottomBannerRotation(),
                                            galleryAdsForm.getBottomBannerExpiryDate(),
                                            bottomBannerSectionResources,
                                            admin);

        sections.add(logoSection);
        sections.add(bgSection);
        sections.add(topBannerSection);
        sections.add(bottomBannerSection);


        this.create(sections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create( Advertisement popSmsAdv,Advertisement popEmailAdv,PopupAdsForm popupAdsForm, Admin admin){
        Integer smsVideoToken = popupAdsForm.getSmsPopupVideo();
        Integer emailVideoToken = popupAdsForm.getEmailPopupVideo();

        List<Section> sections = new ArrayList<>();

        List<SectionResource> smsSecResources = this.sectionResourceService.prepareSectionResource(popupAdsForm.getSmsPopupBanner(),FILE_TYPE.IMAGE);
        List<SectionResource> emailSecResources = this.sectionResourceService.prepareSectionResource(popupAdsForm.getEmailPopupBanner(),FILE_TYPE.IMAGE);


        if(smsVideoToken!=null && smsVideoToken>0){
            SectionResource tmpSmsVideoSecResource =  this.sectionResourceService.prepareSectionResource(popupAdsForm.getSmsPopupVideo(),FILE_TYPE.VIDEO);
            smsSecResources.add(tmpSmsVideoSecResource);
        }

        if(emailVideoToken!=null && emailVideoToken>0){
            SectionResource tmpEmailVideoSecResource =  this.sectionResourceService.prepareSectionResource(popupAdsForm.getEmailPopupVideo(),FILE_TYPE.VIDEO);
            emailSecResources.add(tmpEmailVideoSecResource);
        }



        Section smsSection = this.getSection(
                                            popSmsAdv.getId(),
                                            popupAdsForm.getSmsAdPrice(),
                                            smsSecResources.size(),
                                            popupAdsForm.getSmsPopupVideoDuration(),
                                            SECTION_TYPE.BANNER,
                                            popupAdsForm.getSmsRotation(),
                                            popupAdsForm.getSmsExpiryDate(),
                                            smsSecResources,
                                            admin);

        Section emailSection = this.getSection(
                                            popEmailAdv.getId(),
                                            popupAdsForm.getEmailAdPrice(),
                                            emailSecResources.size(),
                                            popupAdsForm.getEmailPopupVideoDuration(),
                                            SECTION_TYPE.BANNER,
                                            popupAdsForm.getEmailRotation(),
                                            popupAdsForm.getEmailExpiryDate(),
                                            emailSecResources,
                                            admin);







        sections.add(emailSection);
        sections.add(smsSection);

        this.create(sections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Advertisement advertisement,SlideShowAdsForm slideShowAdsForm,Admin admin){



        List<Section> sections = new ArrayList<>();
        List<SectionResource> topBannerSectionResources = this.sectionResourceService.prepareSectionResource(slideShowAdsForm.getSlideShowAdsBannerTokens(),FILE_TYPE.IMAGE);
        List<SectionResource> videoSectionResources = new LinkedList<>();

        SectionResource tmpVideoSecResource =  this.sectionResourceService.prepareSectionResource(slideShowAdsForm.getSlideShowAdsVideoToken(),FILE_TYPE.VIDEO);

        videoSectionResources.add(tmpVideoSecResource);

        Section topBannerSection = this.getSection(
                                            advertisement.getId(),
                                            slideShowAdsForm.getBannerPrice(),
                                            topBannerSectionResources.size(),
                                            slideShowAdsForm.getSlideShowBannerDuration(),
                                            SECTION_TYPE.TOP_BANNER,
                                            slideShowAdsForm.getBannerRotation(),
                                            slideShowAdsForm.getBannerExpiryDate(),
                                            topBannerSectionResources,
                                            admin);

        Section videoSection = this.getSection(advertisement.getId(),
                                            slideShowAdsForm.getVideoPrice(),
                                            videoSectionResources.size(),
                                            slideShowAdsForm.getSlideShowVideoDuration(),
                                            SECTION_TYPE.BOTTOM_BANNER,
                                            slideShowAdsForm.getVideoRotation(),
                                            slideShowAdsForm.getVideoExpiryDate(),
                                            videoSectionResources,
                                            admin);





        sections.add(videoSection);
        sections.add(topBannerSection);


        this.create(sections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Section section){
        this.sectionDao.insert(section);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(List<Section> sections){
        this.sectionDao.insertAll(sections);
    }

    private Section getSection(Integer advertisementId,
                               Float price,
                               Integer quantity,
                               Integer duration,
                               SECTION_TYPE sectionType,
                               AdvertiseRotationSettings rotation,
                               Date expireDate,
                               List<SectionResource> sectionResources,
                               Admin createdBy){

        Section section = new Section();

        section.setAdvertisementId(advertisementId);
        section.setPrice(price);
        section.setQuantity(quantity);
        section.setDuration(duration);
        section.setSectionType(sectionType);
        section.setRotation(rotation);
        section.setExpireDate(expireDate);
        section.setSectionResource(sectionResources);
        section.setCreatedBy(createdBy);

        return section;

    }
    private Section populateIfChange(Section section,
                                     Float price,
                                     Integer quantity,
                                     Integer duration,
                                     SECTION_TYPE sectionType,
                                     AdvertiseRotationSettings rotation,
                                     Date expireDate,
                                     FileToken newSingleFileToken,
                                     List<FileToken> newTokens){


        if(price!=null) section.setPrice(price);
        if(quantity!=null) section.setQuantity(quantity);
        if(duration!=null) section.setDuration(duration);
        if(sectionType!=null) section.setSectionType(sectionType);
        if(rotation!=null) section.setRotation(rotation);
        if(expireDate!=null) section.setExpireDate(expireDate);

        List<SectionResource> sectionResources = section.getSectionResource();

        if(sectionResources==null){
            sectionResources =  new LinkedList<>();
        }

        /** Section has single file */
        if(newSingleFileToken!=null){
            /**
             * Delete previous file
             * If section has only one file
             *  */
            this.sectionResourceService.delete(sectionResources);
            sectionResources.clear();

            SectionResource sectionResource = this.sectionResourceService.prepareSectionResource(newSingleFileToken.getSingleToken(),newSingleFileToken.getFileType());
            sectionResources.add(sectionResource);
        }

        /** Section has many file */
        if(newTokens!=null){
            for(FileToken fileToken: newTokens ){
                SectionResource sectionResource = this.sectionResourceService.prepareSectionResource(fileToken.getSingleToken(),fileToken.getFileType());
                sectionResources.add(sectionResource);
            }
        }

        section.setSectionResource(sectionResources);

        return section;

    }




    private Section getSection(Integer id) throws EntityNotFound {

        Section section = this.sectionDao.getById(id);

        if(section==null){
            throw new EntityNotFound("section not found by id:"+id);
        }

        return section;

    }
}