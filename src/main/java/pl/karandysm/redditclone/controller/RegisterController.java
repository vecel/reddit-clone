package pl.karandysm.redditclone.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.constants.ModelConstants;
import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.RegisterException;
import pl.karandysm.redditclone.exceptions.UserExistsWithEmailException;
import pl.karandysm.redditclone.exceptions.UserExistsWithUsernameException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {

	private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	private final UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> submitForm(@RequestBody @Valid UserDto userDto, HttpSession session) {
		logger.info("Request to create user from: " + userDto);

		// That looks like terrible approach of returning json body
		Map<String, Object> body = new HashMap<>();
		List<Map<String, String>> errors = new ArrayList<>();
		Map<String, String> error = new HashMap<>();

		try {
			User user = userService.registerUser(userDto);
			session.setAttribute(HttpSessionConstants.USER, user);
			logger.info("Session attribute user is: " + session.getAttribute(HttpSessionConstants.USER));
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (UserExistsWithUsernameException e) {
			error.put("field", "username");
			error.put("defaultMessage", e.getMessage());
		} catch (UserExistsWithEmailException e) {
			error.put("field", "email");
			error.put("defaultMessage", e.getMessage());
		} catch (RegisterException e) {
			logger.error(e.getMessage());
		}
		errors.add(error);
		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
