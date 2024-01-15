package pl.karandysm.redditclone.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.constants.ModelConstants;
import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.DuplicateUsernameException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> submitForm(@RequestBody UserDto userDto,
										HttpSession session) {

		logger.info("login POST request with userDto: " + userDto);

		Map<String, String> error = new HashMap<>();
		error.put("error", "User not found");
		
		try {
			Optional<User> user = userService.validateUser(userDto.getUsername(), userDto.getPassword());

			if (user.isPresent()) {
				session.setAttribute(HttpSessionConstants.USER, user.get());
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
		} catch (DuplicateUsernameException e) {
			e.printStackTrace();
			logger.error("There is duplicated username " + userDto.getUsername() + " in database");
		}


		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
