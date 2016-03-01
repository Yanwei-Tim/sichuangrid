<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<%
	String isPlaning = request.getParameter("isPlaning");
	request.setAttribute("isPlaning",isPlaning);
%>
<div class="content">
<div class="ui-corner-all" id="nav">
<div class="btnbanner btnbannerData">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<select name="year" id="year">
</select>
年
<span id="quarterSpan">

</span>
</div>
<span class="lineBetween "></span>
<pop:JugePermissionTag
	ename="checkData">
	<a id="checkData" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>审核</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag
	ename="reportData">
	<a id="reportData" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>上报</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag
	ename="viewData">
	<a id="viewData" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>查看</span></a>
</pop:JugePermissionTag>
<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	
</div>
<div style="clear: both;"></div>
<div style="">
<table id="villageReportSummaryList">
</table>
<div id="villageReportSummaryListPager"></div>
</div>
</div>

<div id="villageReportSummaryDialog"></div>
<div id="checkVillageReportSummaryDialog"></div>

<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getCurrentOrgId();

function onOrgChanged(orgId){
    var	initParam = {
		"reportDataSummary.organization.id":orgId,
		"reportDataSummary.year":$("#year").val(),
		"reportDataSummary.quarter":$("#quarter").val(),
		"isPlaning":${isPlaning}
	}

	$("#basicInfoMationList").setGridParam({
		url:'${path}/baseinfo/villageReportSummaryManage/findVillageReportSummaryForlist.action',
		datatype:'json',
		page:1
	});
	$("#villageReportSummaryList").setPostData(initParam);
	$("#villageReportSummaryList").trigger("reloadGrid");
}

function findReportSummaryByYearAndQuarter(){
	onOrgChanged(getCurrentOrgId());
}

function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#year").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarter").append("<option  value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
	
