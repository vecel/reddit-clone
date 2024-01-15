package pl.karandysm.redditclone.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karandysm.redditclone.controller.UserController;
import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.DuplicateUsernameException;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.exceptions.UserExistsWithEmailException;
import pl.karandysm.redditclone.exceptions.UserExistsWithUsernameException;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.PostRepository;
import pl.karandysm.redditclone.repository.UserRepository;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User registerUser(UserDto userDto) throws RegisterException {
		String messageEnding = " is already taken";
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserExistsWithUsernameException("Username " + userDto.getUsername() + messageEnding);
		}
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserExistsWithEmailException("Email " + userDto.getEmail() + messageEnding);
		}

		User user = this.createUserFromDto(userDto);
		logger.info("Registered user: " + user);
		return userRepository.save(user);
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
			logger.info("Validated user: " + user);
			return Optional.of(user);
		}
		// Naruszam zasade DRY, bo nie mam ochoty teraz rozkminiac jak ja obejsc
		return Optional.empty();
	}
	
/*
 * Niedozwolone dopoki nie naprawie
 */
//	public void deleteUser(User user) {
//		List<Post> posts = postRepository.findAllByAuthor(user);
//		posts.stream().forEach(p -> p.setAuthor(null));
//		postRepository.flush();
//		userRepository.delete(user);
//	}
	
	private User createUserFromDto(UserDto userDto) {
		return new User(userDto.getUsername(), userDto.getEmail(), Objects.hash(userDto.getPassword()));
	}

}
