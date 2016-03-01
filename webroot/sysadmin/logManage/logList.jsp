<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="content container_24">
	<div class="ui-corner-all" id="nav">
		<div  style="float:left;height:25px;line-height:25px;">
			<label style="float:left;line-height:25px;padding-right:10px;"><span style="color:red;">*</span>开始日期：</label>
			<input type="text" id="startDate" name="startDate" readonly="readonly" size="10"  style="float:left;margin-top:3px;" />
			<label style="float:left;line-height:25px;padding:0 10px;">结束日期：</label>
			<input type="text" id="endDate" name="endDate" readonly="readonly" size="10" style="float:left;margin-top:3px;" />
			<label style="float:left;padding:0 10px;line-height:25px;">模块名称：</label>
			<select id="firstModel" name="" style="float:left;margin-top:3px;" onchange="firstModelChange(this.value)">
				<option value="">全部</option>
				<s:iterator value="@com.tianque.core.systemLog.util.ModelType@getTopModelTypes()" var="topModelTypes">
			   		<option value="${topModelTypes.value}">${topModelTypes.key}</option>
			  	</s:iterator>
		  	</select>
	  		<label style="float:left;padding:0 10px;line-height:25px;"> &gt; </label>
		  	<select id="secondModel"  name="systemLog.moduleName"  style="float:left;margin-top:3px;" >
		  		<option value="">全部</option>
		  	</select>
		  	<!--  
			<label style="float:left;padding:0 10px;line-height:25px;">模块名称：</label>
			<select id="model" name="systemLog.moduleName" style="float:left;margin-top:3px;" onchange="changgemode(this)">
			  <option value=""></option>
			  	<s:iterator value="@com.tianque.core.systemLog.util.ModelType@getModelTypes()" var="modelType">
			   		<option value="${modelType}">${modelType}</option>
			  	</s:iterator>
			</select>
			-->
			<div id="div2" style="display: inline;float: left;padding:0 10px;line-height:25px;">
			<label style="float:left;padding:0 10px;line-height:25px;">操作名称：</label>
			<select id="operation" name="systemLog.operationType" style="float:left;margin-top:3px;" >
			   <option value="">全部</option>
			   <option value="1">新增</option>
			   <option value="2">修改</option>
			   <option value="3">删除</option>
			   <option value="5">导入</option>
			   <option value="9">启用</option>
			   <option value="10">禁用</option>
			   <option value="7">锁定</option>
			   <option value="8">解锁</option>
			   <option value="6">重置密码</option>
               <option value="13">导出</option>
               <option value="14">关注</option>
               <option value="15">取消关注</option>
               <option value="18">撤销认领</option>
			</select>
         </div>

			<label style="float:left;padding:0 10px;line-height:25px;">用户名：</label>
			<input type="text" id="user_autocomplete" name="systemLog.userName" style="width: 100px;float:left;margin-right:10px;margin-top:3px;"/>
			 <div id="div1" style="display: none; float: left;padding:0 10px;line-height:25px;">
			<label style="float:left;padding:0 10px;line-height:25px;">登录状态：</label>
			<select id="successOrFailId"  style="float:left;margin-top:3px;">
			   <option value="0">全部</option>
			   <option value="1">登录成功</option>
			   <option value="2">登录失败</option>
			</select>
			</div>

			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</div>
		<div class="clearLine">&nbsp;</div>
	     <span style="float:left;padding:0;margin:0;text-indent:0;line-height:25px;">常用日期：</span>
		<a id="today" href="javascript:void(0)"><span>今天</span></a>
		<a id="yesterday" href="javascript:void(0)"><span>昨天</span></a>
		<a id="weekly" href="javascript:void(0)"><span>最近七天内</span></a>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;" class="click_load">
	<a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="logList"></table>
		<div id="logListPager"></div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){

	var str = "<option value=''>全部</option>"
		+"<option value='3'>删除</option>"
		+"<option value='2'>修改</option>"
		+"<option value='1'>新增</option>"
		+"<option value='5'>导入</option>"
		+"<option value='9'>启用</option>"
		+"<option value='10'>禁用</option>"
		+"<option value='7'>锁定</option>"
		+"<option value='8'>解锁</option>"
		+"<option value='13'>导出</option>"
		+"<option value='6'>重置密码</option>"
		+"<option value='18'>撤销认领</option>";
		var str1 = "<option value=''></option>"
			+"<option value='3'>删除</option>"
			+"<option value='2'>修改</option>"
			+"<option value='1'>新增</option>"
			+"<option value='5'>导入</option>";
		var str2="<option value=''></option>"
			+"<option value='13'>导出</option>"
			+"<option value='3'>删除</option>";
	
	$('#startDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d',
    	showClearButton:false
        	});
	$('#endDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d'
        	});
    $("#startDate").datepicker("setDate" ,+1);
    $("#endDate").datepicker("setDate" , +1);
    loadList();
	function loadList(){
		jQuery("#logList").jqGridFunction({
			datatype: "local",
		   	colModel:[
				{name:'id', index:'id',hidden:true,sortable:false },
		   		{name:'operateTime', label:'时间', index:'operateTime', align:'center',sortable:true},
		   		{name:'operation', label:'操作描述',sortable:true},
		   		{name:'operationType', label:'操作类型',formatter:formatteroperationType,sortable:true},
		   		{name:'moduleName',label:'模块名称',sortable:true },
		   		{name:'userName', label:'用户名',sortable:true },
		   		{name:'clientIp', label:'访问IP',sortable:true }
		   	],
		   	shrinkToFit:true,
		   	showColModelButton:false
		});
	}
	function removeLoad(){
		$(".click_load .click_btn").hide();
	}
	$(".click_load .click_btn").click(function(){
		reload();
		$(this).hide(100);
	});
	
	$("#contentDiv").click( function () { 
		removeLoad();
	}); 

	$("#reload").click(function(){
		reload();
	});

	function reload(){
		$("#successOrFailId").val(0);
		$("#user_autocomplete").val("");
		$("#operation").empty();
		$("#operation").append(str);
		//$('#div1').hide();
		//$('#div2').show();
		$("#firstModel").val("").change();
		$("#secondModel").val("");
		 $("#startDate").datepicker( "setDate" ,+1);
	    $("#endDate").datepicker( "setDate" , +0);
	    
		var initParam = {
				"startDate": function (){ return $("#startDate").val()},
				"endDate":   function (){ return $("#endDate").val()},
				"systemLog.moduleName":function (){ return $("#secondModel").val()},
				"systemLog.operationType":  function (){ return $("#operation").val()},
				"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
			}
		$("#logList").setGridParam({
		url:"${path}/sysadmin/logManage/findSystemLogByOrgCode.action",
		datatype: "json",
		page:1
		});
		$("#logList").setPostData(initParam);
		$("#logList").trigger("reloadGrid");
	}
	
	$('#search').click(function(){
		if(!check()) return;
		$("#logList").trigger("reloadGrid");
	});

	$("#today").click(function(){
		$("#secondModel").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("");
		$("#successOrFailId").val(0);
	    $("#startDate").datepicker( "setDate" ,+1);
	    $("#endDate").datepicker( "setDate" , +1);
		$("#logList").trigger("reloadGrid");
	});

	$('#yesterday').click(function(){
		$("#secondModel").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("");
		$("#successOrFailId").val(0);
	    $("#startDate").datepicker( "setDate" ,-1);
	    $("#endDate").datepicker( "setDate",-1);
		$("#logList").trigger("reloadGrid");
	});

	$('#weekly').click(function(){
		$("#secondModel").val("");
		$("#operation").val("");
		$("#user_autocomplete").val("")
		$("#successOrFailId").val(0);
		$("#startDate").datepicker( "setDate" ,-7);
	    $("#endDate").datepicker( "setDate" ,+1);
		$("#logList").trigger("reloadGrid");
	});



});

