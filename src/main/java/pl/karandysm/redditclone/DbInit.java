package pl.karandysm.redditclone;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

@Configuration
public class DbInit implements CommandLineRunner {

	private final UserRepository userRepository;
	
	public DbInit(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		userRepository.saveAll(List.of(
			new User("test.test@gmail.com", "testuser", "Testuserpass1"),
			new User("admin.admin@gmail.com", "admin", "Adminadmin0")
		));
	}
	 
}
