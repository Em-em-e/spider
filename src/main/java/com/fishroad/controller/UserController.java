package com.fishroad.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.services.IUserService;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.vo.News;
import com.fishroad.vo.User;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Resource
	private NewsServiceImpl newsServiceImpl;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		//int userId = Integer.parseInt(request.getParameter("id"));
		int userId = 0;
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		
		News ne=new News();
		ne.setFirstTime(new Date());ne.setCmtCount(23);ne.setTitle("sdffff");
		newsServiceImpl.saveNews(ne);
		schedulerFactoryBean.stop();
		return "showUser";
	}
	@RequestMapping("/newsList")
	public String startJob(HttpServletRequest request){
		System.out.println("aa");
		return "newsList";
	}
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<News> li=newsServiceImpl.getAll();
		Object re=JSONObject.toJSON(li);
		response.getWriter().print(re.toString());
	}

}
