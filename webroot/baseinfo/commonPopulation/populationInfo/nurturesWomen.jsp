<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="manProvinceValue"	type="hidden" name="" value="${population.manProvince}" />
<input id="manCityValue"	type="hidden" name="" value="${population.manCity}" />
<input id="manDistrictValue"	type="hidden" name="" value="${population.manDistrict}" />

	<span style="font-weight: bold; margin-left:20px; ">育龄妇女</span>
	<div class='clearLine'>&nbsp;</div>
<input id="businessHouse" type="hidden" value=""/>
<input id="other" type="hidden" value=""/>

<div class="grid_4 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_7">
	<select name="population.attentionExtent.id" id="attentionExtent" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">初婚时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.firstMarriageTime" id="firstMarriageTime" maxlength="32" readonly value='<s:date name="population.firstMarriageTime" format="yyyy-MM-dd" />'class="form-txt {validateFirstMarriageTime:true,messages:{validateFirstMarriageTime:'初婚时间不能小于出生日期'}}" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lb1">离婚时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.marriageRegistrationTime" id="marriageRegistrationTime" maxlength="32" readonly  value='<s:date name="population.marriageRegistrationTime" format="yyyy-MM-dd" />'class="form-txt {validateMarriageRegistrationTime:true,messages:{validateMarriageRegistrationTime:'离婚时间不能小于初婚时间'}}" />
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">再婚时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.marriageOrDivorceTime" id="marriageOrDivorceTime" maxlength="32" readonly value='<s:date name="population.marriageOrDivorceTime" format="yyyy-MM-dd" />'class="form-txt {validateMarriageOrDivorceTime:true,messages:{validateMarriageOrDivorceTime:'最近再婚时间不能小于离婚时间'}}" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">男孩数：</label>
</div>
<div class="grid_1">
	<input type="text" name="population.boyNumber" id="boyNumber" maxlength="1" value="${population.boyNumber}" class="form-txt {isDigit:true,messages:{isDigit:'请输入小于10的正整数'}}"/>
</div>
<div class="grid_1">
  <label class="form-lbl">&nbsp;人</label>
</div>
<div class="grid_3 lable-right">
	<label class="form-lb1">女孩数：</label>
</div>
<div class="grid_1">
	<input type="text" name="population.girlNumber" id="girlNumber" maxlength="1" value="${population.girlNumber}" class="form-txt {isDigit:true,messages:{isDigit:'请输入小于10的正整数'}}" />
</div>
<div class="grid_1">
 		  <label class="form-lbl">&nbsp;人</label>
</div>
<div  class="grid_6 lable-right">
	<label class="form-lb1">独生子女证号：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.singleChildCardno" id="singleChildCardno"  maxlength="50" value="${population.singleChildCardno}"  class="form-txt"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lb1">办理时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.singleChildCDIssueTime" id="singleChildCDIssueTime"  readonly  value='<s:date name="population.singleChildCDIssueTime" format="yyyy-MM-dd" />'class="form-txt {validateTimeBigThanBirthday:true,messages:{validateTimeBigThanBirthday:'办理时间不能小于出生日期'}}" />
</div>
<div  class="grid_6 lable-right">
 	<label class="form-lb1">夫妻独生子女情况：</label>
</div>
<div class="grid_7">
    <select name="population.onechildOfCouple.id" id="onechildOfCouple" class="form-txt"   >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION" defaultValue="${population.onechildOfCouple.id}" />
	</select>
</div>
<div  class="grid_4 lable-right">
	<label class="form-lb1">领证情况：</label>
</div>
<div class="grid_7">
    <select name="population.licenseSituation.id" id="licenseSituation" class="form-txt"  >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION" defaultValue="${population.licenseSituation.id}" />
	</select>
</div>

