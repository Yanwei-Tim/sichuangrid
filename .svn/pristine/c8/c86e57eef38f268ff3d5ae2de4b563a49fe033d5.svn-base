<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization,com.tianque.domain.property.OrganizationLevel,
         com.tianque.core.util.ThreadVariable,com.tianque.domain.User" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="tabList">
	<ul>
		<li><a href="${path}/account/informationAnalysis/ledgerPoepleMonthAnalysis.jsp">民生台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/informationAnalysis/ledgerPoorPoepleMonthAnalysis.jsp">困难台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/informationAnalysis/ledgerSteadyWorkMonthAnalysis.jsp">稳定台账&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</ul>
 </div>
 
 <script type="text/javascript">

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