package spittr.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import spittr.config.DataConfig;
import spittr.config.SecurityConfig;
import spittr.entities.Authorities;
import spittr.entities.User;
import spittr.repositories.AuthoritiesRepository;
import spittr.repositories.UserRepository;

public class DataPrimer {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        LOG.info("-- @profile=dev");
//        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(DataConfig.class);
        ctx.refresh();
        
        UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
        AuthoritiesRepository authoritiesRepository = (AuthoritiesRepository)ctx.getBean("authoritiesRepository");
		
		StandardPasswordEncoder spe = new StandardPasswordEncoder(SecurityConfig.ENC);		
		User admin = new User();
    	admin.setUsername("admin");
    	admin.setFirstName("Admin");
    	admin.setLastName("God");
    	admin.setPassword(spe.encode(admin.getUsername()));
    	admin.setEnabled(Boolean.TRUE);
    	userRepository.saveAndFlush(admin);

		Authorities adminAuth = new Authorities();
		adminAuth.setUsername("admin");
		adminAuth.setAuthority("ROLE_ADMIN");
		authoritiesRepository.saveAndFlush(adminAuth);

    	ctx.close();

	}

}
