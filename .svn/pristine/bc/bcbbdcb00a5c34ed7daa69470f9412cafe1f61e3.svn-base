<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

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
 		url:'${path}/newPartyOrgManage/statisticNewPartyOrgBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#statisticPartyList").setPostData(initParam);
	$("#statisticPartyList").trigger("reloadGrid");
}

$(function(){
	$("#statisticPartyList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:'organization.orgName', index:'organization.orgName',label:'单位', width:150, sortable:false, align:'center', hidden:false}, 	
	    	{name:'villageOrgNum', index:'villageOrgNum',label:'两新组织党组织数', width:300, sortable:false, align:'center', hidden:false}, 	 	
	    	{name:'villageMemberNum', index:'villageMemberNum',label:'两新组织党员数', width:300, sortable:false, align:'center', hidden:false}, 	
	    	{name:'villageRecordNum', index:'villageRecordNum',label:'两新组织党组织活动记录数', width:300, sortable:false, align:'center', hidden:false} 	
	   	],
	  	multiselect:false,
	  	rowNum:-1,
	  	height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-65
	});
	if(null!=getCurrentOrgId()){
		onOrgChanged(getCurrentOrgId());
	}
	
	
});

</script>
