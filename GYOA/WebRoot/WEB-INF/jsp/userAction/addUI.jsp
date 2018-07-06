<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>用户新增</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <%@ include file="/WEB-INF/jsp/public/common.jspf" %>

<script type="text/javascript">
	var ListUtil = new Object();
	//向右移 一个元素   
  	function rightSingle(){   
  		//var arr = ListUtil.getSelectIndexes(move1Listbox);
  		//alert(arr.length);
   	 	//ListUtil.move(move1Listbox,move2Listbox);   
   	 	//获得下拉框
		var move1Listbox = document.getElementById("userAction_add_roleIdsTemp");
		var move2Listbox = document.getElementById("userAction_add_roleIds");
   	 	var count=0;
		var intvalue="";
		//alert(move1Listbox.length);
		//alert(move1Listbox.options.length);//[object HTMLSelectElement]
		//alert(move1Listbox.options[0].selected);
		///var arrs = ListUtil.getSelectIndexes(move1Listbox);
		//alert(arrs.length);
		ListUtil.move(move1Listbox,move2Listbox);
  	};   
  	//向左移 一个元素   
  function leftSingle(){   
  	var move1Listbox = document.getElementById("userAction_add_roleIdsTemp");
	var move2Listbox = document.getElementById("userAction_add_roleIds");
    ListUtil.move(move2Listbox,move1Listbox);   
  }   
  //向右移所有选项   
  function rightAll(){   
  	 var move1Listbox = document.getElementById("userAction_add_roleIdsTemp");
	 var move2Listbox = document.getElementById("userAction_add_roleIds");
     ListUtil.moveAll(move1Listbox,move2Listbox);   
  }   
  //向左移所有选项   
  function leftAll(){   
	var move1Listbox = document.getElementById("userAction_add_roleIdsTemp");
	var move2Listbox = document.getElementById("userAction_add_roleIds");
   	ListUtil.moveAll(move2Listbox,move1Listbox);   
  }   
  //将一边的都移到另一边
  ListUtil.moveAll = function(oListboxFrom,oListboxTo){   
    for(var i=oListboxFrom.options.length-1;i>=0;i--){   
        oOption = oListboxFrom.options[i];   
        //alert(oOption);   
        oListboxTo.appendChild(oOption);   
    }   
  }   
  
  //移动一个或多个选中的选项   
  ListUtil.move = function(oListboxFrom ,oListboxTo){   
    var arrIndexes = ListUtil.getSelectIndexes(oListboxFrom);   
    var oOption ;   
   
    if(arrIndexes.length == 0 ){   
        alert("请选择至少一个选项!");   
        return ;   
    }else{   
   
        for(var i=oListboxFrom.options.length-1;i>=0;i--){   
             oOption = oListboxFrom.options[i];  
            if(oOption.selected && oOption != null ){   
                oListboxTo.appendChild(oOption);   
            }   
        }   
    }      
  }   
  
  //入参 下拉框对象   
   ListUtil.getSelectIndexes = function (oListbox){   
       var arrIndexes =  new Array();   
       for(var i=0 ; i<oListbox.options.length;i++){   
           //如果该项被选中则把该项对应的索引添加到数组中   
           if(oListbox.options[i].selected){   
                   arrIndexes.push(i);   
           }   
       }   
       return  arrIndexes; //返回选中的选项索引   
   } 
	
