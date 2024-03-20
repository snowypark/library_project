package com.study.library.service;

import com.study.library.security.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class AccountMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendAuthMail() {
        boolean result = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        String toMailAddress = principalUser.getEmail();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}

