package com.stanula.controller;

import com.stanula.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String listPosts(Model model){
        List<Post> postsList = new ArrayList<Post>();
        postsList.add(Post.createInstance("Post1"));
        postsList.add(Post.createInstance("Post2"));
        model.addAttribute("posts", postsList);
        return "posts";
    }

}
