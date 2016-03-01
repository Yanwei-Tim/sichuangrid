<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="leaderLevel">
	<div id="townTabList" style="padding: 5px 0;" >
		<ul>
			<li><a id="" href="${path}/partyBuilding/systems/systemPartyList.jsp?type=BusinessOrg&partyOrgType=<s:property value='@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@BUSINESS_ORG'/>">事业单位党组织</a> </li>
			<li><a id="" href="${path}/partyBuilding/systems/systemPartyList.jsp?type=CollectiveOrg&partyOrgType=<s:property value='@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@COLLECTIVE_ORG'/>">国有(集体党组织)</a> </li>
			<li><a id="" href="${path}/partyBuilding/systems/systemPartyList.jsp?type=TwoNewPartyForSys&partyOrgType=<s:property value='@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@TWO_NEW_PARTY'/>">两新组织党组织</a> </li>
		</ul>
	</div>
	
</div>
<script>
$(function() {

	$( "#townTabList" ).tabs({ selected: 0 ,beforeLoad:function(){
		$( "#townTabList" ).find(".ui-tabs-panel").empty();
	}});

});
//社区级别以上
function isVillageUp(){
	return $("#currentOrgId").attr("orgLevelInternalId") > '<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>'
}

//乡镇街道级别以上
function isTownUp(){
	return $("#currentOrgId").attr("orgLevelInternalId") > '<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>';
}

function isUserVillageUp(){
	return USER_ORG_LEVEL > '<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>';
}
</script>