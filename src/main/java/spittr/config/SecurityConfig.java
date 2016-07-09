package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import spittr.repositories.SpitterRepository;
import spittr.security.SpitterUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String ENC = "thisNeedsToBe100%Safe";
	public static final StandardPasswordEncoder SPE;
	static {
		SPE = new StandardPasswordEncoder(SecurityConfig.ENC);
	}
	
	// used for examples 1-3
	// @Autowired
	// DataSource dataSource;

	// Used for example 4.
	@Autowired
	SpitterRepository spitterRepository;

	// 1. a basic auth with no users
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// .authorizeRequests()
	// .anyRequest().authenticated()
	// .and()
	// .formLogin().and()
	// .httpBasic();
	// }

	// 2. an in-memory authorization with configured users (must comment out the
	// above for this to work)
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication()
	// .withUser("user").password("user").roles("USER").and()
	// .withUser("admin").password("admin").roles("USER", "ADMIN");
	// }

	// 3. a database authorization (must comment out the above for this to work)
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth)
	// throws Exception {
	// auth
	// .jdbcAuthentication()
	// .dataSource(dataSource)
	// .passwordEncoder(new StandardPasswordEncoder(ENC));
	// }

	// 4. apply spring securities UserDetailService (must comment out the above
	// for this to work)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(new SpitterUserService(spitterRepository))
			.passwordEncoder(new StandardPasswordEncoder(ENC));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()				// activate default login page
//				.loginPage("/login")    // use a custom login page
//			 .and()
//			.rememberMe()   			// add a remember me capabilities
//				 .tokenValiditySeconds(2419200) //two weeks
//				 .key("spittrKey")
			 .and()
			.logout()					// provide logout link, with the logout URL below
	          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              .deleteCookies("remove")
              .invalidateHttpSession(true)
	          .logoutSuccessUrl("/")
			 .and()
			.authorizeRequests()
				.antMatchers("/", "/home", "/register").permitAll()				// all everybody on the home page
				.antMatchers("/spitters").authenticated() 						// must be authenticated to view /spitters
				.antMatchers("/spittles").authenticated() 						// must be authenticated to view /spittles
//				.antMatchers("/spitter/me").access("hasRole('ROLE_SPITTER')")  	// This uses SpEL for a more flexible filter.
//				.antMatchers("/spitters/me").authenticated()         			// Less flexible security configuration
//				.antMatchers(HttpMethod.GET, "/spittles").authenticated() 
//				.antMatchers(HttpMethod.POST, "/spittles").authenticated() 
//				.anyRequest().permitAll()
//			 .and()
//			.requiresChannel()
//				.antMatchers("/spitter/registerForm").requiresSecure()  // should switch to https channel
//				.antMatchers("/").requiresInsecure(); 			// should switch to http channel if already in https
				;
	}

}