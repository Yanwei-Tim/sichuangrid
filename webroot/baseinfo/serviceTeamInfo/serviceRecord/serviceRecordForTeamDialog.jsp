<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#custom-queue {
	width: 99%;
	height: 60px;
	overflow-y: auto;
	overflow-x: hidden;
}
#holder__serviceMembers{
	width:auto!important;
	height:30px!important;
}
#holder__serviceObjects{
	width:auto!important;
	height:50px!important;
}
</style>
<div class="container container_24">
	<form id="serviceRecord_form" method="post" action="${path}/baseinfo/serviceRecordManage/maintainServiceRecordForTeam.action?mode=${mode}" >
	<pop:token />
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">发生时间：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" id="_occurDate" name="serviceRecord.occurDate"  <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> <s:else>readonly="readonly"</s:else> value='<s:date name="serviceRecord.occurDate" format="yyyy-MM-dd"/>' style="width: 99%"
   			 class="form-txt {required:true,messages:{required:'请选择发生时间'}}" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">发生地点：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" name="serviceRecord.occurPlace" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> value="${serviceRecord.occurPlace}" style="width: 99%" 
   				class="form-txt {required:true,messages:{required:'请选择发生地点'}}" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
        
        <s:if test='"fromMember".equals(fromSource)'>
        <div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务对象：</label>
   		</div>
   		<div class="grid_18 heightAuto" style="margin-top:8px;">
				<input type="text" id="_serviceObjects" name="serviceRecord.serviceObjects" class="form-txt" />
    	</div>
   		<div class="grid_2 lable-right">
			<input type="button" value="选  择" class="defaultButton"  onclick="javascript:openSelectObjects();"/>
   		</div>
   		<input type="hidden" name="serviceRecord.serviceMembers" class="form-txt" value="${serviceRecord.serviceMembers}" />
        </s:if>
      
        <s:elseif test='"fromObject".equals(fromSource)'>
        <div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务人员：</label>
   		</div>
   		<div class="grid_18 heightAuto" style="margin-top:8px;">
				<input type="text" id="_serviceMembers" name="serviceRecord.serviceMembers" class="form-txt" />
    	</div>
   		<div class="grid_2 lable-right">
			<input type="button" value="选  择" class="defaultButton"  onclick="javascript:openSelectMembers();"/>
   		</div>
   		<input type="hidden" name="serviceRecord.serviceObjects" class="form-txt"  value="${serviceRecord.serviceObjects}"/>
        </s:elseif>
        
        <s:else>
   		<s:if test='!"view".equals(mode)'>
   		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务对象：</label>
   		</div>
   		<div class="grid_18 heightAuto" style="margin-top:8px;">
				<input type="text" id="_serviceObjects" name="serviceRecord.serviceObjects" class="form-txt" />
    	</div>
   		<div class="grid_2 lable-right">
			<input type="button" value="选  择" class="defaultButton"  onclick="javascript:openSelectObjects();"/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务人员：</label>
   		</div>
   		<div class="grid_18 heightAuto" style="margin-top:8px;">
				<input type="text" id="_serviceMembers" name="serviceRecord.serviceMembers" class="form-txt" />
    	</div>
   		<div class="grid_2 lable-right">
			<input type="button" value="选  择" class="defaultButton"  onclick="javascript:openSelectMembers();"/>
   		</div>
		</s:if>
        </s:else>
		
		<s:if test='"view".equals(mode)'>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务对象：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" name="serviceRecord.serviceObjects" value="${serviceRecord.serviceObjects}" class="form-txt" disabled="disabled" style="width: 99%" />
   		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">服务人员：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="serviceRecord.serviceMembers" value="${serviceRecord.serviceMembers}" class="form-txt" disabled="disabled" style="width: 99%" />
    	</div>
		</s:if>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label >内容：</label>
		</div>
		<div class="grid_18 heightAuto" style="margin-top:8px;">
			<textarea name="serviceRecord.serviceContent"  style="width:98%" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> class="form-txt">${serviceRecord.serviceContent}</textarea>
		</div>
		<s:if test='"edit".equals(mode)'>
		<input type="hidden" name="serviceRecord.id" value="${serviceRecord.id}"/>
		</s:if>
		<input name="serviceRecord.manageTeam.id" type="hidden" value="${serviceRecord.manageTeam.id}"/>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
   	</form>

   	<div class="filePanel">
	   	<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>

		<div id="attachFilesDiv" class="grid_18 heightAuto">
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
function openSelectObjects() {
	$("#searchConditionDialog").createDialog({
		width: 700,
		height: 400,
		title: '服务对象查询',
		url:'${path}/baseinfo/serviceTeamMember/addServiceObject.jsp',
		buttons: {
	   		"确认":function(event){
			addServiceObjectForMember();
			$(this).dialog("close");
   			},
	   		"关闭":function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function openSelectMembers() {
	$("#searchConditionDialog").createDialog({
		width: 700,
		height: 600,
		title: '服务人员查询',
		url:'${path}/baseinfo/serviceTeamInfo/serviceRecord/searchServiceMembersForTeamDialog.jsp',
		buttons: {
	   		"确认":function(event){
	   			$.each($("#teamMemberList").jqGrid("getGridParam", "selarrrow"),function(index,val){
	   				var row = $("#teamMemberList").getRowData(val);
	   				var vv = row.memberId + "-" + row.memberName + "-" + row.teamId;
	   				$("#_serviceMembers").setPersonnelCompleteVal({value:vv, label:row.memberName, desc:""}); 
	   			});
	   			$(this).dialog("close");
   			},
	   		"关闭":function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$(document).ready(function(){
	
	<s:if test='"fromMember".equals(fromSource)'>
	$("#_serviceObjects").personnelComplete({
		multiple: true,
		height:50
	});
	</s:if>
  
    <s:elseif test='"fromObject".equals(fromSource)'>
    $("#_serviceMembers").personnelComplete({
		multiple: true,
		height:50
	});
    </s:elseif>
	
    <s:else>
	<s:if test='!"view".equals(mode)'>
	$("#_serviceObjects").personnelComplete({
		multiple: true,
		height:50
	});
	$("#_serviceMembers").personnelComplete({
		multiple: true,
		height:50
	});
	</s:if>
	</s:else>
	
	$('#_occurDate').datePicker({
		yearRange: '2001:2030',
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
		var value = "${member.id}-${member.name}-${member.teamId}"
		var label = "${member.name}";
		$("#_serviceMembers").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
		</s:iterator>	
		
		<s:iterator id="serviceObject" value="objects">
		var value = "${serviceObject.populationId}-${serviceObject.name}-${serviceObject.populationType}"
		var label = "${serviceObject.name}";
		$("#_serviceObjects").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
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
			$(form).ajaxSubmit({
				success:function(data){
					if(typeof(data)=='string'){
               	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
                	}else{
                		<s:if test='"add".equals(mode)'>
						$.messageBox({message:"已经成功新增服务记录!"});
						</s:if>
						<s:if test='"edit".equals(mode)'>
						$.messageBox({message:"已经成功修改服务记录!"});
						</s:if>
						<s:if test='"fromMember".equals(fromSource)'>
						$("#TeamMemberMaintanceDialog").dialog("close");
						</s:if>
						<s:elseif test='"fromObject".equals(fromSource)'>
						$("#serviceObjectDialog").dialog("close");
						</s:elseif>
						<s:else>
						$("#serviceRecordDialog").dialog("close");
						$("#reloadServiceRecord").click();
						</s:else>
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