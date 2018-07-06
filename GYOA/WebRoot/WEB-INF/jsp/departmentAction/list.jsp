<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>部门列表</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath }/style/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">部门名称</td>
				<td width="150px">上级部门名称</td>
				<td width="200px">职能说明</td>
				<td>相关操作</td>
            </tr>
        </thead>
		<!--显示数据列表-->
       <tbody id="TableData" class="dataContainer" datakey="departmentList">
	        <s:iterator value="#departmentList"><!-- 不加#号只能取到一行的值 -->
				<tr class="TableDetail1 template">
					<%-- <td><a href="_list_level2.html">${department.name}</a>&nbsp;</td> --%>
					<td>
						<%-- <s:a action="departmentAction_list?parentId=${id }">${deptName}</s:a>&nbsp; --%>
						<%-- <s:action name="departmentAction_list?parentId=${id }">${deptName} </s:action> &nbsp; --%>
						<a href="departmentAction_list.action?parentId=${id }">${deptName}</a>  
					</td>
					<td>${parent.deptName} &nbsp;</td>
					<td>${deptDesc} &nbsp;</td>
					<<%-- td>${id} &nbsp;</td> --%>
					
					<td>
						<s:if test="#session.user.hasPrivilegeByName('部门修改')">
							<a href="departmentAction_editUI.action?id=${id }">修改</a>        
						</s:if>
						<s:a></s:a>
						<s:if test="#session.user.hasPrivilegeByName('部门删除')">
  						<a href="departmentAction_delete.action?id=${id }&parentId=${parent.id}" onclick="return window.confirm('您确定要删除吗？')" >删除返回本页</a>
  						<a href="departmentAction_delete.action?id=${id }&parentId=${parent.parent.id}" onclick="return window.confirm('您确定要删除吗？')" >删除返回上一页</a>
  						</s:if>
  						
  						<%-- <oa:a action="departmentAction_editUI.action?id=${id }" >修改</oa:a> --%>
<!--   						<oa:a action="" privilegeUrl="departmentAction_edit">修改</oa:a> -->
  						&nbsp;
  						
  						<!-- 
						<a onClick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')" href="#">删除</a>
						<a href="saveUI.html">修改</a> -->
					</td>
				</tr>
			</s:iterator>
        </tbody>
        
         <%-- <tbody id="TableData" class="dataContainer" datakey="departmentList">
        	<s:iterator value="#departmentList">
				<tr class="TableDetail1 template">
					<td><a href="_list_level2.html">${department.name}</a>&nbsp;</td>
					<td>${deptName} &nbsp;</td>
					<td>${deptDesc} &nbsp;</td>
					<td>
						<a href="roleAction_editUI.action?id=${id }" onclick="return confirm('确定要修改吗?')">修改</a>        
  						<a href="roleAction_delete.action?id=${id }" onclick="return window.confirm('您确定要删除吗？')" >删除</a>
  						<a href="setPrivilegeUI.html">设置权限</a><br/>
					</td>
				</tr>
			</s:iterator>
        </tbody> --%>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <%-- <a href="saveUI.html"><img src="${pageContext.request.contextPath }/style/images/createNew.png" /></a> --%>
            <!-- 当前页面已经传过来了parentID -->
            <s:if test="#session.user.hasPrivilegeByName('部门添加')">
	            <a href="departmentAction_addUI.action?parentId=${parentId }"><img src="${pageContext.request.contextPath }/style/images/createNew.png" /></a>
	            <!-- 顶级不需要返回上一级 -->
	            
	            <s:if test="%{parentId!=null}">
		             <a href="departmentAction_list.action?parentId=${parent.parent.id }">返回上一级</a>
		             <!-- el表达式支持运行时的值获取以及判断 -->
		             <c:if test="${parent.parent.id != null}">
		            	<a href="departmentAction_list.action?parentId=${parent.parent.parent.id }">返回上两级</a>
		            </c:if>
	            </s:if>
            </s:if>
            <!-- id是Department的属性==》parent.parent==》还是Department:假如当前的是parent==》它的上级：parent = getById(parentId) -->
            <!-- #表示从map
            里面获取值 -->
            <!-- $表示直接从值栈中获取数据 -->
        </div>
    </div>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
</div>

</body>
</html>
