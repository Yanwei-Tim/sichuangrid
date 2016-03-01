<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table  border="1" class="tablelist">

	<tr>
    	 <td class="title"><label>所属网格</label></td>
    	<td colspan="3" class="content" id="partyOrgReportOrgName">${partyOrgReport.organization.orgName}</td>
    	
	</tr>
	<tr>
		<td class="title"><label>党组织类别</label></td>
    	<td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE" defaultValue="${partyOrgReport.partyOrgType.id}" /></span></span></td>
    	<td class="title"><label>单位名称</label></td>
    	<td class="content"><span>${partyOrgReport.name}</span></td>
	</tr>
	<tr>
		<td class="title"><label>地址</label></td>
    	<td colspan="3"  class="content"><span>${partyOrgReport.address}</span></td>
	</tr>
	
	<tr>
		<td class="title"><label>联系人</label></td>
    	<td class="content"><span>${partyOrgReport.contractor}</span></td>
    	<td class="title"><label>联系电话</label></td>
    	<td class="content"><span>${partyOrgReport.telephone}</span></td>
	</tr>
	<tr>
		<td class="title"><label>认领服务项目</label></td>
    	<td colspan="3" class="content"><span>${partyOrgReport.claimServiceProjectName}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>备注</label></td>
    	<td colspan="3"  class="content"><span>${partyOrgReport.remark}</span></td>
	</tr>
	<c:if test='${partyOrgReport.isEmphasis}'>
	  <tr>
	  	 <td class="title"><label>取消关注时间</label></td>
		 <td class="content"><span id=""><fmt:formatDate value="${partyOrgReport.logOutTime}" type="date" pattern="yyyy-MM-dd" /></span></td>	
		 <td class="title"><label>取消关注原因</label></td>
		 <td colspan="2" class="content"><span id="">${partyOrgReport.logOutReason}</span></td>
	  </tr>
	 </c:if>

</table>