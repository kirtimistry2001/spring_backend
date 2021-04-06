package net.learnjava.springboot.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	/**
	 * configure Authentication using Authentication Manager
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	/**
	 * set up Authorization using HttpSecurity
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()  //use when need to authorize all endspoint
		.antMatchers("/admin").hasRole("ADMIN") ///admin endpoint for loggedin user with role  'ADMIN'
		.antMatchers("/user").hasAnyRole("ADMIN","USER") ///users endpoing for logged in user with 'User" role, User can have ADMIN role as well. so its any Role
		.antMatchers("/*").permitAll() // for all end points it needs to access login form. Note:never put this first
		.and().formLogin();

	}
}
