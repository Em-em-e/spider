package com.fishroad.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fishroad.services.IUserService;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.spider.MyProcessor;
import com.fishroad.vo.News;
import com.fishroad.vo.User;

import us.codecraft.webmagic.Spider;


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
	@RequestMapping("/startJob")
	public void startJob(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		MyProcessor p=new MyProcessor();
		
		long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider sp=Spider.create(p).addUrl("http://news.163.com/").thread(50);
        sp.run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+MyProcessor.getCount()+"条记录");
		response.getWriter().println("sss");
	}

}
