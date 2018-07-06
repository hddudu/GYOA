package cn.gy.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	//获取spring对象管理
	
	private ApplicationContext 	ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private Log log = LogFactory.getLog(SpringTest.class);
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testLog() throws Exception{
		log.debug("这个是debug信息");
		log.error("这个是错误信息");
		log.fatal("严重错误信息");
		log.info("基本信息");
		log.warn("这是警告信息");
	}
	
	//测试sessionfactory
	@Test
	public void testSessionFactory() throws Exception{
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println("sessionFactory:"+sessionFactory);
		//sessionFactory:org.hibernate.impl.SessionFactoryImpl@7e521366
		//当打开了mysql的服务之后就正确了
		
		System.out.println("测试建表！");
	}
	//测试事务
	@Test
	public void testTransaction() throws Exception{
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveTwoUsers();
		
	}
	
	/**
	 * 输出日志信息到问价
	 * @param message
	 * 测试事务
	 */
	@SuppressWarnings("unused")
	private static void logToFile(String message){
		System.out.println("保存两个用户!");
		System.out.println(message);
	}
	
}
