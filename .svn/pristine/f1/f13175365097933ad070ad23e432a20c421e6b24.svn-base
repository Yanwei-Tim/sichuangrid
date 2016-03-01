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
	<form id="serviceRecord_form" method="post" action="${path}/baseinfo/serviceRecordManage/maintainServiceRecordForTeam.action?mode=${mode}">
	<pop:token />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">服务时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="_occurDate" name="serviceRecord.occurDate"  <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> <s:else>readonly="readonly"</s:else> value='<s:date name="serviceRecord.occurDate" format="yyyy-MM-dd"/>' style="width: 99%"
			 class="form-txt {required:true,messages:{required:'请选择发生时间'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">发生地点：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="serviceRecord.occurPlace" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> value="${serviceRecord.occurPlace}" style="width: 99%" 
				class="form-txt {required:true,messages:{required:'请填写发生地点'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">服务人员：</label>
		</div>
		<s:if test="'fromMember'.equals(#parameters.fromSource[0])">
		<div class="grid_18 heightAuto" style="margin-top:8px;">
		<table id="memberList"></table>
		<input type="hidden" id="_serviceMembers" name="serviceRecord.serviceMembers" class="form-txt " />
		</div>
		</s:if>
		<s:else>
		<div class="grid_18 heightAuto" style="margin-top:8px;">
			<input type="text" id="_serviceMembers" name="serviceRecord.serviceMembers" class="form-txt " />
		</div>
		</s:else>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">服务对象：</label>
		</div>
		<div class="grid_17 heightAuto" style="margin-top:8px;">
			<input type="text" id="_serviceObjects" name="serviceRecord.serviceObjects" class="form-txt " readonly="readonly" />
		</div>
		<div class="grid_2 lable-right">
			<input type="button" value="选  择" class="defaultBtn" id="selectServiceObjects" style="margin-top:8px;" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>服务内容：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-top:5px;margin-bottom:8px;">
			<textarea name="serviceRecord.serviceContent"  style="width:98%;" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> class="form-txt">${serviceRecord.serviceContent}</textarea>
		</div>
		<s:if test='"edit".equals(mode)'>
		<input type="hidden" name="serviceRecord.id" value="${serviceRecord.id}"/>
		</s:if>
		<input name="serviceRecord.manageTeam.id" type="hidden" value="${serviceRecord.manageTeam.id}" id="teamId"/>
		<input type="hidden"  id="objectType"/>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div class='clearLine'>&nbsp;</div>
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_20 heightAuto">
			<div id="custom-queue" class="ui-widget-border"></div>
			<s:if test='!"view".equals(mode)'>
			<input id="custom_file_upload" name="uploadFile" type="file" />
			</s:if>
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="searchConditionDialog"></div>
	<s:iterator status=""></s:iterator>
