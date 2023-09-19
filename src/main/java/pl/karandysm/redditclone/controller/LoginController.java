package pl.karandysm.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.karandysm.redditclone.model.User;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String displayForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String submitForm(@ModelAttribute("user") User user) {
		System.out.println(user);
		return "index";
	}
	
}