<div class="grid_6 lable-right">
	<label class="form-lb1">生育服务证号：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.fertilityServicesNo" id="fertilityServicesNo"  maxlength="50" value="${population.fertilityServicesNo}"  class="form-txt"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lb1">领证时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.licenseTime" id="licenseTime" maxlength="32" readonly  value='<s:date name="population.licenseTime" format="yyyy-MM-dd" />'class="form-txt {validateLicenseTime:true,messages:{validateLicenseTime:'领证时间不能小于出生日期'}}" />
</div>

<div class="grid_6 lable-right">
	<label class="form-lb1">发证单位：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.certifiedUnit" id="certifiedUnit" maxlength="50" value="${population.certifiedUnit}"   class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4"></div>
<div class="grid_8">
	<input id="isUnmarriedPregnant" type="checkbox" name="population.isUnmarriedPregnant" value="1" class="dialogtip"
	<s:if test="population.isUnmarriedPregnant==1" >checked="checked"</s:if>
	<s:if test='"view".equals(mode)'>disabled</s:if> />
	<label class="form-check-text">是否未婚先孕</label>
</div>
<div class="grid_6"></div>
<div class="grid_6">
	<input id="isLevied" type="checkbox" name="population.isLevied" value="1" class="dialogtip"
	<s:if test="population.isLevied==1" >checked="checked"</s:if>
	<s:if test='"view".equals(mode)'>disabled</s:if> />
	<label class="form-check-text">是否征收</label>
</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">避孕方法：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.contraceptiveMethod" id="contraceptiveMethod" maxlength="50" value="${population.contraceptiveMethod}"   class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}"/>
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">避孕起始时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.contraceptiveTime" id="contraceptiveTime" maxlength="32" readonly value='<s:date name="population.contraceptiveTime" format="yyyy-MM-dd" />'class="form-txt {validateContraceptiveTime:true,messages:{validateContraceptiveTime:'避孕起始时间不能小于出生日期'}}" />
</div>

<div class='clearLine'>&nbsp;</div>

<div class="grid_4">
</div>
<div class="grid_7">
	<input  type="checkbox" id="isMaternityCard" name="population.isMaternityCard" value="1" class="dialogtip"
		<s:if test="population.isMaternityCard==1" >checked="checked"</s:if>
		<s:if test='"view".equals(mode)'>disabled</s:if> />
	<label class="form-check-text">是否有婚育证 </label>
</div>
<div id="moreRequirement" class="accdContent">
	<div class="grid_6 lable-right">
		<label class="form-lb1">发放单位：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardUnit" id="maternityCardUnit" maxlength="50" value="${population.maternityCardUnit}"   class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}"/>
	</div>
<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">证编号：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardNo" id="maternityCardNo" maxlength="30" value="${population.maternityCardNo}"   class="form-txt"/>
	</div>
	<div class="grid_6 lable-right">
		<label class="form-lb1">发放时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardIssueTime" id="maternityCardIssueTime"  readonly  value='<s:date name="population.maternityCardIssueTime" format="yyyy-MM-dd" />'   class="form-txt {validateTimeBigThanBirthday:true,messages:{validateTimeBigThanBirthday:'领证时间不能小于出生日期'}}" />
	</div>
<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">有效期至：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardValidityTime" id="maternityCardValidityTime" readonly value='<s:date name="population.maternityCardValidityTime" format="yyyy-MM-dd" />'   class="form-txt {validateTimeBigThanBirthday:true,messages:{validateTimeBigThanBirthday:'有效期截至时间不能小于出生日期'}}" />
	</div>
	<div class="grid_6 lable-right">
		<label class="form-lb1">查验时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardCheckTime" id="maternityCardCheckTime"  readonly value='<s:date name="population.maternityCardCheckTime" format="yyyy-MM-dd" />'  class="form-txt {validateTimeBigThanBirthday:true,messages:{validateTimeBigThanBirthday:'查验时间不能小于出生日期'}}" />
	</div>
<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">查验情况：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.maternityCardRemark" id="maternityCardRemark"  value="${population.maternityCardRemark}"    maxlength="50" class='form-txt {exculdeParticalChar:true,maxlength:50,messages:{exculdeParticalChar:"不能输入非法字符",maxlength:$.format("最多输入50个字符")}}'/>
	</div>
