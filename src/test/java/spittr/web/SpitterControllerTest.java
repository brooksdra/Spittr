package spittr.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.entities.Spitter;
import spittr.repositories.AuthoritiesRepository;
import spittr.repositories.SpitterRepository;

public class SpitterControllerTest {

	@Test
	public void shouldShowRegistrationPage() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		AuthoritiesRepository mockAuthRepository = mock(AuthoritiesRepository.class);
		SpitterController controller = new SpitterController(mockRepository, mockAuthRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		AuthoritiesRepository mockAuthRepository = mock(AuthoritiesRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "jbauer@24hours");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@24hours");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		SpitterController controller = new SpitterController(mockRepository, mockAuthRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register")
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")
				.param("email", "jbauer@24hours"))
				.andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	
//	 @Test
//	  public void shouldFailValidationWithNoData() throws Exception {
//	    SpitterRepository mockRepository = mock(SpitterRepository.class);    
//	    SpitterController controller = new SpitterController(mockRepository);
//	    MockMvc mockMvc = standaloneSetup(controller).build();
//	    
//	    mockMvc.perform(post("/spitter/register"))
//	        .andExpect(status().isOk())
//	        .andExpect(view().name("registerForm"))
//	        .andExpect(model().errorCount(5))
//	        .andExpect(model().attributeHasFieldErrors(
//	            "spitter", "firstName", "lastName", "username", "password", "email"));
//	  }
	 
	@Test
	public void shouldShowProfile() throws Exception {

		Spitter jbauer = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "jbauer@24hours");
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		AuthoritiesRepository mockAuthRepository = mock(AuthoritiesRepository.class);
	    when(mockRepository.findByUsername("jbauer")).thenReturn(jbauer);
		SpitterController controller = new SpitterController(mockRepository, mockAuthRepository);
	    MockMvc mockMvc = standaloneSetup(controller)
	        .setSingleView(new InternalResourceView("/WEB-INF/pages/profile.jsp"))
	        .build();
		
		mockMvc.perform(get("/spitter/jbauer")).andExpect(view().name("profile"));
	}

}
