<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="width: 100%;">
	<table id="targeOrgSelectList"></table>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="inflowingReason" domainName="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" />
<pop:formatterProperty name="registrationType" domainName="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="currentAddressType" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" />

$(document).ready(function(){
	jQuery("#targeOrgSelectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	   		{name:'uuid',index:'id',hidden:true,sortable:false},
	   		{name:"name", index:'name', label:'姓名',width:80 },
	        {name:'idCardNo',index:'idCardNo', label:'身份证号码', width:130},
	        {name:'gender',formatter:genderFormatter, label:'性别', align:'center', width:50},
	        {name:"usedName", index:'usedName', label:'曾用名/别名', width:80 },
	        {name:'mobileNumber', sortable:false, label:'联系手机', width:80},
	        {name:'telephone', sortable:false, label:'固定电话', width:80},
	        {name:'province',sortable:false,label:'户籍地',formatter:nativePlaceFormatter,width:150},
	        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150},
	        {name:'currentAddress', sortable:false, label:'常住地址', width:150 },
	        {name:"birthday", index:'birthday', label:'出生日期', width:80 },
	        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80},
	        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:80},
	        {name:"schooling",sortable:false,label:"文化程度",formatter:schoolingFormatter,width:80},
	        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80},
	        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:100},
	        {name:"maritalState",sortable:false,label:"婚姻状况",formatter:maritalStateFormatter,width:80},
	        {name:"stature",sortable:false,label:"身高",width:80 },
	        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80},
	        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80},
	        {name:"email",sortable:false,label:"电子邮箱",width:80},
	        {name:'otherAddress', sortable:false, label:'临时居所',width:150},
	        {name:'city',index:'city',hidden:true,sortable:false},
	        {name:'district',index:'district',hidden:true,sortable:false},
	        {name:'province',index:'province',hidden:true,sortable:false},
	        {name:'remark',index:'remark',hidden:true,sortable:false},
	        {name:'currentAddressType',index:'currentAddressType',hidden:true,sortable:false,formatter:currentAddressTypeFormatter},
	        {name:'community',index:'community',hidden:true,sortable:false},
	        {name:'block',index:'block',hidden:true,sortable:false},
	        {name:'unit',index:'unit',hidden:true,sortable:false},
	        {name:'room',index:'room',hidden:true,sortable:false}
	   	],
	   	width: 480,
		height: 300,
	    rowNum:-1,
	    pager:false
	});

	function onOrgChanged(){
		var name="";
		if($("#name").val()==undefined || $("#name").val()=="undefined" || $("#name").val()==null || $("#name").val()==""){
			name=$("#primaryOrganizationName").val();
		}else{
			name=$("#name").val();
		}
		$("#targeOrgSelectList").setGridParam({
			url:'${path}/commonPopulation/commonPopulationManage/findPopulationsByCardNoAndNameAndOrgId.action',
			datatype: "json",
			page:1
		});
		$("#targeOrgSelectList").setPostData({
			"orgId":$("#populationOrganizationId").val(),
			"idCardNo":$("#idCardNo").val(),
			"name":name
	   	});
		$("#targeOrgSelectList").trigger("reloadGrid");
	}
	onOrgChanged();
	function nativePlaceFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city;
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}
});
</script>
