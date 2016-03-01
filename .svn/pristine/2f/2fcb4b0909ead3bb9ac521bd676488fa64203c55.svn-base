<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationTypeProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE">
	</s:param>
</s:action>
<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationLevelProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL">
	</s:param>
</s:action>

<div style="width: 100%;">
	<table id="targeOrgSelectList"></table>
</div>

<script type="text/javascript">	
var orgTypeData = new Array();
var orgTypeInternalIdArray = new Array();
<s:iterator value="#getOrganizationTypeProperties.propertyDicts" var="propertyDict">
orgTypeData[<s:property value="#propertyDict.id"/>]='<s:property value="#propertyDict.displayName"/>';
</s:iterator>

var orgType = function(el,options,rowData){
	if(rowData["orgType.id"]){
		return orgTypeData[rowData["orgType.id"]];
	}
	if(!rowData.orgType){
		return "";
	}
	return orgTypeData[rowData.orgType.id];
}

var orgLevelData = new Array();
<s:iterator value="#getOrganizationLevelProperties.propertyDicts" var="propertyDict">
orgLevelData[<s:property value="#propertyDict.id"/>]='<s:property value="#propertyDict.displayName"/>';
</s:iterator>

var orgLevel = function(el,options,rowData){
	if(rowData["orgLevel.id"]){
		return orgLevelData[rowData["orgLevel.id"]];
	}
	if(!rowData.orgLevel){
		return "";
	}
	return orgLevelData[rowData.orgLevel.id];
}

$(document).ready(function(){
	$("#targeOrgSelectList").jqGridFunction({
		url:'${path}/issue/issueManage/searchTargeOrg/searchTargeOrgsForSelector.action',
		datatype: "json",
		postData: {"issueDealType":"<s:property value='#parameters.issueDealType'/>","orgIds":"<s:property value='#parameters.orgIds'/>"},
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'orgName',index:'orgName',sortable:false, label:'部门名称',width:90},
	   		{name:'orgType.id',index:'orgType.id',sortable:false,formatter:orgType, label:'部门类型',width:90},
	   		{name:'orgLevel.id',index:'orgLevel.id', sortable:false,formatter:orgLevel, label:'部门级别',width:90},
	   		{name:'contactWay',index:'contactWay', sortable:false, label:'部门联系电话',width:90},		
	   		{name:'remark',index:'remark', sortable:false, label:'备注',width:90}
	   	],
		multiselect: <s:property value='#parameters.isMultiple'/>,
		loadComplete:selectTargeOrgs,
	   	width: 480,
		height: 300,
	    rowNum:-1,
	    pager:false
	})
});


function selectTargeOrgs(){
	$("#cb_targeOrgSelectList").attr("disabled",true);
	var idval = "#<s:property value='#parameters.inputCompleteId'/>";
	var selectOrgIds = $(idval).val().split(",");
	if(selectOrgIds.length >= 1 && selectOrgIds[0] != ""){
		for(var i = 0; i < selectOrgIds.length; i++){
			$("#targeOrgSelectList").setSelection(selectOrgIds[i],true);
		}
	}
}

</script>
