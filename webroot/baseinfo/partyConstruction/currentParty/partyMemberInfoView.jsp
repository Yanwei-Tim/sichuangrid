<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="optimalPopulationOrgName"><span>${population.organization.orgName}</span></td>
  </tr>
  <tr>
     <td class="title"><label>所属党支部 </label></td>
     <td class="content"><span>${population.partyOrgInfo.partyBranchName }</span></td>
	 <td class="title"><label>进入党支部时间  </label></td>
	 <td class="content"><span><s:date name="population.joinPartyBranchDate" format="yyyy-MM-dd"/></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>党员类型</label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PARTYMEMBER_TYPE" defaultValue="${population.partyMemberType.id}" /></span></td>
     <td class="title"><label>入党时间</label></td>
	 <td class="content"><span><s:date name="population.joinPartyDate" format="yyyy-MM-dd"/></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>入党时所在党支部</label></td>
	 <td class="content"><span>${population.joinPartyBranch}</span></td>
	 <td class="title"><label>转正时间</label></td>
	 <td class="content"><span><s:date name="population.becomesDate" format="yyyy-MM-dd"/></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>转正情况 </label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BECOMES_STATE" defaultValue="${population.becomesState.id}" /></span></td>
  	 <td class="title"><label>党内主要职务</label></td>
	 <td class="content"><span>${population.partyPosition}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>任职日期 </label></td>
	 <td class="content"><span id=""><s:date name="population.officeDate" format="yyyy-MM-dd"/></span></td>
  	 <td class="title"><label>培训情况</label></td>
	 <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TRAINING_STATE" defaultValue="${population.trainingState.id}" /></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>奖惩情况 </label></td>
	 <td class="content"><span id="">${population.rewardsPunishment}</span></td>
  	 <td class="title"><label>专长</label></td>
	 <td class="content"><span>${population.expertise}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>是否持流动党员证 </label></td>
	 <td class="content">
		<label class="form-check-text">
			<s:if test='1==population.isFlowPartyCard'>是</s:if>
			<s:else>否</s:else>
		 </label>
	 </td>
  	 <td class="title"><label>流入地（单位）党支部</label></td>
	 <td class="content"><span>${population.flowPartyBranch}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>流入时间</label></td>
	 <td class="content"><span id=""><s:date name="population.flowPartyBranchDate" format="yyyy-MM-dd"/></span></td>
  	 <td class="title"><label>党支部联系人</label></td>
	 <td class="content"><span>${population.partyBranchContacts}</span></td>
  </tr>
   <tr>
   	 <td class="title"><label>固定电话（党支部联系人）</label></td>
	 <td class="content"><span id="">${population.branchTelephone}</span></td>
  	 <td class="title"><label>联系手机（党支部联系人）</label></td>
	 <td class="content"><span>${population.branchMobileNumber}</span></td>
  </tr>

</table>