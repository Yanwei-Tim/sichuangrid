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
	<table  width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${turnForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县三本台账工作<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
	          			<s:if test="turnForm.ledgerType==2">困   难</s:if>
	          			<s:elseif test="turnForm.ledgerType==3">稳   定</s:elseif>
	          			<s:else>民   生</s:else>
	          		
	          		</u>信息转办事项通知单
	          	    <br><br>
	          	</td>
        	</tr>
        	
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
       		<tr>
        		<td width="12%" class="tdhr"><b>承办单位：</b></td>
	          	<td width="48%">${turnForm.targetOrg.orgName}</td>
	          	<td width="16%" class="tdhr"><b>责任人：  </b></td>
	          	<td width="24%">${turnForm.manager}</td>
        	</tr>	
        
       
	       	<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       			主要事项
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			${turnForm.reason}
	            </td>
	       	</tr>
       	
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		办理时限
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			<s:if test="turnForm.handleStartDate!=null"><s:date name="turnForm.handleStartDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	       			   —— 
	       			<s:if test="turnForm.handleEndDate!=null"><s:date name="turnForm.handleEndDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>	   
	            </td>
       		</tr>
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		转办单位分管领导意 见
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	            	${turnForm.subOpinion}
	            </td>
       		</tr>
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		转办单位主要领导意见
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			${turnForm.subOpinion}
	            	<p align="right" valign="bottom" style="margin-right:120px;margin-top:40px;">  签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	            	<p align="right" valign="bottom" style="margin-right:20px;"> &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</p>
	            </td>
       		</tr>
       		<tr>
        		<td width="12%" class="tdhr"><b>承办单位接件时间</b></td>
	          	<td width="48%">
	          		<s:if test="turnForm.receiveDate!=null"><s:date name="turnForm.receiveDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	          	</td>
	          	<td width="16%" class="tdhr"><b>承办单位联系人：  </b></td>
	          	<td width="24%">${turnForm.name}</td>
        	</tr>	
       		
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		办理结果
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	            	${turnForm.handleResult}
	            </td>
       		</tr>
    </table>
</div>
