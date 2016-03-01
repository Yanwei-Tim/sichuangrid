<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<table width="200" class="tablelist">

<tr>
 <td class="title"><label>所属网格</label></td>
  <td class="content" colspan="3">${propagandaAndVerification.organization.fullOrgName}</td>
</tr>

 <tr>
  <td class="title"><label>姓名</label></td>
  <td class="content">${propagandaAndVerification.name}</td>
  <td class="title"><label>身份证号码</label></td>
  <td class="content">${propagandaAndVerification.idCard}</td>
 </tr>
 
 <tr>
  <td class="title"><label>电话号码</label></td>
  <td class="content">${propagandaAndVerification.phone}</td>
  <td class="title"><label>宣传</label></td>
  <td class="content">
	    <s:if test="propagandaAndVerification.propaganda==1"> 是 </s:if>
		<s:if test="propagandaAndVerification.propaganda==0"> 否 </s:if>
  </td>
 </tr>
 
 <tr>
  <td class="title"><label>时间</label></td>
  <td class="content"><fmt:formatDate value="${propagandaAndVerification.occurrenceDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  <td class="title"><label>核查申报</label></td>
  <td class="content">
	    <s:if test="propagandaAndVerification.verificationReport==1"> 是 </s:if>
		<s:if test="propagandaAndVerification.verificationReport==0"> 否 </s:if>
  </td>
 </tr>
 
  <tr>
    <td class="title"><label>地点</label></td>
    <td class="content" colspan="3">${propagandaAndVerification.address }</td>
  </tr>
  
  <tr>
    <td class="title"><label>网格员电话</label></td>
    <td  class="content" colspan="3">${propagandaAndVerification.telephone }</td>
  </tr>
  
   <tr>
    <td class="title"><label>签收人</label></td>
    <td  class="content">${propagandaAndVerification.signUserName }</td>
     <td class="title"><label>签收时间</label></td>
    <td class="content"><fmt:formatDate value="${propagandaAndVerification.signDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
   </tr>
   
  
  <tr>
   <td class="title"><label>签收意见</label></td>
    <td class="content" colspan="3">${propagandaAndVerification.advice }</td>
  </tr>
  
   <tr>
   <td class="title"><label>备注</label></td>
    <td class="content" colspan="3">${propagandaAndVerification.remark }</td>
   </tr>
  
  
 </table>

