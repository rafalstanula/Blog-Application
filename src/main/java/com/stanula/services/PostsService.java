package com.stanula.services;

import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Post> getPosts() {
        return postsRepository.findAll();
    }

    public List<Post> getPosts(String author) {
        return postsRepository.findAllByAuthor(author);
    }

    @Transactional
    public void addPost(Post post) {
        postsRepository.save(post);
    }
}
