package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.VenueDao;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Location;
import com.workspaceit.pmc.entity.Venue;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.exception.EntityNotFound;
import com.workspaceit.pmc.validation.venue.VenueForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Tomal on 1/8/2018.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VenueService {
    private VenueDao venueDao;
    private LocationService locationService;
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    @Autowired
    protected void setVenueDao(VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    @Autowired
    protected void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Transactional
    public List<Venue> getAll(){
        return this.venueDao.getAll();
    }

    @Transactional
    public List<Venue> getActiveVenues(){
        return this.venueDao.getActiveVenues();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Venue> getSuggestedVenues(String searchTerm, Boolean active){
        return this.venueDao.getSuggestedVenues(searchTerm, active);
    }

    public Venue getById(int id){
        return this.venueDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(Venue venue){
        this.venueDao.insert(venue);
    }

    @Transactional(rollbackFor = Exception.class)
    public Venue create(VenueForm venueForm){
        Venue venue = getVenueFromVenueForm(venueForm);
        this.create(venue);

        return venue;
    }
    public void create(Venue venue){
        this.venueDao.insert(venue);
    }


    @Transactional(rollbackFor = Exception.class)
    public Venue update(int id,VenueForm venueForm)throws EntityNotFound{
        Venue venue = this.getVenue(id);
        venue.setName(venueForm.getName());
        venue.setCreatedBy(adminService.getAdminByEmail(venueForm.getUserEmail()));
        try {
            venue.setLocation(locationService.getLocation(Integer.parseInt(venueForm.getLocation_id())));
        }catch (Exception ex){}
        this.update(venue);

        return venue;
    }
    public void update(Venue venue){
        this.venueDao.update(venue);
    }


    public Venue getVenue(int id)throws EntityNotFound{
        Venue venue = this.venueDao.getById(id);
        if(venue==null){
            throw new EntityNotFound("Location not found by id :"+id);
        }

        return venue;
    }
    private Venue getVenueFromVenueForm(VenueForm venueForm){

        Venue venue = new Venue();

        venue.setName(venueForm.getName());
        venue.setCreatedBy(adminService.getAdminByEmail(venueForm.getUserEmail()));
        venue.setActive(true);
        try {
            venue.setLocation(locationService.getLocation(Integer.parseInt(venueForm.getLocation_id())));
        }catch (Exception ex){}

        return venue;
    }




}
