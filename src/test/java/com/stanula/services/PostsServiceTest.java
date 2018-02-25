package com.stanula.services;

import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostsServiceTest {

    private PostsRepository repositoryMock;
    private PostsService postsService;
    private List<Post> listOfPostsWithDifferentAuthors;
    private List<Post> listWithOnePost;
    private List<Post> listOfPostsWithOneAuthor;
    private List<Post> emptyList;
    private Post redPost;
    private Post bluePost;
    private Post greenPost;

    @Before
    public void setUp() {
        repositoryMock = mock(PostsRepository.class);
        postsService = new PostsService(repositoryMock);
        listOfPostsWithDifferentAuthors = new ArrayList<>();
        listWithOnePost = new ArrayList<>();
        listOfPostsWithOneAuthor = new ArrayList<>();
        emptyList = new ArrayList<>();

        redPost = Post.createPost("John", "My red post");
        bluePost = Post.createPost("John", "My blue post");
        greenPost = Post.createPost("Max", "My green post");
        listOfPostsWithDifferentAuthors = Arrays.asList(redPost, bluePost, greenPost);
        listOfPostsWithOneAuthor = Arrays.asList(redPost, bluePost);
        listWithOnePost = Arrays.asList(redPost);
    }

    @Test
    public void shouldReturnListOfPostsWhenGetPostsWithoutParametersIsCall() {
        when(repositoryMock.findAll()).thenReturn(listOfPostsWithDifferentAuthors)
                .thenReturn(listWithOnePost)
                .thenReturn(emptyList);

        assertThat(postsService.getPosts()).containsOnly(redPost, bluePost, greenPost);
        assertThat(postsService.getPosts()).containsOnly(redPost);
        assertThat(postsService.getPosts()).isEmpty();
    }

    @Test
    public void shouldReturnListOfPostWhenGetPostsWithParameterIsCall() {
        when(repositoryMock.findAllByAuthor("John")).thenReturn(listOfPostsWithOneAuthor);
        when(repositoryMock.findAllByAuthor("David")).thenReturn(emptyList);

        assertThat(postsService.getPosts("John")).contains(redPost, bluePost)
                .extracting("content")
                .contains("My red post", "My blue post");
        assertThat(postsService.getPosts("David")).isEmpty();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenInstanceOfPostsRepositoryIsNotCreated() {
        PostsRepository postsRepository = null;
        PostsService postsService = new PostsService(postsRepository);

        postsService.getPosts();
        postsService.getPosts("John");
    }
}