</div>

		<div class='clearLine'>&nbsp;</div>

		<div class="grid_21 lable-right">
	<hr align="right" />
</div>
<h1 style="text-align:left;">男方信息</h1>

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lb1">身份证号码：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.manIdcardNo" id="manIdcardNo" maxlength="18" value="${population.manIdcardNo}"   class="form-txt {idCard:true,manIdcardNoIsMan:true,messages:{idCard:'请输入正确的身份证号码',manIdcardNoIsMan:'请输入性别为男的身份证'}}"/>
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">姓名：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.manName" id="manName" maxlength="20" value="${population.manName}"  class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'姓名至少需要输入{0}个字符',maxlength:'姓名最多需要输入{0}个字符'}}" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">婚姻状况：</label>
</div>
<div class="grid_7">
	 <select name="population.manMaritalState.id" id="manMaritalState" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.manMaritalState.id}" />
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">初婚时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.manFirstMarriageTime" id="manFirstMarriageTime" maxlength="32" readonly value='<s:date name="population.manFirstMarriageTime" format="yyyy-MM-dd" />'class="form-txt {validateManFirstMarriageTime:true,messages:{validateManFirstMarriageTime:'初婚时间不能小于出生日期'}}" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">联系手机：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.manMobileNumber" id="manMobileNumber" maxlength="11"  value="${population.manMobileNumber}"   class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">固定电话：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.manTelephone" id="manTelephone" maxlength="20"  value="${population.manTelephone }"   class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">出生日期：</label>
</div>
<div class="grid_7" id="manbirthdayDiv">
	<input type="text" name="population.manBirthday" id="manBirthday" maxlength="32" readonly value='<s:date name="population.manBirthday" format="yyyy-MM-dd" />'class="form-txt" />
</div>
<div  class="grid_6 lable-right">
	<label class="form-lbl">民族：</label>
</div>
<div class="grid_7">
   <select name="population.manNation.id" id="manNation" class="form-txt" >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.manNation.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>

<div  class="grid_4 lable-right">
	<label class="form-lbl">政治面貌：</label>
</div>
<div class="grid_7">
   <select name="population.manPoliticalBackground.id" id="manPoliticalBackground" class="form-txt" >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.manPoliticalBackground.id}" />
	</select>
</div>
<div  class="grid_6 lable-right">
	<label class="form-lbl">文化程度：</label>
</div>
<div class="grid_7">
   <select name="population.manSchooling.id" id="manSchooling" class="form-txt" >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.manSchooling.id}" />
	</select>
</div>
<div class="clearLine">&nbsp;</div>

<div  class="grid_4 lable-right">
	<label class="form-lbl">职业：</label>
</div>
<div class="grid_7">
    <select name="population.manCareer.id" id="manCareer" class="form-txt" >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.manCareer.id}" />
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lb1">工作单位或就读学校：</label>
</div>
<div class="grid_7">
	 <input type="text" name="population.manWorkUnit" id="manWorkUnit" maxlength="50" value="${population.manWorkUnit }"  class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" />
</div>
<div class="clearLine">&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">户籍地：</label>
</div>
<div class="grid_6">
	<select name="population.manProvince" id="manProvince" class="form-txt" >
	</select>
</div>
<div class="grid_1">
  <label class="form-lbl">省</label>
</div>
<div class="grid_6">
	<select name="population.manCity" id="manCity" class="form-txt" >
	</select>
</div>
<div class="grid_1">
  <label class="form-lbl">市</label>
</div>
<div class="grid_5">
	<select name="population.manDistrict" id="manDistrict" class="form-txt" >
	</select>
</div>
<div class="grid_1">
  <label class="form-lbl">县</label>
</div>
<div class="clearLine">&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">户籍地详址：</label>
</div>
<div class="grid_20">
	<input type="text" name="population.manNativeplaceAddress" id="manNativeplaceAddress" maxlength="50" value="${population.manNativeplaceAddress }" class="form-txt" style="width: 99%"/>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">常住地址：</label>
