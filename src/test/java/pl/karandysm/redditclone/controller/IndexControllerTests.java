package pl.karandysm.redditclone.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IndexControllerTests {
	
	@Autowired
	private IndexController indexController;
	
	@Test
	void contextLoads() {
		assertThat(indexController).isNotNull();
	}
}
