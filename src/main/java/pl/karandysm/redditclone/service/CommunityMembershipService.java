package pl.karandysm.redditclone.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.repository.CommunityRepository;
import pl.karandysm.redditclone.repository.UserRepository;

import java.util.Optional;

@Service
public class CommunityMembershipService {

    private final Logger logger = LoggerFactory.getLogger(CommunityMembershipService.class);

    private CommunityRepository communityRepository;

    private UserRepository userRepository;

    public CommunityMembershipService(CommunityRepository communityRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    public boolean addUserToCommunity(Long userId, Long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);
        Optional<User> user = userRepository.findById(userId);

        if (community.isEmpty() || user.isEmpty()) {
            logger.warn("Can't add user to community, any of given arguments user, community is null");
            return false;
        }

        community.ifPresent(c -> c.getMembers().add(user.get()));
        user.ifPresent(u -> u.getCommunities().add(community.get()));

        communityRepository.flush();
        userRepository.flush();

        logger.info("Added user: " + user.get() + " to community: " + community.get());
        return true;
    }
}
