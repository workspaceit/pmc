package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings;
import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.SectionDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.validation.advertisement.gallery.GalleryAdsForm;
import com.workspaceit.pmc.validation.advertisement.popup.PopupAdsForm;
import com.workspaceit.pmc.validation.advertisement.slideshow.SlideShowAdsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
                                            SECTION_TYPE.LOGO,
                                            AdvertiseRotationSettings.STATIC,
                                            logoSectionResources,
                                            admin);

        Section bgSection = this.getSection(advertisement.getId(),
                                            galleryAdsForm.getBgPrice(),
                                            bgSectionResources.size(),
                                            SECTION_TYPE.BACKGROUND,
                                            AdvertiseRotationSettings.STATIC,
                                            bgSectionResources,
                                            admin);

        Section topBannerSection = this.getSection(
                                            advertisement.getId(),
                                            galleryAdsForm.getTopBannerPrice(),
                                            topBannerSectionResources.size(),
                                            SECTION_TYPE.TOP_BANNER,
                                            galleryAdsForm.getTopBannerRotation(),
                                            topBannerSectionResources,
                                            admin);

        Section bottomBannerSection = this.getSection(
                                            advertisement.getId(),
                                            galleryAdsForm.getBottomBannerPrice(),
                                            bottomBannerSectionResources.size(),
                                            SECTION_TYPE.BOTTOM_BANNER,
                                            galleryAdsForm.getBottomBannerRotation(),
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


        SectionResource tmpSmsVideoSecResource =  getSectionResource(popupAdsForm.getEmailPopupVideo(),FILE_TYPE.VIDEO);
        SectionResource tmpEmailVideoSecResource =  getSectionResource(popupAdsForm.getEmailPopupVideo(),FILE_TYPE.VIDEO);

        smsSecResources.add(tmpSmsVideoSecResource);
        emailSecResources.add(tmpEmailVideoSecResource);


        Section emailSection = this.getSection(
                popSmsAdv.getId(),
                popupAdsForm.getEmailAdPrice(),
                emailSecResources.size(),
                SECTION_TYPE.BANNER,
                popupAdsForm.getEmailRotation(),
                emailSecResources,
                admin);


        Section smsBannerSection = this.getSection(
                popEmailAdv.getId(),
                popupAdsForm.getEmailAdPrice(),
                emailSecResources.size(),
                SECTION_TYPE.BANNER,
                popupAdsForm.getEmailRotation(),
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
                SECTION_TYPE.TOP_BANNER,
                slideShowAdsForm.getBannerRotation(),
                topBannerSectionResources,
                admin);

        Section videoSection = this.getSection(advertisement.getId(),
                slideShowAdsForm.getVideoPrice(),
                videoSectionResources.size(),
                SECTION_TYPE.BOTTOM_BANNER,
                slideShowAdsForm.getVideoRotation(),
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
                               SECTION_TYPE sectionType,
                               AdvertiseRotationSettings rotation,
                               List<SectionResource> sectionResources,
                               Admin createdBy){

        Section section = new Section();

        section.setAdvertisementId(advertisementId);
        section.setPrice(price);
        section.setQuantity(quantity);
        section.setSectionType(sectionType);
        section.setRotation(rotation);
        section.setSectionResource(sectionResources);
        section.setCreatedBy(createdBy);

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
        SectionResource sectionResource = new SectionResource();

        sectionResource.setFileType(fileType);
        sectionResource.setFileName(logoFileName);

        return sectionResource;
    }
}