<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="commonPopulation" class="container container_24">
	<form id="maintainForm" method="post" action="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(actionName)"/>" >
		<pop:token />
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
		<input id="mode" type="hidden" name="mode" value="${mode}" />		 
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="populationOrgInternalCode" type="hidden" name="population.orgInternalCode" value="${population.orgInternalCode }" />
		<input id="provinceValue"	type="hidden" name="" value="${population.province}" />
		<input id="cityValue"	type="hidden" name="" value="${population.city}" />
		<input id="districtValue"	type="hidden" name="" value="${population.district}" />
		<input type="hidden" id="actualPopulationType" name="population.actualPopulationType" value="${population.actualPopulationType}"/>
		<input type="hidden" id="attentionPopulationType" name="population.attentionPopulationType" value="${population.attentionPopulationType}"/>
		<input type="hidden" id="addressInfoId" name="population.addressInfoId" value="${population.addressInfoId}"/>
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<s:if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
			<input type="hidden" id="caseReason" name="population.caseReason" value="${population.caseReason}"/>
		</s:if>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
   			<label class="form-lb1">所属网格：</label>
   		</div>
   		<div class="grid_20">
   			<input type="text"  id="commonPopulationOrgName"  name="population.organization.orgName"  readonly value="${population.organization.orgName}" style="width: 99%"	class="form-txt" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">身份证号码：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="population.idCardNo" id="idCardNo" maxlength="18" value="${population.idCardNo}" style="width: 99%"
   				<s:if test='!"add".equals(mode)'><pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag></s:if>
   				<s:if test='"edit".equals(mode)'><pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag></s:if>
   				<s:if test='"edit".equals(mode)'><pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">readonly</pop:GlobalSettingTag></s:if>
   				<s:if test="population.id!=null">readonly</s:if> class="form-txt {required:true,idCard:true,exsistedIdCard:true,<s:if test="'rectificativePersonDialog'.equals(#parameters.dailogName[0])">exsistedPositiveInfoIdCard:true,</s:if><s:if test="'positiveInfoDialog'.equals(#parameters.dailogName[0])">exsistedRectificativePersonIdCard:true,</s:if><s:if test="'idleYouthDialog'.equals(#parameters.dailogName[0])">isYouth:true,</s:if><s:if test="'elderlyPeopleDialog'.equals(#parameters.dailogName[0])">isElderly:true,</s:if><s:if test="'nurturesWomenDialog'.equals(#parameters.dailogName[0])">isNurturesWomen:true,fillGenderByIdCardNoForNurturesWomen:true,</s:if> messages:{required:'请输入身份证号码',exsistedIdCard:'该身份证号码已经存在，请重新输入',idCard:'请输入一个合法的身份证号码'<s:if test="'rectificativePersonDialog'.equals(#parameters.dailogName[0])">,exsistedPositiveInfoIdCard:'该人员已存在于刑事解教人员中'</s:if><s:if test="'positiveInfoDialog'.equals(#parameters.dailogName[0])">,exsistedRectificativePersonIdCard:'该人员已存在于社区矫正人员中'</s:if><s:if test="'idleYouthDialog'.equals(#parameters.dailogName[0])">,isYouth:'青少年年龄应在0~35之间'</s:if><s:if test="'elderlyPeopleDialog'.equals(#parameters.dailogName[0])">,isElderly:'老年人年龄应在60岁以上'</s:if><s:if test="'nurturesWomenDialog'.equals(#parameters.dailogName[0])">,isNurturesWomen:'育龄妇女年龄应在15-49岁之间',fillGenderByIdCardNoForNurturesWomen:'育龄妇女性别应为女性!'</s:if>}}"/>
   		</div>
   		<div class="grid_5 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">姓名：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.name" id="name" maxlength="20" value="${population.name}"
   			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>

   		<div class='clearLine'>&nbsp;</div>

		<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
	   			<input type="hidden" id="actualPersonType" name="actualPersonType" value='<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>' />
<!-- 	   		<div id="actualPersonTypeDiv" > -->
<!-- 		   		<div class="grid_4 lable-right"> -->
<!-- 		   			<em class="form-req">*</em> -->
<!-- 		   			<label class="form-lb1">实口类型：</label> -->
<!-- 		   		</div> -->
<!-- 		   		<div class="grid_8"> -->
<%-- 		   			<input type="radio" onclick="householdType()" class="form-btn  {actualPersonTypeNotNull:true,messages:{actualPersonTypeNotNull:'人员类型必须至少勾选一种类型'}}" name="actualPersonType" value='<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>' />&nbsp;&nbsp;户籍人口&nbsp;&nbsp;&nbsp;&nbsp; --%>
<%-- 		   			<input type="radio" onclick="floatType()" class="form-btn {actualPersonTypeNotNull:true,messages:{actualPersonTypeNotNull:'人员类型必须至少勾选一种类型'}}" name="actualPersonType" value='<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>' />&nbsp;&nbsp;流动人口 --%>
<!-- 		   		</div> -->
<!-- 		   		<div class='grid_12'>&nbsp;</div> -->
<!-- 	   		</div> -->
		</pop:GlobalSettingTag>
		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">性别：</label>
  		</div>
		<div class="grid_7">
		    <select name="" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
	    	 disabled="disabled">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" />
			</select>
			<input type="hidden" name="population.gender.id" id="populationGender" value="${population.gender.id}"/>
   		</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">曾用名/别名：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.usedName" id="usedName" maxlength="20" value="${population.usedName}"
   				class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("曾用名/别名至少需要输入{0}个字符"),maxlength:$.format("曾用名/别名最多需要输入{0}个字符")}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">联系手机：</label>
  		</div>
		<div class="grid_7">
		    <input type="text" name="population.mobileNumber" id="mobileNumber" maxlength="11" value="${population.mobileNumber }" title="请输入11位以1开头的联系手机  例如：13988888888"
		    	class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
		    />
   		</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">固定电话：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.telephone" id="telephone" maxlength="20"  value="${population.telephone }" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
   				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">出生日期：</label>
   		</div>
   		<div class="grid_7" id="birthdayDiv">
   			<input type="text" name="population.birthday" id="birthday" maxlength="32" readonly  value='<s:date name="population.birthday" format="yyyy-MM-dd" />'
   					class='form-txt'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
		
		<div class="grid_5 lable-right">
   			<label class="form-lb1">工作单位或就读学校：</label>
   		</div>
   		<div class="grid_8">
   			 <input type="text" name="population.workUnit" id="workUnit" maxlength="50" value="${population.workUnit }"
   			 class="form-txt "
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			 />
   		</div>
		
   		<div class='clearLine'>&nbsp;</div>
   		<div id="beforeInfo">
			
		</div>
		
		<!-- 优化显示方式，否则修改的时候无法默认选择 -->
		<!-- <div id="floatTypeInfo" style="display: none;"> -->
	<div  class="grid_4 lable-right">
	<em class="form-req">*</em>
  			<label class="form-lbl">政治面貌：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.politicalBackground.id" id="politicalBackground2" class='form-txt {required:true,messages:{required:"请选择政治面貌"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.politicalBackground.id}" />
			</select>
   		</div>
   		<div  class="grid_5 lable-right">
   		<em class="form-req">*</em>
  			<label class="form-lbl">文化程度：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.schooling.id" id="schooling2" class='form-txt {required:true,messages:{required:"请选择文化程度"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.schooling.id}" />
			</select>
   		</div>
   		 <div class="clearLine">&nbsp;</div>
		<div  class="grid_4 lable-right">
		<em class="form-req">*</em>
  			<label class="form-lbl">民族：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.nation.id" id="nation2" class='form-txt {required:true,messages:{required:"请选择民族"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" />
			</select>
   		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
   			<label class="form-lb1">婚姻状况：</label>
   		</div>
   		<div class="grid_8">
   			 <select name="population.maritalState.id" id="maritalState2" class='form-txt {required:true,messages:{required:"请选择婚姻状况"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
   			 >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.maritalState.id}" />
			</select>
   		</div>