// 功能: 1)去除字符串前后所有空格
// 2)去除字符串中所有空格(包括中间空格,需要设置第2个参数为:g)
/**function trim(str,is_global) {
	var result = str.replace(/(^\s+)|(\s+$)/g,"");
	if(is_global.toLowerCase()=="g")
		result = result.replace(/\s/g,"");
	return result;
}*/
// function diffDays(startDate,endDate){
// 	return Math.abs((endDate - startDate)/(3600*24*1000));
// }
function formatteroperationType(el,options,rowData){
	var operationType=rowData.operationType;
	if(operationType=='1'){
	   return "新增";
	}
	if(operationType=='2'){
		   return "修改";
		}
	if(operationType=='3'){
		   return "删除";
		}
	if(operationType=='5'){
		   return "导入";
		}
	if(operationType=='6'){
		   return "重置密码";
		}
	if(operationType=='7'){
		   return "锁定";
		}
	if(operationType=='8'){
		   return "解锁";
		}
	if(operationType=='9'){
		   return "启用";
		}
	if(operationType=='10'){
		   return "禁用";
		}
	if(operationType=='11'){
		   return "登录";
		}
	if(operationType=='12'){
		   return "登出";
		}
	if(operationType=='13'){
		   return "导出";
		}
	if(operationType=='14'){
		   return "关注";
		}
	if(operationType=='15'){
		   return "取消关注";
		}
	if(operationType=='18'){
		   return "撤销认领";
		}
	if(operationType=='19'){
		   return "用户登录成功";
		}
	return "";
}


