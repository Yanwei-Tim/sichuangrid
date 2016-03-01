<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% Long orgId =ThreadVariable.getSession().getOrganization().getId(); %>
<div class="content">
	<input id="orgId" type="hidden" value="<%=orgId %>" />
	<input id="teamId" type="hidden" value="${serviceObject.serviceTeamId}"/>
	<input id="memberId" type="hidden" value="${serviceObject.serviceTeamMemberId}"/>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
	<table id="serviceObjectList"></table>
	<div id="serviceObjectListPager"></div>
	</div>
	<div id="serviceObjectDialog"></div>
	<div id="serviceObjectViewDlg"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="ServiceObjectManagement">
		<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var dialogWidth=1090;
var dialogHeight=590;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();
$(document).ready(function(){
	$("#serviceObjectList").jqGridFunction({
		datatype: "local",
		colNames:['sid','populationId','姓名','所属网格','性别','身份证号码','人员类型En','人员类型','服务者','服务记录','最新接受服务时间'],
	   	colModel:[
			{name:"sid",index:"sid",hidden:true,key:true,frozen:true},
	        {name:"populationId",index:"populationId",hidden:true,frozen:true},
	        {name:"name",index:'name',width:100,formatter:nameFont,sortable:false},
	        {name:"organization.orgName",index:"organization.orgName",sortable:false},
		    {name:'gender',formatter:genderFormatter,sortable:true,width:70},
		    {name:'idCardNo',index:'idCardNo',width:130,formatter:idCardNoFont,sortable:false,hidden:true},
		    {name:'populationType',index:'populationType',hidden:true,width:70,sortable:false,frozen:true},
		    {name:'attentionPopulationTypeCname',index:'attentionPopulationTypeCname',width:70,sortable:false,frozen:true},
		    {name:'servicer',index:'servicer',sortable:false,frozen:true,hidden:true},
		    {name:'serviceRecord',index:'serviceRecord',width:100,sortable:false,frozen:true,hidden:true},
	        {name:'lastestServiceRecordDate',index:'lastestServiceRecordDate',sortable:false}
		],
		multiselect:false,
		ondblClickRow: doubleClickTable
	});
	initParam = {"serviceObject.serviceTeamId":${serviceObject.serviceTeamId},"serviceObject.serviceTeamMemberId":${serviceObject.serviceTeamMemberId}};
	$("#serviceObjectList").setGridParam({
		url:"${path}/baseinfo/serviceTeam/serviceObject/findServiceObjectForExistedByServiceMemberIdAndTeamId.action",
		datatype: "json",
		page:1
	});
	$("#serviceObjectList").setPostData(initParam);
	$("#serviceObjectList").jqGrid('setFrozenColumns');
	$("#serviceObjectList").trigger("reloadGrid");

	function doubleClickTable(selectedId){
		var rowData = $("#serviceObjectList").getRowData(selectedId);
		if(rowData==null){
		 	return;
		}
		$("#serviceObjectViewDlg").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看服务对象信息",
			url:"${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=view&populationId="+rowData.populationId+"&populationType="+rowData.populationType+"&serviceTeamId="+$("#teamId").val(),
			buttons: {
		  		 "关闭" : function(){
		        	$(this).dialog("close");
		   			}
			}
		});
	}
});
function nameFont(el,options,rowData){
	if(rowData.death == true || rowData.death == "true"){
		return "<font color='red'>"+rowData.name+"</font>";
	}
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<font color='#778899'>"+rowData.name+"</font>";
	}
	return "<font color='#000'>"+rowData.name+"</font>";
}
function idCardNoFont(el,options,rowData){
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}else{
		return "<font color='#000'>"+rowData.idCardNo+"</font>";
	}
}
function getSelectedIds(){
	var selectedIds=$("#serviceObjectList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function getSelectedId(){
	var selectedIdLast = null;
    var selectedIds = $("#serviceObjectList").jqGrid("getGridParam", "selarrrow");
    
    for(var i=0;i<selectedIds.length;i++){
		selectedIdLast = selectedIds[i];
   }
   return selectedIdLast;
}
</script>