package cn.gy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gy.oa.domain.Role;
import cn.gy.oa.service.RoleService;

@Service
//@Transactional//在业务层开启事务:事务还是在xml写比较好
//@Repository
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	public List<Role> findNotAll(Long[] ids) {
		if(ids == null) {
			return findAll();
		}
		List<Role> roleList = getSession().createQuery("FROM " + clazz.getSimpleName() + " where id not IN(:ids)")
		.setParameterList("ids", ids).list();
		return roleList;
	}
	
}
