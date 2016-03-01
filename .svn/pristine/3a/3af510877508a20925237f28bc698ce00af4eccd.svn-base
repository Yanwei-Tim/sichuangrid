<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div >
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>
	<table  width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${replyForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县三本台账工作<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
							<s:if test="replyForm.ledgerType==2">困   难</s:if>
		          			<s:elseif test="replyForm.ledgerType==3">稳   定</s:elseif>
		          			<s:else>民   生</s:else>
					</u>信息回复表
	          	    <br><br>
	          	</td>
        	</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        <tr>
       		<td width="18%" class="tdhr"><b>回复单位：</b></td>
          	<td width="42%">${replyForm.sourceOrg.orgName}</td>
          	<td width="16%" class="tdhr"><b>回复时间：  </b></td>
          	<td width="24%"><s:date name="replyForm.replyDate" format="yyyy年MM月dd日" /></td>
        </tr>	
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		主要事项
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${replyForm.reason}
            </td>
       	</tr>
       	
       		<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		回复内容摘要
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${replyForm.handleContent}
            	<p align="right" valign="bottom" style="margin-right:20px;"> &nbsp;&nbsp;&nbsp;&nbsp;</p>
            </td>
       	</tr>
       	<tr class="tr1" >
       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
       		回复单位联系人
       		</td>
            <td width="48%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
           ${replyForm.name}
            </td>
            <td width="4%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >联系电话</td>
            <td width="36%" style="text-align:left;font-Size:10px; height:20px;width:36%;white-space: nowrap;" >
             ${replyForm.mobile}
            </td>
        </tr>
       	
    </table>
</div>
