package com.example.sample2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.sample2.dto.ArticleForm;
import com.example.sample2.entity.Article;

@SpringBootTest
public class ArticleServiceTest {
  @Autowired
  ArticleService articleService;

  @Test
  @Transactional
  void testCreateSuccess() {
    // 예상
    String title = "라라라라";
    String content = "4444";
    ArticleForm dto = new ArticleForm(null, title, content);
    Article expected = new Article(4L, title, content);
    // 실제
    Article article = articleService.create(dto);
    // 비교
    assertEquals(expected.toString(), article.toString());
  }

  @Test
  @Transactional
  void testCreateFail() {
    // 예상
    String title = "라라라라";
    String content = "4444";
    ArticleForm dto = new ArticleForm(4L, title, content);
    Article expected = null;
    // 실제
    Article article = articleService.create(dto);
    // 비교
    assertEquals(expected, article);

  }

  @Test
  void testCreateArticles() {

  }

  @Test
  void testDelete() {

  }

  @Test
  void testIndex() {
    // 예상
    Article a = new Article(1L, "가가가가", "1111");
    Article b = new Article(2L, "나나나나", "2222");
    Article c = new Article(3L, "다다다다", "3333");
    List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
    // 실제
    List<Article> articles = articleService.index();
    // 검증
    assertEquals(expected.toString(), articles.toString());
  }

  @Test
  void testShowSuccess() {
    // 예상
    Long id = 1L;
    Article expected = new Article(id, "가가가가", "1111");
    // 실제
    Article article = articleService.show(id);
    // 비교
    assertEquals(expected.toString(), article.toString());
  }

  @Test
  void testShowFail() {
    // 예상
    Long id = 1L;
    Article expected = new Article(id, "가가가가", "1111");
    // 실제
    Article article = articleService.show(id);
    // 비교
    assertEquals(expected.toString(), article.toString());
  }

  @Test
  void testUpdate() {

  }
}
