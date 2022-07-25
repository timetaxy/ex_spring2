package com.example.sample2.controller;

import com.example.sample2.dto.ArticleForm;
import com.example.sample2.entity.Article;
import com.example.sample2.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RestController
public class ArticleApiController {
  @Autowired
  private ArticleRepository articleRepository;

  // GET
  @GetMapping("/api/articles")
  public List<Article> index() {
    return articleRepository.findAll();
  }

  @GetMapping("/api/articles/{id}")
  public Article show(@PathVariable Long id) {
    return articleRepository.findById(id).orElse(null);
  }

  // POST
  @PostMapping("/api/articles")
  public Article create(@RequestBody ArticleForm dto) {
    Article article = dto.toEntity();
    return articleRepository.save(article);
  }

  // PATCH
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
    Article article = dto.toEntity();
    log.info("id: {}, article: {}", id, article.toString());
    Article target = articleRepository.findById(id).orElse(null);
    if (target == null || id != article.getId()) {
      // 400, 잘못된 요청 응답!
      log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    target.patch(article);
    Article updated = articleRepository.save(target);
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

  // DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> delete(@PathVariable Long id) {
    Article target = articleRepository.findById(id).orElse(null);
    if (target == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    articleRepository.delete(target);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
