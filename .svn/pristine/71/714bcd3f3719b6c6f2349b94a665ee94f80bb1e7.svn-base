<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="OptimalObject" name="moduleName"/>
</jsp:include>
<%@ include file="/baseinfo/civilAdministration/optimalObject/colModel.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/commonPopulationList.jsp">
	<jsp:param value="OptimalObject" name="moduleName"/>
	<jsp:param value="优抚对象" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@OPTIMALOBJECT_KEY)" escape="false"/>'/>
</div>