<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
  <tr>
  	<td class="title"><label>姓名</label></td>
    <td class="content"><span>${poorPeopleMembers.name}</span></td>
  	<td class="title"><label>性别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${poorPeopleMembers.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>出生年月</label></td>
    <td class="content"><span><s:date name="poorPeopleMembers.birthday" format="yyyy-MM-dd"/></span></td>
    <td class="title"><label>政治面貌</label></td>
    <td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${poorPeopleMembers.politiCalbackGround.id}" /></td>
  </tr>
   <tr>
    <td class="title"><label>学历</label></td>
    <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${poorPeopleMembers.schooling.id}" /></span></td>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${poorPeopleMembers.nation.id}" /></span></td>
  </tr>

  <tr>
    <td class="title"><label>职业</label></td>
    <td class="content"><span>${poorPeopleMembers.career}</span></td>
    <td class="title"><label>健康状况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" defaultValue="${poorPeopleMembers.healthState.id}" /></span></td>
  </tr>
  <tr>
   	<td class="title"><label>与户主关系</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" defaultValue="${poorPeopleMembers.relationShipWithHead.id}" /></span></td>
    <td class="title"><label>纳入低保（五保）情况</label></td>
    <td class="content" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INSURANCETYPE" defaultValue="${poorPeopleMembers.insuranceType.id}" /></span></td>
  </tr>
</table>