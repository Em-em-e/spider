package com.fishroad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.dao.AccountMapper;
import com.fishroad.util.ExcelUtil;
import com.fishroad.vo.Account;
import com.fishroad.vo.News;

@Controller
public class AccountController {
	
	@Autowired
	private AccountMapper accountMapper;
	
	
	@RequestMapping(value = "/imports")  
    public void imports(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException {  
  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        InputStream is=file.getInputStream();
        List<Object> accounts=new ExcelUtil().importDataFromExcel(new Account(), is, fileName);
        System.out.println(accounts.size());
        accountMapper.insertList(accounts);
        response.getWriter().print("<script>alert('导入成功！');history.go(-1);</script>");
        
    }
	
	@RequestMapping("account.json")
	public void list(HttpServletRequest request,HttpServletResponse response,int limit,int offset,Account ac,
			String sort,String order) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Account> li=accountMapper.queryPage(limit, offset, sort,order, ac);
		
		int total=accountMapper.count(ac);
		int page=total%limit==0?total/limit:(total/limit+1);
		Object o=JSONObject.toJSON(li);
		
		response.getWriter().print("{\"total\":" + total +",\"page\":"+page+ ",\"rows\":"+o.toString()+"}");
	}
	
	@RequestMapping("/sendFinish")
	public void sendFinish(HttpServletRequest request,HttpServletResponse response,String username) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Account ac=accountMapper.findByUsername(username);
		if("ok".equals(ac.getRemark2())){
			ac.setRemark2("");
		}else{
			ac.setRemark2("ok");
		}
		accountMapper.updateByPrimaryKeySelective(ac);
		response.getWriter().print("ok");
	}

}
