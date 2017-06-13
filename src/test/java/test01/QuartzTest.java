package test01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Matcher ma=Pattern.compile("authtoken=.+&").matcher("secstate=PA000&gotourl=&authtoken=fd3eAQvd95iktCXwkb6xqPdZJwPE0SCTY8diMGjo1dOPfJia4g87Yxjv9%2FCV8grQmaHXddF%2FZTQTIrkjaDMhx%2BE1hv1JuaEksd4sMMFT%2BVTJ4TyOB%2FQeA%2BLaTq0vJh10kB%2BC051KfW9SLOoe0KC1Kc%2FI3jrWU9pH8jqf%2BHYA1kncf5iiBwzFUleayG5uiULgPkHKUaasT9TbUfSM52l%2FuY1mynmmfGU0zJKMqGP3xTuIkAk1DjO1OUsQY0kliKXvm%2B7PauHu8LsNIjf5CoKjXvk0sQfEvC4WZh13%2B46bj1ol%2BUC4MntxGOWFh0CDpkwjBlrcAMMdpIG%2BgX%2FyphyPh%2F9t4pEPag&loginproxy=https://passport.baidu.com/v2/%3Floginproxy%26u%3Dhttp%253A%252F%252Fbaijiahao.baidu.com%252F%26tpl%3Dmn%26ltok");
		System.out.println(ma.find());
		System.out.println(ma.group().substring(0, ma.group().length()-1));
	}
}
