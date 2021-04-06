package net.learnjava.springboot.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements  UserDetails{


	private static final long serialVersionUID = 1L;
	private String userName;

	MyUserDetails() {
		//empty constructor
	}
	MyUserDetails(String userName) {
		this.userName = userName;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// temporary passing some constant values
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
				new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public String getPassword() {
		System.out.println("pass");
		return "pass"; //temporary hardcoded
	}

	@Override
	public String getUsername() {
		System.out.println("userName: "+userName);
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
