package com.example.practiceproject.service;

import com.example.practiceproject.dto.ArticleForm;
import com.example.practiceproject.entity.Article;
import com.example.practiceproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null만 반환
        }
        // 4. 업데이트하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;     // 응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null만 반환
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;      // DB에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음(리스트)을 엔티티 묶음(리스트)으로 변환하기
        List<Article> articleList = dtos.stream()   // dtos를 스트림화
                .map(dto -> dto.toEntity())     // map()으로 dto가 하나하나 올 때마다 엔티티로 변환 후 매핑
                .collect(Collectors.toList());  // 매핑한 것을 리스트로 묶음
        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 3. 강제로 에러를 발생시키기
        articleRepository.findById(-1L)     // id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        // 4. 결과 값 반환하기
        return articleList;
    }
}
