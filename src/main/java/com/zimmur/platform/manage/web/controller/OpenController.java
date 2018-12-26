package com.zimmur.platform.manage.web.controller;

import com.zimmur.platform.manage.web.common.ReturnJsonEntity;
import com.zimmur.platform.manage.web.common.StatusCodeEnum;
import com.zimmur.platform.manage.web.domain.model.Account;
import com.zimmur.platform.manage.web.shiro.token.TokenManager;
import com.zimmur.platform.manage.web.util.VerifyCodeUtils;
import com.zimmur.platform.manage.web.util.VerifyCodeUtils.Verify;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/open")
public class OpenController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(OpenController.class);
	/**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("open/login");
	}
	/**
	 * 登录提交
	 * @param entity		登录的UUser
	 * @param rememberMe	是否记住
	 * @param request		request，用来取登录之前Url地址，用来登录后跳转到没有登录之前的页面。
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/submitLogin",method=RequestMethod.POST)
	public ReturnJsonEntity submitLogin(Account account,Boolean rememberMe,HttpServletRequest request){
		ReturnJsonEntity entity = new ReturnJsonEntity();
		StatusCodeEnum codeEnum = StatusCodeEnum.CODE100000;
		try {

			//account.setAccountPwd(MD5.MD5Encode(account.getPassword()).toUpperCase());
			account = TokenManager.login(account, rememberMe);
			if (account != null) {
				codeEnum = StatusCodeEnum.CODE000000;
			}
			/**
			 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
			 */
		} catch (DisabledAccountException e) {
			codeEnum = StatusCodeEnum.CODE200403;
		} catch (Exception e) {
			codeEnum = StatusCodeEnum.CODE200404;
		}
		entity.init(codeEnum);
		return entity;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(){
		TokenManager.logout();
		return new ModelAndView("redirect:/open/login.shtml");
	}
	
	
	
	/** 
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中) 
     */  
    @RequestMapping("/getVerifyCodeImage")  
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        //设置页面不缓存  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        Verify verify = VerifyCodeUtils.generateVerify();
        //将验证码放到HttpSession里面  
        request.getSession().setAttribute("verifyCode", verify.getCode());  
        System.out.println("本次生成的验证码为[" + verify.getCode() + "],已存放到HttpSession中");  
        //设置输出的内容的类型为JPEG图像  
        response.setContentType("image/jpeg");  
        VerifyCodeUtils.outputImage(90, 30, response.getOutputStream(), verify.getCode());  
    }  
}
