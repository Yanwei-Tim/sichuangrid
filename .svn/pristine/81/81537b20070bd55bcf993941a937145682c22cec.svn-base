<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="" class="container container_24">
	<form id="searchInboxPlaformMessageForm">
		<div class="grid_4 lable-right">
		<label class="form-lbl">标题：</label>
		</div>
		<div class="grid_8">
			<input type="text"  name="searchPlatformMessageVo.title"  class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">内容：</label>
		</div>
		<div class="grid_8">
			<input type="text"  name="searchPlatformMessageVo.content"  class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">日期 从：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="startTime"  name="searchPlatformMessageVo.sendTimeStart" class="form-txt" />
		</div>
			<div class="grid_4 lable-right">
			<label class="form-lbl">到：</label>
		</div>
		
		<div class="grid_8">
			<input type="text" id="endTime"  name="searchPlatformMessageVo.sendTimeEnd"  class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否有附件：</label>
		</div>
		<div class="grid_8">
			<select name="searchPlatformMessageVo.hasAttach" class="form-txt">
				<option value="">请选择</option>
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否已读：</label>
		</div>
		<div class="grid_8">
			<select name="searchPlatformMessageVo.readState" class="form-txt">
				<option value="">请选择</option>
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">消息类型：</label>
		</div>
		<div class="grid_8">
			<select name="searchPlatformMessageVo.messageType" class="form-txt">
				<option value="">请选择</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SYSTEM_MESSAGE" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SYSTEM_MESSAGE_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NEED_DO_ISSUE_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NEED_DO_ISSUE_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SERIOUS_ISSUE_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SERIOUS_ISSUE_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_REPORT_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_REPORT_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_RUSHTO_REPORT_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_RUSHTO_REPORT_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_BACK_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_BACK_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_REPORT_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_REPORT_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_BACK_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_BACK_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SIGN_FILE_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SIGN_FILE_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_REPORT_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_REPORT_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_BACK_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_BACK_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SHARED_FILE_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SHARED_FILE_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@RECTIFICATIVE_PERSON_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@RECTIFICATIVE_PERSON_REMIND_DisplayName" />
				</option>
				
				<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NURTURES_WOMEN_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NURTURES_WOMEN_REMIND_DisplayName" />
				</option>
				<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@FLOATINGPOPULATION_PEOPLE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@FLOATINGPOPULATION_PEOPLE_REMIND_DisplayName" />
				</option>
				
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@POSITIVEINFO_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@POSITIVEINFO_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@IDLEYOUTH_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@IDLEYOUTH_REMIND_DisplayName" />
				</option>
				<option  value="<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ELDERLY_PEOPLE_REMIND" />">
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ELDERLY_PEOPLE_REMIND_DisplayName" />
				</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">发件人：</label>
		</div>
		<div class="grid_8" >
			<input type="text" id="senderId" name="searchPlatformMessageVo.sendUserId" class="form-txt" />
		</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#startTime').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'}); 
	$('#endTime').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'}); 

	//发件人的自动完成
	$("#senderId").personnelComplete({
		url: "${path}/interactive/outboxPlatformMessageManage/searchContacterForAutoComplete.action",
		multiple: false,
		postDataFunction: function(){
		    return {orgId:''};
		}
	});

	$("#holder_senderId").attr("style","height: 63%;");
	
    
});
</script>


