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
//		BaiDu b=(BaiDu) ProtostuffUtil.deserializeProtoStuffDataListToProductsObject(ac.getRemark1(), BaiDu.class);
		BaiDu b=(BaiDu) request.getServletContext().getAttribute("login-"+username);
		if(b==null){
			b=new BaiDu(ac.getUsername(), ac.getPassword());
			b.login();
		}
		
		OutputStream os=response.getOutputStream();
		b.getCode(os);
//		ac.setRemark1(ProtostuffUtil.serializeProtoStuffObject(b, b.getClass()));
		request.getServletContext().setAttribute("login-"+username, b);
		accountMapper.updateByPrimaryKey(ac);
		response.getOutputStream().flush();
	}
	
	@RequestMapping("/login")
	public void autoLogin(HttpServletRequest request,HttpServletResponse response,String username,String verifycode) throws Exception{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if(ac!=null){
//			BaiDu b=(BaiDu) ProtostuffUtil.deserializeProtoStuffDataListToProductsObject(ac.getRemark1(), BaiDu.class);
			BaiDu b=(BaiDu) request.getServletContext().getAttribute("login-"+username);
			if(b!=null){
				b.volidate(verifycode);//验证码校验
				
				System.out.println(b.errorMsg);
				String cookies=JSONObject.toJSON(b.cookieStore.getCookies()).toString();
				if("获取cookie成功".equals(b.errorMsg)){
					ac.setLoginCookie(cookies);
//					ac.setRemark1(ProtostuffUtil.serializeProtoStuffObject(b, b.getClass()));
					request.getServletContext().setAttribute("login-"+username, b);
					accountMapper.updateByPrimaryKey(ac);
				}
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(b.errorMsg);
			}
		}
	}
	
	@RequestMapping("/checkEmailLogin")
	public void checkEmailLogin(HttpServletRequest request,HttpServletResponse response,String username,String emailCode) throws Exception{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if(ac!=null){
//			BaiDu b=(BaiDu)ProtostuffUtil.deserializeProtoStuffDataListToProductsObject(ac.getRemark1(), BaiDu.class);
			BaiDu b=(BaiDu) request.getServletContext().getAttribute("login-"+username);
			if(b!=null){
				b.checkEmailCode(emailCode);
				if("邮箱验证成功".equals(b.errorMsg)){
					String cookies=JSONObject.toJSON(b.cookieStore.getCookies()).toString();
					ac.setLoginCookie(cookies);
//					ac.setRemark1(ProtostuffUtil.serializeProtoStuffObject(b, b.getClass()));
					request.getServletContext().setAttribute("login-"+username, b);
					accountMapper.updateByPrimaryKey(ac);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("获取cookie成功");
				}
			}
		}
	}
	@RequestMapping("/checkCookie")
	public void cookieCheck(HttpServletRequest request,HttpServletResponse response,String username) throws Exception{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
//		CookieStore c=new BasicCookieStore();
//		JSONArray cookies=JSONObject.parseArray(ac.getLoginCookie());
//		for(int i=0;i<cookies.size();i++){
//			JSONObject cookie=(JSONObject) cookies.get(i);
//			if(!"BAIDUID".equals(cookie.getString("name"))){
//				BasicClientCookie co=new BasicClientCookie(cookie.getString("name"),cookie.getString("value"));
//				co.setDomain(cookie.getString("domain"));
//				co.setPath(cookie.getString("path"));
//				co.setExpiryDate(cookie.getDate("expiryDate"));
//				co.setSecure(cookie.getBooleanValue("secure"));
//				co.setVersion(cookie.getIntValue("version"));
//				c.addCookie(co);
//				System.out.println(co.toString());
//			}
//		}
//		BaiDu b=(BaiDu)ProtostuffUtil.deserializeProtoStuffDataListToProductsObject(ac.getRemark1(), BaiDu.class);
//		BaiDu b=new BaiDu(username,ac.getPassword(),c);
		BaiDu b=(BaiDu) request.getServletContext().getAttribute("login-"+username);
		if(b!=null)
		if(b.checkCookie()){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
	}
	
	@RequestMapping("getCookie")
	public void getCookie(HttpServletRequest request,HttpServletResponse response,String username) throws IOException{
		response.setCharacterEncoding("UTF-8");
		Account a=accountMapper.findByUsername(username);
		response.getWriter().print(a.getLoginCookie());
	}
	
	
	
}
