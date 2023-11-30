package pl.karandysm.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
