<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
request.setAttribute("dictType",com.tianque.domain.property.PropertyTypes.ASPIRATIONTYPE);
request.setAttribute("accountType",com.tianque.xichang.working.flow.constant.AccountType.STEADYWORK);
%>
<script>
var postData={
		"dictType":"${dictType}",
		"dictName":"诉求类别"
}
</script>
<div>
	<jsp:include page="${path}/xichang/working/report/commonReportInfo.jsp">
		<jsp:param  name="accountType" value="${accountType}"/>
	</jsp:include>
</div>