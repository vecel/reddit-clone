package pl.karandysm.redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
//	List<Post> findByCommunityId(Long id);
	
	List<Post> findAllByCommunity(Community community);
	
	List<Post> findAllByAuthor(User user);
}
