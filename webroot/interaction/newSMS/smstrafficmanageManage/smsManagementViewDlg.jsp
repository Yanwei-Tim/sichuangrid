<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden"  id="smsId" value="${param.smsId}" />
	 	<input type="hidden" id="port" value="${param.port }" />
	 	<input type="hidden" id="realmName" value="${param.realmName }" />
	 	
	 	<div class="grid_10 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">globalName：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" id="globalName" name="globalName" value="${param.globalName }" maxlength="60" readonly="readonly" />
		</div>
	 	
	 	<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_10 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">globalShowName：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" id="globalShowName" name="globalShowName" value="${param.globalShowName }" maxlength="60"  />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_10 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">globalValue：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" id="globalValue" name="globalValue" value="${param.globalValue }" maxlength="60"  />
		</div>
	 	
	</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
	


$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
	        	dataType : "jsonp",
	    		jsonp:'callback',
				success: function(data){
		        	if(!data){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
		        	
		        	var result={
							"globalname":$("#globalName").val(),
							"globalshowname":$("#globalShowName").val(),
							"globalvalue":$("#globalValue").val()
						};
		        	
					$("#smsManagementList").setRowData($("#smsId").val(),result);
				    $.messageBox({message:"修改成功!"});
				    $("#smsManagementDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			globalName: {
				required: true
			},
			globalShowName: {
				required: true
			},
			globalValue :{
				required: true
			}
		},
		messages:{
			globalName: {
				required: "请输入globalName！"
			},
			globalShowName: {
				required: "请输入globalShowName！"
			},
			globalValue :{
				required: "请输入globalValue！"
			}
		},
		ignore:':hidden'
	});
	
	var port=$("#port").val();
	var realmName=$("#realmName").val();
	
    $("#maintainForm").attr("action","http://"+realmName+":"+port+"/updateGlobalServletForJsonp.action");

});

</script>



