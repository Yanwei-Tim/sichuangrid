<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<% request.setAttribute("regActivitiesType",request.getParameter("regActivitiesType"));%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select id="logoutStatus" class="basic-input">
					<option value="-1">全部</option>
					<option value="0" selected="selected">未注销</option>
					<option value="1">已注销</option>
				</select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入身份证号码" name="searchTextMember" id="searchTextMember" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入身份证号码':this.value;" onfocus="value=(this.value=='请输入身份证号码')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<!-- <pop:JugePermissionTag ename="searchMemberEnroll">
			<a id="searchMemberEnroll" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag> -->
	    <span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addMemberEnroll">
			<a id="addMemberEnroll" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateMemberEnroll">
			<a id="updateMemberEnroll" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteMemberEnroll">
			<a id="deleteMemberEnroll" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewMemberEnroll">
			<a id="viewMemberEnroll" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="signMemberEnroll">
			<a id="signMemberEnroll" href="javascript:void(0)"><span>报到</span></a>
		</pop:JugePermissionTag>
		<a href="javascript:;" class="nav-dropdownBtn" id="setLogoutStatus"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="logoutMemberEnroll">
				<a id="logoutMemberEnroll" href="javascript:void(0)"><span>注销</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="unLogoutMemberEnroll">
				<a id="unLogoutMemberEnroll" href="javascript:void(0)"><span>取消注销</span></a>
			</pop:JugePermissionTag>
		</div>
		<a id="refreshMemberEnroll" href="javascript:void(0);"><span>刷新</span></a>
		<!--  
		<pop:JugePermissionTag ename="downMemberEnroll">
			<a id="downMemberEnroll" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		-->
	</div>
	<div style="width: 100%;">
		<table id="membersErollList"></table>
		<div id="membersErollListPager"></div>
	</div>
	<div id="memberErollDialog" style="overflow: auto"></div>
	<div id="claimVolunteerJobsDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="member.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="member.belongOrg" domainName="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" />

var regActivitiesType="${regActivitiesType}";
var dialogWidthMember = 820;
var dialogHeightMember = 500;
function callback(){
var dfop = {
	path:'${path}',
	orgLevel:'<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>',
	yesName:'<s:property value="@com.tianque.partyBuilding.doubleRegActivities.constant.YesOrNoType@YES_NAME"/>',
	noName:'<s:property value="@com.tianque.partyBuilding.doubleRegActivities.constant.YesOrNoType@NO_NAME"/>'
}
TQ.memberEnrollList(dfop)

}

$.cacheScript({
url:'/resource/getScript/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollList.js',
callback: callback
}) 
</script>