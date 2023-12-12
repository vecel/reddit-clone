package pl.karandysm.redditclone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

@SpringBootTest
class UserServiceTests {
	
	@Autowired
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;

	@Test
	void registeredUserHasProperFieldValues() throws RegisterException {
		UserDto userDto = new UserDto("newUser", "newuser@example.com", "password123", "password123");
        when(userRepository.existsByUsername(userDto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);

        User registeredUser = userService.registerUser(userDto);

        assertThat(registeredUser).isNotNull();
        assertThat(registeredUser.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(registeredUser.getEmail()).isEqualTo(userDto.getEmail());
	}

}
