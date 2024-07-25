package com.thesis.sofen.controllers.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TestSecurity {

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }
}
