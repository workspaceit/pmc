package com.workspaceit.pmc.controller;

import com.workspaceit.pmc.constant.ControllerUriPrefix;
import com.workspaceit.pmc.entity.*;
import com.workspaceit.pmc.service.*;
import com.workspaceit.pmc.util.MailUtil;
import com.workspaceit.pmc.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by mi_rafi on 1/3/18.
 */
@Controller
@RequestMapping(ControllerUriPrefix.ADMIN+"/invoice")
public class InvoiceController {
    private AdvertiserService advertiserService;
    private AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService;
    private AdvertiserTransactionService advertiserTransactionService;
    private AdvertisementService advertisementService;
    private MailUtil mailUtil;


    @Autowired
    public void setAdvertiserService(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    @Autowired
    public void setAdvertisementPriceAndQuantityService(AdvertisementPriceAndQuantityService advertisementPriceAndQuantityService) {
        this.advertisementPriceAndQuantityService = advertisementPriceAndQuantityService;
    }

    @Autowired
    public void setAdvertiserTransactionService(AdvertiserTransactionService advertiserTransactionService) {
        this.advertiserTransactionService = advertiserTransactionService;
    }
    @Autowired
    public void setAdvertisementService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @RequestMapping("/checkout/{reqType}/{advertiserId}")
    public ModelAndView invoice(@PathVariable("advertiserId") Integer advertiserId,
                                @PathVariable("reqType") String reqType,HttpServletResponse response) {

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        AdvertiserTransaction advertiserTransaction =  this.advertiserTransactionService.getLastByAdvertiserId(advertiser.getId());


        /* Price and quantity */
        Map<String,Object>   priceAndQuantity = this.advertisementPriceAndQuantityService.getSoldPriceAndQuantity(advertiserId);

        String priceMapKey = AdvertisementPriceAndQuantityService.priceMapKey;
        String quantityMapKey =AdvertisementPriceAndQuantityService.quantityMapKey;

        Map<Object,Float> prices =(Map<Object,Float>) priceAndQuantity.get(priceMapKey);
        Map<Object,Integer> quantities =(Map<Object,Integer>) priceAndQuantity.get(quantityMapKey);

        int transactionId = (advertiserTransaction!=null)?advertiserTransaction.getId():0;
        float totalPrice = this.advertisementPriceAndQuantityService.calculateTotal(prices,quantities);
        float discount = (advertiserTransaction!=null)?advertiserTransaction.getDiscount():0;
        float totalPayedPrice =(advertiserTransaction!=null)?advertiserTransaction.getTotalPaid():0;
        float totalDuePrice =(advertiserTransaction!=null)?advertiserTransaction.getTotalDue():(totalPrice-discount)-(totalPayedPrice);
        float priceAfterDiscount = totalPrice-discount;
        float amountReturn = (totalPayedPrice>priceAfterDiscount)?(totalPayedPrice - priceAfterDiscount):0;

        String pagePath;

        switch(reqType){
            case "page":
                pagePath = "admin/advertiser/checkout";
                break;
            case "ajax":
                pagePath = "admin/advertiser/checkout-body";
                break;
            default:
                return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }


        ModelAndView model = new ModelAndView(pagePath);

        model.addObject("transactionId",transactionId);
        model.addObject("advertiser",advertiser);

        /* Price and quantity */
        model.addObject("prices",prices);
        model.addObject("quantities",quantities);
        model.addObject("totalPrice",totalPrice);
        model.addObject("amountReturn",amountReturn);
        model.addObject("discount",discount);
        model.addObject("totalDuePrice",totalDuePrice);
        model.addObject("totalPayedPrice",totalPayedPrice);


        /* Number format settings values */
        model.addObject("currencyCode","USD");
        model.addObject("currencySymbol","$");
        model.addObject("maxFractionDigits",2);


        return model;
    }
    @RequestMapping(value = "/mail-tanveer/{advertiserId}",method = RequestMethod.GET)
    public ModelAndView mailInvoiceTanveer(@PathVariable("advertiserId")int advertiserId,
                                     @RequestParam("email") String email){
        //ModelAndView model = new ModelAndView(pagePath);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        if(email==null || email.equals("")){
            serviceResponse.setValidationError("email","Email required");
        }

        if(serviceResponse.hasErrors()){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        Advertiser advertiser =  this.advertiserService.getById(advertiserId);

        if(advertiser==null){
            return new ModelAndView("redirect:"+"/admin/advertiser/all");
        }

        String emailBody = this.advertisementService.getInvoiceInHtml(advertiser);
        //this.mailUtil.sendAdvertisementInvoice(email,emailBody);
        System.out.println(emailBody);
        ModelAndView model = new ModelAndView("admin/mail-view-test");

        model.addObject("emailBody",emailBody);
        return model;
    }
}