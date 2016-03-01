<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="optimalPopulationOrgName">${partyOrgInfo.organization.orgName}</td>
  </tr>
  <tr>
     <td class="title"><label>党支部名称 </label></td>
     <td class="content"><span>${partyOrgInfo.partyBranchName}</span></td>
	 <td class="title"><label>党支部成立时间  </label></td>
	 <td class="content"><span><s:date name="partyOrgInfo.establishedDate" format="yyyy-MM-dd"/></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>党支部书记</label></td>
	 <td class="content"><span>${partyOrgInfo.partyBranchSecretary}</span></td>
     <td class="title"><label>身份证号码</label></td>
	 <td  colspan="2" class="content"><span>${partyOrgInfo.idCardNo}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>联系手机</label></td>
	 <td class="content"><span>${partyOrgInfo.mobileNumber}</span></td>
	 <td class="title"><label>联系电话</label></td>
	 <td colspan="2" class="content"><span>${partyOrgInfo.telephone}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>党员人数 </label></td>
	 <td class="content" class="content"><span>${partyOrgInfo.partyNumbers}</span></td>
	 <td class="title"><label>党组织地址</label></td>
	 <td colspan="2" class="content"><span>${partyOrgInfo.partyBranchAddr}</span></td>
  </tr>
</table>
