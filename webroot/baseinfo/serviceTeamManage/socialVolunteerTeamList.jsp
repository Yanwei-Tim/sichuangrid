<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
	<pop:formatterProperty name="position" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY" />
	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE" />	
	var colHead = ['id','organization.id','队伍名称','队伍类别','所属区域(网格)','成员数','成立时间','服务对象','服务记录'];
</script>

<jsp:include page="${path }/baseinfo/serviceTeamManage/commonServiceTeam/commonServiceTeamList.jsp">
	<jsp:param value="SocialVolunteerTeam" name="moduleName" />
	<jsp:param value="社会志愿者队伍" name="moduleCName" />
</jsp:include>