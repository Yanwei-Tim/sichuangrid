<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
	<tr>
		<td class="title"><label>所属网格</label></td>
		<td colspan="3" class="content" id="commonPopulationOrgName">${houseFamily.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>当前地址</label></td>
		<td colspan="3" class="content"><span>${houseFamily.censusRegisterFamily.currentAddress}</span></td>
	</tr>
	<tr>
		<td class="title"><label>户口号</label></td>
		<td class="content"><span>${houseFamily.censusRegisterFamily.accountNumber}</span></td>
		<td class="title"><label>身份证号码</label></td>
		<td class="content"><span>${houseFamily.censusRegisterFamily.idCardNo}</span></td>
	</tr>

	<tr>
		<td class="title"><label>户主姓名</label></td>
		<td class="content"><span>${houseFamily.censusRegisterFamily.householdName}</span></td>
		<td class="title"><label>户籍地</label></td>
		<td class="content"><span>${houseFamily.nativeLocation}</span></td>
	</tr>
	<tr>
		<td class="title"><label>联系手机</label></td>
		<td class="content"><span>${houseFamily.censusRegisterFamily.mobileNumber}</span></td>
		<td class="title"><label>固定电话</label></td>
		<td class="content"><span>${houseFamily.censusRegisterFamily.telePhone}</span></td>
	</tr>
	<tr>
		<td class="title"><label>家庭称号</label></td>
		<td colspan="3" class="content"><span><s:iterator value="%{houseFamily.houseMarchType}" id="houseMarch">${houseMarch.displayName} </s:iterator></span></td>
	</tr>

</table>
	<input type="hidden" id="houseFamilyId" value="${houseFamily.censusRegisterFamily.id}" maxlength="20" class="form-txt" disabled="disabled"/>
	<input type="hidden" id="orgId" value="${orgId}" maxlength="20" class="form-txt" disabled="disabled"/>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>现有成员</b>
	</div>
		<div style="clear: both;"></div>

		<table id="houseFamilyMemberList"></table>
		<div id="houseFamilyMemberDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
$(document).ready(function(){
	loadList();
	function loadList(){
		$("#houseFamilyMemberList").jqGridFunction({
			width:400,
			height:200,
			datatype: "local",
			colModel:[
				{name:"name",index:"name",label:"姓名",hidden:false},
				{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
				{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
				{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
				{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false}
			],
			multiselect:false,
			rowNum:Math.pow(2,31)-1,
		    shrinkToFit:true,
		    height:'auto'
		});
		$("#houseFamilyMemberList").jqGrid('setFrozenColumns');
		var initParam = {
			"orgId": $("#orgId").val(),
			"houseFamily.id": $("#houseFamilyId").val()
		}
		$("#houseFamilyMemberList").setGridParam({
			url:'${path}/baseinfo/householdStaff/findHouseholdStaffByOrgIdAndId.action?householdStaffVo.logout=0',
			datatype: "json",
			page:1
		});
		$("#houseFamilyMemberList").setPostData(initParam);
		$("#houseFamilyMemberList").trigger("reloadGrid");
	}
});
</script>