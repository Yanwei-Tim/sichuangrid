<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container container_24">
<input id="mode" type="hidden" name="mode" value="${mode}" />		 
<c:if test="${mode=='add'}">
<form id="publicNoticeForm" method="post" action="/sysadmin/publicNoticeManage/maintainPublicNotice.action">
</c:if> 
<c:if test="${mode=='edit'}">
<form id="publicNoticeForm" method="post" action="/sysadmin/publicNoticeManage/maintainPublicNoticeByEncrypt.action">
</c:if>
	<input id="mode" type="hidden" name="mode" value="${mode }" /> 
	<c:if test='${mode=="add"}'>
		<pop:token/>
	</c:if>
    <input name="isSubmit" id="isSubmit" type="hidden" value="">
	<input id="publicNoticeOrgId" type="hidden"	name="publicNotice.organization.id"		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
	<input type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().id"/>"
		name="publicNotice.userId" id="userId"> 
	<input type="hidden" name="publicNotice.id" value="${publicNotice.encryptId }" /> 
    <input type="hidden" name="publicNotice.isAttachment" id="isAttachment" value="">		
	<div class="grid_4 lable-right"><label class="form-lb1">所属网格：</label></div>
	<div class="grid_19">
	<input  type="text" id="commonPopulationOrgName"
			name="publicNotice.organization.orgName" readonly
			value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgName"/>"
			class="form-txt" />
	</div>
	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">标题：</label></div>
	<div class="grid_19">
		<input  type="text" id="publicNoticeTitle" maxlength="100"
			name="publicNotice.publicNoticeTitle" style="width: 97%"
			value="${publicNotice.publicNoticeTitle}" title="请输入标题"
			class='form-txt {required:true,maxlength:100,messages:{required:"请输入标题" , maxlength:$.format("标题最多输入{0}个字符")}}' />
	</div>
	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">接收对象：</label></div>
	<div class="grid_19">
		<input type="hidden" id="publicNoticeRole" name="publicNotice.publicNoticeObject.publicNoticeRole" title="${publicNotice.publicNoticeObject.publicNoticeRoleContent}" value="${publicNotice.publicNoticeObject.publicNoticeRole}"/>
		<input type="hidden" id="publicNoticeOrgZN" name="publicNotice.publicNoticeObject.publicNoticeOrgZN" title="${publicNotice.publicNoticeObject.publicNoticeOrgZNContent}" value="${publicNotice.publicNoticeObject.publicNoticeOrgZN}"/>
		<input type="hidden" id="publicNoticeOrgXZ" name="publicNotice.publicNoticeObject.publicNoticeOrgXZ" title="${publicNotice.publicNoticeObject.publicNoticeOrgXZContent}" value="${publicNotice.publicNoticeObject.publicNoticeOrgXZ}"/>
		<input type="text" id="publicNoticeObject" value="${publicNotice.publicNoticeObject.publicNoticeObject}" readonly="readonly"
			title="请选择接收对象" class='form-txt {required:true,messages:{required:"请选择接收对象"}}' style="width: 87%;" />
		<div class="btnbanner btnbannerData"><a id="publicNoticeObjectButton" href="javascript:void(0)"><span>选择</span></a></div>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl"><em class="form-req">*&nbsp;</em>落款： </label>
	</div>
	<div class="grid_19">
		<input  type="text" maxlength="100" name="publicNotice.publicNoticeInscribe" style="width: 97%" value="${publicNotice.publicNoticeInscribe}" title="请输入落款"
			class='form-txt {required:true,maxlength:100,messages:{required:"请输入落款" , maxlength:$.format("落款最多输入{0}个字符")}}' />
		<input type="hidden"
			value="<s:property value='@com.tianque.core.util.ThreadVariable@getUser().name' />"
			readonly="readonly" id="noticeEditor"
			name="publicNotice.noticeEditor" class="form-txt"  />
		<input type="hidden" name="publicNotice.editorDate" id="editorDate"
			style="width: 166px;" readonly="readonly"  class="form-txt" />
	</div>

	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">内容：</label></div>
	<div class="grid_19 " style="height:45%;">
		<textarea id="publicNoticeMatter" rows="8" style="width: 100%;"
			name="publicNotice.publicNoticeMatter" 
			title="请输入内容"
			class='form-txt'>${publicNotice.publicNoticeMatter}</textarea>
   </div>
   <div class="grid_1"></div>
	<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	<div id="showOverdue" style="display: none;">
		<div class="grid_4 lable-right"><label class="form-lb1">截止日期：</label></div>
		<div class="grid_8"><input type="text" readonly="readonly" id="overdueinput" class="form-txt"
				value="${overdueInput }" /></div>
		
		<div class="grid_12">
			<input type="button" class="defaultButton" id="resetOverdueDate" value="重设" />
		</div>
	</div>
		
	<div id="setOverdueDate">
		<div class="grid_4 lable-right"  >
			<label class="form-lb1">有效期：</label> 
		</div>
		<div class="grid_18" >
			<input type="radio"	value="不限" id="validityDate" name="validityDate" checked="checked"  />不限&nbsp;&nbsp;
			<input type="radio" value="3" id="validityDate" name="validityDate"  style="padding-right: 8px;"/>3天&nbsp;
			<input type="radio" value="7" id="validityDate" name="validityDate" />7天&nbsp;
			<input type="radio" value="15" id="validityDate" name="validityDate" />15天&nbsp;
			<input type="radio" value="30" id="validityDate" name="validityDate" />30天&nbsp;
			<input type="radio" id="buttonRadio" name="validityDate" value="-1" >其他
			<input type="text" id="overdueDate" name="publicNotice.overdueDate" title="点击设定时间"
				value="<fmt:formatDate value="${publicNotice.overdueDate}" type="date" pattern="yyyy-MM-dd" />"
				readonly="readonly" class="form-txt" style="width:80px;margin:0;" />
		</div>
	</div>	