<!-- </div> -->
		
		
   		 <div  class="grid_4 lable-right">
  			<label class="form-lbl">职业：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.career.id" id="career" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.career.id}" />
			</select>
   		</div>
   		<s:if test='!"savePositiveInfoBaseInfoForRectificative".equals(actionName)'>
   		<div id="populationDeathDiv" >
	   		<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" condition="notEq" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">
		   		<div class="grid_5 lable-right">
				</div>
				<div class="grid_6">
					<input type="checkbox" id="isDead" name="population.death" value="true"   <s:if test='true==population.death'>checked="checked"</s:if>
		   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
					/>
					<label class="form-check-text">是否死亡 </label>
				</div>
			</pop:GlobalSettingTag>
		</div>
		</s:if>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">身高：</label>
   		</div>
   		<div class="grid_6">
   			<input type="text" name="population.stature" id="stature" maxlength="3"  value="${population.stature }" title="请输入不大于300的正整数，如175"
   				class='form-txt {stature:true,messages:{stature:"请输入不大于300的正整数"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
   		<div class="grid_1">cm</div>
   		<div  class="grid_5 lable-right">
  			<label class="form-lbl">血型：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.bloodType.id" id="bloodType" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${population.bloodType.id}" />
			</select>
   		</div>
   		<div class="clearLine">&nbsp;</div>

   		<div class="grid_4 lable-right">
   			<label class="form-lb1">宗教信仰：</label>
   		</div>
   		<div class="grid_7">
   			<select name="population.faith.id" id="faith" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
   			>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${population.faith.id}" />
			</select>
   		</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">电子邮箱：</label>
   		</div>
   		<div class="grid_8">
   			<input type="text" name="population.email" id="email" maxlength="50"  value="${population.email}"
   				class='form-txt {email:true,messages:{email:"请输入一个合法的电子邮箱"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<s:if test='"householdStaffPopulationDialog".equals(#parameters.dailogName[0])'>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">户口类别：</label>
   		</div>
   		<div class="grid_7">
   			<select name="population.residenceType.id" id="residenceType" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
   			>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${population.residenceType.id}" />
			</select>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   	    </s:if>
   		<div class="grid_4 lable-right">
   			<em class="form-req" id="houseprovincecitydistrictid" >*</em>
   			<label class="form-lb1">户籍地：</label>
   		</div>
   		<div class="grid_7">
			<select name="population.province" id="province"
				class='form-txt {required:true,messages:{required:"请选择户籍地省"}}' <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>>
			</select>
			省
  		</div>
   		<div class="grid_7">
			<select name="population.city" id="city" class='form-txt {checkIsNeedCity:true,messages:{checkIsNeedCity:"请选择户籍地市"}}' <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>>
			</select>
			市
 		</div>
   		<div class="grid_6">
			<select name="population.district" id="district" class='form-txt {checkIsNeedDistrict:true,messages:{checkIsNeedDistrict:"请选择户籍地县"}}'<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>>
			</select>
			县
  		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">户籍地详址：</label>
   		</div>
   		<div class="grid_20">
   			<input type="text" name="population.nativePlaceAddress" id="nativePlaceAddress" maxlength="50" value="${population.nativePlaceAddress }" class="form-txt" style="width: 99%"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
			/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<%-- fateson add  户籍派出所地址  这里需要排除 6个对象 （老年人，残疾人，优抚，需要，失业，妇女，流动） --%>
		               
		<s:if test="!('floatingPopulationDialog'.equals(#parameters.dailogName[0]))">
	   		<div class="grid_4 lable-right" id="police">
	   			<label class="form-lb1">户籍派出所：</label>
	   		</div>
	   		<div class="grid_20" id="police_h">
	   			<input type="text" name="population.nativePoliceStation" id="nativePoliceStation" maxlength="50" value="${population.nativePoliceStation }" class="form-txt" style="width: 99%"
	   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
				/>
			</div>
		</s:if>
		
		<div class='clearLine'>&nbsp;</div>
			<jsp:include page="/baseinfo/commonPopulation/commonSearchAddress.jsp"/>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">临时居所：</label>
   		</div>
   		<div class="grid_20">
   			<input type="text" name="population.otherAddress" id="populationOtherAddress"  maxlength="50" value="${population.otherAddress }" class="form-txt" style="width: 99%"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_20" style="height: 120px">
   			<textarea rows="4" name="population.remark" id="remark" style="width: 99%" maxlength=300
   				class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
   			>${population.remark }</textarea>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
	<div id="populationDialogJsp"></div>
</div>
<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
<jsp:include page="/baseinfo/commonPopulation/businessInfoDependActualInfo/canMaintainActualInfo.jsp"></jsp:include>
</pop:GlobalSettingTag>
<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">
<jsp:include page="/baseinfo/commonPopulation/businessInfoDependActualInfo/canNotMaintainActualInfo.jsp"></jsp:include>
</pop:GlobalSettingTag>
<script type="text/javascript">
if($("#mode").val()=="add"){
	$("#populationDeathDiv").css("display","none");
	$("#isDead").attr("checked",false);
}
function getBirthDayTextFromIdCard(idCard){
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(6,14);
		if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
			return "";
		}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
			return "";
		}else{
			return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
		}
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(6,12);
		if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
			return "";
		}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
			return "";
		}else{
			return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
		}
	}
	return "";
}

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='population.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

