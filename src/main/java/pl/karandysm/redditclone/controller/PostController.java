package pl.karandysm.redditclone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karandysm.redditclone.dto.PostDto;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.service.PostManagementService;
import pl.karandysm.redditclone.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    private PostService postService;
    private PostManagementService postManagementService;

    public PostController(PostService postService, PostManagementService postManagementService) {
        this.postService = postService;
        this.postManagementService = postManagementService;
    }

    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.findAll();
    }

    @PostMapping("/post/add/{communityId}/{authorId}")
    public ResponseEntity<?> addPost(@PathVariable Long communityId, @PathVariable Long authorId, @RequestBody PostDto postDto) {
        logger.info("Add post to community: " + communityId + ", author: " + authorId + ", postDto: " + postDto);
        boolean saved = postManagementService.savePost(postDto, authorId, communityId);
        logger.info(saved ? "Post saved" : "Post not saved");
        return new ResponseEntity<>(saved ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
