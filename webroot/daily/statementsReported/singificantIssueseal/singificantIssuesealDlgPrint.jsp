<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" id="singificantIssuesealDlgPrint">
<style type="text/css">
		*{
			padding:0;
			margin:0;
			font-size:12px;
			font-family: Arial;
		}
		.mod_print{
			margin:0 auto;
			width:200mm;
			height:70mm;
		}
		.mod_print h1{
			font-size:25px;
			text-align:center;
			line-height:3em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.3em 0.5em;
			border:1px solid #333;
		}
		.mod_print .b table th{
			text-align:right;
		}
		.mod_print .b table .th{
			text-align:center;
			font-weight:bold;
		}

	</style>
	<div class="mod_print">
	  <h1>重大矛盾纠纷排查调处信息</h1>
    	<div class="b">
    	<table>
    	<tr>
    	    <th width="12%" class="th">时间</th>
	    	<td width="14%" class="heightAuto"><label><s:date name="significantIssuedealWorkingRecord.investigationDate" format="yyyy-MM-dd"/></label></td>
	    	<td width="10%" class="th">地点（单位）</td>
	    	<td width="15%" class="heightAuto">${significantIssuedealWorkingRecord.address}</td>
    	</tr>
    	<tr>
    	    <th width="12%" class="th">责任领导</th>
	    	<td width="14%" class="heightAuto">${significantIssuedealWorkingRecord.accountabilityLeading}</td>
	    	<td  width="10%" class="th">责任单位</td>
	    	<td width="15%" class="heightAuto" align="left">${significantIssuedealWorkingRecord.accountabilityUnit}</td>
    	</tr>
   		<tr>
            <th width="12%" class="th">矛盾纠纷简况</th>
            <td colspan="3" class="heightAuto" >${significantIssuedealWorkingRecord.significantIssuedealReason}</td>
        </tr>
        <tr>
            <th width="12%" class="th">调处情况</th>
            <td colspan="3" class="heightAuto" >${significantIssuedealWorkingRecord.remarks}</td>
        </tr>
		</table>
	  </div>
	</div>
</div>
