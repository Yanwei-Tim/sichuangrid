<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="changeHouseCodeForm" action="${path}/baseinfo/actualHouseManage/changeHouseCode.action">
	<pop:token />
		<div class="grid_8 lable-right"><label class="form-lbl">原房屋编号：</label>
		</div>
		<div class="grid_16"><input type="text" id="oldHouseCodeShow" value="${houseInfo.houseCode}" maxlength="20" class="form-txt" disabled="disabled"/>
		</div>
		<div class="grid_8 lable-right"><label class="form-lbl">新房屋编号：</label>
		</div>
		<div class="grid_16"><input type="text" id="newHouseCode" name="houseInfo.houseCode" maxlength="20" class="form-txt" />
		</div>
		<input type="hidden" id="oldHouseCode" name="oldHouseCode" value="${houseInfo.houseCode}" maxlength="20" class="form-txt"/>
		<input type="hidden" name="orgId" value="${orgId}"/>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#changeHouseCodeForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	     		success: function(data){
	     			$("#actualHouseList").trigger("reloadGrid");
					$.messageBox({message:"成功重置房屋编号!"});
	     			$("#actualHouseDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
	  	  	});
		},
		rules:{
			"houseInfo.houseCode":{
				required:true,
				maxlength:50,
				notExist:true
			}
		},
		messages:{
			"houseInfo.houseCode":{
				required:"请填写新房屋编号",
				maxlength:$.format("房屋编号不能大于{0}个字符"),
				notExist:"房屋编号已存在"
			}
		}
	});
	jQuery.validator.addMethod("notExist", function(value, element){
		var flag = false;
		if($("#oldHouseCodeShow").val() == $("#newHouseCode").val()){
			flag = true;
		}else{
			$.ajax({
				async:false,
				url:'${path}/baseinfo/actualHouseManage/exist.action?orgId='+getCurrentOrgId()+'&houseInfo.houseCode='+$("#newHouseCode").val(),
				success:function(data){
					flag=!data;
				}
			});
		}
		return flag;
	});
});
</script>