</div>
<div class="grid_3">
	<select name="population.manCurrentAddressType.id" id="manCurrentAddressType" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" defaultValue="${population.manCurrentAddressType.id}" notNull="true" />
	</select>
</div>
<div id="building" >
 	<div class="grid_3">
 		<input type="text"  id="manCommunity"  name="population.manCommunity"  maxlength="20"  value="${population.manCommunity}" style="width: 93%"	class="form-txt" />
	</div>
	<div class="grid_2">
   		 <label class="form-lbl">小区</label>
	</div>
	<div class="grid_3">
		<input type="text"  id="manBlock"  name="population.manBlock"  maxlength="8"  value="${population.manBlock}" style="width: 93%"	class="form-txt" />
	</div>
	<div class="grid_1">
    	 <label class="form-lbl">幢</label>
	</div>
	<div class="grid_3">
	 	<input type="text"  id="manUnit"  name="population.manUnit"  maxlength="6"  value="${population.manUnit}" style="width: 93%"	class="form-txt" />
	</div>
	<div class="grid_1">
 	 	<label class="form-lbl">单元</label>
	</div>
	<div class="grid_3">
	 	<input type="text"  id="manRoom"  name="population.manRoom"  maxlength="10"  value="${population.manRoom}" style="width: 93%"	class="form-txt" />
	</div>
	<div class="grid_1">
	  	<label class="form-lbl">室</label>
	</div>
</div>
<div id="otherAddress" class="grid_17" style="display:none">
	<input type="text"  id="manCurrentAddress"  name="population.manCurrentAddress"  maxlength="50"  value="${population.manCurrentAddress }" style="width: 99%"	class="form-txt" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lb1">备注：</label>
</div>
<div class="grid_20 heightAuto">
	<textarea rows="4" name="population.manRemark" id="manRemark" 
	class="form-txt {exculdeParticalChar:false,minlength:2,maxlength:150,messages:{exculdeParticalChar:'不能输入非法字符',minlength:'备注至少需要输入2个字符',maxlength:'备注最多需要输入150个字符'}}" 
	maxlength="150">${population.manRemark }</textarea>
</div>

<div class='clearLine'>&nbsp;</div>
<script>
var manIdFlg = false;


function whenMaritalStateChange(data){
	if(data){
		$('#firstMarriageTime').removeAttr("disabled");
		$('#marriageRegistrationTime').removeAttr("disabled");

		$('#marriageOrDivorceTime').removeAttr("disabled");
	}else{
		$('#firstMarriageTime').attr("disabled","disabled");
		$('#marriageRegistrationTime').attr("disabled","disabled");

		$('#marriageOrDivorceTime').attr("disabled","disabled");

		$('#firstMarriageTime').val("");
		$('#marriageRegistrationTime').val("");

		$('#marriageOrDivorceTime').val("");
	}
}

function whenLicenseSituationChange(){
	$.ajax({
		type:"post",
		url:"${path}/baseinfo/nurturesWomenManage/validateType.action",
		data:"searchNurturesWomenVo.licenseSituation="+$("#licenseSituation").val(),
		success:function(data){
			if(data){
				$('#fertilityServicesNo').removeAttr("disabled");
				$('#licenseTime').removeAttr("disabled");
				$("#certifiedUnit").removeAttr("disabled");
			} else {
				$('#fertilityServicesNo').attr("disabled","disabled");
				$('#licenseTime').attr("disabled","disabled");
				$("#certifiedUnit").attr("disabled","disabled");
				$('#fertilityServicesNo').val("");
				$('#licenseTime').val("");
				$("#certifiedUnit").val("");
			}
		}
	});

}

