package com.fishroad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fishroad.dao.AccountMapper;
import com.fishroad.util.ExcelUtil;
import com.fishroad.vo.Account;

@Controller
public class ExcelImportController {
	
	@Autowired
	private AccountMapper accountMapper;
	
	
	@RequestMapping(value = "/imports")  
    public void imports(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IOException {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        InputStream is=file.getInputStream();
        List<Object> accounts=new ExcelUtil().importDataFromExcel(new Account(), is, fileName);
        System.out.println(accounts.size());
        accountMapper.insertList(accounts);
        
    }  

}
