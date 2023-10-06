package pl.karandysm.redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM users WHERE username=:username", nativeQuery = true)
	List<User> findUsersByUsername(@Param("username") String username);
	
	@Query(value = "SELECT * FROM users WHERE email=:email", nativeQuery = true)
	List<User> findUsersByEmail(@Param("email") String email);
}
