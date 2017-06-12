package com.fishroad.controller;

import java.io.IOException;

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
	
	@RequestMapping("/login")
	public void autoLogin(HttpServletRequest request,HttpServletResponse response,String username) throws IOException{
		response.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if(ac!=null){
			BaiDu b=new BaiDu();
			b.doLogin(ac.getUsername(), ac.getPassword());
			System.out.println(b.errorMsg);
			String cookies=JSONObject.toJSON(b.cookieStore.getCookies()).toString();
	
			if("登录成功".equals(b.errorMsg)){
				ac.setLoginCookie(cookies);
				accountMapper.updateByPrimaryKey(ac);
			}
			response.getWriter().print(cookies);
		}
	}
	
	@RequestMapping("getCookie")
	public void getCookie(HttpServletRequest request,HttpServletResponse response,String username) throws IOException{
		response.setCharacterEncoding("UTF-8");
		Account a=accountMapper.findByUsername(username);
		response.getWriter().print(a.getLoginCookie());
	}
	
	
}
