<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table  width="200" class="tablelist">

	<tr>
    	 <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="skynetOrgName">${skynet.organization.orgName}</td>
	</tr>
	<tr>
    	<td class="title"><label>编号</label></td>
    	<td class="content"><span>${skynet.skynetNo}</span></td>
    	<td class="title"><label>&nbsp;</label></td>
    	<td class="content"><span>&nbsp;</span></td>
	</tr>
	<tr>
    	<td class="title"><label>地址</label></td>
    	<td class="content" colspan="3">${skynet.address}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>2.5维经度</label></td>
    	<td class="content"><span>${skynet.openLayersMapInfo.centerLon}</span></td>
    	<td class="title"><label>2.5维纬度</label></td>
    	<td class="content"><span>${skynet.openLayersMapInfo.centerLat}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>二维经度</label></td>
    	<td class="content"><span>${skynet.openLayersMapInfo.centerLon2}</span></td>
    	<td class="title"><label>二维纬度</label></td>
    	<td class="content"><span>${skynet.openLayersMapInfo.centerLat2}</span></td>
	</tr>
	
	<c:if test="${skynet.isEmphasis }">
	  <tr>
	  	 <td class="title"><label>取消关注时间</label></td>
		 <td class="content"><span id=""><fmt:formatDate value="${skynet.logoutTime}" type="date" pattern="yyyy-MM-dd" /></span></td>	
		 <td class="title"><label>取消关注原因</label></td>
		 <td class="content"><span id="">${skynet.logoutReason}</span></td>
	  </tr>
  </c:if>

</table>