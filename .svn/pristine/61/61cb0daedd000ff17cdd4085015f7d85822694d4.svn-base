<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.defaultTable{width;100%;}
.defaultTable{margin-top: 5px;width:100%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
.defaultTable th{
background:#efefef;
vertical-align:middle;text-align:center;
height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
.defaultTable td{padding:5px;height:26px;line-height:26px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
.defaultTable .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
.defaultTable .title{width:13%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
.defaultTable .text{width:25%;}
.defaultTable .number{width:50px;}
.defaultTable .textarea{width:70px;height:70px;}
.defaultTable .add{color:#339900;font:bold 14px/1.5em '' !important;}
.defaultTable .subtract{color:#FF0000;font:bold 14px/1.5em '' !important;}
.defaultTable .rowInfo{color:#999;border:0;}
.defaultTable .rowInfo td{border:0;}
.defaultTable .rowInfo .rowTitle{font:bold 16px/1.5em '' !important;}
.defaultTable .rowInfo .rowSmalltext{color:#333;}
.rowtitle{color:#444;font:bold 14px/35px "" !important;}
.rowtitle span{color:#999;font-size:14px !important;}
.operator{}
</style>
<div id="dialog-form" title="事件处理记录修改" class="container container_24">
	<input type="hidden" name="issue.id" value="${keyId}">
	      <table class="defaultTable" id="editIssueLogTable">
	          <tbody>
	          	<tr>
	                  <th colspan="1" rowspan="1" width="70%">事件处理记录</th>
	                  <th colspan="1" rowspan="1" width="20%">附件</th>
	                  <th colspan="1" rowspan="1" width="10%">操作</th>
	              </tr>
	              <s:subset source="issueDealLogs" start="0"><s:iterator status="index">
	              <tr>
	                  <td>
	                  	<table class="rowInfo">
	                  		<tr>
	                  			<td style="width:30%;vertical-align: middle;">
	                  			<span class="rowTitle">
	                  			<c:choose>
								    <c:when test="${dealUserName== '超级管理员' || dealUserName=='admin'}">
								     	[系统消息]
								    </c:when>
								    <c:otherwise>
								    	[<s:property value="dealOrg.orgName"/>]
								    </c:otherwise>
								</c:choose>
	                  			</span></td>
	                  			<td style="width:50%;">
	                  				<span class="rowDate"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></span>
	                  				<div class="rowSmalltext">${content }</div>
	                  			</td>
	                  			<td style="width:20%;"><span class="rowName">
	          
	                  			<c:choose>
								    <c:when test="${dealUserName== '超级管理员' || dealUserName=='admin'}">
								     	[系统消息]
								    </c:when>
								    <c:otherwise>
								    	[<s:property value="dealUserName"/>]
								    </c:otherwise>
								</c:choose>
	                  			<s:property value="dealDescription"/></span></td>
	                  		</tr>
	                  	</table>
	                  </td>
	                  <td>
	                  	<c:if test="${issueLogAttachFiles[id] != null && fn:length(issueLogAttachFiles[id])>0}">
	                  	 	<c:forEach items="${issueLogAttachFiles[id]}" var="issueLogAttachFile">
                                  <a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=${issueLogAttachFile.id}">${issueLogAttachFile.fileName}</a>
                            </c:forEach>
                  		</c:if>
	                  </td>
	                  <td style="vertical-align: middle;">
	                 	 <span class="rowTitle" id="opratorSpan">
	                 	 	<div class="operator" stepId="<s:property value='issueStep.id'/>"></div> 
	                 	 	<s:if test="content!=null && !''.equals(content)">
								    <s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()">
								      <a href="javascript:void(0)" onclick="editIssueLog(<s:property value='issue.id'/>,<s:property value='id'/>)">【修改】</a>
								    </s:if>
								    <s:elseif test="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId().equals(dealOrg.id)">
								    	<a href="javascript:void(0)" class="operator" stepId="<s:property value='issueStep.id'/>" onclick="editIssueLog(<s:property value='issue.id'/>,<s:property value='id'/>)">【修改】</a>
								    </s:elseif>
	                 	 	</s:if>
	                 	 </span>
	                  </td>
	              </tr>
	              </s:iterator></s:subset>
	          </tbody>
	      </table>
</div>
<div id="editIssueLog"></div>
<script type="text/javascript">
function editIssueLog(issueId,issueLogId){
	$("#editIssueLog").createDialog({
		width: 700,
		height: 400,
		title: "修改操作记录",
		url: "${path}/issues/issueManage/dispatch.action?mode=editIssueLog&issueLogForEdit.id="+issueLogId+"&issue.id="+issueId,
		buttons: {
	   		"保存" : function(event){
	   			$("#issueLogForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$(function(){
	var operatorArray = $("#editIssueLogTable").find("a.operator");
	var  operatorDivArray= $("#editIssueLogTable").find("div.operator");
	var lastDiv=operatorDivArray[operatorDivArray.length-1];
	for(var i=0;i<operatorArray.length;i++){
		if($(lastDiv).attr("stepId")!=$(operatorArray[i]).attr("stepId")){
			$(operatorArray[i]).hide();
		}
	}	
});
</script>