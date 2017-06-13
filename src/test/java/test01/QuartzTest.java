package test01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	public void cookieTest() throws Exception{
		BaiDu b=new BaiDu("qffox2oez15900@163.com","mybj2017");
		
		b.login();
		
		b.checkEmailCode();
		
	}
}
