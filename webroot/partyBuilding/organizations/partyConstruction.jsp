<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<jsp:include page="${path }/common/orgSelectedComponent.jsp?plateName=party"/>
<div id="townLevel">
	<div class="container container_24">
		<s:action name="findPartyOrgInfo" namespace="/baseinfo/partyOrgInfoManage" executeResult="true">
		</s:action>
	</div>
	<div id="regionalTabList">
		<ul>
			<!-- <li><a id="partyInfo" href="">党员信息</a> </li> -->
			<li><a id="partyOrganizationMember" href="">区域党委成员</a> </li>
			<li><a id="partyOrganizationActiveRecord" href="">工作动态</a></li>
			<li><a id="partyOrganizationSource" href="${path}/partyBuilding/organizations/partyresourceList.jsp">区域内主要党组织资源</a></li>
			<li><a id="partyRegionalBuildInfo" href="${path}/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoList.jsp">区域联建情况</a></li>
		</ul>
	</div>
</div>
<script>
$(function() {
	//$("#partyInfo").attr("href","${path}/partyBuilding/members/memberForTownList.jsp?partyOrgType=<s:property value='@com.tianque.partyBuilding.members.constant.MemberType@REGION_PARTY_ORG'/>"+"&partyOrg="+$("#partyBranchName").val()+"&partyModule=regionalParty");
	$("#partyOrganizationMember").attr("href","${path}/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberList.jsp?partyOrg="+$("#partyBranchName").val());
	
	$("#partyOrganizationActiveRecord").attr("href","${path}/partyBuilding/activityRecords/activityRecordsList.jsp?partyOrganizationType=<s:property value='@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@PARTY_ORGANIZATION'/>&orgId="+
			getCurrentOrgId()+"&partyOrganizationId="+$("#partyOrgInfoId").val()+"&partyOrganizationName="+$("#partyBranchName").val()+"&partyModule=regionalParty");
	if($("#currentOrgId").attr("orgLevelInternalId")>="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){//县区及以上的层级出统计数据
		$("#townLevel").empty();
		$("#townLevel").load("${path}/partyBuilding/organizations/partyStatisticsList.jsp");
	}else{
		$( "#regionalTabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#regionalTabList" ).find(".ui-tabs-panel").empty();
		}});
	}
});
</script>