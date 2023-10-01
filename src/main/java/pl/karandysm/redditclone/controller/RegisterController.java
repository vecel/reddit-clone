package pl.karandysm.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.UserRepository;

@Controller
public class RegisterController {
	
	private final UserRepository userRepository;
	
	public RegisterController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/register")
	public String displayForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("user") User user) {
//		System.out.println(user);
//		System.out.println(userRepository.findAll());
		return "index";
	}
}
