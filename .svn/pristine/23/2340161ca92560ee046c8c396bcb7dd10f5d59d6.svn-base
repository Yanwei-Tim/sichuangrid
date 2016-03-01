<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>名称：</label></td>
    <td class="content" >${taskPloy.cname}(${taskPloy.ename})</td>
    <td class="title"><label>类型：</label></td>
    <td class="content" >${taskPloy.type.displayName}</td>
  </tr>
  <tr>
    <td class="title"><label>代码：</label></td>
    <td class="content" colspan="5">${taskPloy.code}</td>
  </tr>
   <tr>
    <td class="title"><label>描述：</label></td>
    <td colspan="5" class="content">${taskPloy.description}</td>
  </tr>
</table>
<div class='clearLine'></div>
<input type="hidden" id="baseUrl" value="${path} "/>