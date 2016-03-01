<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#versionsList").jqGridFunction({
	   	url:'${path}/sysadmin/versionsManage/versionsList.action',
		colNames:['id','版本号','版本说明'],
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'versionId',sortable:true,index:'versionId'},
	   		{name:'versionContent',sortable:false}

	   	],
	   	shrinkToFit:true,
	   	showColModelButton:false
	    <pop:JugePermissionTag ename="viewRole">
        	,ondblClickRow: doubleClickTable
  		</pop:JugePermissionTag>
	});

	$("#reload").click(function(){
		$("#versionsList").trigger("reloadGrid");
	});

	$("#add").click(function(event){
		$("#versionsDialog").createDialog({
			width:650,
			height:550,
			title:'新增版本信息',
			url:'${path}/sysadmin/versionsManage/dispatch.action?mode=add',
			buttons: {
			  "保存" : function(event){
			      $("#versionsForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	$("#update").click(function(event){
		var selectedId = $("#versionsList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var versions =  $("#versionsList").getRowData(selectedId);
		reSetPatelConfig(versions.versionId);
		$("#versionsDialog").createDialog({
			width:550,
			height:520,
			title:'修改版本信息',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/versionsManage/dispatch.action?mode=edit&versions.versionId='+ versions.versionId,
			buttons: {
			   "保存" : function(event){
			      $("#versionsForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
	   });
	});

	$("#view").click(function(){
		var selectedId = $("#versionsList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		doubleClickTable(selectedId);
	});

});


function doubleClickTable(rowid){
		var versions =  $("#versionsList").getRowData(rowid);
		$("#versionsDialog").createDialog({
			width:550,
			height:520,
			title:'查看版本信息',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/versionsManage/dispatch.action?mode=view&versions.versionId='+ versions.versionId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
}

function reSetPatelConfig(roleId){
	$.ajax({
		async: false ,
		url:"${path }/sysadmin/userManage/reSetPatelConfig.action",
	   	data:{
		"roleId":roleId
		}
	});
}
</script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addRole">
				<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateRole">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewRole">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="versionsList"></table>
		<div id="versionsListPager"></div>
	</div>
	<div id="versionsDialog" style="overflow: hidden"></div>
</div>

