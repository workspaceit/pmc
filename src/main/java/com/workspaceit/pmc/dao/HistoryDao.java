package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.Admin;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryDao extends BaseDao {

    public AuditReader getAuditReader(){
        AuditReader auditReader = AuditReaderFactory.get(getCurrentSession());
       List<Number> numberList =  auditReader.getRevisions(Admin.class,139);
       for(Number rev :numberList){

         Admin admin =    auditReader.find(Admin.class,139,rev);
           System.out.println(admin.getName());
        }
        return null;
    }
}
