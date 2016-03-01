<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-layout-caseLibrary-west">
</div>
<script>
function caseLibraryTreeShow(isBasicInformation,afterLoad){
	$(".ui-layout-caseLibrary-west").load("${path}/incident/extTreeForIncident/caseLibraryTree.jsp?selectedIncidentId=<s:property value='#parameters.selectedIncidentId'/>&isBasicInformation="+isBasicInformation,function(){
		function afterChangNode(node){
			afterLoad();
		}
		$.addClick(tree,afterChangNode);
	});
}
function afterLoad(){
	$.ajax({
		url : PATH+'/incident/emergencyDisposal/findCaseLibraryList.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/>',
		cache: false,
		success : function(result) {
			$(".center-right").html(result);
		}
	});
}
$(function(){
	caseLibraryTreeShow(true,afterLoad);
});
</script>