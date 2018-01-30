package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.SectionDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Advertiser;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.util.FileToken;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsUpdateForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.*;

@Transactional
@Service
public class SectionService {

    private FileService fileService;


    private SectionDao sectionDao;


    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement advertisement, GalleryAdsUpdateForm galleryAdsForm, Admin admin) throws EntityNotFound{
       Integer logoToken =  galleryAdsForm.getLogoToken();

       Section logoSection =  advertisement.getSections().get(SECTION_TYPE.LOGO);

       FileToken fileToken = new FileToken(logoToken,FILE_TYPE.IMAGE);


       logoSection =  this.getSection(logoSection,
                null,
                null,
                null,
                null,
                null,
                null,
                fileToken,
                null,
                null);


    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(Section section, GalleryAdsUpdateForm galleryAdsForm, Admin admin) throws EntityNotFound{




    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Advertisement advertisement,GalleryAdsForm galleryAdsForm,Admin admin){



        List<Section> sections = new ArrayList<>();
        List<SectionResource> logoSectionResources = new LinkedList<>();
        List<SectionResource> bgSectionResources = new LinkedList<>();
        List<SectionResource> topBannerSectionResources = getSectionResource(galleryAdsForm.getTopBannerImgTokens(),FILE_TYPE.IMAGE);
        List<SectionResource> bottomBannerSectionResources = getSectionResource(galleryAdsForm.getBottomBannerImgTokens(),FILE_TYPE.IMAGE);

        SectionResource tmpLogoSectionResource = getSectionResource(galleryAdsForm.getLogoToken(),FILE_TYPE.IMAGE);
        SectionResource tmpBGSectionResource = getSectionResource(galleryAdsForm.getBgImgTokens(),FILE_TYPE.IMAGE);


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



        List<Section> sections = new ArrayList<>();

        List<SectionResource> smsSecResources = getSectionResource(popupAdsForm.getSmsPopupBanner(),FILE_TYPE.IMAGE);
        List<SectionResource> emailSecResources = getSectionResource(popupAdsForm.getEmailPopupBanner(),FILE_TYPE.IMAGE);


        SectionResource tmpSmsVideoSecResource =  getSectionResource(popupAdsForm.getSmsPopupVideo(),FILE_TYPE.VIDEO);
        SectionResource tmpEmailVideoSecResource =  getSectionResource(popupAdsForm.getEmailPopupVideo(),FILE_TYPE.VIDEO);

        smsSecResources.add(tmpSmsVideoSecResource);
        emailSecResources.add(tmpEmailVideoSecResource);


        Section emailSection = this.getSection(
                                                popSmsAdv.getId(),
                                                popupAdsForm.getEmailAdPrice(),
                                                emailSecResources.size(),
                                                popupAdsForm.getEmailPopupVideoDuration(),
                                                SECTION_TYPE.BANNER,
                                                popupAdsForm.getEmailRotation(),
                                                popupAdsForm.getEmailExpiryDate(),
                                                emailSecResources,
                                                admin);


        Section smsBannerSection = this.getSection(
                                                popEmailAdv.getId(),
                                                popupAdsForm.getEmailAdPrice(),
                                                emailSecResources.size(),
                                                popupAdsForm.getSmsPopupVideoDuration(),
                                                SECTION_TYPE.BANNER,
                                                popupAdsForm.getEmailRotation(),
                                                popupAdsForm.getSmsExpiryDate(),
                                                smsSecResources,
                                                admin);




        sections.add(emailSection);
        sections.add(smsBannerSection);

        this.create(sections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Advertisement advertisement,SlideShowAdsForm slideShowAdsForm,Admin admin){



        List<Section> sections = new ArrayList<>();
        List<SectionResource> topBannerSectionResources = getSectionResource(slideShowAdsForm.getSlideShowAdsBannerTokens(),FILE_TYPE.IMAGE);
        List<SectionResource> videoSectionResources = new LinkedList<>();

        SectionResource tmpVideoSecResource =  getSectionResource(slideShowAdsForm.getSlideShowAdsVideoToken(),FILE_TYPE.VIDEO);

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
    private Section getSection(Section section,
                               Float price,
                               Integer quantity,
                               Integer duration,
                               SECTION_TYPE sectionType,
                               AdvertiseRotationSettings rotation,
                               Date expireDate,
                               FileToken newSingleFileToken,
                               Integer[] newTokens,
                               Integer[] deletedToken){


        if(price!=null) section.setPrice(price);
        if(quantity!=null) section.setQuantity(quantity);
        if(duration!=null) section.setDuration(duration);
        if(sectionType!=null) section.setSectionType(sectionType);
        if(rotation!=null) section.setRotation(rotation);
        if(expireDate!=null) section.setExpireDate(expireDate);


        if(newSingleFileToken!=null){
            List<SectionResource> sectionResources = new LinkedList<>();

            SectionResource sectionResource = getSectionResource(newSingleFileToken.getSingleToken(),newSingleFileToken.getFileType());
            sectionResources.add(sectionResource);

            section.setSectionResource(sectionResources);
        }


        return section;

    }

    private List<SectionResource> getSectionResource(Integer[] tokens,FILE_TYPE fileType){
        List<SectionResource> sectionResources = new LinkedList<>();
        for(Integer token:tokens){
           SectionResource tmpSectionResources =  this.getSectionResource(token,fileType);

            sectionResources.add(tmpSectionResources);
        }

        return sectionResources;
    }
    private SectionResource getSectionResource(Integer token,FILE_TYPE fileType){
        String logoFileName = this.fileService.copyFile(token);
        String mimeType = this.fileService.getMimeTypeByToken(token);
        SectionResource sectionResource = new SectionResource();

        sectionResource.setFileType(fileType);
        sectionResource.setFileName(logoFileName);
        sectionResource.setMimeType(mimeType);

        return sectionResource;
    }


    private Section getSection(Integer id) throws EntityNotFound {

        Section section = this.sectionDao.getById(id);

        if(section==null){
            throw new EntityNotFound("section not found by id:"+id);
        }

        return section;

    }
}