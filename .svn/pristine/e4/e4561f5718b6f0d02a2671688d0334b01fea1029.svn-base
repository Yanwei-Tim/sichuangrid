<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
<s:iterator value="houseFamilyList" var="houseFamily" status="st" >
   <s:if test="#st.index==0">
	<tr>
		<td class="title"><label>所属网格</label></td>
		<td class="content" id="commonPopulationOrgName"><s:property value="#houseFamily.organization.orgName"/></td>
		 <td class="title"><label>户口号</label></td>
		<td class="content"><span><s:property value="#houseFamily.censusRegisterFamily.accountNumber"/></span></td>
	 </tr>
	 <tr>
		<td class="title"><label>户籍地</label></td>
		<td class="content"><span><s:property value="#houseFamily.nativeLocation"/></span></td>
		<td class="title"><label>当前地址</label></td>
		<td class="content"><span><s:property value="#houseFamily.censusRegisterFamily.currentAddress"/></span></td>
	</tr>
	 <tr>
		<td class="title"><label>家庭称号</label></td>
		<td colspan="3" class="content"><span><s:iterator value="#houseFamily.houseMarchType" id="houseMarch">${houseMarch.displayName} </s:iterator></span></td>
	</tr>
</s:if>
	<tr>
		<td class="title"><label>姓 名</label></td>
		<td class="content"><span><s:property value="#houseFamily.name"/></span></td>
		<td class="title"><label>身份证号码</label></td>
		<td class="content"><span><s:property value="#houseFamily.idCardNo"/></span></td>
	</tr>
	<tr>
		<td class="title"><label>性别</label></td>
	    <s:set name="genderId" value="#houseFamily.gender.id" scope="request"/>
		<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${genderId}" /></span></td>
		<td class="title"><label>工作单位或就读学校</label></td>
		<td class="content"><span><s:property value="#houseFamily.workUnit"/></span></td>
	</tr>
	<tr>
		<td class="title"><label>联系手机</label></td>
		<td class="content"><span><s:property value="#houseFamily.mobileNumber"/></span></td>
		<td class="title"><label>固定电话</label></td>
		<td class="content"><span><s:property value="#houseFamily.telephone"/></span></td>
	</tr>
</s:iterator>
</table>