package pl.karandysm.redditclone.service;

import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

public class UserService {

	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User registerUser(UserDto userDto) {
		return null;
	}
}
