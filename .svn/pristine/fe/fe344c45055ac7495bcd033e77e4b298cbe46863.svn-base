<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div align="right" class="zt_Accounting_operations" style="float: right;white-space: nowrap;margin: 0 25px 10px 0;">
			<img src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'>已上报&nbsp;&nbsp;
			<img src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></img>未上报&nbsp;&nbsp;
			<input type="checkbox" disabled="disabled"/>&nbsp;&nbsp;可催报
		</div>
		<pop:JugePermissionTag ename="warmStatementsDirectory">
		<a id="warn" href="javascript:void(0)"><span>催报</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	
	<div style="width: 100%;" id="grid_content">
		<table id="dailyLogList"></table>
		<div id="dailyLogListPager"></div>
	</div>
</div>

<s:if test=""></s:if>

<script type="text/javascript">
var title="";
var width=650;
var height=370;
var widthWhenAdd=650;
var heightWhenAdd=370;

function afterLoad(){
	$.loadingComp("close");
}

function setDailyLogOperateButton(){
}

$(document).ready(function(){
<s:if test='"月报".equals(reportedType)'>
				$("#dailyLogList").jqGridFunction({
					datatype: "json",
					height:$("div.ui-layout-center").height()-120,
					url: '${path}/daily/publicSecurityRenovateManage/popedomReports.action?dailyDirectory.Id='+$("#dailyDirectoryId").val()+'&dailyYear.id='+$("#dailyYear").val(),
					page:1,
					colNames:['id','单位名称',"一月","二月","三月",'四月',"五月","六月","七月","八月","九月","十月","十一月","十二月"],
				   	colModel:[
				        {name:"orgId",index:"orgId",hidden:true,hidedlg:true},
				        {name:'orgName',sortable:false,width:160},
				  		{name:"january",sortable:false,width:60,formatter:chooseJanuaryModule},
						{name:"february",sortable:false,width:60,formatter:chooseFebruaryModule},
						{name:'march',sortable:false,width:60,formatter:chooseMarchModule},
					    {name:'april',sortable:false,width:60,formatter:chooseAprilModule},
				        {name:'may',sortable:false,width:60,formatter:chooseMayModule},
						{name:'june',sortable:false,width:60,formatter:chooseJuneModule},
						{name:'july',sortable:false,width:60,formatter:chooseJulyModule},
						{name:'august',sortable:false,width:60,formatter:chooseAugustModule},
				        {name:'september',sortable:false,width:60,formatter:chooseSeptemberModule},
						{name:'october',sortable:false,width:60,formatter:chooseOctoberModule},
						{name:'november',sortable:false,width:60,formatter:chooseNovemberModule},
						{name:'december',sortable:false,width:60,formatter:chooseDecemberModule}
					],
					scrollrow:true,
					rowNum:100,
					rowList:[100],
					scroll:true,
					loadComplete: afterLoad,
					onSelectRow: setDailyLogOperateButton
				});
				
				$("#reload").click(function(){
					$("#dailyLogList").setGridParam({
					url: '${path}/daily/publicSecurityRenovateManage/popedomReports.action',
					datatype: "json",
					page:1
				});
				$("#dailyLogList").setPostData({
			    	"dailyDirectory.Id":$("#dailyDirectoryId").val(),
			    	"dailyYear.id":$("#dailyYear").val()
			   	});
				$("#dailyLogList").trigger("reloadGrid");
				});
				$("#warn").click(function(){
				    var ids = ''; 
					$("[name='warnObject']:checkbox:checked").each(function(index,domEle){ids+=$(this).val()+',';});
					if(ids==null||ids==''){
						$.messageBox({message:"请选择具体月份",level:"warn"});
						return;
					}
			       $.ajax({
					async: false,
					url: "${path}/daily/publicSecurityRenovateManage/addDailyLogWarmMessage.action",
					data:{
						"ids":ids,
						"modeType":"month",
						"objectType":"${modeType}"
					},
					success:function(responseData){
						$.messageBox({message:"催报成功"});
					}
				});	
			        
				});

</s:if>



<s:if test='"季报".equals(reportedType)'>
	$("#dailyLogList").jqGridFunction({
		datatype: "json",
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportQuarter.action?dailyDirectory.Id='+$("#dailyDirectoryId").val()+'&dailyYear.id='+$("#dailyYear").val(),
		colNames:['id','单位名称',"一季度","二季度","三季度",'四季度'],
	   	colModel:[
	        {name:"orgId",index:"orgId",hidden:true,hidedlg:true},
	        {name:'orgName',sortable:false,width:160},
	  		{name:"firstQuarter",sortable:false,formatter:firstQuarterDecemberModule},
			{name:"secondQuarter",sortable:false,formatter:secondQuarterDecemberModule},
			{name:'thirdQuarter',sortable:false,formatter:thirdQuarterDecemberModule},
		    {name:'fourthQuarter',sortable:false,formatter:fourthQuarterDecemberModule}
		],
		scrollrow:true,
		rowNum:100,
		rowList:[100],
		scroll:true,
		loadComplete: afterLoad,
		onSelectRow: setDailyLogOperateButton
	});
	$("#reload").click(function(){
		$("#dailyLogList").setGridParam({
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportQuarter.action',
		datatype: "json",
		page:1
	});
	$("#dailyLogList").setPostData({
    	"dailyDirectory.Id":$("#dailyDirectoryId").val(),
    	"dailyYear.id":$("#dailyYear").val()
   	});
	$("#dailyLogList").trigger("reloadGrid");
	});
	
	$("#warn").click(function(){
	    var ids = ''; 
		$("[name='warnObject']:checkbox:checked").each(function(index,domEle){ids+=$(this).val()+',';});
		if(ids==null||ids==''){
			$.messageBox({message:"请选择具体季度",level:"warn"});
			return;
		}
       $.ajax({
		async: false,
		url: "${path}/daily/publicSecurityRenovateManage/addDailyLogWarmMessage.action",
		data:{
			"ids":ids,
			"modeType":"quarter",
			"objectType":"${modeType}"
		},
		success:function(responseData){
			$.messageBox({message:"催报成功"});
		}
	});	
        
	});

</s:if>

<s:if test='"半年报".equals(reportedType)'>
	$("#dailyLogList").jqGridFunction({
		datatype: "json",
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportforHalf.action?dailyDirectory.Id='+$("#dailyDirectoryId").val()+'&dailyYear.id='+$("#dailyYear").val(),
		colNames:['id','单位名称',"上半年","下半年"],
	   	colModel:[
	        {name:"orgId",index:"orgId",hidden:true,hidedlg:true},
	        {name:'orgName',sortable:false},
	  		{name:"firstHalf",sortable:false,formatter:firstHalfDecemberModule},
			{name:"secondHalf",sortable:false,formatter:secondHalfDecemberModule}
		],
		scrollrow:true,
		rowNum:100,
		rowList:[100],
		scroll:true,
		loadComplete: afterLoad,
		onSelectRow: setDailyLogOperateButton
	});
	
	$("#reload").click(function(){
		$("#dailyLogList").setGridParam({
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportforHalf.action',
		datatype: "json",
		page:1
	});
	$("#dailyLogList").setPostData({
    	"dailyDirectory.Id":$("#dailyDirectoryId").val(),
    	"dailyYear.id":$("#dailyYear").val()
   	});
	$("#dailyLogList").trigger("reloadGrid");
	});
	
	$("#warn").click(function(){
	    var ids = ''; 
		$("[name='warnObject']:checkbox:checked").each(function(index,domEle){ids+=$(this).val()+',';});
		if(ids==null||ids==''){
			$.messageBox({message:"请选择具体半年",level:"warn"});
			return;
		}
       $.ajax({
		async: false,
		url: "${path}/daily/publicSecurityRenovateManage/addDailyLogWarmMessage.action",
		data:{
			"ids":ids,
			"modeType":"half",
			"objectType":"${modeType}"
		},
		success:function(responseData){
			$.messageBox({message:"催报成功"});
		}
	});	
        
	});

</s:if>

<s:if test='"年报".equals(reportedType)'>
	$("#dailyLogList").jqGridFunction({
		datatype: "json",
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportforYear.action?dailyDirectory.Id='+$("#dailyDirectoryId").val()+'&dailyYear.id='+$("#dailyYear").val(),
		colNames:['id','单位名称',"年度"],
	   	colModel:[
	        {name:"orgId",index:"orgId",hidden:true,hidedlg:true},
	        {name:'orgName',sortable:false},
	  		{name:"years",sortable:false,formatter:yearsDecemberModule}
		],
		scrollrow:true,
		rowNum:100,
		rowList:[100],
		scroll:true,
		loadComplete: afterLoad,
		onSelectRow: setDailyLogOperateButton
	});
	$("#reload").click(function(){
		$("#dailyLogList").setGridParam({
		url: '${path}/daily/publicSecurityRenovateManage/popedomReportforYear.action',
		datatype: "json",
		page:1
	});
	$("#dailyLogList").setPostData({
    	"dailyDirectory.Id":$("#dailyDirectoryId").val(),
    	"dailyYear.id":$("#dailyYear").val()
   	});
	$("#dailyLogList").trigger("reloadGrid");
	});
	$("#warn").click(function(){
	    var ids = ''; 
		$("[name='warnObject']:checkbox:checked").each(function(index,domEle){ids+=$(this).val()+',';});
		if(ids==null||ids==''){
			$.messageBox({message:"请选择年度",level:"warn"});
			return;
		}
       $.ajax({
		async: false,
		url: "${path}/daily/publicSecurityRenovateManage/addDailyLogWarmMessage.action",
		data:{
			"ids":ids,
			"modeType":"year",
			"objectType":"${modeType}"
		},
		success:function(responseData){
			$.messageBox({message:"催报成功"});
		}
	});	
});

</s:if>



function chooseJanuaryModule(el,options,rowData){
	if(rowData.january=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.january=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.january=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_1'  /></center>";
	}
	return "";
};
function chooseFebruaryModule(el,options,rowData){
	if(rowData.february=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.february=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.february=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_2'  /></center>";
	}
	return "";
}
function chooseMarchModule(el,options,rowData){
	if(rowData.march=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.march=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.march=="3"){
		  return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_3'  /></center>";
	}
	return "";
}
function chooseAprilModule(el,options,rowData){
	if(rowData.april=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.april=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.april=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_4'  /></center>";
	}
	return "";
}
function chooseMayModule(el,options,rowData){
	if(rowData.may=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.may=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.may=="3"){
		  return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_5'  /></center>";
	}
	return "";
}
function chooseJuneModule(el,options,rowData){
	if(rowData.june=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.june=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.june=="3"){
		  return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_6'  /></center>";
	}
	return "";
}
function chooseJulyModule(el,options,rowData){
	if(rowData.july=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.july=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.july=="3"){
		  return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_7'  /></center>";
	}
	return "";
}
function chooseAugustModule(el,options,rowData){
	if(rowData.august=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.august=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.august=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_8'  /></center>";
	}
	return "";
}
function chooseSeptemberModule(el,options,rowData){
	if(rowData.september=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.september=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.september=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_9'  /></center>";
	}
	return "";
}
function chooseOctoberModule(el,options,rowData){
	if(rowData.october=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.october=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.october=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_10'  /></center>";
	}
	return "";
}
function chooseNovemberModule(el,options,rowData){
	if(rowData.november=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.november=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.november=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_11'  /></center>";
	}
	return "";
}

function chooseDecemberModule(el,options,rowData){
	if(rowData.december=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.december=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.december=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_12'  /></center>";
	}
	return "";
	
}

function secondQuarterDecemberModule(el,options,rowData){
	if(rowData.secondQuarter=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.secondQuarter=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.secondQuarter=="3"){
	 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_2'  /></center>";
	}
	return "";
	
}

function thirdQuarterDecemberModule(el,options,rowData){
	if(rowData.thirdQuarter=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.thirdQuarter=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.thirdQuarter=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_3'  /></center>";
	}
	return "";
	
}

function fourthQuarterDecemberModule(el,options,rowData){
	if(rowData.fourthQuarter=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.fourthQuarter=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.fourthQuarter=="3"){
		  return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_4'  /></center>";
	}
	return "";
	
}

function firstQuarterDecemberModule(el,options,rowData){
	if(rowData.firstQuarter=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.firstQuarter=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.firstQuarter=="3"){
		 return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_1'  /></center>";
	}
	return "";
	
}

function firstHalfDecemberModule(el,options,rowData){
	if(rowData.firstHalf=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.firstHalf=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.firstHalf=="3"){
		return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_1'  /></center>";
	}
	return "";
	
}

function secondHalfDecemberModule(el,options,rowData){
	if(rowData.secondHalf=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.secondHalf=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.secondHalf=="3"){
		return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_2'  /></center>";
	}
	return "";
}

function yearsDecemberModule(el,options,rowData){
	if(rowData.years=="1"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/dailysuccess.gif'></center>";
	}else if(rowData.years=="2"){
		return "<center><img id=''  src='${resource_path}/resource/system/images/evaluateManagement/delete.gif'></center>";
	}else if(rowData.years=="3"){
		return "<center><input type=\"checkbox\" name=\"warnObject\" value='"+rowData.orgId+"_"+$("#dailyYear  option:selected").text()+"'  /></center>";
	}
	return "";
	
}

});

</script>