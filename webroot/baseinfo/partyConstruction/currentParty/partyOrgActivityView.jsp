<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	    <div class="grid_4 lable-right">
			<label >所属网格：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${population.organization.orgName}"  disabled="disabled" />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>所属党支部：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="population.partyOrgId" id="partyOrgId" value="${population.partyOrgInfo.partyBranchName}"  disabled="disabled" />
		</div>

		<div class="grid_4 lable-right">
			<label>活动时间：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="activityDate" name="population.activityDate" class="form-txt" disabled="disabled" value="<s:date name="population.activityDate" format="yyyy-MM-dd"/>" class="form-txt" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>活动地点：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityAddr" name="population.activityAddr" class="form-txt" maxlength="300" disabled="disabled" value="${population.activityAddr}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>活动主题：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activitySubject" name="population.activitySubject" class="form-txt" maxlength="150" disabled="disabled"  value="${population.activitySubject}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>组织者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="organizers" name="population.organizers" class="form-txt" maxlength="250" disabled="disabled" value="${population.organizers}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>参与者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="participants" name="population.participants" class="form-txt" maxlength="250" disabled="disabled" value="${population.participants}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label >活动内容：</label>
		</div>
		<div style="display:inline;float:left;height:110px;line-height:110px;width:40.916%;">
			<textarea name="population.activeContent" style="height:95px;" class="form-txt" disabled="disabled">${population.activeContent}</textarea>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none" disabled="disabled" />
		<div style="clear:both"></div>
		<div id="attachFilesDiv" style="position:absolute;top:65px;left:400px;">
			<div id="custom-queue" style="width: 180px; height: 172px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		</div>
</div>

<script type="text/javascript">
$(document).ready(function(){

	fillFile();

});
function fillFile(){
	<s:if test="population.activityAttachFile!=null && population.activityAttachFile.size > 0">
        <s:iterator value="population.activityAttachFile">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/baseinfo/partyOrgActivityManage/downloadPartyOrgActivityFile.action?partyOrgActivityFile.id=<s:property value='id'/>",
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</s:if>
}
</script>