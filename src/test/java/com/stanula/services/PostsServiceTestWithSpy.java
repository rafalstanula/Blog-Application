package com.stanula.services;

import com.stanula.repositories.PostsRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsServiceTestWithSpy {

    @InjectMocks
    private PostsService postsService;

    @Spy
    private PostsRepository repositoryMock;
}
