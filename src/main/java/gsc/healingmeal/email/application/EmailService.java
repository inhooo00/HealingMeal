package gsc.healingmeal.email.application;


import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    String sendEmail(String email) throws MessagingException, UnsupportedEncodingException;
}
