<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
function teamClazzFormatter(el, options, rowData){
		//alert('<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@massForce"/>');
		if (rowData["teamClazz"]=='<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@autonomousOrganization"/>')
		return "自治组织";
		if (rowData["teamClazz"]=='<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@massForce"/>')
		return "群防群治队伍";
		if (rowData["teamClazz"]=='<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@socialVolunteerTeam"/>')
		return "社会志愿者队伍";
		if (rowData["teamClazz"]=='<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@specialWorkLeadingGroup"/>')
		return "专项工作领导小组";
		return "";
	}
</script>