function manageCommonPopulation(responseData){
	//省市县的选中
	threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:responseData.province,
		cityValue:responseData.city,
		districtValue:responseData.district
	});
}
jQuery.validator.addMethod("checkIsNeedCity", function(value, element){
	var flag = false;
	if($('#province').val()==""){return false;}
	if($('#city').val()!=""){return true;}
	$.ajax({
		type:'post',
		dataType:'json',
		async: false,
		url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentName.action',
		data:{"parentName":$('#province').val()},
		success:function(data){
			var count = data.split("\,");
			if(count.length<=1){
				flag = true;
			}
		}
	});
	return flag;
});

jQuery.validator.addMethod("checkIsNeedDistrict", function(value, element){
	var flag = false;
	if($('#province').val()==""){return false;}
	if($('#district').val()!=""){return true;}
	$.ajax({
		type:'post',
		dataType:'json',
		async: false,
		url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentNameAndPId.action',
		data:{"parentName":$('#city').val()},
		success:function(data){
			var count = data.split("\,");
			if(count.length<=1){
				flag = true;
			}
		}
	});
	return flag;
});

function ajaxForCommonPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageCommonPopulation(responseData);
		}
	});
}

function getCommonPopulation(idCardNo){
	if( idCardNo.length != null && idCardNo.length == 18){
		ajaxForCommonPopulation(idCardNo);
	}else if( idCardNo.length != null && idCardNo.length == 15){
		ajaxForCommonPopulation(idCardNo);
	}
}

