<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="printSignificantIssuedealList">
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
<h1 align="center"><font size="6" style="font-weight:700;">${organization.orgName}重大矛盾纠纷排查调处信息</font></h1>
<table >
 <tr>
     <th class="th" width="5%">序号</th>
     <th class="th" width="13%">排查部门</th>
     <th class="th" width="9%">排查日期</th>
     <th class="th" width="15%">单位地点</th>
     <th class="th" width="20%">矛盾纠纷简况及原因</th>
     <th class="th" width="15%">责任单位</th>
     <th class="th" width="8%">责任领导</th>
     <th class="th" width="20%">调处情况</th>
 </tr>
 <s:iterator value="result" var="obj">
	 <tr>
	  <td class="autoNewline" width="5%">${obj.id}</td>
	  <td class="autoNewline" width="13%">${obj.investigationOrg.orgName}</td>
	  <td width="9%">${obj.strInvestigationDate}
	  </td>
	  <td class="autoNewline" width="15%">${obj.address}</td>
	  <td class="autoNewline" width="20%">${obj.significantIssuedealReason}</td>
	  <td class="autoNewline" width="15%">${obj.accountabilityUnit}</td>
	  <td class="autoNewline" width="8%">${obj.accountabilityLeading}</td>
	  <td class="autoNewline" width="20%">${obj.remarks}</td>
	 </tr>
 </s:iterator>
</table>
</div>
</div>
</div>
<script>
</script>
