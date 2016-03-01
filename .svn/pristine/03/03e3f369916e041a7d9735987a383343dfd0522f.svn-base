<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization,com.tianque.domain.property.OrganizationLevel,
         com.tianque.core.util.ThreadVariable,com.tianque.domain.User" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/includes/baseInclude.jsp" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<%
    Organization userOrg = ThreadVariable.getUser().getOrganization();
	boolean isTown = false;
    if (userOrg.getOrgType().getDisplayName().equals(
            OrganizationType.getInternalProperties().get(
                    OrganizationType.FUNCTIONAL_ORG).getDisplayName())) {
        request.setAttribute("isFun", true);
    } else {
        request.setAttribute("isFun", false);
    }
    if (userOrg.getOrgLevel().getInternalId()==OrganizationLevel.TOWN) {
    	isTown = true;
	}
%>
<input type="hidden" id="reportType" value="2"/>
<input type="hidden" id="orgLevel" value="${getFullOrgById.organization.orgLevel.internalId }"/>
<input type="hidden" id="keyId" value="${getFullOrgById.organization.id}"/>
<input type="hidden" id="functionalOrgType" value="${getFullOrgById.organization.functionalOrgType.id}"/>
<input type="hidden" id="isHideOrgSearchOrg" value="${isFun}"/>

 <div>
	<div class="ui-corner-all nav-buttons" id="nav" style="float: left" >
	   <div>
		    <c:if test="${isFun!='true' }">
		     	<jsp:include page="/common/orgSelectedComponent.jsp"/>
		     </c:if>
	    	 <c:if test="${(fn:endsWith(loginAction.user.organization.departmentNo, '1jg')||fn:endsWith(loginAction.user.organization.departmentNo, 'lxw')||fn:endsWith(loginAction.user.organization.departmentNo, '1lx'))}">
	    		<jsp:include page="/common/orgSelectedComponent.jsp">
		     		<jsp:param  name="selectType" value="account"/>  
		     	</jsp:include>
	    	 </c:if>
	    	<div style="float: right;padding-bottom: 10px;margin-left: 5px;">
       			<select name="year" id="year" class="form-txt" style="width: 80px;"></select>
       			<span id="monthSpan"></span>&emsp;	
			</div>	 
	   </div>
	</div>
   </div>
   <br/><br/><br/><br/>
   <jsp:include page="/account/informationAnalysis/mould/currentYearCollectDoneRateSumMould.jsp"  flush="true"/>

<script type="text/javascript">
$(function(){
	$.ajax({
		async : false,
		url : "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success : function(responseData) {
			var dataLength = responseData.length;
			for (var i = 0; i < dataLength; i++) {
				if (i == 0)
					$("#year").append("<option selected='selected' value='"+responseData[i]+"'>" + responseData[i] + "年</option>");
				else
					if (responseData[i] > 2012)
						$("#year").append("<option value='"+responseData[i]+"'>" + responseData[i] + "年</option>");
			}
		}
	});
	$("#monthSpan").append('<select name="month" id="month" onchange="initYearCollect()" class="form-txt" style="width: 80px;"></select>');
	getMonth();
	$("#year").change(function() {
		$("#month").empty();
		getMonth();
		initYearCollect();
	});
	
	function getMonth() {
		$.ajax({
			async : false,
			url : "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear=" + $("#year").val(),
			success : function(responseData) {
				var dataLength = responseData.length;
				for (var i = 0; i < dataLength; i++) {
					$("#month").append("<option  value='"+responseData[i]+"'>" + responseData[i] + "月</option>");
				}
			}
		});
	}
	initYearCollect();
})

function initYearCollect(){
	var statGridCollect = initCurrentYearCollectGridCollect("当年建账办结情况汇总",false);
	getPrimaryOrgStat(statGridCollect);
}

function getPrimaryOrgStat(statGridCollect){
	$.ajax({
		url:'${path}/threeRecordsIssue/ledgerAccountReportManage/findYearCollectDoneRateReportVo.action',
		data:{
			"accountReport.organization.id":getCurrentOrgId(),
			"accountReport.accountType":0,
			"accountReport.reportType":<s:property value="@com.tianque.plugin.account.constants.LedgerReportType@YEAR_COLLECT_DONE_SUM"/>,
			"accountReport.orgLevelInternalId":$("#currentOrgId").attr("orgLevelInternalId"),
			"accountReport.reportYear":$("#year").val(),
			"accountReport.reportMonth":$("#month").val()
		},
		success:function(data){
			var dataObj = $.parseJSON(data);
			if(data == null || dataObj != null){
				var message = (data == null) ? '获取报表失败' : dataObj.message;
				$.messageBox({
					message: message,
					level: "error"
				});
			}
			
			statGridCollect.bindData(data);
		}
	})
}
</script>