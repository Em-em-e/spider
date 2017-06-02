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
}
