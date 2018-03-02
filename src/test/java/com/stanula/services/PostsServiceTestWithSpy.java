package com.stanula.services;

import com.stanula.repositories.PostsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsServiceTestWithSpy {

    @InjectMocks
    private PostsService postsService;

    @Spy
    private PostsRepository repositorySpy;

    @Test
    public void givenPostsRepositorySpy_whenGetPostsMethodIsCall_thenFindAllMethodIsInvocatedOnce() {
        postsService.getPosts();

        verify(repositorySpy, times(1)).findAll();
    }

    @Test
    public void givenPostsRepositorySpy_whenGetPostsMethodWithParameterIsCall_thenFindAllByAuthorMethodIsInvocatedOnce() {
        postsService.getPosts("John");

        verify(repositorySpy, times(1)).findAllByAuthor("John");
    }
}
