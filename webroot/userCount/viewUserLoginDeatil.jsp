<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="" class="container container_24">
        <table class="tablelist">
        	<tr>
			 	<td class="title"><label><s:if test="1==searchType">上周登录详情</s:if><s:if test="2==searchType">上月登录详情</s:if></label></td>
			</tr>
			<c:forEach items="${userSignList }" var="vo">
			<tr>
			 	<td><label><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${vo.createDate }"/></label></td>
			</tr>
			</c:forEach>
        </table>
</div>	

