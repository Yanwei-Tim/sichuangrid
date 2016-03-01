<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container_24">
	<form action="${path}/partyBuildng/memberManage/editMember.action" id='maintainForm' method="post">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="_imgUrl" name="membersEnroll.member.imgUrl" type="hidden" value="${membersEnroll.member.imgUrl}"/>
		<div id="baseInfo">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">身份证号：</label>
			</div>
		    <div class="grid_7">
				<input type="text" name="membersEnroll.member.idCardNo" id="idCardNo" maxlength="18" value='${membersEnroll.member.idCardNo }' 
				class="form-txt {required:true,idCard:true,exsistedIdCard:true,messages:{required:'请输入身份证号码',idCard:'请输入一个合法的身份证号码',exsistedIdCard:'该身份证号码已经存在，请重新输入'}}"/>	    
		    </div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">姓名：</label>
			</div>
		    <div class="grid_8">
				<input type="text" name="membersEnroll.member.name" id="name" maxlength="20" value='${membersEnroll.member.name }' 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'/>	    
		    </div>
		    <div class="clearLine">&nbsp;</div>
		    <div class="grid_4 lable-right">
		    	<label class="form-lbl">联系手机：</label>
		    </div>
		    <div class="grid_7">
				<input type="text" name="membersEnroll.member.telephone" id="telephone"  maxlength="11"  value='${membersEnroll.member.telephone }' title="请输入11位以1开头的联系手机  例如：13988888888" 
					class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />	    
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
			<div class="grid_4 lable-right">
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
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">现居地址：</label>
	   		</div>
	   		<div class="grid_19">
	   			<input type="text" name="membersEnroll.member.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${membersEnroll.member.nativePlaceAddress }"  style="width: 99%"
	   			 class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'现居地址不能输入特殊字符'}}"
				/>
			</div>
			
	   		<div class="grid_4" >
			<em class=""></em>
	   			<label class="form-lb1">认领志愿者岗位：</label>
	   		</div>
	   		<div class="grid_7" style="width: 475px;height: 100px;">
				<input type=hidden name="memberVolunteerJobsIds" id="memberVolunteerJobsIds"  maxlength="" class="form-txt" value="<s:property value="memberVolunteerJobsMap.memberVolunteerJobsIds"/>"/>
				<textarea readonly rows="7" cols="30" id="memberVolunteerJobsName" class="form-txt" style="width: 475px;"  ><s:property value="memberVolunteerJobsMap.memberVolunteerJobsNames"/></textarea>
			</div>
	   		<div class='clearLine'>&nbsp;</div>
	    </div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$(".form-txt").attr("disabled","disabled");
 	$(".shadow").hide();
 	$("#fileToUpload").attr("disabled","disabled");
 	$("#deleteHeaderImage").hide();
 	$(".bgc").hide();
 	if($(".ui-button-text").text()=="保存关闭"){
 		$(".ui-button-text").eq(0).parent().remove();
 	}
});

var _imgUrl = $("#_imgUrl").val();
if(null!=_imgUrl && _imgUrl!=""){
	$("#headerImg").attr("src",_imgUrl+"?r="+Math.random());
	$(".shadow").show();
}
</script>