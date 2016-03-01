<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table  class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${serviceObject.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>姓名</label></td>
    <td class="content"><span>${serviceObject.name}</span></td>
    <td class="title"><label>性别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${serviceObject.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${serviceObject.idCardNo}</span></td>
    <td class="title"><label>人员类型</label></td>
    <td class="content"><span>${serviceObject.attentionPopulationTypeCname}</span></td>
  </tr>
  <tr>
    <td class="title" ><label>服务团队</label></td>
    <td colspan="3" class="content">
  	<s:iterator value="serviceObject.serviceTeams" var="teams">
    	<span>${teams.name}&nbsp</span>
  	</s:iterator>
    </td>
  </tr>
  <tr>
    <td class="title" ><label>服务者</label></td>
    <td colspan="3" class="content">
  	<s:iterator value="serviceObject.serviceMembers" var="members">
    	<span>${members.name}&nbsp</span>
  	</s:iterator>
    </td>
  </tr>
  <s:iterator value="serviceObject.serviceRecords" var="records">
  <tr>
  	<td class="title"><label>服务记录时间</label></td>
    <td class="content"><span><s:date name="#records.occurDate" format="yyyy-MM-dd"/></span></td>
    <td class="title"><label>服务记录</label></td>
    <td class="content"><span>${records.serviceContent}</span></td>
  </tr>
  </s:iterator>
</table>

<script>
$(document).ready(function(){

});
</script>