function chooseCurrentAddressType(){
	var businessHouse = $("#businessHouse").val();
	var other = $("#other").val();
	var manCurrentAddressType = $("#manCurrentAddressType").val();
	if(manCurrentAddressType==businessHouse.split("-")[0]){
		$("#building").css("display","block");
		$("#otherAddress").css("display","none");
	}else if(manCurrentAddressType==other.split("-")[0]){
		$("#building").css("display","none");
		$("#otherAddress").css("display","block");
	}else{
		$("#building").css("display","none");
		$("#otherAddress").css("display","none");
	}
	
	
	
}

 
 function getmanCurrentAddress(){
	//fateson add
	var businessHouse = $("#businessHouse").val();
	var other = $("#other").val();
	var manCurrentAddressType = $("#manCurrentAddressType").val();
		if(manCurrentAddressType==businessHouse.split("-")[0]){
			var manCommunity = $("#manCommunity").val();
			var manBlock = $("#manBlock").val();
			var manUnit = $("#manUnit").val();
			var manRoom = $("#manRoom").val();
			var manCurrentAddress ="";
			if(manCommunity!=null&&manCommunity!=undefined&&manCommunity!=""){
				manCurrentAddress += manCommunity+"小区";
			}
			if(manBlock!=null&&manBlock!=undefined&&manBlock!=""){
				manCurrentAddress += manBlock+"幢";
			}
			if(manUnit!=null&&manUnit!=undefined&&manUnit!=""){
				manCurrentAddress += manUnit+"单元";
			}
			if(manRoom!=null&&manRoom!=undefined&&manRoom!=""){
				manCurrentAddress += manRoom+"室";
			}
			 $("#manCurrentAddress").val(manCurrentAddress);
		}else{
			$("#manCommunity").val("");
			$("#manBlock").val("");
			$("#manUnit").val("");
			$("#manRoom").val("");
		}
 }
 


function getManPopulation(idCardNo){
	if( idCardNo.length != null && idCardNo.length == 18){
		ajaxForManPopulation(idCardNo);
	}else if( idCardNo.length != null && idCardNo.length == 15){
		ajaxForManPopulation(idCardNo);
	}
}

function ajaxForManPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageManPopulation(responseData);
		}
	});
}
function manageManPopulation(responseData){
	//省市县的选中
	threeSelect({
		province:'manProvince',
		city:'manCity',
		district:'manDistrict',
		provinceValue:responseData.province,
		cityValue:responseData.city,
		districtValue:responseData.district
	});

}
 function lastIdCardChange(){
		var text=$('#manIdcardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetMenBirthdayField(text);
		getManPopulation($('#manIdcardNo').val());
	}

 
   
function initCurrentAddress(){
	$.ajax({
		async: false ,
		url:"${path }/sysadmin/propertyManage/findPropertyDictByDomainName.action",
	   	data:{
		"propertyDomain.domainName":"现居住址类型"
        },
		success:function(responseData){
    	   if(responseData!=null&&responseData.length>0){
        	   for(var i=0;i<responseData.length;i++){
            	   var data = responseData[i];
            	   if(data.internalId==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
        			   $("#businessHouse").val(data.id+"-"+data.internalId);
        	   		}else{
        	   		   $("#other").val(data.id+"-"+data.internalId);
        	   		}
        	   }
			}
        }
	});
	chooseCurrentAddressType();
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

function resetMenBirthdayField(text){
	if (text!="" && $('#manIdcardNo').val()!=""){
		$("#manbirthdayDiv").html("<input type='text' name='population.manBirthday' id='manBirthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#manBirthday').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
		    maxDate:'+0d'});
	}
}
$(function(){
	$('#firstMarriageTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#marriageRegistrationTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#marriageOrDivorceTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#licenseTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#contraceptiveTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#manFirstMarriageTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});

	var times = $("#maternityCardIssueTime,#maternityCardValidityTime").datePicker({
		onSelect: function( selectedDate ) {
			var option = this.id == "maternityCardIssueTime" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
					instance.settings.dateFormat ||
					$.datepicker._defaults.dateFormat,
					selectedDate, instance.settings );
			times.not( this ).datepicker( "option", option, date );
		}
	});



	$("#maternityCardCheckTime").datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'
	});
	$("#singleChildCDIssueTime").datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'
	});




	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	}

	threeSelect({
		province:'manProvince',
		city:'manCity',
		district:'manDistrict',
		provinceValue:$('#manProvinceValue').val(),
		cityValue:$('#manCityValue').val(),
		districtValue:$('#manDistrictValue').val()
	});

	$("#manCurrentAddressType").change(chooseCurrentAddressType);
	$('#manIdcardNo').blur(lastIdCardChange);
	$("#licenseSituation").change(whenLicenseSituationChange);
	initCurrentAddress();

	jQuery.validator.addMethod("validateFirstMarriageTime", function(value, element){
		if($("#firstMarriageTime").val() == "" || $("#birthday").val() == ""){
			return true;
		}
		if($("#firstMarriageTime").val() < $("#birthday").val()){
			return false;
		}
		return true;
	});

	jQuery.validator.addMethod("validateMarriageRegistrationTime", function(value, element){
		if($("#marriageRegistrationTime").val() == "" || $("#firstMarriageTime").val() == ""){
			return true;
		}
		if($("#marriageRegistrationTime").val() < $("#firstMarriageTime").val()){
			return false;
		}
		return true;
	});


	//验证时间控件的值是否大于生日控件，当前控件没有值或者当前值大于生日控件时返回true
	jQuery.validator.addMethod("validateTimeBigThanBirthday", function(value, element){
		//提交之前调用 该方法
		getmanCurrentAddress();
		
		if(!value){
			return true;
		}
		if(value < $("#birthday").val()){
			return false;
		}
		return true;
	});



	jQuery.validator.addMethod("validateMarriageOrDivorceTime", function(value, element){
		if($("#marriageRegistrationTime").val() == "" || $("#marriageOrDivorceTime").val() == ""){
			return true;
		}
		if($("#marriageOrDivorceTime").val() < $("#marriageRegistrationTime").val()){
			return false;
		}
		return true;
	});

	jQuery.validator.addMethod("validateLicenseTime", function(value, element){
		if($("#licenseTime").val() == "" || $("#birthday").val() == ""){
			return true;
		}
		if($("#licenseTime").val() < $("#birthday").val()){
			return false;
		}
		return true;
	});

	jQuery.validator.addMethod("validateContraceptiveTime", function(value, element){
		if($("#contraceptiveTime").val() == "" || $("#birthday").val() == ""){
			return true;
		}
		if($("#contraceptiveTime").val() < $("#birthday").val()){
			return false;
		}
		return true;
	});

	jQuery.validator.addMethod("validateManFirstMarriageTime", function(value, element){
		if($("#manFirstMarriageTime").val() == "" || $("#manBirthday").val() == ""){
			return true;
		}
		if($("#manFirstMarriageTime").val() < $("#manBirthday").val()){
			return false;
		}
		return true;
	});


	jQuery.validator.addMethod("isDigit", function(value, element){
		if(value!=null && value.trim().length>0){

			var reg=/^[0-9]*[0-9][0-9]*$/;
			if(!reg.exec(value)){
				return false;
			}
			if(value<0 || value>9){
				return false
			}
		}
		return true;
	});
	function fullManIdcardNo(idCardNo){
		if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
			manIdFlg =true;
			return true;
		}
		if(idCardNo.length!=15 && idCardNo.length!=18){
			manIdFlg =true;
			return true;
		}
		 
		if (15 == idCardNo.length) { //15位身份证号码
			if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14)){
	            sex = true;
				manIdFlg =true;
			}else{
	            sex = false;	 
			    manIdFlg=false;
	        }
		 }
		if (18 == idCardNo.length) { //18位身份证号码
			if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16)){
		       sex = true;
				manIdFlg =true;
			}else{
		      sex = false;
			  manIdFlg=false;
			}
		}
		return sex;
	}
	
	jQuery.validator.addMethod("manIdcardNoIsMan", function(value, element){
		if(value == "" || value == null){
			return true;
		}
		return fullManIdcardNo(value);
	});
	
});



