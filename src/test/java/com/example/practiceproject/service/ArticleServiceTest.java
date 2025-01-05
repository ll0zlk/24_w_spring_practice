package com.example.practiceproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest     // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;      // 객체 주입
    @Test
    void index() {
    }
}