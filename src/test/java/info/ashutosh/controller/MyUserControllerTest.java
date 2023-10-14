package info.ashutosh.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.ashutosh.domain.MyUser;
import info.ashutosh.repository.MyUserService;

@WebMvcTest(controllers = MyUserController.class)
public class MyUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MyUserService userService;

	@Test
	public void testCreateUser() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		MyUser user = new MyUser();
		user.setId(1L);
		user.setName("John");
		user.setEmail("john@example.com");
		user.setUsername("john_username");
		user.setPassword("password");

		when(userService.createUser(any(MyUser.class))).thenReturn(user);

		String json = objectMapper.writeValueAsString(user);

		mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
				.contentType(MediaType.APPLICATION_JSON).content(json))
	            .andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"));
	}

	@Test
	public void testGetUserById() throws Exception {
		MyUser user = new MyUser();
		user.setId(1L);
		user.setName("John");

		when(userService.getUserById(1L)).thenReturn(Optional.of(user));

		mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"));
	}

	@Test
	public void testGetAllUsers() throws Exception {
		MyUser user1 = new MyUser();
		user1.setId(1L);
		user1.setName("John");
		MyUser user2 = new MyUser();
		user2.setId(2L);
		user2.setName("Alice");

		when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

		mockMvc.perform(MockMvcRequestBuilders.get("/users"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Alice"));
	}

	@Test
	public void testUpdateUser() throws Exception {
		MyUser user = new MyUser();
		user.setId(1L);
		user.setName("Updated John");

		// Mock the updateUser method of the userService to return the updated user
		when(userService.updateUser(any(Long.class), any(MyUser.class))).thenReturn(user);

		ObjectMapper objectMapper = new ObjectMapper();
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
		.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers
						.jsonPath("$.name")
						.value("Updated John"));
	}

	@Test
	public void testDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
