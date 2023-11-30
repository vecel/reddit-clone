package pl.karandysm.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
public class RegisterController {

	private final UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String displayForm(Model model) {
		model.addAttribute(ModelConstants.USER_DTO, new UserDto());
		return "register";
	}

	@PostMapping("/register")
	public String submitForm(@Valid @ModelAttribute(ModelConstants.USER_DTO) UserDto userDto, BindingResult bindingResult,
			HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "register";
		}

		try {
			User user = userService.registerUser(userDto);
			session.setAttribute(HttpSessionConstants.USER, user);
		} catch (UserExistsWithUsernameException e) {
			bindingResult.rejectValue("username", "usernameTaken", e.getMessage());
			return "register";
		} catch (UserExistsWithEmailException e) {
			bindingResult.rejectValue("email", "emailTaken", e.getMessage());
			return "register";
		} catch (RegisterException e) {

		}

		return "redirect:/";
	}
}
