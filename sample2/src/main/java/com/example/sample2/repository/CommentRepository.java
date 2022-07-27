package com.example.sample2.repository;

import com.example.sample2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT * " + "FROM comment " + "WHERE article_id = :articleId",
      nativeQuery = true)
  List<Comment> findByArticleId(Long articleId);
  // articleId를 찾지 못해 에러 발생 시, @Param 어노테이션으로 파라미터 정보 추가
  // List<Comment> findByArticleId(@Param("articleId") Long articleId);

  List<Comment> findByNickname(String nickname);
}
