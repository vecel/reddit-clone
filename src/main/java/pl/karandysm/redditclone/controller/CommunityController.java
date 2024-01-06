package pl.karandysm.redditclone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.service.CommunityService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommunityController {

    private CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/communities")
    public List<Community> communities() {
        return communityService.findAll();
    }
}
