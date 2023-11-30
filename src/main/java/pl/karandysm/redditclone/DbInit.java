package pl.karandysm.redditclone;

import java.util.ArrayList;
import java.util.List;

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
		userRepository.saveAll(List.of(new User("testuser", "test.test@gmail.com", 2137),
				new User("admin", "admin.admin@gmail.com", 1)));
	}
	
	private void initCommunities() {
		communityRepository.saveAll(List.of(new Community("Koty", "To jest community o kotach"),
				new Community("Community z uzytkownikami", "Lorem ipsum dolor set amet",
						new ArrayList<Long>(List.of((long) 1, (long) 2, (long) 6)))));
	}
	
	private void initPosts() {
		postRepository.saveAll(List.of(new Post("Koty", "To jest post o kotach", (long) 1, (long) 1),
				new Post("Psy", "Psy sa lepsze haha", (long) 1, (long) 2)));
	}
	
	private void initComments() {
		commentRepository.saveAll(List.of(new Comment((long) 1, (long) 2, "Nie masz racji")));
	}

}
