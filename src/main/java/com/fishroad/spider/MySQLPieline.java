package com.fishroad.spider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.vo.News;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MySQLPieline implements Pipeline{
	
	@Autowired
	private NewsServiceImpl newsServiceImpl;
	
	public MySQLPieline()
	{
	   this.newsServiceImpl = ((NewsServiceImpl)new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml").getBean("newsService"));
	}

	@Override
	public void process(ResultItems item, Task task) {
		JSONObject o=item.get("item");
		if(o!=null){
			News n=new News();
	    	n.setTitle(o.getString("title"));
	    	n.setNews_type(o.getString("boardId"));
	    	n.setUrl(o.getString("url"));
	    	n.setLast_update_time(new Date());
	    	n.setCreate_time(o.getString("createTime"));
	    	n.setModify_time(o.getString("modifyTime"));
	    	n.setCmt_count(o.getIntValue("cmtAgainst")+o.getIntValue("cmtVote")+o.getIntValue("rcount"));
	    	newsServiceImpl.saveNews(n);
		}
	}

}
