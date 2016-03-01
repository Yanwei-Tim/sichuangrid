<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<style type="text/css">
.tablelist .title {
  width: 4%;
}
</style>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><label>粮食作物(亩)</label></td>
	 	<td class="title"><label>经济作物(亩)</label></td>
	 	<td class="title"><label>特色经济种植</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>适度规模种植户</label></td>
	 	<td class="title"><label>种植户总数</label></td>
	 	</s:if>
	 	<td class="title"><label>生猪</label></td>
	 	<td class="title"><label>牛羊</label></td>
	 	<td class="title"><label>小家禽/畜</label></td>
	 	<td class="title"><label>水产</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>适度规模养殖户</label></td>
	 	<td class="title"><label>养殖户总数</label></td>
	 	</s:if>
	 	<td class="title"><label>专业合作组织</label></td>
	 	<td class="title"><label>家庭农场</label></td>
	 	<td class="title"><label>种养大户</label></td>
	 	<td class="title"><label>农产品加工企业 </label></td>
	 	<td class="title"><label>县级以上龙头企业</label></td>
	 	<td class="title"><label>产业化带动农户数</label></td>
	 	
	 	<td class="title"><label>乡村酒店</label></td>
	 	<td class="title"><label>农家乐</label></td>
	 	<td class="title"><label>带动农户就业数</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.industryDevelopment.grainCrops }' pattern='#0.00'/></label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.industryDevelopment.economicCrops }' pattern='#0.00'/></label></td>
	 	
	 	<td class=""><label><fmt:formatNumber value='${vo.industryDevelopment.characteristicPlanting }' pattern='#0.00'/></label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.industryDevelopment.scalePlanting }</label></td>
	 	<td class=""><label>${vo.industryDevelopment.plantingHouseholdsCount }</label></td>
	 	</s:if>
	 	
	 	<td class=""><label>${vo.industryDevelopment.pigNum }</label></td>
	 	<td class=""><label>${vo.industryDevelopment.cattleSheepNum }</label></td>
	 	<td class=""><label>${vo.industryDevelopment.poultryNum }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.industryDevelopment.aquaticProductNum }' pattern='#0.00'/></label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.industryDevelopment.scaleBreed }</label></td>
	 	<td class=""><label>${vo.industryDevelopment.farmHouseholds }</label></td>
	 	</s:if>
	 	
	 	<td class=""><label>${vo.industryDevelopment.specialistNum }</label></td>
		<td class=""><label>${vo.industryDevelopment.familyFarmNum }</label></td>
		<td class=""><label>${vo.industryDevelopment.plantingNum }</label></td>
		
		<td class=""><label>${vo.industryDevelopment.productProcessing }</label></td>
		<td class=""><label>${vo.industryDevelopment.countyCorporateChampion }</label></td>
		<td class=""><label>${vo.industryDevelopment.industrialOrganization }</label></td>
		
		<td class=""><label>${vo.industryDevelopment.villaggioBoutiqueHotel }</label></td>
		<td class=""><label>${vo.industryDevelopment.agritainment }</label></td>
		<td class=""><label>${vo.industryDevelopment.householdEmployment }</label></td>
		</tr>
	</c:forEach>
</table>


