package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.service.AdminService;
import com.workspaceit.pmc.service.WatermarkService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static java.lang.System.in;

/**
 * Created by mi_rafi on 12/26/17.
 */

@Controller
@RequestMapping(value = "/hello")
public class TestController {

    private WatermarkService watermarkService;

    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
    @ResponseBody
    @RequestMapping(value = "/watermark/{id}",method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] watermark(@PathVariable("id") int id) throws IOException {
        return watermarkService.getImageWithWaterMark(id);
    }

    @RequestMapping(value = "/watermark1/{id}",method = RequestMethod.GET)
    public  ResponseEntity<byte[]> watermark1(@PathVariable("id") int id) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(watermarkService.getImageWithWaterMark(id), headers, HttpStatus.CREATED);
    }



}
