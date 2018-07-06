<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>配置权限</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
    <script type="text/javascript">
    	//初始化全选是否勾上
    /*
    $(document).ready(function() { 
    	var flag = false;
    	//
    	$("[name=privilegeIds]").each(function(){
    		if(this.checked == true) {
    			flag = true;
    			return false;//表示跳出本次循环
    		}
    	});
    	if(flag == true) {
    		$("#selectAll").attr('checked',true);
    	}
    	if(flag == false) {
    		$("#selectAll").attr('checked',false);
    		$("#selectAllNot").attr('checked',true);
    	}
    	//$("#root").treeview();//初始化树状结构
    });
    */
   $(function() {
   		////给所有的权限复选框添加事件
    	$("[name=privilegeIds]").click(function(){
    		//自己选中或者取消时，把所有的下级权限也都同时选中或者取消
    		//$(this)==》就变成了JQuery对象==>this表示原始对象
    		//$(this).siblings("ul").find("[type=checkbox]").attr('checked',this.checked);//this.checked=true 或者 this.checked=false
    		$(this).siblings("ul").find("input").attr('checked',this.checked);//this.checked=true 或者 this.checked=false
    		//自己选中时，将自己的上级权限全部选中
    		if(this.checked){
    			//$(this).parents().siblings("[name=privilegeIds]").attr('checked',this.checked);//
    			$(this).parents("li").children("input").attr('checked',this.checked);//
    		} else {
    			//当取消一个权限时，就是不选中一个复选框的时候，要判断是否要取消祖父、父亲级权限
    			//如果同级没有选中的时候，就取消他的上级权限；
    			//再向上也这样做 :匹配所有的选中项目
    			if($(this).parent().siblings("li").children("input:checked").size() == 0) {//当同级无选中时==>选中的size为0
    				$(this).parent().parent().siblings("input").attr("checked",false);
    				//转换出发点==>使用递归对象进行操作
    				var start = $(this).parent().parent().siblings("input");
    				if(start.parent().siblings("li").children("input:checked").size() == 0){
    					start.parent().parent().siblings("input").attr("checked",false);
    				}
    			}
    		}
    	});
    });
    
    
    
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath }/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="roleAction_setPrivilege">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath }/style/blue/images/item_point.gif" /> 正在为【${role.roleName }】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<!-- 全选 : this当前选中 -->
								<!-- <input type="checkbox" onClick="$('input[type=checkbox]').attr('checked',this.checked)"/> -->
<!-- 								<input type="checkbox" onClick="$('[name=privilegeIds]').attr('checked',this.checked)"/> -->
								<input type="checkbox" id="selectAll" onClick="$('[name=privilegeIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
								<input type="checkbox" id="selectAllNot" onClick="$('[name=privilegeIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全不选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
							<!-- 显示所有的权限的数据 -->
	<%-- <s:checkboxlist list="#privilegeList" listKey="id" listValue="privName"
		name="privilegeIds"> --%>
		<!-- freemark的模板文件:以.ftl结尾 
			模板技术
			模板+数据==最终结果
			jsp在web环境下运行
			使用freemarker技术：程序运行会先加载classapath下的的文件，如果没找到才会进入jar包中去找
			显示树状结构太复杂，要么自己写模板，要么改模板，不行
		-->
	<%-- </s:checkboxlist> --%>
	<!-- 自己写的html，并自行回显：接下来是打算做树状结构 -->
	<%-- <s:iterator value="#privilegeList">
		<input type="checkbox" name="privilegeIds" value="${id }" 
			id="cb_${id }" 
			<s:property value="%{id in privilegeIds ? 'checked' : ''}" />
		>
			
		<!-- 使用ognl表达式==取对象栈，取map(构造list、构造map)，判断集合 -->
		<label for="cb_${id }">${privName}</label>
		<br/>
	</s:iterator> --%>
	
<ul id="root">
	<!-- 先循环li项 -->
	<!-- 一级菜单 -->	
<s:iterator value="#topPrivilegeList">
		<li>
			<input type="checkbox" name="privilegeIds" value="${id }" id="cb_${id }" <s:property value="%{id in privilegeIds ? 'checked' : ''}" />>
			<label for="cb_${id }"><span class="folder">${privName}</span></label>
			<ul>
				<!-- 二级菜单:只能是上级的孩子 -->
			<s:iterator value="childrens">
					<li>
						<input type="checkbox" name="privilegeIds" value="${id }" id="cb_${id }" <s:property value="%{id in privilegeIds ? 'checked' : ''}" />>
						<label for="cb_${id }"><span class="folder">${privName}</span></label>
						<ul>
						<!-- 三级菜单 -->
						<s:iterator value="childrens">
								<li>
									<input type="checkbox" name="privilegeIds" value="${id }" id="cb_${id }" <s:property value="%{id in privilegeIds ? 'checked' : ''}" />>
									<label for="cb_${id }"><span class="folder">${privName}</span></label>
								</li>
						</s:iterator>
						</ul>
					</li>
			</s:iterator>
			</ul>
		</li>
</s:iterator>
</ul>
<script type="text/javascript">
	$("#root").treeview();//这样写的目的是： 在加载完了树形的基本数据后马上进行显示树形美化
</script>

							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath }/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath }/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，选中一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>

</body>
</html>
