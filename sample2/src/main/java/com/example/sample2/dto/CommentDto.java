package com.example.sample2.dto;

import com.example.sample2.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
  private Long id;

  @JsonProperty("article_id")
  private Long articleId;
  private String nickname;
  private String body;

  public static CommentDto createCommentDto(Comment comment) {
    return new CommentDto(comment.getId(), comment.getArticle().getId(), comment.getNickname(),
        comment.getBody());
  }
}

