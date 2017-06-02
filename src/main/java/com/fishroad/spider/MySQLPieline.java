package com.fishroad.spider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.vo.News;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MySQLPieline implements Pipeline{
	
	@Autowired
	private NewsServiceImpl newsServiceImpl;

	@Override
	public void process(ResultItems item, Task task) {
		JSONObject o=item.get("item");
		News n=new News();
    	n.setTitle(o.getString("title"));
    	n.setFirstTime(new Date());
    	n.setNewsType(o.getString("boardId"));
    	n.setUrl(o.getString("url"));
    	n.setCmtCount(o.getIntValue("cmtAgainst")+o.getIntValue("cmtVote")+o.getIntValue("rcount"));
    	newsServiceImpl.saveNews(n);
	}

}