function idCardChanged(){
	var text=$('#idCardNo').val();
	if(isUnsettledPopulation(text)){
		$('#idCardNo').val("");
		return;
	}
	
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}

function isUnsettledPopulation(idCardNo){
	var isTure;
	$.ajax({
		async: false,
		url: "${path }/baseinfo/unsettledPopulationManage/getUnsettledPopulationByIdCardNo.action",
		data:{
			"unsettledPopulation.organization.id" : $("#currentOrgId").val(),
			"unsettledPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			if(responseData!=null){
				isTure = true;
				$.messageBox({
					message:"未落户人口不能新增为重点人员！",
					level: "error"
			 	});
			}else{
				isTure = false;
			}
		}
	});
	return isTure;
}

function proccessAddressBySyncData(data){
	if(data.baseInfoId == null){
		return;
	}
	if(data.isHaveHouse == true){
		$("#haveNotHouse").hide();
		$("#haveHouse").show();
		$("input[name='population.houseId']", $("#maintainForm")).val(data.houseId);
		$("input[name='population.noHouseReason']", $("#maintainForm")).val("");
		$("select[name='population.isHaveHouse']", $("#maintainForm")).val("true");
		$("#currentAddress").val(data.currentAddress);
	}else if(data.isHaveHouse == false){
		$("#haveNotHouse").show();
		$("#haveHouse").hide();
		$("input[name='population.houseId']", $("#maintainForm")).val("");
		$("input[name='population.noHouseReason']", $("#maintainForm")).val(data.noHouseReason);
		$("select[name='population.isHaveHouse']", $("#maintainForm")).val("false");
		populationHasHouseInfoChanged();
	}else{
		//fateson add 房屋地址为未知这时候需要修改地址的名字 (BUG单 #21299系统中所有业务人员时若新增了房屋为未知的人员后修改时回填的房屋为无住房)
		//start
	    $("#populationHasHouseInfo").attr("name","aaaaaaaaaaaaaaaa");
		//end
		$("#haveHouse").hide();
		$("#haveNotHouse").hide();
		$("input[name='population.houseId']", $("#maintainform")).val("");
		$("input[name='population.nohousereason']", $("#maintainform")).val("");
		$("#populationHasHouseInfo").val("null");
		populationHasHouseInfoChanged();
	}
}

