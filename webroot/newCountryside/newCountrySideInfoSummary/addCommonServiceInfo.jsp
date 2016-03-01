<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><label>村小</label></td>
	 	<td class="title"><label>幼儿园</label></td>
	 	<td class="title"><label>中学</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>义务教育适龄人数</label></td>
	 	<td class="title"><label>适龄已入学人数</label></td>
	 	</s:if>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>新农合参保人数</label></td>
	 	<td class="title"><label>应保尽保</label></td>
	 	</s:if>
	 	<td class="title"><label>卫生室</label></td>
	 	
	 	<td class="title"><label>公共服务中心</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>公共服务中心面积</label></td>
	 	</s:if>
	 	<td class="title"><label>图书馆</label></td>
	 	<td class="title"><label>健身广场(平方米)</label></td>
	 	<td class="title"><label>健身器材(套)</label></td>
	 	<td class="title"><label>文化活动室</label></td>
	 	<td class="title"><label>农家超市</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>开展文体活动</label></td>
	 	<td class="title"><label>参加活动人数</label></td>
	 	</s:if>
	 	
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.villageSchool }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.kindergarten }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.highSchool }</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.commonServiceInfo.compulsoryEducationCount }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.inCompulsoryEducationCount }</label></td>
	 	
	 	<td class=""><label>${vo.commonServiceInfo.insuredNumber }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.hasBuyInsurance }</label></td>
	 	</s:if>
	 	<td class=""><label>${vo.commonServiceInfo.clinic }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.socialWorkCenter }</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label><fmt:formatNumber value='${vo.commonServiceInfo.socialWorkCenterArea }' pattern='#0.00'/></label></td>
	 	</s:if>
	 	<td class=""><label>${vo.commonServiceInfo.library }</label></td>
	 	<td class=""><label><fmt:formatNumber value='${vo.commonServiceInfo.fitnessSquare }' pattern='#0.00'/></label></td>
	 	<td class=""><label>${vo.commonServiceInfo.fitnessEquipment }</label></td>
	 	<td class=""><label>${vo.commonServiceInfo.entertainmentRoom }</label></td>
		<td class=""><label>${vo.commonServiceInfo.farmerSupermarket }</label></td>
		<s:if test="0==isPlaning">
		<td class=""><label>${vo.commonServiceInfo.recreationalActivities }</label></td>
		<td class=""><label>${vo.commonServiceInfo.recreationalActivitiesPeople }</label></td>
		</s:if>
		</tr>
	</c:forEach>
</table>


