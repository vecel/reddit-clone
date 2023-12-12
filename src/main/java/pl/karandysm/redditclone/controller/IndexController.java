package pl.karandysm.redditclone.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.service.CommunityService;

@Controller
public class IndexController {

	@Autowired
	private CommunityService communityService;

	@GetMapping("/")
	public String index(Model model) {
		addCommunitiesListToModel(model);
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionConstants.USER);
		return "redirect:/";
	}
	
	@GetMapping("/community")
	public String community(@RequestParam("name") String name, Model model) {
		addCommunitiesListToModel(model);
		Optional<Community> oCommunity = communityService.getCommunityByName(name);
		if (oCommunity.isEmpty()) {
			// placeholder
			return "redirect:/error";
		}
		model.addAttribute("community", oCommunity.get());
		return "index";
	}

	private void addCommunitiesListToModel(Model model) {
		model.addAttribute("communitiesList", communityService.findAll());
	}
}
