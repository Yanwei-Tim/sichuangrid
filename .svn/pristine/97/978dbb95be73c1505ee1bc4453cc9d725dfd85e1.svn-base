<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
		<input id="manProvinceValue"	type="hidden" name="" value="${population.manProvince}" />
		<input id="manCityValue"	type="hidden" name="" value="${population.manCity}" />
		<input id="manDistrictValue"	type="hidden" name="" value="${population.manDistrict}" />
		<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<body>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="commonPopulationOrgName">${population.organization.orgName}</td>
    <td class="imagesTX" rowspan="5">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="commonPopulationImg" name="population.imgUrl" src="" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>关注程度</label></td>
    <td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>初婚时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.firstMarriageTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
    <td class="title"><label>离婚时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.marriageRegistrationTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
   	<td class="title"><label>最近再婚时间</label></td>
   	<td class="content"><span><fmt:formatDate value="${population.marriageOrDivorceTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
   	<td class="title"><label>孩子数</label></td>
    <td class="content"><span id="childNumber"></span></td>
   
  </tr>
   <tr>
    <td class="title"><label>独生子女证号</label></td>
    <td class="content"><span>${population.singleChildCardno }</span></td>
    <td class="title"><label>领证时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.licenseTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
    
  </tr>
  <tr>
  	<td class="title"><label>独生子女证领证时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.singleChildCDIssueTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
    <td class="title" style="width: 20%"><label>夫妻双方独生子女情况</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION" defaultValue="${population.onechildOfCouple.id}" /></span></td>
   </tr>
    
  <tr>
   <td class="title"><label>领证情况</label></td>
   <td class="content" colspan="4"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION" defaultValue="${population.licenseSituation.id}" /></span></td>
  </tr> 
   <tr>
    <td class="title"><label>生育服务证号</label></td>
    <td class="content"><span>${population.fertilityServicesNo}</span></td>
    <td class="title" style="width: 20%;"><label>生育服务证发证单位</label></td>
    <td class="content" colspan="2"><span>${population.certifiedUnit }</span></td>
   </tr> 
  <tr>
    <td class="title"><label>是否未婚先孕</label></td>
    <td class="content">
		   <c:choose>
		     <c:when test="${population.isUnmarriedPregnant==1 }">
		     <span>是</span>
		     </c:when>
		     <c:otherwise>
		     <span>否</span>
		     </c:otherwise>
		    </c:choose>
    </td>
     <td class="title"><label>是否征收</label></td>
    <td class="content" colspan="2">
		    <c:choose>
		     <c:when test="${population.isLevied==1 }">
		     <span>是</span>
		     </c:when>
		     <c:otherwise>
		     <span>否</span>
		     </c:otherwise>
		    </c:choose>
    </td>
  </tr>
  <tr>
    <td class="title"><label>避孕方法</label></td>
    <td class="content"><span>${population.contraceptiveMethod }</span></td>
    <td class="title"><label>避孕起始时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${population.contraceptiveTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>是否持有婚育证明</label></td>
    <td class="content">
 			<c:choose>
		     <c:when test="${population.isMaternityCard==1 }">
		     <span>是</span>
		     </c:when>
		     <c:otherwise>
		     <span>否</span>
		     </c:otherwise>
		    </c:choose>
    </td>
    <td class="title"><label>婚育证发证单位</label></td>
    <td class="content" colspan="2"><span>${population.maternityCardUnit}</span></td>
  </tr>
   <tr>
    <td class="title"><label>婚育证号</label></td>
    <td class="content"><span>${population.maternityCardNo }</span></td>
    <td class="title"><label>发放时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${population.maternityCardIssueTime }" type="date" pattern="yyyy-MM-dd" /></span></td>
 
  </tr>
  <tr>
 	<td class="title"><label>有效期截至时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.maternityCardValidityTime}" type="date" pattern="yyyy-MM-dd" /></span></td>	
    <td class="title"><label>查验时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${population.maternityCardCheckTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
   </tr>
  <tr>
    <td class="title"><label>查验情况</label></td>
    <td class="content" colspan="4"><span>${population.maternityCardRemark}</span></td>
  </tr>
  
  <tr>
  	<td colspan="5" class="btitle">男方信息</td>
  </tr>
  <tr>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${population.manIdcardNo }</span></td>
    <td class="title"><label>姓名</label></td>
    <td class="content" colspan="5"><span>${population.manName }</span></td>
  </tr>
  <tr>
    <td class="title"><label>婚姻状况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.manMaritalState.id}" /></span></td>
    <td class="title"><label>初婚时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${population.manFirstMarriageTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.manMobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content" colspan="2"><span>${population.manTelephone }</span></td>
  </tr>
  <tr>
  	<td class="title"><label>出生日期</label></td>
    <td class="content"><span><fmt:formatDate value="${population.manBirthday}" type="date" pattern="yyyy-MM-dd" /></span></td>
    <td class="title"><label>民族</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.manNation.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>政治面貌</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.manPoliticalBackground.id}" /></span></td>
  	<td class="title"><label>文化程度</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.manSchooling.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>职业</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.manCareer.id}" /></span></td>
  	<td class="title"><label>工作单位或就读学校</label></td>
    <td class="content" colspan="2"><span>${population.manWorkUnit}</span></td>
  </tr>

  <tr>
  	<td class="title"><label>户籍地址</label></td>
    <td colspan="5" class="content"><span id="manNativePlace"></span></td>
  </tr>
  <tr>
  	<td class="title"><label>户籍详址</label></td>
    <td colspan="5" class="content"><span>${population.manNativeplaceAddress}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>常住地址</label></td>
    <td colspan="5" class="content"><span>${population.manCurrentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content">${population.manRemark}</td>
  </tr>
</table>
</body>

<script>
$(document).ready(function(){
	nativePlaceFormatter();
	childNumberFormatter();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#commonPopulationImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	
});

function nativePlaceFormatter(){

	var str = "";
	if($("#manProvinceValue").val() != null &&$("#manProvinceValue").val()!="")
		str += $("#manProvinceValue").val();
	if($("#manCityValue").val() != null &&$("#manCityValue").val()!="")
		str += $("#manCityValue").val();
	if($("#manDistrictValue").val() != null &&$("#manDistrictValue").val()!="")
		str += $("#manDistrictValue").val();
	$("#manNativePlace").html(str);
}

function childNumberFormatter(){
	var str="";
	if(${population.boyNumber }!=0){
		str+=${population.boyNumber }+"男";
	}
	if(${population.girlNumber }!=0){
		str+=${population.girlNumber }+"女";
	}
	$("#childNumber").html(str);
}
</script>