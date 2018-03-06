package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS;
import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import com.workspaceit.pmc.entity.advertisement.Section;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SectionDao extends BaseDao{
    public Section getById(int id){
        Session session = this.getCurrentSession();
        return (Section)session.createQuery("select distinct sec FROM Section sec " +
                                            "left join fetch sec.advertisement as adv WHERE sec.id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public List<Section> getByAdTypeSectionTypeAndRotation(ADVERTISEMENT_TYPE adType, SECTION_TYPE sectionType,
                                                           ADVERTISEMENT_ROTATION_SETTINGS rotationSettings,Integer... exceptIds){
        List<Integer> ids = new ArrayList<>();
        if(exceptIds!=null && exceptIds.length>0){
            ids = Arrays.asList(exceptIds);
        }
        if(ids.size()==0){
            return this.getByAdTypeSectionTypeAndRotation(adType,sectionType,rotationSettings);
        }

        Session session = this.getCurrentSession();
        return session.createQuery("FROM Section sec " +
                "join fetch sec.advertisement as adv  " +
                "WHERE adv.adType = :adType and " +
                "sec.sectionType = :sectionType and " +
                "sec.rotation = :rotationSettings and " +
                "sec.id not in (:ids) ")
                .setParameter("adType",adType)
                .setParameter("sectionType",sectionType)
                .setParameter("rotationSettings",rotationSettings)
                .setParameter("ids",ids)
                .list();
    }
    public List<Section> getByAdTypeSectionTypeAndRotation(ADVERTISEMENT_TYPE adType, SECTION_TYPE sectionType,
                                                           ADVERTISEMENT_ROTATION_SETTINGS rotationSettings){

        Session session = this.getCurrentSession();
        return session.createQuery("FROM Section sec " +
                "join fetch sec.advertisement as adv  " +
                "WHERE adv.adType = :adType and " +
                "sec.sectionType = :sectionType and " +
                "sec.rotation = :rotationSettings ")
                .setParameter("adType",adType)
                .setParameter("sectionType",sectionType)
                .setParameter("rotationSettings",rotationSettings)
                .list();
    }
}