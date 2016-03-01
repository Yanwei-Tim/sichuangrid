<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<div class="content" >
	<div class="ui-corner-all" id="nav">
<%-- 		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a> --%>
		<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-cakan"></strong>查看</span></a>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>查询</span></a>
		<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a>
	</div>		
	
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="mobileInfoList"> </table>
		<div id="mobileInfoListPager"></div>
	</div>
</div>

<div id="mobileInfoDialog"></div>

<script type="text/javascript">
$(document).ready(function(){
	$("#mobileInfoList").jqGridFunction(gridData());

	onLoad();
	/*
	$("#add").click(function(){
		$("#mobileInfoDialog").createDialog({
			width:500,
			height:240,
			title:"新增 手机IMSI相关信息",
			url:"${path}/sysadmin/mobileInfo/dispatch.action?mode=add&orgId=1",
			buttons: {
				"保存" : function(event){
					$("#maintainForm").submit();
					afterLoad();
				},
	   			"关闭" : function(){
			        	$(this).dialog("close");
			   	}
			}
		});
	});
	*/

	$("#view").click(viewData);

	$("#reload").click(onLoad);
	
	$("#export").click(function(event){
		if($("#export").attr("disabled")=="true" || $("#export").attr("disabled")=="disabled"){
			return;
		}
		if($("#mobileInfoList").getGridParam("records")>0){
			$("#mobileInfoDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出手机IMSI相关信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'mobileInfoList',
					downloadUrl:'${path}/sysadmin/mobileInfo/downloadMobileInfo.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}
	});
	
	$("#search").click(function(event){
		if($("#search").attr("disabled")=="true" || $("#search").attr("disabled")=="disabled"){
			return;
		}
		$("#mobileInfoDialog").createDialog({
			width: 500,
			height:200 ,
			title:'手机IMSI相关信息查询-请输入查询条件',
			url:'${path}/sysadmin/mobileInfo/dispatch.action?mode=search&orgId=1',
			buttons: {
		   		"查询" : function(event){
					searchMobileInfo();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

});

function searchMobileInfo(){
	$("#mobileInfoList").setGridParam({
		url:"${path}/sysadmin/mobileInfo/findMobileInfosByQueryCondition.action",
		datatype: "json",
		page:1
	});
	$("#mobileInfoList").setPostData({
		"mobileInfo.imsiNo":$("#imsiNo").val(),
		"mobileInfo.name":$("#name").val(),
		"mobileInfo.mobileNumber":$("#mobileNumber").val(),
		"orgId":$("#searchOrgId").val()
	});
	$("#mobileInfoList").trigger("reloadGrid");
}

function viewData(){
	var id = $("#mobileInfoList").getGridParam("selrow");

	if(id == null){
		return;
	}
	if($("#view").attr("disabled")=="true" || $("#view").attr("disabled")=="disabled"){
		return;
	}
	$("#mobileInfoDialog").createDialog({
		width:500,
		height:240,
		title:"查看手机IMSI相关信息",
		url:"${path}/sysadmin/mobileInfo/dispatch.action?mode=view&mobileInfo.id="+id,
		buttons: {
   			"关闭" : function(){
		        	$(this).dialog("close");
		   	}
		}
	});
}

function onLoad(){
	$("#mobileInfoList").setGridParam({
		url:'${path}/sysadmin/mobileInfo/findMobileInfos.action',
		datatype: "json",
		page:1
	});
	$("#mobileInfoList").setPostData({
		"orgId":1
	});
	$("#mobileInfoList").trigger("reloadGrid");
}

function gridData(){
	return {
		datatype: "local",
		colModel:[
	    		{name:"id", index:"id", hidden:true},
	  		{name:"imsiNo", index:"imsiNo",label:"IMSI号",width:100},
  			{name:"name", index:"name",label:"户主名称",width:100},
  			{name:"mobileNumber", index:"mobileNumber",label:"手机号码",width:100},
  			{name:"organization.orgName", index:"orgInternalCode",label:"所属网格",width:100},
  			{name:"acceptTime", index:"acceptTime",label:"受理时间",width:100},
  			{name:"effectivelyTime", index:"effectivelyTime",label:"有效时间",width:100}
	  	],
	   	loadComplete: afterLoad,
	 	ondblClickRow: doubleClickTable,
	 	onSelectRow: selectRow
	}
}

function selectRow(){
	$("#view").buttonEnable();
}

function afterLoad(){
	$("#view").buttonDisable();
	if($("#mobileInfoList").getGridParam("records")==0){
		$("#export").buttonDisable();
	} else {
		$("#export").buttonEnable();
	}
}

function doubleClickTable(){
	viewData();
}
</script>