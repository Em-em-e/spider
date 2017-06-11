package com.fishroad.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fishroad.dao.AccountMapper;
import com.fishroad.vo.Account;

@Controller
@RequestMapping("/auto")
public class AutoLoginController {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@RequestMapping("/login")
	public void autoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		
		Account ac=accountMapper.selectByPrimaryKey(1);
		
		BaiDu b=new BaiDu();
		b.doLogin("18321830773", "ll920521609219", null);
		
		for(int i=0;i<b.cookieStore.getCookies().size();i++){
			BasicClientCookie bc=(BasicClientCookie) b.cookieStore.getCookies().get(i);
			Cookie c=new Cookie(bc.getName(), bc.getValue());
			c.setPath(bc.getPath());
			c.setMaxAge(60*60*24);
			c.setDomain(bc.getDomain());
			response.addCookie(c);
		}
		
		response.getWriter().print(b.respString);
	}
}
