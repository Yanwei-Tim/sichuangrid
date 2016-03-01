<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div >
	<style>
	.printAssignForm{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.printAssignForm td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.printAssignForm .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.printAssignForm .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>
	<table width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${assignForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<s:if test="assignForm.assignType==2">
		          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
		          		中江县三本台账工作县委县政府交办单
		          		<br><br>
		          	</td>
	          	</s:if>
	          	<s:else>
		          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
		          		中江县三本台账工作联席会议<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
		          			<s:if test="assignForm.ledgerType==2">困   难</s:if>
		          			<s:elseif test="assignForm.ledgerType==3">稳   定</s:elseif>
		          			<s:else>民   生</s:else>
		          		</u>信息交办单
		          	    <br><br>
		          	</td>
	          	</s:else>
        	</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="printAssignForm">
        <s:if test="assignForm.assignType==2">	
         <tr class="tr1" height="20">
       		<td height="20" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" width="20%" >
       		台账类型
       		</td>
       		<td  style="text-align:left;font-Size:10px; height:20px;" >
            	<ul class="propertyMulSelect">
	            	<li><input type='checkbox'  name='type' disabled='disabled' <s:if test="assignForm.ledgerType!=3&&assignForm.ledgerType!=2">checked='checked'</s:if>/>&nbsp;&nbsp;&nbsp;民生工作台账 </li>
	            	<li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==2">checked='checked'</s:if>/>&nbsp;&nbsp;&nbsp;困难群众工作台账  </li>
	            	<li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==3">checked='checked'</s:if>/>&nbsp;&nbsp;&nbsp;稳定工作台账  </li>
            	</ul>
            </td>
          
            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >会议期数</td>
            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
             ${assignForm.periods}
            </td>
             <td colspan="2" style="text-align:left;font-Size:10px; height:20px;width:24%;" >
             	会议类型<pop:PropertyDictMultiCheckbox name="meetingType"
					column="2" readOnly="true"
					domainName="@com.tianque.plugin.account.property.PropertyTypes@ASSGIN_MEETING_TYPE"/>
            </td>
       	</tr>
       	</s:if>
       	<s:else>
       	<td height="20" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" width="20%" >
       		台账类型
       		</td>
       		<td  colspan="3" style="text-align:left;font-Size:10px; height:20px;" >
            	<ul class="propertyMulSelect">
	            	<li><input type='checkbox'  name='type' disabled='disabled' <s:if test="assignForm.ledgerType!=3&&assignForm.ledgerType!=2">checked='checked'</s:if>/>民生工作台账 </li>
	            	<li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==2">checked='checked'</s:if>/>困难群众工作台账  </li>
	            	<li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==3">checked='checked'</s:if>/>稳定工作台账  </li>
            	</ul>
            </td>
            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >会议期数</td>
            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
             ${assignForm.periods}
            </td>
       	</s:else>
      
       	<tr class="tr1" height="40">
       		<td height="40" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
       		主要事项
       		</td>
       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${assignForm.reason}
            </td>
	       	</tr>
	       		<tr class="tr1" height="40">
	       		<td height="40" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
		       		<s:if test="assignForm.assignType==2">
		       		会议议定事项摘要
		       		</s:if><s:else>
			       		<span>联席会议</span>
			       		<span>议决内容</span>
			       		<span>摘要</span>
		       		</s:else>
	       		</td>
	       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			${assignForm.handleContent}
	            </td>
	       	</tr>
        
        	 <c:forEach var="receiver" items="${assignForm.receivers}" varStatus="status">
        		<tr class="tr1" >
	       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		    <c:if test="${receiver.isManage }">主办单位</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位${status.index}</c:if>
	       		</td>
	            <td colspan="3" width="36%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
	           		${receiver.targetOrg.orgName}
	            </td>
	            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >责任人</td>
	            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
	             	${receiver.manager}
	            </td>
             </tr>
             </c:forEach>
             <c:forEach begin="${fn:length(assignForm.receivers)}" end="3" varStatus="status">
        		<tr class="tr1" >
		       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" width="20%" >
		       		    协办单位${status.index}
		       		</td>
		            <td colspan="3"  width="36%" style="text-align: left;font-Size:10px; height:20px;width:36%;white-space: nowrap;">
		            </td>
		            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >责任人</td>
		            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
		            </td>
	            </tr>
        	</c:forEach>
        
	        <tr class="tr1">
	       		<td  style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		办理时限
	       		</td>
	       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			<s:if test="assignForm.handleStartDate!=null"><s:date name="assignForm.handleStartDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	       			   —— 
	       			<s:if test="assignForm.handleEndDate!=null"><s:date name="assignForm.handleEndDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>	   
	            </td>
	       	</tr>
        
       		 <c:forEach var="receiver" items="${assignForm.receivers}" varStatus="status">
        		<tr class="tr1" >
	       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		    <c:if test="${receiver.isManage }">责任单位接件时间</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位${status.index}接件时间</c:if>
	       		</td>
	            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<fmt:formatDate value="${receiver.receiveDate}" pattern="yyyy年MM月dd日"/>
	            </td>
	            <td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		    <c:if test="${receiver.isManage }">责任单位联系人</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位联系人</c:if>
	       		</td>
	            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	           		${receiver.name}
	            </td>
	            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >联系电话</td>
	            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
	             	${receiver.mobile}
	            </td>
             </tr>
             </c:forEach>
             <c:forEach begin="${fn:length(assignForm.receivers)}" end="3" varStatus="status">
	            <tr class="tr1" >
		       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
		       		    协办单位${status.index}接件时间
		       		</td>
		            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
		            </td>
		            <td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
		       		   	协办单位联系人
		       		</td>
		            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
		            </td>
		            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >联系电话</td>
		            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
		            </td>
             	</tr>
        	</c:forEach>
    </table>
</div>

<script type="text/javascript">
var meetingType='${assignForm.type.id}';
$(document).ready(function(){
	$("input[name='meetingType']").each(function(){
        if($(this).val() ==meetingType) 
           $(this).attr('checked',"checked");
   });
	$(".printAssignForm li").each(function(){
		$(this).attr("style","width:50%;white-space: nowrap;text-align:left;display: list-item;list-style: none;");
	})
})
</script>