<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="dialog-form"  class="container container_24">
	<form id="maintainRegionalBuildInfoForm" method="post" action="${path}/partyBuilding/regionalBuildInfoManage/maintainRegionalBuildInfo.action">
	<pop:token/>
		<input type="hidden" name="mode" id="modePart" value="${mode}" />
		<input type="hidden" name="regionalBuildInfo.id" id="mode" value="${regionalBuildInfo.id}" />
		<input type="hidden" name="regionalBuildInfo.isAttachment" id="isAttachment">	
		<input type="hidden" name="regionalBuildInfo.organization.id" id="regionalBuildInfoOrgId" value="${regionalBuildInfo.organization.id}" />
		<input type="hidden" name="regionalBuildInfo.organization.orgInternalCode" id="regionalBuildInfoOrgCode" value="${regionalBuildInfo.organization.orgInternalCode}" />

	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="orgInternalCode" name="" class="form-txt" maxlength="40" value="${regionalBuildInfo.organization.orgName}" readonly />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>服务项目名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="regionalBuildInfo.serviceItem" id="serviceItem"  maxlength="60" class="form-txt {required:true,messages:{required:'输入服务项目名称'}}" value="${regionalBuildInfo.serviceItem}"/>
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label >推进情况：</label>
		</div>
		<div style="display:inline;float:left;height:110px;line-height:110px;width:40.916%;">
			<textarea name="regionalBuildInfo.advancementInfo" style="height:95px;" class="form-txt {required:true,messages:{required:'输入推进情况'}}">${regionalBuildInfo.advancementInfo}</textarea>
		</div>
		<div style="clear:both"></div>
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
	
	function isAttachFileValue(){
		if($("#attachFileNames").val()){
			$("#isAttachment").val(true);
		}else{
			$("#isAttachment").val(false);
		}
	}
	
	
	$("#maintainRegionalBuildInfoForm").formValidate({
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
	                	 $.messageBox({message:"区域联建情况新增成功！"});
	                	 $("#regionalBuildInfoList").trigger("reloadGrid");
	                }
	                if("edit"=="${mode}"){
	                	 $("#regionalBuildInfoList").trigger("reloadGrid");
	                	 $.messageBox({message:"区域联建情况修改成功！"});
	                }
	                $("#regionalBuildInfoDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			
		},
		messages:{
			
		}
	});


	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	fillFile();

});

function fillFile(){
	<c:if test="${regionalBuildInfo.regionalBuildInfoAttachFiles!=null && fn:length(regionalBuildInfo.regionalBuildInfoAttachFiles) > 0}">
        <s:iterator value="regionalBuildInfo.regionalBuildInfoAttachFiles">
        $("#custom-queue").addUploadFileValue({
	          id:"<s:property value='id'/>",
	          filename:"<s:property value='fileName'/>",
	          link:"${path}/partyBuilding/regionalBuildInfoManage/downloadRegionalBuildInfoAttachFiles.action?regionalBuildInfoAttachFile.id=<s:property value='id'/>",
	          onRemove:function(id){deleteRegionalBuildInfoAttachFiles(id)}
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

function deleteRegionalBuildInfoAttachFiles(id){
	$.ajax({
		url:"${path}/partyBuilding/regionalBuildInfoManage/deleteRegionalBuildInfoAttachFile.action?regionalBuildInfoAttachFile.id="+id,
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