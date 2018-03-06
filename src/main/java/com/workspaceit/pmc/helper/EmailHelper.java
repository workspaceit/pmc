package com.workspaceit.pmc.helper;

import com.workspaceit.pmc.config.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

@Component
public class EmailHelper {

    Environment environment;

    @Autowired
    public void setAppProperties(Environment appProperties) {
        this.environment = appProperties;
    }

    private static String from= "developer_beta@workspaceit.com";
    private  static String username = "developer_beta@workspaceit.com";
    private  static String password = "wsit9748!@";
    //private static String link = "http://localhost:3000/#/";
    private static String link = "http://localhost:8080/";

//    @PostConstruct
//    public void init(){
//        link = appProperties.getMailOrigin();
//    }

    private Properties getProperties(){
        Properties properties = System.getProperties();
        // properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", environment.getMailHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user",environment.getMailUsername());
        properties.put("mail.smtp.password",environment.getMailPassword());
        properties.put("mail.smtp.port",environment.getMailPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        from = environment.getMailSenderEmail();
        username = environment.getMailSenderEmail();
        password = environment.getMailSenderPassword();
        link = environment.getMailServerLink();
        return properties;
    }

    public  boolean sendPasswordRestMail(String email,String activationCode,String url){
        String to = email;
        Properties properties = this.getProperties();
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username,password);// Specify the Username and the PassWord
            }
        });
        String activationUrl = link +  "activate-account/"+activationCode;

        String link = "<a href='"+activationUrl+"'>Click here</a>";
        String emailHtmlBody = "Hi,<br>   Please click this link " + link + " to activate your account";
        try{
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Email Confirmation");
            message.setText(emailHtmlBody,null,"html");
            Transport.send(message);
            String title = "Email Confirmation";
            String body = "";
        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean sendPasswordResettMail(int userId,String email,String code) {
        String to = email;
        String id = Integer.toString(userId);
        Properties properties = getProperties();
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);// Specify the Username and the PassWord
            }
        });
        String activationUrl = link + "reset-password-verify/" +id+"/"+code;
        String link = "<a href='" + activationUrl + "'>Click here</a>";
        String emailHtmlBody = "Hi,<br>Please click this link " + link + " to reset your password";
        try {
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Password Reset");
            message.setText(emailHtmlBody, null, "html");
            Transport.send(message);
            System.out.println("inside the helper" + to);
            String title = "Password Reset";
            String body = "";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }


}