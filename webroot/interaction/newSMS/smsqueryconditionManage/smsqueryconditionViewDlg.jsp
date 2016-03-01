<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="查询条件管理表" class="container container_24">
	
	<form id="smsQueryForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="queryId" name="smsquerycondition.id" value="${smsquerycondition.id}" />
		<input type="hidden" id="query_objectId" name="smsquerycondition.smsSendObject.id" value="${objectId}" />
 		<div class="grid_5 lable-right" >
 			<em class="form-req">*</em>
			<label>中间key：</label>
		</div>
		<div class="grid_7">
		     <input type="text" name="smsquerycondition.key" value="${smsquerycondition.key}" class="form-txt" <s:if test='"view".equals(mode)'>readOnly</s:if> />
		</div>
		<div class="grid_5 lable-right" >
 			<em class="form-req">*</em>
			<label>类型：</label>
		</div>
		<div class="grid_7">
			<select name="smsquerycondition.type" class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
				<option <s:if test='"table".equals(smsquerycondition.type)'>selected</s:if> value="table">table</option>
				<option <s:if test='"varchar2".equals(smsquerycondition.type)'>selected</s:if> value="varchar2">varchar2</option>
				<option <s:if test='"number".equals(smsquerycondition.type)'>selected</s:if> value="number">number</option>
				<option <s:if test='"date".equals(smsquerycondition.type)'>selected</s:if> value="date">date</option>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right" >
			<em class="form-req">*</em>
			<label>描述：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="smsquerycondition.description" value="${smsquerycondition.description}" class="form-txt" <s:if test='"view".equals(mode)'>readOnly</s:if> />
		</div>
		<div class="grid_5 lable-right" >
			<label>是否必填：</label>
		</div>
		<div class="grid_2 lable-right">
			<input type="radio" id="radio1" name="smsquerycondition.isNull" <s:if test="smsquerycondition.isNull == true">checked</s:if> value="true">
			<label for="radio1">是</label>
		</div>
		<div class="grid_2 lable-right">
			<input type="radio" id="radio2" name="smsquerycondition.isNull" <s:if test="smsquerycondition.isNull != true">checked</s:if> value="false">
			<label for="radio2">否</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right" >
			<em class="form-req">*</em>
			<label>sql语句：</label>
		</div>
		<div class="grid_19" style="height:160px;">
			<textarea  class="form-txt" name="smsquerycondition.sqlTemplate" style="height:140px;" <s:if test='"view".equals(mode)'>readOnly</s:if> >${smsquerycondition.sqlTemplate}</textarea>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<hr align="left" width="80%">
		<div class="grid_1" >
			<em class="form-req">注：</em>
		</div>
		<div class="grid_23" >
			<em class="form-req">
			首次新增时应先建立 ，如 ：select name as name, mobilenumber as mobile, orgid , orginternalcode from tableXXX where 1=1 and mobilenumber is not null,且中间key是table的查询条件。
			</em>
		</div>
	 	
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	jQuery.validator.addMethod("validateKeyIsRepeat", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var bol = false;
		$.ajax({
			async: false,
			url:"${path}/smsqueryconditionManage/validateKeyIsRepeat.action",
			data:{
				"id":$("#queryId").val(),
				"fieldStr":value,
				"objectId":$("#query_objectId").val()
			},
			success:function(data){
				if(data == true || data == "true"){
					bol = true;
				}
	        }
		});
		return bol  
	});
	
	jQuery.validator.addMethod("validateDescriptionIsRepeat", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var bol = false;
		$.ajax({
			async: false,
			url:"${path}/smsqueryconditionManage/validateDescriptionIsRepeat.action",
			data:{
				"id":$("#queryId").val(),
				"fieldStr":value,
				"objectId":$("#query_objectId").val()
			},
			success:function(data){
				if(data == true || data == "true"){
					bol = true;
				}
	        }
		});
		return bol  
	});
	
	$("#smsQueryForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
					<s:if test='"edit".equals(mode)'>
						$("#smsqueryconditionList").setRowData(data.id,data);
				    	$.messageBox({message:"查询条件修改成功!"});
					</s:if>
					<s:if test='"add".equals(mode)'>
						$("#smsqueryconditionList").addRowData(data.id,data,"first");
						$.messageBox({message:"查询条件新增成功!"});
					</s:if>
// 					$("#reloadSquery").click();
					$("#smsqueryconditionDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"smsquerycondition.description":{
				required:true,
				validateDescriptionIsRepeat:true
			},
			"smsquerycondition.key":{
				required:true,
				validateKeyIsRepeat:true
				
			},
			"smsquerycondition.sqlTemplate":{
				required:true
			}
		},
		messages:{
			"smsquerycondition.description":{
				required:"描述不能为空！",
				validateDescriptionIsRepeat:"描述已经存在！"
			},
			"smsquerycondition.key":{
				required:"中间key不能为空！",
				validateKeyIsRepeat:"中间key已经存在！"
			},
			"smsquerycondition.sqlTemplate":{
				required:"sql语句不能为空！"
			}
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#smsQueryForm").attr("action","${path}/smsqueryconditionManage/addSmsquerycondition.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#smsQueryForm").attr("action","${path}/smsqueryconditionManage/updateSmsquerycondition.action");
</s:if>
});

</script>


