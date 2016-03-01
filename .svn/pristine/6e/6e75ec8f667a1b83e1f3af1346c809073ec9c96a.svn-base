<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<input id="orgId" name="orgId" type="hidden" value="${orgId}"/>
	<input id="serviceTeamId" name="serviceTeamId" type="hidden" value="${serviceTeamId}"/>
	<input id="selectedIds" name="selectedIds" type="hidden" value="${selectedIds}"/>
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="teamMemberList"> </table>
		<div id="teamMemberListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
$(document).ready(function(){
	$("#teamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','职务','姓名','性别','身份证号码','联系手机'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	        {name:"orgId",index:"orgId",hidden:true,frozen:true},
	        {name:"position.displayName",index:"position",formatter:positionFormatter,width:20,frozen:true},
	        {name:'name',index:"name",width:50},
	        {name:'sex',index:'sex',width:20,formatter:genderFormatter,frozen:true},
	        {name:'idCardNo',index:'idCardNo',width:80,frozen:true},
	        {name:'mobile',index:'mobile',sortable:false,width:50}
		],
		height:350,
    	rowNum:10,
    	rowList:[10,15,20,25,30,50,80,100],
		multiselect:true,
		shrinkToFit:true,
		showColModelButton:false
	});
	$("#teamMemberList").setGridParam({
		url:"${path}/baseinfo/serviceTeamMember/findServiceTeamMemberInTeam.action",
		datatype: "json",
		page:1
	});
	$("#teamMemberList").setPostData({
		"teamId":$("#serviceTeamId").val()
   	});
	$("#teamMemberList").trigger("reloadGrid");
	$("#teamMemberList").jqGrid('setFrozenColumns');
})
	function addServers(){
		var selectedIds = $("#teamMemberList").jqGrid("getGridParam", "selarrrow");
		$.ajax({
			async:false,
			url: "${path}/baseinfo/serviceTeam/serviceObject/addServers.action?selectedIds="+selectedIds,
			data:{
				"populations":$("#selectedIds").val(),
				"serviceTeamId":$("#serviceTeamId").val()
			},
			success:function(datas){
				$.messageBox({message:"成功新增监护人员！"});
				$("#serviceObjectDialog").dialog("close");
				$("#serviceObjectList").trigger("reloadGrid");
			}
		});
	}
</script>