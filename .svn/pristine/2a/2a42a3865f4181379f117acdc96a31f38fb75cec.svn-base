<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="tabList">
	<ul>
		<li><a href="${path}/account/report/month/threeRecordsJgReportMonth.jsp">台账办月报表&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/report/month/threeRecordsDistrictReportMonth.jsp">全县汇总表&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</ul>
</div>

<script>
$(function() {
	var isAuditDelay= '<s:property value="#parameters.isAuditDelay"/>';
	
	if(isAuditDelay!='' && isAuditDelay!=null){
		$( "#tabList" ).tabs({ selected: isAuditDelay ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}else{
		$( "#tabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}
});
</script>