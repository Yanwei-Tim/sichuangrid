<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="provinceValue"	type="hidden" name="" value="${population.province}" />
<input id="cityValue"	type="hidden" name="" value="${population.city}" />
<input id="districtValue"	type="hidden" name="" value="${population.district}" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="commonPopulationOrgName">${population.organization.orgName}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
    <s:if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="commonImg" name="population.imgUrl" src="" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="population.imgUrl" src="${resource_path}/resource/images/head.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${population.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span id="idCardNo"></span></td>
  </tr>
  <tr>
    <td class="title"><label>曾用名/别名</label></td>
    <td class="content"><span>${population.usedName}</span></td>
    <td class="title"><label>性别</label></td>
   <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" /></span></td>
    <td class="title"><label>出生日期</label></td>
    <td class="content"><span><s:date name="population.birthday" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.telephone }</span></td>
  </tr>
  <tr>
    <td class="title"><label>政治面貌</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.politicalBackground.id}" /></span></td>
    <td class="title"><label>文化程度</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.schooling.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>身高</label></td>
    <td class="content"><span>${population.stature }(CM)</span></td>
    <td class="title"><label>职业</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.career.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>工作单位或就读学校</label></td>
    <td class="content"><span>${population.workUnit }</span></td>
    <td class="title"><label>婚姻状况</label></td>
    <td colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.maritalState.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>是否死亡</label></td>
    <td class="content"><span><s:if test='true==population.death'>是</s:if><s:else>否</s:else> </span></td>
    <td class="title"><label>电子邮箱</label></td>
    <td colspan="2" class="content"><span>${population.email}</span></td>
  </tr>
	
  <s:if test="population.logoutDetail.logout==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
   <tr>
    <td class="title"><label>注销时间</label></td>
    <td  class="content"><span><s:date name="population.logoutDetail.logoutDate" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${population.logoutDetail.logoutReason}</span></td>
  </tr>
  </s:if>

  <tr>
    <td class="title"><label>血型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${population.bloodType.id}" /></span></td>
    <td class="title"><label>户籍地址</label></td>
    <td colspan="2" class="content"><span id="nativePlace"></span></td>
  </tr>
  <tr>
    <td class="title"><label>宗教信仰</label></td>
    <td class="content" colspan=5"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${population.faith.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>户籍地详址</label></td>
    <td class="content" colspan="5"><span>${population.nativePlaceAddress}</span></td>
  </tr>
  <s:if test="!('floatingPopulation'.equals(population.actualPopulationType))">
  <tr>
    <td class="title"><label>户籍派出所</label></td>
    <td class="content" colspan="5"><span>${population.nativePoliceStation}</span></td>
  </tr>
  </s:if>

  <tr>
  
<%--update by  fateson    简单的判断: 1 ：有房屋地址  0 ：没有房屋地址  null：未知 --%>
    <td class="title"><label>${population.isHaveHouse?'常住地址':'有无住所'}</label></td>
    <td colspan="${population.isHaveHouse==false?1:4}" class="content">
    <span><c:choose><c:when test="${population.isHaveHouse==null}">未知</c:when><c:when test="${population.isHaveHouse=='false'}">无住所</c:when><c:otherwise>${population.currentAddress}</c:otherwise></c:choose></span>
    </td>
    <c:if test="${population.isHaveHouse=='false'}">
    <td class="title"><label>无住所原因</label></td>
    <td colspan="2" class="content"><span>${population.noHouseReason}</span></td>
    </c:if>
  </tr>
  <tr>
    <td class="title"><label>临时居所</label></td>
    <td colspan="4" class="content"><span>${population.otherAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content">${population.remark}</td>
  </tr>
</table>
<script>
$(document).ready(function(){
	nativePlaceFormatter();
	subIdCardNo();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#commonImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});

function nativePlaceFormatter(){
	var str = "";
	if($("#provinceValue").val() != null &&$("#provinceValue").val()!="")
		str += $("#provinceValue").val()+"  ";
	if($("#cityValue").val() != null &&$("#cityValue").val()!="")
		str += $("#cityValue").val()+"  ";
	if($("#districtValue").val() != null &&$("#districtValue").val()!="")
		str += $("#districtValue").val()+"  ";
	$("#nativePlace").html(str);
}

function subIdCardNo(){
	var idCardNo='${population.idCardNo}';
	var idCardNoSub = idCardNo.substring(0,10)+"****"+idCardNo.substring(14);
	$("#idCardNo").text(idCardNoSub);
	if('${population.attentionPopulationType}'==("floatingPopulation")){
		  <pop:JugePermissionTag ename="isFloatingPopulationNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
	}
	if('${population.attentionPopulationType}'==("positiveInfo")){
	  <pop:JugePermissionTag ename="isPositiveInfoManagementNotHidCard">
		 $("#idCardNo").text(idCardNo);
	  </pop:JugePermissionTag>
	}
	if('${population.attentionPopulationType}'==("rectificativePerson")){
		  <pop:JugePermissionTag ename="isRectificativePersonManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("mentalPatient")){
		  <pop:JugePermissionTag ename="isMentalPatientManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("druggy")){
		  <pop:JugePermissionTag ename="isDruggyManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("aidspopulation")){
		  <pop:JugePermissionTag ename="isAidspopulationsManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("idleYouth")){
		  <pop:JugePermissionTag ename="isIdleYouthManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("superiorVisit")){
		  <pop:JugePermissionTag ename="isSuperiorVisitManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("dangerousGoodsPractitioner")){
		  <pop:JugePermissionTag ename="isDangerousGoodsPractitionerManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("otherAttentionPersonnel")){
		  <pop:JugePermissionTag ename="isOtherAttentionPersonnelManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("goodSamaritan")){
		  <pop:JugePermissionTag ename="isGoodSamaritanManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("fPersonnel")){
		  <pop:JugePermissionTag ename="isFPersonnelManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("qPersonnel")){
		  <pop:JugePermissionTag ename="isQPersonnelManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("mPersonnel")){
		  <pop:JugePermissionTag ename="isMPersonnelManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("householdStaff")){
		  <pop:JugePermissionTag ename="isYoungstersManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("nurturesWomen")){
		  <pop:JugePermissionTag ename="isNurturesWomenNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("nurturesWomen")){
		  <pop:JugePermissionTag ename="isNurturesWomenNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("unemployedPeople")){
		  <pop:JugePermissionTag ename="isUnemployedPeopleManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("elderlyPeople")){
		  <pop:JugePermissionTag ename="isElderlyPeopleManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("handicapped")){
		  <pop:JugePermissionTag ename="isHandicappedManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("optimalObject")){
		  <pop:JugePermissionTag ename="isOptimalObjectManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	if('${population.attentionPopulationType}'==("aidNeedPopulation")){
		  <pop:JugePermissionTag ename="isAidNeedPopulationManagementNotHidCard">
			 $("#idCardNo").text(idCardNo);
		  </pop:JugePermissionTag>
		}
	
}

</script>