</script>
</head>
<body>
<!-- 删掉演示的内容非常重要-->
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath }/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="userAction_add">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath }/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">所属部门</td>
                        <td>
                        	<%-- <select name="departmentId" class="SelectStyle">
                                <option value="0" selected="selected">请选择部门</option>
                                <option value="7">┠总经理室</option>
                                <option value="1">┠市场部</option>
                                <option value="2">　┠咨询部</option>
                                <option value="3">　┠招生部</option>
                                <option value="4">┠教学部</option>
                                <option value="5">┠后勤部</option>
                            </select>  --%>
                            
                            <s:select name="departmentId"  cssClass="SelectStyle"
                            	headerKey="" headerValue="请选择部门"
                            list="#departmentList" listKey="id" listValue="deptName"></s:select>
                            <!-- id:是提交属性；deptName：是显示属性 -->
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><s:textfield name="loginName" cssClass="InputStyle"/> *
							（登录名要唯一）
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield name="realName" cssClass="InputStyle"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                        <!-- 
                        	<input type="RADIO" name="gender" value="男" id="male"/><label for="male">男</label>
							<input type="RADIO" name="gender" value="女" id="female"/><label for="female">女</label>
							list代表选项信息(对象集合)的集合==> 显示和实际值 -->
							<%-- <s:radio list="" listKey="" listValue=""></s:radio> --%>
							
							<s:radio name="gender" list="{'男','女' }"></s:radio>
							
<%-- 							<s:radio list="#{'男':'男','女':'女' }"></s:radio> 
							 构建map : #{'男':'男','女':'女' }--%>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phoneNumber" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>备注</td>
                        <td><s:textarea name="userDesc" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath }/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>
                        	<!-- <select name="roleIdList" multiple="true" size="10" class="SelectStyle">
                                <option value="1">程序员</option>
                                <option value="2">行政秘书</option>
                                <option value="3">出纳</option>
                                <option value="4">总经理</option>
                                <option value="5">测试员</option>
                            </select> -->
                            <s:select name="roleIdsTemp" cssClass="SelectStyle" 
                            	multiple="true" size="10" listKey="id" listValue="roleName"
                            	list="#roleList" >
                            	<!--headerKey="" headerValue="请选择" 是默认值  -->
                            </s:select>
                            <!-- ?rid=1&rid=2&rid=3 -->
                            按住Ctrl键可以多选或取消选择
                        </td>
            <td width="100">   
                <input type="button" value=">" onclick = "rightSingle()" />   
                 <input type="button" value=">>" onclick = "rightAll()"/>   
                 <input type="button" value="<" onclick = "leftSingle()"/>   
                 <input type="button" value="<<" onclick = "leftAll()"/>
                 <input id="btnSelect" type="button" value="确定选择岗位" onclick="clk()"/>   
            </td>   
               <td>
                  <div>   
	                <select name="roleIds" id="userAction_add_roleIds" class="SelectStyle" size="10"   multiple="multiple">   
	                
	                </select>
                </div>
                         <!-- 待完成.....将需要添加的岗位加入到右边 -->
                        	<%-- <s:select name="roleIds" cssClass="SelectStyle" 
                            	multiple="true" size="10" listKey="id" listValue="roleName"
                            	list="#roleList" > --%>
                            	<!--headerKey="" headerValue="请选择" 是默认值  -->
                         <%--    </s:select> --%>
                        </td>
                    </tr>
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
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改用户信息时，登录名不可修改。
</div>
<script type="text/javascript">
	//js动态添加事件:将左边下拉框的选择项加入到右边下拉框:右边的是可以提交的选择项
	//$("#btnSelect").onclick(function() {
	//	alert('');	
	//});
	function clk() {
		//alert('OK');
		var move1Listbox = document.getElementById("userAction_add_roleIdsTemp");
		var move2Listbox = document.getElementById("userAction_add_roleIds");
		//alert("选择岗位数: " + move1Listbox.options.length);//[object HTMLSelectElement]
		//var arrs = ListUtil.getSelectIndexes(move2Listbox);
		//alert("未选择岗位数: " + arrs.length);
		var ret = "";
        for(var i=0 ; i<move2Listbox.options.length;i++){   
           //如果该项被选中则把该项对应的索引添加到数组中   
           if(move2Listbox.options[i].selected){   
           		var opt = move2Listbox.options[i];
           		ret = ret.concat(opt.value + ":" + opt.text + ",");
                //ret = ret.concat(.selectedIndex + ",");
           }   
       }   
       alert(ret);
	}
</script>
</body>
</html>
