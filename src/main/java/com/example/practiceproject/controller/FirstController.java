package com.example.practiceproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")      // URL 요청 접수
    public String niceToMeetYou() {
        return "greetings";     // greetings.mustache 파일 반환
    }
}
