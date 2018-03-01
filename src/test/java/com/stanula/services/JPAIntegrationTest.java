package com.stanula.services;

import com.stanula.BlogApplication;
import com.stanula.repositories.PostsRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BlogApplication.class)
public class JPAIntegrationTest {

    @Autowired
    private PostsRepository postsRepository;
}
