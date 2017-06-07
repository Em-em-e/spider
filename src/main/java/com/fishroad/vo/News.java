package com.fishroad.vo;

import java.util.Date;

public class News {
    private String url;

    private String title;

    private String news_type;

    private Integer cmt_count;

    private String create_time;

    private String modify_time;

    private Date last_update_time;
    
    private int count;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNews_type() {
		return news_type;
	}

	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}

	public Integer getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(Integer cmt_count) {
		this.cmt_count = cmt_count;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}

   
}