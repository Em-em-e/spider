package com.fishroad.util;

import com.fishroad.spider.MyProcessor;
import com.fishroad.spider.MySQLPieline;

import us.codecraft.webmagic.Spider;


public class MyProcesserFactory {


    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static Spider instance;   

    // 定义一个静态的方法（调用时再初始化SingletonTest，使用synchronized 避免多线程访问时，可能造成重的复初始化问题）
    public static synchronized  Spider getInstance() {   
        if (instance == null)   
            instance = Spider.create(new MyProcessor()).addUrl("http://news.163.com/").addPipeline(new MySQLPieline());   
        return instance;   
    }
    
    public static synchronized Spider createNewInstance(){
    	return Spider.create(new MyProcessor()).addUrl("http://news.163.com/").addPipeline(new MySQLPieline());
    }
} 
