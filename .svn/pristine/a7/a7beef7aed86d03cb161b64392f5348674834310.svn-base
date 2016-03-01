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
    <td colspan="3" class="content" id="orgName">${newEconomicOrganizations.organization.orgName}</td>
    <td class="imagesTX" rowspan="6">
    <input id="_imgUrl" type="hidden" name="newEconomicOrganizations.imgUrl" value="${newEconomicOrganizations.imgUrl}"/>
    <c:choose>
    <c:when test='${null!=newEconomicOrganizations.imgUrl && newEconomicOrganizations.imgUrl!=""}'>
		<img id="newEcimg" name="newEconomicOrganizations.imgUrl" src="${path }${newEconomicOrganizations.imgUrl}" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="newEconomicOrganizations.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>名称</label></td>
    <td class="content" colspan="3" ><span>${newEconomicOrganizations.name}</span></td>
  </tr>
 <tr>
    <td class="title"><label>住所</label></td>
    <td class="content" colspan="3" ><span>${newEconomicOrganizations.residence}</span></td>
  </tr>
  <tr>
    <td class="title"><label>营业执照号码</label></td>
    <td class="content"  ><span>${newEconomicOrganizations.licenseNumber}</span></td>
    <td class="title"><label>有效期</label></td>
    <td class="content"><span>从&nbsp;<fmt:formatDate value="${newEconomicOrganizations.validityStartDate}" type="date" pattern="yyyy-MM-dd" />&nbsp;至&nbsp;<fmt:formatDate value="${newEconomicOrganizations.validityEndDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>类别</label></td>
    <td class="content" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" defaultValue="${newEconomicOrganizations.style.id}" /></span></td>
    <td class="title"><label>负责人（法人）</label></td>
    <td class="content" ><span>${newEconomicOrganizations.chief}</span></td>
  </tr>
  <tr>
    <td class="title"><label>固定电话</label></td>
    <td class="content" ><span>${newEconomicOrganizations.telephone}</span></td>
     <td class="title"><label>联系手机</label></td>
    <td class="content" ><span>${newEconomicOrganizations.mobileNumber}</span></td>
  </tr>
  <tr>
    <td class="title"><label>传真号码</label></td>
    <td class="content" ><span>${newEconomicOrganizations.foxNumber}</span></td>
     <td class="title"><label>面积</label></td>
    <td class="content" colspan="2"><span>${newEconomicOrganizations.areaStringValue}</span>
    <font size="1">M</font><font size="1"><sup>2</sup></font> 
    </td>
  </tr>
    <tr>
    <td class="title"><label>从业人数</label></td>
    <td class="content" ><span>${newEconomicOrganizations.employeeNumber}</span></td>
     <td class="title"><label>党员人数</label></td>
    <td class="content" colspan="2"><span>${newEconomicOrganizations.partyMemberNumber}</span></td>
  </tr>
  <c:if test="${newEconomicOrganizations.isEmphasis==com.tianque.service.vo.IsEmphasis.IsNotEmphasis }">
  	<tr>
    <td class="title"><label>注销时间</label></td>
    <td class="content" ><span><fmt:formatDate value="${newEconomicOrganizations.logoutDetail.logoutDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${newEconomicOrganizations.logoutDetail.logoutReason}</span></td>
  </tr>
  </c:if>
  <tr>
	 <td class="title"><label>简介</label></td>
	 <td class="content" colspan="4" ><span><pre>${newEconomicOrganizations.introduction}</td>
 </tr>
  
 
  <tr>
    <td class="title"><label>荣誉</label></td>
    <td class="content" colspan="4" ><span><pre>${newEconomicOrganizations.honor}</td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="4">${newEconomicOrganizations.remark}</td>
  </tr>
</table>

<script type="text/javascript">
$(function (){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#newEcimg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>

</body>

