<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="短信提示语模版" class="container container_24">
	    <form id="maintainForm" method="post" action="" >
	    <pop:token/>
	<input id="mode" type="hidden" name="mode" value="${mode}" />
			
    <div class="grid_4 lable-right">
		<label >类型：</label>
	</div>
	<div class="grid_6 lable-right">
		<s:select name="smsMessageMark.operationtType" list="operatesTypeList" onchange="operatesTypeChanged()" listKey="code" listValue="name" emptyOption="true" id="operatesCode"/>
	</div>
	<div class="grid_6 lable-right" id="deal" style="display: none;">
		<s:select name="smsMessageMark.dealType" list="dealTypeList" onchange="dealTypeChanged()" listKey="code" listValue="name" emptyOption="true" id="dealCode"/>
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label >摸版：</label>
	</div>
	<div style="display:inline;float:left;height:110px;line-height:110px;width:81.667%;">
		<textarea name="smsMessageMark.model" style="height:95px;"
		 class="form-txt" id="myContact-remark" disabled="disabled">${smsMessageMark.model}</textarea>
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right" style="color:red;">
		<label >*</label>
	</div>
	<div style="display:inline;float:left;height:110px;line-height:110px;width:81.667%;">
		模板格式为：尊敬的#user#您好！您的网格化系统帐号于#time#收到一条待处理事件，为了不影响您的考核，请及时处理！ 
	</div>
				
	   </form>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	$("#operatesCode").attr("disabled","disabled");
	if($("#operatesCode").val()==<s:property value='@com.tianque.domain.property.SmsMessageMarkType@DEAL_CODE'/>){
		$("#deal").show();
		$("#dealCode").attr("disabled","disabled");
	}
		

		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				"smsMessageMark.model":{
					required:true,
					maxlength:200
				}
			},
			messages:{
				"smsMessageMark.model":{
					required:"请输入model模板",
					maxlength:$.format("备注最多只能输入{0}个字符")
				}
			}
			});
		 
	<c:if test='${mode=="add"}'>
		$("#maintainForm").attr("action","${path}/smsMessageMarkManage/addSmsMessageMark.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
		$("#maintainForm").attr("action","${path}/smsMessageMarkManage/updateSmsMessageMark.action");
	</c:if>
});

function operatesTypeChanged(){
	var operateCode = $("#operatesCode").val();
	if(operateCode!=<s:property value='@com.tianque.domain.property.SmsMessageMarkType@DEAL_CODE'/>){
		$("#deal").hide();
	}else{
		$("#deal").show();
	}
}

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
                if(!data.id){
               	 $.messageBox({
						message:data,
						level: "error"							
					 });
                	return;
                }
   	   		 	<c:if test='${mode=="add"}'>
   	   				$("#smsMessageMarkList").addRowData(data.id,data,"first");
			    	$.messageBox({message:"成功保存短信提示语模板信息!"});
			     </c:if>
			     <c:if test='${mode=="edit"}'>
			     	$("#smsMessageMarkList").setRowData(data.id,data);
				    $.messageBox({message:"成功保存短信提示语模板修改信息!"});
			     </c:if>
			     $("#smsMessageMarkMaintanceDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

</script>