package pl.karandysm.redditclone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.repository.CommunityRepository;

@Controller
public class IndexController {

	private CommunityRepository communityRepository;

	public IndexController(CommunityRepository communityRepository) {
		super();
		this.communityRepository = communityRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
//		communityRepository.findAll().stream().forEach(t -> System.out.println(t.getCommunityName()));
		List<Community> communities = communityRepository.findAll();
		model.addAttribute("communitiesList", communities);
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionConstants.USER);
		return "redirect:/";
	}

}
