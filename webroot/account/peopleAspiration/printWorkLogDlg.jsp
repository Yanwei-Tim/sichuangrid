<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<LedgerFeedBack> feedbacks = (List) request
			.getAttribute("feedbacks");
	if (feedbacks != null && feedbacks.size() > 0) {
		request.setAttribute("feedback", feedbacks.get(0));
	}
%>


<%      int pageSize= 12;
     	List<ThreeRecordsIssueLogNew> issueDealLogs = (List) request
     			.getAttribute("issueDealLogs");
		if(issueDealLogs==null||issueDealLogs.size()==0){
			request.setAttribute("logs", new ArrayList());
		}
		else{
      	int total = issueDealLogs.size() % pageSize == 0 ? issueDealLogs.size() / pageSize
      			: (issueDealLogs.size() / pageSize + 1);
      	for (int i = 0; i < total; i++) {
      		if (i == total - 1)
      			request.setAttribute("logs", issueDealLogs.subList(i * pageSize,
      					issueDealLogs.size()));
      		else
      			request.setAttribute("logs", issueDealLogs.subList(i * pageSize,
      					(i + 1) * pageSize));
     	}
  %>
      <div style="page-break-after: always;"></div> 
      <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=13>
        			<p>工</p><p>作</p><p>情</p><p>况</p><p>记</p><p>录</p>
        		</td>
        		<td width="18%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;white-space: nowrap;" >日期</td>
	            <td width="60%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:60%;font-weight:750;white-space: nowrap;" >工作措施及内容</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">部门</td>
        	</tr>
        	<s:iterator status="index" value="#request.logs">
        		<tr class="tr1">
        			<td width="18%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;white-space: nowrap;" >
        			<s:date name="operateTime" format="yyyy年MM月dd日" /></td>
        			
        			<td width="54%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" >
        			<s:property value="dealDescription"/>${content }</td>
        			<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;" >
        			${dealOrg.orgName }</td>
        			
        		</tr>
              </s:iterator>
              <c:forEach begin="${fn:length(logs)+2}" end="13">
        		<tr class="tr1">
        			<td width="18%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;" >&nbsp;
        			</td>
        			<td width="54%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:54%;font-weight:750;" >&nbsp;
        			</td>
        			<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" >&nbsp;
        			</td>
        		</tr>
        	</c:forEach>
   
    </table>
    <div style="page-break-after: always;"></div> 
    <%
     	}
     	List<ThreeRecordsIssueLogNew> last3Logs = new ArrayList();
     	int j = 0;
     	if(issueDealLogs!=null&&issueDealLogs.size()!=0){
	     	for (int i = issueDealLogs.size() - 1; i > 0; i--) {
	     		if (j == 3){
	     			break;
	     		}
	     		ThreeRecordsIssueLogNew log = issueDealLogs.get(i);
	     		if (log.getDealType() == null)
	     			continue;
	     		if (log.getCompleteType() != null) {
	     			if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.REALCOMPLETE)
	     				log.setDealDescription("①");
	     			else if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.QUESTIONEND)
	     				log.setDealDescription("②");
	     			else if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.POLICYSOLUTION)
	     				log.setDealDescription("③");
	     			else if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.ITEM)
	     				log.setDealDescription("⑥");
	     			else if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.IMPLEMENT)
	     				log.setDealDescription("④");
	     			else if (log.getCompleteType() == com.tianque.plugin.account.constants.CompleteType.INTEGRATE_PROJECT)
	     				log.setDealDescription("⑤");
	     			last3Logs.add(log);
	     			++j;
	     		} else if (log.getTargeOrg() != null) {
	     			if (log.getDealType().intValue() == com.tianque.plugin.account.state.ThreeRecordsIssueOperate.DECLARE
	     					.getCode()) {
	     				if (log.getDealDescription().contains("联席")) {
	     					log.setDealDescription("⑨");
	     				} else
	     					log.setDealDescription("⑩");
	     			} else if (log.getDealType().intValue() == com.tianque.plugin.account.state.ThreeRecordsIssueOperate.SUBMIT
	     					.getCode()) {
	     				if (log.getDealDescription().contains("台账办")) {
	     					log.setDealDescription("⑧");
	     				} else if (log.getDealDescription().contains("镇")) {
	     					log.setDealDescription("⑦");
	     				} else
	     					log.setDealDescription("⑪");
	     			} else {
	     				log.setDealDescription("⑪");
	     			}
	     			++j;
	     			last3Logs.add(log);
	     		}
	     		
	     	}
     	}
     	request.setAttribute("last3Logs",last3Logs);
     	
     %>
    
    
     <div style="page-break-after: always;"></div> 
    
     <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" >
        			办结时间
        		</td>
        		<td width="18%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;white-space: nowrap;" >
        		<c:forEach items="${last3Logs}"  var="last3Logs">
        			<p><fmt:formatDate value="${last3Logs.operateTime}" pattern="yyyy年	MM月	dd日"/>落实&nbsp;${last3Logs.dealDescription}&nbsp;方式</p>
        		</c:forEach>
        		
        		
	        	 <c:forEach begin="${fn:length(last3Logs)}" end="2">
	        		<p>&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日落实&nbsp;&nbsp;&nbsp;&nbsp;方式</p>
	        	 </c:forEach>
        		
        		</td>
	            <td width="60%" colspan="2" style="text-align: left;font-Size:10px; height:20px;width:60%;" >
	             <p> 办结方式：</p>
 				 <p> 实质性办结：①实质办结②问题中止</p>
 				 <p> 程序性办结：③政策解答⑥纳入项目库⑦呈报乡镇
 				 		⑧呈报县台账办
 				 		⑨申报县台账工作联席会议
 				 		⑩申报县委县政府⑪其他方式</p>
 				 <p> 阶段性办结：④落实项目、落实帮扶⑤整合项目</p>
  				</td>
        	</tr>
        	
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" >
        			办理结果
        		</td>
        		<td width="60%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:60%;font-weight:750;" >
        		
        		<c:forEach items="${last3Logs}"  var="last3Logs">
        			<p>${last3Logs.content}</p>
        		</c:forEach>
        		 <c:forEach begin="${fn:length(last3Logs)}" end="2">
        			<p>&nbsp;</p>
        	 	</c:forEach>
        		</td>
	            <td width="18%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;white-space: nowrap;" >转入其他台账</td>
	            <td id="intoOtherParameter" width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	           		 稳定工作台账 </br>
					困难群众工作台账 </br>
	            </td>
        	</tr>
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;" width="18%" >
        			<p>承办人信息反馈方式：</p>
       				<p><input type="checkbox" name="feedBackType" 
       					<s:if test="#request.feedback.feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_VERBAL">checked="checked"</s:if>
       				  	value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_VERBAL'/>" disabled/>口头 </p>
        			<p><input type="checkbox" name="feedBackType"
        				<s:if test="#request.feedback.feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_PHONE">checked="checked"</s:if>
        			 	value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_PHONE'/>" disabled/>电话  </p>
      				<p><input type="checkbox" name="feedBackType" 
      					<s:if test="#request.feedback.feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_WRITTEN">checked="checked"</s:if>
      					value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_WRITTEN'/>" disabled/>书面 </p>
        		</td>
        		<td colspan="3"  width="18%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:18%;font-weight:750;white-space: nowrap;" >
        		
        			<p>反映人反馈意见：</p>
        			
    				<input type="checkbox" name="feedBackOpinion"
    				<s:if test="#request.feedback.feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_SATISFACTION">checked="checked"</s:if>
    				 value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_SATISFACTION'/>" disabled/> 满意
    				<input type="checkbox" name="feedBackOpinion" 
    				<s:if test="#request.feedback.feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_RUDIMENTARY_SATISFACTION">checked="checked"</s:if>
    				value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_RUDIMENTARY_SATISFACTION'/>" disabled/>基本满意 
        			<input type="checkbox" name="feedBackOpinion"
        			<s:if test="#request.feedback.feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_NOT_SATISFACTION">checked="checked"</s:if>
        			 value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_NOT_SATISFACTION'/>" disabled/>不满意 
      				<input type="checkbox" name="feedBackOpinion"
      				<s:if test="#request.feedback.feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_OTHER">checked="checked"</s:if>
      				 value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_OTHER'/>" disabled/>其他 
        		
       				<p align="right" valign="bottom" style="margin-right:160px;margin-top:20px;"> 签名：</p>
        		</td>
        		
	            
        	</tr>
        	
   
    </table>
  