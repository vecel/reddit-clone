package pl.karandysm.redditclone.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void userDtoIsPresentInModelAfterGet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(model().attributeExists("userDto"));
	}
	
}
