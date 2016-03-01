<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
request.setAttribute("ename",request.getParameter("ename"));
%>
<div>
	<jsp:include page="${path}/common/orgSelectedComponent.jsp" />
	<div id="tabByParentEname">
	</div>
</div>

<script type="text/javascript">
$(function(){
	afterOrgChanged();
})

function afterOrgChanged(){
	var postData = {orgId:$("#currentOrgId").val()}
	switch("${ename}"){
		case "twoNewPartyOrganizationManagement":
			if(isVillageUp()){
				$("#tabByParentEname").load("${path}/statisticPartyManage/statisticTwoNewPartyOrg.action",postData);
				break;
			}
		case "streetCommunityPartyOrganizationManagement":
			if(isUserTownUp() && isTownUp()){
				$("#tabByParentEname").load("${path}/statisticPartyManage/statisticStreetCommunityPartyOrgForTown.action",postData);
				break;
			}else if(isUserTownUp() && isVillageUp()){
				$("#tabByParentEname").load("${path}/statisticPartyManage/statisticStreetCommunityPartyOrgForVillage.action",postData);
				break;
			}
		default:
			$("#tabByParentEname").load("${path}/sysadmin/menuManage/getTabByParentEname.action?ename=${ename}");
	}

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
}
</script>