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
	    <th width="76"  rowspan="2" scope="col" class="delleft">合计</th>
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
	    <th width="21" rowspan="14" scope="row" class="deltop">市直机关、企事业单位累计</th>
	    <td width="20" rowspan="7" class="deltop" scope="row">累计收集</td>
	    <td colspan="2">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">市台账办转办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本单位建账数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="49" rowspan="4">其中</td>
	    <td width="254">本单位直接收集数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>上级主管部门和市领导班子有关领导交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市人大议案建议意见交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市政协提案建议意见交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="20" rowspan="7" class="deltop" scope="row">累计办结</td>
	    <td colspan="2">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本单位建账办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">市台账办转办件办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td width="49" rowspan="4">其中</td>
	    <td>实质性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>程序性办结数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中申报&quot;三本台账&quot;协调领导小组研究数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>其中申报“<span class="accountCType"></span>”台帐办研究数</td>
	  </tr>
	  <tr class="rowCount">
	    <th rowspan="16" class="deltop" scope="row">市直机关、企事业单位本月</th>
	    <td colspan="3" class="deltop" scope="row">上月办理中</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="7" class="deltop" scope="row">本月收集</td>
	    <td colspan="2">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">市台账办转办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本单位建账数</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="4">其中</td>
	    <td>本单位直接收集数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>上级主管部门和市级领导班子有关领导交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市人大议案建议意见交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>市政协提案建议意见交办数</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="7" class="deltop" scope="row">本月办结</td>
	    <td colspan="2">合计</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">本单位建账办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="2">市台账办转办件办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td rowspan="4">其中</td>
	    <td>实质性办结数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>程序性办结数</td>
	  </tr>
	  <tr class="rowCount editEnable">
	    <td>其中申报&quot;三本台账&quot;协调领导小组研究数</td>
	  </tr>
	  <tr class="rowCount">
	    <td>其中申报“<span class="accountCType"></span>”台帐办研究数</td>
	  </tr>
	  <tr class="rowCount">
	    <td colspan="3" class="deltop" scope="row">本月办理中</td>
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
	if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION'/>"){
		$(".accountCType").text("<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION_STR' escape='false'/>");
	}else if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE'/>"){
		$(".accountCType").text("<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE_STR' escape='false'/>");
	}else if($("#accountType").val()=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK'/>"){
		$(".accountCType").text("<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK_STR' escape='false'/>");
	}
})

function getReportType(){
	return "<s:property value='@com.tianque.xichang.working.report.constant.ReportType@DISTRICTMOULD'/>"
}
</script>