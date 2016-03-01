<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24" style="width:99.8%;height: 520px;text-align:center;">
	<form id="parameterConfigForm" action="${path}/parameterConfigManage/saveDeductionStandard.action">
		<br><br>
		<div class="grid_3 lable-right" >
			<label>超期办理扣分：</label>
		</div>
		<div class="grid_5">
			<input name="parameterConfig.handleScore" type="text" maxlength="6" value="${parameterConfig.handleScore}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某台账超过时限未办理将导致处理部门被扣分。 </label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right" >
			<label>超期办结扣分：</label>
		</div>
		<div class="grid_5">
			<input name="parameterConfig.transferredScore"type="text" maxlength="6" value="${parameterConfig.transferredScore}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某台账超过时限未办结将导致处理部门被扣分。 </label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right" >
			<label>超期流转扣分：</label>
		</div>
		<div class="grid_5">
			<input name="parameterConfig.circulationScore" type="text" maxlength="6" value="${parameterConfig.circulationScore}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：如果台账需要流转，超过时限未流转将导致处理部门被扣分。</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<br>
		<pop:JugePermissionTag ename="parameterEvaluationManagement">
			<div class="grid_10" style="text-align:center;">
				<button type="submit" style="height:30px;">保存</button>
				<button type="reset"" style="height:30px;">重置</button>
			</div>
		</pop:JugePermissionTag>
	</form>
</div>
<script type="text/javascript">
//精确到3位的小数
jQuery.validator.addMethod("positiveDouble", function(value, element) {
	var positiveInteger = /^\d+(\.[0-9]{1,3})?$/;
	return this.optional(element) || (positiveInteger.test(value));
});
$(function(){
	 $("#parameterConfigForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	        	     if(!data.handleScore){
             	     	$.messageBox({ message:data, level: "error"});
             	 		return ;
                	 }
			    	 $.messageBox({message:"成功保存设置信息!"});
			    	 return ;
		      	  },
		      	  error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	  }
	      	  });
		},
		rules:{
			"parameterConfig.handleScore":{
				required:true,
				positiveDouble:true,
				max:99.999
			},
			"parameterConfig.transferredScore":{
				required:true,
				positiveDouble:true,
				max:99.999
			},
			"parameterConfig.circulationScore":{
				required:true,
				positiveDouble:true,
				max:99.999
			}
		},
		messages:{
			"parameterConfig.handleScore":{
				required:"请输入超期办理扣分数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			},
			"parameterConfig.transferredScore":{
				required:"请输入超期办结扣分数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			},
			"parameterConfig.circulationScore":{
				required:"请输入超期流转扣数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			}
		}
	});
	
});
</script>