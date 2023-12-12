package pl.karandysm.redditclone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.UserService;

@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUpDatabase() {
		userRepository.deleteAll();
	}
	
	@Test
	public void registeredUserIsFoundByEmail() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		List<User> users = userRepository.findByEmail(email);
		
		// then
		assertThat(users.size()).isEqualTo(1);
		assertThat(users.get(0).getEmail()).isNotNull();
		assertThat(users.get(0).getEmail()).isEqualTo(email);
		assertThat(users.get(0).getUsername()).isEqualTo(username);
	}
	
	@Test
	public void registeredUserIsFoundByUsername() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		List<User> users = userRepository.findByUsername(username);
		
		// then
		assertThat(users.size()).isEqualTo(1);
		assertThat(users.get(0).getEmail()).isNotNull();
		assertThat(users.get(0).getEmail()).isEqualTo(email);
		assertThat(users.get(0).getUsername()).isEqualTo(username);
	}
	
	@Test
	public void registeredUserIsNotFoundByNotExistingEmail() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		List<User> users = userRepository.findByEmail("notindatabase");
		
		// then
		assertThat(users.size()).isEqualTo(0);
	}
	
	@Test
	public void registeredUserIsNotFoundByNotExistingUsername() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		List<User> users = userRepository.findByUsername("notindatabase");
		
		// then
		assertThat(users.size()).isEqualTo(0);
	}
	
	@Test
	public void registeredUserExistsByEmail() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		boolean exists = userRepository.existsByEmail(email);
		boolean notExists = userRepository.existsByEmail("notindatabase");
		
		// then
		assertThat(exists).isTrue();
		assertThat(notExists).isFalse();
	}
	
	@Test
	public void registeredUserExistsByUsername() throws RegisterException {
		// given
		String email = "tomek@gmail.com";
		String username = "tomek";
		UserDto userDto = new UserDto(username, email, "password123", "password123");
		userService.registerUser(userDto);
		
		// when
		boolean exists = userRepository.existsByUsername(username);
		boolean notExists = userRepository.existsByUsername("notindatabase");
		
		// then
		assertThat(exists).isTrue();
		assertThat(notExists).isFalse();
	}
}
