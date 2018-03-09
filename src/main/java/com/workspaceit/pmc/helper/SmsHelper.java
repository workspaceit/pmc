package com.workspaceit.pmc.helper;

import com.twilio.http.TwilioRestClient;
import com.workspaceit.pmc.config.Environment;
import com.workspaceit.pmc.util.TwilioMessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsHelper {
    Environment environment;

    private TwilioMessageCreator messageCreator;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setProperties(){
        this.messageCreator = new TwilioMessageCreator(
                new TwilioRestClient.Builder(environment.getSmsSid(), environment.getSmsAuthToken()).build()
        );
    }
    public boolean sendMessage(String name,String to,String code, String message) {
        this.setProperties();
        String msg = "Hi "+name+","+message;
        String mediaUrl = environment.getMailServerLink()+ "slideshow-images/"+code;
        String messageBody = msg+"click the link to see images: "+mediaUrl;
        System.out.println(messageBody);
        this.messageCreator.create(to, environment.getSmsPhoneNumber(), messageBody, null);
        return true;
    }
}
