<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
    	<td class="title"><label></label></td>
    	<td class="content"><span>${issueSkiprule.id}</span></td>
    	<td class="title"><label>是否短信提醒(0否1是)</label></td>
    	<td class="content"><span>${issueSkiprule.messageFlag}</span></td>
	</tr>
	<tr>
    	<td class="title"><label></label></td>
    	<td class="content"><span><fmt:formatDate value="${issueSkiprule.createdate}" type="date" pattern="yyyy-MM-dd" /></span></td>
    	<td class="title"><label></label></td>
    	<td class="content"><span><fmt:formatDate value="${issueSkiprule.updatedate}" type="date" pattern="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
    	<td class="title"><label>所属网格</label></td>
    	<td class="content"><span>${issueSkiprule.orgId}</span></td>
    	<td class="title"><label>事件所属类型(小类)id</label></td>
    	<td class="content"><span>${issueSkiprule.issueTypeId}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>事件所属类型(大类)id</label></td>
    	<td class="content"><span>${issueSkiprule.issueTypeDomainId}</span></td>
    	<td class="title"><label>审核流程(重大紧急等级)</label></td>
    	<td class="content"><span>${issueSkiprule.issueUrgentLevel}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>上报单位id</label></td>
    	<td class="content"><span>${issueSkiprule.submitOrgId}</span></td>
    	<td class="title"><label>所属网格编号</label></td>
    	<td class="content"><span>${issueSkiprule.orgInternalCode}</span></td>
	</tr>
	<tr>
    	<td class="title"><label></label></td>
    	<td class="content"><span>${issueSkiprule.createuser}</span></td>
    	<td class="title"><label></label></td>
    	<td class="content"><span>${issueSkiprule.updateuser}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>抄送单位id数组</label></td>
    	<td class="content"><span>${issueSkiprule.ccOrgIds}</span></td>
    	<td class="title"><label>&nbsp;</label></td>
    	<td class="content"><span>&nbsp;</span></td>
	</tr>

</table>