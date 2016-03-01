<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<body>
<input id="houseSource" type="hidden" value="<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" defaultValue="${houseInfo.houseSource.id}"/>"/>
<input id="ownProperty" type="hidden" value="<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@OWN_PROPERTY" defaultValue="${houseInfo.ownProperty.id}"/>"/>
<input id="rentalBuildings" type="hidden" value="<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RENTAL_BUILDINGS" defaultValue="${houseInfo.rentalBuildings.id}"/>"/>
<input id="address" type="hidden" value="${houseInfo.address}">
<input id="houseType" type="hidden" value="house">
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" >${houseInfo.organization.orgName}</td>
    <td class="imagesTX" rowspan="6">
    <c:choose>
    <c:when test='${null!=houseInfo.imgUrl && not empty houseInfo.imgUrl}'>
		<img id="img" name="houseInfo.imgUrl" src="${path }${houseInfo.imgUrl}?r=111" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="houseInfo.imgUrl" src="${resource_path }/resource/images/locationHead.png?r=222" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>住房编号</label></td>
    <td class="content" colspan="3"><span>${houseInfo.houseCode}</span></td>
  </tr>
  <tr>
    <td class="title"><label>房屋用途</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" defaultValue="${houseInfo.houseUses.id}" /></span></td>
    <td class="title"><label>房屋来源</label></td>
    <td class="content"><span id="houseSourceInfo"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" defaultValue="${houseInfo.houseSource.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label><c:choose><c:when test="${ houseType =='rentalHouse'}">出租房面积</c:when><c:otherwise>本户建筑面积</c:otherwise></c:choose></label></td>
    <td class="content"><span id="houseArea_rent">${houseInfo.houseArea}</span><span><font size="1">M</font><font size="1"><sup>2</sup></font></span></td>
  	 <td class="title"><label>房屋结构</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${houseInfo.houseStructure.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>房屋凭证</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HOUSING_VOUCHERS" defaultValue="${houseInfo.housingVouchers.id}" /></span></td>
    <td class="title"><label>房屋权证号</label></td>
    <td class="content"><span>${houseInfo.houseRightNumber}</span></td>
  </tr>
  <tr>
    <td class="title"><label>发证时间</label></td>
    <td class="content"><span><fmt:formatDate value="${houseInfo.houseRightNumberDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
    <td class="title"><label>土地凭证</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LAND_DOCUMENTS" defaultValue="${houseInfo.landDocuments.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>土地权证号</label></td>
    <td class="content"><span>${houseInfo.landRightsNumbe}</span></td>
    <td class="title"><label>发证时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${houseInfo.landRightsNumbeDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>产权人类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" defaultValue="${houseInfo.propertyTypes.id}" /></span></td>
    <td class="title"><label>产权人名称</label></td>
    <td class="content" colspan="2"><span>${houseInfo.name}</span></td>
  </tr>
  <tr>
    <td class="title"><label>证件类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" defaultValue="${houseInfo.certificateType.id}" /></span></td>
    <td class="title"><label>证件号码</label></td>
    <td class="content" colspan="2"><span>${houseInfo.certificateNumbe}</span></td>
  </tr>
  <tr>
    <td class="title"><label>产权人电话</label></td>
    <td class="content"><span>${houseInfo.propertyPersonTel}</span></td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" colspan="2"><span>${houseInfo.propertyPersonMobile}</span></td>
  </tr>
  <tr>
    <td class="title"><label>房屋户型</label></td>
    <td class="content"><span>${houseInfo.houseDoorModel}</span></td>
    <td class="title"><label>建成年份</label></td>
    <td class="content" colspan="2"><span>${houseInfo.builtYear}</span></td>
  </tr>
  <tr>
    <td class="title"><label>房屋准确地址</label></td>
    <td class="content"><span>${houseInfo.address}</span></td>
    <td class="title"><label>房产证地址</label></td>
    <td class="content" colspan="2"><span>${houseInfo.houseAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>建筑物名称</label></td>
    <td class="content"><span>${houseInfo.buildingName}</span></td>
    <td class="title"><label>建筑物用途</label></td>
   <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BUILDING_USES" defaultValue="${houseInfo.buildingUses.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>物管单位名称</label></td>
    <td class="content"><span>${houseInfo.propertyName}</span></td>
    <td class="title"><label>代表人/业主</label></td>
    <td class="content" colspan="2"><span>${houseInfo.houseOwner}</span></td>
  </tr>
  <tr>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${houseInfo.houseOwnerIdCardNo}</span></td>
    <td class="title"><label>代表人电话</label></td>
    <td class="content" colspan="2"><span>${houseInfo.telephone}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="4">${houseInfo.remark}</td>
  </tr>
</table>
</body>

<script>
$(document).ready(function(){
	var houseSource = $("#houseSourceInfo").text();
	if(houseSource!=null && houseSource!='') {
		if(houseSource=="租赁公房"){
				$("#houseSourceInfo").text($("#houseSource").val() + '--' + $("#rentalBuildings").val());
		} else {
				$("#houseSourceInfo").text($("#houseSource").val() + '--' + $("#ownProperty").val());
		}
	}
});

</script>
