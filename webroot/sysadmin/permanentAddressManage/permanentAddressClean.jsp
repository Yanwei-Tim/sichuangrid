<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div class="container container_24">
<form action="${path}/baseinfo/permanentAddressManage/permanentAddressClean.action" method="post" id="cleanForm">
	<input type="hidden" id="newAddressLevel" name="permanentAddress.addressLevel" />
	<div id="thingsType-select">
	<div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">所属层级：</label>
	</div>
	<div class="grid_7">
		<select id="addressL" name="addressL" class="form-select" style="width:180px" defaulValue="">
			<option value="1" selected="selected">省级</option>
			<option value="2">市级</option>
			<option value="3">区县级</option>
		</select>
	</div>
	</div>
	<div id="provinceDiv" class="show">
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">原省级：</label>
	</div>
	<div class="grid_5">
		<input  id="province" name="permanentAddress.proviceName" class='form-txt {required:true,messages:{required:"请选择户籍地省"}}' maxlength="20"  style="width:180px"></input>
	</div>
	<div class="clear"></div>
	</div>
	
	<div id="cityDiv" class="hidden">
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">原市级：</label>
	</div>
	<div class="grid_5">	
		<input  id="city" name="permanentAddress.cityName" class='form-txt {required:true,messages:{required:"请选择户籍地市"}}' maxlength="20" style="width:180px"></input>
	</div>
	</div>
	<div id="dostrict" class="hidden">
	<div class="grid_6 lable-right" id="oldName">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">原区县名称：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="addressName" name="permanentAddress.district" class="form-txt" maxlength="20"  />
	</div>
	</div>
	<div class="grid_4 lable-right" id="addressNumber">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">地区编号：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="addressNo" name="permanentAddress.addressNo" class="form-txt" maxlength="20"  />
	</div>
	
	<div class="clear"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em><label class="form-lbl">新地区名称：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="newAddressName" name="permanentAddress.newAddressName" class="form-txt" maxlength="20"  />
	</div>
	
</form>
</div>

<script type="text/javascript">
$(document).ready(function (){
	
	$("#addressL").change(function(event){
		var selectLevelId=$("#addressL").val();
		$("#newAddressLevel").val(selectLevelId);
		if(selectLevelId==''||selectLevelId==0){
			$("#provinceDiv").show();
			$("#cityDiv").hide();
			$("#dostrict").hide();
			$("#oldName").removeClass("grid_6").addClass("grid_4");
			$("#addressNumber").removeClass("grid_6").addClass("grid_4");
		}else if(selectLevelId==1){
			$("#provinceDiv").show();
			$("#cityDiv").hide();
			$("#dostrict").hide();
			$("#addressNumber").removeClass("grid_6").addClass("grid_4");
		}else if(selectLevelId==2){
			$("#provinceDiv").show();
			$("#oldName").removeClass("grid_6").addClass("grid_4");
			$("#addressNumber").removeClass("grid_4").addClass("grid_6");
			$("#cityDiv").show();
			$("#dostrict").hide();
		}else if(selectLevelId==3){
			$("#provinceDiv").show();
			$("#cityDiv").show();
			$("#oldName").removeClass("grid_4").addClass("grid_6");
			$("#addressNumber").removeClass("grid_6").addClass("grid_4");
			$("#dostrict").show();
		}
		$(".tip-error").remove();
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
	
	jQuery.validator.addMethod("checkNewNameIsChinese", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var str=$("#newAddressName").val();
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

	
	$("#cleanForm").formValidate({
		submitHandler: function(form) {
			var selectLevelId=$("#addressL").val();
			if(selectLevelId==""||selectLevelId==1){
				$("#newAddressLevel").val(selectLevelId);
				$("#newParentid").val("1");
			}
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(!data.id){
	            	 			$.messageBox({
	            	 				level: "warn",
		 							message:data
	 			 				});
	             			return;
	 					}
		                $("#permanentAddressLogList").trigger("reloadGrid");
		                $.messageBox({message:"新增日志成功！"});
		               	$("#addAddressCleanDlg").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   	}
	      	});
		},
		ignore:":hidden",
		rules:{
			"permanentAddress.district":{
				required:true,
				maxlength:20,
				checkIsChinese:true
			},
			"permanentAddress.addressNo":{
				required:true,
				isNumber:true,
				maxlength:6,
				minlength:6
			},
			"permanentAddress.newAddressName":{
				required:true,
				maxlength:20,
				minlength:2,
				checkNewNameIsChinese:true
			}
		},
		messages:{
			"permanentAddress.district":{
				required:"请输入原地区名称",
				maxlength:$.format("地区名称在{0}字以内"),
				checkIsChinese:"原地区名称必须为中文"
			},
			"permanentAddress.addressNo":{
				required:"请输入地区编号",
				isNumber:"地区编号只能输入6位数字",
				maxlength:$.format("地区编号长度为6"),
				minlength:$.format("地区编号长度为6")
			},
			"permanentAddress.newAddressName":{
				required:"请输入新地区名称",
				maxlength:"新地区名称不能大于20个字符",
				minlength:"最少输入2个子都",
				checkNewNameIsChinese:"新地区名称必须为中文"
			}
		}
	});
});
</script>
