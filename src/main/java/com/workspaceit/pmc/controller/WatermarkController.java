package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.service.WatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi_rafi on 12/26/17.
 */

@Controller
@RequestMapping(value = ControllerUriPrefix.ADMIN+"/watermark")
public class WatermarkController {

    private Environment environment;


    private WatermarkService watermarkService;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView addWatermark(Authentication authentication){

        ModelAndView model = new ModelAndView("admin/watermark/add");
        model.addObject("previewSampleUri",environment.getWatermarkSamplePreviewImgUri());
        return model;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allWatermark(Authentication authentication){
        List<Watermark> watermarkList=watermarkService.getAll();
        ModelAndView model = new ModelAndView("admin/watermark/all");


        model.addObject("previewSampleUri",environment.getWatermarkSamplePreviewImgUri());
        model.addObject("watermarkList",watermarkList);
        return model;
    }


    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView editWatermark(@PathVariable("id") int id){
        Watermark watermark = this.watermarkService.getById(id);
        if(watermark==null){
            return new ModelAndView("redirect:"+"/admin/watermark/all");
        }


        ModelAndView model = new ModelAndView("admin/watermark/edit");
        model.addObject("previewSampleUri",environment.getWatermarkSamplePreviewImgUri());
        model.addObject("watermark",watermark);
        return model;
    }


}
