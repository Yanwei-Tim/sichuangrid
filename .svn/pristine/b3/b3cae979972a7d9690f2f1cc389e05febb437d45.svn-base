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
<div class="container_24">
	<input type="hidden" id="houseFamilyId" value="${houseFamily.censusRegisterFamily.id}" maxlength="20" class="form-txt" disabled="disabled"/>
	<input type="hidden" id="orgId" value="${orgId}" maxlength="20" class="form-txt" disabled="disabled"/>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>现有成员</b>
	</div>
	<div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="houseFamilyMemberList"></table>
			<div id="houseFamilyMemberListPager"></div>
		</div>
		<div id="houseFamilyMemberDialog"></div>
	</div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
$(document).ready(function(){
	function nameFont(el,options,rowData){
	    var moduleName = "HouseholdStaff";
		if(null == rowData.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
			return '<a href="javascript:;" <pop:JugePermissionTag ename="viewHouseholdStaff"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="red">'+rowData.name+'</font></a>';
		}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
			return '<a href="javascript:;"  <pop:JugePermissionTag ename="viewHouseholdStaff"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="#778899">'+rowData.name+'</font></a>';
		}
		return '<a href="javascript:;" <pop:JugePermissionTag ename="viewHouseholdStaff"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font></a>';
	}
	loadList();
	function loadList(){
		$("#houseFamilyMemberList").jqGridFunction({
			width:400,
			height:200,
			datatype: "local",
			colModel:[
				{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
				{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true,hidedlg:true},
				{name:"name",index:"name",label:"姓名",formatter:nameFont,hidden:false},
				{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
				{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
				{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
				{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false}
			],
			multiselect:false
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

function viewDialog(event,rowid){
	if(null == rowid){
 		return;
	}
	var rowData = $("#houseFamilyMemberList").getRowData(rowid);
	var rowId = rowData.encryptId;
	$("#houseHoldStaffViewDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看户籍人口详细信息',
		modal : true,
		url:'${path}/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+rowId,
		buttons: {
			"打印" : function(){
				printChoose(rowid);
	  	   	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
</script>