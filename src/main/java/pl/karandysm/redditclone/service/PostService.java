package pl.karandysm.redditclone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.repository.PostRepository;

@Service
public class PostService {

	PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<Post> getPostsForCommunity(Community community) {
		return postRepository.findAllByCommunity(community);
	}
	
	public Post addPost(Post post) {
		return postRepository.save(post);
	}
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
}
