package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.SentSlideshow;
import org.springframework.stereotype.Repository;

@Repository
public class SentSlideShowDao extends BaseDao{

    public void save(SentSlideshow sentSlideshow){
        this.insert(sentSlideshow);
    }
}
