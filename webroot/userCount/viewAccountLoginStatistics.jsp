<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
td{
text-align:center;
}
</style>
<table class="tablelist" id="infoList">
<tr>
<td class="title"><b>区域</b></td>
<td class="title" colspan="7"><span><b>${organization.orgName }下辖登录详情报表</b></span></td>
</tr>
<c:forEach items="${listVo}" var="vo">
<tr>
		 <td class="title" rowspan="13"><label>${vo.organization.orgName }</label></td>
		 <td class="title"><label><b>PC账号数</b></label></td>
		 <td class="title"><label><b>PC活跃度（上周）</b></label></td>
		 <td class="title"><label><b>PC活跃度（上月）</b></label></td>
		 <td class="title"><label><b>手机账号数</b></label></td>
		 <td class="title"><label><b>手机活跃度（上周）</b></label></td>
		 <td class="title"><label><b>手机活跃度（上月）</b></label></td>

</tr>
<tr>
<td class="title" colspan="7" ><label>行政部门</label></td>
</tr>
<tr>
		 <td class=""><label>${vo.executiveAccountPC }</label></td>
		 <td class=""><label>${vo.executiveWeekLivenessPC }</label></td>
		 <td class=""><label>${vo.executiveMonthLivenessPC }</label></td>
		 <td class=""><label>${vo.executiveAccountMobile }</label></td>
		 <td class=""><label>${vo.executiveWeekLivenessMobile }</label></td>
		 <td class=""><label>${vo.executiveMonthLivenessMobile }</label></td>
</tr>
<tr>
<td class="title" colspan="7" ><label>职能部门</label></td>
</tr>
<tr>
		 <td class=""><label>${vo.functionalAccountPC }</label></td>
		 <td class=""><label>${vo.functionalWeekLivenessPC }</label></td>
		 <td class=""><label>${vo.functionalMonthLivenessPC }</label></td>
		 <td class=""><label>${vo.functionalAccountMobile }</label></td>
		 <td class=""><label>${vo.functionalWeekLivenessMobile }</label></td>
		 <td class=""><label>${vo.functionalMonthLivenessMobile }</label></td>
</tr>
<tr>
<td class="title" colspan="7" ><label>城市</label></td>
</tr>
<tr>
		 <td class=""><label>${vo.cityAccountPC }</label></td>
		 <td class=""><label>${vo.cityWeekLivenessPC }</label></td>
		 <td class=""><label>${vo.cityMonthLivenessPC }</label></td>
		 <td class=""><label>${vo.cityAccountMobile }</label></td>
		 <td class=""><label>${vo.cityWeekLivenessMobile }</label></td>
		 <td class=""><label>${vo.cityMonthLivenessMobile }</label></td>
</tr>
<tr>
<td class="title" colspan="7" ><label>农村</label></td>
</tr>
<tr>
		 <td class=""><label>${vo.countrysideAccountPC }</label></td>
		 <td class=""><label>${vo.countrysideWeekLivenessPC }</label></td>
		 <td class=""><label>${vo.countrysideMonthLivenessPC }</label></td>
		 <td class=""><label>${vo.countrysideAccountMobile }</label></td>
		 <td class=""><label>${vo.countrysideWeekLivenessMobile }</label></td>
		 <td class=""><label>${vo.countrysideMonthLivenessMobile }</label></td>
</tr>
<tr>
<td class="title" colspan="7" ><label>合计</label></td>
</tr>
<tr>
		 <td class=""><label>${vo.accountPC }</label></td>
		 <td class=""><label>${vo.weekLivenessPC }</label></td>
		 <td class=""><label>${vo.monthLivenessPC }</label></td>
		 <td class=""><label>${vo.accountMobile }</label></td>
		 <td class=""><label>${vo.weekLivenessMobile }</label></td>
		 <td class=""><label>${vo.monthLivenessMobile }</label></td>
</tr>
<tr>
<td class="title" colspan="2" ><label>总账号数</label></td>
<td class="title" colspan="2" ><label>总活跃度（上周）</label></td>
<td class="title" colspan="2" ><label>总活跃度（上月）</label></td>
</tr>
<tr>
		 <td class="" colspan="2"><label>${vo.accountCount }</label></td>
		 <td class="" colspan="2"><label>${vo.livenessWeek }</label></td>
		 <td class="" colspan="2"><label>${vo.livenessMonth }</label></td>
</tr>
<tr>
<td colspan="8" style="background:rgb(202, 222, 252);"></td>
<tr>
</c:forEach>
</table>

<div id="showTaskListReply"></div>

