//package info.ashutosh;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class SpringJUnitMockitoAPITestingTests {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@WithMockUser(password = "user", username = "user", authorities = "USER")
//	@Test
//	void endpointWhenUserAuthorityThenAuthorized() throws Exception {
//		this.mvc.perform(get("/foods")).andExpect(status().isOk());
//	}
//
//	@WithMockUser
//	@Test
//	void endpointWhenNotUserAuthorityThenForbidden() throws Exception {
//		this.mvc.perform(get("/foods")).andExpect(status().isUnauthorized());
//	}
//
//	@Test
//	void anyWhenUnauthenticatedThenUnauthorized() throws Exception {
//		this.mvc.perform(get("/any")).andExpect(status().isUnauthorized());
//	}
//
//}
