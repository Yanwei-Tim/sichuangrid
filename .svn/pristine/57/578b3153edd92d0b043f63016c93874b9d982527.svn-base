<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="dialog-form" title="企业信息维护" class="container container_24">
<form id="maintainForm" method="post"action="${path}/baseInfo/leadingEnterpriseManage/maintainLeadingEnterpaise.action"  >
<pop:token />
<input type="hidden" name="leadingEnterprise.organization.id" value="${organization.id }"/>
<input type="hidden" name="leadingEnterprise.id" id="newVillageBasicId" value="${leadingEnterprise.id }"/>
<input type="hidden" name="mode" id="mode" value="${mode }"/>

<div class="grid_4 lable-right" >
	<em class="form-req" id="maritalStateStat"  >*</em>
	<label class="form-lbl">企业名称：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.enterpriseName" id="" class="form-txt" value="${leadingEnterprise.enterpriseName }" maxlength="20" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" >
	<label class="form-lbl">负责人：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.chargePerson" id="" class="form-txt " value="${leadingEnterprise.chargePerson }" maxlength="10" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">联系电话：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.phoneNumber" id="" class="form-txt {telephone:true,messages:{telephone:$.format('联系电话不合法，只能输入数字和横杠（-）')}}" value="${leadingEnterprise.phoneNumber }" maxlength="20" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">地址：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.address" id="" class="form-txt " value="${leadingEnterprise.address }" maxlength="30" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">注册资金(万元)：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.registeredCapital" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" value="${leadingEnterprise.registeredCapital }" maxlength="13" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">注册时间：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.registeredDate" id="registeredDate" readonly class="form-txt"
			value="<fmt:formatDate value="${leadingEnterprise.registeredDate}" type="date" pattern="yyyy-MM-dd" />"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">经济性质：</label>
</div>
<div class="grid_8">
	<select name="leadingEnterprise.economicCharacter.id" id="" class="form-txt" <s:if test="'view'.equals(mode)">disabled</s:if>>
		    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ECONOMICCHARACTER"  defaultValue="${leadingEnterprise.economicCharacter.id }"/>
	</select>
</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">营业执照：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.licenseNumber" id="" class="form-txt " value="${leadingEnterprise.licenseNumber }" maxlength="30" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" >
	<label class="form-lbl">组织机构代码：</label>
</div>
<div class="grid_8">
	<input type="text" name="leadingEnterprise.bodyCode" id="" class="form-txt " value="${leadingEnterprise.bodyCode }" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_20">
   			<textarea rows="5" cols="100"  maxlength="200" name="leadingEnterprise.remark" id="" class="form-txt" <s:if test="'view'.equals(mode)">disabled</s:if>>${leadingEnterprise.remark }</textarea>
   		</div>

 </form>
 </div>
<script>
$(document).ready(function(){
	if('view' != $("#mode").val()){
		$('#registeredDate').datePicker({
			yearRange:'1930:2030',
			dateFormat:'yy-mm-dd',
    		maxDate:'+0d'
		});
	}
	
	//营业执照
	jQuery.validator.addMethod("businessLicense", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var license = /^[0-9]{15}$/;
		var length = value.length;
		if(length == 15 && license.test(value)){return true;}
		return false;
	});
	
	//组织机构代码
	jQuery.validator.addMethod("organizationCode", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var code = /^[A-Z0-9]{9}$/;
		var length = value.length;
		if(length == 9 && code.test(value)){return true;}
		return false;
	});
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(data.id){
						 $.messageBox({message:"企业信息保存成功!"});
						 $("#leadingEnterpriseList").trigger("reloadGrid");
					     $("#addleadingEnterpriseDialog").dialog("close");
					}else{
						 $.messageBox({message:data,level:'error'});
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"leadingEnterprise.enterpriseName":{
				required:true,
				exculdeParticalChar:true
			},
			"leadingEnterprise.chargePerson":{
				exculdeParticalChar:true
			},
			"leadingEnterprise.licenseNumber":{
				businessLicense:true
			},
			"leadingEnterprise.bodyCode":{
				organizationCode:true
			}
		},
		messages:{
			"leadingEnterprise.enterpriseName":{
				required:'企业名称必须填写',
				exculdeParticalChar:"出现非法字符"
			},
			"leadingEnterprise.chargePerson":{
				exculdeParticalChar:"出现非法字符"
			},
			"leadingEnterprise.licenseNumber":{
				businessLicense:'营业执照由15位数字组成'
			},
			"leadingEnterprise.bodyCode":{
				organizationCode:'全国组织机构代码由八位数字（或大写拉丁字母）本体代码和一位数字（或大写拉丁字母）校验码组成'
			}
		}
	});
});
</script>