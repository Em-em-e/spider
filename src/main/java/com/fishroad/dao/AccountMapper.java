package com.fishroad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fishroad.vo.Account;
import com.fishroad.vo.News;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    int insertList(List<Object> accounts);

	List<Account> queryPage(@Param("limit")int limit,@Param("offset")int offset,
    		@Param("sort")String sort, @Param("order")String order,@Param("ac") Account ac);
	
	int count(@Param("ac")Account ac);
}