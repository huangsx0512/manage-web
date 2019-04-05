package com.zimmur.platform.manage.web.controller;

import com.zimmur.platform.manage.web.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/accountRole")
public class AccountRoleController {
	/**
	 * @Description: 
	 * @auther: huangsx
	 * @date:  2019/4/5 22:35
	 * @param: [request, model]
	 * @return: java.lang.String
	 */
	@RequestMapping("/index")
	public String home(HttpServletRequest request, ModelMap model) {
		model.addAttribute("dd", DateUtils.getNow());
		return "/open/index";
	}
}
