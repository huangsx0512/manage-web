package com.zimmur.platform.manage.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zimmur.platform.manage.web.util.DateUtils;

@Controller
@RequestMapping("/accountRole")
public class AccountRoleController {
	@RequestMapping("/index")
	public String home(HttpServletRequest request,ModelMap model){
		model.addAttribute("dd", DateUtils.getNow());
		return "/open/index";
	}
}
