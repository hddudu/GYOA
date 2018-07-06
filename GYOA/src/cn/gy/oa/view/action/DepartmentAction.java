package cn.gy.oa.view.action;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.gy.oa.domain.Department;
import cn.gy.oa.domain.User;
import cn.gy.oa.service.DepartmentService;
import cn.gy.oa.utils.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope(value="prototype")
public class DepartmentAction extends BaseAction<Department> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long parentId;
	
	//6个请求 6个方法
	/**列表**/
	public String list()throws Exception {
//		List<Department> departList = departmentService.findAll();
		List<Department> departList = null;
		if(parentId == null) { //查询所有顶级部门的列表
			departList = departmentService.findTopList();
		} else {//查询子部门列表
			departList = departmentService.findChildrenList(parentId);
			//准备parent的Department对象
			Department parent = departmentService.getById(parentId);//当前id：5
			ActionContext.getContext().put("parent", parent);
		}
//		ActionContext.getContext().getValueStack().push(departList);//放到栈顶
		if (departList != null && departList.size() > 0)
			ActionContext.getContext().put("departmentList", departList);
		else
			System.out.println("查出的值为空,不会吧???问题在哪里");// 查出的值为空,不会吧???问题在哪里
		return "list";
	}
	/**删除**/
	public String delete()throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}
	/**添加:不希望直接跳到顶级了，咋办呢？还是跳到当前查询也面
	 * ①不选择重定向==>重定向是可以的，只是传值必须得改变l 
	 * 	必须传parentId
	 * ②保村当前页面的查询传入参数，继续查询
	 * 
	 * **/
	public String add()throws Exception {
		Date date = new Date();
		Timestamp curDate1 = new Timestamp(date.getTime());
		model.setCrtDate(curDate1);
		//添加父亲id
		if(parentId != null)
			model.setParent(departmentService.getById(parentId));//存起来，设置到模型实体中
		departmentService.save(model);
		return "toList";//重定向到主页面
	}
	/**添加页面
	 * 	在action部分处理，然后再页面进行显示
	 * 
	 * **/
	public String addUI()throws Exception {
//		List<Department> departmentList = departmentService.findAll();//部门还要求是树状结构呢
//		List<Department> departmentList = showTree(topList);
		List<Department> topList = departmentService.findTopList();//寻找顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		
//		for(Department d : departmentList) 
//			System.out.println(d.getDeptName() + ":" + d.getId());
		ActionContext.getContext().put("departmentList", departmentList);//将其放入到map中，在页面可以获取
		if(parentId != null)
			model.setId(parentId);//设置往页面传参属性值
		return "addUI";
	}
	/**
	 * @param topList
	 * @return
	 */
	private List<Department> showTree(Collection<Department> topList) {
		for(Department dept : topList) {
			dept.setDeptName("  ┣" + dept.getDeptName());
			showTree(dept.getDepartments());
		}
		return (List<Department>) topList;
	}
	/**修改 : 获取原来的对象，然后设置新的值，再进行更新**/
	public String edit()throws Exception {
		Department depart = departmentService.getById(model.getId());
		Date date = new Date();
		Timestamp updDate = new Timestamp(date.getTime());
		depart.setUpdDate(updDate);
		depart.setDeptName(model.getDeptName());
		depart.setDeptDesc(model.getDeptDesc());
		//添加父亲id
		if(parentId != null)//防止空指针
			depart.setParent(departmentService.getById(parentId));//
		departmentService.update(depart);
		return "toList";
	}
	/**修改页面**/
	public String editUI()throws Exception {
		//准备回显的对象
//		List<Department> departmentList = departmentService.findAll();//部门还要求是树状结构呢
		List<Department> topList = departmentService.findTopList();//寻找顶级部门
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);//将其放入到map中，在页面可以获取
		//下面就是回显的信息==》案例元模型是有值的,但是它的值是Parent（不是具体的id值）
		Department depart = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(depart);//将值放置到顶部
		//parentId==>对应显示页面的默认值
		if(depart.getParent() != null) {
			parentId = depart.getParent().getId();
		}
		return "editUI";
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


}
