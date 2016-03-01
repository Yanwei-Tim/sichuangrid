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
	    <th colspan="4" rowspan="2" scope="col">内容</th>
	    <th width="226" rowspan="2" scope="col" class="delleft">合计</th>
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
	    <th width="82" rowspan="4" scope="row" class="deltop">全市累计建账</th>
	    <td colspan="3" class="deltop" scope="row">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">村、社区建账</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">乡镇、街道建账</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">市级机关、企事业单位建账</td>
	  </tr>
	  <tr class="rowCount">
	    <th rowspan="8" class="deltop" scope="row">全市累计办结</th>
	    <td colspan="3" class="deltop" scope="row">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">村、社区办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">乡镇、街道办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">市级机关、企事业单位办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="50" rowspan="4" class="deltop" scope="row">其中</td>
	    <td colspan="2" class="colsTit">实质性办结</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2" class="colsTit">程序性办结</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2" class="colsTit">申报市民生诉求台账办</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td colspan="2" class="colsTit">提交&quot;三本台账&quot;协调领导小组研究</td>
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
});
function getReportType(){
	return "<s:property value='@com.tianque.xichang.working.report.constant.ReportType@XICHANG_TWO_MOULD'/>";
}
</script>