package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
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
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
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



    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Autowired
    public void setSectionResourceService(SectionResourceService sectionResourceService) {
        this.sectionResourceService = sectionResourceService;
    }


    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement advertisement, GalleryAdsUpdateForm galleryAdsForm) throws EntityNotFound{
        /** New File */
        Integer logoToken =  galleryAdsForm.getLogoSectionResource().getToken();
        Integer bgToken =  galleryAdsForm.getBgSectionResource().getToken();
        SectionResourceForm[] tBannerSectionResource = galleryAdsForm.getTopSectionResource();
        SectionResourceForm[] bBannerSectionResource = galleryAdsForm.getBottomSectionResource();

        if(tBannerSectionResource==null)tBannerSectionResource=new SectionResourceForm[0];
        if(bBannerSectionResource==null)bBannerSectionResource=new SectionResourceForm[0];

        FileToken logoNewFileToken =(logoToken!=null && logoToken>0)?new FileToken(galleryAdsForm.getLogoSectionResource(),FILE_TYPE.IMAGE):null;
        FileToken bgNewFileToken = (bgToken!=null && bgToken>0)?new FileToken(galleryAdsForm.getBgSectionResource(),FILE_TYPE.IMAGE):null;
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


        for(SectionResourceForm sectionResource :tBannerSectionResource){
            tBannerNewFileToken.add(new FileToken(sectionResource,FILE_TYPE.IMAGE));
        }

        for(SectionResourceForm sectionResource  :bBannerSectionResource){
            bBannerNewFileToken.add(new FileToken(sectionResource,FILE_TYPE.IMAGE));
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
                galleryAdsForm.getBgPrice(),
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


        for(SectionResource secRes:tBannerSectionRes){
            if(tBrmIds.contains(secRes.getId())){
                rmSecResource.add(secRes);
            }
        }

        /** Remove from  of oneToMany association  section first
         *  Cascade All re-save if delete object exist in List of section
         * */
        tBannerSectionRes.removeAll(rmSecResource);

        for(SectionResource secRes:bBannerSectionRes){
            if(bBrmIds.contains(secRes.getId())){
                rmSecResource.add(secRes);
            }
        }

        /** Remove from  of oneToMany association  section first
         *  Cascade All re-save if delete object exist in List of section
         * */
        bBannerSectionRes.removeAll(rmSecResource);

        if(rmSecResource.size()>0){
            this.sectionResourceService.delete(rmSecResource);
        }

        Integer logoQ = (logoSection.getSectionResource()==null)?0:logoSection.getSectionResource().size();
        Integer bgQ = (bgSection.getSectionResource()==null)?0:bgSection.getSectionResource().size();
        Integer tbQ = (tBannerSection.getSectionResource()==null)?0:tBannerSection.getSectionResource().size();
        Integer bbQ = (bBannerSection.getSectionResource()==null)?0:bBannerSection.getSectionResource().size();

        logoSection.setQuantity(logoQ);
        bgSection.setQuantity(bgQ);
        tBannerSection.setQuantity(tbQ);
        bBannerSection.setQuantity(bbQ);

        this.update(gallerySections);
    }


    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement advertisement, SlideShowAdsUpdateForm slideShowAdsForm) throws EntityNotFound{
        /** New File */
        SectionResourceForm[] bannerResources =  slideShowAdsForm.getSlideShowAdsBannerResources();
        SectionResourceForm videoResources =  slideShowAdsForm.getSlideShowAdsVideoResources();
        Integer videoToken = (videoResources!=null )? videoResources.getToken():null;

        if(bannerResources==null)bannerResources=new SectionResourceForm[0];

        List<FileToken> tBannerNewFileToken = new LinkedList<>();
        FileToken bBannerVideoNewFileToken = (videoToken!=null && videoToken>0)? new FileToken(videoResources,FILE_TYPE.VIDEO):null;

        /** Removed File */
        Integer[] tBannerRmIds = slideShowAdsForm.getRemoveBannerIds();


        /** All slide show section */
        List<Section> slideShowSections = new LinkedList<>();
        Section tBannerSection = advertisement.getSections().get(SECTION_TYPE.TOP_BANNER);


        for(SectionResourceForm sectionResource :bannerResources){
            tBannerNewFileToken.add(new FileToken(sectionResource,FILE_TYPE.IMAGE));
        }


        tBannerSection =  this.populateIfChange(tBannerSection,
                slideShowAdsForm.getBannerPrice(),
                null,
                slideShowAdsForm.getSlideShowBannerDuration(),
                null,
                slideShowAdsForm.getBannerRotation(),
                slideShowAdsForm.getBannerExpiryDate(),
                bBannerVideoNewFileToken,
                tBannerNewFileToken);



        slideShowSections.add(tBannerSection);

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

        Integer tBQ = (tBannerSection.getSectionResource()==null)?0:tBannerSection.getSectionResource().size();

        tBannerSection.setQuantity(tBQ);

        this.update(slideShowSections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void  update(Advertisement smsAdv,Advertisement emailAdv, PopupAdsUpdateForm popupAdsUpdateForm) throws EntityNotFound{
        /** New File */
        SectionResourceForm[] smsBannerToken =  popupAdsUpdateForm.getSmsPopupBannerResources();
        SectionResourceForm[] emailBannerToken =  popupAdsUpdateForm.getEmailPopupBannerResources();
        List<FileToken> smsBannerNewFileToken = new LinkedList<>();
        List<FileToken> emailBannerNewFileToken = new LinkedList<>();

        if(smsBannerToken==null)smsBannerToken=new SectionResourceForm[0];
        if(emailBannerToken==null)emailBannerToken=new SectionResourceForm[0];


        Integer smsVideoToken =  popupAdsUpdateForm.getSmsPopupVideoResource().getToken();
        Integer emailVideoToken =  popupAdsUpdateForm.getEmailPopupVideoResource().getToken();

        FileToken smsVideoNewFileToken = (smsVideoToken!=null && smsVideoToken>0)? new FileToken(popupAdsUpdateForm.getSmsPopupVideoResource(),FILE_TYPE.VIDEO):null;
        FileToken emailVideoNewFileToken = (emailVideoToken!=null && emailVideoToken>0)? new FileToken(popupAdsUpdateForm.getEmailPopupVideoResource(),FILE_TYPE.VIDEO):null;

        /** Removed File */
        Integer[] smsBannerRmIds = popupAdsUpdateForm.getRemoveSmsBannerIds();
        Integer[] emailBannerRmIds = popupAdsUpdateForm.getRemoveEmailBannerIds();

        /** All popup show section */
        List<Section> popupAdSections = new LinkedList<>();
        Section smsBannerSection = smsAdv.getSections().get(SECTION_TYPE.BANNER);
        Section emailBannerSection = emailAdv.getSections().get(SECTION_TYPE.BANNER);


        for(SectionResourceForm sectionResourceForm :smsBannerToken){
            smsBannerNewFileToken.add(new FileToken(sectionResourceForm,FILE_TYPE.IMAGE));
        }
        for(SectionResourceForm sectionResourceForm :emailBannerToken){
            emailBannerNewFileToken.add(new FileToken(sectionResourceForm,FILE_TYPE.IMAGE));
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
                popupAdsUpdateForm.getEmailPopupVideoDuration(),
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

        List<SectionResource> smsSectionResources = smsBannerSection.getSectionResource();
        List<SectionResource> emailSectionResources = emailBannerSection.getSectionResource();


        if(smsBannerNewFileToken.size()>0){
            this.deleteResourceAndRemoveFromList(smsSectionResources,FILE_TYPE.VIDEO);
        }

        if(emailBannerNewFileToken.size()>0){
            this.deleteResourceAndRemoveFromList(emailSectionResources,FILE_TYPE.VIDEO);
        }


        if(smsBannerRmIds!=null && smsBannerRmIds.length>0){
            this.deleteResourceAndRemoveFromList(smsSectionResources,smsBannerRmIds);
        }
        if(emailBannerRmIds!=null && emailBannerRmIds.length>0){
            this.deleteResourceAndRemoveFromList(emailSectionResources,emailBannerRmIds);
        }


        Integer smsBQ = (smsBannerSection.getSectionResource()==null)?0:smsBannerSection.getSectionResource().size();
        Integer emailBQ = (emailBannerSection.getSectionResource()==null)?0:emailBannerSection.getSectionResource().size();

        smsBannerSection.setQuantity(smsBQ);
        emailBannerSection.setQuantity(emailBQ);

        /** Update Section and remove section resource */

        this.update(popupAdSections);

    }

    private void deleteResourceAndRemoveFromList(List<SectionResource> sectionResources,FILE_TYPE fileType){
        List<SectionResource> tmpSectionResources = null;
        if(sectionResources!=null){
            tmpSectionResources = this.sectionResourceService.delete(sectionResources,fileType);
        }
        if(tmpSectionResources!=null && tmpSectionResources.size()>0){
            sectionResources.removeAll(tmpSectionResources);
        }
    }
    private void deleteResourceAndRemoveFromList(List<SectionResource> sectionResources,Integer[] removeIds){
        List<SectionResource> tmpSecResources = this.sectionResourceService.getById(removeIds);
        this.sectionResourceService.delete(tmpSecResources);
        sectionResources.removeAll(tmpSecResources);

    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(List<Section> sections) throws EntityNotFound{
        this.sectionDao.updateAll(sections);
        //this.makePreviousSectionStaticToRotatingByAdvertisementId(sections);
        //sections.stream().forEach(section -> {System.out.println("Section "+section.getId()+" TYPE "+section.getSectionType());});
        System.out.println("****************** Section Ends ******************");
    }
    @Transactional(rollbackFor = Exception.class)
    public void  update(Section section) throws EntityNotFound{
       // this.makePreviousSectionStaticToRotatingByAdvertisementId(section);
        this.sectionDao.update(section);
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Section> create(Advertisement advertisement,GalleryAdsForm galleryAdsForm,Admin admin){



        List<Section> sections = new ArrayList<>();
        List<SectionResource> logoSectionResources = new LinkedList<>();
        List<SectionResource> bgSectionResources = new LinkedList<>();
        List<SectionResource> topBannerSectionResources = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getTopSectionResource(),FILE_TYPE.IMAGE);
        List<SectionResource> bottomBannerSectionResources = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getBottomSectionResource(),FILE_TYPE.IMAGE);

        SectionResource tmpLogoSectionResource = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getLogoSectionResource(),FILE_TYPE.IMAGE);
        SectionResource tmpBGSectionResource = this.sectionResourceService.prepareSectionResource(galleryAdsForm.getBgSectionResource(),FILE_TYPE.IMAGE);

        if(tmpLogoSectionResource.getFileName()!=null && !tmpLogoSectionResource.getFileName().trim().equals("")){

            logoSectionResources.add(tmpLogoSectionResource);
        }
        if(tmpBGSectionResource.getFileName()!=null && !tmpBGSectionResource.getFileName().trim().equals("")){

            bgSectionResources.add(tmpBGSectionResource);
        }



        Section logoSection = this.getSection(advertisement,
                                            null,
                                            logoSectionResources.size(),
                                            null,
                                            SECTION_TYPE.LOGO,
                                            ADVERTISEMENT_ROTATION_SETTINGS.STATIC,
                                            null,
                                            logoSectionResources,
                                            admin);

        Section bgSection = this.getSection(advertisement,
                                            galleryAdsForm.getBgPrice(),
                                            bgSectionResources.size(),
                                            null,
                                            SECTION_TYPE.BACKGROUND,
                                            ADVERTISEMENT_ROTATION_SETTINGS.STATIC,
                                            null,
                                            bgSectionResources,
                                            admin);

        Section topBannerSection = this.getSection(
                                            advertisement,
                                            galleryAdsForm.getTopBannerPrice(),
                                            topBannerSectionResources.size(),
                                            null,
                                            SECTION_TYPE.TOP_BANNER,
                                            galleryAdsForm.getTopBannerRotation(),
                                            (topBannerSectionResources.size()>0)?galleryAdsForm.getTopBannerExpiryDate():null,
                                            topBannerSectionResources,
                                            admin);

        Section bottomBannerSection = this.getSection(
                                            advertisement,
                                            galleryAdsForm.getBottomBannerPrice(),
                                            bottomBannerSectionResources.size(),
                                            null,
                                            SECTION_TYPE.BOTTOM_BANNER,
                                            galleryAdsForm.getBottomBannerRotation(),
                                            (bottomBannerSectionResources.size()>0)?galleryAdsForm.getBottomBannerExpiryDate():null,
                                            bottomBannerSectionResources,
                                            admin);

        sections.add(logoSection);
        sections.add(bgSection);
        sections.add(topBannerSection);
        sections.add(bottomBannerSection);


        this.create(sections);
        return sections;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<ADVERTISEMENT_TYPE,Section> create( Advertisement popSmsAdv,Advertisement popEmailAdv,PopupAdsForm popupAdsForm, Admin admin){
        Integer smsVideoToken = popupAdsForm.getSmsPopupVideoResource().getToken();
        Integer emailVideoToken = popupAdsForm.getEmailPopupVideoResource().getToken();

        List<Section> sections = new ArrayList<>();

        List<SectionResource> smsSecResources = this.sectionResourceService.prepareSectionResource(popupAdsForm.getSmsPopupBannerResources(),FILE_TYPE.IMAGE);
        List<SectionResource> emailSecResources = this.sectionResourceService.prepareSectionResource(popupAdsForm.getEmailPopupBannerResources(),FILE_TYPE.IMAGE);


        if(smsVideoToken!=null && smsVideoToken>0){
            SectionResource tmpSmsVideoSecResource =  this.sectionResourceService.prepareSectionResource(popupAdsForm.getSmsPopupVideoResource(),FILE_TYPE.VIDEO);
            smsSecResources.add(tmpSmsVideoSecResource);
        }

        if(emailVideoToken!=null && emailVideoToken>0){
            SectionResource tmpEmailVideoSecResource =  this.sectionResourceService.prepareSectionResource(popupAdsForm.getEmailPopupVideoResource(),FILE_TYPE.VIDEO);
            emailSecResources.add(tmpEmailVideoSecResource);
        }



        Section smsSection = this.getSection(
                                            popSmsAdv,
                                            popupAdsForm.getSmsAdPrice(),
                                            smsSecResources.size(),
                                            popupAdsForm.getSmsPopupVideoDuration(),
                                            SECTION_TYPE.BANNER,
                                            popupAdsForm.getSmsRotation(),
                (smsSecResources.size()>0)?popupAdsForm.getSmsExpiryDate():null,
                                            smsSecResources,
                                            admin);

        Section emailSection = this.getSection(
                                            popEmailAdv,
                                            popupAdsForm.getEmailAdPrice(),
                                            emailSecResources.size(),
                                            popupAdsForm.getEmailPopupVideoDuration(),
                                            SECTION_TYPE.BANNER,
                                            popupAdsForm.getEmailRotation(),
                (emailSecResources.size()>0)?popupAdsForm.getEmailExpiryDate():null,
                                            emailSecResources,
                                            admin);







        sections.add(smsSection);
        sections.add(emailSection);

        Map<ADVERTISEMENT_TYPE,Section> sectionMap = new HashMap<>();
        sectionMap.put(ADVERTISEMENT_TYPE.POPUP_SMS,smsSection);
        sectionMap.put(ADVERTISEMENT_TYPE.POPUP_EMAIL,emailSection);

        this.create(sections);

        return sectionMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Section> create(Advertisement advertisement,SlideShowAdsForm slideShowAdsForm,Admin admin){

        Integer videoToken = slideShowAdsForm.getSlideShowAdsVideoResources().getToken();

        List<Section> sections = new ArrayList<>();
        List<SectionResource> topBannerSectionResources = this.sectionResourceService.prepareSectionResource(slideShowAdsForm.getSlideShowAdsBannerResources(),FILE_TYPE.IMAGE);

        SectionResource tmpVideoSecResource = null;
        if(videoToken!=null && videoToken>0){
            tmpVideoSecResource =  this.sectionResourceService.prepareSectionResource(slideShowAdsForm.getSlideShowAdsVideoResources(),FILE_TYPE.VIDEO);

        }

        if( tmpVideoSecResource != null
                && tmpVideoSecResource.getFileName()!=null
                && !tmpVideoSecResource.getFileName().trim().equals("")){
            topBannerSectionResources.add(tmpVideoSecResource);
        }

        Section topBannerSection = this.getSection(
                                            advertisement,
                                            slideShowAdsForm.getBannerPrice(),
                                            topBannerSectionResources.size(),
                                            slideShowAdsForm.getSlideShowBannerDuration(),
                                            SECTION_TYPE.TOP_BANNER,
                                            slideShowAdsForm.getBannerRotation(),
                                            (topBannerSectionResources.size()>0)?slideShowAdsForm.getBannerExpiryDate():null,
                                            topBannerSectionResources,
                                            admin);


        sections.add(topBannerSection);


        this.create(sections);
        return sections;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Section section){
        this.sectionDao.insert(section);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(List<Section> sections){

       // this.makePreviousSectionStaticToRotatingByAdvertisementId(sections);
        this.sectionDao.insertAll(sections);
    }

    @Transactional(rollbackFor = Exception.class)
    public void makePreviousSectionStaticToRotatingByAdvertisementId(List<Section> sections){
        List<Section> updatableSectionList = new ArrayList<>();

        for(Section section :sections){
            Section tmpSec = this.sectionDao.getById(section.getId());
            if(section.getRotation().equals(ADVERTISEMENT_ROTATION_SETTINGS.STATIC)){
                List<Section> sectionList =  this.sectionDao.getByAdTypeSectionTypeAndRotation(tmpSec.getAdvertisement().getAdType(),
                        tmpSec.getSectionType(),
                        ADVERTISEMENT_ROTATION_SETTINGS.STATIC,section.getId());

                sectionList.stream().forEach(s->System.out.println("S "+s.getId()+" T"+s.getSectionType()));
                updatableSectionList.addAll(sectionList);

            }

        }
        for(Section section :updatableSectionList){
            section.setRotation(ADVERTISEMENT_ROTATION_SETTINGS.ROTATE);
        }
        updatableSectionList.removeAll(sections);

        updatableSectionList.stream().forEach(section -> {System.out.println("M "+section.getId());});
        if(updatableSectionList.size()>0){

            this.sectionDao.updateAll(updatableSectionList);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void makePreviousSectionStaticToRotatingByAdvertisementId(Section section){
        List<Section> updatableSectionList = new ArrayList<>();
        if(section.getRotation().equals(ADVERTISEMENT_ROTATION_SETTINGS.STATIC)){
            Section tmpSec = this.sectionDao.getById(section.getId());
            updatableSectionList =  this.sectionDao.getByAdTypeSectionTypeAndRotation(tmpSec.getAdvertisement().getAdType(),
                    tmpSec.getSectionType(),
                    ADVERTISEMENT_ROTATION_SETTINGS.STATIC,section.getId());
        }
        updatableSectionList.remove(section);
        this.sectionDao.update(updatableSectionList);
    }
    private Section getSection(Advertisement advertisement,
                               Float price,
                               Integer quantity,
                               Integer duration,
                               SECTION_TYPE sectionType,
                               ADVERTISEMENT_ROTATION_SETTINGS rotation,
                               Date expireDate,
                               List<SectionResource> sectionResources,
                               Admin createdBy){

        Section section = new Section();

        section.setAdvertisement(advertisement);
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
                                     ADVERTISEMENT_ROTATION_SETTINGS rotation,
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

        if(sectionResources==null || sectionResources.size()==0){
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

            SectionResource sectionResource = this.sectionResourceService.prepareSectionResource(newSingleFileToken.getSingleSecResource(),newSingleFileToken.getFileType());
            sectionResources.add(sectionResource);
        }

        /** Section has many file */
        if(newTokens!=null && newTokens.size()>0){

            Optional<SectionResource> optionalSectionResource = sectionResources.stream()
                                                                    .filter(secRes->secRes.getFileType().equals(FILE_TYPE.VIDEO))
                                                                    .findFirst();
            if(optionalSectionResource.isPresent()){
                sectionResources.clear();
                this.sectionResourceService.delete(optionalSectionResource.get());
            }
            for(FileToken fileToken: newTokens ){
                SectionResource sectionResource = this.sectionResourceService.prepareSectionResource(fileToken.getSingleSecResource(),fileToken.getFileType());
                sectionResources.add(sectionResource);
            }
        }

        section.setSectionResource(sectionResources);

        return section;

    }



    public Map<SECTION_TYPE,Section> getSectionInMap(List<Section> sections){
        Map<SECTION_TYPE,Section> sectionMap = new HashMap<>();

        for(Section section:sections){
            sectionMap.put(section.getSectionType(),section);
        }
        return sectionMap;
    }
    public Map<SECTION_TYPE,Section> getSectionInMap(Section section){
        Map<SECTION_TYPE,Section> sectionMap = new HashMap<>();

        sectionMap.put(section.getSectionType(),section);
        return sectionMap;
    }
    private Section getSection(Integer id) throws EntityNotFound {

        Section section = this.sectionDao.getById(id);

        if(section==null){
            throw new EntityNotFound("section not found by id:"+id);
        }

        return section;

    }
}