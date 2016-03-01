<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
	  <tr>
	    <td class="title"><span>字段</span></td>
	    <td class="content"><label>修改前数据</label></td>
	    <td class="content"><label>修改后数据</label></td>
	  </tr>
<s:if test="compareObjectLogResults != null">
	<s:iterator value="compareObjectLogResults" id="compareObjectLogResult"> 
	  <tr>
	    <td class="title"><span>${compareObjectLogResult.cname}</span></td>
	    <td class="content"><label>${compareObjectLogResult.oldValue}</label></td>
	    <td class="content"><label>${compareObjectLogResult.newValue}</label></td>
	  </tr>
	 </s:iterator>
</s:if>
<s:else>
	 <tr>
	    <td class="content" colspan="3"><label>修改后无差别</label></td>
	  </tr>
</s:else>
</table>
<script>

</script>
