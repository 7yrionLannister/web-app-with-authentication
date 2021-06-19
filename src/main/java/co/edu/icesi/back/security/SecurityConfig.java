package co.edu.icesi.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.back.model.UserType;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity//.userDetailsService(myCustomUserDetailsService)
		.csrf().disable()// se deshabilita para que funcionen las peticiones a los rest controllers, es mala practica deshabilitarlo, corregirlo de otra manera
		.formLogin()
		.loginPage("/login").permitAll()
		.and().authorizeRequests()
		.antMatchers("/api/**")
		.permitAll()
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