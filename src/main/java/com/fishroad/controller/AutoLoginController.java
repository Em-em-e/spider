package com.fishroad.controller;

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
	public void autoLogin(){
		
		Account ac=accountMapper.selectByPrimaryKey(1);
		
	}
}
