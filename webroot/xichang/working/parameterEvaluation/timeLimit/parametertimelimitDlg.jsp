<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="三本台账时限标准表" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
	<pop:token/>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="parametertimelimit.id" value="${parametertimelimit.id}"/>
		<s:if test='"edit".equals(mode)'>
			<input type="hidden" name="parametertimelimit.departmentlevel.id" value="${parametertimelimit.departmentlevel.id}"/>
		</s:if>
	 	<div class="grid_6 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">应用层级：</label>
	 	</div>
		<div class="grid_10">
			<select  name="parametertimelimit.departmentlevel.id" <s:if test='"edit".equals(mode)'>disabled="disabled"</s:if> id="departmentlevel" class="form-txt {required:true,messages:{required:'请选择应用层级'}}">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACCOUNT_TIME_LIMIT_LEVEL" defaultValue="${parametertimelimit.departmentlevel.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_6 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">办理时限：</label>
	 	</div>
		<div class="grid_10">
			<input type="text" name="parametertimelimit.handlelimit" id="handlelimit"  maxlength="5" class="form-txt {required:true,digits:true,messages:{required:'请输入办理时限',digits:'必须输入整数'}}" value="${parametertimelimit.handlelimit}"/>
		</div>
		<div class="grid_8">
			<label class="form-req">&nbsp;&nbsp;(单位工作日)</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_6 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">办结时限：</label>
	 	</div>
		<div class="grid_10">
			<input type="text" name="parametertimelimit.transferredlimit" id="transferredlimit"  maxlength="5" class="form-txt {required:true,digits:true,messages:{required:'请输入办结时限',digits:'必须输入整数'}}" value="${parametertimelimit.transferredlimit}"/>
		</div>
		<div class="grid_8">
			<label class="form-req">&nbsp;&nbsp;(单位工作日)</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_6 lable-right">
	 		<em class="form-req">*</em>
			<label class="form-lbl">流转时限：</label>
	 	</div>
		<div class="grid_10">
			<input type="text" name="parametertimelimit.circulationlimit" id="circulationlimit"  maxlength="5" class="form-txt {required:true,digits:true,messages:{required:'请输入流转时限',digits:'必须输入整数'}}" value="${parametertimelimit.circulationlimit}"/>
		</div>
		<div class="grid_8">
			<label class="form-req">&nbsp;&nbsp;(单位工作日)</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	</form>
	
</div>
<script type="text/javascript">

$(document).ready(function(){
	$("#maintainForm").formValidate({
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
						//$("#parametertimelimitList").setRowData(data.id,data);
						$("#parametertimelimitList").trigger("reloadGrid");
				    	$.messageBox({message:"三本台账时限标准表修改成功!"});
					</s:if>
					<s:if test='"add".equals(mode)'>
						$("#parametertimelimitList").addRowData(data.id,data,"first");
						$.messageBox({message:"三本台账时限标准表新增成功!"});
					</s:if>
					 $("#parametertimelimitDialog").dialog("close");
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
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm").attr("action","${path}/parametertimelimitManage/addParametertimelimit.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#maintainForm").attr("action","${path}/parametertimelimitManage/updateParametertimelimit.action");
</s:if>

});

</script>


