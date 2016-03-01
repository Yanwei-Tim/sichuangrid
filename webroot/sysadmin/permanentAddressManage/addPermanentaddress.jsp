<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div class="container container_24">
<form action="${path}/baseinfo/permanentAddressManage/addPermanentAddress.action" method="post" id="addform">
	<input type="hidden" id="newParentid" name="permanentAddress.parentid" />
	<input type="hidden" id="newAddressLevel" name="permanentAddress.addressLevel" />
	<div id="thingsType-select">
	<div class="grid_1"></div>
	<div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">所属层级：</label>
	</div>
	<div class="grid_7">
		<select id="addressL" name="addressL" class="form-select" style="width:180px">
			<option value="1" selected="selected">省级</option>
			<option value="2">市级</option>
			<option value="3">区县级</option>
		</select>
	</div>
	</div>
	<div class="clear"></div>
	<div id="provinceDiv" class="hidden">
	<div class="grid_5 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">省级：</label>
	</div>
	<div class="grid_5">
		<select  id="province" name="province" class='form-txt {required:true,messages:{required:"请选择户籍地省"}}'  style="width:180px"></select>
	</div>
	</div>
	<div class="clear"></div>
	<div id="cityDiv" class="hidden">
	<div class="grid_5 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">市级：</label>
	</div>
	<div class="grid_5">	
		<select  id="city" name="city" class='form-txt {required:true,messages:{required:"请选择户籍地市"}}' style="width:180px"></select>
	</div>
	</div>
	<div class="clear"></div>	
	<div class="grid_5 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">地区名称：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="addressName" name="permanentAddress.addressName" class="form-txt" maxlength="15"  />
	</div>
	<div class="grid_1"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">地区编号：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="addressNo" name="permanentAddress.addressNo" class="form-txt" maxlength="6"  />
	</div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function (){
	
	$("#addressL").change(function(event){
		var selectLevelId=$("#addressL").val();
		$("#newAddressLevel").val(selectLevelId);
		if(selectLevelId==''||selectLevelId==0){
			$("#provinceDiv").hide();
			$("#cityDiv").hide();
		}else if(selectLevelId==1){
			$("#provinceDiv").hide();
			$("#cityDiv").hide();
		}else if(selectLevelId==2){
			$("#provinceDiv").show();
			$("#cityDiv").hide();
		}else if(selectLevelId==3){
			$("#provinceDiv").show();
			$("#cityDiv").show();
		}
		$(".tip-error").remove();
	});
	
	threeSelect({province:'province',
		city:'city',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val()
	});

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

	jQuery.validator.addMethod("isNumber", validatorNo);
	function validatorNo(value,element){
		if(value==null||value==undefined||value=="" ){return true};
		var str=$("#addressNo").val();
		 var reg =/^[0-9]{6}$/; 
		 if(!reg.test(str)){
		 	 return false; 
		 }	else {
			  return true;
		}
	}

	
	$("#addform").formValidate({
		submitHandler: function(form) {
			var selectLevelId=$("#addressL").val();
			if(selectLevelId==""||selectLevelId==1){
				$("#newAddressLevel").val(selectLevelId);
				$("#newParentid").val("1");
			}
			if(selectLevelId==2){
				$("#newParentid").val($("#province").val());
			}else if(selectLevelId==3){
				$("#newParentid").val($("#province").val()+","+$("#city").val());
			}
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
		                $.messageBox({message:"新增成功！"});
		               	$("#permanentAddresslog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   	}
	      	});
		},
		ignore:":hidden",
		rules:{
			"permanentAddress.addressName":{
				required:true,
				maxlength:20,
				checkIsChinese:true
			},
			"permanentAddress.addressNo":{
				required:true,
				isNumber:true,
				maxlength:6,
				minlength:6
			}
		},
		messages:{
			"permanentAddress.addressName":{
				required:"请输入地区名称",
				maxlength:$.format("地区名称在{0}字以内"),
				checkIsChinese:"地区名称必须为中文"
			},
			"permanentAddress.addressNo":{
				required:"请输入地区编号",
				isNumber:"地区编号只能输入6位数字",
				maxlength:$.format("地区编号长度为6"),
				minlength:$.format("地区编号长度为6")
			}
		}
	});
});
</script>
