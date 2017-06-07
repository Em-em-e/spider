package com.fishroad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fishroad.dao.NewsMapper;
import com.fishroad.vo.News;

@Service("newsService")
public class NewsServiceImpl {
	
	@Autowired
	private NewsMapper newsMapper;
	
	public void saveNews(News news){
		newsMapper.insert(news);
	}

	public List<News> getAll() {
		return newsMapper.getAll();
	}
	
	public void saveOrUpdate(News news){
		News n=newsMapper.selectByPrimaryKey(news.getUrl());
		if(n==null){
			newsMapper.insert(news);
		}else{
			newsMapper.updateByPrimaryKey(news);
		}
	}
	
	public List<News> queryPage(int offset,int limit,String sort, String order, News news){
		
		return newsMapper.queryPage(limit, offset*limit, sort,order,news);
	}
	
	public int count(News news){
		return newsMapper.count(news);
	}
}
