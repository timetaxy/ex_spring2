package com.example.sample2.controller;

import com.example.sample2.dto.ArticleForm;
import com.example.sample2.dto.CommentDto;
import com.example.sample2.entity.Article;
import com.example.sample2.repository.ArticleRepository;
import com.example.sample2.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private CommentService commentService;

  @GetMapping("/articles/new")
  public String newArticleForm() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String createArticle(ArticleForm form) {
    // System.out.println(form.toString());
    log.info(form.toString());
    Article article = form.toEntity();
    // System.out.println(article.toString());
    log.info(article.toString());
    Article saved = articleRepository.save(article);
    // System.out.println(saved.toString());
    log.info(saved.toString());
    return "redirect:/articles/" + saved.getId();
  }

  @GetMapping("/articles/{id}")
  public String show(@PathVariable Long id, Model model) {
    log.info("id = " + id);
    // Optional<Article> articleEntity = articleRepository.findById(id);
    // Article articleEntity = articleRepository.findById(id).orElse(null);

    Article articleEntity = articleRepository.findById(id).orElse(null);
    List<CommentDto> commentsDtos = commentService.comments(id);
    model.addAttribute("commentDtos", commentsDtos);
    return "articles/show";
  }

  @GetMapping("/articles")
  public String index(Model model) {
    // Iterable<Article> articleEntityList = articleRepository.findAll();
    // 리턴타입 Iterable
    List<Article> articleEntityList = articleRepository.findAll();
    // 2: 가져온 Article 묶음을 뷰로 전달!
    model.addAttribute("articleList", articleEntityList);
    // 3: 뷰 페이지를 설정!
    return "articles/index";
  }

  @GetMapping("/articles/{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    Article articleEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("article", articleEntity);
    return "articles/edit";
  }

  @PostMapping("/articles/update")
  public String update(ArticleForm form) {
    log.info(form.toString());
    Article articleEntity = form.toEntity();
    log.info(articleEntity.toString());
    Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
    if (target != null) {
      articleRepository.save(articleEntity);
    }
    return "redirect:/articles/" + articleEntity.getId();
  }

  @GetMapping("/articles/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes rttr) {
    log.info("삭제 요청이 들어왔습니다!!");
    Article target = articleRepository.findById(id).orElse(null);
    log.info(target.toString());
    if (target != null) {
      articleRepository.delete(target);
      rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
    }
    return "redirect:/articles";
  }
}
