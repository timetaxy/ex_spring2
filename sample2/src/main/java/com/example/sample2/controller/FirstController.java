package com.example.sample2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
  /**
   * @return
   */
  @GetMapping("/")
  public String greet(Model model) {
    model.addAttribute("username", "metaxy");
    return "greetings";// mustache file name
  }

  @GetMapping("/api/hello")
  public String hello() {
    return "hello world!";
  }
}
