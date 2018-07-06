package cn.gy.oa.service;

import cn.gy.oa.domain.User;

public interface UserService extends BaseService<User>{

	User getByLoginNameAndPassword(String loginName, String password);

//	List<User> findAll();
//
//	void deleteById(Long id);
//
//	void save(User model);
//
//	User getById(Long id);
	
	
}
