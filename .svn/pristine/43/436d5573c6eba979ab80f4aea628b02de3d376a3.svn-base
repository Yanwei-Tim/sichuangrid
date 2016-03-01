<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
//$(document).ready(function(){
//	var type = $("#_formType").find("option:selected").text();
//	$("#formTypeLabel").text(type);
//})
</script>

<!-- <div style="visibility: hidden;">
	<select name="acceptForm.formType.id" id="_formType" class="form-select {required:true}" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FORM_TYPE" defaultValue="${acceptForm.formType.id}"  showInternalId="true"/>
	</select>
</div> -->

<div>
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>
	
	<table width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${acceptForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
	          			${acceptForm.acceptOrg.orgName}
	          		</u>三本台账工作处理单
	          	    <br><br>
	          	</td>
        	</tr>
        	<tr>
        		<td width="24%" align="center" class="tdhr"><b>（各级台账办内部使用）</b></td>
        	</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
       	<tr class="tr1" >
       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		来件时间
       		</td>
            <td width="48%"  style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
          	 <!-- ${acceptForm.formComeDate} -->
          	 <s:date name="acceptForm.formComeDate" format="yyyy年MM月dd日" />
            </td>
            <td width="4%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >台账类型</td>
            <td width="36%" style="text-align:left;font-Size:10px; height:20px;width:36%;white-space: nowrap;" >
             	<s:if test="acceptForm.ledgerType==2">困难群众工作台账</s:if>
	          	<s:elseif test="acceptForm.ledgerType==3">稳定工作台账</s:elseif>
	          	<s:else>民生工作台账</s:else>
            </td>
        </tr>
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		来件类型
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
				<!-- <label id="formTypeLabel"></label> 
				<select name="acceptForm.formType.id" id="_formType" class="form-select {required:true}" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FORM_TYPE" defaultValue="${acceptForm.formType.id}"  showInternalId="true"/>
				</select>-->
				${acceptForm.formType.displayName}
            </td>
       	</tr>
       	
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		本级台账办拟办意见
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${acceptForm.currentOrgOpinion}
            </td>
       	</tr>
       		<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		分管领导意见
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       		${acceptForm.inChargeOfLeaderOpinion}
            </td>
       	</tr>
       	
       	</tr>
       		<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		主要领导意见
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       		${acceptForm.majorLeaderOpinion}
            </td>
       	</tr>
       	
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		办理结果
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">&nbsp;&nbsp;&nbsp;
       		${acceptForm.handleResult}
            </td>
       	</tr>
       	
       	<tr class="tr1" >
       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		接件时间
       		</td>
            <td width="48%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
          	<!--  ${acceptForm.formAcceptDate} -->
          	 <s:date name="acceptForm.formAcceptDate" format="yyyy年MM月dd日" />
            </td>
            <td width="4%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >承办人签字</td>
            <td width="36%" style="text-align:left;font-Size:10px; height:20px;width:36%;white-space: nowrap;" >
            <!--   ${acceptForm.dealUserName} -->
            </td>
        </tr>
       	
    </table>
</div>
