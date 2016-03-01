<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="updateIdCardNoForm" method="post" action="${path}/baseinfo/baseinfoIdCardChange/baseinfoIdCardChange.action">
	<pop:token />
		<input type="hidden" id="actualPopulationType" name="countrymen.actualPopulationType" class="form-txt" value="<s:property value="#parameters.actualPopulationType"/>" class="form-txt" />
		<input type="hidden" id="populationId" name="countrymen.id" class="form-txt" value="<s:property value="#parameters.countrymenId"/>" class="form-txt" />
		<input type="hidden" id="idCardNo_" name="idCardNo" class="form-txt" value="<s:property value="#parameters.idCardNo"/>" class="form-txt" />
		<input type="hidden" id="orgId" name="countrymen.organization.id" class="form-txt" value="<s:property value="#parameters.orgId"/>" class="form-txt" />
	
	<div class="grid_10 lable-right">
			<em class="form-req">*</em>
			<label>身份证号码：</label>
		</div>
		<div class="grid_14">
			<input type="text" id="idCardNo" name="countrymen.idCardNo" maxlength="18" class="form-txt" value="<s:property value="#parameters.idCardNo"/>" class="form-txt" />
		</div>
	 </form>
	<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("exsistedIdCard", function(value, element){
	var flag =true;
	if(value==null||value==undefined||value==""){return true}
	if(value==$("#idCardNo_").val()){return true}
	$.ajax({
		async: false ,
		url:"${path}/baseinfo/baseinfoIdCardChange/exsistedIdCard.action",
	   	data:{
			"idCardNo":$('#idCardNo').val(),
			"orgId":getCurrentOrgId(),
			"actualPopulationType": function(){return $("#actualPopulationType").val();},
			"populationId":$('#populationId').val()
        },
		success:function(responseData){
			if(responseData){
				flag = false;
			}
		}
	});
	return flag;
});
$(document).ready(function(){
	$("#updateIdCardNoForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							evel: "error",
							message:data
			 			});
            			return;
					}
	                $("#"+"<s:property value='#parameters.listTable'/>").trigger("reloadGrid");
	                $.messageBox({message:"身份证修改成功！"});
	                $("#"+"<s:property value='#parameters.dialog'/>").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"countrymen.idCardNo":{
				required:true,
				idCard:true,
				exsistedIdCard:true,
				maxlength:18
			}
		},
		messages:{
			"countrymen.idCardNo":{
				required:"请输入身份证号码",
				idCard:"请输入一个合法的身份证号码",
				exsistedIdCard:"已存在此身份证号码请重新录入",
				maxlength:$.format("身份证号码最多需要输入{0}个字符")
			}	
		}
	});
	
});
</script>