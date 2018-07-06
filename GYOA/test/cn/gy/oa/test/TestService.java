package cn.gy.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gy.oa.domain.User;
@Service("testService")
public class TestService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional//开启事务
	public void saveTwoUsers(){
		//因为测试,所以事务没人管 采用当前的session
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
//		int a = 1 / 0;//这行会抛出异常
		session.save(new User());
	}
	
}
