package com.stanula.services;

import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostsServiceTestWithSpring {

	@InjectMocks
	private PostsService postsService;

	@Mock
	private PostsRepository repositoryMock;

	private Post redPost;
	private Post bluePost;
	private Post greenPost;

	@Before
	public void setUp() {
		redPost = Post.createPost("John", "My red post");
		bluePost = Post.createPost("John", "My blue post");
		greenPost = Post.createPost("Max", "My green post");
	}

	@Test
	public void givenListOfThreePostsFromDifferentAuthors_whenAllPostsAreRequested_thenPostsFromAllAuthorsAreReturned() {
		List<Post> listOfPostsWithDifferentAuthors = Arrays.asList(redPost, bluePost, greenPost);

		when(repositoryMock.findAll()).thenReturn(listOfPostsWithDifferentAuthors);

		assertThat(postsService.getPosts()).containsOnly(redPost, bluePost, greenPost);
	}

	@Test
	public void givenListOfOnePost_whenAllPostsAreRequested_thenOnePostAreReturned() {
		List<Post> listWithOnePost = Arrays.asList(redPost);

		when(repositoryMock.findAll()).thenReturn(listWithOnePost);

		assertThat(postsService.getPosts()).containsOnly(redPost);
	}

	@Test
	public void givenEmptyList_whenAllPostsAreRequested_thenEmptyListAreReturned() {
		when(repositoryMock.findAll()).thenReturn(Collections.emptyList());

		assertThat(postsService.getPosts()).isEmpty();
	}

	@Test
	public void givenListOfTwoPostsFromOneAuthor_whenAllPostsFromOneAreRequested_thenAllPostsFromOneAuthorAreReturned() {
		List<Post> listOfPostsWithOneAuthor = Arrays.asList(redPost, bluePost);

		when(repositoryMock.findAllByAuthor("John")).thenReturn(listOfPostsWithOneAuthor);

		assertThat(postsService.getPosts("John")).contains(redPost, bluePost)
				.extracting("content")
				.contains("My red post", "My blue post");
	}

	@Test
	public void givenEmptyList_whenAllPostsFromOneAreRequested_thenEmptyListAreReturned() {
		when(repositoryMock.findAllByAuthor("David")).thenReturn(Collections.emptyList());

		assertThat(postsService.getPosts("David")).isEmpty();
	}

	@Test(expected = NullPointerException.class)
	public void givenBadlyInitializedPostsRepository_whenAllPostsAreRequested_thenNullPointerExceptionIsThrown() {
		PostsRepository postsRepository = null;
		PostsService postsService = new PostsService(postsRepository);

		postsService.getPosts();
	}

	@Test(expected = NullPointerException.class)
	public void givenBadlyInitializedPostsRepository_whenAddPostAreRequested_thenNullPointerExceptionIsThrown() {
		PostsRepository postsRepository = null;
		PostsService postsService = new PostsService(postsRepository);

		postsService.addPost(redPost);
	}

	@Test
	public void givenPostsRepositoryMock_whenAddPostInServiceIsCall_thenAddPostMethodInRepositoryIsInvocatedOnce() {
		postsService.addPost(redPost);

		verify(repositoryMock, times(1)).save(redPost);
	}
}