$(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
	var model=[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"reportOrg.orgName",label:"上报单位",sortable:true,width:147},
				{name:"year",label:"年份",sortable:true,width:127},
				{name:"quarter",label:"季度",sortable:true,width:100,align:'center'},
				{name:"reportDate",label:'上报时间',sortable:false,width:150,align:'center'},
		        {name:"reportStatus",label:'上报状态',sortable:false,width:100,align:'center',formatter:reportStatusFormatter},
		        {name:"reportUser",label:'上报人',sortable:false,width:100,align:'center'},
		        {name:"checkStatus",label:"审核状态",sortable:true,width:100,align:'center',formatter:checkStatusFormatter},
		        {name:"checkDate",label:"审核时间",sortable:true,width:150,align:'center'},
		        {name:"checkUser",label:"审核人",sortable:true,width:100,align:'center'}
		];
	if(${isPlaning}=='1'){
		model=[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"reportOrg.orgName",label:"上报单位",sortable:true,width:147},
				{name:"year",label:"年份",sortable:true,width:127},
				{name:"reportDate",label:'上报时间',sortable:false,width:150,align:'center'},
		        {name:"reportStatus",label:'上报状态',sortable:false,width:100,align:'center',formatter:reportStatusFormatter},
		        {name:"reportUser",label:'上报人',sortable:false,width:100,align:'center'},
		        {name:"checkStatus",label:"审核状态",sortable:true,width:100,align:'center',formatter:checkStatusFormatter},
		        {name:"checkDate",label:"审核时间",sortable:true,width:150,align:'center'},
		        {name:"checkUser",label:"审核人",sortable:true,width:100,align:'center'}
		];
	}else{
		$("#quarterSpan").append('<select name="quarter" id="quarter" onchange="findReportSummaryByYearAndQuarter()"></select>季度');
		getQuarter();
	}
	$("#villageReportSummaryList").jqGridFunction({
		url:'${path}/baseinfo/villageReportSummaryManage/findVillageReportSummaryForlist.action',
		datatype: "json",
		postData:{
			"reportDataSummary.organization.id":getCurrentOrgId(),
			"reportDataSummary.year":$("#year").val(),
			"reportDataSummary.quarter":$("#quarter").val(),
			"isPlaning":${isPlaning}
		},
		mytype:"post",
	    colModel:model,
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		onSelectRow:selectRow
	});
	jQuery("#villageReportSummaryList").jqGrid('setFrozenColumns');
	onOrgChanged(getCurrentOrgId());
	
	
	
	$("#year").change(function(){
		$("#quarter").empty();
		getQuarter();
		onOrgChanged(getCurrentOrgId());
	});
	//刷新
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId());
	});
	
	
	$("#checkData").click(function(){
		var rowIds = $("#villageReportSummaryList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请至少选择一条数据进行审核！"});
			 return;
		}
		$("#checkVillageReportSummaryDialog").createDialog({
			width: 1200,
			height: 600,
			title:${isPlaning}==0?'审核上报信息':'审核年度任务规划',
			url:'${path}/newCountryside/newCountrySideInfoSummary/reportSummaryTabList.jsp?ids='+rowIds+""+'&isPlaning=${isPlaning}',
			buttons: {
				"审核通过":function(){
					var villageReportSummary;
					if(rowIds.length >= 1){
						for(var i = 0; i < rowIds.length; i++){
							villageReportSummary = $("#villageReportSummaryList").getRowData(rowIds[i]);
								if(villageReportSummary.checkStatus == "审核通过"){
									$.messageBox({level:"warn",message:"选中数据已审核通过，无需再审核!"});
									return;
								}else if(villageReportSummary.checkStatus == "审核不通过"){
									$.messageBox({level:"warn",message:"选中数据审核不通过，请检查后再次上报!"});
									return;
								}
						}
					}
					$("#checkStatus").val("1");
					$("#checkSummaryFrom").submit();
				},
				"回退":function(){
					var villageReportSummary;
					if(rowIds.length >= 1){
						for(var i = 0; i < rowIds.length; i++){
							villageReportSummary = $("#villageReportSummaryList").getRowData(rowIds[i]);
								if(villageReportSummary.reportStatus == "已上报" && villageReportSummary.checkStatus == "审核通过"){
									$.messageBox({level:"warn",message:"选中数据已上报，不能回退!"});
									return;
								}
						}
					}
					$("#checkStatus").val("2");
					$("#checkSummaryFrom").submit();
				},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});	
	});
	
	$("#viewData").click(function(){
		var rowIds = $("#villageReportSummaryList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请至少选择一条数据进行查看！"});
			 return;
		}
		$("#checkVillageReportSummaryDialog").createDialog({
			width: 1200,
			height: 600,
			title:${isPlaning}==0?'查看上报信息':'查看年度任务规划',
			url:'${path}/newCountryside/newCountrySideInfoSummary/reportSummaryTabList.jsp?ids='+rowIds+""+'&isPlaning=${isPlaning}',
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});	
	});
	
	$("#reportData").click(function(){
		var rowIds = $("#villageReportSummaryList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请至少选择一条数据进行上报！"});
			 return;
		}
		var villageReportSummary;
		if(rowIds.length >= 1){
			for(var i = 0; i < rowIds.length; i++){
				villageReportSummary = $("#villageReportSummaryList").getRowData(rowIds[i]);
				if(villageReportSummary.checkStatus == "未审核"){
					$.messageBox({level:"warn",message:"选中数据未审核，请审核后上报!"});
					return;
				}else if(villageReportSummary.checkStatus == "审核通过"){
					if(villageReportSummary.reportStatus == "已上报"){
						$.messageBox({level:"warn",message:"选中数据已上报，请不要重复上报!"});
						return;
					}
				}
			}
		}
		$.confirm({
			title:"确认上报",
			message:"确定要上报吗?一经上报，在非审核不通过情况下不可修改",
			okFunc: function(){
				$.ajax({
					url:PATH+"/baseinfo/villageReportSummaryManage/reportAllById.action",
					type:"post",
					data:{
						"ids":rowIds+"",
						"isPlaning":${isPlaning}
					},
					success:function(data){
						if(data==true || data=='true'){
								$("#villageReportSummaryList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功上报信息!"});
						}else{
							$.messageBox({
								level:"error",
								message:data
							});
						}
				    }
			    });
			}
		});
	}); 
	
});


function reportStatusFormatter(el,options,rowData){
	if(rowData.reportStatus==0 || rowData.reportStatus=='0'){
		return "未上报";
	}
	return "已上报";
}
function checkStatusFormatter(el,options,rowData){
	if(rowData.checkStatus==0 || rowData.checkStatus=='0'){
		return "未审核";
	}else if(rowData.checkStatus==1 || rowData.checkStatus=='1'){
		return "审核通过";
	}else{
		return "审核不通过";
	}
}
function selectRow(){
	var count = $("#villageReportSummaryList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("villageReportSummaryList");
	if(selectedCounts == count){
		jqGridMultiSelectState("villageReportSummaryList", true);
	}else{
		jqGridMultiSelectState("villageReportSummaryList", false);
	}
}
	
</script>