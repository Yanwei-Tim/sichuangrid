<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24" >
	<form action="${path}/issues/issueStandardForFunOrgManage/updateIssueStandardForFunOrg.action" method="post" id="maintainForm">
		<pop:token />
		<input type="hidden" value=${issueStandardForFunOrg.id } name="issueStandardForFunOrg.id"/>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">黄牌受理时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.yellowLimitAccept" id="yellowLimitAccept" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"黄牌受理时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.yellowLimitAccept }"/>
		</div>
		<div class="grid_7">
			(单位工作日)内受理
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从交办之日起计算
		</div>
       <div class='clearLine'></div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">黄牌办理时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.yellowLimitHandle" id="yellowLimitHandle" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"黄牌办理时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.yellowLimitHandle }"/>
		</div>
		<div class="grid_7">
			(单位工作日)内办理完成
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从受理之日起计算
		</div>
		
       <div class='clearLine'></div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">黄牌验证时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.yellowLimitVerify" id="yellowLimitVerify" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"黄牌验证时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.yellowLimitVerify }"/>
		</div>
		<div class="grid_7">
			(单位工作日)内办理完成
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从办理之日起计算
		</div>
				
		<div class='clearLine'></div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">红牌受理时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.redLimitAccept" id="redLimitAccept" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"红牌受理时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.redLimitAccept }" maxlength="50"/>
		</div>
		<div class="grid_7">
			(单位工作日)内受理
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从交办之日起计算
		</div>
		<div class='clearLine'></div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">红牌办理时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.redLimitHandle" id="redLimitHandle" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"红牌办理时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.redLimitHandle }" maxlength="50"/>
		</div>
		<div class="grid_7">
			(单位工作日)内办理完成
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从受理之日起计算
		</div>
		
		<div class='clearLine'></div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">红牌验证时限：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="issueStandardForFunOrg.redLimitVerify" id="redLimitVerify" 
			class='form-txt {required:true,positiveInteger:true,messages:{required:"红牌验证时限必须输入",positiveInteger:"请输入正整数"}}' value="${issueStandardForFunOrg.redLimitVerify }" maxlength="50"/>
		</div>
		<div class="grid_7">
			(单位工作日)内办理完成
		</div>
		<div class="grid_7 lable-right" style="color:red;">
			注：从办理之日起计算
		</div>		
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
	</form>
</div>
<script>
$(document).ready(function(){
	$("#maintainForm").formValidate({
		showErrors:showErrorsForTab,
		promptPosition: "bottomLeft",
		submitHandler: function(form){
        	formSubmit(form);
		},
		rules:{
		},
		messages:{
		}
	});
});

function formSubmit(form){
    $(form).ajaxSubmit({
        success: function(data){
                if(!data.id){
               	 $.messageBox({
						message:data,
						level: "error"
					 });
                	return;
                }
               	$.messageBox({message:"修改默认时限标准成功!"});
               	$("#standardList").setRowData(data.id,data);
                $("#issueDialog").dialog("close");
                $("#standardList").trigger("reloadGrid");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	      alert("提交数据时发生错误");
 	   }
	});
}
</script>
