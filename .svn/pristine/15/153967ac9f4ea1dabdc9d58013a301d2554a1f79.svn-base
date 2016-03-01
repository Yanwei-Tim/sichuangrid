<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container_24">
	<form action="${path}/partyBuildng/doubleRegActivities/editMember.action" id='maintainForm' method="post">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="membersEnroll.id" id="id" value="${membersEnroll.id}" />
		<input type="hidden" name="membersEnroll.member.id" id="memberId" value="${membersEnroll.member.id}" />
		<input id="_imgUrl" name="membersEnroll.member.imgUrl" type="hidden" value="${membersEnroll.member.imgUrl}"/>
		<input type="hidden" name="membersEnroll.organization.id" id="organizationId" value="${membersEnroll.organization.id}" />
		<input type="hidden" name="membersEnroll.isEnroll" id="isEnroll" value="<c:choose><c:when test='${membersEnroll.isEnroll == null }'>0</c:when><c:otherwise>${membersEnroll.isEnroll}</c:otherwise></c:choose>"/>
		<input type="hidden" name="membersEnroll.logoutStatus" id="logoutStatus" value="<c:choose><c:when test='${membersEnroll.logoutStatus == null }'>0</c:when><c:otherwise>${membersEnroll.logoutStatus}</c:otherwise></c:choose>" />
		<input type="hidden" name="membersEnroll.orgInternalCode" id="orgInternalCode" value="${membersEnroll.orgInternalCode}" />
		<div id="baseInfo">
			<div class="grid_5 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">身份证号：</label>
			</div>
		    <div class="grid_7">
				<input type="text" name="membersEnroll.member.idCardNo" id="idCardNo" maxlength="18" value='${membersEnroll.member.idCardNo }' 
				class="form-txt {required:true,idCard:true,exsistedIdCard:true,messages:{required:'请输入身份证号码',idCard:'请输入一个合法的身份证号码',exsistedIdCard:'该党员在本网格下已报到或已注销，请重新输入!'}}"/>	    
		    </div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">姓名：</label>
			</div>
		    <div class="grid_8">
				<input type="text" name="membersEnroll.member.name" id="name" maxlength="20" value='${membersEnroll.member.name}' 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'/>	    
		    </div>
		    <div class="clearLine">&nbsp;</div>
		    <div class="grid_5 lable-right">
		    	<label class="form-lbl">联系电话：</label>
		    </div>
		    <div class="grid_7">
				<input type="text" name="membersEnroll.member.telephone" id="telephone"  maxlength="13"  value='${membersEnroll.member.telephone}' 
					class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888"  />	    
			</div>
		    <div class="grid_4 lable-right">
		    	<em class="form-req">*</em>
		    	<label class="form-lbl">性别：</label>
		    </div>
		    <div class="grid_8">
				<select name="membersEnroll.member.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${membersEnroll.member.gender.id}" />
				</select>
			</div>
			<div class="clearLine">&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">所属部门：</label>
			</div>
		    <div class="grid_7">
			   <select name="membersEnroll.member.belongOrg.id" id="belongOrg" class='form-txt'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" defaultValue="${membersEnroll.member.belongOrg.id}" />
				</select>
	   		</div>
	   		<div  class="grid_4 lable-right">
	  			<label class="form-lbl">所属类型：</label>
	  		</div>
			<div class="grid_8">
				<select name="membersEnroll.regActivitiesType" id="regActivitiesType" class='form-txt'>
			   		<option value="<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@AUTHORITY_CLASS'/>"
			   			<c:if test='${(membersEnroll.regActivitiesType)=="authorityClass"}'>selected='selected'</c:if>>机关类</option>
			   		<option value="<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@CAUSE_CLASS'/>"
			   			<c:if test='${(membersEnroll.regActivitiesType)=="causeClass"}'>selected='selected'</c:if>>事业类</option>
			   		<option value="<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@ENTERPRISE_CLASS'/>"
			   			<c:if test='${(membersEnroll.regActivitiesType)=="enterpriseClass"}'>selected='selected'</c:if>>企业类</option>
			   		<option value="<s:property value='@com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType@OTHER_CLASS'/>"
			   			<c:if test='${(membersEnroll.regActivitiesType)=="otherClass"}'>selected='selected'</c:if>>其他</option>
				</select>
			</div>
			
			<div class="clearLine">&nbsp;</div>
	   		<div class="grid_5 lable-right">
	   			<label class="form-lb1">现居地址：</label>
	   		</div>
	   		<div class="grid_19">
	   			<input type="text" name="membersEnroll.member.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${membersEnroll.member.nativePlaceAddress}"
	   			 style="width: 99%" class='form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:"现居地址不能输入特殊字符"}}'/>
			</div>
			
			<div class="clearLine">&nbsp;</div>
	   		<div class="grid_5 lable-right">
			<label class="form-lbl">认领志愿者岗位：</label>
	 		</div>
			<div class="grid_7 heightAuto" style="width: 420px;height: 120px;"> 
				<input type=hidden name="memberVolunteerJobsIds" id="memberVolunteerJobsIds"  maxlength="" class="form-txt" value="<s:property value="memberVolunteerJobsMap.memberVolunteerJobsIds"/>"/>
				<textarea readonly rows="9" cols="20" id="memberVolunteerJobsName" class="form-txt" style="width: 420px;"  ><s:property value="memberVolunteerJobsMap.memberVolunteerJobsNames"/></textarea>
			</div>
			<div class="grid_1 lable-left">
				<input type="button" id="change" value="选  择" style="margin-top: 20px;margin-left: 12px;width: 50px;height: 25px;text-align: center;"/>
			</div>
	    </div>
	</form>
