<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/includes/baseInclude.jsp" />
	<div class="container container_24">
		<form action="${path}/baseInfo/propagandaAndVerificationManage/updatePropagandaAndVerificationSignDetail.action" method="post" id="maintainForm">
		<input type="hidden" name="propagandaAndVerification.id" id="propagandaAndVerification-id" value="${propagandaAndVerification.id}" />
		<input type="hidden" name="propagandaAndVerification.organization.id" id="orgId" value="${(propagandaAndVerification.organization.id)}" />
		
		<div class="grid_7 lable-right">
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_14">	<input type="text" name="propagandaAndVerification.occurrenceDate" id="occurrenceDate" class="form-txt"
		value="<s:date name="propagandaAndVerification.occurrenceDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_14">
			<input name="propagandaAndVerification.address" value="${propagandaAndVerification.address}" maxlength="50" readonly class="form-txt {maxlength:50,required:true,messages:{required:'地点必须填写',maxlength:'地点最多50个字符'}} " />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_14">
			<input name="propagandaAndVerification.name" value="${propagandaAndVerification.name}" maxlength="16" class="form-txt {maxlength:16,required:true,messages:{required:'姓名必须填写',maxlength:'姓名最多16个字符'}}" readonly/>
		</div>
    <div class='clearLine'>&nbsp;</div>
  		<div class="grid_7 lable-right">
			<label class="form-lbl">宣传： </label>
		</div>
		<div class="grid_14">
		   <input type="radio" disabled name="propagandaAndVerification.propaganda" value="1" <s:if test="propagandaAndVerification.propaganda==1"> checked="checked" </s:if>>是
		   &nbsp;&nbsp;<input type="radio" name="propagandaAndVerification.propaganda" value="0"  disabled <s:if test="propagandaAndVerification.propaganda==0"> checked="checked" </s:if>>否
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">核查申报： </label>
		</div>
		<div class="grid_14">
		    <input type="radio" name="propagandaAndVerification.verificationReport" value="1" disabled <s:if test="propagandaAndVerification.verificationReport==1"> checked="checked" </s:if>>是 
		   &nbsp;&nbsp;<input type="radio" name="propagandaAndVerification.verificationReport" value="0"  disabled <s:if test="propagandaAndVerification.verificationReport==0"> checked="checked" </s:if>>否
  		</div>
  		<div class='clearLine'>&nbsp;</div>
			
		<div class="grid_7 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_14">
            <textarea rows="1" name="propagandaAndVerification.remark"  cols="" class="form-txt" readonly maxlength="50">${propagandaAndVerification.remark}</textarea>
        </div>
        <div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收人:</label>
		</div>
		<div class="grid_14">
			<input type="text" id="signUserName" name="propagandaAndVerification.signUserName" readonly class="form-txt" value="${propagandaAndVerification.signUserName}" maxlength="20"/>
		</div>
			<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="propagandaAndVerification.signDate" id="signDate" class="form-txt"
		value="<s:date name="propagandaAndVerification.signDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		</div>
			<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		 <div class="grid_14">
            <textarea rows="4" name="propagandaAndVerification.advice"  cols="" class="form-txt {maxCharLength:600,messages:{maxCharLength:'长度不能超过300'}}">${propagandaAndVerification.advice}</textarea>
        </div>
		</form>
	 </div>	
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form){
		
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (data!=true) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
				$.messageBox({message:"成功保存信息!"});
			     $("#propagandaAndVerificationDialog").dialog("close");
			     $("#propagandaAndVerificationList").trigger("reloadGrid");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		}
	});
});

</script>