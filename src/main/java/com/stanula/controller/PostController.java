package com.stanula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping ("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts");
        return "posts";
    }

}
