/**
 * 
 */
package com.demo.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.service.UserService;


/**
 * @author Addo
 *
 */
public class RemoteUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.demo.bean.User user = userService.loadUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException(username);
		}
		boolean enables = true;  
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true; 
        Set<GrantedAuthority> authorities = obtionGrantedAuthorities(user);
        User userDetails = new User(user.getUsername(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return userDetails;
	}
	
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.demo.bean.User user) {  
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();  
        authSet.add(new SimpleGrantedAuthority(user.getRole()));
        return authSet;  
    }

}