</div>

<script type="text/javascript"><!--
var idcardno = '${membersEnroll.member.idCardNo}';
var organization = getCurrentOrgId();

var flag = true;
var membersEnroll;

if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

$(function(){
	$("#idCardNo").blur(function(){
    	if(flag) {
			isSettled(membersEnroll);
			membersEnroll=null;
		}
	});
	
	jQuery.validator.addMethod("exsistedIdCard", exsistedIdCard);
	jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){
			return true;
		}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value); 
	}
	
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
			    	$("#membersErollList").trigger("reloadGrid");
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
	<c:if test='${mode=="edit"}'>
	 	$("#idCardNo").attr("readonly","readonly");
	</c:if>
});

function isEmpty(o){
	if(o==null){
		return null;
	}else{
		return o.id;
	}
}

/* function formatDate(date) {
	return date.substr(0,date.indexOf('T'));
} */

function exsistedIdCard(value, element){
	if(value==null||value==undefined||value==""
			||("edit"==$("#mode").val()&&idcardno==$("#idCardNo").val())){
		return flag = true;
	}
	$.ajax({
		async: false,
		url:'${path}/partyBuildng/doubleRegActivities/exsistedIdCard.action',
	   	data:{
			"membersEnroll.member.idCardNo":$("#idCardNo").val()
        },
		success:function(data){
			if(data.member != null){
				membersEnroll = data;
				flag = true;
			}else{
				flag = false;
			}
		}
	});
	return flag;
}

function isSettled(data){
	$("#memberId").val(data.member.id);
	$("#idCardNo").val(data.member.idCardNo);
	$("#name").val(data.member.name);
	$("#gender").val(isEmpty(data.member.gender));
	$("#telephone").val(data.member.telephone);
	
	$("#nativePlaceAddress").val(data.member.nativePlaceAddress);
	$("#belongOrg").val(isEmpty(data.member.belongOrg));
	$("#organizationId").val(organization);
	
	$("#isEnroll").val(data.isEnroll);
	$("#id").val(data.id);
	$("#regActivitiesType").val(data.regActivitiesType);
	$("#orgInternalCode").val(data.orgInternalCode);
	if(null!=data.member.imgUrl && data.member.imgUrl!=""){
		$("#_imgUrl").val(data.member.imgUrl);
		$("#headerImg").attr("src",data.member.imgUrl+"?r="+Math.random());
		$(".shadow").show();
	}
}
 
//添加志愿者关联 
$("#change").click(function(){
	var orgid = $("#organizationId").val();
	$("#claimVolunteerJobsDialog").createDialog({
		title:"添加志愿者关联",
		width: 600,
		height: 300,
		url:'${path}/partyBuilding/doubleRegActivities/memberEnroll/addVolunteerJobs.jsp?mode=addVolunteerJobs&organization.id='+orgid+'&dailogName=claimVolunteerJobsDialog',
		buttons: {
			"确定" : function(){
				$(this).dialog("close");
			}
		}
	});
});
</script>