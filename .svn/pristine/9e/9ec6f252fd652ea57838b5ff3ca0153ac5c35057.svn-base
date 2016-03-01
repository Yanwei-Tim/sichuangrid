<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<a id="search" href="javascript:void(0)" class="normalBut"><span>高级搜索</span></a>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="mobileErrorLogList"></table>
		<div id="mobileErrorLogListPager"></div>
	</div>
	<div style="display: none;">
	    <span id="title">手机客户端错误日志采集</span>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#mobileErrorLogList").jqGridFunction({
	   	url:'${path}/mobileErrorLogsManage/findMobileErrorLogs.action',
	   	postData: {
			"orgId":getCurrentOrgId()
        },
		datatype: "json",
	   	colModel:[
            {name:"id",index:"id",hidden:true,sortable:false,hidedlg:true,frozen:true},
	   		{name:'name',label:'用户名',width:100,index:'name', sortable:true},
            {name:"errorLogsName",index:'errorLogsName',label:'错误标题',sortable:true,width:120,frozen:true},
			{name:"错误详情",label:'错误详情',width:90,sortable:false,frozen:true,align:"center",formatter:errorLogsFormat},
			{name:"occurDate",index:"occurDate",label:"错误发生时间",sortable:true,width:150}
	   	],
	   	showColModelButton:false
	});

});

$("#reload").click(function(){
	reload();
}); 

function reload(){
	var params = {
		"orgId":getCurrentOrgId()
    }
	$("#mobileErrorLogList").setGridParam({
		url:'${path}/mobileErrorLogsManage/findMobileErrorLogs.action',
		datatype: "json",
		page:1,
		mtype:"get"
	});
	$("#mobileErrorLogList").setPostData(params);
	$("#mobileErrorLogList").trigger("reloadGrid");
}

var title=$("#title").html();
$("#search").click(function(event){
	$("#dispatchDocumentsMaintanceDialog").createDialog({
		width: 460,
		height: 300,
		title:title+'查询-请输入查询条件',
		url:'${path}/mobileErrorLogsManage/prepareMobileErrorLogs.action?mode=search',
		buttons: {
			"查询" : function(event){
				submitSearchMobileErrorLogs();
				$(this).dialog("close");
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
});
function submitSearchMobileErrorLogs(){
	var data=$("#maintainForm").serializeArray();
	var params ={
		"mobileErrorLogs.orgId":getCurrentOrgId()
	};
	for(var i in data){
		params[data[i].name]=data[i].value ;
	}
	$("#mobileErrorLogList").setGridParam({
		url:'${path}/mobileErrorLogsManage/advancedSearchMobileErrorLogs.action',
		datatype: "json",
		page:1,
		mtype:"get"
	});
	$("#mobileErrorLogList").setPostData(params);
	$("#mobileErrorLogList").trigger("reloadGrid");
}
function errorLogsFormat(el, options, rowData){
	return "<a href='${path}/mobileErrorLogsManage/downLoadMobileErrorLogs.action?occurDate="+rowData.occurDate+"'><span style='color:#ff0000;'>下载详情</span></a>";
	
} 
</script>
