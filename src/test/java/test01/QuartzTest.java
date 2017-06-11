package test01;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.fishroad.controller.BaiDu;
import com.fishroad.services.NewsServiceImpl;
import com.fishroad.vo.News;

public class QuartzTest {
	public static void main(String[] args) {
		ApplicationContext ap=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
//		SchedulerFactoryBean schedulerFactoryBean = (SchedulerFactoryBean) ap.getBean( "schedulerFactoryBean");  
//        //启动调度器  
//		schedulerFactoryBean.start();
		
		NewsServiceImpl s=(NewsServiceImpl) ap.getBean("newsService");
		
		News n=new News();
		n.setUrl("aaaa");
//		n.setCmtCount(24);
		
		s.saveOrUpdate(n);
		
	}
	
	@Test
	public void tt(){
		String a="http://news.163.com/sa/0227/15/AJFKS12P00264M4F";
		System.out.println(a.matches("(http://news\\.163\\.com(/\\w{3,}/|/\\D{2}/|(/16/|/17/))[/\\w*]+)"));
		System.out.println(a.matches("(http://news\\.163\\.com(/\\d{2}/)[/\\w*]+)"));
	}
	
	@Test
	public void cookieTest(){
		BaiDu b=new BaiDu();
		b.doLogin("18321830773", "ll920521609219", null);
//		http://baijiahao.baidu.com/
		CookieStore cookieStore=b.cookieStore;
		String cookieString="";
    	cookieString+=JSONObject.toJSONString(cookieStore.getCookies());
    	System.out.println(cookieString);
    	String a=JSONObject.toJSON(cookieStore).toString();
    	System.out.println(a);
    	List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36"));
        HttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore)
                .setDefaultHeaders(headers).build();
        HttpGet get = new HttpGet();
        HttpResponse res = null;
        try {
			get.setURI(new URI("http://pan.baidu.com/"));
			res=client.execute(get);
			
			 String string = EntityUtils.toString(res.getEntity());
		     System.out.println(string);
		     String co=JSONObject.toJSONString(cookieStore.getCookies());
		     List<Object> coo=(List<Object>) JSONObject.parse(co);
		     for(int i=0;i<coo.size();i++){
		    	 BasicClientCookie k=(BasicClientCookie) coo.get(i);
		    	 k.toString();
		     }
		     System.out.println(cookieStore.getCookies().get(0).getClass().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
