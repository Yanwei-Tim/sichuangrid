<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<table class="tablelist">
	<tr>
		<td class="title">名称：</td>
		<td class="content">${task.name}</td>
		<td class="title">所在组：</td>
		<td class="content">${task.taskGroup}</td>
	</tr>
	<tr>
		<td class="title">状态：</td>
		<td class="content"><s:if test="task.closed==1">开启</s:if><s:if test="task.closed==0">关闭</s:if></td>
		<td class="title">时间：</td>
		<td class="content">${task.config}</td>
	</tr>
	<tr>
		<td class="title">描述：</td>
		<td class="content" colspan="3">${task.description}</td>
	</tr>
	<tr>
		<td class="title">策略信息：</td>
		<td class="content" colspan="3">
			<table width="200" class="tablelist">
			  <tr>
			    <td class="title"><label>名称：</label></td>
			    <td class="content" >${task.taskPloy.cname}(${task.taskPloy.ename})</td>
			    <td class="title"><label>类型：</label></td>
			    <td class="content" >${task.taskPloy.type.displayName}</td>
			  </tr>
			  <tr>
			    <td class="title"><label>代码：</label></td>
			    <td class="content" colspan="5">${task.taskPloy.code}</td>
			  </tr>
			   <tr>
			    <td class="title"><label>描述：</label></td>
			    <td colspan="5" class="content">${task.taskPloy.description}</td>
			  </tr>
			</table>
		</td>
	</tr>
</table>
<div class='clearLine'></div>
<input type="hidden" id="baseUrl" value="${path} "/>