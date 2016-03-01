<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="keyAreasOfInvestigationInfoDlgPrint">
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
	  <h1>治安重点排查情况</h1>
    	<div class="b">
    	<table>
    	<tr>
    	    <td width="12%" class="th">地区名称</td>
	    	<td width="18%" class="heightAuto">${keyAreasOfInvestigationInfo.areaName}</td>
    	    <th width="10%" class="th">排查日期</th>
	    	<td width="12%"><label><s:date name="keyAreasOfInvestigationInfo.investigationDate" format="yyyy-MM-dd"/></label></td>
    	</tr>
    	<tr>
            <th width="12%" class="th">警示或挂牌</th>
            <td colspan="3" class="heightAuto"><p>${keyAreasOfInvestigationInfo.warningOrListing} </p></td>
        </tr>
    	<tr>
            <th width="12%" class="th">主要问题</th>
            <td colspan="3" class="heightAuto"><p>${keyAreasOfInvestigationInfo.mainProblem} </p></td>
        </tr>
   		<tr>
            <th width="12%" class="th">对策与措施</th>
            <td colspan="3" class="heightAuto"><p>${keyAreasOfInvestigationInfo.policiesAndMeasures} </p></td>
        </tr>
        <tr>
            <th width="12%" class="th">备注</th>
            <td colspan="3" class="heightAuto"><p>${keyAreasOfInvestigationInfo.remark} </p></td>
        </tr>
		</table>
	  </div>
	</div>
</div>
