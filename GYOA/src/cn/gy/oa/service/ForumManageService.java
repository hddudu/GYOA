package cn.gy.oa.service;

import cn.gy.oa.domain.Forum;

public interface ForumManageService extends BaseService<Forum>{

	void moveUp(long id);

	void moveDown(long id);

}
