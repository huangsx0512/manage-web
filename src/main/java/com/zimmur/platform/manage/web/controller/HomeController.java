package com.zimmur.platform.manage.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zimmur.platform.manage.web.util.DateUtils;


@Controller
@RequestMapping("/home")
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping("/index")
	public String home(HttpServletRequest request,ModelMap model){
		model.addAttribute("dd", DateUtils.getNow());
		return "/open/index";
	}
	
	/**
	 * 没有权限提示页面
	 * @return
	 */
	@RequestMapping(value="unauthorized",method=RequestMethod.GET)
	public ModelAndView unauthorized(){
		return new ModelAndView("common/unauthorized");
	}
	
}
