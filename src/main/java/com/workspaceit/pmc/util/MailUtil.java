package com.workspaceit.pmc.util;


import com.workspaceit.pmc.config.Environment;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailUtil {

    private  String from;
    private  String username;
    private  String password;
    private  String appBaseUrl;

    private Environment environment;
    private TaskExecutor taskExecutor;
    private VelocityUtil velocityUtil;

    @Autowired
    public void setAppProperties(Environment appProperties) {
        this.environment = appProperties;
    }

    @Autowired
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Autowired
    public void setVelocityUtil(VelocityUtil velocityUtil) {
        this.velocityUtil = velocityUtil;
    }

    @PostConstruct
    private void init(){
        this.from = this.username = this.environment.getMailSenderEmail();
        this.password = this.environment.getMailPassword();
        this.appBaseUrl = this.environment.getFrontEndAppBaseUrl();
    }



    private Properties getProperties(){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", environment.getMailHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user",environment.getMailUsername());
        properties.put("mail.smtp.password",environment.getMailPassword());
        properties.put("mail.smtp.port",environment.getMailPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return properties;
    }



    public boolean sendAdvertisementInvoice(String recipientEmail,String emailHtmlBody) {

        String to = recipientEmail;
        Properties properties = getProperties();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });





        try {
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Advertisement Invoice");
            message.setText(emailHtmlBody, null, "html");
            /**
             * Mail sent asynchronously
             * */
            this.taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        transportMail(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
    private void transportMail(MimeMessage message) throws MessagingException {
        Transport.send(message);
    }


}