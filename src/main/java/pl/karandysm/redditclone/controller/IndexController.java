package pl.karandysm.redditclone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.CommunityService;
import pl.karandysm.redditclone.service.PostService;
import pl.karandysm.redditclone.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		addCommunitiesListToModel(model);
		model.addAttribute("postsDefaultView", postService.findAll());
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionConstants.USER);
		return "redirect:/";
	}
	
	@GetMapping("/community")
	public String community(@RequestParam("name") String name, Model model, HttpSession session) {
		addCommunitiesListToModel(model);
		Optional<Community> oCommunity = communityService.getCommunityByName(name);
		if (oCommunity.isEmpty()) {
			// placeholder
			return "redirect:/error";
		}
		List<Post> posts = postService.getPostsForCommunity(oCommunity.get());

		model.addAttribute("community", oCommunity.get());
		model.addAttribute("posts", posts);
		model.addAttribute("userInCommunity", communityService.isUserInCommunity((User)session.getAttribute(HttpSessionConstants.USER), oCommunity.get()));
		return "index";
	}
	
	@GetMapping("/community/join")
	public String communityJoin(@RequestParam("name") String name, Model model, HttpSession session){
		Community community = communityService.getCommunityByName(name).get();
		communityService.addUserToCommunity((User) session.getAttribute(HttpSessionConstants.USER), community);
		return "redirect:/community?name=" + name;
	}

	private void addCommunitiesListToModel(Model model) {
		model.addAttribute("communitiesList", communityService.findAll());
	}
}
