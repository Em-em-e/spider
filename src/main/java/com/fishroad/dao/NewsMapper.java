package com.fishroad.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fishroad.vo.News;

public interface NewsMapper {
    int deleteByPrimaryKey(String url);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String url);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    List<News> getAll();
    
    List<News> queryPage(@Param("limit")int limit,@Param("offset")int offset,
    		@Param("sort")String sort, @Param("order")String order, @Param("news")News news);
    
    int count(@Param("news")News news);
}