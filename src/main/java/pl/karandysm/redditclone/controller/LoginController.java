package pl.karandysm.redditclone.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pl.karandysm.redditclone.dto.UserDto;
import pl.karandysm.redditclone.exceptions.DuplicateUsernameException;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.UserService;

@Controller
public class LoginController {

	private UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String displayForm(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "login";
	}
	
	@PostMapping("/login")
	public String submitForm(@ModelAttribute("userDto") UserDto userDto,
			HttpSession session) {
		
		try {
			Optional<User> oUser = userService.validateUser(userDto.getUsername(), userDto.getPassword());
			if (oUser.isPresent()) {
				session.setAttribute("user", oUser.get());
				return "redirect:/";
			}
			System.out.println("Nie znaleziono usera w bazie");
		} catch (DuplicateUsernameException e) {
			e.printStackTrace();
			System.err.println("Application should stop here, there is a problem with duplicate usernames in databse");
		}
		
		return "login";
	}
	
}
