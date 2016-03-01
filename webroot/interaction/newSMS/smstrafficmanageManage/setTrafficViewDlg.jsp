<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="流量管理" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="organizationId" name="smstrafficmanage.orgId" value="${smstrafficmanage.orgId}"/>
		<div class="grid_9 lable-right">
			<label class="form-lbl">电信流量：</label>
		 </div>
		 <div class="grid_12">
			<input type="text"  maxlength="20" name="smstrafficmanage.telecomTraffic"  id="telecom"
			value="${smstrafficmanage.telecomTraffic }" class='form-txt {required:true,digits:true,messages:{required:"请输入电信流量",digits:"请输入整数"}}'/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_9 lable-right">
			<label class="form-lbl">移动流量：</label>
		 </div>
		 <div class="grid_12">
			<input type="text"  maxlength="20" name="smstrafficmanage.mobileTraffic"  id="mobile"
			value="${smstrafficmanage.mobileTraffic }" class='form-txt {required:true,digits:true,messages:{required:"请输入移动流量",digits:"请输入整数"}}'/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_9 lable-right">
			<label class="form-lbl">联通流量：</label>
		 </div>
		 <div class="grid_12">
			<input type="text"  maxlength="20" name="smstrafficmanage.chinaUnicomTraffic"   id="unicom"
			value="${smstrafficmanage.chinaUnicomTraffic }" class='form-txt {required:true,digits:true,messages:{required:"请输入联通流量",digits:"请输入整数"}}'/>
		</div>
	</form>
	
</div>
<script type="text/javascript">
	 	
$(document).ready(function(){
	
	if($("#telecom").val()==""){
		$("#telecom").val($("#telecomTraffic").val());
	}
	if($("#mobile").val()==""){
		$("#mobile").val($("#mobileTraffic").val());
	}
	if($("#unicom").val()==""){
		$("#unicom").val($("#chinaUnicomTraffic").val());
	}
	
	<c:if test='${mode=="set"}'>
    	$("#maintainForm").attr("action","${path}/smstrafficmanageManage/addSmstrafficmanage.action");
	</c:if>	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
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
					<c:if test='${mode=="set"}'>
						$.messageBox({message:"设置成功!"});
					</c:if>
					$("#orgTrafficList").trigger("reloadGrid");
					$("#trafficList").trigger("reloadGrid");
					$("#trafficDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:''
	});

});

</script>


