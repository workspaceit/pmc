package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_TYPE;
import com.workspaceit.pmc.constant.advertisement.SECTION_TYPE;
import com.workspaceit.pmc.entity.advertisement.Advertisement;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertisementDao extends BaseDao {
    public Advertisement getById(Integer id){
        Session session = this.openSession();
        try{
            return (Advertisement)session.createQuery("SELECT adv FROM Advertisement adv " +
                    "inner join fetch adv.sections  " +
                    "WHERE adv.id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
    public List<Advertisement> getByAdvertiserId(Integer advertiserId){
        Session session = this.openSession();
        try{
            return (List<Advertisement>)session.createQuery("SELECT distinct adv FROM Advertisement adv " +
                    "inner join fetch adv.sections as section  " +
                    "WHERE adv.advertiserId=:advertiserId")
                    .setParameter("advertiserId",advertiserId)
                    .list();
        }finally {
            if(session!=null)session.close();
        }
    }
    public Advertisement getByIdAndType(Integer id, ADVERTISEMENT_TYPE adType){
        Session session = this.openSession();
        try{
            return (Advertisement)session.createQuery("SELECT distinct adv FROM Advertisement adv " +
                    "inner join fetch adv.sections as section  " +
                    "WHERE adv.id = :id and " +
                    " adv.adType =                                                                                                                                                      :adType")
                    .setParameter("id",id)
                    .setParameter("adType",adType)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
    public Advertisement getByAdvertiserIdAndType(Integer advertiserId, ADVERTISEMENT_TYPE adType){
        Session session = this.openSession();
        try{
            return (Advertisement)session.createQuery("SELECT distinct adv FROM Advertisement adv " +
                    "inner join fetch adv.sections as section  " +
                    "WHERE adv.advertiserId= :advertiserId and " +
                    " adv.adType =                                                                                                                                                      :adType")
                    .setParameter("advertiserId",advertiserId)
                    .setParameter("adType",adType)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null)session.close();
        }
    }
    public List<Advertisement> getByAdvertiserId(List<Integer> advertiserId){
        Session session = this.openSession();
        try{
            return (List<Advertisement>)session.createQuery("SELECT distinct adv FROM Advertisement adv " +
                    "inner join fetch adv.sections as section  " +
                    "WHERE advertiser_id in :advertiser_id")
                    .setParameter("advertiser_id",advertiserId)
                    .list();
        }finally {
            if(session!=null)session.close();
        }
    }

}