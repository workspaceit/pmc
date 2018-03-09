package com.workspaceit.pmc.helper;

import org.springframework.stereotype.Component;

@Component
public class EntityHelper {
    public String getClassName(String type){
        String entityClassName = "";
        switch (type){
            case "event":
                entityClassName = "Event";
                break;
            case "photographer":
                entityClassName = "Photographer";
                break;
            case "watermark":
                entityClassName = "Watermark";
                break;
            case "location":
                entityClassName = "Location";
                break;
            case "advertiser":
                entityClassName = "Advertiser";
                break;
            case "venue":
                entityClassName = "Venue";
                break;
            case "user":
                entityClassName = "Admin";
                break;
            default:
                break;
        }
        return entityClassName;
    }
}
