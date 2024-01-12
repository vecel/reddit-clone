package pl.karandysm.redditclone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/api")
@CrossOrigin
public class RegisterController {

	private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	private final UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String displayForm(Model model) {
		model.addAttribute(ModelConstants.USER_DTO, new UserDto());
		return "register";
	}

	@PostMapping(value = "/register", consumes = "application/json")
	public ResponseEntity<?> submitForm(@RequestBody @Valid UserDto userDto) {
		logger.info("Request to create user from: " + userDto);

		return ResponseEntity.ok().body(userDto);
//
//		if (bindingResult.hasErrors()) {
//			return "register";
//		}
//
//		try {
//			User user = userService.registerUser(userDto);
//			session.setAttribute(HttpSessionConstants.USER, user);
//		} catch (UserExistsWithUsernameException e) {
//			bindingResult.rejectValue("username", "usernameTaken", e.getMessage());
//			return "register";
//		} catch (UserExistsWithEmailException e) {
//			bindingResult.rejectValue("email", "emailTaken", e.getMessage());
//			return "register";
//		} catch (RegisterException e) {
//
//		}
//
//		return "redirect:/";
	}
}
