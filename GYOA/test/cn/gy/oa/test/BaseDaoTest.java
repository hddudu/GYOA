package cn.gy.oa.test;

import org.junit.Test;

import cn.gy.oa.dao.RoleDao;
import cn.gy.oa.dao.UserDao;
import cn.gy.oa.dao.impl.RoleDaoImpl;
import cn.gy.oa.dao.impl.UserDaoImple;

public class BaseDaoTest {
	@Test
	public void testGetById(){
		UserDao userDao = new UserDaoImple();
		RoleDao roleDao = new RoleDaoImpl();
	}
}
