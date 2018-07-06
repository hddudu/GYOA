<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>			<!-- map里面的值直接用#号声明 -->
  heloword<br/>
  <%--
  	<s:iterator value="#roleList" >
  		<s:property value="id"/>
  		<s:property value="roleName"/>
  		<s:property value="roleDesc"/>
  		<a href="roleAction_delete.action?id=<s:property value='id'/>" onclick="return confirm('确定要删除吗?')" >删除</a><br/>
  	</s:iterator>
   <a href="roleAction_delete?id=<s:property value='id'/>">删除</a> 
   --%>
   	<s:iterator value="#roleList" >
   		${id },${roleName },${roleDesc }
   		<a href="roleAction_editUI.action?id=${id }" onclick="return confirm('确定要修改吗?')">修改</a>        
  		 <a href="roleAction_delete.action?id=%{id }" onclick="return confirm('确定要删除吗?')" >删除</a><br/>
  		<%-- <s:a action="roleAction_delete">
			<s:param name="id" value="%{id}"></s:param>  		
			删除
  		</s:a> --%>
  		<%--
这个写法必须要更新原因是struts2的包和xwork的包的版本不同
我用的是
xwork-2.0.4.jar
struts2-core-2.1.8.1.jar <s:a action="roleAction_delete?id=%{id}">删除</s:a><br/> --%>
  	</s:iterator>
  	<a href="roleAction_addUI.action">添加</a><br/>
  </body>
</html>
