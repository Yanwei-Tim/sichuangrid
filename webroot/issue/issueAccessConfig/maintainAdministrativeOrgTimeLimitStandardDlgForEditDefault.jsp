<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="administrativeOrgTimeLimitStandardForm" method="post" action="${path}/administrativeOrgTimeLimitStandardManage/updateAdministrativeOrgTimeLimitStandard.action">
<pop:token />
        <input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="administrativeOrgTimeLimitStandardId" type="hidden"	name="administrativeOrgTimeLimitStandard.id" value="${administrativeOrgTimeLimitStandard.id }" />
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">黄牌受理时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text"  id="administrativeOrgTimeLimitStandardYellowLimitAccept" maxlength="50" name="administrativeOrgTimeLimitStandard.yellowLimitAccept" value="${administrativeOrgTimeLimitStandard.yellowLimitAccept}" title="黄牌受理时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入黄牌受理时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内受理(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从交办之日起计算。 </label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">黄牌办理时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text" id="administrativeOrgTimeLimitStandardYellowLimitHandle" maxlength="50" name="administrativeOrgTimeLimitStandard.yellowLimitHandle" value="${administrativeOrgTimeLimitStandard.yellowLimitHandle}" title="黄牌办理时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入黄牌办理时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内办理完成(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从受理之日起计算。 </label>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">黄牌验证时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text" id="administrativeOrgTimeLimitStandardYellowLimitVerify" maxlength="50" name="administrativeOrgTimeLimitStandard.yellowLimitVerify" value="${administrativeOrgTimeLimitStandard.yellowLimitVerify}" title="黄牌验证时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入黄牌验证时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内办理完成(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从受理之日起计算。 </label>
	</div>	
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">红牌受理时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitAccept" maxlength="50" name="administrativeOrgTimeLimitStandard.redLimitAccept" value="${administrativeOrgTimeLimitStandard.redLimitAccept}" title="红牌受理时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入红牌受理时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内受理(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从交办之日起计算。 </label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">红牌办理时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitHandle" maxlength="50" name="administrativeOrgTimeLimitStandard.redLimitHandle" value="${administrativeOrgTimeLimitStandard.redLimitHandle}" title="红牌办理时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入红牌办理时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内办理完成(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从受理之日起计算。 </label>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">红牌验证时限：</label>
	</div>
	<div class="grid_3">
		<input  type="text" id="administrativeOrgTimeLimitStandardRedLimitVerify" maxlength="50" name="administrativeOrgTimeLimitStandard.redLimitVerify" value="${administrativeOrgTimeLimitStandard.redLimitVerify}" title="红牌验证时限"
			class='form-txt {required:true,digits:true,min:1,max:9999,messages:{required:"请输入红牌验证时限",digits:"请输入正整数",min:$.format("时限不能小于{0}"),max:$.format("时限不能大于{0}")}}' />
	</div>
	<div class="grid_7">
		&nbsp;&nbsp;天内办理完成(单位工作日)
	</div>
	<div class="grid_7" style="text-align:left">
		<label class="form-req">&nbsp;&nbsp;&nbsp;&nbsp;注：从受理之日起计算。 </label>
	</div>	
</form>

</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#administrativeOrgTimeLimitStandardForm").formValidate({
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
        			$.messageBox({message:"成功修改行政部门时限标准!"});
        			$("#administrativeOrgTimeLimitStandardDialog").dialog("close");
					$("#administrativeOrgTimeLimitStandardList").trigger("reloadGrid");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		}
	});
});
</script>