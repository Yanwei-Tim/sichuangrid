<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<div class="incdList_caseLibrary" style="position:relative;">
	<div class="ui-layout-caseLibrary-west">
	</div>
</div>
<script>
function caseLibraryTreeShow(isBasicInformation,afterLoad){
	$(".ui-layout-caseLibrary-west").load("${path}/incident/extTreeForIncident/caseLibraryTree.jsp?selectedIncidentId=<s:property value='#parameters.selectedIncidentId'/>&isBasicInformation="+isBasicInformation,function(){
		function afterChangNode(node){
			afterLoad(node.attributes.propertyDictId);
		}
		$.addClick(tree,afterChangNode);
	});
}
function afterLoad(propertyDictId){
	var url = '${path}/incident/emergencyDisposal/findCaseLibraryList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/>';
	if(getcurrentLevel()==3){
		var category = getParentNodePropertyDictId();
		var subdivision = getParentNodeIncidentTypeId();
		url+="&incidents.degree.id="+propertyDictId+"&incidents.category.id="+category+"&incidents.subdivision="+subdivision;
	}else if(getcurrentLevel()==2){
		url+="&incidents.subdivision="+currentSelectNodeId()+"&incidents.category.id="+propertyDictId;;
	}else if(getcurrentLevel()==1){
		url+="&incidents.category.id="+propertyDictId;;
	}
	else{
	}
	//var url = '/incident/emergencyDisposal/findCaseLibraryList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/>';
//	if(propertyDictId){
//		url+="&incidents.category.id="+propertyDictId;
	//}
	$.ajax({
		url : '${path}'+url,
		cache: false,
		success : function(result) {
			$("#incidentRightBox").html(result);
		}
	});
 }
$(function(){
	caseLibraryTreeShow(true,afterLoad);
});
</script>