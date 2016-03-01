<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${parameters.title[0]=='myEvaluate' }">
		<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">上级考核</a>&nbsp;&gt;&nbsp;<a id="currentPosition" class="dq">我的考核</a></h1>
	</c:when>
	<c:when test="${parameters.title[0]=='selfEvaluate'}">
		<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">上级考核</a>&nbsp;&gt;&nbsp;<a id="currentPosition" class="dq">自评</a></h1>
	</c:when>
	<c:when test="${parameters.title[0]=='standardEvaluate'}">
		<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">考核标准管理</a>&nbsp;&gt;&nbsp;<a id="currentPosition" class="dq">考核标准设置</a></h1>
	</c:when>
	<c:when test="${parameters.title[0]=='lowerEvaluate' }">
		<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">上级考核</a>&nbsp;&gt;&nbsp;<a id="currentPosition" class="dq">辖区上报情况</a></h1>
	</c:when>
	<c:otherwise>
		<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">考核标准列表</a>&nbsp;&gt;&nbsp;<a id="currentPosition" class="dq">自评</a></h1>
	</c:otherwise>
</c:choose>
