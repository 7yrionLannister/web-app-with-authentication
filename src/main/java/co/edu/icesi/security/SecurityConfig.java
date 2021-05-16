package co.edu.icesi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.model.UserType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	//	@Override
	//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.authenticationProvider(authenticationProvider());
	//	}
//
///*	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = 
//				PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		/*
//		auth
//		.inMemoryAuthentication()
//		.withUser("user")
//		.password(encoder.encode("password"))
//		.roles("USER")
//		.and()
//		.withUser("admin")
//		.password(encoder.encode("admin"))
//		.roles("USER", "ADMIN");
//		 */
//		Institution administration = new Institution();
//		administration.setInstName("administration");
//		administration = institutionRepository.save(administration);
//		Userr admin = new Userr();
//		admin.setUserName("admin");
//		admin.setUserType(UserType.ADMINISTRATOR);
//		admin.setUserPassword("admin");
//		admin.setInstitution(administration);
//		admin = userRepository.save(admin); // This is the admin added in the 
//		//auth.inMemoryAuthentication().withUser(admin.getUserName()).password(admin.getUserPassword()).roles(admin.getUserType().toString());
//		//auth.inMemoryAuthentication().withUser(admin.getUserName()).password(encoder.encode(admin.getUserPassword())).roles(admin.getUserType().toString());
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles(UserType.ADMINISTRATOR.toString());
//	}
	//	@Bean
	//	public DaoAuthenticationProvider authenticationProvider() {
	//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	//		authProvider.setUserDetailsService(userDetailsService);
	//		authProvider.setPasswordEncoder(encoder());
	//		return authProvider;
	//	}
	//
	//	@Bean
	//	public PasswordEncoder encoder() {
	//		return new BCryptPasswordEncoder(11);
	//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity//.userDetailsService(myCustomUserDetailsService)
		.formLogin()
		.loginPage("/login").permitAll()
		.and().authorizeRequests()
		.antMatchers("/auts/**")
		.hasRole(UserType.ADMINISTRATOR.toString())
		.antMatchers("/pres/**", "/thrs/**", "/locs/**")
		.hasRole(UserType.OPERATOR.toString())
		.anyRequest().authenticated().and()
		.httpBasic().and().logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
	}
}