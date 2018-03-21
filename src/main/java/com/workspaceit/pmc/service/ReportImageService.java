package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.ReportImageDao;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.ReportedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by anik on 3/19/18.
 */

@Service
public class ReportImageService {

    private ReportImageDao reportImageDao;

    @Autowired
    public void setReportImageDao(ReportImageDao reportImageDao) {
        this.reportImageDao = reportImageDao;
    }

    @Transactional
    public void reportImage(EventImage eventImage) {
        ReportedImage reportedImage = new ReportedImage();
        reportedImage.setEventImage(eventImage);
        reportedImage.setActionTaken(false);
        reportImageDao.insert(reportedImage);
    }

    @Transactional
    public Boolean isReported(EventImage eventImage){
        return reportImageDao.isReported(eventImage);
    }
}
