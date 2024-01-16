package pl.karandysm.redditclone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.CommunityRepository;

@Service
public class CommunityService {

	private CommunityRepository communityRepository;

	public CommunityService(CommunityRepository communityRepository) {
		this.communityRepository = communityRepository;
	}

	public List<Community> findAll() {
		return communityRepository.findAll();
	}

	public Optional<Community> findById(Long id) {
		return communityRepository.findById(id);
	}

	public Optional<Community> getCommunityByName(String name) {
		List<Community> communities = communityRepository.findByCommunityName(name);
		if (!communities.isEmpty()) {
			return Optional.of(communities.get(0));
		}
		return Optional.empty();
	}
	
	public Community addCommunity(Community community) {
		return communityRepository.save(community);
	}

}
