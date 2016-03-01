<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table  border="1" class="tablelist">
	<tr>
		<td class="title"><label>党组织类型</label></td>
    	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE" defaultValue="${organsParty.type.id}" /></span></td>
    	<td class="title"><label>所属部门</label></td>
    	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" defaultValue="${organsParty.department.id}"/></span></td>
	</tr>
	<tr>
    	<td class="title"><label>党组织名称</label></td>
    	<td class="content"><span>${organsParty.name}</span></td>
    	<td class="title"><label>上级党组织</label></td>
    	<td class="content"><span>${organsParty.superior.name}</span></td>
	</tr>
	<tr>
		<td class="title"><label>联系人</label></td>
    	<td class="content"><span>${organsParty.contact}</span></td>
    	<td class="title"><label>联系电话</label></td>
    	<td class="content"><span>${organsParty.telephone}</span></td>
	</tr>
	<tr>
    	<td class="title"><label>备注</label></td>
    	<td class="content" colspan="3"><span>${organsParty.remark}</span></td>
	</tr>

</table>