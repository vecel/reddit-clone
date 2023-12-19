package pl.karandysm.redditclone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;

@SpringBootTest
public class PostRepositoryTests {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUpRepositories() {
		postRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void findAllByAuthorWorks() {
		// given
		User user = new User("testuser", "testemail", -1);
		Post post1 = new Post("title1", "content2", null, user);
		Post post2 = new Post("title2", "content2", null, user);
		Set<Post> posts = new HashSet<>();
		posts.add(post1);
		posts.add(post2);
		user.setPosts(posts);
		userRepository.save(user);
		postRepository.save(post1);
		postRepository.save(post2);
		
		// when
		List<Post> tested = postRepository.findAllByAuthor(user);
		
		// then
		assertThat(tested.size()).isEqualTo(2);
		assertThat(tested.contains(post1)).isTrue();
		assertThat(tested.contains(post2)).isTrue();
	}
	
	@Test
	public void emptyListIsReturnedWhenUserHasNoPosts() {
		// given
		User user = new User("testuser", "testemail", -1);
		userRepository.save(user);
		
		// when
		List<Post> tested = postRepository.findAllByAuthor(user);
		
		// then
		assertThat(tested).isEmpty();
	}
	
}
