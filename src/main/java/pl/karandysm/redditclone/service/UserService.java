package pl.karandysm.redditclone.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.exceptions.UserExistsWithEmailException;
import pl.karandysm.redditclone.exceptions.UserExistsWithUsernameException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User registerUser(UserDto userDto) throws RegisterException {
		String messageEnding = " is already taken";
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserExistsWithUsernameException("Username " + userDto.getUsername() + messageEnding);
		}
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserExistsWithEmailException("Email " + userDto.getEmail() + messageEnding);
		}
		
		return userRepository.save(this.createUserFromDto(userDto));
	}

	private User createUserFromDto(UserDto userDto) {
		return new User(userDto.getUsername(), userDto.getEmail(), Objects.hash(userDto.getPassword()));
	}

	/*
	 * public boolean existsByUsername(String username) { return
	 * userRepository.existsByUsername(username); }
	 * 
	 * public boolean existsByEmail(String email) { return
	 * userRepository.existsByEmail(email); }
	 */
}
