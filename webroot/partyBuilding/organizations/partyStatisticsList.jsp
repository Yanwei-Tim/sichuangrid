<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class='clearLine'>&nbsp;</div>
	<div style="width: 100%;">
		<table id="partyStatisticsList">
		</table>
		<div id="partyStatisticsListPager"></div>
	</div>
</div>
<script type="text/javascript">
function onOrgChanged(orgId){
	var initParam = {
		"organizationId": orgId
	}
	$("#partyStatisticsList").setGridParam({
 		url:'${path}/districtPartyManage/findDistrictPartyStatistics.action',
		datatype: "json",
		page:1
	});
	$("#partyStatisticsList").setPostData(initParam);
	$("#partyStatisticsList").trigger("reloadGrid");
}

$(function(){
	
	// 生成列表
	$("#partyStatisticsList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:'organization.orgName', index:'organization.orgName',label:'单位', width:150, sortable:false, align:'center', hidden:false}, 	
	    	{name:'townMemberNum', index:'townMemberNum',label:'乡镇街道成员数', width:150, sortable:false, align:'center', hidden:false}, 	 	
	    	{name:'townActivityRecordNum', index:'townActivityRecordNum',label:'乡镇街道工作动态', width:150, sortable:false, align:'center', hidden:false}, 	
	    	{name:'townPartyResourceNum', index:'townPartyResourceNum',label:'乡镇街道区域内其他党组织资源', width:170, sortable:false, align:'center', hidden:false}, 
	    	{name:'townRegionalBuildInfoNum', index:'townRegionalBuildInfoNum',label:'乡镇街道区域联建情况', width:170, sortable:false, align:'center', hidden:false}, 
	    	{name:'villageMemberNum',index:'villageMemberNum',label:'村/社区成员数',align:'center',width:150,sortable:false},
	    	{name:'villageActivityRecordNum', index:'villageActivityRecordNum',label:'村/社区工作动态', width:150, sortable:false, align:'center', hidden:false},
	    	{name:'villagePartyResourceNum', index:'villagePartyResourceNum',label:'村/社区区域内其他党组织资源', width:170, sortable:false, align:'center', hidden:false},
	    	{name:'villageRegionalBuildInfoNum', index:'villageRegionalBuildInfoNum',label:'村/社区区域联建情况', width:170, sortable:false, align:'center', hidden:false}	
	   	],
	  	multiselect:false,
	  	height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-65
	  	//rowNum:"all"
	});
	if(null!=getCurrentOrgId()){
		onOrgChanged(getCurrentOrgId());
	}
	
	
});

</script>