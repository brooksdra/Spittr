package spittr.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spittr.config.DataConfig;
import spittr.config.SecurityConfig;
import spittr.entities.Authorities;
import spittr.entities.Spitter;
import spittr.repositories.AuthoritiesRepository;
import spittr.repositories.SpitterRepository;

public class DataPrimer {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        LOG.info("-- @profile=dev");
//        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(DataConfig.class);
        ctx.refresh();
        
        SpitterRepository spitterRepository = (SpitterRepository)ctx.getBean("spitterRepository");
        AuthoritiesRepository authoritiesRepository = (AuthoritiesRepository)ctx.getBean("authoritiesRepository");
		
//		StandardPasswordEncoder spe = new StandardPasswordEncoder(SecurityConfig.ENC);		
//		User admin = new User();
//    	admin.setUsername("admin");
//    	admin.setFirstName("Admin");
//    	admin.setLastName("God");
//    	admin.setPassword(spe.encode(admin.getUsername()));
//    	admin.setEnabled(Boolean.TRUE);
//    	userRepository.saveAndFlush(admin);
//    	
//    	Authorities adminAuth = new Authorities();
//		adminAuth.setUsername(admin.getUsername());
//		adminAuth.setAuthority("ROLE_ADMIN");
//		authoritiesRepository.saveAndFlush(adminAuth);
    	
    	Spitter spitter = new Spitter();
    	spitter.setUsername("spitter@gmail.com");
    	spitter.setEmail(spitter.getUsername());
    	spitter.setFirstName("Spitter");
    	spitter.setLastName("Face");
    	spitter.setPassword(SecurityConfig.SPE.encode("ethjil00"));
    	spitter.setEnabled(Boolean.TRUE);
    	spitterRepository.saveAndFlush(spitter);

    	Authorities spitterAuth = new Authorities();
    	spitterAuth.setUsername(spitter.getUsername());
    	spitterAuth.setAuthority("ROLE_USER");
		authoritiesRepository.saveAndFlush(spitterAuth);
		

    	ctx.close();

	}

}
