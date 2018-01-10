package com.workspaceit.pmc.validation.advertisement.price;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Tomal on 1/10/2018.
 */
public class AdvertisementPricesForm {

    @NotNull(message = "Price is required")
    private int[] ids;

    @NotNull(message = "Price is required")
    private double[] price;

    private String userEmail;


    public double[] getPrice() {
        return price;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public int[] getIds(){
        return ids;
    }
    public void setIds(int[] ids){this.ids =ids;}


    public String getUserEmail(){return this.userEmail;}

    public void setUserEmail(String userEmail){this.userEmail = userEmail;}



}
