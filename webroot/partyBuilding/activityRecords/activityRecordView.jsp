<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dialog-form"  class="container container_24">
	    <div class="grid_4 lable-right">
			<label >所属网格：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${activityRecords.organization.orgName}"  disabled="disabled" />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>所属党支部：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="activityRecords.organizationName" id="organizationName" class="form-txt" value="${activityRecords.organizationName}" disabled="disabled" />
			<input type="hidden" name="activityRecords.organizationId" id="" class="form-txt" value="${activityRecords.organizationId}" />
			<input type="hidden" name="activityRecords.organizationType" id="" class="form-txt" value="${partyOrganizationType }" />
		</div>

		<div class="grid_4 lable-right">
			<label>活动时间：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="activityDate" name="activityRecords.activityDate" class="form-txt" disabled="disabled" value="<fmt:formatDate value="${activityRecords.activityDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>活动地点：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityPlace" name="activityRecords.activityPlace" class="form-txt" maxlength="300" disabled="disabled" value="${activityRecords.activityPlace}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>活动主题：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityTheme" name="activityRecords.activityTheme" class="form-txt" maxlength="150" disabled="disabled"  value="${activityRecords.activityTheme}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>组织者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="organizer" name="activityRecords.organizer" class="form-txt" maxlength="250" disabled="disabled" value="${activityRecords.organizer}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>参与者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="participant" name="activityRecords.participant" class="form-txt" maxlength="250" disabled="disabled" value="${activityRecords.participant}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label >活动内容：</label>
		</div>
		<div style="display:inline;float:left;height:110px;line-height:110px;width:40.916%;">
			<textarea name="activityRecords.details" style="height:95px;" class="form-txt" disabled="disabled">${activityRecords.details}</textarea>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none" disabled="disabled" ></select>
		<div style="clear:both"></div>
		<div id="attachFilesDiv" style="position:absolute;top:76px;left:420px;">
			<div id="custom-queue" style="width: 180px; height: 172px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		</div>
</div>

<script type="text/javascript">
$(document).ready(function(){

	fillFile();
	$("#custom-queue div.cancel a").each(function(index){
		$(this).hide()
	});

});
function fillFile(){
	<c:if test="${activityRecords.activityRecordFiles!=null && fn:length(activityRecords.activityRecordFiles)>0}">
        <s:iterator value="activityRecords.activityRecordFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/partyBuilding/activityRecordManage/downloadActivityRecordsAttachFiles.action?activityRecordsAttachFiles.id=<s:property value='id'/>"
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</c:if>
}
</script>