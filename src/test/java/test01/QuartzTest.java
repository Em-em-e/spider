package test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzTest {
	public static void main(String[] args) {
		ApplicationContext ap=new ClassPathXmlApplicationContext("classpath:spring-quartz.xml");
		SchedulerFactoryBean schedulerFactoryBean = (SchedulerFactoryBean) ap.getBean( "schedulerFactoryBean");  
        //启动调度器  
		schedulerFactoryBean.start();
	}
}
