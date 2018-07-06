package cn.gy.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.gy.oa.domain.Forum;

/**
 * l论坛管理action
 * @author dudu
 *
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String list() throws Exception{
		List<Forum> forumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		forumManageService.delete(model.getId());
		return "delete";
	}
	
	/**
	 * 不需要准备数据
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		return "editUI";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		forumManageService.save(model);//保存信息
		return "toList";
	}
	
	/**
	 * 需要准备数据
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception{
		//准备回显的数据
		Forum forum = forumManageService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);//放入值栈
		return "editUI";
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		//1。取出原对象
		//2。设置要修改的属性
		//3。更新到数据库
		Forum forum = forumManageService.getById(model.getId());
		
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		//
		forumManageService.save(forum);
		return "toList";
	}
	
	/**
	 * 上移动
	 * @return
	 * @throws Exception
	 */
	public String moveUp() throws Exception{
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	
	/**
	 * 下移动
	 * @return
	 * @throws Exception
	 */
	public String moveDown() throws Exception{
		forumManageService.moveDown(model.getId());
		return "toList";
	}
	
}
