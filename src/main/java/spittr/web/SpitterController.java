package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.config.SecurityConfig;
import spittr.entities.Authorities;
import spittr.entities.Spitter;
import spittr.repositories.AuthoritiesRepository;
import spittr.repositories.SpitterRepository;

@Controller
@RequestMapping("/spitters")
public class SpitterController {

	private SpitterRepository spitterRepository;	
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository, AuthoritiesRepository authoritiesRepository) {
		this.spitterRepository = spitterRepository;
		this.authoritiesRepository = authoritiesRepository;
	}

	@RequestMapping(method = GET)
	public List<Spitter> showSpitters(Model model) {
		return spitterRepository.findAll();
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@RequestPart() Part profilePicture, @Valid Spitter spitter, Errors errors, RedirectAttributes model) {		
		if (errors.hasErrors()) {
			return "registerForm";
		}
		
		if (profilePicture != null) {
			try {
				profilePicture.write("/data/spittr/" + profilePicture.getSubmittedFileName());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//secure the password and for now enable useage. This will probably be corrected with email response
		spitter.setPassword(SecurityConfig.SPE.encode(spitter.getPassword()));
		spitter.setEnabled(Boolean.TRUE);
		spitterRepository.save(spitter);
		
		Authorities spitterAuth = new Authorities();
    	spitterAuth.setUsername(spitter.getUsername());
    	spitterAuth.setAuthority("ROLE_USER");
		authoritiesRepository.saveAndFlush(spitterAuth);
		
		model.addAttribute("username", spitter.getUsername());
		model.addFlashAttribute("spitter", spitter);
		return "redirect:/spitters/{username}";
	}
	
	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")) {
			Spitter spitter = spitterRepository.findByUsername(username);
			model.addAttribute(spitter);
		}
				
		return "profile";
	}

}
