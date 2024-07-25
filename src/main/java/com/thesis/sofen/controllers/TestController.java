package com.thesis.sofen.controllers;

import com.thesis.sofen.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final EmailService emailService;

    @GetMapping("/send-email")
    public void sendEmail() {
        emailService.sendSimpleMailMessage("name", "to", "token");
    }
}
