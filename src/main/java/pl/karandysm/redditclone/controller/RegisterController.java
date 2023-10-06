package pl.karandysm.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
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
	public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		System.out.println(user);
		if(bindingResult.hasErrors()) {
			System.out.println("dupa");
			return "register";
		}
		
		// todo dodac do bazy danych
		return "redirect:/";
	}
}
