package com.workspaceit.pmc.constant.advertisement;

/**
 * Created by mi_rafi on 1/8/18.
 */
public enum PopupAdConstant implements AdvertisementDisplay {
    SMS("Popup Sms Ad"), EMAIL("Popup Email Ad");
    private String displayText;

    PopupAdConstant(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}