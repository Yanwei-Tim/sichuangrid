<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>发生网格</label></td>
    <td colspan="3" class="content">${poorPeople.occurOrg.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>编号</label></td>
    <td class="content"><span>${poorPeople.serialNumber}</span></td>
  	<td class="title"><label>建表类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" defaultValue="${poorPeople.createTableType.id}" /></span></span></td>
  </tr>
  <tr>
  	<td class="title"><label>网格号</label></td>
    <td class="content"><span>${poorPeople.gridNo}</span></td>
    <td class="title"><label>登记人</label></td>
    <td class="content"><span>${poorPeople.registrant}</span></td>
  </tr>
  <tr>
	<td class="title"><label>登记单位</label></td>
 	<td class="content" colspan="3"><span>${poorPeople.bookingUnit}</span></td>
  </tr>
  <tr>
    <td class="title"><label>登记时间</label></td>
    <td class="content"><span><s:date name="poorPeople.registrationTime" format="yyyy-MM-dd HH:mm:ss" /></span></td>
    <td class="title"><label>姓名</label></td>
    <td class="content"><span>${poorPeople.name }</span></td>
  </tr>
  
  <tr>
    <td class="title"><label>身份证号</label></td>
    <td class="content"><span>${poorPeople.idCardNo }</span></td>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${poorPeople.mobileNumber }</span></td>
  </tr>
  <tr>
  	<td class="title"><label>性别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${poorPeople.gender.id}" /></span></td>
    <td class="title"><label>出生年月</label></td>
    <td class="content"><span><s:date name="poorPeople.birthDay" format="yyyy-MM-dd" /></span></td>
  </tr>
   <tr>
    <td class="title"><label>常住地址</label></td>
    <td class="content" colspan="3"><span>${poorPeople.permanentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>是否党员</label></td>
    <td class="content"><span><s:if test='true==poorPeople.isPartyMember'>是</s:if><s:else>否</s:else> </span></td>
    <td class="title"><label>职业或身份</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POSITIONORIDENTITY" defaultValue="${poorPeople.position.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>学历</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${poorPeople.schooling.id}" /></span></td>
    <td class="title"><label>纳入保险</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INSURANCETYPE" defaultValue="${poorPeople.insuranceType.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>家庭人口</label></td>
    <td class="content"><span>${poorPeople.memberNum}</span></td>
    <td class="title"><label>上年度人均年收入</label></td>
    <td class="content"><span>${poorPeople.lastYearMemberIncome}</span></td>
  </tr>
  <tr>
    <td class="title"><label>困难原因</label></td>
    <td class="content" colspan="3"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POORBIGINFO" defaultValue="${poorPeople.poorBigInfo.id}" />
    --&gt; <pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POORINFO" defaultValue="${poorPeople.poorInfo.id}" /></td>
  </tr>
  <tr>
    <td class="title"><label>帮扶需求</label></td>
    <td class="content" colspan="3"><span>${poorPeople.helpInfo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>年度帮扶项目</label></td>
    <td class="content" colspan="3"><span>${poorPeople.yearHelpInfo}</span></td>
  </tr>

  <tr>
    <td class="title"><label>服务联系人</label></td>
    <td class="content"><span>${poorPeople.serverContractor}</span></td>
    <td class="title"><label>服务联系电话</label></td>
    <td class="content"><span>${poorPeople.serverTelephone }</span></td>
  </tr>
  <tr>
    <td class="title"><label>职务</label></td>
    <td class="content"><span>${poorPeople.serverJob }</span></td>
    <td class="title"><label>服务联系人单位</label></td>
    <td class="content"><span>${poorPeople.serverUnit }</span></td>
  </tr>
</table>
