package pl.karandysm.redditclone.service;

import org.springframework.stereotype.Service;
import pl.karandysm.redditclone.dto.PostDto;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.CommunityRepository;
import pl.karandysm.redditclone.repository.PostRepository;
import pl.karandysm.redditclone.repository.UserRepository;

import java.util.Optional;

@Service
public class PostManagementService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommunityRepository communityRepository;

    public PostManagementService(PostRepository postRepository, UserRepository userRepository, CommunityRepository communityRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.communityRepository = communityRepository;
    }

    public boolean savePost(PostDto postDto, Long authorId, Long communityId) {
        Optional<User> author = userRepository.findById(authorId);
        Optional<Community> community = communityRepository.findById(communityId);

        // Would be better to throw exceptions
        if (author.isEmpty()) {
            return false;
        }
        if (community.isEmpty()) {
            return false;
        }

        Post post = new Post(postDto.getTitle(), postDto.getContent(), community.get(), author.get());
        community.get().getPosts().add(post);
        author.get().getPosts().add(post);

        postRepository.save(post);

        return true;
    }
}
