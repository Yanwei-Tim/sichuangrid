<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">

	<tr>
    	<td class="title"><label>号码</label></td>
    	<td class="content"><span>${smstrash.mobile}</span></td>
    	<td class="title"><label>时间</label></td>
    	<td class="content"><span><fmt:formatDate value="${smstrash.time}" type="date" pattern="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
    	<td class="title"><label>短信来源</label></td>
    	<td class="content" colspan="3"><span><c:if test="${smstrash.fromType == 1 }">发件箱</c:if><c:if test="${smstrash.fromType == 2 }">收件箱</c:if></span></td>
    	
	</tr>
	<tr>
    	<td class="title"><label>短信内容</label></td>
    	<td class="content" colspan="3"><span>${smstrash.content}</span></td>
	</tr>

</table>