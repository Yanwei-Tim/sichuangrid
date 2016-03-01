<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="addPartyOrgActivityForm" method="post" action="${path}/baseinfo/partyOrgActivityManage/addPartyOrgActivity.action">
		<input type="hidden" name="mode" id="modePart" value="${mode}" />
		<input type="hidden" name="orgId" id="orgId" value="${orgId}" />
		<input type="hidden" name="population.id" id="mode" value="${population.id}" />
		<input type="hidden" name="population.partyOrgInfo.id" id="population.partyOrgInfo.id" value="${population.partyOrgInfo.id}" />
		<input type="hidden" name="population.organization.id" id="organizationId" value="${orgId}" />

	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${population.organization.orgName}" readonly />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>所属党支部：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="population.partyOrgId" id="partyOrgId" class="form-txt" value="${population.partyOrgInfo.partyBranchName}" readonly />
		</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动时间：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="activityDate" name="population.activityDate" class="form-txt" readonly="readonly" value="<s:date name="population.activityDate" format="yyyy-MM-dd"/>" class="form-txt" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动地点：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activityAddr" name="population.activityAddr" class="form-txt" maxlength="280" value="${population.activityAddr}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>活动主题：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="activitySubject" name="population.activitySubject" class="form-txt" maxlength="148" value="${population.activitySubject}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>组织者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="organizers" name="population.organizers" class="form-txt" maxlength="248" value="${population.organizers}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label>参与者：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="participants" name="population.participants" class="form-txt" maxlength="248" value="${population.participants}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label >活动内容：</label>
		</div>
		<div style="display:inline;float:left;height:110px;line-height:110px;width:40.916%;">
			<textarea name="population.activeContent" style="height:95px;" class="form-txt">${population.activeContent}</textarea>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"/>
	   	</form>
	<div style="clear:both"></div>
	<div id="attachFilesDiv" style="position:absolute;top:65px;left:400px;">
		<div id="custom-queue" style="width: 180px; height: 172px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="searchandaddDialog"></div>
	<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
$(document).ready(function(){

	$("#addPartyOrgActivityForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
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
	                	 $.messageBox({message:"党组织活动记录新增成功！"});
	    				 $("#partyOrgActivityList").addRowData(data.id,data,"first");
	    		         //doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
		                 $("#partyOrgActivityList").setSelection(data.id);
	                }
	                if("edit"=="${mode}"){
	                	 $("#partyOrgActivityList").setRowData(data.id,data);
	                	 $.messageBox({message:"党组织活动记录修改成功！"});
	    				 //doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                $("#partyOrgActivityDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"population.partyOrgId":{
				required:true
			},
			"population.activityDate":{
				required:true
			},
			"population.activityAddr":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:280
			},
			"population.activitySubject":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:148
			},
			"population.organizers":{
				minlength:2,
				maxlength:248
			},
			"population.participants":{
				minlength:2,
				maxlength:248
			},
			"population.activeContent":{
				required:true
			}
		},
		messages:{
			"population.partyOrgId":{
				required:"所属党支部不能为空"
			},
			"population.activityDate":{
				required:"请选择活动时间"
			},
			"population.activityAddr":{
				required:"请输入活动地点",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("活动地点至少需要输入{0}个字符"),
				maxlength:$.format("活动地点最多需要输入{0}个字符")
			},
			"population.activitySubject":{
				required:"请输活动主题",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("活动主题至少需要输入{0}个字符"),
				maxlength:$.format("活动主题最多需要输入{0}个字符")
			},
			"population.organizers":{
				minlength:$.format("组织者至少需要输入{0}个字符"),
				maxlength:$.format("组织者最多需要输入{0}个字符")
			},
			"population.participants":{
				minlength:$.format("参与者至少需要输入{0}个字符"),
				maxlength:$.format("参与者最多需要输入{0}个字符")
			},
			"population.activeContent":{
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
	<s:if test="population.activityAttachFile!=null && population.activityAttachFile.size > 0">
        <s:iterator value="population.activityAttachFile">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/baseinfo/partyOrgActivityManage/downloadPartyOrgActivityFile.action?partyOrgActivityFile.id=<s:property value='id'/>",
	          onRemove:function(id){deletePartyOrgActivityAttachFile(id)}
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
        </s:iterator>
	</s:if>
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deletePartyOrgActivityAttachFile(id){
	$.ajax({
		url:"${path}/baseinfo/partyOrgActivityManage/deletePartyOrgActivityAttachFile.action?partyOrgActivityFile.id="+id,
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