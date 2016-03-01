<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="content container_24">
	<div class="ui-corner-all" id="nav">
		<div  style="float:left;height:25px;line-height:25px;">
			<label style="float:left;line-height:25px;padding-right:10px;">操作业务类型：</label>
			 <select name="businessType" id="businessType"   style="float:left;margin-top:3px;">
		    	<option value="">全部</option>
		       <s:iterator value="@com.tianque.core.util.NewBaseInfoTables@populationTypes">
					<option value="<s:property value='key'/>"><s:property value="value"/></option>
				</s:iterator>
		    </select>
		    <label style="float:left;line-height:25px;padding:0 10px;">操作类型：</label>
			 <select name="operateType" id="operateType"   style="float:left;margin-top:3px;">
		    	<option value="">全部</option>
		       <s:iterator value="@com.tianque.systemOperateLog.util.SystemOperateType@systemOperateTypes">
					<option value="<s:property value='key'/>"><s:property value="value"/></option>
				</s:iterator>
		    </select>
		    <label style="float:left;line-height:25px;padding:0 10px;">身份证号码：</label>
			<input type="text" id="dataKeyword" name="dataKeyword"  size="10" style="float:left;margin-top:3px;width:130px;" />
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div  style="float:left;height:25px;line-height:25px;">
			<label style="float:left;line-height:25px;padding-right:10px;">操作开始日期：</label>
			<input type="text" id="startDate" name="startDate" readonly="readonly" size="10"  style="float:left;margin-top:3px;width:115px;" />
			<label style="float:left;line-height:25px;padding:0 10px;">操作结束日期：</label>
			<input type="text" id="endDate" name="endDate" readonly="readonly" size="10" style="float:left;margin-top:3px;width:115px;" />
			<label style="float:left;line-height:25px;padding-right: 32px;">&nbsp;</label>
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
			<a id="reset" href="javascript:void(0)"><span>重置</span></a>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;" class="click_load">
		<a href="javascript:;" class="click_btn">点击加载数据</a>
		<table id="systemLogList"></table>
		<div id="systemLogListPager"></div>
	</div>
	<div id="compareDataDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	loadList();
	$('#startDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d',
    	onSelect: function(dateText, inst) {
  			if(dateText!=null&&dateText!=''){
  				var dateUnit=dateText.split('-');
  				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
  				$("#endDate").datepicker("option", "minDate",date);
  				}
  			}
       	});
	$('#endDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
		minDate:'-1m',
    	maxDate:'+0d',
   		onSelect: function(dateText, inst) {
  			if(dateText!=null&&dateText!=''){
  				var dateUnit=dateText.split('-');
  				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
  				$("#startDate").datepicker("option", "maxDate",date);
  				}
  			}
       	});
    $("#startDate").datePicker("setDate" ,"+0d");
    $("#endDate").datePicker("setDate" , "+1d");
    
    function loadList(){
		jQuery("#systemLogList").jqGridFunction({
			datatype: "local",
		   	colModel:[
				{name:'id', index:'id',hidden:true,sortable:false, },
		   		{name:'operateDate', label:'时间', index:'operateTime', align:'center',sortable:true,width:200},
		   		{name:'operatePerson', label:'操作人',sortable:true,width:100, align:'center' },
		   		{name:'moduleType',label:'模块类型',sortable:true,width:150 , align:'center'},
		   		{name:'businessType',label:'业务类型',sortable:true,width:150 , align:'center'},
		   		{name:'operateType', label:'操作类型',formatter:formatteroperationType,sortable:true,width:80, align:'center'},
		   		{name:'dataKeyword', label:'身份证号码',sortable:true,width:160, align:'center'},
		   		{name:'contrastState', label:'操作前后对比',sortable:true,width:100, align:'center',formatter:formatterContrastState}
		   	],
		   	shrinkToFit:false,
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

	function reload(){
		$("#model").val("");
		$("#successOrFailId").val(0);
		$("#user_autocomplete").val("");
		$("#operation").empty();
		//$("#operation").append(str);
		
		var initParam = {
				"systemOperateLogVo.startDate": function (){ return $("#startDate").val()},
				"systemOperateLogVo.endDate":   function (){ return $("#endDate").val()},
				"systemOperateLogVo.searchDataKeyword":  function (){ return $("#dataKeyword").val()},
				"systemOperateLogVo.searchBusinessType":  function (){ return $("#businessType").val()},
				"systemOperateLogVo.operateType":  function (){ return $("#operateType").val()}
			}
		$("#systemLogList").setGridParam({
			url:"${path}/systemOperateLogManage/findSystemOperateLogsPagerBySearchVo.action",
			datatype:"json",
			page:1
		});
		$("#systemLogList").setPostData(initParam);
		$("#systemLogList").trigger("reloadGrid");
	}	

	$("#contentDiv").click( function () { 
		removeLoad();
	}); 

	$("#reload").click(function(){
		reload();
	});

	$('#search').click(function(){
		if(!check()) return;
		$("#systemLogList").trigger("reloadGrid");
	});
	$('#reset').click(function(){
		resetSearchCondition();
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
function resetSearchCondition(){
	$("#startDate").val("");
	$("#endDate").val("");
	$("#dataKeyword").val("");
	$("#businessType").val("");
	$("#operateType").val("");
}

function diffDays(startDate,endDate){
	return Math.abs((endDate - startDate)/(3600*24*1000));
}
function formatteroperationType(el,options,rowData){
	var operateType=rowData.operateType;
	if(operateType=='1'){
	   return "新增";
	}
	if(operateType=='2'){
		   return "修改";
		}
	if(operateType=='3'){
		   return "删除";
		}
	if(operateType=='4'){
		   return "注销";
		}
	if(operateType=='5'){
		   return "取消注销";
		}
	if(operateType=='6'){
		   return "取消死亡";
		}
	if(operateType=='7'){
		   return "死亡";
		}
	if(operateType=='8'){
		   return "导入";
		}
	if(operateType=='9'){
		   return "转为户籍人口";
		}
	if(operateType=='10'){
		   return "转为流动人口";
		}
	if(operateType=='11'){
		   return "转为刑释解救";
		}
	if(operateType=='12'){
		   return "转移";
		}
	if(operateType=='13'){
		   return "落户";
		}
	return "";
}

function formatterContrastState(el,options,rowData){
	var contrastState=rowData.contrastState;
	if(contrastState == '1' || contrastState == '2'){
		return '<a href="javascript:void(0)" onclick="viewCompareData('+rowData.id+')"><span>比较</span></a>'
	}else{
		return '';
	}
	
}
function viewCompareData(systemoOperateLogId){
	$("#compareDataDialog").createDialog({
		width:550,
		height:400,
		title:'数据对比',
		url:'${path}/systemOperateLogManage/viewCompareData.action?systemoOperateLogId='+systemoOperateLogId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
				$("#systemLogList").trigger("reloadGrid");
			}
		}
	});
}


function check() {
    var startDate = $("#startDate").datePicker( "getDate");
    var endDate = $("#endDate").datePicker( "getDate");

	if(diffDays(startDate,endDate)>7){
		$.notice({
			message:"开始日期和结束日期相差不能超过7天!",
			width: 400
		});
		return false;
	}
	if(startDate>endDate){
		$.notice({
			message:"开始日期不能大于结束日期!",
			width: 400
		});
		return false;
	}
	return true;
}


</script>