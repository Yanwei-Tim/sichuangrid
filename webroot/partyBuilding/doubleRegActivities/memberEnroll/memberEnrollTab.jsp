<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<jsp:include page="${path}/common/orgSelectedComponent.jsp?plateName=party"/>
<div id="leaderLevel">
	<div id="townTabList" style="padding: 5px 0;">
		<ul>
			<li><a id="" href="${path}/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.jsp?regActivitiesType=<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@AUTHORITY_CLASS'/>">机关类</a> </li>
			<li><a id="" href="${path}/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.jsp?regActivitiesType=<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@CAUSE_CLASS'/>">事业类</a></li>
			<li><a id="" href="${path}/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.jsp?regActivitiesType=<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@ENTERPRISE_CLASS'/>">企业类</a></li>
			<li><a id="" href="${path}/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.jsp?regActivitiesType=<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@OTHER_CLASS'/>">其他</a></li>
		</ul>
	</div>
</div>
<script>
$(function() {
	if(isUserVillageUp() && (isTownUp() || isVillageUp() )){
		$("#leaderLevel").empty().load("${path}/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.jsp?regActivitiesType=<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@AUTHORITY_CLASS'/>");
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

function isUserVillageUp(){
	return USER_ORG_LEVEL > '<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>';
}
</script>