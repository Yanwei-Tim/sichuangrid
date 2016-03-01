<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div class="container container_24">
<form action="${path}/baseinfo/permanentAddressManage/updatePermanentAddress.action" method="post" id="updateform">
	<div class="grid_5 lable-right">
		<label class="form-lbl">地区编号：</label>
	</div>
	<input type="hidden" id="addrLevel" name="permanentAddress.addressLevel" value="${permanentAddress.addressLevel }" />
	<div class="grid_7">
	<input type="text" id="addressNo" name="permanentAddress.addressNo" class="form-txt" maxlength="20" readonly="readonly" value="${permanentAddress.addressNo}"/>
	</div>
	<div class="grid_1"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">地区名称：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="addressName" name="permanentAddress.addressName" class="form-txt" maxlength="15"  value="${permanentAddress.addressName}"/></div>
	<div class="grid_5 lable-right">
	<label class="form-lbl">上级编号：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="parentid" name="permanentAddress.parentid" class="form-txt" maxlength="15" readonly="readonly" value="${permanentAddress.parentid}" /></div>	
	<div id="thingsType-select">
	<div class="grid_1"></div>
	<div class="grid_4 lable-right"><label class="form-lbl">所属层级：</label>
	</div>
	<div class="grid_7">
		<select class="form-select" style="width:180px" id="levelSelect" name="levelSelect" disabled="disabled">
			<option value="1">省级</option>
			<option value="2">市级</option>
			<option value="3">区县级</option>
		</select>
	</div>
</div>
</form>
</div>

<Script type="text/javascript">
$(document).ready(function () {
	$("#levelSelect option[value='"+$("#addrLevel").val()+"']").attr("selected","selected");
	 
	jQuery.validator.addMethod("checkIsChinese", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var str=$("#addressName").val();
		 var reg = /^[\u4e00-\u9fa5]+$/gi; 
		 if(!reg.test(str)){
		 	 return false; 
		 }	else {
			  return true;
		}
	});
	
	$("#updateform").formValidate({
		submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(!data.addressNo){
	            	 			$.messageBox({
	            	 				level: "warn",
		 							message:data
	 			 				});
	             			return;
	 					}
		                $("#permanentAddressList").trigger("reloadGrid");
		                $.messageBox({message:"修改成功！"});
		               	$("#permanentAddresslog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   	}
	      	});
		},
		rules:{
			"permanentAddress.addressName":{
				required:true,
				checkIsChinese:true,
				maxlength:20
			}
		},
		messages:{
			"permanentAddress.addressName":{
				required:"请输入地区名称",
				checkIsChinese:"地区名称必须为中文",
				maxlength:$.format("事件在名称在{0}字以内")
			}
		}
	});
	
	
});
</Script>
