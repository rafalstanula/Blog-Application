package com.stanula.controller;

import com.stanula.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    private PostsService postsService;

    @Autowired
    public void setPostsService(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsService.getPosts());
        return "posts";
    }
    @GetMapping("/posts/{aythor}")
    public String getPostByAuthor(Model model, @PathVariable String author){
        model.addAttribute("posts", postsService.getPosts(author));
        return "posts";
    }

}
