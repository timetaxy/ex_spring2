package com.example.sample2.controller;

import org.springframework.data.repository.CrudRepository;
import com.example.sample2.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
