<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	

<div>
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>


	<table width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${reportForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县三本台账工作<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
	          			<s:if test="reportForm.ledgerType==2">困   难</s:if>
	          			<s:elseif test="reportForm.ledgerType==3">稳   定</s:elseif>
	          			<s:else>民   生</s:else>
	          		</u>信息呈报表
	          	    <br><br>
	          	</td>
        	</tr>
        	<tr>
        		<td width="24%" class="tdhr"><b>呈报单位（盖章）：</b></td>
	          	<td width="36%">${reportForm.sourceOrg.orgName}</td>
	          	<td width="16%" class="tdhr"><b>呈报时间：  </b></td>
	          	<td width="24%"><s:date name="reportForm.reportDate" format="yyyy年MM月dd日" /></td>
        	</tr>
        	<tr>
	          	<td width="100%" colspan="6" align="center"><hr/></td>
        	</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
        
        <tr class="tr1" height="50">
       		<td height="50" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		上级部门
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
            	${reportForm.targetOrg.orgName}
            </td>
       	</tr>
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		呈报主要事项及原因
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${reportForm.reason}
            </td>
       	</tr>
       	
       		<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		呈报单位工作建议意见
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${reportForm.handleContent}
            	<p align="right" valign="bottom" style="margin-right:120px;margin-top:40px;">  主要领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
            	<p align="right" valign="bottom" style="margin-right:20px;"> &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</p>
            </td>
       	</tr>
       		<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		上级单位领导审核意见
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">&nbsp;&nbsp;&nbsp;
            	<p align="right" valign="bottom" style="margin-right:120px;margin-top:40px;">  签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
            	<p align="right" valign="bottom" style="margin-right:20px;"> &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</p>
            </td>
       	</tr>
       	<tr class="tr1" >
       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		呈报单位联系人姓名
       		</td>
            <td width="48%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
           ${reportForm.name}
            </td>
            <td width="4%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >联系电话</td>
            <td width="36%" style="text-align:left;font-Size:10px; height:20px;width:36%;white-space: nowrap;" >
             ${reportForm.mobile}
            </td>
        </tr>
       	
    </table>
</div>
