<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<input id="inflowingProvinceValue"	type="hidden" name="" value="${population.inflowingProvince}" />
		<input id="inflowingCityValue"	type="hidden" name="" value="${population.inflowingCity}" />
		<input id="inflowingDistrictValue"	type="hidden" name="" value="${population.inflowingDistrict}" />
<body>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="inflowingPopulationOrgName">${population.organization.orgName}</td>
    <td class="imagesTX"  rowspan="7">
         <input id="_imgUrl"" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
         <c:choose>
		    <c:when test="${null!=population.imgUrl && not empty population.imgUrl}">
		     	<img id="inflowingPImg" name="population.imgUrl" src="" width="148px"/>
		    </c:when>
		    <c:otherwise>
		    	<img id="img" name="population.imgUrl" src="${resource_path }/resource/images/head.png" width="148px"/>
		    </c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${population.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${population.idCardNo}</span></td>
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
    <td class="title"><label>流入原因</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" defaultValue="${population.inflowingReason.id}" /></span></td>
    <td class="title"><label>流入时间</label></td>
    <td class="content"><span><s:date name="population.inflowingDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>流入来源</label></td>
    <td class="content"><span id="inflowingSource"></span></td>
    <td class="title"><label>登记证情况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE" defaultValue="${population.registrationType.id}" /></span></td>
  </tr>
  <tr>
	<td class="title"><label>登记证编号</label></td>
    <td class="content"><span>${population.certificateNumber}</span></td>
    <td class="title"><label>登记日期</label></td>
    <td class="content" colspan="2"><span><s:date name="population.registerDate" format="yyyy-MM-dd" /></span></td>
  </tr>
 <tr>
	<td class="title"><label>预计到期日期</label></td>
    <td  class="content"><span><s:date name="population.expectedDatedue" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>暂住处所</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@STAY_LOCATION_TYPE" defaultValue="${population.stayLocationType.id}" /></span></td>
  </tr>
 <tr>
	<td class="title"><label>经济来源</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ECONOMY_SOURCE" defaultValue="${population.economySource.id}" /></span></td>
    <td class="title"><label>已居住时限</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@STAY_TIME_LIMIT" defaultValue="${population.stayTimeLimit.id}" /></span></td>
  </tr>
 <tr>
	<td class="title"><label>预居住时限</label></td>
    <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PREPARED_STAY_TIME_LIMIT" defaultValue="${population.preparedStayTimeLimit.id}" /></span></td>
    <td class="title"><label>是否婚育证明</label></td>
    <td class="content" colspan="2"><span><c:choose><c:when test='${population.hasMarriedProve==true}'>是</c:when><c:otherwise>否</c:otherwise></c:choose></span></td>
  </tr>
</table>
</body>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#inflowingPImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	inflowingSourceFormatter();
});

function inflowingSourceFormatter(){
	var str = "";
	if($("#inflowingProvinceValue").val() != null &&$("#inflowingProvinceValue").val()!="")
		str += $("#inflowingProvinceValue").val();
	if($("#inflowingCityValue").val() != null &&$("#inflowingCityValue").val()!="")
		str += $("#inflowingCityValue").val();
	if($("#inflowingDistrictValue").val() != null &&$("#inflowingDistrictValue").val()!="")
		str += $("#inflowingDistrictValue").val();
	$("#inflowingSource").html(str);
}

</script>
