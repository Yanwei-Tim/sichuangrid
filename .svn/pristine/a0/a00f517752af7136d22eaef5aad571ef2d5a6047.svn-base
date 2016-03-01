<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<body>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" >${houseInfo.organization.orgName}</td>
	    <input  name="houseInfo.imgUrl" id="imgUrl" value="${path}${houseInfo.imgUrl}" type="hidden"/>
    <td class="imagesTX" rowspan="7">
	    <img id="imgs" width="148px"/>
    </td>
  </tr>
  <tr>
    <td class="title"><label>住房编号</label></td>
    <td class="content"><span>${houseInfo.houseCode}</span></td>
    <td class="title"><label>常住地址</label></td>
    <td class="content" ><span>${houseInfo.address}</span></td>
  </tr>
  <tr>
    <td class="title"><label>出租用途</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" defaultValue="${houseInfo.usage.id}" /></span></td>
    <td class="title"><label>租赁备案证号</label></td>
   <td class="content"><span>${houseInfo.houseFileNum}</span></td>
  </tr>
  <tr>
    <td class="title"><label>出租人类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LESSOR_TYPE" defaultValue="${houseInfo.lessorType.id}" /></span></td>
    <td class="title"><label>房主姓名</label></td>
    <td class="content"><span>${houseInfo.rentalPerson}</span></td>
  </tr>
  <tr>
    <td class="title"><label>证件类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" defaultValue="${houseInfo.rentalCertificateType.id}" /></span></td>
    <td class="title"><label>证件号码</label></td>
    <td class="content"><span>${houseInfo.rentalCertificateNumbe}</span></td>
  </tr>
  <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${houseInfo.rentalTelephone}</span></td>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${houseInfo.rentalMobileNumber}</span></td>
  </tr>
  <tr>
    <td class="title"><label>房主地址</label></td>
    <td class="content"><span>${houseInfo.lessorAddress}</span></td>
  	 <td class="title"><label>隐患程度</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" defaultValue="${houseInfo.hiddenDangerLevel.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>管理类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MANGER_TYPES" defaultValue="${houseInfo.mangerTypes.id}" /></span></td>
    <td class="title"><label>出租房结构</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${houseInfo.houseStructure.id}" /></span></td>
  </tr>
   <tr>
    <td class="title"><label>出租房性质</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY" defaultValue="${houseInfo.rentalHouseProperty.id}" /></span></td>
    <td class="title"><label>出租房类别</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE" defaultValue="${houseInfo.rentalType.id}" /></span></td>
  </tr>
  
   <tr>
    <td class="title"><label>限制人数</label></td>
    <td class="content"><span>${houseInfo.limitPersons}</span></td>
    <td class="title"><label>登记时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${houseInfo.registDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>出租间数</label></td>
    <td class="content"><span>${houseInfo.roomNumber}</span></td>
    <td class="title"><label>出租时间</label></td>
    <td class="content" colspan="2"><span><fmt:formatDate value="${houseInfo.lessorDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>月租金</label></td>
    <td class="content"><span>${houseInfo.rentMonth}</span></td>
    <td class="title"><label>是否签订治安责任保证书</label></td>
    <td colspan="2" class="content">
		<label class="form-check-text">
		<c:choose>
			<c:when test='${1==houseInfo.isSignGuarantee }'>是</c:when>
			<c:otherwise>否</c:otherwise>
		</c:choose>
		 </label>
	 </td>
  </tr>
  <tr>
    <td class="title"><label>有无安全通道</label></td>
    <td class="content">
		<label class="form-check-text">
		<c:choose>
			<c:when test='${1==houseInfo.isSafetyChannel}'>有</c:when>
			<c:otherwise>无</c:otherwise>
		</c:choose>
		 </label>
	 </td>
    <td class="title"><label>有无消防通道</label></td>
    <td colspan="2" class="content">
		<label class="form-check-text">
		<c:choose>
			<c:when test='${1==houseInfo.isFireChannels }'>有</c:when>
			<c:otherwise>无</c:otherwise>
		</c:choose>
		 </label>
	 </td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" ><span>${houseInfo.remark}</span></td>
     <td class="title"><label>隐患情况</label></td>
       <td class="content" colspan="2"><span>${houseInfo.hiddenRectification}</span></td>
    
  </tr>
</table>
</body>
<script>
$(document).ready(function(){
	if(null!=$("#imgUrl").val() && $("#imgUrl").val()!=""){
		$("#imgs").attr("src",$("#imgUrl").val()+"?r="+Math.random());
	}else{
		$("#imgs").attr("src","${resource_path }/resource/images/locationHead.png");
	}
});
</script>