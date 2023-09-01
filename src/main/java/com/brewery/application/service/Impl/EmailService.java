package com.brewery.application.service.Impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
@Service
public class EmailService {

    private String globalOtp;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender  = javaMailSender;
    }

    public String sendEmail(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Email Verification");
        message.setText("The Otp is : "+" "+generateOtp(6));
        message.setTo(to);
        javaMailSender.send(message);
        return "email send successfully";
    }

    public String resetPassword(String email){
        try {
            String s = FirebaseAuth.getInstance().generatePasswordResetLink(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Reset Password");
            message.setText(s);
            message.setTo(email);
            javaMailSender.send(message);
            return "Password reset email sent.";
        }
        catch (FirebaseAuthException e) {
            return "Failed to send password reset email.";
        }
    }

    public String generateOtp(int length){

        Random random = new Random();

        StringBuilder otp = new StringBuilder();

        for(int i=0;i<length;i++){
            otp.append(random.nextInt(10));
        }

        globalOtp = otp.toString();

        System.out.println(globalOtp);

        return otp.toString();
    }
    public boolean verifyOtp(String otp){
        System.out.println(globalOtp+" "+otp);
        boolean var = globalOtp.equals(otp);
        if(var){
            globalOtp = "";
        }
        return var;
    }

}
