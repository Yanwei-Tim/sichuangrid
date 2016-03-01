<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div
	style="height: 100%; overflow: auto; overflow-x: hidden; position: relative;">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<select id="infoScreening" style="width:100px;height:25px;" onchange="infoScreening(this.value)">
				<option value="all">全部</option>
				<option value="superior">上级</option>
				<option value="sameLevel">本级</option>
				<option value="jurisdiction">下辖</option>
			</select>
			<pop:JugePermissionTag ename="searchWorkContact">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
			<span class="lineBetween "></span>
			
			<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="width: 100%;" id="grid_content">
			<table id="workContactList"></table>
			<div id="workContactListPager"></div>
		</div>
		<div id="workContactMaintanceDialog"></div>
		<div id="searchDialog"></div>
	</div>
</div>

<script type="text/javascript">
var dialogWidth=550;
var dialogHeight=270;
var currentOrgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgLevel().getId()'/>";
$(document).ready(function(){
	$("#workContactList").jqGridFunction({
		url:"${path}/contact/workContactManage/findWorkContacts.action",
		datatype: "json",
		colNames:['id','organization.id','organization.orgLevel.id','部门','姓名','固定电话','联系手机'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false,hidedlg:true},
	        {name:"organization.id",index:"organization.id",hidden:true,sortable:false,hidedlg:true},
	        {name:"organization.orgLevel.id",index:"organization.orgLevel.id",hidden:true,sortable:false,hidedlg:true},
	        {name:'organization.orgName',sortable:false,width:350},
	        {name:'name',sortable:true,width:200},
	        {name:"fromUser.workPhone",sortable:true,width:200,formatter:workPhoneFormatter},
	  		{name:"mobileNumber",sortable:true,width:200,formatter:operatorFormatter}
		],
		scrollrow:true
	});
	
	$("#reload").click(function(event){
		reloadWorkContactList();
		$("#infoScreening").find("option[value='all']").attr("selected",true);
	});

	$("#search").click(function(event){
		$("#searchDialog").createDialog({
			width: dialogWidth,
			height: 200,
			datatype: "json",
			title:'平台内联系人查询-请输入查询条件',
			url:'${path}/interaction/contact/workContact/searchWorkContactDlg.jsp',
			buttons: {
				"查询" : function(event){
					refreshWorkContactGrid();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});	
});

function operatorFormatter(el,options,rowData){
		var data = rowData.organization.orgLevel.id;
		if(currentOrgId<data){
			return "";
		}else{
			return rowData.mobileNumber;
		}
}

function workPhoneFormatter(el,options,rowData){
	var data = rowData.organization.orgLevel.id;
	if(currentOrgId<data){
		return "";
	}else{
		return rowData.fromUser.workPhone;
	}
}

//联系人筛选
function infoScreening(level){
	if(level==null || level==undefined || level==''){
		 $.messageBox({
			 message:"筛选信息失败，请重试",
			 level: "error"
			 });
	}
	var url="${path}/contact/workContactManage/findWorkContacts.action";
	$("#workContactList").setGridParam({
			url:url,
			postData:{
				"leavel":level
			}
	});
	$("#workContactList").trigger("reloadGrid");
}

function reloadWorkContactList(){
	$("#workContactList").setGridParam({
		url:"${path}/contact/workContactManage/findWorkContacts.action?leavel=all",
		datatype: "json",
		page:1
	});
	$("#workContactList").trigger("reloadGrid");
	$("#infoScreening").find("option[value='all']").attr("selected",true);
}

function refreshWorkContactGrid() {
	var workContactVoName=$('#workContactVoName').val();
	var workContactVoMobileNumber = $("#workContactVoMobileNumber").val();
	var workContactOrgId=$("#workContactOrgId").val();
	var workPhone=$("#workContactPhone").val();
	$("#workContactList").setGridParam({
		url:'${path}/contact/workContactManage/searchWorkContacters.action',
		postData: {
			"contacterVo.name":workContactVoName,
			"contacterVo.mobileNumber":workContactVoMobileNumber,
			"contacterVo.organization.id":workContactOrgId,
			"contacterVo.owner.workPhone":workPhone
		}
	});
	$("#workContactList").trigger("reloadGrid");
	$("#infoScreening").find("option[value='all']").attr("selected",true);
}
</script>