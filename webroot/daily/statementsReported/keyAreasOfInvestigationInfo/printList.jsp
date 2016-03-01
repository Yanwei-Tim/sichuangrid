<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="printKeyAreasOfInvestigationInfoList">
<style type="text/css">
	.mod_print{
			margin:0 auto;
		}
		.mod_print h1{
			font-size:30px;
			text-align:center;
			line-height:2em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.2em 0.3em;
			border:1px solid #333;
		}
		.mod_print .b table th{
			text-align:right;
		}
		.mod_print .b table .th{
			text-align:center;
			font-weight:bold;
		}
		.autoNewline{
		  	word-break: break-all;
		}
	</style>
<div class="mod_print">
<div class="b">
<h1 align="center"><font size="6" style="font-weight:700;">${organization.orgName}治安重点排查情况</font></h1>
<table >
 <tr>
     <th class="th" width="5%">序号</th>
     <th class="th" width="16%">排查部门</th>
     <th class="th" width="16%">警示或挂牌</th>
     <th class="th" width="20%">主要问题</th>
     <th class="th" width="20%">对策与措施</th>
     <th class="th" width="16%">地区名称</th>
     <th class="th" width="10%">排查日期</th>
     <th class="th" width="23%">备注</th>
 </tr>
 <s:iterator value="result" var="obj">
	 <tr>
	  <td class="autoNewline" width="5%">${obj.id}</td>
	  <td class="autoNewline" width="16%">${obj.investigationOrg.orgName}</td>
	  <td class="autoNewline" width="16%">${obj.warningOrListing}</td>
	  <td class="autoNewline" width="20%">${obj.mainProblem}</td>
	  <td class="autoNewline" width="20%">${obj.policiesAndMeasures}</td>
	  <td class="autoNewline" width="16%">${obj.areaName}</td>
	  <td width="10%">${obj.investigatOrgDate}
	    <label><s:date name="obj.investigationDate" format="yyyy-MM-dd"/></label>
	  </td>
	  <td class="autoNewline" width="23%">${obj.remark}</td>
	 </tr>
 </s:iterator>
</table>
</div>
</div>
</div>
<script>

</script>