$(document).ready(function(){

	var checked = $("#isMaternityCard").attr("checked");
	if("checked" == checked){
		$("#moreRequirement").show();
	}else{
		$("#moreRequirement").hide();
	}
	$("#isMaternityCard").click(function(){
		if("checked"==$(this).attr("checked")){
			$("#moreRequirement").show();
		}else{
			$("#moreRequirement").hide();
		}
	});
	
	function isEmpty(o){
		if(o==null){
			return null;
		}else{
			return o.id;
		}
	}
	
	function clearBaseInfoMessage(){
		$("#manName").val("");
		$("#manMaritalState").val("");
		$("#manFirstMarriageTime").val("");
		$("#manMobileNumber").val("");
		$("#manTelephone").val("");
		$("#manNation").val("");
		$("#manPoliticalBackground").val("");
		$("#manSchooling").val("");
		$("#manCareer").val("");
		$("#manWorkUnit").val("");
		$("#manNativeplaceAddress").val("");
		$("#manCurrentAddressType").val("");
		$("#manCommunity").val("");
		$("#manBlock").val("");
		$("#manUnit").val("");
		$("#manRoom").val(""); 
		$("#manCurrentAddress").val("");
		$("#manRemark").val("");
		if($("#manIdcardNo").val()==null || $("#manIdcardNo").val()==''){
	 		$("#manProvince").val("");
	 		$("#manCity").val("");
	 		$("#manDistrict").val("");
	 		$("#manBirthday").val("");
		}
	}
	
	function getBaseInfoByIdCardNo(){
		$.ajax({
			async:false,
			url:"${path }/baseinfo/baseinfoPopulation/getBaseInfoByIdCardNo.action",
		   	data:{
			"idCardNo": $("#manIdcardNo").val()
	        },
			success:function(responseData){
	    	   if(null!=responseData){
	    		   if(responseData.death == true ||responseData.death == 'true'){
	    			   $.confirm({
		           			title:"提示",
		           			message:"该身份证在其他网格已被标记为死亡，若取消死亡，请去掉死亡勾选。",
		           			cancelFunc: function(){
		           				clearBaseInfoMessage();
		                   		return;
		           			}
		           		});
	    		   }
	    		    $("#manName").val(responseData.name);
					$("#manMaritalState").val(isEmpty(responseData.maritalState));
					$("#manFirstMarriageTime").val(responseData.firstMarriageTime);
					$("#manMobileNumber").val(responseData.mobileNumber);
					$("#manTelephone").val(responseData.telephone);
					$("#manBirthday").val(responseData.birthday);
					$("#manNation").val(isEmpty(responseData.nation));
					$("#manPoliticalBackground").val(isEmpty(responseData.politicalBackground));
					$("#manSchooling").val(isEmpty(responseData.schooling));
					$("#manCareer").val(isEmpty(responseData.career));
					$("#manWorkUnit").val(responseData.workUnit);
					setTimeout(function(){
							threeSelect({
								province:'manProvince',
								city:'manCity',
								district:'manDistrict',
								provinceValue:responseData.province,
								cityValue:responseData.city,
								districtValue:responseData.district
							});
			         	},10);
					$("#manNativeplaceAddress").val(responseData.nativePlaceAddress);
					$("#manCurrentAddressType").val(responseData.currentAddressType);
					/**因为户籍 流口 未落户人口中没有常住地址字段，所有将这些字段置空，由手动填写*/
					$("#manCommunity").val("");
					$("#manBlock").val("");
					$("#manCurrentAddress").val("");
					$("#manUnit").val("");
					$("#manRoom").val("");
					$("#manRemark").val("");
	    	   }else{
	    		   clearBaseInfoMessage();
	    	   }
	        }
		});
	}
	
	$("#manIdcardNo").blur(function(){
		if(manIdFlg){
			getBaseInfoByIdCardNo();
		}else{
			 clearBaseInfoMessage();
		}
	});
});

</script>