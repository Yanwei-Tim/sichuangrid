<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	<div class="container_24">
		<input id="orgId" name="orgId" type="hidden" value="${orgId}"/>
		<input id="serviceTeamId" name="serviceTeamId" type="hidden" value="${serviceTeamId}"/>
		<input id="populationId" name="populationId" type="hidden" value="${serviceObject.populationId}"/>
		<input id="populationType" name="populationType" type="hidden" value="${serviceObject.populationType}"/>
		<div class='clearLine'></div>
		<div style="width: 100%;margin-top:6px">
			<table id="existedServiceTeamMember"></table>
			<div id="existedServiceTeamMemberPager"></div>
		</div>
		<div class='clearLine'></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	$("#existedServiceTeamMember").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','职务','姓名','性别','身份证号码','联系手机','操作'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"orgId",index:"orgId",hidden:true,frozen:true},
	        {name:"position.displayName",index:"position",width:20,frozen:true},
	        {name:'name',index:"name",width:30},
	        {name:'gender',index:'gender',width:20,formatter:genderFormatter,frozen:true},
	        {name:'idCardNo',index:'idCardNo',width:50,frozen:true},
	        {name:'mobile',index:'mobile',sortable:false,width:40},
	        {name:' ',label:"操作",formatter:removeMember,width:30}
		],
		height:350,
		multiselect:false,
    	rowNum:10,
    	rowList:[10,15,20,25,30,50,80,100],
		shrinkToFit:true,
		showColModelButton:false
	});
	$("#existedServiceTeamMember").setGridParam({
		url:"${path}/baseinfo/serviceTeam/serviceObject/findServiceTeamMemberForExistedByServiceObjectInfo.action",
		datatype: "json",
		page:1
	});
	$("#existedServiceTeamMember").setPostData({
		"organizationId":$("#orgId").val(),
		"serviceObject.populationType":$("#populationType").val(),
		"serviceObject.populationId":$("#populationId").val(),
		"serviceObject.serviceTeamId":$("#serviceTeamId").val()
   	});
	$("#existedServiceTeamMember").trigger("reloadGrid");
	$("#existedServiceTeamMember").jqGrid('setFrozenColumns');
	
	
	/**$("#teamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','职务','姓名','性别','身份证号码','联系手机','操作'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	        {name:"orgId",index:"orgId",hidden:true,frozen:true},
	        {name:"position.displayName",index:"position",width:30,frozen:true},
	        {name:'name',index:"name",width:30},
	        {name:'gender',index:'gender',width:20,formatter:genderFormatter,frozen:true},
	        {name:'idCardNo',index:'idCardNo',width:50,frozen:true},
	        {name:'mobile',index:'mobile',sortable:false,width:50},
	        {name:' ',label:"操作",hidedlg:true,formatter:appendMember,width:20}
		],
		height:145,
    	multiselect:false,
    	rowNum:5,
    	rowList:[5,10,15,20,25,30,50,80,100],
		shrinkToFit:true,
		showColModelButton:false
	});
	$("#teamMemberList").setGridParam({
		url:"${path}/baseinfo/serviceTeam/serviceObject/findServiceTeamMemberForAddByServiceObjectInfo.action",
		datatype: "json",
		page:1
	});
	$("#teamMemberList").setPostData({
		"organizationId":$("#orgId").val(),
		"serviceObject.populationType":$("#populationType").val(),
		"serviceObject.populationId":$("#populationId").val(),
		"serviceObject.serviceTeamId":$("#serviceTeamId").val()
   	});
	$("#teamMemberList").trigger("reloadGrid");
	$("#teamMemberList").jqGrid('setFrozenColumns');*/
})
function deleteFromGrid(id){
	$.ajax({
		url:'${path}/baseinfo/serviceTeam/serviceObject/removeServers.action',
		data:{
			"serviceObject.populationType":$("#populationType").val(),
			"serviceObject.populationId":$("#populationId").val(),
			"serviceObject.serviceTeamMemberId":id,
			"serviceObject.serviceTeamId":$("#serviceTeamId").val()
		},
		success:function(data){
			var data = $("#existedServiceTeamMember").getRowData(id);
			$("#existedServiceTeamMember").delRowData(id);
			$("#teamMemberList").addRowData(id,data,"first");
			$.messageBox({message:"该服务成员已经成功移除!"});
		}
	})
}
function removeMember(el,option,rowdata){
	return "<a href='javascript:;' onclick=deleteFromGrid('"+rowdata.id+"') >移除</a>";
}
/**
function transferSearchToBindedGrid(id){
	$.ajax({
		async:false,
		url: "${path}/baseinfo/serviceTeam/serviceObject/addServers.action?selectedIds="+id,
		data:{
			"serviceObject.populationType":$("#populationType").val(),
			"serviceObject.populationId":$("#populationId").val(),
			"serviceObject.serviceTeamId":$("#serviceTeamId").val()
		},
		success:function(datas){
			$.messageBox({message:"成功新增监护人员！"});
			var data = $("#teamMemberList").getRowData(id);
			$("#teamMemberList").delRowData(id);
			$("#existedServiceTeamMember").addRowData(id,data,"first");
		}
	});
}
function appendMember(el,option,rowdata){
	return "<a href='javascript:;' onclick=transferSearchToBindedGrid('"+rowdata.id+"') >添加</a>";
}*/
</script>
