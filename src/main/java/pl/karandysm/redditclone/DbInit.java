package pl.karandysm.redditclone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		initUsers();
		initCommunities();
		initPosts();
		initComments();
	}
	
	private void initUsers() {
		userRepository.saveAll(Arrays.asList(new User("testuser", "test.test@gmail.com", Objects.hash("2137")),
				new User("admin", "admin.admin@gmail.com", Objects.hash("1"))));
	}
	
	private void initCommunities() {
		communityRepository.saveAll(Arrays.asList(new Community("Koty", "To jest community o kotach"),
				new Community("Community z uzytkownikami", "Lorem ipsum dolor set amet",
						new ArrayList<Long>(List.of((long) 1, (long) 2, (long) 6)),
						new ArrayList<Long>(List.of((long) 2, (long) 1, (long) 3, (long) 7))),
				new Community("Psy", "Tu sa psy")));
	}
	
	private void initPosts() {
		postRepository.saveAll(Arrays.asList(new Post("Koty", "To jest post o kotach", (long) 1, (long) 1),
				new Post("Psy", "Psy sa lepsze haha", (long) 1, (long) 2)));
	}
	
	private void initComments() {
		commentRepository.saveAll(Arrays.asList(new Comment((long) 1, (long) 2, "Nie masz racji")));
	}

}
