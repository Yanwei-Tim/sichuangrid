<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
 <%request.setAttribute("partyModule",request.getParameter("partyModule"));
 request.setAttribute("partyModule",((String)request.getAttribute("partyModule")).substring(0,1).toUpperCase()+request.getParameter("partyModule").substring(1));
 %> 
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入身份证号码" name="searchTextMember" id="searchTextMember" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入身份证号码':this.value;" onfocus="value=(this.value=='请输入身份证号码')?'':this.value;" />
				<button id="refreshSearchKeyMember" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButtonMember"><span>搜索</span></a>
	    <span class="lineBetween "></span>
		<pop:JugePermissionTag ename="view${partyModule }Member">
			<a id="viewMember" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reloadMember" href="javascript:void(0);"><span>刷新</span></a>
		<pop:JugePermissionTag ename="import${partyModule }Member">
			<a id="importMember" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="width: 100%;">
		<table id="memberList"></table>
		<div id="memberListPager"></div>
	</div>
	<div id="memberDialog" style="overflow: auto"></div>
</div>
<script type="text/javascript">
var dialogWidthMember = 800;
var dialogHeightMember = 550;
var partyOrgType='${param.partyOrgType}';
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
function viewMember(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#memberList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#memberDialog").createTabDialog({
		title:"查看成员信息",
		width: dialogWidthMember,
		height: dialogHeightMember,
		mode:'view',
		postData:{
			imageType:"population"
		},
		tabs:[
				{title:'成员信息',url:'${path}/partyBuildng/memberManage/dispatch.action?mode=view&member.partyOrgType='+partyOrgType+'&member.id='+id+"&member.orgId="+$("#currentOrgId").val()}
			]
	});
}
function callback(){
	var dfop = {
		path:'${path}',
		regionPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@REGION_PARTY_ORG"/>',
		officePartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@OFFICE_PARTY_ORG"/>',
		townPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@TOWN_PARTY_ORG"/>',
		twoNewPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@TWO_NEW_ORGNIZATION_PARTY_ORG"/>',
		systemPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@SYSTEM_PARTY_ORG"/>',
		doubleReg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@DOUBLE_REG_ACTIVITIES"/>',
		businessOrg:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@BUSINESS_ORG"/>',
		collectiveOrg:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@COLLECTIVE_ORG"/>',
		twoNewParty:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@TWO_NEW_PARTY"/>'
		
	}
	TQ.memberList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/members/memberList.js',
	callback: callback
})

</script>