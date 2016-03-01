<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#custom-queue {
	width: 99%;
	height: 65px;
	margin:0 0 5px 0;
	overflow-y: auto;
	overflow-x: hidden;
}
#holder__serviceMembers{
	width:auto!important;
	height:30px!important;
}
</style>

<div class="container container_24">
	<form id="serviceRecord_form" method="post" action="${path}/baseinfo/serviceRecordManage/maintainServiceRecord.action?mode=${mode}" >
	<pop:token />
		<input name="serviceRecord.manageTeam.id" type="hidden" value="${serviceRecord.manageTeam.id}" id="teamId"/>
		<input type="hidden" name="population.id" value="${population.id}"/>
		<input type="hidden" name="population.attentionPopulationType" value="${population.attentionPopulationType}"/>
		<input type="hidden" name="locationId" value="${locationId}"/>
		<input type="hidden" name="locationType" value="${locationType}"/>
		<input type="hidden" name="locationName" value="${locationName}"/>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">发生时间：</label>
   		</div>
   		<div class="grid_19">
   			<input type="text" id="_occurDate" name="serviceRecord.occurDate"  <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> <s:else> readonly="readonly" </s:else> value='<s:date name="serviceRecord.occurDate" format="yyyy-MM-dd"/>' style="width: 99%"
   			 class="form-txt {required:true,messages:{required:'请选择发生时间'}}" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">发生地点：</label>
   		</div>
   		<div class="grid_19">
   			<input type="text" name="serviceRecord.occurPlace" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> value="${serviceRecord.occurPlace}" style="width: 99%"
   				class="form-txt {required:true,messages:{required:'请选择发生地点'}}" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>

   		<s:if test='!"view".equals(mode)'>
   			<s:if test='null==locationType || "".equals(locationType)'>
   				<div class="grid_4 lable-right">
				   	<em class="form-req">*</em>
		   			<label class="form-lb1">服务人员：</label>
		   		</div>
   			</s:if>
			<s:else>
				<div class="grid_4 lable-right">
				   	<em class="form-req">*</em>
		   			<label class="form-lb1">治安负责人：</label>
		   		</div>
		   	</s:else>
	   		<div class="grid_19" style="margin-top:8px;">
				<input type="text" id="_serviceMembers" name="serviceRecord.serviceMembers" class="form-txt" />
    		</div>
		</s:if>

		<s:if test='"view".equals(mode)'>
			<s:if test='null==locationType || "".equals(locationType)'>
   				<div class="grid_4 lable-right">
				   	<em class="form-req">*</em>
		   			<label class="form-lb1">服务人员：</label>
		   		</div>
   			</s:if>
			<s:else>
				<div class="grid_4 lable-right">
				   	<em class="form-req">*</em>
		   			<label class="form-lb1">治安负责人：</label>
		   		</div>
			</s:else>
			<div class="grid_19">
				<input type="text" name="serviceRecord.serviceMembers" value="${serviceRecord.serviceMembers}" class="form-txt" disabled="disabled" style="width: 99%" />
	    	</div>
		</s:if>

		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label >内容：</label>
		</div>
		<div class="grid_19 heightAuto" style="margin:8px 0 0 0;margin:8px 0\9;">
			<textarea name="serviceRecord.serviceContent"  style="width:98%;height:80px;" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> class="form-txt">${serviceRecord.serviceContent}</textarea>
		</div>
		<s:if test='"edit".equals(mode)'>
		<input type="hidden" name="serviceRecord.id" value="${serviceRecord.id}"/>
		</s:if>
			<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
   	</form>

   	<div class="filePanel">
	   	<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>

		<div id="attachFilesDiv" class="grid_19 heightAuto">
			<div id="custom-queue" class="ui-widget-border"></div>
			<s:if test='!"view".equals(mode)'>
				<input id="custom_file_upload" name="uploadFile" type="file" />
			</s:if>
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="searchConditionDialog"></div>
</div>
<script type="text/javascript">

