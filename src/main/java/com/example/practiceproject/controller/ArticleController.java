package com.example.practiceproject.controller;

import com.example.practiceproject.dto.ArticleForm;
import com.example.practiceproject.entity.Article;
import com.example.practiceproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j  // 로깅 기능을 위한 어노테이션 (Simple Logging Facade for Java)
@Controller
public class ArticleController {
    @Autowired      // 의존성 주입 (DI)
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString());
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());     // 실제 서버에서 println()문으로 데이터 검증 시 기록에도 안 남고 서버 성능에도 악영향

        // 2. 레포지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "";
    }
}