</div>
<script type="text/javascript">
function openSelectObjects(){
	$("#searchConditionDialog").createDialog({
		width: 700,
		height: 470,
		title: '服务对象查询',
		url:'${path}/baseinfo/serviceTeamManage/serviceRecord/addServiceObject.jsp',
		buttons: {
			"确认":function(event){
				$("#_serviceObjects").clearPersonnelComplete();
				$.each(addServiceObjectForMember(),function(ind, val){
					$("#_serviceObjects").setPersonnelCompleteVal({value:val.id+"-"+val.name+"-"+val.type,label:val.name,desc:""}); 
					$("#objectType").val(val.type);
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
	$('#_occurDate').datePicker({
		yearRange: '2001:2030',
		dateFormat: 'yy-mm-dd',
		maxDate:'+0d'
	});
	$('#selectServiceObjects').click(function(event){
		openSelectObjects();
	});
	$("#_serviceObjects").personnelComplete({
		multiple: true,
		height:80,
		itemClose: function(data){
			if($("#_serviceObjects").val()==""){
				$("#objectType").val("");
			}
		}
	});
	<s:if test="'fromMember'.equals(#parameters.fromSource[0])">
	$("#memberList").jqGrid({
		datatype: "local",
		colNames:['baseId','memberId','teamId','人员姓名','所属团队'],
		colModel:[
			{name:'baseId',hidden:true},
			{name:'memberId',hidden:true},
			{name:'teamId',hidden:true},
			{name:'memberName',sortable:false, width:200},
			{name:'teamName',sortable:false, width:200}
		]
	});
	<s:iterator var="member" value="members">
	var team = '<select  id="team${member[0].baseId}">';
	team = team.concat("<option value='-1' selected='selected'>请选择</option>");
	<s:iterator var="temp" value="#member"  >
	team = team.concat("<option value=${temp.teamId}-${temp.memberId} >${temp.teamName}</option>");
	</s:iterator>
	team = team.concat("</select>");
	$("#memberList").addRowData("${member[0].baseId}",{baseId:"${member[0].baseId}",memberId:"${member[0].memberId}",teamId:-1,memberName:"${member[0].memberName}",teamName:team});
	</s:iterator>
	</s:if>
	<s:else>
	$("#_serviceMembers").personnelComplete({
		multiple: true,
		height:80,
		param:"searchServiceTeamMemberVo.cardOrName",
		url:"${path}/baseinfo/serviceTeamMemberManage/findServiceTeamMemberByNameOrIdCardNo.action",
		postDataFunction: function(){
			return {"searchServiceTeamMemberVo.orgId":$("#currentOrgId").val(),"searchServiceTeamMemberVo.teamId":$("#teamId").val()};
		},
		select: function(data){
			<s:if test='"add".equals(mode)'>
			$("#teamId").val(data.split("-")[2]);
			</s:if>
		},
		itemClose: function(data){
			<s:if test='"add".equals(mode)'>
			if($("#_serviceMembers").val()==""){
				$("#teamId").val("");
			}
			</s:if>
		}
	});
	</s:else>
	$("#input__serviceObjects").remove();
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
		
		<s:iterator id="serviceObject" value="objects">
		var value = "${serviceObject.objectId}-${serviceObject.objectName}-${serviceObject.objectType}"
		var label = "${serviceObject.objectName}";
		$("#_serviceObjects").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
		$("#objectType").val("${serviceObject.objectType}");
		</s:iterator>	
	
		<s:if test="serviceRecord.attachments.size > 0">
		    <s:iterator id="attachment" value="serviceRecord.attachments">
		    $("#custom-queue").addUploadFileValue({
			     id:"${attachment.id}",
			     filename:"${attachment.fileName}",
			     link:"${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId=${attachment.id}",   	     	 
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
				link:"${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId=${attachment.id}", 	     	 
				showCloseButton:false
			});
			$("<option>").attr("id",${attachment.id}).val("${attachment.id},${attachment.fileName}").html('${attachment.fileName}').attr("selected",true).appendTo($("#attachFileNames"));		    
			</s:iterator>
		</s:if>
	</s:if>
	
	$("#serviceRecord_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			<s:if test="'fromMember'.equals(#parameters.fromSource[0])">
			var data=$("#memberList").getRowData();
			var serviceMembers="";
			for(var i=0;i<data.length;i++){
				if(null != $("#team"+data[i].baseId).find(":selected").val() && $("#team"+data[i].baseId).find(":selected").val() != -1) {
					var temp=$("#team"+data[i].baseId).find(":selected").val();
					serviceMembers=serviceMembers+","+temp.split("-")[1]+"-"+data[i].memberName+"-"+temp.split("-")[0];
				}else{
					$.messageBox({
						message:"请选择服务成员所在的组织！",
						level:"error"
					});
					return;
				}
			}
			$("#_serviceMembers").val(serviceMembers.substr(1));
			</s:if>
			<s:else>
			if($("#_serviceMembers").val()==""){
				 $.messageBox({
					message:"请选择服务成员！",
					level:"error"
				});
				return;
			}
			</s:else>
			if($("#_serviceObjects").val()==""){
				 $.messageBox({
					message:"请选择服务对象 ！",
					level:"error"
				});
				return 
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
						$.messageBox({message:"已经成功新增服务记录!"});
						</s:if>
						<s:if test='"edit".equals(mode)'>
						$.messageBox({message:"已经成功修改服务记录!"});
						</s:if>
						<s:if test="'fromMember'.equals(#parameters.fromSource[0])">
						$("#_serviceTeamMemberDialog").dialog("close");
						</s:if>
						<s:else>
						$("#serviceRecordDialog").dialog("close");
						$("#serviceRecordList").trigger("reloadGrid");
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
});
</script>