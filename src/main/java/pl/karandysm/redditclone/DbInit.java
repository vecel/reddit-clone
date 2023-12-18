package pl.karandysm.redditclone;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import pl.karandysm.redditclone.model.Comment;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.CommentRepository;
import pl.karandysm.redditclone.repository.CommunityRepository;
import pl.karandysm.redditclone.repository.PostRepository;
import pl.karandysm.redditclone.repository.UserRepository;

@Configuration
public class DbInit implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CommunityRepository communityRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public DbInit(UserRepository userRepository, CommunityRepository communityRepository, PostRepository postRepository,
			CommentRepository commentRepository) {
		super();
		this.userRepository = userRepository;
		this.communityRepository = communityRepository;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("testuser", "test.test@gmail.com", Objects.hash("2137"));
		User user2 = new User("admin", "admin.admin@gmail.com", Objects.hash("1"));
		User user3 = new User("mateusz", "mateusz@gmail.com", Objects.hash("mateusz"));

		Community community1 = new Community("Koty", "To jest community o kotach");
		Community community2 = new Community("Psy", "To jest community o psach");
		
		Post post1 = new Post("Post koty 1", "To jest post o kotach", community1, user1);
		Post post2 = new Post("Post koty 2", "Psy sa lepsze", community1, user2);
		Post post3 = new Post("Post koty 3", "Nie sa", community1, user3);
		Post post4 = new Post("Post psy 1", "To jest post o psach", community2, user3);
		Post post5 = new Post("Post psy 2", "Psy sa lepsze haha", community2, user1);

		user1.setCommunities(new HashSet<>(Arrays.asList(community1, community2)));
		user2.setCommunities(new HashSet<>(Arrays.asList(community1)));
		user3.setCommunities(new HashSet<>(Arrays.asList(community1, community2)));
		
		user1.setPosts(new HashSet<>(Arrays.asList(post1, post5)));
		user2.setPosts(new HashSet<>(Arrays.asList(post2)));
		user3.setPosts(new HashSet<>(Arrays.asList(post3, post4)));
		
		community1.setMembers(new HashSet<>(Arrays.asList(user1, user2, user3)));
		community2.setMembers(new HashSet<>(Arrays.asList(user1, user3)));
		
		community1.setPosts(new HashSet<>(Arrays.asList(post1, post2, post3)));
		community2.setPosts(new HashSet<>(Arrays.asList(post4, post5)));
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		communityRepository.saveAll(Arrays.asList(community1, community2));
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
	}

}
