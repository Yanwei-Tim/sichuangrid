<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<body>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="OrgName">${population.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${population.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${population.idCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>性别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" /></span></td>
    <td class="title"><label>职务</label></td>
    <td class="content"><span>${population.memberPosition}</span></td>		
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobile }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.homePhone }</span></td>
  </tr>
</table>

</body>

<script>
$(document).ready(function(){

});


</script>
