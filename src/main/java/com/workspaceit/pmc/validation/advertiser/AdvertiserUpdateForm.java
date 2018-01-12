package com.workspaceit.pmc.validation.advertiser;

/**
 * Created by mi_rafi on 1/4/18.
 */
public class AdvertiserUpdateForm extends AdvertiserForm {
    Integer[] removeLocationIds;
    Integer[] removeEventIds;
    Integer[] removeOtherImageIds;


    public Integer[] getRemoveLocationIds() {
        return removeLocationIds;
    }

    public void setRemoveLocationIds(Integer[] removeLocationIds) {
        this.removeLocationIds = removeLocationIds;
    }

    public Integer[] getRemoveEventIds() {
        return removeEventIds;
    }

    public void setRemoveEventIds(Integer[] removeEventIds) {
        this.removeEventIds = removeEventIds;
    }

    public Integer[] getRemoveOtherImageIds() {
        return removeOtherImageIds;
    }

    public void setRemoveOtherImageIds(Integer[] removeOtherImageIds) {
        this.removeOtherImageIds = removeOtherImageIds;
    }
}