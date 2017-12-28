package com.workspaceit.pmc.dao;

import com.workspaceit.pmc.entity.TempFile;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TempFileDao extends BaseDao {

    public void insert(TempFile tempFile){
        Session session = this.getCurrentSession();
        session.save(tempFile);
    	
    }

    public TempFile getByToken(int token){
        Session session = this.openSession();

        try{
            return (TempFile)session.createQuery("FROM TempFile  WHERE token=:token")
                    .setParameter("token",token)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            if(session!=null){
                session.close();
            }

        }

    }
    public List<TempFile> getByToken(List<Integer> tokenList){
        Session session = this.openSession();
        try{
            if(tokenList!=null && tokenList.size()>0){
                return session.createQuery("FROM TempFile  WHERE token in :tokenList ")
                        .setParameter("tokenList",tokenList)
                        .getResultList();
            }
        }finally {
            if(session!=null){
                session.close();
            }

        }

        return null;
    }
    public void delete(TempFile tempFile){
        Session session = this.getCurrentSession();
        session.delete(tempFile);
    }
}
