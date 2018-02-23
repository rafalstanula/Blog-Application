package com.stanula.services;

import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PostsServiceTest {

    @Test
    public void shouldReturnListOfPostsWhenGetPostsWithoutParametersIsCall(){
        List<Post> listOfPosts = prepareListOfPosts();
        PostsRepository repositoryMock = mock(PostsRepository.class);
        PostsService postsService = new PostsService(repositoryMock);

        when(repositoryMock.findAll()).thenReturn(listOfPosts);

        assertThat(postsService.getPosts().get(2).getAuthor()).isEqualTo("John");
        assertThat(postsService.getPosts().get(1).getContent()).isEqualTo("SecondComment");
        assertThat(postsService.getPosts().size()).isEqualTo(3);
    }

    @Test
    public void shouldReturnListOfPostWhenGetPostsWithParameterIsCall(){
        List<Post> listOfPosts = prepareListOfPosts();
        PostsRepository repositoryMock = mock(PostsRepository.class);
        PostsService postsService = new PostsService(repositoryMock);

        when(repositoryMock.findAllByAuthor("John")).thenReturn(listOfPosts);

        assertThat(postsService.getPosts("John").get(0).getContent()).isEqualTo("FirstComment");
        assertThat(postsService.getPosts("John").get(1).getContent()).isEqualTo("SecondComment");
        assertThat(postsService.getPosts("John").get(2).getContent()).isEqualTo("ThirdComment");
        assertThat(postsService.getPosts("John").size()).isEqualTo(3);
    }

    private List<Post> prepareListOfPosts(){
        List<Post> listOfPosts = new ArrayList<>();
        listOfPosts.add(Post.createPost("John", "FirstComment"));
        listOfPosts.add(Post.createPost("John", "SecondComment"));
        listOfPosts.add(Post.createPost("John", "ThirdComment"));

        return listOfPosts;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenInstanceOfPostsRepositoryIsNotCreated(){
        PostsRepository postsRepository = null;
        PostsService postsService = new PostsService(postsRepository);

        postsService.getPosts();
    }
}