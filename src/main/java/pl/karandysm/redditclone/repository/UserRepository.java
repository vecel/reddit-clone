package pl.karandysm.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
