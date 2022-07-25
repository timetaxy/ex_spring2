package com.example.sample2.dto;

import com.example.sample2.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
  private Long id;
  private String title;
  private String content;

  // public ArticleForm(String title, String content) {
  // this.title = title;
  // this.content = content;
  // }

  public Article toEntity() {
    return new Article(id, title, content);
  }

  // public String getTitle() {
  // return this.title;
  // }

  // public void setTitle(String title) {
  // this.title = title;
  // }

  // public String getContent() {
  // return this.content;
  // }

  // public void setContent(String content) {
  // this.content = content;
  // }

  // @Override
  // public String toString() {
  // return "{" + " title='" + getTitle() + "'" + ", content='" + getContent() + "'" + "}";
  // }

}
