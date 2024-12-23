package com.example.practiceproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")      // URL 요청 접수
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "효진");   // 변수 등록 시 addAttribute 메서드 사용
        return "greetings";     // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "효진");
        return "goodbye";
    }
}