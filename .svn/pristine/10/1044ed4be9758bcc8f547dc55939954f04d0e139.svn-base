<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="其他联系人维护" class="container container_24">
	<c:if test='${mode!="view"}'>
	    <form id="maintainForm" method="post" action="" >
	    <pop:token/>
	</c:if>
	<input id="mode" type="hidden" name="mode" value="${mode}" />
	<input type="hidden" name="myContacter.id" value="${myContacter.encryptId}" />
	<input type="hidden" name="myContacter.belongClass" value="${myContacter.belongClass}" />
			
    <div class="grid_4 lable-right">
		<label >姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="myContacter.name" class="form-txt" maxlength="20" 
		<c:if test='${mode=="view"}'>disabled='true'</c:if> id="myContactName" value="${myContacter.name}"
		title="联系人的姓名，请您输入 2-20个字符"/>
	</div>
	<c:if test='${mode!="view"}'>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>
	</c:if>
	<div class="grid_4 lable-right">
		<label>联系手机：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="myContacter.mobileNumber" class="form-txt" maxlength="11"
		<c:if test='${mode=="view"}'>disabled='true'</c:if>
		 id="myContact-mobileNumber" value="${myContacter.mobileNumber}"
		 title="请输入11位以1开头的联系手机  例如：13988888888" />
	</div>
	<c:if test='${mode!="view"}'>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>
	</c:if>
				
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label >备注：</label>
	</div>
	<div style="display:inline;float:left;height:110px;line-height:110px;width:81.667%;">
		<textarea name="myContacter.remark" style="height:95px;"
		 <c:if test='${mode=="view"}'>disabled='true'</c:if>
		 class="form-txt" id="myContact-remark">${myContacter.remark}</textarea>
	</div>
				
   <c:if test='${mode!="view"}'>
	   </form>
	</c:if>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	<c:if test='${mode!="view"}'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				"myContacter.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
				},
				"myContacter.mobileNumber":{
					required:true,
					mobile:true
				},
				"myContacter.remark":{
					maxlength:200
				}
			},
			messages:{
				"myContacter.name":{
					required:"请输入姓名",
					exculdeParticalChar:"不能输入特殊字符",
					minlength:$.format("姓名最少需要输入{0}个字符"),
					maxlength:$.format("姓名最多只能输入{0}个字符")
				},
				"myContacter.mobileNumber":{
					required:"请输入联系手机",
					mobile:"手机号码输入只能是以1开头的11位数字"
				},
				"myContacter.remark":{
					maxlength:$.format("备注最多只能输入{0}个字符")
				}
			}
			});
	</c:if>
		 
	<c:if test='${mode=="add"}'>
		$("#maintainForm").attr("action","${path}/contact/myContacterManage/addMyContacter.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
		$("#maintainForm").attr("action","${path}/contact/myContacterManage/updateMyContacter.action");
	</c:if>
});

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
   	   				$("#myContactList").addRowData(data.id,data,"first");
			    	$.messageBox({message:"成功保存新其他联系人信息!"});
			    	clearData();
			     </c:if>
			     <c:if test='${mode=="edit"}'>
			     	$("#myContactList").setRowData(data.id,data);
				    $.messageBox({message:"成功保存其他联系人修改信息!"});
			     </c:if>
			     $("#myContactMaintanceDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function clearData() {
	$("#myContactName").val("");
	$("#myContact-mobileNumber").val("");
	$("#myContact-remark").val("");
}

function doNothing(){}

</script>