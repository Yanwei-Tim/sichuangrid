<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="5" class="content">${steadyWork.occurOrg.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>编号</label></td>
    <td class="content"><span>${steadyWork.serialNumber}</span></td>
    <td class="title"><label>建表类型</label></td>
    <td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" defaultValue="${steadyWork.createTableType.id}" /></span></span></td>
   
  </tr>
  <tr>
    <td class="title"><label>网格号</label></td>
    <td class="content"><span>${steadyWork.gridNo}</span></td>
    <td class="title"><label>登记人</label></td>
    <td class="content" colspan="3" ><span>${steadyWork.registrant}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>登记单位</label></td>
 	<td class="content"><span>${steadyWork.bookingUnit}</span></td>
    <td class="title"><label>登记时间</label></td>
    <td class="content" colspan="3" ><span><s:date name="steadyWork.registrationTime" format="yyyy-MM-dd HH:mm:ss" /></span></td>
  </tr>
  <c:forEach items="${steadyWork.peopleInfos }" var="peopleInfo">
  		 <tr>
		  	<td class="title"><label>姓名</label></td>
		    <td class="content"><span>${peopleInfo.name }</span></td>
		    <td class="title"><label>常住地址</label></td>
		   <td class="content" colspan="3"><span>${peopleInfo.permanentAddress }</span></td>
		  </tr>
		  <tr>
		    <td class="title"><label>身份证号</label></td>
		    <td class="content"><span>${peopleInfo.idCardNo }</span></td>
		    <td class="title"><label>出生年月</label></td>
		    <td class="content" colspan="3" ><span><fmt:formatDate value="${peopleInfo.birthDay}" type="date" pattern="yyyy-MM-dd" /></span></td>
		  </tr>
		  <tr>
		  	<td class="title"><label>性别</label></td>
		    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${peopleInfo.gender.id}" /></span></td>
		    <td class="title"><label>联系手机</label></td>
		    <td class="content" colspan="3" ><span>${peopleInfo.mobileNumber }</span></td>
		  </tr>
		
		  <tr>
		    <td class="title"><label>是否党员</label></td>
		    <td class="content"><span><c:choose><c:when test='${true==peopleInfo.isPartyMember }'>是</c:when><c:otherwise>否</c:otherwise></c:choose></span></td>
		    <td class="title"><label>职业或身份</label></td>
		    <td class="content" colspan="3" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${peopleInfo.position.id}" /></span></td>
		  </tr>
  </c:forEach>
 
  
  <tr>
    <td class="title"><label>涉稳群体人数</label></td>
    <td class="content"><span>${steadyWork.involvingSteadyNum} &nbsp;</span></td>
    <td class="title"><label>涉稳人群类别</label></td>
    <td colspan="3" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INVOLVING_STEADY_TYPE" defaultValue="${steadyWork.involvingSteadyType.id}" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>涉稳事项</label></td>
    <td colspan="5" class="content"><span><pre>${steadyWork.involvingSteadyInfo}</pre></span></td>
  </tr>
   <tr>
    <td class="title"><label>历次涉稳情况</label></td>
    <td colspan="5" class="content"><span><pre>${steadyWork.previousSteadyInfo}</pre></span></td>
  </tr>
   <tr>
    <td class="title"><label>历次化解情况</label></td>
    <td colspan="5" class="content"><span><pre>${steadyWork.previousResolveInfo}</pre></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>稳控责任单位</label></td>
    <td class="content"><span>${steadyWork.steadyUnit}</span></td>
    <td class="title"><label>稳控责任人</label></td>
    <td class="content" colspan="3" ><span>${steadyWork.steadyUser }</span></td>
  </tr>
   <tr>
    <td class="title"><label>化解责任部门</label></td>
    <td class="content"><span>${steadyWork.resolveUser}</span></td>
    <td class="title"><label>化解责任人</label></td>
    <td class="content" colspan="3" ><span>${steadyWork.resolveUser }</span></td>
  </tr>
  
  <tr>  <td class="title"><label>涉稳人员或群体稳定现状</label></td>
    <td class="content" colspan="5"><span>${steadyWork.steadyInfo}</span></td>
  	
  </tr>
	
   <tr>
    <td class="title"><label>诉求类别</label></td>
    <td  class="content"><span>${steadyWork.aspirationTypeName }</span></td>
    <td class="title"><label>预警级别</label></td>
    <td class="content" colspan="3" ><span>${steadyWork.warningTypeName }</span></td>
  </tr>

  <tr>
    <td class="title"><label>服务联系人</label></td>
    <td class="content"><span>${steadyWork.serverContractor}</span></td>
    <td class="title"><label>服务联系电话</label></td>
    <td class="content" colspan="3" ><span>${steadyWork.serverTelephone }</span></td>
  </tr>
  <tr>
  	<td class="title"><label>服务职务</label></td>
    <td class="content"><span>${steadyWork.serverJob}</span></td>
    <td class="title"><label>服务联系人单位</label></td>
    <td class="content" colspan="3"><span>${steadyWork.serverUnit }</span></td>
  </tr>
</table>
