<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>   
    <head>   
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">   
        <title>grid</title>   
   <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>   
    <body>   
      <input type="button" value="getSelect" onclick = "getSelect()"/>   
      <input type="button" value="selectbtn" onclick = "getSelAge()"/>   
         
      <div>   
      <select name="selectAge" id="selectAge">   
        <option value="1">18-21</option>   
        <option value="2">22-25</option>   
        <option value="3">26-29</option>   
        <option value="4">30-35</option>   
        <option value="5">Over35</option>   
            
      </select>   
      </div>   
      <p>   
       <input type="button" value="moreSelect" onclick = "moreSelect()"/>   
      <div>   
        <div>多选 需要增加 multiple属性   
            在多选中size属性 可以初始化下拉框默认显示几个选项   
        </div>   
        <div>   
        <select name="moreselAge" id="moreselAge" multiple="multiple">   
        <option value="1">18-21</option>   
        <option value="2">22-25</option>   
        <option value="3">26-29</option>   
        <option value="4">30-35</option>   
        <option value="5">Over35</option>   
        <option value="6">Over40</option>   
        <option value="7">Over50</option>   
            
      </select>   
        </div>   
      </div>   
   
      <p></p>   
       <input type="button" value="addNewbtn" onclick = "addNewSelections()"/>   
        <input type="button" value="deletebtn" onclick = "deleteselections()"/>   
   
        <input type="button" value="deleAllbtn" onclick = "deleteAllSelections()"/>   
       <div>selectName :<input type="text" id="txtName"/></div>   
       <div>selectValue:<input type="text" id="txtValue"/></div>   
   
   
       <div>   
        <select name="moreselAge" id="addNew">   
        <option value="1" selected>18-21</option>   
        <option value="2">22-25</option>   
        <option value="3">26-29</option>   
        <option value="4">30-35</option>   
        <option value="5">Over35</option>   
        <option value="6">Over40</option>   
        <option value="7">Over50</option>   
            
      </select>   
        </div>   
   
    <p>移动选项</p>   
     <p>   
     <table>   
        <tr collspan="2">   
            <td>   
                   <div>   
                <select name="moreselAge" id="move1"  multiple="multiple" size="7">   
                <option value="1">18-21sfiods</option>   
                <option value="2">22-25sjdfd</option>   
                <option value="3">26-29xxs</option>   
                <option value="4">30-35vs</option>   
                <option value="5">Over35dcff</option>   
                <option value="6">Over40shhfsd</option>   
                <option value="7">Over50sdefs</option>   
                <option value="8">Over88www</option>   
                    
              </select>   
                </div>   
                
            </td>   
            <td width="100" align="center">   
                <input type="button" value=">" onclick = "rightSingle()" />   
                 <input type="button" value=">>" onclick = "rightAll()"/>   
                 <input type="button" value="<" onclick = "leftSingle()"/>   
                 <input type="button" value="<<" onclick = "leftAll()"/>   
            </td>   
                
            <td>   
                <div>   
                <select name="moreselAge" id="move2"  multiple="multiple" size="7">   
                <option value="1">18-21</option>   
                <option value="2">22-25</option>   
                <option value="3">26-29</option>   
                <option value="4">30-35</option>   
                <option value="5">Over35</option>   
                <option value="6">Over40</option>   
                <option value="7">Over50</option>   
                <option value="8">Over88</option>   
                    
              </select>   
                </div>   
            </td>   
        <tr>   
     </table>   
   
   
   
           
    </body>   
    <script type="text/javascript">   
        //获得下拉列表对象   
        oListbox = document.getElementById("selectAge");   
        var ListUtil = new Object();   
            
   
        var selectbtn = document.getElementById("selectbtn");   
   
         function getSelAge (){   
        //访问选项   
            alert(oListbox.options[1].firstChild.nodeValue); //显示的内容   
   
            alert(oListbox.options[1].getAttribute("value"));//对应的value   
   
            alert("获得它在集合中的位置== " + oListbox.options[2].index); //获得它在集合中的位置   
   
            alert("获得集合的元素个数长度== " + oListbox.options.length); //获得集合的元素个数长度   
        }   
    /*************************************************************************************************/  
        //获得选中选项   
        function getSelect(){   
            var indx = oListbox.selectedIndex;   
            alert("获得选中的选项的索引 "+ indx );   
        }   
            
        //多选下拉框   
        var moreselAgeList = document.getElementById("moreselAge");    
   
    /*******************************************************************/  
   
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
   
    /***************************************************************/  
    // 多选   
        function moreSelect(){   
            var arrIndexes = ListUtil.getSelectIndexes(moreselAgeList);   
            alert("选中的数组length = "+ arrIndexes.length + " 选中的选项索引为 ："+ arrIndexes);   
        }   
   
