package pl.karandysm.redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUsername(String username);
	
	List<User> findByEmail(String email);
	
	List<User> findByIdIn(List<Long> ids);

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
