package com.fishroad.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fishroad.dao.SysParamMapper;
import com.fishroad.vo.SysParam;

@Controller
public class MainController {
	@Autowired
	private SysParamMapper sysParamMapper;
	
	@RequestMapping("/index")
	public String startJob(HttpServletRequest request,Model model){
		SysParam sp=sysParamMapper.selectByPrimaryKey(1);
		model.addAttribute("incomeRate", sp.getParamValue());
		return "index";
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
