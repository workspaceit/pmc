package com.workspaceit.pmc.constant.advertisement;

import com.workspaceit.pmc.exception.EntityNotFound;

public enum ADVERTISEMENT_TYPE {
     GALLERY, SLIDESHOW, POPUP_SMS,POPUP_EMAIL;

     public static ADVERTISEMENT_TYPE getFromString(String type) throws EntityNotFound {
          for(ADVERTISEMENT_TYPE adType : ADVERTISEMENT_TYPE.values()){
               if(adType.name().toLowerCase().equals(type.toLowerCase())){
                    return adType;
               }
          }
          throw new EntityNotFound("ADVERTISEMENT_TYPE not found");
     }


}