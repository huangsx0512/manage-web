package com.zimmur.platform.manage.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.zimmur.platform.common.utils.StringUtil;
import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.service.IMentService;
import com.zimmur.platform.manage.web.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
@Controller
@RequestMapping("/ment")
public class MentController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MentController.class);
	@Autowired
	private IMentService mentServcie;
	/**
	 * 初始化权限信息快速检索界面
	 * @return
	 */
	@RequestMapping(value="/initMent",method=RequestMethod.GET)
	public String login(ModelMap model) {
		return "/centre/auth/permission/list";
	}
	/**
	 * 综合检索权限信息界面
	 * @return
	 */
	@RequestMapping(value="/mentList",method=RequestMethod.GET)
	public String list(ModelMap model){
		Integer pageNo = getParam("pageNo",1);
		Integer pageSize = getParam("pageSize",20);
		String keyword = getParam("keyword");
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		//查询权限信息
		PageUtil<Ment> mentInfo = mentServcie.queryMentInfo(pageNo, pageSize, keyword);
		model.addAttribute("page", mentInfo);
		return "/centre/auth/permission/list";
	}
	/**
	 * 变更权限状态
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	public String updateStatus(ModelMap model){
		Integer mentId = getIntParam("mentId");
		Short status = getShortParam("status");
		//
		Ment ment = new Ment();
		ment.setId(mentId);
		ment.setStatus(status);
		int flag = mentServcie.update(ment);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/permission/list";
	}
	/**
	 * 初始化角色编辑页面
	 * @return
	 */
	@RequestMapping(value="/initEditInfo",method=RequestMethod.GET)
	public String initEditInfo(ModelMap model){
		Integer mentId = getIntParam("mentId");
		if(mentId!=0){//修改权限信息初始化编辑页面数据
			//查询权限信息
			Ment mentInfo = mentServcie.readById(mentId);
			model.addAttribute("ment",mentInfo);
		}else{//新增权限信息初始化编辑页面
			//查询权限信息
			Ment ment = new Ment();
			model.addAttribute("ment",ment);
		}
		return "/centre/auth/permission/edit";
	}
	/**
	 * 保存权限信息
	 * @return
	 */
	@RequestMapping(value = "/saveMentInfo", method = RequestMethod.POST)
	public void saveMentInfo(PrintWriter printWriter) {
		JSONObject jsonObject = new JSONObject();
		Integer mentId = getIntParam("mentId");
		Short status = getShortParam("status");
		String apiServer = getParam("apiServer");
		String name = getParam("name");
		String url = getParam("url");
		String method = getParam("method");
		int flag = 0;
		if (mentId == 0) {//新增权限
			Ment ment = new Ment();
			ment.setCreateTime(new Date());
			ment.setName(name);
			ment.setStatus(status);
			ment.setUrl(url);
			ment.setMethod(method);
			ment.setApiServer(apiServer);
			ment.setCreateTime(new Date());
			flag = mentServcie.add(ment);
		} else {//变更权限信息
			Ment ment = new Ment();
			if (StringUtil.isNotEmptyOrNull(name)) {
				ment.setName(name);
			}
			if (StringUtil.isNotEmptyOrNull(apiServer)) {
				ment.setApiServer(apiServer);
			}
			if (StringUtil.isNotEmptyOrNull(url)) {
				ment.setApiServer(url);
			}
			if (StringUtil.isNotEmptyOrNull(method)) {
				ment.setApiServer(method);
			}
			ment.setStatus(status);
			ment.setId(mentId);
			flag = mentServcie.update(ment);
		}
		if (flag == 1) {
			jsonObject.put("code", "000000");
			jsonObject.put("msg", "权限信息修改成功！");
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
		printWriter.close();
	}
	/**
	 * 删除权限信息
	 * @return
	 */
	@RequestMapping(value="/deleteMent",method=RequestMethod.POST)
	public String deleteMent(ModelMap model){
		Integer mentId = getIntParam("mentId");
		int flag = mentServcie.delete(mentId);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/permission/list";
	}
}
