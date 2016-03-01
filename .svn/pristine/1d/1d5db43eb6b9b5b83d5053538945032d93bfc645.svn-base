<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="4" class="content" id="commonPopulationOrgName">${houseInfo.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>房屋编号</label></td>
    <td class="content"><span>${houseInfo.houseCode }</span></td>
    <td class="title"><label>房屋用途</label></td>
    <td class="content" colspan="2" ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" defaultValue="${houseInfo.houseUses.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>房屋准确地址</label></td>
    <td class="content"><span>${houseInfo.address}</span></td>
    <td class="title"><label>房产证地址</label></td>
    <td class="content" colspan="2"><span>${houseInfo.houseAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>产权人类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" defaultValue="${houseInfo.propertyTypes.id}" /></span></td>
    <td class="title"><label>产权人名称</label></td>
    <td colspan="2" class="content"><span>${houseInfo.name}</span></td>
  </tr>
  <tr>
    <td class="title"><label>产权人证件类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" defaultValue="${houseInfo.certificateType.id}" /> </span></td>
    <td class="title"><label>证件号码</label></td>
    <td colspan="2" class="content"><span>${houseInfo.certificateNumbe}</span></td>
  </tr>
  <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${houseInfo.propertyPersonTel}</span></td>
    <td class="title"><label>联系手机</label></td>
    <td colspan="2" class="content"><span>${houseInfo.propertyPersonMobile}</span></td>
  </tr>
  <c:if test='${true==houseInfo.isRentalHouse }'>
	<tr>
      <td class="title"><label>是否为出租房</label></td>
      <td class="content" ><span><s:if test='true==houseInfo.isRentalHouse'>是</s:if><s:else>否</s:else></span></td>
      <td class="title"><label>隐患等级</label></td>
      <td colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" defaultValue="${houseInfo.hiddenDangerLevel.id}" /></span></td>
	</tr>
    <tr>
      <td class="title"><label>出租用途</label></td>
      <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" defaultValue="${houseInfo.usage.id}" /></span></td>
      <td class="title"><label>出租人</label></td>
      <td colspan="2" class="content"><span>${houseInfo.rentalPerson}</span></td>
    </tr>
    <tr>
      <td class="title"><label>出租人电话</label></td>
      <td class="content"><span>${houseInfo.rentalTelephone}</span></td>
      <td class="title"><label>出租人手机</label></td>
      <td colspan="2" class="content"><span>${houseInfo.rentalMobileNumber}</span></td>
    </tr>
	<tr>
      <td class="title"><label>出租人地址</label></td>
      <td class="content" colspan="4" ><span>${houseInfo.lessorAddress}</span></td>
    </tr>
  </c:if>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content">${houseInfo.remark}</td>
  </tr>
</table>
<div class='clearLine'></div>
<div class='grid_24'>
	<b>现有住户</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="liveInHousePopulationList"></table>
		<div id="liveInHousePopulationListPager"></div>
	</div>
</div>
<input type="hidden" id="baseUrl" value="${path} "/>

<script type="text/javascript">
$(document).ready(function(){
	$("#liveInHousePopulationList").jqGridFunction({
		url:'${path}/baseinfo/actualHousePopulation/findLivingInHousePopulationInfos.action',
		postData:{
			"houseId":'${houseInfo.encryptId}'
		},
		datatype: "json",
		width:720,
		height:160,
   		colModel:[
			{name:"sid",index:'sid',width:50,hidedlg:true,sortable:false,hidden:true,key:true},
        	{name:"id",index:"id",hidden:true},
  			{name:"certificateNumber",index:"certificateNumber",label:"证件号码",sortable:false,width:170},
        	{name:'personName',index:"personName",label:"姓名",sortable:false,width:80},
        	{name:'genderName',index:"genderName",label:"性别",sortable:false,width:50,align:"center"},
        	{name:'type.displayName',index:"type.displayName",label:"人员类型",sortable:false,width:80},
        	{name:'householdPlace',index:"householdPlace",label:"户籍地",sortable:false,width:180},
  			{name:"workCompany",index:"workCompany",label:"工作单位或就读学校",sortable:false,width:200}
		],
		showColModelButton:false
	});

  	jQuery("#liveInHousePopulationList").jqGrid('setFrozenColumns');
  	

});

</script>