function check() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    if(startDate==null || startDate==""){
    	$.notice({
			message:"开始时间必须选择!",
			width: 400
		});
		return false;
    }
    if(endDate!=null && endDate!="" && startDate.split("-")[1] != endDate.split("-")[1]){
    	$.notice({
			message:"开始时间月份和结束时间月份请保持一致!",
			width: 400
		});
		return false;
    }
// 	if(diffDays(startDate,endDate)>7){
// 		$.notice({
// 			message:"开始日期和结束日期相差不能超过7天!",
// 			width: 400
// 		});
// 		return false;
// 	}
	if(endDate!=null && endDate!="" &&  startDate>endDate){
		$.notice({
			message:"开始日期不能大于结束日期!",
			width: 400
		});
		return false;
	}
	return true;
}

function firstModelChange(text){
	$("#secondModel").empty();
	if(text == ""){
		$("#secondModel").append("<option value=''>全部</option>");
		return ;
	}
	$.ajax({
		async:false,
		url:"${path}/sysadmin/logManage/getSecondModelTypes.action",
		data:{
			"topLogType": function(){ return text}
		},
		success:function(responseData){
			if(responseData == false || responseData =="false"){
				$("#secondModel").empty();
			}else{
				var list = eval(responseData);
				var option = "";
				for(i=list.length-1 ; i>=0 ; i--){
					option += "<option value='"+list[i]+"'>"+list[i]+"</option>";
				}
				$("#secondModel").append(option);
			}
		}
	});
	
}
//==========================================下面代码暂时没用==========================================
function changgemode(text){
    if(text.value=='系统登录'){
    	$("#logList").setGridParam({
    		url:"${path}/sysadmin/logManage/logList.action",
    		postData:{
    			"startDate": function (){ return $("#startDate").val()},
    			"endDate":   function (){ return $("#endDate").val()},
    			"successOrFailId":function (){ return $("#successOrFailId").val()},
    			"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
    		},
    		datatype: "json",
    		page:1
    	});

    	$("#logList").trigger("reloadGrid");
    }
    if(text.value!='系统登录'){
    	$("#logList").setGridParam({
    	url:"${path}/sysadmin/logManage/findSystemLogByOrgCode.action",
		postData:{
			"startDate": function (){ return $("#startDate").val()},
			"endDate":   function (){ return $("#endDate").val()},
			"systemLog.moduleName":function (){ return $("#secondModel").val()},
			"systemLog.operationType":  function (){ return $("#operation").val()},
			"systemLog.userName":  function (){ return $("#user_autocomplete").val()}
		},
		datatype: "json",
		page:1
    	});
    	$("#logList").trigger("reloadGrid");
    }

	text = text.value;
	if(text!=null &&text=="岗位管理"){
		$('#div1').hide();
		$('#div2').show();
		$("#operation option:[value=5]").remove();
         $("#operation option:[value=6]").remove();
         $("#operation option:[value=7]").remove();
         $("#operation option:[value=8]").remove();
         $("#operation option:[value=9]").remove();
         $("#operation option:[value=10]").remove();
	}else if(text!=null && text=="部门管理"){
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str1);

	}else if(text!=null && text=="系统登录"){
		$('#div1').show();
    	$('#div2').hide();
	}else if(text!=null && (text=="重点场所"||text=="特殊人群"||text=="出租房"||text=="规上企业"||text=="社会组织")){
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str2);
	}else{
		$('#div1').hide();
		$('#div2').show();
		$("#operation").empty();
		$("#operation").append(str);

	}


}

</script>