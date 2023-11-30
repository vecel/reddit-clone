package pl.karandysm.redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.karandysm.redditclone.model.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
	
	List<Community> findByCommunityName(String communityName);
}
