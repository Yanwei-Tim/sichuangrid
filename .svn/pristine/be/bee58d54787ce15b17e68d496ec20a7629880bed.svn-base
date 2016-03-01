<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
request.setAttribute("orgLevel",request.getParameter("orgLevel"));
%>
<div class="content">
<div class="ui-corner-all" id="nav"></div>
	<div class='clearLine'>&nbsp;</div>
	<div style="width: 100%;">
		<table id="statisticPartyList">
		</table>
	</div>
</div>
<script type="text/javascript">
function onOrgChanged(orgId){
	var initParam = {
		"searchVo.orgId": orgId
	}
	$("#statisticPartyList").setGridParam({
 		url:'${path}/townPartyOrgManage/statisticTownPartyOrgBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#statisticPartyList").setPostData(initParam);
	$("#statisticPartyList").trigger("reloadGrid");
}

$(function(){
	var colModel = [{name:'organization.orgName', index:'organization.orgName',label:'单位', width:150, sortable:false, align:'center', hidden:false}]
	var villageColModel = [
	        	    	{name:'villageOrgNum', index:'villageOrgNum',label:'村/社区党组织数', width:150, sortable:false, align:'center', hidden:false}, 	 	
	        	    	{name:'villageMemberNum', index:'villageMemberNum',label:'村/社区党员数', width:150, sortable:false, align:'center', hidden:false}, 	
	        	    	{name:'villageRecordNum', index:'villageRecordNum',label:'村/社区党组织活动记录数', width:170, sortable:false, align:'center', hidden:false} 	
	        	   	]
	var  townColModel = [
	        	    	{name:'townOrgNum', index:'townOrgNum',label:'乡镇/街道党组织数', width:150, sortable:false, align:'center', hidden:false}, 	 	
	        	    	{name:'townMemberNum', index:'townMemberNum',label:'乡镇/街道党员数', width:150, sortable:false, align:'center', hidden:false}, 	
	        	    	{name:'townRecordNum', index:'townRecordNum',label:'乡镇/街道党组织活动记录数', width:170, sortable:false, align:'center', hidden:false} 	
	        	   	]
   	if(isTownUp()){
   		colModel = colModel.concat(townColModel).concat(villageColModel);
	}else if(isVillageUp()){
		colModel = colModel.concat(villageColModel)
	}
	$("#statisticPartyList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:colModel,
	  	multiselect:false,
	  	rowNum:-1,
	  	height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-65
	});
	if(null!=getCurrentOrgId()){
		onOrgChanged(getCurrentOrgId());
	}
	
	
});

</script>
