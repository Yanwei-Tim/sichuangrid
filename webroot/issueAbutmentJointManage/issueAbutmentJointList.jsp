<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
		<div class="btnbanner btnbannerData" style="float:left">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<div class="ui-corner-all" id="nav" style="position:relative;float:left;">
			<pop:JugePermissionTag ename="searchIssueAbutmentJoint">
				<a id="search" href="javascript:void(0)"><span>查询</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewIssueAbutmentJoint">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="importIssueAbutmentJoint">
				<a id="import" href="javascript:void(0)"><span>导入</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="refreshIssueAbutmentJoint">
				<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
			</pop:JugePermissionTag>
		</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueAbutmentJointList"> </table>
		<div id="issueAbutmentJointListPager"></div>
	</div>
	<div id="issueAbutmentJointDialog"></div>
</div>	
<script type="text/javascript">
<pop:formatterProperty name="issueJointType" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE" />
<pop:formatterProperty name="issueJointTypeSub" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE_SUB" />

$(document).ready(function() {
//发生时间 
function occurDateFormatter(el,options,rowData){
	if(rowData.occurDate!=null && rowData.hours!=null && rowData.minute!=null){
		return rowData.occurDate+' '+ rowData.hours + ":" +rowData.minute;
	}
	return rowData.occurDate;
}
//状态
function statusFormatter(el,options,rowData){
	return "已办结"
}
//
function issueJointTypeFullFormatter(el,options,rowData){
	var result="";
	if(rowData.issueJointTypeSub.id!=null && rowData.issueJointType.id != null){
		result= issueJointTypeData[rowData.issueJointType.id] + "-" + issueJointTypeSubData[rowData.issueJointTypeSub.id];
	}
	return result;
}
function isUserOrgFunctional(){
	 var isOrgFunctional= false;
	$.ajax({
		async: false,
		url:PATH+'/issueJointManage/getLoginUserOrgType.action',
		success:function(data){
			if(data!=null && typeof(data) !="undefined" &&(data==1 || data=="1")){
				isOrgFunctional=true;
			}
		}
	});
	return isOrgFunctional;
}	
	 //生成组织活动列表
	$("#issueAbutmentJointList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel:[
	    	{name:"id", index:"id", hidden:true,frozen : true},
  			{name:"serialNumber", index:"serialNumber",label:"服务单号",width:200, frozen : true,align:'center',sortable:true},
  			{name:"subject", index:"subject",label:"事件名称",width:120, align:'center',frozen : true,sortable:true},
  			{name:"dealUserName", index:'dealUserName', label:'承办人', width:120, align:'center',sortable:true},
  			{name:"lastDealTime", index:'lastDealTime', label:'最后处理时间', width:150,formatter:'date',align:'center',formatoptions:{newformat: 'Y-m-d'},sortable:true},
  			{name:"feedbackTime", index:'feedbackTime', label:'反馈时间', width:150, formatter:'date',align:'center',formatoptions:{newformat: 'Y-m-d'},sortable:true},
  			{name:"maincharacters", index:'maincharacters', label:'主要当事人', width:120, align:'center',sortable:true},
  		  	{name:"mobile", index:'mobile', label:'主要当事人手机', width:120,sortable:true},
  		  	{name:"issueJointTypeFull", index:'issueJointType', label:'事件类别', width:120,align:'center',sortable:false,formatter:issueJointTypeFullFormatter},
  		  	{name:"occurDate", index:'occurDate', label:'发生时间', width:150, formatter:occurDateFormatter,align:'center',sortable:true},
  		  	{name:"status", index:'status', label:'状态', width:120,formatter:statusFormatter,align:'center',sortable:false},
  		  	{name:"dealUserName", index:'dealUserName', label:'最后操作用户名称', width:120,align:'center',sortable:true}
  			
	  	],
	  	multiselect:true,
	  	onSelectAll:function(){},
    	loadComplete: function(){
    		
    	},
		<pop:JugePermissionTag ename="viewIssueAbutmentJoint">
        	ondblClickRow: viewIssueJoint,
		</pop:JugePermissionTag>
		onSelectRow:function(){},
		height:$(".ui-layout-center").height()-150
	});
	jQuery("#issueAbutmentJointList").jqGrid('setFrozenColumns');
	
	$("#reload").click(function (){
		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			onOrgChanged(getCurrentOrgId());
		}
		
	}).click();
	
	$("#import").click(function(event){
		if(isUserOrgFunctional()){
			$.messageBox({level:'warn',message:"职能部门用户不能导入事件！"});
	 		return;
		}
		$("#issueAbutmentJointDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:PATH+"/common/import.jsp?isNew=1&dataType=issueJoint&dialog=issueAbutmentJointDialog&startRow=5&templates=ISSUEJOINT&listName=issueAbutmentJointList",
			buttons:{
				"导入" : function(event){
					$("#mForm").submit();
				},
			   	"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			},
			  shouldEmptyHtml:false
		});
	});
	$("#search").click(function(event){
		$("#issueAbutmentJointDialog").createDialog({
			width:700,
			height:320,
			title:'对接事件查询-请输入查询条件',
 	 		url:PATH+'/issueJointManage/dispatchOperate.action?mode=search',
			buttons: {
		   		"查询" : function(event){
					searchIssueJoint($("#searchIssueJointForm").serializeObject());
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}); 
	
	$("#view").click(function(){
		var selectedIds = $("#issueAbutmentJointList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds == null || selectedIds.length > 1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		var selectedId = getSelectedIdLastForIssueJoint();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewIssueJoint(selectedId);
	});
});

function viewIssueJoint(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#issueAbutmentJointList").getRowData(selectedId);
	if(rowData==null){
		 return;
	}
	$("#issueAbutmentJointDialog").createDialog({
		width:800,
		height:500,
		title:"查看对接事件详情信息",
 		url:PATH+'/issueJointManage/dispatchOperate.action?mode=view&issueJoint.id='+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function searchIssueJoint(serializeObject){
	
	search($.extend({
		'issueJointVo.createOrg.id':getCurrentOrgId()
	},serializeObject));
	
}

function search(postData){
	$("#issueAbutmentJointList").setGridParam({
		url:PATH+'/issueJointManage/searchIssueJointForList.action',
		datatype: "json",
		page:1
	});
	$("#issueAbutmentJointList").setPostData(postData);
	$("#issueAbutmentJointList").trigger("reloadGrid");
}

function onOrgChanged(orgId){
	var initParam = {
			"issueJointVo.createOrg.id": orgId
		}
		$("#issueAbutmentJointList").setGridParam({
			url:PATH+'/issueJointManage/searchIssueJointForList.action',
			datatype: "json",
			page:1
		});
		$("#issueAbutmentJointList").setPostData(initParam);
		$("#issueAbutmentJointList").trigger("reloadGrid");
}

function getSelectedIdLastForIssueJoint(){
	var selectedId;
	var selectedIds = $("#issueAbutmentJointList").jqGrid("getGridParam", "selarrrow");
		selectedId = selectedIds[selectedIds.length-1];
	return selectedId;
}
</script>