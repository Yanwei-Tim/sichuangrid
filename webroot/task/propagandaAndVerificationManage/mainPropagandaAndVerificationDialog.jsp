<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="宣传核查" class="container container_24">
	 <form id="maintainForm" method="post"	action="${path}/baseInfo/propagandaAndVerificationManage/addPropagandaAndVerification.action" >
	 	 <pop:token/>
	 	<input type="hidden" name="propagandaAndVerification.organization.id" id="orgId" value="${propagandaAndVerification.organization.id }"/>
	 	<input type="hidden" name="propagandaAndVerification.organization.orgName" id="orgName" value="${propagandaAndVerification.organization.orgName }"/>
	 	<input type="hidden" name="propagandaAndVerification.id" id="propagandaAndVerificationId" value="${propagandaAndVerification.id }"/>
		<div class="grid_8 lable-right">
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_15">
			<input type="text" name="propagandaAndVerification.occurrenceDate"  value="<fmt:formatDate value="${propagandaAndVerification.occurrenceDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" class="form-txt" readonly="readonly"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_15">
			<input name="propagandaAndVerification.address" value="${propagandaAndVerification.address}"  class="form-txt {maxlength:40,required:true,messages:{required:'地点必须填写',maxlength:'地点最多40个字'}}" />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_15">
			<input name="propagandaAndVerification.name" value="${propagandaAndVerification.name}"  class="form-txt {maxlength:10,required:true,messages:{required:'姓名必须填写',maxlength:'姓名最多10个字'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_8 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_15">
			<input name="propagandaAndVerification.idCard" value="${propagandaAndVerification.idCard}"  class="form-txt {idCard:true,messages:{idCard:$.format('请输入一个合法的身份证号码')}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">电话号码：</label>
		</div>
		<div class="grid_15">
			<input name="propagandaAndVerification.phone" value="${propagandaAndVerification.phone}"  class="form-txt {phoneAndMobile:true,messages:{phoneAndMobile:$.format('请输入以固定电话：028-87653333或者手机：15102888888为格式的号码')}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
  		<div class="grid_8 lable-right">
			<label class="form-lbl">宣传： </label>
		</div>
		<div class="grid_15">
		   <input type="radio" name="propagandaAndVerification.propaganda" value="1" <s:if test="propagandaAndVerification.propaganda==1"> checked="checked" </s:if>>是
		   &nbsp;&nbsp;<input type="radio" name="propagandaAndVerification.propaganda" value="0" <s:if test="propagandaAndVerification.propaganda==0"> checked="checked" </s:if>>否
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">核查申报： </label>
		</div>
		<div class="grid_15">
		    <input type="radio" name="propagandaAndVerification.verificationReport" value="1" <s:if test="propagandaAndVerification.verificationReport==1"> checked="checked" </s:if>>是 
		   &nbsp;&nbsp;<input type="radio" name="propagandaAndVerification.verificationReport" value="0"  <s:if test="propagandaAndVerification.verificationReport==0"> checked="checked" </s:if>>否
  		</div>
  		<div class='clearLine'>&nbsp;</div>
			
		<div class="grid_8 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_15">
            <textarea rows="3" name="propagandaAndVerification.remark"  cols="" class="form-txt" maxlength="250">${propagandaAndVerification.remark}</textarea>
        </div>
	</form>
</div>	
<script type="text/javascript">
jQuery.validator.addMethod("phoneAndMobile", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
	var length = value.length;
	if(length == 11 && mobile.test(value)){return true;}
	var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
	if (value.match(phone)==null) {return false;}
	return true
});
$(document).ready(function(){
	<s:if test="propagandaAndVerification.id!=null">
		$("#maintainForm").attr("action","${path}/baseInfo/propagandaAndVerificationManage/updatePropagandaAndVerification.action");
	</s:if>
});
$("#maintainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form) {
     $(form).ajaxSubmit({
         success: function(data){
        	 if (!data) {
                	 $.messageBox({
						message:"保存信息失败",
						level: "error"
					 });
                 	return;
                 }
				$.messageBox({message:"成功保存信息!"});
			     $("#propagandaAndVerificationDialog").dialog("close");
			     $("#propagandaAndVerificationList").trigger("reloadGrid");
  	   },
  	   error: function(XMLHttpRequest, textStatus, errorThrown){
  	      alert("提交错误");
  	   }
  	  });
},
rules:{
	"propagandaAndVerification.remark":{
		maxlength:200
	}
},
messages:{	
	"propagandaAndVerification.remark":{
	    maxlength:$.format("备注最多输入{0}个字")
			}
}
});
</script>