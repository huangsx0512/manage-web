package com.zimmur.platform.manage.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zimmur.platform.manage.web.util.DateUtils;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping("/index")
	public String index(ModelMap model){
		Integer pageNo = getIntParam("pageNo");
		Integer pageSize = getIntParam("pageSize");
		String keyword = getParam("keyword");
		model.addAttribute("dd", DateUtils.getNow());
		return "/centre/account/list";
	}
}
