<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="PositiveInfo" name="moduleName"/>
</jsp:include>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<%@ include file="/baseinfo/positiveInfo/colModel.jsp"%>

<div class="content">
<!-- 
<div class="ui-corner-all" id="nav">
<pop:JugePermissionTag ename="addPositiveInfo">
	<a id="add" href="javascript:;"><span>新增</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="updatePositiveInfo">
	<a id="update" href="javascript:void(0)"><span>修改</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="viewPositiveInfo">
	<a id="view" href="javascript:void(0)"><span>查看</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="deletePositiveInfo">
	<a id="delete" href="javascript:void(0)"><span>删除</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="searchPositiveInfo">
	<a id="search" href="javascript:void(0)"><span>查询</span></a>
</pop:JugePermissionTag>
<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
<pop:JugePermissionTag ename="importPositiveInfo">
	<a id="import" href="javascript:void(0)"><span>导入</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="downPositiveInfo">
	<a id="export" href="javascript:void(0)"><span>导出</span></a>
</pop:JugePermissionTag>
 <a id="isEmphasis" href="javascript:void(0)"><span>取消关注</span></a> 
</div>
-->
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="PositiveInfo" name="moduleName"/>
	<jsp:param value="刑释人员" name="moduleCName"/>
	<jsp:param value="服务人员" name="supervisorPerson"/>
	<jsp:param value="${searchType}" name="searchType"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_KEY)" escape="false"/>'/>
</div>