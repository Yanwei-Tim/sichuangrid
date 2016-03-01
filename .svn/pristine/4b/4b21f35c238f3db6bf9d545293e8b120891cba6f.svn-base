<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="5" class="content">${peopleAspirations.occurOrg.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>编号</label></td>
    <td class="content"  colspan="5" ><span>${peopleAspirations.serialNumber}</span></td>
   
  </tr>
  <tr>
  	<td class="title"><label>建表类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" defaultValue="${peopleAspirations.createTableType.id}" /></span></span></td>
    <td class="title"><label>网格号</label></td>
    <td class="content" colspan="3" ><span>${peopleAspirations.gridNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>登记人</label></td>
    <td class="content"><span>${peopleAspirations.registrant}</span></td>
    <td class="title"><label>登记时间</label></td>
    <td class="content" colspan="3" ><span><s:date name="peopleAspirations.registrationTime" format="yyyy-MM-dd HH:mm:ss" /></span></td>
  </tr>
  
  <tr>
	<td class="title"><label>登记单位</label></td>
 	<td class="content"><span>${peopleAspirations.bookingUnit}</span></td>
    <td class="title"><label>姓名</label></td>
    <td class="content" colspan="3" ><span>${peopleAspirations.name }</span></td>
  </tr>
  <tr>
    <td class="title"><label>身份证号</label></td>
    <td class="content"><span>${peopleAspirations.idCardNo }</span></td>
    <td class="title"><label>出生年月</label></td>
    <td class="content" colspan="3" ><span><s:date name="peopleAspirations.birthDay" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>性别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${peopleAspirations.gender.id}" /></span></td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" colspan="3" ><span>${peopleAspirations.mobileNumber }</span></td>
  </tr>
   <tr>
    <td class="title"><label>常住地址</label></td>
    <td class="content" colspan="5"><span>${peopleAspirations.permanentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>是否党员</label></td>
    <td class="content"><span><s:if test='true==peopleAspirations.isPartyMember'>是</s:if><s:else>否</s:else> </span></td>
    <td class="title"><label>职业或身份</label></td>
    <td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${peopleAspirations.position.id}" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>反应群体人数</label></td>
    <td class="content"><span>${peopleAspirations.responseGroupNo} &nbsp;</span></td>
    <td class="title"><label>诉求人类别</label></td>
    <td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@APPEAL_HUMAN_TYPE" defaultValue="${peopleAspirations.appealHumanType.id}" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>主要愿望或诉求</label></td>
    <td colspan="5" class="content"><span><pre>${peopleAspirations.appealContent}</pre></span></td>
  </tr>
	
   <tr>
    <td class="title"><label>项目类别</label></td>
    <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ITEM_CATEGORY" defaultValue="${peopleAspirations.itemCategory.id}" /></span></td>
    <td class="title"><label>项目子类</label></td>
    <td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ITEM_CATEGORY_SUB" defaultValue="${peopleAspirations.itemCategorySub.id}" /></span></td>
  </tr>

  <tr>
    <td class="title"><label>服务联系人</label></td>
    <td class="content"><span>${peopleAspirations.serverContractor}</span></td>
    <td class="title"><label>服务联系电话</label></td>
    <td class="content" colspan="3" ><span>${peopleAspirations.serverTelephone }</span></td>
  </tr>
  <tr>
   	<td class="title"><label>服务职务</label></td>
    <td class="content"><span>${peopleAspirations.serverJob}</span></td>
    <td class="title"><label>服务联系人单位</label></td>
    <td class="content" colspan="3"><span>${peopleAspirations.serverUnit }</span></td>
  </tr>
</table>
