package com.study.member.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.sun.mail.handlers.image_gif;

@Service
public class MailSendService {
    
	@Inject   //context-mail에서 빈 등록했기 때문에 주입받을 수 있다. Spring에서 제공하는 MailSender. 
    JavaMailSenderImpl mailSender;
    
    private String getKey(int size) {
    	String key= "";
    	Random random= new Random();
    	for(int i = 0; i<6; i++) {
    		key += random.nextInt(10);
    	}
        return key;  //6개 숫자 랜덤 만들어보세요
    }
    public String sendAuthMail(String mail)  throws MessagingException{
        String authKey = getKey(6);
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String mailContent = "인증번호:   "+authKey ;     //보낼 메시지 
            mailMessage.setSubject("창희가보내는 이메일", "utf-8"); 
            mailMessage.setText(mailContent, "utf-8", "html");  
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            mailSender.send(mailMessage);
        
          return authKey;
    }
}