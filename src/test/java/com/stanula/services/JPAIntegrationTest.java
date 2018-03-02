package com.stanula.services;

import com.stanula.BlogApplication;
import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BlogApplication.class)
public class JPAIntegrationTest {

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void givenOnePost_whenSaveAndRetrievePost_thenRetrievedPostIsNotNull() {
        Post redPost = Post.createPost("John", "My red post");

        Post savedPost = postsRepository.save(redPost);
        Post foundPost = postsRepository.findOne(savedPost.getId());

        assertNotNull(foundPost);
    }
}