package org.jsp.usermvcapp.controller;

import org.jsp.usermvcapp.dao.UserDao;
import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value = "/open")
	public String openview(@RequestParam String v) {
		return v;
	}
	
	@PostMapping(value="/register")
	public ModelAndView saveuser(@ModelAttribute User u,ModelAndView v) {
		u=dao.saveuser(u);
		v.addObject("msg","user saved with Id  "+u.getId());
		v.setViewName("print");
		return v;
	}
	@RequestMapping(value = "/edit")
	public ModelAndView edituser(@RequestParam int id,ModelAndView v) {
		User u=dao.finduser(id);
		if(u!=null) {
			v.addObject("u", u);
			v.setViewName("update");
			return v;
		}
		else {
			v.addObject("msg", "The Id is Invalid");
			v.setViewName("print");
			return v;
		}
	}
	@RequestMapping(value = "/update")
	public ModelAndView updateuser(@ModelAttribute User u,ModelAndView v) {
			dao.updateuser(u);
			v.addObject("msg", "User is Updated");
			v.setViewName("print");
			return v;
		}
	@RequestMapping(value="/verify-phone")
	public ModelAndView verifybyphone(@RequestParam long phone,@RequestParam String password,ModelAndView v) {
		User u=dao.verifyuser(phone, password);
		if(u!=null) {
			v.addObject("u", u);
			v.setViewName("view");
			return v;
		}
		else {
			v.addObject("msg", "Your are Entered Invalid Phone Number or password");
			v.setViewName("print");
			return v;
		}
	}
	@RequestMapping(value="/verify-email")
	public ModelAndView verifybyemail(@RequestParam String email,@RequestParam String password,ModelAndView v) {
		User u=dao.verifyuser(email, password);
		if(u!=null) {
			v.addObject("u", u);
			v.setViewName("view");
			return v;
		}
		else {
			v.addObject("msg", "Your are Entered Invalid Email or password");
			v.setViewName("print");
			return v;
		}
	}
	@RequestMapping(value="/verify-id")
	public ModelAndView verifybyemail(@RequestParam int id,@RequestParam String password,ModelAndView v) {
		User u=dao.verifyuser(id, password);
		if(u!=null) {
			v.addObject("u", u);
			v.setViewName("view");
			return v;
		}
		else {
			v.addObject("msg", "Your are Entered Invalid Id or password");
			v.setViewName("print");
			return v;
		}
	}
	@RequestMapping(value="/delete")
	public ModelAndView deleteuser(@RequestParam int id,ModelAndView v) {
	     boolean deleted=dao.deleteuser(id);
	     if(deleted) {
	     v.setViewName("print");	 
	     v.addObject("msg", "User deleted successfully");
		 return v;
		}
	     else {
	    	 v.addObject("msg", "User id is Invalid");
	    	 v.setViewName("print");	
	    	 return v;
	     }
	}
}    
 

