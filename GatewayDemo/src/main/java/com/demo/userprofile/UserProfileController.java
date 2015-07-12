/**
 * 
 */
package com.demo.userprofile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.bean.User;
import com.demo.service.UserService;

/**
 * @author Addo
 *
 */
@Controller
public class UserProfileController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/**
	 * @return
	 */
	@RequestMapping(value="/loadUserProfile.do", method=RequestMethod.GET)
	public ModelAndView loadUserProfile(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.loadUserByUsername(auth.getName());
		ModelAndView mav = new ModelAndView();
		mav.addObject("userprofile", user);
		mav.setViewName("update");
		return mav;
	}
	@RequestMapping(value="/updateUserProfile.do", method=RequestMethod.POST)
	public @ResponseBody Object updateUserProfile(@RequestBody User user){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userOld = userService.loadUserByUsername(auth.getName());
		user.setUsername(userOld.getUsername());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(userOld.getRole());
		userService.update(user);
		return new HashMap<String, String>();
	}
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public @ResponseBody Object register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		String tempPassword = user.getPassword();
		User userOld = userService.loadUserByUsername(user.getUsername());
		Map result = new HashMap<String, String>();
		if(userOld != null){
			result.put("ErrorCode", "E002");
			result.put("Msg", "Username token");
			return result;
		}else{
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_USERS");
			userService.update(user);
			doAutoLogin(user.getUsername(), tempPassword, request);
			return result;
		}
	}
	
	private void doAutoLogin(String username, String password, HttpServletRequest request) {

	    try {
	        // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
	        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
	        token.setDetails(new WebAuthenticationDetails(request));
	        Authentication authentication = authenticationManager.authenticate(token);
	        System.out.println(authentication.getPrincipal());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    } catch (Exception e) {
	        SecurityContextHolder.getContext().setAuthentication(null);
	    }

	}
}