/************************添加新选项***************************************************************/  
    //   
    var addNewLisbox = document.getElementById("addNew"); //获得下拉框对象   
    var otxtName = document.getElementById("txtName");   //name 文本框   
    var otxtValue  = document.getElementById("txtValue"); //value 文本框   
        
   
    //添加方法   
    ListUtil.addOptions = function(oListbox,sName,sValue){   
            
        var arryV = new Array();   
        //标记输入的值是否可以添加   
        var isAdd = false;   
        //判断是否有重复的值   
        for(var i =0 ;i<oListbox.options.length;i++){   
            var sv = oListbox.options[i].getAttribute("value");   
            if(sv == sValue){   
                alert("不能添加重复的value");   
                return ;   
            }else{   
                isAdd = true;   
            }   
        }   
   
        if(isAdd || oListbox.options.length == 0){     
   
            //下面使用dom方法创建节点   
            var oOption = document.createElement("option");// 创建option元素   
            oOption.appendChild(document.createTextNode(sName));   
   
            //因为选项的值不是必须的，所以如果传入了值 则添加进来   
            if(arguments.length == 3){   
                oOption.setAttribute("value",sValue);   
            }   
            oListbox.appendChild(oOption); //把选项添加进列表框   
            alert("添加成功!!");   
   
        }       // end if(isAdd)   
            
   
    }   
   
    //添加按钮的点击事件方法   
    function addNewSelections(){   
        var txtname = otxtName.value;   
        var txtvalue = otxtValue.value;   
        if(txtname != "" && txtvalue != ""){   
            ListUtil.addOptions(addNewLisbox,txtname,txtvalue);//添加新项   
            otxtName.value = "";   
            otxtValue.value = "";   
            
        }else{   
            alert("请输入要添加的值和name");   
            return;   
        }   
    }   
   
/*******************删除选中选项****************************************************************/  
   
//传入下拉框对象和(索引)   
ListUtil.deleteOptons = function(oListbox){   
    var selIndex = oListbox.selectedIndex;   
   
    if(oListbox.options.length == 0){   
        alert("列表中无元素可删除");   
        return ;   
    }   
    oListbox.remove(selIndex); //删除选中的选项   
}   
   
//删除按钮点击事件   
function deleteselections(){   
   
    ListUtil.deleteOptons(addNewLisbox);   
}   
   
/**********删除所有***********************************************************************/  
ListUtil.deletsAllOptions = function(oListbox){   
    if(oListbox.options.length != 0){          
        for(var i= oListbox.options.length-1;i>=0;i--){  //倒着删除是因为   
            oListbox.remove(i);   
        }   
    }else{   
        alert("该列表为空!");   
    }   
}   
   
function deleteAllSelections(){   
    ListUtil.deletsAllOptions(addNewLisbox);   
}   
   
/*******移动选项***************************************************************************************/  
     
 //获得下拉框    
  var move1Listbox = document.getElementById("move1"); //左边下拉框   
  var move2Listbox = document.getElementById("move2"); //右边下拉框   
   
  //移动一个或多个选中的选项   
  ListUtil.move = function(oListboxFrom ,oListboxTo){   
    //var idx1 = oListboxFrom.selectedIndex;   
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
   
  //向右移 一个元素   
  function rightSingle(){   
   
    ListUtil.move(move1Listbox,move2Listbox);   
  };   
   
  //向左移 一个元素   
  function leftSingle(){   
    ListUtil.move(move2Listbox,move1Listbox);   
  }   
   
  ListUtil.moveAll = function(oListboxFrom,oListboxTo){   
    for(var i=oListboxFrom.options.length-1;i>=0;i--){   
        oOption = oListboxFrom.options[i];   
        //alert(oOption);   
        oListboxTo.appendChild(oOption);   
    }   
  }   
   
  //向右移所有选项   
  function rightAll(){   
   
     ListUtil.moveAll(move1Listbox,move2Listbox);   
  }   
   
  //向左移所有选项   
  function leftAll(){   
    ListUtil.moveAll(move2Listbox,move1Listbox);   
  }   
   
   
   
    </script>   
</html>