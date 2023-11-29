package pl.karandysm.redditclone.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import pl.karandysm.redditclone.dto.UserDto;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void userDtoIsPresentInModelAfterGet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(model().attributeExists("userDto"));
	}

	@Test
	void userIsPresentInSessionAfterCreationFromDto() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUsername("testUsername");
		userDto.setEmail("test@test.test");
		userDto.setPassword("Supertajnehaslo1234");
		userDto.setPasswordMatch("Supertajnehaslo1234");
		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/register").flashAttr("userDto", userDto));
		resultActions.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
		
		Object userAttribute = resultActions.andReturn().getRequest().getSession().getAttribute("user");
		assertThat(userAttribute).isNotNull();
	}

}
