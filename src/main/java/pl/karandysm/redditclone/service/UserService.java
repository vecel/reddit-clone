package pl.karandysm.redditclone.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.DuplicateUsernameException;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.exceptions.UserExistsWithEmailException;
import pl.karandysm.redditclone.exceptions.UserExistsWithUsernameException;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

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

	public Optional<User> validateUser(String username, String password) throws DuplicateUsernameException {
		List<User> users = userRepository.findByUsername(username);
		if (users.size() > 1) {
			throw new DuplicateUsernameException(username);
		}
		if (users.isEmpty()) {
			return Optional.empty();
		}
		User user = users.get(0);
		if (user.getPasswordHash() == Objects.hash(password)) {
			return Optional.of(user);
		}
		// Naruszam zasade DRY, bo nie mam ochoty teraz rozkminiac jak ja obejsc
		return Optional.empty();
	}
	
	public List<User> getPostAuthors(List<Post> posts) {
		List<Long> ids = posts.stream().map(p -> p.getAuthorId()).collect(Collectors.toList());
		return userRepository.findByIdIn(ids);
	}
	
	private User createUserFromDto(UserDto userDto) {
		return new User(userDto.getUsername(), userDto.getEmail(), Objects.hash(userDto.getPassword()));
	}

}
