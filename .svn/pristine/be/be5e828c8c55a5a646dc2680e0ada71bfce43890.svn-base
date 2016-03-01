<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="NewSocietyOrganizations" name="moduleName"/>
</jsp:include>


<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" />
<pop:formatterProperty name="subType" domainName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" />
var dialogWidth=810;
var dialogHeight=640;
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateNewSocietyOrganizations'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteNewSocietyOrganizations'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewSocietyOrganizations'> onclick='viewNewSocietyOrganizations("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewNewSocietyOrganizations'> onclick='viewNewSocietyOrganizations("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
}
function getLocationName(rowData){
	return $(rowData.name).text();
}

</script>
<script  src="/baseinfo/twoNewOrganization/newSocietyOrganizations/tableGridModel.js"></script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="NewSocietyOrganizations" name="moduleName"/>
	<jsp:param value="社会组织" name="moduleCName"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_KEY"/>'/>
	<input type="hidden" id="_supervisorName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
</div>