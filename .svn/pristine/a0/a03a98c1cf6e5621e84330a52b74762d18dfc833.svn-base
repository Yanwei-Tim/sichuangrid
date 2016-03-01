<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
	<pop:formatterProperty name="position" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY" />
	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" />	
	var colHead = ['id','organization.id','小组名称','小组类别','所属区域(网格)','成员数','成立时间','服务对象','服务记录'];
</script>

<jsp:include page="${path }/baseinfo/serviceTeamManage/commonServiceTeam/commonServiceTeamList.jsp">
	<jsp:param value="SpecialWorkLeadingGroup" name="moduleName" />
	<jsp:param value="专项工作领导小组" name="moduleCName" />
</jsp:include>