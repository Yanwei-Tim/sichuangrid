<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
request.setAttribute("dictType",request.getParameter("dictType"));
request.setAttribute("dictName",request.getParameter("dictName"));
%>
<s:action namespace="/sysadmin/propertyManage" name="findPropertyDictByDomainName" var="dictTypes">
	<s:param name="propertyDomain.domainName">${dictType}</s:param>
</s:action>
<div class="accountType">
	<table width="100%" cellspacing="0" class="countlist">
	  <tr class="countlistHead">
	    <th colspan="3" rowspan="2" scope="col">内容</th>
	    <th width="102" rowspan="2" scope="col" class="delleft">合计</th>
	    <th height="24" colspan="<s:property value="#dictTypes.propertyDicts.size" />" scope="col" class="delleft" id="dictType">${dictName}</th>
	  </tr>
	  <tr class="countlistHead">
	    <c:forEach items="${dictTypes.propertyDicts}" var="dict">
	  		<td width="67" height="25">${dict.displayName }</td>
	  	</c:forEach>
	  </tr>
	  <tr class="hidden" id="rowDataMould" title="统计数据展示的模板行">
		<td id="colAll">0</td>
		<c:forEach items="${dictTypes.propertyDicts}" var="dict" varStatus="status">
			<td id="col${dict.id}">0</td>
		</c:forEach>
	  </tr>
	  <tr class="rowCount">
	    <th width="22" rowspan="5" scope="row" class="deltop">乡镇、街道累计</th>
	    <td colspan="2">累计建账数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">累计办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="42" rowspan="3">其中</td>
	    <td width="81">实质性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>程序性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>其中呈报乡镇、街道数</td>
	  </tr>
	  <tr class="rowCount">
	    <th rowspan="7" scope="row" class="deltop">乡镇、街道本月</th>
	    <td colspan="2">上月办理中</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本月建账数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本月办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="3">其中</td>
	    <td>实质性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>程序性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>其中呈报乡镇、街道数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本月办理中</td>
	  </tr>
	</table>
</div>

<script>
$(function(){
	$(".accountType").height($(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-15);
	$(".rowCount").each(function(i){
		$(this).append($("#rowDataMould").html())
		$(this).attr("id","row"+i);
	});
})
function getReportType(){
	return "<s:property value='@com.tianque.xichang.working.report.constant.ReportType@TOWN_ONE_MOULD'/>"
}
</script>