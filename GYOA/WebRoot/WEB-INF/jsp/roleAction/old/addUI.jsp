<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>		
  		<s:form action="roleAction_add">
  			<s:textfield name="roleName" cssClass="" cssStyle="" label="名称"></s:textfield>
  			<s:textarea name="roleDesc" label="说明"></s:textarea>
  			<s:submit value="提交"></s:submit>
  		</s:form>
  
  </body>
</html>