</form>
<div class="clear"></div>
<div class="grid_4 lable-right"></div>
<div class="grid_20">
	<div id="custom-queue" style="width: 475px;height:80px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
	<input id="custom_file_upload" name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
</div>
</div>
<div id="publicNoticeObjectDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
	$("#publicNoticeObjectButton").click(function (){
		$("#publicNoticeObjectDialog").createDialog({
			width:760,
			height:510,
			title:'选择接收对象',
			url:'${path}/sysadmin/publicNoticeManage/selectPublicNoticeObject.jsp',
			buttons: {
				"确定" : function(){
					var _val = '', _content = '';
					$("input[name='notice_role_hidden']").each(function (){
						_val += $(this).val() + ',';
						_content += $(this).attr('title') + ';';
					});
					$("#publicNoticeRole").val(_val);
					$("#publicNoticeRole").attr("title", _content);
					_val = '';
					_content = '';
					$("input[name='notice_ZN_hidden']").each(function (){
						_val += $(this).val() + ',';
						_content += $(this).attr('title') + ';';
					});
					$("#publicNoticeOrgZN").val(_val);
					$("#publicNoticeOrgZN").attr("title", _content);
					_val = '';
					_content = '';
					$("input[name='notice_XZ_hidden']").each(function (){
						_val += $(this).val() + ',';
						_content += $(this).attr('title') + ';';
					});
					$("#publicNoticeOrgXZ").val(_val);
					$("#publicNoticeOrgXZ").attr("title", _content);
					_val = '';
					$("div[name='publicNotice_div_content_sendObject']").each(function (){
						_val += $(this).text() + ';';
					});
					$("#publicNoticeObject").val(_val);
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	function isAttachFileValue(){
	if($("#attachFileNames").val()){
		$("#isAttachment").val("true");
	}else{
		$("#isAttachment").val("false");
	}
	}
	
	if ($("#mode").val() == 'edit') {
	$("#setOverdueDate").hide();
	}

    $("#resetOverdueDate").click(function() {
	$("#setOverdueDate").show();
	});
	function getNewOverdueDate(selectValue) {
		$.ajax({
			 url : '${path}/sysadmin/publicNoticeManage/updateNewOverdue.action',
			 type : 'post',
			 dataType : 'json',
			 data : {
					  "validityDate" : selectValue,
					  "publicNotice.editorDate" : $("#editorDate").val()
					 },
			 success : function(data) {
					   $("#overdueDate").val(data);
						}
			});
      }

 	$(":radio").each(function() {
		$("#overdueDate").hide();
		$(this).click(function() {
			if ($(this).attr("id") == 'buttonRadio'	&& $(this).attr("checked")) {
				$("#overdueDate").val("");
				$("#overdueDate").show();
			 }else {
					$("#overdueDate").hide();
					getNewOverdueDate($(this).val());
					}
			});
     });

	if ($("#mode").val() == 'edit') {
		$("#showOverdue").show();
	}

	$("#editorDate").val(new Date());

	$('#overdueDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		minDate : '+0d'
	});

	$("#publicNoticeTitle").focus();
	$('#publicNoticeMatter').richImg();
	$("#publicNoticeForm").formValidate({
		showErrors : showErrorsForTab,
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			isAttachFileValue();
			$(form).ajaxSubmit({
				success : function(data) {
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
					return;
					}
					if ("add" == $("#mode").val()) {
						$("#publicNoticeList").addRowData(data.id,data,"first");
						$("#publicNoticeList").setSelection(data.id);
						 
						if ($("#isSubmit").val() == "true"){
							$("#publicNoticeDialog").dialog("close");
						} else {
								emptyObject();
								}
						$.messageBox({message : "通知通报添加成功"
						});

						$("#peopleLogList").trigger("reloadGrid");
					}
					if ("edit" == $("#mode").val()) {
						$("#publicNoticeList").setRowData(data.id,data);
						$.messageBox({message : "通知通报修改成功！"
						});
					$("#publicNoticeList").setSelection(data.id);
					$("#publicNoticeDialog").dialog("close");
					}
					$("#refresh").click();

				},
				error : function(
						XMLHttpRequest,
						textStatus,
						errorThrown){
						alert("提交错误");
						}
			});
		},
		rules : {

				},
		messages : {}
	});
	
	function emptyObject() {
		$("#publicNoticeForm")[0].reset();
		$("#attachFileNames").html("");
		$("#custom-queue").html("");
		$("#editorDate").val(new Date());
		$("#overdueDate").hide();
		$("#publicNoticeOrgXZ").val('${publicNotice.publicNoticeObject.publicNoticeOrgXZ}');
		$("#publicNoticeOrgXZ").attr('title','${publicNotice.publicNoticeObject.publicNoticeOrgXZContent}');
	}
						
//附件
	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});

	fillFile();
						
});

function fillFile(){
<c:if test="${publicNotice.noticeFiles!=null && fn:length(publicNotice.noticeFiles)>0}">
	<c:forEach items="${publicNotice.noticeFiles}" var="noticeFile">
        $("#custom-queue").addUploadFileValue({
	          id:"${noticeFile.id}",
	          filename:"${noticeFile.fileName}",
	          link:"${path}/sysadmin/publicNoticeManage/downloadPublicNoticeAttachFile.action?publicNoticeAttachFile.id=${noticeFile.id}",
	          onRemove:function(id){deletePublicNoticeAttachFile(id)}
		});
        $("<option>").attr("id","${noticeFile.id}").val("${noticeFile.fileName}").attr("selected",true).appendTo($("#attachFileNames"));
        </c:forEach>
	</c:if>
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}

function deletePublicNoticeAttachFile(id){
	$.ajax({
		url:"${path}/sysadmin/publicNoticeManage/deletePublicNoticeAttachFile.action?publicNoticeAttachFile.id="+id,
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