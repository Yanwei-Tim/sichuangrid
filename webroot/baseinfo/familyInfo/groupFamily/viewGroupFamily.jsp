<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content">${groupFamily.organization.orgName}</td>
  </tr>
  <tr>
   	<td class="title"><label>家庭编号</label></td>
    <td class="content"><span>${groupFamily.familyAccount}</span></td>
    <td class="title"><label>家庭地址</label></td>
    <td class="content"><span>${groupFamily.currentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>家长姓名</label></td>
    <td class="content"><span>${groupFamily.familyMaster}</span></td>
    <td class="title"><label>家长证件号</label></td>
   <td class="content"><span>${groupFamily.masterCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>家长手机</label></td>
    <td class="content"><span>${groupFamily.masterMobileNumber}</span></td>
    <td class="title"><label>家长电话</label></td>
    <td class="content"><span>${groupFamily.masterTelephone}</span></td>
  </tr>
</table>
