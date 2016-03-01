<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <div class="header" id="header">
     <h1 id="logo" class="logo"><a href="javascript:;"><img src="${resource_path}/resource/judgmentAnalysis/img/logo.png" alt=""/></a></h1>
     <div id="toolBar" class="toolBar">
         <div class="welcome">
             <div class="pic">
                 <img src="${resource_path}/resource/judgmentAnalysis/pic/world.jpg" alt=""/>
                 <span class="veil"></span>
             </div>
             
                              欢迎您！<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>
        
         </div>
         <a class="exit" id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>" class="sys-exit">退出系统</a>
         
     </div>
     <div class="menu" id="menu">
         <ul>
             <li><a href="javascript:;"><span class="icon_ypfx"></span>研判分析</a></li>
         </ul>
     </div>
 </div>
<div id="userDialog_risk"></div>
<script type="text/javascript">
$(document).ready(function(){
	display();
	$("#top_index").click(function(){
		$('#accordion a').removeClass("cur");
		$("#index").addClass("cur");
	});
});
$("#updatePassword_risk").click(function(){
	$("#userDialog_risk").createDialog({
		width:700,
		height:260,
		title:'个人信息',
		position:'top',
		url:'${path}/passwordUpdateDlg.jsp',
		buttons: {
			"确定" : function(event){
				$("#firstUpdatePassForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
});
function display(){
	  var date=new Date();
	  var year = date.getFullYear();
	  var month = date.getMonth() +1;
	  var day = date.getDate();
	  var week = date.getDay();
	  switch (week) {
	      case 0 :
	    	  week= "星期日";
	          break;
	      case 1 :
	    	  week= "星期一";
	          break;
	      case 2 :
	    	  week= "星期二";
	          break;
	      case 3 :
	    	  week= "星期三";
	          break;
	      case 4 :
	    	  week= "星期四";
	          break;
	      case 5 :
	    	  week= "星期五";
	          break;
	      case 6 :
	    	  week= "星期六";
	          break;
	}
	  myclock_risk="<font size='2' color='white'> 今天是 "+year +"年"+month +"月"+day+"日         "+week+"</font>";
	  $("#liveclock_risk").html(myclock_risk);
}
</script>