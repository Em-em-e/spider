package com.fishroad.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.dao.SysParamMapper;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.util.MyProcesserFactory;
import com.fishroad.vo.News;
import com.fishroad.vo.StatusModel;
import com.fishroad.vo.SysParam;

import us.codecraft.webmagic.Spider;


@Controller
public class UserController {
	
	@Resource
	private NewsServiceImpl newsServiceImpl;
	@Autowired
	private SysParamMapper sysParamMapper;
	
	@RequestMapping("/index.html")
	public String startJob(HttpServletRequest request,Model model){
		SysParam sp=sysParamMapper.selectByPrimaryKey(1);
		model.addAttribute("incomeRate", sp.getParamValue());
		return "index";
	}
	
	@RequestMapping("list.json")
	public void list(HttpServletRequest request,HttpServletResponse response,int limit,int offset,News news,
			String sort,String order) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<News> li=newsServiceImpl.queryPage(offset, limit,sort,order, news);
		
		int total=newsServiceImpl.count(news);
		int page=total%limit==0?total/limit:(total/limit+1);
		Object o=JSONObject.toJSON(li);
		
		response.getWriter().print("{\"total\":" + total +",\"page\":"+page+ ",\"rows\":"+o.toString()+"}");
	}
	
	@RequestMapping("/start")
	public void list() throws IOException{
//		List<News> li=newsServiceImpl.getAll();
//		Object re=JSONObject.toJSON(li);
//		response.getWriter().print(re.toString());
		
        System.out.println("开始爬取...");
		Spider sp=MyProcesserFactory.getInstance();
		if(Spider.Status.Stopped.toString().equals(sp.getStatus().toString())
				||Spider.Status.Init.toString().equals(sp.getStatus().toString())){
			sp.thread(50).start();
		}
	}
	
	@RequestMapping("/stop")
	public void stopSpider(HttpServletRequest request,HttpServletResponse response){
		Spider sp=MyProcesserFactory.getInstance();
		if(Spider.Status.Running.toString().equals(sp.getStatus().toString()))
			sp.stop();
	}
	
	@RequestMapping("/status")
	public void spiderStatus(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		
		StatusModel mo=new StatusModel();
		Spider sp=MyProcesserFactory.getInstance();
		
//		SystemInfo cpu = new SystemInfo();
		
//		Runtime lRuntime = Runtime.getRuntime();
		
//		mo.setCpuUsage(cpu.getCpuUsage());
//		mo.setMemUsage(cpu.getMemUsage());
//		mo.setFreeMemory(lRuntime.freeMemory()/1024);
//		mo.setMaxMemory(lRuntime.maxMemory());
//		mo.setTotalMemory(lRuntime.totalMemory());
//		mo.setAvailableProcessors(lRuntime.availableProcessors());
		
		mo.setCpuUsage("86");
		mo.setMemUsage("1020");
		mo.setAvailableProcessors(42);
		mo.setFreeMemory(20150/1024);
		mo.setSpiderStatus(sp.getStatus().toString());
		mo.setThreadAlive(sp.getThreadAlive());
		mo.setPageCount(sp.getPageCount());
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(sp.getStartTime()!=null)
			mo.setStartTime(s.format(sp.getStartTime()));
		
		Object o=JSONObject.toJSON(mo);
		
		response.getWriter().print(o.toString());
	}
		
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<News> li=newsServiceImpl.getAll();
		Object re=JSONObject.toJSON(li);
		response.getWriter().print(re.toString());
	}
	
	@RequestMapping("/getRate")
	public void getRate(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SysParam sp=sysParamMapper.selectByPrimaryKey(1);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(sp.getParamValue());
	}
	@RequestMapping("/updateRate")
	public void updateRate(HttpServletRequest request,HttpServletResponse response,String incomeRate) throws IOException{
		response.setCharacterEncoding("UTF-8");
		if(incomeRate!=null && incomeRate!=""){
			try {
				Float.parseFloat(incomeRate);
			} catch (Exception e) {
				response.getWriter().print("请输入合法的数字");
				return;
			}
			SysParam s=new SysParam();
			s.setId(1);s.setParamName("rate");s.setParamValue(incomeRate);
			sysParamMapper.updateByPrimaryKeySelective(s);
			response.getWriter().print("修改成功！");
		}else{
			response.getWriter().print("请输入一个有效的数字！");
		}
	}

}
