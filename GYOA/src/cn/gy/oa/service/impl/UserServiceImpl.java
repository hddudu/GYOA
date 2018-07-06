package cn.gy.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gy.oa.domain.User;
import cn.gy.oa.service.UserService;

@Service

//@Repository
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	public User getByLoginNameAndPassword(String loginName, String password) {
		
		return (User) getSession()
				.createQuery("FROM User u where u.loginName= ? AND u.password= ?")
				.setParameter(0, loginName)
				.setParameter(1, DigestUtils.md5Hex(password))
				.uniqueResult();
	}
	
}
