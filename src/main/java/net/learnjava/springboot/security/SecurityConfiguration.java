package net.learnjava.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.learnjava.springboot.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	MyUserDetailsService userDetailsService;
	/**
	 * configure Authentication using Authentication Manager
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	/**
	 * set up Authorization using HttpSecurity
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()  //use when need to authorize all endspoint
		.antMatchers("/api/v1/admin").hasRole("ADMIN") ///admin endpoint for loggedin user with role  'ADMIN'
		.antMatchers("/api/v1/user").hasAnyRole("ADMIN","USER") ///users endpoing for logged in user with 'User" role, User can have ADMIN role as well. so its any Role
		.antMatchers("/api/v1/*").permitAll() // for all end points it needs to access login form. Note:never put this first
		.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// this is just demo purpose. in production(reality)
		// you have to use password encoder
		return NoOpPasswordEncoder.getInstance();
	}
}
