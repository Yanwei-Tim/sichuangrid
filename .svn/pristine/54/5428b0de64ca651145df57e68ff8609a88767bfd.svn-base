<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container container_24">
	<form action="${path}/partyBuildng/memberManage/editMember.action" id='maintainForm' method="post">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="member.partyOrgType" id="partyOrgType" value="${member.partyOrgType }" />
		<input type="hidden" name="member.id" id="memberId" value="${member.id }" />
		<input type="hidden" name="member.associateId" id="associateId" value="${member.associateId }" />
		<input id="provinceValue" type="hidden" name="" value="${member.province}" />
		<input id="cityValue" type="hidden" name="" value="${member.city}" />
		<input id="districtValue" type="hidden" name="" value="${member.district}" />
		<input id="_imgUrl" name="member.imgUrl" type="hidden" value="${member.imgUrl}"/>
		<input id="selectReportOrganization" name="member.reportOrganization.id" type="hidden" value="${member.reportOrganization.id}"/>
		<input id="selectReportOrganizationName" name="selectReportOrganizationName" type="hidden" value="${organization.orgName}"/>
		
		<div id="baseInfo">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">身份证号：</label>
			</div>
		    <div class="grid_7">
				<input type="text" name="member.idCardNo" id="idCardNo" maxlength="18" value='${member.idCardNo }' 
				class="form-txt {required:true,idCard:true,exsistedIdCard:true,messages:{required:'请输入身份证号码',idCard:'请输入一个合法的身份证号码',exsistedIdCard:'该身份证已经存在，请重新输入'}}"/>	    
		    </div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">姓名：</label>
			</div>
		    <div class="grid_8">
				<input type="text" name="member.name" id="name" maxlength="20" value='${member.name }' 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'/>	    
		    </div>
		    <div class="clearLine">&nbsp;</div>
		    <div class="grid_4 lable-right">
		    	<label class="form-lbl">出生日期：</label>
		    </div>
		    <div class="grid_7" id="birthdayDiv">
				<input type="text" name="member.birthday" id="birthday" class="form-txt" maxlength="32" readonly value='<fmt:formatDate value="${member.birthday}" type="date" pattern="yyyy-MM-dd" />' />	    
			</div>
		    <div class="grid_4 lable-right">
		    	<em class="form-req">*</em>
		    	<label class="form-lbl">性别：</label>
		    </div>
		    <div class="grid_8">
				<select name="member.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${member.gender.id}" />
				</select>
			</div>
			<div class="clearLine">&nbsp;</div>
			<div  class="grid_4 lable-right">
	  			<label class="form-lbl">民族：</label>
	  		</div>
			<div class="grid_7">
			   <select name="member.nation.id" id="nation" class='form-txt' >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${member.nation.id}" />
				</select>
	   		</div>
	   		<div  class="grid_4 lable-right">
	  			<label class="form-lbl">文化程度：</label>
	  		</div>
			<div class="grid_8">
			   <select name="member.schooling.id" id="schooling" class='form-txt'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${member.schooling.id}" />
				</select>
	   		</div>
	   		<div class="clearLine">&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">学位：</label>
			</div>
		    <div class="grid_7">
				<input type="text" name="member.degree" id="degree" class="form-txt"  maxlength="20" value='${member.degree }'/>	    
		    </div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">专业技术职务：</label>
			</div>
		    <div class="grid_8">
				<input type="text" name="member.specialPosition" id='specialPosition' class="form-txt"  maxlength="20" value='${member.specialPosition }'/>	    
		    </div>
		    <div class="clearLine">&nbsp;</div>
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">现居地址：</label>
	   		</div>
	   		<div class="grid_19">
	   			<input type="text" name="member.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${member.nativePlaceAddress }"  style="width: 99%"
	   			 class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'现居地址不能输入特殊字符'}}"
				/>
			</div>
	   		<div class="clearLine">&nbsp;</div>
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">户籍地：</label>
	   		</div>
	   		<div class="grid_5">
				<select name="member.province" id="province1" class='form-txt'></select>
	  		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">省</label>
	   		</div>
	   		<div class="grid_5">
				<select name="member.city" id="city1" class='form-txt'></select>
	 		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">市</label>
	   		</div>
	   		<div class="grid_5">
				<select name="member.district" id="district1" class='form-txt'></select>
	  		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">县</label>
	   		</div>
			<div class='clearLine'>&nbsp;</div>
		    <div class="grid_4 lable-right">
		    	<label class="form-lbl">联系手机：</label>
		    </div>
		    <div class="grid_7">
				<input type="text" name="member.mobileNumber" id="mobileNumber"  maxlength="11"  value='${member.mobileNumber }' title="请输入11位以1开头的联系手机  例如：13988888888" 
					class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />	    
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">固定电话：</label>
			</div>
		    <div class="grid_8">
		    	<input type="text" name="member.telephone" id="telephone"  maxlength="13"  value="${member.telephone}"
					class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888" />
		    </div>
		    <div class='clearLine'>&nbsp;</div>
	   		<div  class="grid_4 lable-right">
	  			<label class="form-lbl">所属部门：</label>
	  		</div>
			<div class="grid_7">
			   <select name="member.belongOrg.id" id="belongOrg" class='form-txt'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" defaultValue="${member.belongOrg.id}" />
				</select>
	   		</div>
	   	</div>
	</form>
</div>

<script type="text/javascript">
$(".form-txt").attr("disabled","disabled");
	$(function(){
	$("#maintainForm input[type=checkbox]").attr("disabled","disabled");
	$("#fileToUpload").attr("disabled","disabled");
	$("#deleteHeaderImage").css("display","none");
	$(".shadow").hide();
	//$(".ui-button-text").html("关闭");
	//$(".ui-button-text").click(function(){
		//$("#memberDialog").dialog("close");
	//});
	if($(".ui-button-text").text()=="关闭保存关闭"){
		$(".ui-button-text").eq(1).parent().remove();
	}
	threeSelect({province:'province1',
		city:'city1',
		district:'district1',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});
});
</script>