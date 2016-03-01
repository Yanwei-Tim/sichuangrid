<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%
	request.setAttribute("partyModule",com.tianque.partyBuilding.members.constant.MemberType.STREETPARTY);
	request.setAttribute("partyOrgType",com.tianque.partyBuilding.members.constant.MemberType.TOWN_PARTY_ORG);
%>
<jsp:include page="${path}/common/orgSelectedComponent.jsp?plateName=party"/>
<div id="leaderLevel">
	<div id="townTabList">
		<ul>
			<li><a id="" href="${path}/partyBuilding/organizations/townPartyOrgManage/townPartyOrgList.jsp">党组织信息</a> </li>
			<li><a id="" href="${path}/partyBuilding/members/memberList.jsp?partyModule=${partyModule }&partyOrgType=${partyOrgType}">党员信息</a></li>
			<li><a id="" href="${path}/partyBuilding/activityRecordManage/dispatchOperate.action?mode=list&partyOrganizationType=<s:property value='@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@TOWN_PARTY_ORGANIZATION'/>&partyModule=${partyModule }">活动记录</a></li>
		</ul>
	</div>
</div>
<script>
$(function() {
	if(isUserTownUp() && (isTownUp() || isVillageUp() )){
		$("#leaderLevel").empty().load("${path}/partyBuilding/organizations/townPartyOrgManage/townPartyOrgStatisticList.jsp");
	}else{
		$( "#townTabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#townTabList" ).find(".ui-tabs-panel").empty();
		}});
	}

});
//社区级别以上
function isVillageUp(){
	return $("#currentOrgId").attr("orgLevelInternalId") > '<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>'
}

//乡镇街道级别以上
function isTownUp(){
	return $("#currentOrgId").attr("orgLevelInternalId") > '<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>';
}

function isUserTownUp(){
	return USER_ORG_LEVEL > '<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>';
}
</script>