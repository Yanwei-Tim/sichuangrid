<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="其他联系人查询"  class="container container_24">
	  <div class="grid_4 lable-right">
		<label >姓名：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="myContacter.name" class="form-txt" maxlength="20" 
		 id="myContactName" value="${myContacter.name}"/>
	</div>
	<div class="grid_4 lable-right">
		<label>联系手机：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="myContacter.mobileNumber" class="form-txt numInput" maxlength="11"
		 id="myContact-mobileNumber" value="${myContacter.mobileNumber}" />
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$(".numInput").bind("blur",function(){
		var inputObject=$(this);
		var name=inputObject.attr("name");
		var errorMsgs=new Array();
		
		if(isNaN(inputObject.val())){
			errorMsgs.push("请输入数字");
		}
		if(errorMsgs.length>0){
			inputObject.poshytip('hide');
			inputObject.dialogtip({
				content:"<div class='inputName' inputName='"+name+"'><span class='error'></span>"+errorMsgs.pop()+"</div>"
			});
			inputObject.poshytip('show');
			inputObject.focus();
		}else{
			inputObject.poshytip('hide');
		}
	});

});

</script>