function clearBaseInfoMessage(){
	$("#name").val("");
	$("#gender").val("");
	$("#populationGender").val("");
	$("#usedName").val("");
	$("#mobileNumber").val("");
	$("#telephone").val("");
	$("#email").val("");
	$("#workUnit").val("");
	$("#stature").val("");
	$("#nativePoliceStation").val("");
	$("#nativePlaceAddress").val("");
	$("#nation2").val("");
	$("#politicalBackground2").val("");
	$("#maritalState2").val("");
	$("#faith").val("");
	$("#career").val("");
	$("#schooling2").val("");
	$("#bloodType").val("");
	$("#_imgUrl").val("");
	$("#populationOtherAddress").val("");
	$("#currentAddress").val("");
	$("#noHouseReason").val("");
	$("#addressInfoId").val("");
	$("#remark").val("");
	$("#headerImg").attr("src","${resource_path}"+"/resource/images/head.png");
	$(".shadow").hide();
}

function searchAndSynData(idCardNo, orgId) {
	$.ajax({
		url:"${path}/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNoIncludeLogout.action",
		async:false,
		data:{
			"orgId":orgId,
			"idCardNo":idCardNo
        },
        success:function(data){
        	householdType();
        	if(data == null) {
//         		<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT" condition="notEq">
//         		notFindPopulationByIdCardNoAndOrgId();
//         		</pop:GlobalSettingTag>
        		idCardChanged();
        		$("#populationDeathDiv").css("display","none");
				$("#isDead").attr("checked",false);
        		clearBaseInfoMessage();

        		<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
        		clearSearchActualData();
   				</pop:GlobalSettingTag>
        		return;
        	}
        	
        	<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
    			afterSearchActualData(data);
   			</pop:GlobalSettingTag>
   			
//         	if(data.baseInfoId > 0) {
//         		$("#actualPersonTypeDiv").hide();
//         	} else {
//         		$("#actualPersonTypeDiv").show();
//         	}
        	if(data.logOut == '1'){
        		if(data.actualPopulationType=='householdStaff'){
	        		$.messageBox({
						message:"该身份证在此网格的户籍人口中已被注销",
						level: "error"
				 	});
        		}else if(data.actualPopulationType=='floatingPopulation'){
        			$.messageBox({
						message:"该身份证在此网格的流动人口中已被注销",
						level: "error"
				 	});
        		}
        		$('#idCardNo').val("");
        		return;
        	}
        	<s:if test='"nurturesWomenDialog".equals(#parameters.dailogName[0])'>
        		if(data.gender!=null&&data.gender.displayName!='女'){
        			$.messageBox({
						message:"该身份证为不为女性，无法新增为育龄妇女！",
						level: "error"
				 	});
        			$('#idCardNo').val("");
        			return;
        		}
        	</s:if>
        	if(data.death == true ||data.death == 'true'){
        		$.confirm({
        			title:"提示",
        			message:"该身份证在其他网格已被标记为死亡，若取消死亡，请去掉死亡勾选。",
        			okFunc:function(){
   	    			   $("#populationDeathDiv").css("display","block");
   	    			   $("#isDead").attr("checked",true);
        			},
        			cancelFunc: function(){
        				$('#idCardNo').val("");
        				$("#populationDeathDiv").css("display","none");
        				$("#isDead").attr("checked",false);
        				clearBaseInfoMessage();
                		return;
        			}
        		});
        	}
        	 
        	proccessAddressBySyncData(data);
			$.each(data,function(i,n){
				if(n.id){
					$("select[name='population."+i+".id']:visible").val(n.id);
				}else{
					$("input[name='population."+i+"']:visible").val(n);
				}
			})
            //fateson add 户籍地两个字段不能获取，且户籍地市、县两字段下拉选项缺失
			//alert(data.province+"----"+data.city+"----"+data.district);
			//$("select[name='population.province']:visible").val(data.province);
			//$("select[name='population.city']:visible").val(data.city);
			//$("select[name='population.district']:visible").val(data.district);
			threeSelect({
				province:'province',
				city:'city',
				district:'district',
				provinceValue:data.province,
				cityValue:data.city,
				districtValue:data.district
			});
			 
			
			$("#remark").val(data.remark);
			$("input[name='population.actualPopulationType']", $("#maintainForm")).val(data.actualPopulationType);
			$("input[name='population.addressInfoId']", $("#maintainForm")).val(data.addressInfoId);
   			//图片同步
   			if(data.imgUrl!=null && data.imgUrl!=""){
   				$("#headerImg").attr("src",data.imgUrl+"?r="+Math.random());
   				$("#_imgUrl").val(data.imgUrl);
   				$(".shadow").show();
   			}
		}
	})
}
function householdType(){
	$("#actualPopulationType").val($("#actualPersonType").val());
	//$("#beforeInfo").load("${path}/baseinfo/commonPopulation/floatingPersonTypeDiv.jsp");
	$("#floatTypeInfo").show();
	$("#police_h").show();
	$("#police").show();
}
function floatType(){
	//$("#beforeInfo").load("${path}/baseinfo/commonPopulation/floatingPersonTypeDiv.jsp");
	$("#floatTypeInfo").show();
	$("#police_h").hide();
	$("#police").hide();
}
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	
	
	<s:if test='"toPositiveInfoDialog".equals(#attr.dailogName)'>
		$('#idCardNo').attr("readonly","readonly");
		//fateson add  start
		$('#idCardNo').attr("class","form-txt");
		//end
	</s:if>

	threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});

	jQuery.validator.addMethod("currentAddress", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			var livingHouse=$("#currentAddress").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
	  	return true;
	});

	jQuery.validator.addMethod("community", function(value, element){
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			var livingHouse=$("#community").val();
			return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
		}
		return true;
	});

	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:$('#ajaxUrl').val(),
		   	data:{
				"population.idCardNo":$('#idCardNo').val(),
				"organizationId":$('#populationOrganizationId').val(),
				"mode":$('#mode').val(),
				"population.id":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	
	
	//新增社区矫正人员和刑释人员身份证号缺少互斥判断
	<s:if test="'positiveInfoDialog'.equals(#parameters.dailogName[0])">
		jQuery.validator.addMethod("exsistedRectificativePersonIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/rectificativePersonManage/hasDuplicateRectificativeForPositive.action",
		   	data:{
				"population.idCardNo":$('#idCardNo').val(),
				"organizationId":$('#populationOrganizationId').val(),
				"mode":$('#mode').val(),
				"population.id":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	</s:if>
	
	
	//新增社区矫正人员和刑释人员身份证号缺少互斥判断
	<s:if test="'rectificativePersonDialog'.equals(#parameters.dailogName[0])">
		jQuery.validator.addMethod("exsistedPositiveInfoIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/positiveInfoManage/hasDuplicatePositiveInfoForRectificative.action",
		   	data:{
				"population.idCardNo":$('#idCardNo').val(),
				"organizationId":$('#populationOrganizationId').val(),
				"mode":$('#mode').val(),
				"population.id":$('#populationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	</s:if>
	
	jQuery.validator.addMethod("isElderly", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var elementDate = year;
		var dat1 = d.getFullYear()-60;//当前年份
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1<year) {
			return false;
		}
		 if(dat1 == year){
			if((nowMon+1) < month){
				return false;
			}
			if((nowMon+1) == month){
				if(nowDay<day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		return true;
	});
	
	jQuery.validator.addMethod("isNurturesWomen", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var d = new Date();
		var nowYear = d.getFullYear();//得到系统时间的年份、月份、日期
		var nowMonth = d.getMonth();
		var nowDay = d.getDate();
		
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var startData = nowYear-15;
		var endData = nowYear-49;
		if (startData < year || endData > year) {
			return false;
		}
		if(endData == year){
			if((nowMonth+1) > month){
				return false;
			}
			if((nowMonth+1) == month){
				if(nowDay > day){
					return false;
				}
				if(nowDay == day){
					return false;
				}
			}
		}
		if(startData == year){
			if((nowMonth+1) < month){
				return false;
			}
			if((nowMonth+1) == month){
				if(nowDay < day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		return true;
	});
	
	jQuery.validator.addMethod("isYouth", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var dat1 = d.getFullYear()-35;//当前年份
		var dat2 = d.getFullYear();
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1>year || dat2<year){
			 return false;
		}
		if(dat1==year){
			if((nowMon+1) > month){
				return false;
			}
			if(eval(nowMon+1) == month){
				if(nowDay>day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		if(dat2 == year){
			if(eval(nowMon+1) < month){
				return false;
			}
			if(eval(nowMon+1) == month){
				if(nowDay<day){
					return false;
				}
				if(nowDay==day){
					return false;
				}
			}
		}
		return true;
	});
	
	jQuery.validator.addMethod("fillGenderByIdCardNoForNurturesWomen", function(value, element){
		var flag=false;
		var idCardNo = $('#idCardNo').val();
		if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
			flag=false;
		}
		if(idCardNo.length!=15 && idCardNo.length!=18){
			flag=false;
		}
		 
		if (15 == idCardNo.length) { //15位身份证号码
			if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14)){
				 flag=false;
			} else{
				flag=true;
			}
		 }
		if (18 == idCardNo.length) { //18位身份证号码
			if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16)){
				flag=false;
			}else{
				flag=true;
			}
		}
		return flag;
	});

	jQuery.validator.addMethod("actualPersonTypeNotNull", function(value, element){
		var flag=false;
		$("input[name='actualPersonType']").each(function(i){
			 if ($(this).attr("checked")=="checked") {
				 flag=true;
			 }
		});
		return flag;
	});
	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$("select",$("#maintainForm")).removeAttr("disabled");
         	$(form).ajaxSubmit({
         		async : false,
         		success : function(data){
					if(!data.id){
	               	 	$.messageBox({
							message:data,
							level: "error"
					 	});
                		return;
                	}
                	$("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.encryptId,data);
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		ignore:':hidden',
		rules:{
		},
		messages:{
		}
	});
	
	var _isNeedIdCardNoBlur = true;
	<s:if test='"edit".equals(mode)'><pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@SYNC_ACTUAL_POPULATION">
	_isNeedIdCardNoBlur = false;
	householdType();/*
	if('${population.nativePoliceStation}' == ''){
		floatType();
	}else{
		householdType();
	}*/
	</pop:GlobalSettingTag></s:if>

	$("#idCardNo").blur(function(){
		if(_isNeedIdCardNoBlur && $(this).attr("createMsg")=="false") {
			searchAndSynData($(this).val(), getCurrentOrgId());
		}
		fillGenderByIdCardNo($("#idCardNo").val(),"gender","populationGender",true);
	});

	<s:if test='"add".equals(mode)'>
    $("#populationOrganizationId").val($("#currentOrgId").val());
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#commonPopulationOrgName").val(responseData);
		}
	});
	</s:if>

	<s:if test='"nurturesWomenDialog".equals(#parameters.dailogName[0])'>
	 $("#gender option").each(function() {
		if($(this).text() == '女') {
	    	$(this).attr("selected", "selected");
	    	$("#populationGender").val($(this).val())
	    }else{
			$(this).remove();
	    }
	});
	   
		//备注修改为600
		$("#remark").attr("class","form-txt {maxlength:600,messages:{maxlength:'备注最多需要输入600个字符'}}");
	</s:if>
	
	
	//如果修改的时候 房屋为未知  那么也修改名字
	<s:if test='"edit".equals(mode)'>
		var  populationHasHouseInfoval=  $("#populationHasHouseInfo").val();
		if(populationHasHouseInfoval=='null'){
			 $("#populationHasHouseInfo").attr("name","aaaaaaaaaaaaaaaa");
		} 
	</s:if>
	
	
	
	 //隐藏星号 省 区 县 非必填写
	 //3.5 中 需要必填写
	/* $("#houseprovincecitydistrictid").hide();
	$("#province").attr("class","form-txt ");
	$("#city").attr("class","form-txt ");
	$("#district").attr("class","form-txt "); */
	
	jQuery.validator.addMethod("exculdeParticalChar", validatorSpecialWord);
	
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！-]");
		return this.optional(element)||!pattern.test(value) ; 
	}
});
</script>