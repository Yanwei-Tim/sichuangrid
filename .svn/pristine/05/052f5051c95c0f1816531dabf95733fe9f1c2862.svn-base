<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%request.setAttribute("dailogName",request.getParameter("dailogName"));%>
<div id="commonPopulation" class="container container_24">
	<form id="mainSpeechForm" method="post" action="/baseinfo/speechManage/saveSpeech.action" >
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		
			<div class="grid_4 lable-right">
				<label class="form-lbl">创建者：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="speech.createUserRealName" id="createUser" value="${speech.createUserRealName}"
	  				readonly="readonly"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">创建时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" value='<s:date name="speech.createDate"/>'/>
		    	
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">标题：</label>
			</div>
			<div class="grid_15">
		    	<input type="text" name="speech.title" id="title" value="${speech.title}"
	  				<s:if test='"view".equals(mode)'>disabled="disabled"</s:if> class="form-txt" style="width: 99%"/>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">内容：</label>
			</div>
			<div class="grid_18">
				<textarea name="speech.content" id="speechContent" rows="18" cols="78" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>${speech.content}</textarea>
			</div>
			<input type="hidden" name="speech.orgId" value="${orgId}"/>
			<input type="hidden" name="speech.speechType" value="${speechType}"/>
			<input type="hidden" name="speech.id" value="${speech.id}"/>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#speechContent').noBarRichText();
	$('#speechContent').focus();
	
	$("#mainSpeechForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
						return;
					}
					document.location.reload();
					
					$.messageBox({message:"操作成功!"});
					$("#${dailogName}").dialog("close");
					
				}
			});
		},
		rules:{
			"speech.title":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:100
			},
			"speech.content":{
				required:true,
			}
			
		},
		messages:{
			"speech.title":{
				required:"请输入标题",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("标题至少需要输入{0}个字符"),
				maxlength:$.format("标题最多需要输入{0}个字符")
			},
		"speech.content":{
			required:"请输入内容"
		}
		}
	});

});
</script>
