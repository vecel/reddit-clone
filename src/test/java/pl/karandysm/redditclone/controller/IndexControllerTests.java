package pl.karandysm.redditclone.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import pl.karandysm.redditclone.model.Community;
import pl.karandysm.redditclone.model.Post;
import pl.karandysm.redditclone.service.CommunityService;
import pl.karandysm.redditclone.service.PostService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() throws Exception {
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:" + port + "/", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void listOfCommunitiesIsRendered() {
		CommunityService communityService = applicationContext.getBean(CommunityService.class);
		Community community1 = new Community("test1", "description 1");
		Community community2 = new Community("test2", "description 2");
		communityService.addCommunity(community1);
		communityService.addCommunity(community2);

		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:" + port + "/", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String responseBody = response.getBody();
		assertThat(responseBody
				.contains("<a class=\"sidebar__list-item sidebar__list-item--hover\" href=\"/community?name=test1\">"))
				.isTrue();
		assertThat(responseBody
				.contains("<a class=\"sidebar__list-item sidebar__list-item--hover\" href=\"/community?name=test2\">"))
				.isTrue();
	}

	@Test
	void postForCommunityAreRendered() {
		CommunityService communityService = applicationContext.getBean(CommunityService.class);
		PostService postService = applicationContext.getBean(PostService.class);

		Community community1 = new Community("test1", "description 1");
		communityService.addCommunity(community1);

		Post post1 = new Post("post1", "", community1.getId(), (long) 1);
		Post post2 = new Post("post2", "", community1.getId(), (long) 1);
		postService.addPost(post1);
		postService.addPost(post2);

		ResponseEntity<String> response = testRestTemplate
				.getForEntity("http://localhost:" + port + "/community?name=test1", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String responseBody = response.getBody();
		assertThat(responseBody.contains("<span class=\"post__title post__title--hover\">post1</span>")).isTrue();
		assertThat(responseBody.contains("<span class=\"post__title post__title--hover\">post2</span>")).isTrue();
	}

	@Test
	void gettingCommunityThatDoesNotExistReturnsStatus500() {
		ResponseEntity<String> response = testRestTemplate
				.getForEntity("http://localhost:" + port + "/community?name=doesnotexist", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
