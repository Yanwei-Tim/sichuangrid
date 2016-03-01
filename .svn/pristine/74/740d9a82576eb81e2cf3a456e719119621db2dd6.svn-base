<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="issueContentInfo messageContentInfo">
	<h1 class="issueReTitle"><em>${platformMessage.title}</em></h1> 
	<ul class="issueContentInfoList">
		
		<c:if test="${platformMessage.sender.userName!='admin'}">
		<li>
			<span>发件人：</span><strong>${platformMessage.sender.name }</strong>
		</li>
		<li><span>发件人所属部门：</span>
			<strong>
				${platformMessage.sender.organization.orgName }
			</strong>
		</li>
		</c:if>
		<li>
			<span>收件人：</span><strong id="senderName">${platformMessage.receiverNames}</strong>
		</li>
		<c:if test="${platformMessage.pmAttachFiles!=null && fn:length(platformMessage.pmAttachFiles)>0}">
			<li>
				<span>附件：</span>
				<c:forEach items="${platformMessage.pmAttachFiles}" var="pmAttachFile">
		             <a target="_blank" href="${path}/interactive/outboxPlatformMessageManage/downloadPmAttachFileById.action?pmAttachFile.id=${pmAttachFile.id}" >${pmAttachFile.fileName}</a>&nbsp;&nbsp;
				</c:forEach>
			</li>
		</c:if>
	</ul>
</div>
<div class="instruction clearfix">
	<p>
		消息内容：${platformMessage.content }
		<c:if test="${platformMessage.url!=null }"> 
	   		<a href="javascript:showPageByTopMenu('${platformMessage.url}');" style="color: #2D62AC;text-decoration: underline;">点击这里查看</a>
	   	</c:if>
	</p>
</div>
<p>
	<c:if test="${platformMessage.historyMessage!=null }">
		<h4 class="historyTag"><span>历史消息</span></h4>
		${platformMessage.historyMessage}
	</c:if>
</p>

<script type="text/javascript">
function viewPartReceiverName(){
	var senderName = "${platformMessage.receiverNames}";
	var name="";
	if(senderName!=null && senderName!=undefined && senderName.length!=0){
		if(senderName.length >50){
			name = senderName.substring(0,50)+"．．．．．<a href='javascript:;' id='viewAllReceiver'><font color='blue'><点击查看所有></font></a>";
		}
	}
	if(name.length!=0){
		$("#senderName").html(name);
	}
	$("#viewAllReceiver").click(function(){
		viewAllReceiverName();
	});
}

function viewAllReceiverName(){
	var senderName = "${platformMessage.receiverNames}";
	senderName = senderName+"    <a href='javascript:;' id='partReceiverName'><font color='blue'><收起></font></a>"
	$("#senderName").html(senderName);
	$("#partReceiverName").on('click',function(){
		viewPartReceiverName();
	});
}

$(document).ready(function(){
	viewPartReceiverName();
	
	$("#viewAllReceiver").click(function(){
		viewAllReceiverName();
	});
	$("#putAway").click(function(){
		viewPartReceiverName();
	});
	
});

</script>