package pl.karandysm.redditclone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public List<Post> getPostsForCommunityById(Long id) {
		return postRepository.findByCommunityId(id);
	}
}
