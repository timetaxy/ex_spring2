package com.example.sample2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String title;
  @Column
  private String content;

  // public Article(Long id, String title, String content) {
  // this.id = id;
  // this.title = title;
  // this.content = content;
  // }

  public Article toEntity() {
    return new Article(null, title, content);
  }

  // @Override
  // public String toString() {
  // return "Article{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\''
  // + '}';
  // }
}
