package com.zimmur.platform.manage.web.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zimmur.platform.manage.web.domain.model.App;
import com.zimmur.platform.manage.web.domain.vo.AppVo;
import com.zimmur.platform.manage.web.domain.vo.RoleVo;
import com.zimmur.platform.manage.web.service.IAppService;
import com.zimmur.platform.manage.web.service.IRoleService;
import com.zimmur.platform.manage.web.util.DateUtils;
import com.zimmur.platform.manage.web.util.PageUtil;
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private IAppService appServcie;
	@Autowired
	private IRoleService roleServcie;
	/**
	 * 初始化应用信息界面
	 * @return
	 */
	@RequestMapping(value="/initApp",method=RequestMethod.GET)
	public String login(ModelMap model){
		return "/centre/auth/app/list";
	}
	/**
	 * 综合检索应用界面
	 * @return
	 */
	@RequestMapping(value="/appList",method=RequestMethod.GET)
	public String list(ModelMap model){
		Integer pageNo = getParam("pageNo",1);
		Integer pageSize = getParam("pageSize",20);
		String keyword = getParam("keyword");
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查询应用信息以及应用具有的角色
		PageUtil<AppVo> appInfo = appServcie.queryAppInfo(pageNo, pageSize, keyword);
		model.addAttribute("page", appInfo);
		return "/centre/auth/app/list";
	}
	/**
	 * 变更应用状态
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	public String updateStatus(ModelMap model){
		Integer appId = getIntParam("appId");
		byte status = getByteParam("status");
		//查询应用信息以及应用具有的角色
		App app = new App();
		app.setId(appId);
		app.setIsEnabled(status);
		int flag = appServcie.update(app);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/app/list";
	}
	/**
	 * 初始化应用编辑页面
	 * @return
	 */
	@RequestMapping(value="/initEditInfo",method=RequestMethod.GET)
	public String initEditInfo(ModelMap model){
		Integer appId = getIntParam("appId");
		if(appId!=0){//修改应用信息初始化编辑页面数据
			//1.查询应用信息
			App app = new App();
			app.setId(appId);
			App appInfo = appServcie.readById(appId);
			model.addAttribute("app",appInfo);
			//2.查询角色信息
			List<RoleVo> roleVoList = roleServcie.queryRoleInfoByAppId(appId);
			model.addAttribute("roleList",roleVoList);
		}else{//新增应用信息初始化编辑页面
			//1.查询应用信息
			App app = new App();
			model.addAttribute("app",app);
			//2.查询角色信息
			List<RoleVo> roleVoList = roleServcie.queryRoleInfoByAppId(appId);
			model.addAttribute("roleList",roleVoList);
		}
		return "/centre/auth/app/edit";
	}
	/**
	 * 保存应用信息(包括应用的角色信息)
	 * @return
	 */
	@RequestMapping(value="/saveAppInfo",method=RequestMethod.POST)
	public void saveAppInfo(PrintWriter printWriter){
		int flag = 0;
		int seq = 0;//序列id
		JSONObject jsonObject = new JSONObject();  
		Integer appId = getIntParam("appId");
		String name = getParam("name");
		String roleCode = getParam("code");
		String appKey = getParam("appKey");
		Byte isEnabled = getByteParam("isEnabled");
		String ips = getParam("ips");
		String roleIds = getParam("roleIds");
		String [] roles = roleIds.split(",");
		List <Integer> roleId = new ArrayList<Integer>();
		for(String role:roles){
			roleId.add(Integer.parseInt(role));
		}
		//1.查询应用信息
		if(appId==0){
			App app = new App();
			app.setId(appId);
			app.setName(name);
			app.setCode(roleCode);
			app.setAppKey(appKey);
			app.setIps(ips);
			app.setIsEnabled(isEnabled);
			seq = appServcie.add(app);
		}else{
			App app = new App();
			app.setId(appId);
			app.setName(name);
			app.setCode(roleCode);
			app.setAppKey(appKey);
			app.setIps(ips);
			app.setIsEnabled(isEnabled);
			flag = appServcie.update(app);
		}
		//2.查询角色信息
		if(!roleId.isEmpty()&&roleId!=null){
			for(Integer role:roleId){
				if(appId!=0&&flag==1){
					appServcie.grandRoleForApp(appId, role);
				}else{
					appServcie.grandRoleForApp(seq, role);
				}
			}
			jsonObject.put("code","000000");
			jsonObject.put("msg","应用信息修改成功！");
		}
		printWriter.write(jsonObject.toString());  
        printWriter.flush();  
        printWriter.close();  
	}
	/**
	 * 删除应用信息
	 * @return
	 */
	@RequestMapping(value="/deleteApp",method=RequestMethod.POST)
	public String deleteApp(ModelMap model){
		Integer appId = getIntParam("appId");
		int flag = appServcie.delete(appId);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/app/list";
	}
}
