<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
	<pop:formatterProperty name="position" domainName="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY" />
	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
	var colHead = ['id','organization.id','组织名称','组织类别','所属区域(网格)','成员数','成立时间','服务对象','服务记录'];
</script>

<jsp:include page="${path }/baseinfo/serviceTeamManage/commonServiceTeam/commonServiceTeamList.jsp">
	<jsp:param value="AutonomousOrganization" name="moduleName" />
	<jsp:param value="自治组织" name="moduleCName" />
</jsp:include>