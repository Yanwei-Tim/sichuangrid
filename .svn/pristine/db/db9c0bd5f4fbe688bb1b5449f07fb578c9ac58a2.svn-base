<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table class="tablelist">
	<tr>
		<td class="title"><label>来源人</label></td>
		<td class="content"><span>${eventSource.sourcePeople }</span></td>
		<td class="title"><label>发生时间</label></td>
		<td class="content"><span><s:date  name="eventSource.sourceDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>联系电话</label></td>
		<td class="content"><span>${eventSource.telephone }</span></td>
		<td class="title"><label>来源方式</label></td>
		<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" defaultValue="${eventSource.sourceType.id}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>发生地点</label></td>
		<td colspan="3" class="content"><span>${eventSource.orgName}</span></td>
	</tr>
	<tr>
		<td class="title"><label>主题</label></td>
		<td colspan="3" class="content"><span><pre>${eventSource.title}</pre></span></td>
	</tr>	
	<tr>
		<td class="title">内 容</td>
		<td  colspan="3" class="content" ><span id=""><pre>${eventSource.content}</pre></span></td>	
	</tr>

</table>




	




