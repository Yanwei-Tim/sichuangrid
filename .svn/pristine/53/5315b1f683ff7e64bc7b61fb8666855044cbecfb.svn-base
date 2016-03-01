<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
request.setAttribute("accountType",request.getParameter("accountType"));
%>
<div id="commonReportList"></div>
<script>
var CommonReport={
		id:null,
		loadCompleted:function(){
			loadCountDatas();
			if($("#commonReportList .editEnable").length>0){
				$(".edit").show().removeClass("on").html("<span>刷新并编辑</span>");
			}else{
				$(".edit").hide();
			}
		},
		init:function(){
			this.orgId = $("#currentOrgId").val();
			this.year = $("#year").val();
			this.month = $("#month").val();
			this.accountType = "${accountType}";
			return this;
		}
}.init();
$(function(){
	if(isTownOrganization()){
		$("#commonReportList").load("${path}/xichang/working/report/mould/townOneMould.jsp",postData,CommonReport.loadCompleted);
	}else if(isVillageOrganization()){
		$("#commonReportList").load("${path}/xichang/working/report/mould/villageMould.jsp",postData,CommonReport.loadCompleted);
	}else if(isDistrictOrganization()){
		$("#commonReportList").load("${path}/xichang/working/report/mould/districtMould.jsp",postData,CommonReport.loadCompleted);
	}else{
		$("#commonReportList").load("${path}/xichang/working/report/mould/districtMould.jsp",postData,CommonReport.loadCompleted);
	}
})
function loadPageByTownType(townType){
	switch(townType){
	case '1':
		$("#commonReportList").load("${path}/xichang/working/report/mould/townOneMould.jsp",postData,CommonReport.loadCompleted);
		break;
	case '2':
		$("#commonReportList").load("${path}/xichang/working/report/mould/townTwoMould.jsp",postData,CommonReport.loadCompleted);
		break;
	}
}

function loadPageByDistrictType(districtType){
	switch(districtType){
	case '1':
		$("#commonReportList").load("${path}/xichang/working/report/mould/districtMould.jsp",postData,CommonReport.loadCompleted);
		break;
	case '2':
		$("#commonReportList").load("${path}/xichang/working/report/mould/xiChangOneMould.jsp",postData,CommonReport.loadCompleted);
		break;
	case '3':
		$("#commonReportList").load("${path}/xichang/working/report/mould/xiChangTwoMould.jsp",postData,CommonReport.loadCompleted);
		break;
	case '4':
		$("#commonReportList").load("${path}/xichang/working/report/mould/xiChangThreeMould.jsp",postData,CommonReport.loadCompleted);
		break;
	}
}

function editAccountReport(){
	CommonReport.init();
	$.dialogLoading("open");
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/account/reportManage/editAccountReport.action",
		data:{
			"accountReport.id":CommonReport.id,
			"accountReport.organization.id":CommonReport.orgId,
			"accountReport.reportYear":CommonReport.year,
			"accountReport.reportMonth":CommonReport.month,
			"accountReport.accountType":CommonReport.accountType,
			"accountReport.reportType":getReportType(),
			"accountReport.content":JSON.stringify(getReportContent())
		},
		success:function(response){
			$.dialogLoading("close");
			if(response!=null && response.id!=null){
				//$.messageBox({message:"报表已成功保存！"});
				loadCountDatas();
			}else{
				$.messageBox({level:"error",message:"报表加载错误"});
			}
			$("#canCreateStatistic").val('0');
		}
	})
}
function saveAccountReport(){
	CommonReport.init();
	$.dialogLoading("open");
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/account/reportManage/saveAccountReport.action",
		data:{
			"accountReport.id":CommonReport.id,
			"accountReport.organization.id":CommonReport.orgId,
			"accountReport.reportYear":CommonReport.year,
			"accountReport.reportMonth":CommonReport.month,
			"accountReport.accountType":CommonReport.accountType,
			"accountReport.reportType":getReportType(),
			"accountReport.content":JSON.stringify(getReportContent())
		},
		success:function(response){
			$.dialogLoading("close");
			if(response!=null && response.id!=null){
				$.messageBox({message:"报表已成功保存！"});
				loadCountDatas();
			}else{
				$.messageBox({level:"error",message:response});
			}
		}
	})
}
function refershAccountReport() {
	CommonReport.init();
	$.dialogLoading("open");
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/account/reportManage/refershAccountReport.action",
		data:{
			"accountReport.id":CommonReport.id,
			"accountReport.organization.id":CommonReport.orgId,
			"accountReport.reportYear":CommonReport.year,
			"accountReport.reportMonth":CommonReport.month,
			"accountReport.accountType":CommonReport.accountType,
			"accountReport.reportType":getReportType(),
			"accountReport.content":JSON.stringify(getReportContent())
		},
		success:function(response){
			$.dialogLoading("close");
			if(response!=null && response.id!=null){
				$.messageBox({message:"报表重新统计成功！"});
				loadCountDatas();
			}else{
				$.messageBox({level:"error",message:response});
			}
		}
	})
}

function loadCountDatas(){
	CommonReport.init();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/account/reportManage/findAccountReportBySearchVo.action",
		data:{
			"searchVo.orgId":CommonReport.orgId,
			"searchVo.reportYear":CommonReport.year,
			"searchVo.reportMonth":CommonReport.month,
			"searchVo.accountType":CommonReport.accountType,
			"searchVo.reportType":getReportType()
		},
		success:function(response){
			CommonReport.id = null;
			var data = {};
			if(response!=null && response.contentObject!=null){
				CommonReport.id = response.id;
				data = response.contentObject;
			}
			var $rows = $("tr.rowCount");
			for(var i=0;i<$rows.length;i++){
				var $cols = $rows.eq(i).find("td[id*='col']");
				var rowData = data[$rows[i].id];
				for(var j=0;j<$cols.length;j++){
					var count = 0;
					if(rowData!=null && rowData[$cols[j].id]!=null){
						count = rowData[$cols[j].id]
					}
					$rows.eq(i).find("#"+$cols[j].id).text(count);
				}
			}
			$("#canCreateStatistic").val('1');
		}
	})
}
function getReportContent(){
	var params = {};
	var $rows = $("tr.rowCount");
	for(var i=0;i<$rows.length;i++){
		if($rows[i].id!=null && $rows[i].id!=""){
			var $cols = $rows.eq(i).find("td[id*='col']");
			var rowData = {};
			for(var j=0;j<$cols.length;j++){
				rowData[$cols[j].id]=$rows.eq(i).find("#"+$cols[j].id).text();
			}
			params[$rows[i].id]=rowData;
		}
	}
	return params;
}

</script>