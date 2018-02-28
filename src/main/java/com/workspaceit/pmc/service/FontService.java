package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.FontDao;
import com.workspaceit.pmc.entity.Font;
import com.workspaceit.pmc.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FontService {

    private FontDao fontDao;

    @Autowired
    public void setFontDao(FontDao fontDao) {
        this.fontDao = fontDao;
    }

    @Transactional
    public List<Font> getAll(){
        return this.fontDao.getAll();
    }
    @Transactional
    public Font getById(int id){
        return this.fontDao.getById(id);
    }
    @Transactional
    public Font getFont(int id) throws EntityNotFound {
        Font font =  this.fontDao.getById(id);
        if(font == null){
            throw new EntityNotFound("Font entity not found by :"+id);
        }
        return font;
    }
}