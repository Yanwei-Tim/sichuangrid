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
	    <th width="170" rowspan="2" scope="col" class="delleft">合计</th>
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
	    <th width="85" rowspan="9" scope="row" class="deltop">台账办累计</th>
	    <td width="64" rowspan="5" class="deltop" scope="row">累计收集</td>
	    <td width="370">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td>乡镇、街道呈报数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市级机关、企事业单位呈报数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市级领导交办数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中申报市委&quot;三本台账&quot;协调领导小组研究处理数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="64" rowspan="4" class="deltop" scope="row">累计办结</td>
	    <td>合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td>转办乡镇、街道数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>转办市级机关、企事业单位数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中经市委&quot;三本台账&quot;协调领导小组研究后交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <th rowspan="11" class="deltop" scope="row">台账办本月</th>
	    <td class="deltop" scope="row">
<!-- 	    	<input type="text" /> -->
	    </td>
	    <td class="deltop" scope="row">上月办理中</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="5" class="deltop" scope="row">本月收集</td>
	    <td>合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td>乡镇、街道呈报数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市级机关、企事业单位呈报数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市级领导交办数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中申报市委&quot;三本台账&quot;协调领导小组研究处理数</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="4" class="deltop" scope="row">本月办结</td>
	    <td>合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td>转办乡镇、街道数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>转办市级机关、企事业单位数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中经市委&quot;三本台账&quot;协调领导小组研究后交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td class="deltop" scope="row">
<!-- 	    	<input type="text" /> -->
	    </td>
	    <td class="deltop" scope="row">本月办理中</td>
	  </tr>
	</table>
</div>
<script>
$(function(){
	$(".accountType").height($(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-45);
	$(".rowCount").each(function(i){
		$(this).append($("#rowDataMould").html())
		$(this).attr("id","row"+i);
	});
})
function getReportType(){
	return "<s:property value='@com.tianque.xichang.working.report.constant.ReportType@XICHANG_ONE_MOULD'/>";
}
</script>