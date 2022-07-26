package com.example.sample2.controller;

import com.example.sample2.dto.ArticleForm;
import com.example.sample2.entity.Article;
import com.example.sample2.repository.ArticleRepository;
import com.example.sample2.service.ArticleService;
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

  @Autowired
  private ArticleService articleService;

  // GET
  @GetMapping("/api/articles")
  public List<Article> index() {
    // return articleRepository.findAll();
    return articleService.index();
  }

  @GetMapping("/api/articles/{id}")
  public Article show(@PathVariable Long id) {
    // return articleRepository.findById(id).orElse(null);
    return articleService.show(id);
  }

  // POST
  @PostMapping("/api/articles")
  public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
    // public Article create(@RequestBody ArticleForm dto) {
    // Article article = dto.toEntity();
    // return articleRepository.save(article);
    Article created = articleService.create(dto);
    return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created)
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  // PATCH
  @PatchMapping("/api/articles/{id}")
  public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
    // Article article = dto.toEntity();
    // log.info("id: {}, article: {}", id, article.toString());
    // Article target = articleRepository.findById(id).orElse(null);
    // if (target == null || id != article.getId()) {
    // // 400, 잘못된 요청 응답!
    // log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
    // target.patch(article);
    // Article updated = articleRepository.save(target);
    // return ResponseEntity.status(HttpStatus.OK).body(updated);
    Article updated = articleService.update(id, dto);
    return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated)
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  // DELETE
  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Article> delete(@PathVariable Long id) {
    // Article target = articleRepository.findById(id).orElse(null);
    // if (target == null) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
    // articleRepository.delete(target);
    // return ResponseEntity.status(HttpStatus.OK).build();
    Article deleted = articleService.delete(id);
    return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  // transactional test
  @PostMapping("/api/transaction-test")
  public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
    List<Article> createdList = articleService.createArticles(dtos);
    return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList)
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
