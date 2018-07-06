package cn.gy.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.gy.oa.dao.UserDao;
import cn.gy.oa.domain.User;

/**
 * 用户实现类
 * @author dudu
 *
 */
@Repository
public class UserDaoImple extends BaseDaoImpl<User> implements UserDao {
	
	/**
	 * 解决 传参为Class类对象的问题
	 */
//	public UserDaoImple(){
//		clazz = User.class;
//	}
}
