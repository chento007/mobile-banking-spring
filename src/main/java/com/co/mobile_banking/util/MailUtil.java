package com.co.mobile_banking.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Meta<T>{
        private String to;
        private String from;
        private String subject;
        private String templateUrl;
        private  T data;
    }
    public void send(Meta<?> meta) throws MessagingException {
        /**
         * MiMeMessage : use to create and sen email in MIME format .
         * MiMe format : is a standard format email message . such as text,subject,send/from to who and html image file
         *
         */
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
        //1. prepare template
        /**
         * - Context : use to pass data from server to template
         * - templateEngine.process : use to generate HTML ,and it requires 2 argu url and context
         * - when you set text in helper it will update in MimeMessage , it is the reason you
         * put in argument mimeMessage instance of helper that you set value hahahaha
         */
        Context context=new Context();
        context.setVariable("data",meta.data);
        String htmlTemplate=templateEngine.process(meta.templateUrl,context);
        helper.setText(htmlTemplate,true);//after generate html then set that code in text
        //2. prepare email send information
        helper.setTo(meta.to);
        helper.setFrom(meta.from);
        helper.setSubject(meta.subject);
        //3. send
        javaMailSender.send(mimeMessage);
    }
}
