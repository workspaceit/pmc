package com.workspaceit.pmc.service;

import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.dao.SectionResourceDao;
import com.workspaceit.pmc.entity.advertisement.SectionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

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

        return sectionResources;
    }



    @Transactional(rollbackFor = Exception.class)
    public void delete(SectionResource sectionResource){
        this.sectionResourceDao.delete(sectionResource);
    }
    // Default access
    List<SectionResource> prepareSectionResource(Integer[] tokens, FILE_TYPE fileType){
        List<SectionResource> sectionResources = new LinkedList<>();
        if(tokens==null){
            return sectionResources;
        }
        for(Integer token:tokens){
            SectionResource tmpSectionResources =  this.prepareSectionResource(token,fileType);
            sectionResources.add(tmpSectionResources);
        }

        return sectionResources;
    }
    // Default access
    SectionResource prepareSectionResource(Integer token, FILE_TYPE fileType){
        String logoFileName = this.fileService.copyFile(token);
        String mimeType = this.fileService.getMimeTypeByToken(token);
        SectionResource sectionResource = new SectionResource();

        sectionResource.setFileType(fileType);
        sectionResource.setFileName(logoFileName);
        sectionResource.setMimeType(mimeType);

        return sectionResource;
    }
}