<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp" %>

<input id="accountNumber" type="hidden" name="accountNumber" value="${population.accountNumber}" />
<input type="hidden" id="_idCardNo"   />
<input type="hidden" id="_householdName"  />
<input type="hidden" id="_mobileNumber"  />
<input type="hidden" id="_telephone"  />
<input type="hidden" id="_homePhone"  />
<input type="hidden" id="_houseMarch"  />
<input id="outGoneValue" type="hidden" name="" value="${population.outGone}" />
<input type="hidden" id="_populationOrgId" value=${population.organization.id } />
<input type="hidden" id="houseFamilyId" value="${population.censusRegisterFamily.id}" />

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="housePopulationOrgName">${population.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>户口号</label></td>
    <td class="content"><span>${population.accountNumber}</span></td>
	<td class="title"><label>与户主关系</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" defaultValue="${population.relationShipWithHead.id}" /><c:if test="${population.relationShipWithHeadText!=null && population.relationShipWithHeadText!=''}">(${population.relationShipWithHeadText })</c:if></span></td>
  </tr>
    <tr>
    <td class="title"><label>户主姓名</label></td>
    <td class="content"><span id="name"></span></td>
	<td class="title"><label>户主身份证号码</label></td>
    <td class="content"><span id="idCard"></span></td>
  </tr>
  <tr>
	<td class="title"><label >户主手机</label></td>
    <td  class="content"><span id="mobleNumbe"></span></td>
    <td class="title"><label>户主固定电话</label></td>
    <td class="content"><span id="telPhone"></span></td>
  </tr>
  <tr>
	<td class="title"><label>家庭称号</label></td>
	<td class="content"><span id="houseMarch"></span></td>
    <td class="title"><label>住宅电话</label></td>
    <td class="content"><span id="homePhone"></span></td>
  </tr>
  <tr>
    <td class="title"><label>人户状态</label></td>
    <td class="content"><span ><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS" defaultValue="${population.residentStatus.id}" /></span></td>
    <td class="title"><label>是否外出</label></td>
    <td class="content"><span id="isout"></span></td>
  </tr>
  <tr>
    <td class="title"><label>外出时间</label></td>
    <td class="content"><span><s:date name="population.reasonsDate" format="yyyy-MM-dd" /></span></td>
 	<td class="title"><label>外出原因</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@OUT_REASON" defaultValue="${population.outReasons.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>外出去向</label></td>
    <td class="content"><span id="nativePlace">${population.outProvince}${population.outCity}${population.outDistrict}</span></td>
    <td class="title"><label>外出详址</label></td>
    <td class="content"><span>${population.goOutDetailedAddress }</span></td>
  </tr>
  <tr>
    <td class="title"><label>户口类别</label></td>
    <td colspan="3" class="content" id="population.actualtype">
    	<span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${population.residenceType.id}" /></span>
    </td>
  </tr>
</table>
<div class='grid_24'>
	<h3>现有成员</h3>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 99%;">
		<table id="houseFamilyMList"></table>
		<div id="houseFamilyMListPager"></div>
	</div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />

$(document).ready(function(){
	outGoneFormatter();
	whetherHousehold();
	idcardFormatter();
	nameFormatter();
	mobleFormatter();
	telPhoneFormatter();
	homephoneFormatter();
	houseMarchFormatter();
	loadList();
function loadList(){
	$("#houseFamilyMList").jqGridFunction({
		height:120,
		datatype: "local",
		colModel:[
			{name:"name",index:"name",label:"姓名",width:100,sortable:false},
			{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:50,align:"center"},
			{name:"idCardNo",index:"idCardNo",label:"身份证号码",sortable:false},
			{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",sortable:false},
			{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false}
		],
		showColModelButton:false
	});
	$("#houseFamilyMList").jqGrid('setFrozenColumns');
	var initParam = {
		"orgId": $("#_populationOrgId").val(),
		"houseFamily.id": $("#houseFamilyId").val()
	}
	$("#houseFamilyMList").setGridParam({
		url:'${path}/baseinfo/householdStaff/findHouseholdStaffByOrgIdAndId.action?householdStaffVo.logout=0',
		datatype: "json",
		page:1
	});
	$("#houseFamilyMList").setPostData(initParam);
	$("#houseFamilyMList").trigger("reloadGrid");
}
});
function outGoneFormatter(){
	var str = "";
	if($("#outGoneValue").val()=="true"||$("#outGoneValue").val()==true)
		str += "是";
	if($("#outGoneValue").val() =="false"||$("#outGoneValue").val()==false)
		str += "否";
	$("#isout").html(str);
}
function idcardFormatter(){
	$("#idCard").html($("#_idCardNo").val());
}
function nameFormatter(){
	$("#name").html($("#_householdName").val());
}
function mobleFormatter(){
	$("#mobleNumbe").html($("#_mobileNumber").val());
}
function telPhoneFormatter(){
	$("#telPhone").html($("#_telephone").val());
}
function homephoneFormatter(){
	$("#homePhone").html($("#_homePhone").val());
}
function houseMarchFormatter(){
	$("#houseMarch").html($("#_houseMarch").val());
}
function whetherHousehold(){
	$.ajax({
		async : false,
		url : "${path }/baseinfo/householdStaff/getCensusRegisterFamilyByOrgIdAndAccountNumber.action?orgId="+$("#_populationOrgId").val()+"&householdStaffVo.accountNumber="+$("#accountNumber").val(),
		success : function(data) {
			if(data != null){
				$("#_idCardNo").val(data.idCardNo);
				$("#_householdName").val(data.householdName);
				$("#_mobileNumber").val(data.mobileNumber);
				$("#_telephone").val(data.telePhone);
				$("#_homePhone").val(data.homePhone);
				if(data.houseMarchList != null && data.houseMarchList != "" && data.houseMarchList!=undefined){
					if(data.houseMarchList[1] != null){
						$("#_houseMarch").val(data.houseMarchList[0].displayName+','+data.houseMarchList[1].displayName)
					}else{
						$("#_houseMarch").val(data.houseMarchList[0].displayName)
					}
				}
			}
		}
    });
}

</script>
<style type="text/css">
.label {
	color: #999;
}
</style>