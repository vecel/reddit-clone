package pl.karandysm.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
