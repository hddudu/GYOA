1:		请求数 	地址栏
转发：	1		不变

重定向：    2		变

重定向就是重发发了一次请求！！！
转发是直接跳转页面！！！

要用重定向：需要刷新 增删改的数据！！！
	避免又做一次重复的增删改操作！！！

2：岗位管理：
	列表与删除都是一个请求
	添加和修改都是两个请求
	
3：
作用				方法名					返回值			页面
--------------------------------------------------------------------------------------
列表：			list				  |	list			list.jsp
删除				delete				  |	toList			
添加页面			addUI/addPage/addForm | addUI			addUI.jsp
添加				add					  | toList
修改页面			editUI/editPage		  |	editUI			editUI.jsp
修改				edit				  | toList
--------------------------------------------------------------------------------------
toList的结果配置:
<result name="toList" type="redirectAction">roleAction_list</result>

	

