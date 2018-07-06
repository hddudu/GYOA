<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
<HTML>
<HEAD>
    <META http-equiv=Content-Type CONTENT="text/html; charset=UTF-8" />
	<TITLE>GYOA</TITLE>
	<LINK HREF="${pageContext.request.contextPath }/style/blue/login.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();//获取焦点（光标）
		});
	if(window.parent != window){//在被嵌套时才刷新上级窗口
		window.parent.location.reload();//刷新当前窗口
	}
	/*
	
	alert(window.parent);
	alert(window.parent == window);
	*/
	</script>
</HEAD>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >
<!-- 获取焦点元素 -->
<s:form action="userAction_login" focusElement="loginNameInput">
    <div ID="CenterAreaBg">
        <div ID="CenterArea">
            <div ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath }/style/blue/images/logo.png" /></DIV>
            <div ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
                			<font color="red"><s:fielderror/></font>
                		</td>
                	</tr>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath }/style/blue/images/login/userId.gif" /></TD>
                        <TD>
                        	<!-- <INPUT SIZE="20" CLASS="TextField" TYPE="text" NAME="loginName" /> -->
                        	<s:textfield name="loginName" cssClass="TextField required"  size="20" tabindex="1"  id="loginNameInput"></s:textfield>
                        </TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;"><INPUT TYPE="image" SRC="${pageContext.request.contextPath }/style/blue/images/login/userLogin_button.gif"/></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath }/style/blue/images/login/password.gif" /></TD>
                        <TD>
                        	<!-- <INPUT SIZE="20" CLASS="TextField" TYPE="password" NAME="password" /> -->
                        	<s:password name="password" cssClass="TextField required" size="20" tabindex="2" showPassword="false"></s:password>
                        </TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2010 版权所有 itcast</A></DIV>
        </DIV>
    </DIV>
</s:form>
</body>
</HTML>