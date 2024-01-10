package pl.karandysm.redditclone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.CommunityService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/community/{id}")
    public ResponseEntity<?> community(@PathVariable Long id) {
        Optional<Community> community = communityService.findById(id);
        return community.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/community/{id}/members")
    public ResponseEntity<?> communityMembers(@PathVariable Long id) {
        Optional<Community> community = communityService.findById(id);
        return community.map(response -> ResponseEntity.ok().body(response.getMembers()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/community/{id}/posts")
    public ResponseEntity<?> communityPosts(@PathVariable Long id) {
        Optional<Community> community = communityService.findById(id);
        return community.map(response -> ResponseEntity.ok().body(response.getPosts()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
