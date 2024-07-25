package com.thesis.sofen.services;


import com.thesis.sofen.dto.BookingInfoDto;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
@Service
@RequiredArgsConstructor
public class EmailService {
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String VALIDATE_EMAIL = "EmailOTPTemplate";
    public static final String BOOKING_SUCCESS_EMAIL = "EmailBookingSuccessTemplate";

    public static final String TEXT_HTML_ENCODING = "text/html";
    public static final String VALIDATE_PASSWORD_EN = "Validate your Account";

    @Value("http://localhost:8080")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final TemplateEngine templateEngine;
    private final JavaMailSender emailSender;

    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(VALIDATE_PASSWORD_EN);
            message.setFrom(fromEmail);
            message.setTo("thinhbo2999@gmail.com");
            message.setText("it word");
            emailSender.send(message);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    @Async
    public void sendValidateEmail(String to, String token){
        try{
            Context context = new Context();
            context.setVariables(Map.of("otp",token));
            String text = templateEngine.process(VALIDATE_EMAIL, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(VALIDATE_PASSWORD_EN);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);

            emailSender.send(message);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }
    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    @Async
    public void sendBookingSuccessEmail(String to, BookingInfoDto bookingInfoDto){
        try{
            Context context = new Context();
            context.setVariables(Map.of("hotelName", bookingInfoDto.getHotelName(),
                    "periodStay", bookingInfoDto.getPeriodStay(),
                    "subPrice", bookingInfoDto.getSubPrice(),
                    "tax", bookingInfoDto.getTax(),
                    "finalPrice", bookingInfoDto.getFinalPrice(),
                    "rooms", bookingInfoDto.getRooms()));
            String text = templateEngine.process(BOOKING_SUCCESS_EMAIL, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("Booking Success");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);

            emailSender.send(message);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }



}