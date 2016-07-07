package spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String ENC = "thisNeedsToBe100%Safe";
	
	@Autowired
	DataSource dataSource;

	// a basic auth with no users
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin().and()
//			.httpBasic();
//	}
	
	// an in-memory authorization with configured users (must comment out the above for this to work)
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user").password("user").roles("USER").and()
//			.withUser("admin").password("admin").roles("USER", "ADMIN");
//	}
	
	// a database authorization (must comment out the above for this to work)
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new StandardPasswordEncoder(ENC));;
	}
}