package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.ReportImageDao;
import com.workspaceit.pmc.entity.Event;
import com.workspaceit.pmc.entity.EventImage;
import com.workspaceit.pmc.entity.Photographer;
import com.workspaceit.pmc.entity.ReportedImage;
import com.workspaceit.pmc.helper.EmailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by anik on 3/19/18.
 */

@Service
public class ReportImageService {

    private ReportImageDao reportImageDao;
    private EmailHelper emailHelper;

    @Autowired
    public void setReportImageDao(ReportImageDao reportImageDao) {
        this.reportImageDao = reportImageDao;
    }

    @Autowired
    public void setEmailHelper(EmailHelper emailHelper) {
        this.emailHelper = emailHelper;
    }

    @Transactional
    public void reportImage(EventImage eventImage) {
        ReportedImage reportedImage = new ReportedImage();
        reportedImage.setEventImage(eventImage);
        reportedImage.setActionTaken(false);
        reportImageDao.insert(reportedImage);
        sendReportedImageNotifierMail(eventImage);
    }

    public void sendReportedImageNotifierMail(EventImage eventImage){
        Set<Photographer> photographers = eventImage.getEvent().getPhotographers();
        for(Photographer photographer: photographers){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("Inside Email sending");
                    emailHelper.sendReportedImageNotifierMail(photographer, eventImage.getEvent());
                    System.out.println("Inside Email sent");
                }
            }).start();
        }
    }

    @Transactional
    public Boolean isReported(EventImage eventImage){
        return reportImageDao.isReported(eventImage);
    }

    @Transactional
    public List<ReportedImage> getAllByEventId(int eventId){
        return reportImageDao.getAllByEventId(eventId);
    }

    @Transactional
    public Boolean takeAction(int[] imageIds){
        for (int imageId:imageIds) {
            ReportedImage reportedImage = reportImageDao.getByImageId(imageId);
            reportedImage.setActionTaken(true);
            reportImageDao.update(reportedImage);

        }
        return true;
    }

    @Transactional
    public int getReportedImageCountByEvent(Event event) {
        return reportImageDao.getCountByEvent(event);
    }
}
