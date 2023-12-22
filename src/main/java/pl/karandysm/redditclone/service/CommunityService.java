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
	
	@Autowired
	private CommunityRepository communityRepository;
	
	public List<Community> findAll() {
		return communityRepository.findAll();
	}
	
	public Optional<Community> getCommunityByName(String name) {
		List<Community> communities = communityRepository.findByCommunityName(name);
		if (communities.size() > 0) {
			return Optional.of(communities.get(0));
		}
		return Optional.empty();
	}
	
	public Community addCommunity(Community community) {
		return communityRepository.save(community);
	}
	
	public boolean isUserInCommunity(User user, Community community) {
		return community.getMembers().contains(user);
	}
	
	public void addUserToCommunity(User user, Community community) {
		community.getMembers().add(user);
		communityRepository.flush();
	}
}
