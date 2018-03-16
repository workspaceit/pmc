package com.workspaceit.pmc.util;

import com.workspaceit.pmc.constant.DAB_OPERATION;
import com.workspaceit.pmc.constant.ENTITY_CARDINALITY;
import com.workspaceit.pmc.constant.NOTIFICATION_DETAILS_REPLACE_TAG;
import com.workspaceit.pmc.entity.Admin;
import com.workspaceit.pmc.entity.Notification;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component
public class NotificationUtil {
    HashMap<String,String> messages = new HashMap<>();
    String replacePrefix = "$";

    @PostConstruct
    private void init() throws IOException {
        String fileName = "notification.properties";
        String fileRelPath = "/msg/"+fileName;
        URL url =  Notification.class.getResource(fileRelPath);
        if(url==null){
            throw new RuntimeException(fileRelPath+" is missing");
        }
        InputStream  inputStream =  url.openStream();
        Properties notificationMsgProperties = new Properties();
        notificationMsgProperties.load(inputStream);
        this.populateNotificationMassagesMap(notificationMsgProperties);
    }
    private void populateNotificationMassagesMap(Properties notificationMsgProperties){
      Set<Map.Entry<Object,Object>> entries =  notificationMsgProperties.entrySet();
      for(Map.Entry<Object,Object> entry:entries){
         String val =  (String) entry.getValue();
         if(val!=null){
             val = val.trim();
         }
          messages.put((String) entry.getKey(),val);
      }
    }
    private String makeMessagesMapKey(Class<?> aClass, ENTITY_CARDINALITY cardinality,DAB_OPERATION operation){
        return "notification.msg."
                +aClass.getSimpleName().toLowerCase()+"."
                +operation.name().toLowerCase()+"."
                +cardinality.name().toLowerCase();
    }
    public String getDetails(Class<?> aClass, ENTITY_CARDINALITY cardinality,DAB_OPERATION operation){

        String key = this.makeMessagesMapKey(aClass,cardinality, operation);
        return messages.get(key);
    }
    public String fillTextInDetails(NOTIFICATION_DETAILS_REPLACE_TAG tag,String details,String replaceText){
        return details.replace(replacePrefix+tag.name().toLowerCase(),replaceText);
    }

    public static void main(String[] args) throws IOException {
        NotificationUtil notificationUtil = new NotificationUtil();
        notificationUtil.init();
        String details = notificationUtil.getDetails(Admin.class,ENTITY_CARDINALITY.ONE, DAB_OPERATION.CREATE);
        System.out.println(notificationUtil.fillTextInDetails(NOTIFICATION_DETAILS_REPLACE_TAG.NAME,details,"420"));
    }
}
