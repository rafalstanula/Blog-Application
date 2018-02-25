package com.stanula.services;

import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostsServiceTest {

    private PostsRepository repositoryMock;
    private PostsService postsService;
    private Post redPost;
    private Post bluePost;
    private Post greenPost;

    @Before
    public void setUp() {
        repositoryMock = mock(PostsRepository.class);
        postsService = new PostsService(repositoryMock);
        redPost = Post.createPost("John", "My red post");
        bluePost = Post.createPost("John", "My blue post");
        greenPost = Post.createPost("Max", "My green post");
    }

    @Test
    public void shouldReturnThreePostsWhenGetPostsWithoutParametersIsCall() {
        List<Post> listOfPostsWithDifferentAuthors = Arrays.asList(redPost, bluePost, greenPost);

        when(repositoryMock.findAll()).thenReturn(listOfPostsWithDifferentAuthors);

        assertThat(postsService.getPosts()).containsOnly(redPost, bluePost, greenPost);
    }

    @Test
    public void shouldReturnOnePostWhenGetPostsWithoutParametersIsCall() {
        List<Post> listWithOnePost = Arrays.asList(redPost);

        when(repositoryMock.findAll()).thenReturn(listWithOnePost);

        assertThat(postsService.getPosts()).containsOnly(redPost);
    }

    @Test
    public void shouldReturnEmptyListWhenGetPostsWithoutParametersIsCall() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThat(postsService.getPosts()).isEmpty();
    }

    @Test
    public void shouldReturnTwoPostsListOfPostWhenGetPostsWithParameterIsCall() {
        List<Post> listOfPostsWithOneAuthor = Arrays.asList(redPost, bluePost);

        when(repositoryMock.findAllByAuthor("John")).thenReturn(listOfPostsWithOneAuthor);

        assertThat(postsService.getPosts("John")).contains(redPost, bluePost)
                .extracting("content")
                .contains("My red post", "My blue post");
    }

    @Test
    public void shouldReturnEmptyListOfPostWhenGetPostsWithParameterIsCall() {
        when(repositoryMock.findAllByAuthor("David")).thenReturn(Collections.emptyList());

        assertThat(postsService.getPosts("David")).isEmpty();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenInstanceOfPostsRepositoryIsNotCreated() {
        PostsRepository postsRepository = null;
        PostsService postsService = new PostsService(postsRepository);

        postsService.getPosts();
    }
}