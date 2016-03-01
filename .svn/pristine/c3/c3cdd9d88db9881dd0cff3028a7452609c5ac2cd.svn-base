<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table  width="200" class="tablelist">

	<tr>
    	 <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="bayonetOrgName">${bayonet.organization.orgName}</td>
	</tr>
	<tr>
    	<td class="title"><label>编号</label></td>
    	<td class="content"><span>${bayonet.bayonetNo}</span></td>
    	<td class="title"><label>&nbsp;</label></td>
    	<td class="content"><span>&nbsp;</span></td>
	</tr>
	<tr>
    	<td class="title"><label>地址</label></td>
    	<td class="content" colspan="3">${bayonet.address}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>2.5维经度</label></td>
    	<td class="content"><span>${bayonet.openLayersMapInfo.centerLon}</span></td>
    	<td class="title"><label>2.5维纬度</label></td>
    	<td class="content"><span>${bayonet.openLayersMapInfo.centerLat}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>二维经度</label></td>
    	<td class="content"><span>${bayonet.openLayersMapInfo.centerLon2}</span></td>
    	<td class="title"><label>二维纬度</label></td>
    	<td class="content"><span>${bayonet.openLayersMapInfo.centerLat2}</span></td>
	</tr>
	
	<c:if test="${bayonet.isEmphasis }">
	  <tr>
	  	 <td class="title"><label>取消关注时间</label></td>
		 <td class="content"><span id=""><fmt:formatDate value="${bayonet.logoutTime}" type="date" pattern="yyyy-MM-dd" /></span></td>	
		 <td class="title"><label>取消关注原因</label></td>
		 <td class="content"><span id="">${bayonet.logoutReason}</span></td>
	  </tr>
  </c:if>

</table>