$(document).ready(function(){


	<s:if test='!"view".equals(mode)'>
	$("#_serviceMembers").personnelComplete({
		multiple: true,
		height:100,
		param:"searchServiceTeamMemberVo.cardOrName",
		url:"${path}/baseinfo/serviceTeamMemberManage/findServiceTeamMemberByNameOrIdCardNo.action",
		postDataFunction: function(){
	    return {"searchServiceTeamMemberVo.orgId":USER_ORG_ID,
	    	 "searchServiceTeamMemberVo.teamId":$("#teamId").val()};
			},
		select: function(data){
			<s:if test='!"view".equals(mode)'>
			$("#teamId").val(data.split("-")[2]);
			</s:if>
			},
		itemClose: function(data){
			<s:if test='!"view".equals(mode)'>
			if($("#_serviceMembers").val()==""){
				$("#teamId").val("");
  			}
			</s:if>
			}
		});

	</s:if>

	$('#_occurDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});

	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});

	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}

	<s:if test='"edit".equals(mode)'>
		<s:iterator id="member" value="members">
			var value = "${member.memberId}-${member.name}-${member.teamId}"
			var label = "${member.name}";
		$("#_serviceMembers").setPersonnelCompleteVal({value:value,label:label,desc:""});
		</s:iterator>

		<s:if test="serviceRecord.attachments.size > 0">
		    <s:iterator id="attachment" value="serviceRecord.attachments">
		    $("#custom-queue").addUploadFileValue({
			     id:"${attachment.id}",
			     filename:"${attachment.fileName}",
			     link:"${path }/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId=${attachment.id}",
				 onRemove:function(){removeAttach("${attachment.fileName}")}
		     });
		    $("<option>").attr("id",${attachment.id}).val("${attachment.id},${attachment.fileName}").html('${attachment.fileName}').attr("selected",true).appendTo($("#attachFileNames"));
		    </s:iterator>
		</s:if>
	</s:if>

	<s:if test='"view".equals(mode)'>

	<s:if test="serviceRecord.attachments.size > 0">
	    <s:iterator id="attachment" value="serviceRecord.attachments">
	    $("#custom-queue").addUploadFileValue({
		     id:"${attachment.id}",
		     filename:"${attachment.fileName}",
		     link:"${path }/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId=${attachment.id}",
		     showCloseButton:false
	     });
	    $("<option>").attr("id",${attachment.id}).val("${attachment.id},${attachment.fileName}").html('${attachment.fileName}').attr("selected",true).appendTo($("#attachFileNames"));
	    </s:iterator>
	</s:if>
</s:if>

	$("#serviceRecord_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			if('!"view".equals(mode)'){
				if(null==$("#_serviceMembers").val() || ""==$("#_serviceMembers").val()){
					<s:if test='null==locationType || "".equals(locationType)'>
						$.messageBox({message:"请选择服务人员！", level:"warn"	});
				 		return;
					</s:if>
					<s:else>
						$.messageBox({message:"请选择治安负责人！",level:"warn"	});
				 		return;
					</s:else>
				}
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(typeof(data)=='string'){
               	 		$.messageBox({
							message:data,
							level:"error"
				 		});
                	}else{
                		<s:if test='"add".equals(mode)'>
                		<s:if test='null==locationType || "".equals(locationType)'>
							$.messageBox({message:"已经成功新增服务记录!"});
						</s:if>
						<s:else>
                			$.messageBox({message:"已经成功新增巡场情况!"});
						</s:else>
                		</s:if>
						<s:if test='"edit".equals(mode)'>
						<s:if test='null==locationType || "".equals(locationType)'>
							$.messageBox({message:"已经成功修改服务记录!"});
						</s:if>
						<s:else>
                			$.messageBox({message:"已经成功修改巡场情况!"});
               			</s:else>
						</s:if>
						$("#superviseRecordDialog").dialog("close");
						$("#reloadSuperviseRecord").click();
	                }
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$.messageBox({
						message:"提交数据时出现问题",
						level:"error"
			 		});
	   		  	}
			});
		},
		rules:{},
		messages:{}
	});
})
</script>