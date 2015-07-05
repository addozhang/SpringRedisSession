/**
 * 
 */
package com.demo.userprofile;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		userService.update(user);
		return new HashMap<String, String>();
	}
}
