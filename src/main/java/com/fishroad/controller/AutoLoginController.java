package com.fishroad.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.dao.AccountMapper;
import com.fishroad.vo.Account;

@Controller
@RequestMapping("/auto")
public class AutoLoginController {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@RequestMapping("getCode")
	public void getCode(HttpServletRequest request,HttpServletResponse response,String username) throws Exception{
		response.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		BaiDu b=(BaiDu) request.getSession().getAttribute("login-"+username);
		if(b==null){
			b=new BaiDu(ac.getUsername(), ac.getPassword());
			b.login();
		}
		
		OutputStream os=response.getOutputStream();
		b.getCode(os);
		request.getSession().setAttribute("login-"+username, b);
		response.getOutputStream().flush();
	}
	
	@RequestMapping("/login")
	public void autoLogin(HttpServletRequest request,HttpServletResponse response,String username,String verifycode) throws Exception{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if(ac!=null){
			BaiDu b=(BaiDu) request.getSession().getAttribute("login-"+username);
			if(b!=null){
				b.volidate(verifycode);//验证码校验
				
				System.out.println(b.errorMsg);
				String cookies=JSONObject.toJSON(b.cookieStore.getCookies()).toString();
				if("需要邮箱验证".equals(b.errorMsg)){
					response.getWriter().print(b.errorMsg);
				}
				if("登录成功".equals(b.errorMsg)){
					ac.setLoginCookie(cookies);
					accountMapper.updateByPrimaryKey(ac);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("获取cookie成功");
				}
				if("该账号未绑定邮箱，请先绑定邮箱".equals(b.errorMsg)){
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(b.errorMsg);
				}
			}
		}
	}
	
	@RequestMapping("/checkEmailLogin")
	public void checkEmailLogin(HttpServletRequest request,HttpServletResponse response,String username,String emailCode) throws Exception{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if(ac!=null){
			BaiDu b=(BaiDu) request.getSession().getAttribute("login-"+username);
			if(b!=null){
				b.checkEmailCode(emailCode);
				if("邮箱验证成功".equals(b.errorMsg)){
					String cookies=JSONObject.toJSON(b.cookieStore.getCookies()).toString();
					ac.setLoginCookie(cookies);
					accountMapper.updateByPrimaryKey(ac);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("获取cookie成功");
				}
			}
		}
	}
	
	@RequestMapping("getCookie")
	public void getCookie(HttpServletRequest request,HttpServletResponse response,String username) throws IOException{
		response.setCharacterEncoding("UTF-8");
		Account a=accountMapper.findByUsername(username);
		response.getWriter().print(a.getLoginCookie());
	}
	
	
}
