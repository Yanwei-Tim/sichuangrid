<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="issueContentInfo messageContentInfo">
	<h1 class="issueReTitle"><em>${platformMessage.title}</em><b></b></h1> 
	<ul class="issueContentInfoList">
		<li>
			<span>发件人：</span>
			<strong><s:if test="platformMessage.sender.userName=='admin'">系统消息</s:if><s:else>${platformMessage.sender.name}</s:else></strong>
		</li>
		<li>
			<span>发件人所属部门：</span>
			<strong>
				<s:if test="@com.tianque.platformMessage.constants.PlatformMessageSendType@MANUAL_SNED.equals( platformMessage.sendType)">${platformMessage.sender.organization.orgName }</s:if>
			</strong>
		</li>
		<li><span>收件人类型：</span>
			<strong>
				<s:if test="@com.tianque.platformMessage.constants.ReceiverType@ORG.equals(platformMessage.receiverType)">
					 部门
					</s:if>
					<s:elseif test="@com.tianque.platformMessage.constants.ReceiverType@USER.equals(platformMessage.receiverType)">
					个人
					</s:elseif>
					<s:elseif test="@com.tianque.platformMessage.constants.ReceiverType@ROLE.equals(platformMessage.receiverType)">
					岗位
				</s:elseif>
			</strong>
		</li>
		<s:if test="platformMessage.pmAttachFiles!=null && platformMessage.pmAttachFiles.size()>0">
			<li>
				<span>附件：</span>
				<s:iterator value="platformMessage.pmAttachFiles" var="pmAttachFile">
		             <a target="_blank" href="${path}/interactive/outboxPlatformMessageManage/downloadPmAttachFileById.action?pmAttachFile.id=${pmAttachFile.id}" >${pmAttachFile.fileName}</a>&nbsp;&nbsp;
				</s:iterator>
			</li>
		</s:if>
	</ul>
</div>
<div class="instruction clearfix">
	<p>
		消息内容：${platformMessage.content }
		<s:if test='platformMessage.url!=null && platformMessage.content.indexOf("设置") > 0'>
		       <a href="javascript:showPageByTopMenu('${platformMessage.url}');" style="color: #2D62AC;text-decoration: underline;">点击这里设置</a>
		</s:if>
	   	<s:if test='platformMessage.url!=null && platformMessage.content.indexOf("设置") < 0' > 
	   		<a href="javascript:showPageByTopMenu('${platformMessage.url}');" style="color: #2D62AC;text-decoration: underline;">点击这里查看</a>
	   	</s:if>
	</p>
	<s:if test="platformMessage.historyMessage!=null">
		<h4 class="historyTag"><span>历史消息</span></h4>
		<div>
			${platformMessage.historyMessage}
		</div>
	</s:if>
</div>
<div class="jqsubgrid" >
	<div class="container_24">
        <s:if test='!"系统发送".equals(platformMessage.sendType)'>
	        <div class="grid_24 heightAuto">
            	<div style="display:none">
					<input type="text" id="key" value="<pop:GetGlobalSettingValueTag key="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_PKI"/>"/>
					<input type="text" id="code" value="<pop:GetGlobalSettingValueTag key="@com.tianque.core.globalSetting.util.GlobalSetting@VIDEO_CONFERENCE_KEY"/>"/>
					<input type="text" id="u" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>@hztianque.com"/>
					<input type="text" id="founder" value="${platformMessage.sender.userName}@hztianque.com"/>
					<input id="tempInput" type="hidden"/>
				</div>
	        </div>
        </s:if>
	</div>
</div>
<div id="inboxDlg"></div>
<script type="text/javascript">
$(document).ready(function(){
	getMessageByUser();
	checkButton($("#restoreTop"));
	checkButton($("#restoreBottom"));
});
function checkButton(restore){
	restore.click(function(event){
		replyPlatformMessage(${platformMessage.id});
		$("#personelMessageList").trigger("reloadGrid");
	});
}
function toJoinMeet() {
	$("#inboxDlg").createDialog({
		width: document.body.clientWidth,
		height: document.body.clientHeight,
		draggable:false,
		title:'参加会议',
		url:'${path}/interaction/netConference/acceptMettingDlg.jsp',
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	        	findUnreadInboxPlatformMessage();
	   		}
		},
		close: function() {
			meetAccLogOut();
		}
	});
}
function meetAccLogOut(acc){
	var data = {};
	data.u = $("#currentUserAcc").val();
	data.code = $('#ppMeetCode').val();
	data.key = encodeURIComponent($('#ppMeetKey').val());
	var url = 'http:\//115.236.101.203:8898/?r=/api/logout&callback=?';
	$.getJSON(url, data,function(json){});
}
</script>