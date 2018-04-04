import com.stanula.BlogApplication;
import com.stanula.domain.Post;
import com.stanula.repositories.PostsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BlogApplication.class)
public class JPAIntegrationTest {

	@Autowired
	private PostsRepository postsRepository;

	private Post redPost;
	private Post bluePost;
	private Post greenPost;
	private List<Post> listOfPosts;

	@Before
	public void setUp() {
		redPost = Post.createPost("John", "My red post");
		bluePost = Post.createPost("John", "My blue post");
		greenPost = Post.createPost("Max", "My green post");
		listOfPosts = Arrays.asList(redPost, bluePost, greenPost);
		postsRepository.deleteAll();
	}

	@Test
	public void givenOnePost_whenSaveAndRetrievePost_thenRetrievedPostIsNotNull() {
		Post savedPost = postsRepository.save(redPost);
		Post foundPost = postsRepository.findOne(savedPost.getId());

		assertNotNull(foundPost);
	}

	@Test
	public void givenOnePost_whenSaveAndRetrieveOnePost_thenSavedAndRetrievedHaveEqualContent() {
		Post savedPost = postsRepository.save(redPost);
		Post foundPost = postsRepository.findOne(savedPost.getId());

		assertThat(savedPost.getContent()).isEqualTo(foundPost.getContent());
	}

	@Test
	public void givenListOfThreePosts_whenSaveAndRetrieveAllPost_thenAllReturnedContentsAreEqual() {
		postsRepository.save(listOfPosts);
		List<Post> foundListOfPosts = postsRepository.findAll();

		assertThat(foundListOfPosts.get(0).getContent()).isEqualTo(listOfPosts.get(0).getContent());
		assertThat(foundListOfPosts.get(1).getContent()).isEqualTo(listOfPosts.get(1).getContent());
		assertThat(foundListOfPosts.get(2).getContent()).isEqualTo(listOfPosts.get(2).getContent());
	}

	@Test
	public void givenListOfThreePosts_whenPostsAreSaveAndPostsFromOneAuthorAreRequested_thenTwoPostsAreReturned() {
		postsRepository.save(listOfPosts);
		List<Post> foundListOfPosts = postsRepository.findAllByAuthor("John");

		assertThat(foundListOfPosts).hasSize(2);
	}

	@Test
	public void givenListOfThreePosts_whenPostsAreSaveAndPostsFromOneAuthorAreRequested_thenDoNotReturnedAnyPosts() {
		postsRepository.save(listOfPosts);
		List<Post> foundListOfPosts = postsRepository.findAllByAuthor("David");

		assertThat(foundListOfPosts).isEmpty();
	}
}
