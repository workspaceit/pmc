package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.dao.SectionResourceDao;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class SectionResourceService {

    private SectionResourceDao sectionResourceDao;
    private FileService fileService;



    @Autowired
    public void setSectionResourceDao(SectionResourceDao sectionResourceDao) {
        this.sectionResourceDao = sectionResourceDao;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
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
}