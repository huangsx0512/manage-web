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
import com.zimmur.platform.manage.web.domain.model.Role;
import com.zimmur.platform.manage.web.domain.vo.MentVo;
import com.zimmur.platform.manage.web.domain.vo.RoleInfoVo;
import com.zimmur.platform.manage.web.service.IMentService;
import com.zimmur.platform.manage.web.service.IRoleService;
import com.zimmur.platform.manage.web.util.PageUtil;
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private IRoleService roleServcie;
	@Autowired
	private IMentService mentServcie;
	/**
	 * 初始化角色信息快速检索界面
	 * @return
	 */
	@RequestMapping(value="/initRole",method=RequestMethod.GET)
	public String login(ModelMap model){
		return "/centre/auth/role/list";
	}
	/**
	 * 综合检索角色界面
	 * @return
	 */
	@RequestMapping(value="/roleList",method=RequestMethod.GET)
	public String list(ModelMap model){
		Integer pageNo = getParam("pageNo",1);
		Integer pageSize = getParam("pageSize",20);
		String keyword = getParam("keyword");
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		//查询角色信息以及角色具有的权限
		PageUtil<RoleInfoVo> roleInfo = roleServcie.queryRoleInfo(pageNo, pageSize, keyword);
		model.addAttribute("page", roleInfo);
		return "/centre/auth/role/list";
	}
	/**
	 * 变更角色状态
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	public String updateStatus(ModelMap model){
		Integer roleId = getIntParam("roleId");
		byte status = getByteParam("status");
		//查询角色信息以及角色具有的权限
		Role role = new Role();
		role.setId(roleId);
		role.setIsEnabled(status);
		int flag = roleServcie.update(role);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/app/list";
	}
	/**
	 * 初始化角色编辑页面
	 * @return
	 */
	@RequestMapping(value="/initEditInfo",method=RequestMethod.GET)
	public String initEditInfo(ModelMap model){
		Integer roleId = getIntParam("roleId");
		if(roleId!=0){//修改角色信息初始化编辑页面数据
			//1.查询角色信息
			Role roleInfo = roleServcie.readById(roleId);
			model.addAttribute("role",roleInfo);
			//2.查询权限信息
			List<MentVo> mentVoList = mentServcie.queryMentInfoByRoleId(roleId);
			model.addAttribute("mentList",mentVoList);
		}else{//新增角色信息初始化编辑页面
			//1.查询角色信息
			Role role = new Role();
			model.addAttribute("role",role);
			//2.查询权限信息
			List<MentVo> mentVoList = mentServcie.queryMentInfoByRoleId(roleId);
			model.addAttribute("mentList",mentVoList);
		}
		return "/centre/auth/role/edit";
	}
	/**
	 * 保存角色信息(包括角色的权限信息)
	 * @return
	 */
	@RequestMapping(value="/saveRoleInfo",method=RequestMethod.POST)
	public void saveAppInfo(PrintWriter printWriter){
		JSONObject jsonObject = new JSONObject();  
		Integer roleId = getIntParam("roleId");
		Byte isEnabled = getByteParam("isEnabled");
		String name = getParam("name");
		int flag = 0;
		int seq = 0;//序列id
		String mentIds = getParam("mentIds");
		String [] ments = mentIds.split(",");
		List <Integer> MentId = new ArrayList<Integer>();
		for(String ment:ments){
			MentId.add(Integer.parseInt(ment));
		}
		//1.查询角色信息
		if(roleId==0){//新增角色
			Role role = new Role();
			role.setIsEnabled(isEnabled);
			role.setName(name);
			seq = roleServcie.add(role);
		}else{//变更角色信息
			Role role = new Role();
			role.setId(roleId);
			role.setIsEnabled(isEnabled);
			role.setName(name);
			flag= roleServcie.update(role);
		}
		//2.查询应用信息
		if(!MentId.isEmpty()&&MentId!=null){
			for(Integer ment:MentId){
				if(roleId!=0&&flag==1){
					roleServcie.grandAuthorityForRole(roleId, ment);//变更数据
				}else{
					roleServcie.grandAuthorityForRole(seq, ment);//插入数据
				}
			}
			jsonObject.put("code","000000");
			jsonObject.put("msg","角色信息修改成功！");
		}
		printWriter.write(jsonObject.toString());  
        printWriter.flush();  
        printWriter.close();
	}
	/**
	 * 删除角色信息
	 * @return
	 */
	@RequestMapping(value="/deleteRole",method=RequestMethod.POST)
	public String deleteApp(ModelMap model){
		Integer roleId = getIntParam("roleId");
		int flag = roleServcie.delete(roleId);
		if(flag==1){
			model.addAttribute("code", "000000");
		}
		return "/centre/auth/app/list";
	}
}
