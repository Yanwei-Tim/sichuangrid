<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dialog-form"  class="container container_24">
	<form id="addActivityRecordForm" method="post" action="${path}/partyBuilding/activityRecordManage/maintainActivityRecord.action">
	<s:if test="#mode[0]=='add'">
	<pop:token />
	</s:if>
		<input type="hidden" name="mode" id="modePart" value="${mode}" />
		<input type="hidden" name="orgId" id="orgId" value="${orgId}" />
		<input type="hidden" name="activityRecords.id" id="mode" value="${activityRecords.id}" />
		<input type="hidden" name="activityRecords.isAttachment" id="isAttachment">	
		<input type="hidden" name="activityRecords.organization.id" id="activityRecordsOrgId" value="${orgId}" />

	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${activityRecords.organization.orgName}" readonly />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>所属党支部：</label>
		</div>
		<div class="grid_10">
		<input type="text" name="activityRecords.organizationName" id="partyOrgName" class="form-txt" value="${activityRecords.organizationName}" readonly/>
		<input type="hidden" name="activityRecords.organizationId" id="partyOrgId" class="form-txt" value="${activityRecords.organizationId}" />
		<input type="hidden" name="activityRecords.organizationType" id="partyOrganizationType" class="form-txt" value="${activityRecords.organizationType }" /> 
		</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动时间：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="activityDate" name="activityRecords.activityDate" class="form-txt" readonly="readonly" value="<fmt:formatDate value="${activityRecords.activityDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动地点：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityPlace" name="activityRecords.activityPlace" class="form-txt" maxlength="280" value="${activityRecords.activityPlace}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动主题：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityTheme" name="activityRecords.activityTheme" class="form-txt" maxlength="148" value="${activityRecords.activityTheme}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>组织者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="organizer" name="activityRecords.organizer" class="form-txt" maxlength="248" value="${activityRecords.organizer}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>参与者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="participant" name="activityRecords.participant" class="form-txt" maxlength="248" value="${activityRecords.participant}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label >活动内容：</label>
		</div>
		<div style="display:inline;float:left;height:110px;line-height:110px;width:40.916%;">
			<textarea name="activityRecords.details" style="height:95px;" class="form-txt">${activityRecords.details}</textarea>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"/>
	   	</form>
	<div style="clear:both"></div>
	<div id="attachFilesDiv" style="position:absolute;top:70px;left:420px;">
		<div id="custom-queue" style="width: 180px; height: 172px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="searchandaddDialog"></div>
	<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	
	function isAttachFileValue(){
		if($("#attachFileNames").val()){
			$("#isAttachment").val(true);
		}else{
			$("#isAttachment").val(false);
		}
	}
	
	
	$("#addActivityRecordForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			isAttachFileValue();
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							evel: "error",
							message:data
			 			});
            			return;
					}
					
	                if("add"=="${mode}"){
	                	 $.messageBox({message:"活动记录新增成功！"});
	    				// $("#activityRecordList").addRowData(data.id,data,"first");
		                // $("#activityRecordList").setSelection(data.id);
	                	 $("#activityRecordList").trigger("reloadGrid");
	                }
	                if("edit"=="${mode}"){
	                	// $("#activityRecordList").setRowData(data.id,data);
	                	 $("#activityRecordList").trigger("reloadGrid");
	                	 $.messageBox({message:"活动记录修改成功！"});
	                }
	                $("#activityRecordDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"activityRecords.organizationName":{
				required:true
			},
			"activityRecords.activityDate":{
				required:true
			},
			"activityRecords.activityPlace":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:280
			},
			"activityRecords.activityTheme":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:148
			},
			"activityRecords.organizer":{
				minlength:2,
				maxlength:248
			},
			"activityRecords.participant":{
				minlength:2,
				maxlength:248
			},
			"activityRecords.details":{
				required:true
			}
		},
		messages:{
			"activityRecords.organizationName":{
				required:"请选择"
			},
			"activityRecords.activityDate":{
				required:"请选择活动时间"
			},
			"activityRecords.activityPlace":{
				required:"请输入活动地点",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("活动地点至少需要输入{0}个字符"),
				maxlength:$.format("活动地点最多需要输入{0}个字符")
			},
			"activityRecords.activityTheme":{
				required:"请输活动主题",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("活动主题至少需要输入{0}个字符"),
				maxlength:$.format("活动主题最多需要输入{0}个字符")
			},
			"activityRecords.organizers":{
				minlength:$.format("组织者至少需要输入{0}个字符"),
				maxlength:$.format("组织者最多需要输入{0}个字符")
			},
			"activityRecords.participants":{
				minlength:$.format("参与者至少需要输入{0}个字符"),
				maxlength:$.format("参与者最多需要输入{0}个字符")
			},
			"activityRecords.details":{
				required:"请输入活动内容"
			}
		}
	});
	$('#activityDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#activityDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	fillFile();

});

function fillFile(){
	<c:if test="${activityRecords.activityRecordFiles!=null && fn:length(activityRecords.activityRecordFiles)> 0}">
        <s:iterator value="activityRecords.activityRecordFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/partyBuilding/activityRecordManage/downloadActivityRecordsAttachFiles.action?activityRecordsAttachFiles.id=<s:property value='id'/>",
	          onRemove:function(id){deleteActivityRecordsAttachFiles(id)}
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</c:if>
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deleteActivityRecordsAttachFiles(id){
	$.ajax({
		url:"${path}/partyBuilding/activityRecordManage/deleteActivityRecordsAttachFiles.action?activityRecordsAttachFiles.id="+id,
		type:'GET',
		dataType:'json',
		success : function(_data){
			if(_data==true){
				removeAttachById(id);
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}
</script>