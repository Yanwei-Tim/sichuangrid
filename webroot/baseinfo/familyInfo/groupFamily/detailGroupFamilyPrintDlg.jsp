<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="4" class="content" id="commonPopulationOrgName">${population.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>家庭地址</label></td>
    <td class="content" colspan="4"><span>${population.currentAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" /></span></td>
    <td class="title" ><label>出生日期</label></td>
    <td class="content" colspan="2"><span><s:date name="population.birthday" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content" colspan="2"><span>${population.telephone }</span></td>
  </tr>
 <tr>
    <td class="title"><label>常住地址</label></td>
    <td class="content" colspan="4"><span>${population.currentAddress}</span></td>
  </tr>
  <tr>
    <td class="title" colspan="5"><label>家庭信息</label></td>
  </tr>
  <tr>
    <td class="title"><label>家庭编号</label></td>
    <td class="content"><span>${population.groupFamily.familyAccount}</span></td>
    <td class="title"><label>与家长关系</label></td>
    <td class="content" colspan="2" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_MASTER" defaultValue="${population.groupFamily.familyRelation.id}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>家长姓名</label></td>
    <td class="content"><span>${population.groupFamily.familyMaster}</span></td>
    <td class="title"><label>家长证件号</label></td>
    <td colspan="2" class="content"><span>${population.groupFamily.masterCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>家长手机</label></td>
    <td class="content"><span>${population.groupFamily.masterMobileNumber}</span></td>
    <td class="title"><label>家长电话</label></td>
    <td colspan="2" class="content"><span>${population.groupFamily.masterTelephone}</span></td>
  </tr>
</table>
<div class='clearLine'></div>
<div class="grid_24">
	<b>现有成员</b>
</div>
	<div style="clear: both;"></div>

	<table id="groupFamilyMList"></table>

<input type="hidden" id="groupFamilyId" name="groupFamily.id" value="${groupFamily.id }">

<script type="text/javascript">

<pop:formatterProperty name="population.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
 
$(document).ready(function(){
	jQuery("#groupFamilyMList").jqGridFunction({
		datatype: "local",
	 	colNames:['id','人员姓名','与家长关系','证件号码','性别','常住地址','联系手机'],
	 	height:110,
	   	colModel:[
		   {name:'id',index:'id',frozen:true,hidden:true,key:true},
   	       {name:'population.name',width:80,sortable:false},
   	       {name:"familyRelation.displayName",width:100,sortable:false},
	       {name:'population.idCardNo',width:170,sortable:false},
	       {name:'population.gender',formatter:genderFormatter,width:50,align:'center',sortable:false},
	       {name:'population.currentAddress',width:220,sortable:false},
	       {name:'population.mobileNumber',width:120,sortable:false,align:'center'}
	   	],
	   	showColModelButton:false,
	   	rowNum:Math.pow(2,31)-1,
	    shrinkToFit:true,
	    height:'auto'
	});
	jQuery("#groupFamilyMList").jqGrid('setFrozenColumns');
	showList();
	
	
});

function showList() {
	var initParam = {
		"groupFamily.id": $("#groupFamilyId").val()
	};
	$("#groupFamilyMList").setGridParam({
		url:'${path}/baseinfo/familyInfo/findFamilyMembersByFamilyId.action',
		datatype: "json",
		page:1
	});
	$("#groupFamilyMList").setPostData(initParam);
	$("#groupFamilyMList").trigger("reloadGrid");
}
</script>