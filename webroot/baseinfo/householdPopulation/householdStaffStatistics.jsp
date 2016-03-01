<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />

<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>

<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER"  />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
<pop:formatterProperty name="outReasons" domainName="@com.tianque.domain.property.PropertyTypes@OUT_REASON" />


var isgridBol = false;
var title=$("#title").html();
function householdRegisterFormatter(el, options, rowData){
	var str = "";
	if(rowData.province != null)
		str += rowData.province;
	if(rowData.city != null)
		str += rowData.city
	if(rowData.district != null)
		str += rowData.district;
	return str;
}
function outProvinceFormatter(el, options, rowData){
	var str = "";
	if(rowData.outProvince != null)
		str += rowData.outProvince;
	if(rowData.outCity != null)
		str += rowData.outCity
	if(rowData.outDistrict != null)
		str += rowData.outDistrict;
	return str;
}
function rendIsOutGone(outGone){
	if(true==outGone || "true" == outGone){
		return "是";
	}else{
		return "否";
	}
}
function rendIsRepeat(el, options, rowData){
	if(1==rowData.isRepeat){
			return "是";
		}else{
			return "否";
		}
	}


function nameFont(el,options,rowData){
	if(rowData.death == true || rowData.death == "true"){
		return "<pop:JugePermissionTag ename="viewHouseholdStaff"><a href='javascript:viewHouseholdStaff("+rowData.id+")'><font color='red'>"+rowData.name+"</font></a></pop:JugePermissionTag>";
	}
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<pop:JugePermissionTag ename="viewHouseholdStaff"><a href='javascript:viewHouseholdStaff("+rowData.id+")'><font color='#778899'>"+rowData.name+"</font></a></pop:JugePermissionTag>";
	}
	return "<pop:JugePermissionTag ename="viewHouseholdStaff"><a href='javascript:viewHouseholdStaff("+rowData.id+")'><font color='#6633FF'>"+rowData.name+"</font></a></pop:JugePermissionTag>";
}

function idCardNoFont(el,options,rowData){
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}else{
		return "<font color='#000'>"+rowData.idCardNo+"</font>";
	}
}

function operateFormatter(el, options, rowData){
	return "<pop:JugePermissionTag ename="viewHouseholdStaff"><a href='javascript:viewHouseholdStaff("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
}
function reasonsDateFormatter(el,options,rowData){
	if(rowData.reasonsDate != null){
		var date = new Date(rowData.reasonsDate);
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		return year+"-"+month+"-"+day;
	}else{
		return "";
	}
}

$(document).ready(function(){
		jQuery("#householdStaffList").jqGridFunction({
			datatype: "local",
			colNames:['id','encryptId','操作','姓名','性别','身份证号码','常住地址','所属区域(网格)','户籍地','户口号','工作单位或就读学校','与户主关系','数据来源','数据更新时间',
		 	          '曾用名','出生日期','户籍地详址','临时居所','联系手机','固定电话','职业',
		 	          '政治面貌','户口类别','婚姻状况','身高(cm)','民族','宗教信仰','文化程度','血型','电子邮箱',
		 	          '是否外出','外出原因','外出时间','外出去向','外出详址','是否死亡','是否注销'],
		   	colModel:[
		   	       {name:"id",index:"id",hidden:true,frozen:true},
		   	       {name:"encryptId",index:"id",hidden:true,frozen:true},
		   	       {name:"operation",formatter:operateFormatter,frozen:true,width:50,sortable:false},
			       {name:"name",index:'name',width:100,frozen:true,formatter:nameFont,sortable:true},
			       {name:'gender',sortable:false,align:'center',formatter:genderFormatter,width:70},
			       {name:'idCardNo',index:'idCardNo',width:130,formatter:idCardNoFont,sortable:true,frozen:true},
			       {name:'currentAddress',index:'currentAddress',sortable:false},
			       {name:'organization.orgName',index:'organization.orgName',hidden:true,sortable:false},
			       {name:'province',index:'province',formatter:householdRegisterFormatter,width:150,hidden:true,sortable:false},
			       {name:'accountNumber',index:'accountNumber',width:90,hidden:true,sortable:false},
			       {name:'workUnit',index:'workUnit',sortable:true},
			       {name:'relationShipWithHead',index:'relationShipWithHead',formatter:relationShipWithHeadFormatter,sortable:false},
			       {name:'sourcesState',index:'sourcesState',sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			       {name:'updateDate', sortable:false, width:140},
			       {name:'usedName',index:'usedName',width:70,hidden:true,sortable:false},
			       {name:'birthday',index:'birthday',width:70,hidden:true,sortable:true},
			       {name:'nativePlaceAddress',index:'nativePlaceAddress',width:150,hidden:true,sortable:false},
			       {name:'otherAddress',index:'otherAddress',width:150,hidden:true,sortable:false},
			       {name:'mobileNumber',index:'mobileNumber',width:50,hidden:true,sortable:false},
			       {name:'telephone',index:'telephone',width:50,hidden:true,sortable:false},
			       {name:'career',index:'career',width:100,sortable:false,hidden:true,formatter:careerFormatter},
			      
			       {name:'politicalBackground',index:'politicalBackground',formatter:politicalBackgroundFormatter,width:70,hidden:true,sortable:false},
			       {name:'residenceType',index:'residenceType',formatter:residenceTypeFormatter,width:70,hidden:true,sortable:false},
			       {name:'maritalState',index:'maritalState',formatter:maritalStateFormatter,width:70,hidden:true,sortable:false},
			       {name:'stature',index:'stature',width:70,hidden:true,sortable:false},
			       {name:'nation',index:'nation',width:70,formatter:nationFormatter,hidden:true,sortable:false},
			       {name:'faith',index:'faith',formatter:faithFormatter,width:70,hidden:true,sortable:false},
			       {name:'schooling',index:'schooling',formatter:schoolingFormatter,width:70,hidden:true,sortable:false},
			       {name:'bloodType',index:'bloodType',formatter:bloodTypeFormatter,width:70,hidden:true,sortable:false},
			       {name:'email',index:'email',width:70,hidden:true,sortable:false},
			       {name:'outGone',sortable:false,hidden:true,width:70,formatter:rendIsOutGone},
			       {name:'outReasons',index:'outReasons',width:70,hidden:true,sortable:false,formatter:outReasonsFormatter},
			       {name:'reasonsDate',index:'reasonsDate',width:70,hidden:true,sortable:false},
			       {name:'outProvince',index:'outProvince',width:70,hidden:true,sortable:false,formatter:outProvinceFormatter},
			       {name:'goOutDetailedAddress',index:'goOutDetailedAddress',width:70,hidden:true,sortable:false},
			       {name:'death',sortable:false,hidedlg:true,hidden:true,width:100},
			       {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80}
		   	],
		   	width:870,
		   	height:450,
		   	multiselect:true,
		   	onSelectAll:function(aRowids,status){ if(status) {
		   		var selectedIds = $("#householdStaffList").jqGrid("getGridParam", "selarrrow");
		   		var cancelLogOut = 0;
	   			var isLogOut = 0;
		   		for(var i = 0;i<selectedIds.length;i++){
	   			var row=$("#householdStaffList").getRowData(selectedIds[i]);
	   				if(selectedIds.length ==1){
			   			$("#view").buttonEnable();
						$("#update").buttonEnable();
					}
	   				if(selectedIds.length >2){
	   					$("#view").buttonDisable();
						$("#update").buttonDisable();
						$("#cancelLogOut").buttonDisable();
						$("#isLogOut").buttonDisable();
	   				}
	   				if(selectedIds.length != 0){
				   		$("#delete").buttonEnable();if(isGrid()){
						$("#shift").buttonEnable();
				   		}
			   		}
			   		if(row.logOut == 1 ||row.logOut == "1" ){
			   			cancelLogOut++;
			   		}else{
			   			isLogOut++;
			   		}
	   			}
	   			if(cancelLogOut>0&&isLogOut==0){
	   				$("#cancelLogOut").buttonEnable();
					$("#isLogOut").buttonDisable();
	   			}else if(cancelLogOut==0&&isLogOut>0){
	   				$("#cancelLogOut").buttonDisable();
					$("#isLogOut").buttonEnable();
	   			}else{
	   				$("#cancelLogOut").buttonDisable();
					$("#isLogOut").buttonDisable();
	   			}
		   	}else{
		   		$("#delete").buttonDisable();
         		$("#shift").buttonDisable();
	   			$("#view").buttonDisable();
				$("#update").buttonDisable();
				$("#cancelLogOut").buttonDisable();
				$("#isLogOut").buttonDisable();
		   	}},
		   	altclass:true,
		   	loadComplete: afterLoad,
		   	<pop:JugePermissionTag ename="viewHouseholdStaff">
		    ondblClickRow: viewHouseholdStaff,
		    </pop:JugePermissionTag>
		    onSelectRow:selectRow
			});
		jQuery("#householdStaffList").jqGrid('setFrozenColumns');
		$("#householdStaffList").closest(".ui-jqgrid-bdiv").css({ "height" : $("#householdStaffListDiv").parent().height()-70});
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if('<s:property value="#parameters.isSearch"/>' == 1){
				refreshPositiveInfoGrid();
			}else{
				search(getOrgIdForStat());
			}
		}
		
		function getOrgIdForStat(){
			var orgIdForStat = $("#orgIdForStat").val();
			if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
				return getCurrentOrgId();
			}else{
				return orgIdForStat;
			}
		}

});

function viewHouseholdStaff(rowid){
	if(rowid==null){
 		return;
	}
	var houseStaff =  $("#householdStaffList").getRowData(rowid);
	$("#viewHouseholdStaffPopulationDialog").createDialog({
		width: 800,
		height: 600,
		title:title+'户籍人口信息',
		url:'${path}/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+houseStaff.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	}).closest(".ui-dialog").css("z-index",9999);
}

function print(rowid){
	$("#householdStaffPopulationDialog").createDialog({
		width: 800,
		height: 605,
		title:'打印'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/positiveInfo/getPositiveInfosById.action?mode=print&positiveInfos.id='+rowid,
		buttons: {
			 "确定" : function(){
			$("#positiveInfoPrint").printArea();
        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function refreshPositiveInfoGrid() {
	var name=$("#householdStaffVo\\.name").val();
	var idCardNo=$("#householdStaffVo\\.idCardNo").val();
	var gender=$("#householdStaffVo\\.gender").val();
	var accountNumber=$("#householdStaffVo\\.accountNumber").val();
	var workUnit=$("#householdStaffVo\\.workUnit").val();
	var outGoneBoolean=$("#householdStaffVo\\.outGoneBoolean").val();
	var telephone=$("#householdStaffVo\\.telephone").val();
	var mobileNumber=$("#householdStaffVo\\.mobileNumber").val();
	var schooling=$("#householdStaffVo\\.schooling").val();
	var birthday=$("#householdStaffVo\\.birthday").val();
	var endBirthday=$("#householdStaffVo\\.endBirthday").val();
	var usedName=$("#householdStaffVo\\.usedName").val();
	var career=$("#householdStaffVo\\.career").val();
	var politicalBackground=$("#householdStaffVo\\.politicalBackground").val();
	var maritalState=$("#householdStaffVo\\.maritalState").val();
	var residenceType=$("#householdStaffVo\\.residenceType").val();
	var residentStatus=$("#_residentStatus").val();
	var nation=$("#householdStaffVo\\.nation").val();
	var faith=$("#householdStaffVo\\.faith").val();
	var bloodType=$("#householdStaffVo\\.bloodType").val();
	var email=$("#householdStaffVo\\.email").val();
	var province=$("#householdStaffVo\\.province").val();
	var city=$("#householdStaffVo\\.city").val();
	var district=$("#householdStaffVo\\.district").val();
	var currentAddress=$("#householdStaffVo\\.currentAddress").val();
	var nativePlaceAddress=$("#householdStaffVo\\.nativePlaceAddress").val();
	var otherAddress=$("#householdStaffVo\\.otherAddress").val();
	var outReasonsId=$("#householdStaffVo\\.outReasonsId").val();
	var outProvince=$("#householdStaffVo\\.outProvince").val();
	var outCity=$("#householdStaffVo\\.outCity").val();
	var outDistrict=$("#householdStaffVo\\.outDistrict").val();
	var logoutDateStart=$("#householdStaffVo\\.logoutDateStart").val();
	var logoutDateEnd=$("#householdStaffVo\\.logoutDateEnd").val();
	var logoutReason=$("#householdStaffVo\\.logoutReason").val();
	var initParam = {
		"orgId": getCurrentOrgId()
	}
	if($("#logout").val()=="-1"){
		if($("#_isDeath").val()=="-1"){
			initParam = {
				"orgId": getCurrentOrgId(),
				"householdStaffVo.name":name,
				"householdStaffVo.idCardNo":idCardNo,
				"householdStaffVo.gender":gender,
				"householdStaffVo.accountNumber":accountNumber,
				"householdStaffVo.workUnit":workUnit,
				"householdStaffVo.outGoneBoolean":outGoneBoolean,
				"householdStaffVo.telephone":telephone,
				"householdStaffVo.mobileNumber":mobileNumber,
				"householdStaffVo.schooling":schooling,
				"householdStaffVo.birthday":birthday,
				"householdStaffVo.endBirthday":endBirthday,
				"householdStaffVo.usedName":usedName,
				"householdStaffVo.career":career,
				"householdStaffVo.politicalBackground":politicalBackground,
				"householdStaffVo.maritalState":maritalState,
				"householdStaffVo.residenceType":residenceType,
				"householdStaffVo.residentStatus":residentStatus,
				"householdStaffVo.nation":nation,
				"householdStaffVo.faith":faith,
				"householdStaffVo.bloodType":bloodType,
				"householdStaffVo.email":email,
				"householdStaffVo.province":province,
				"householdStaffVo.city":city,
				"householdStaffVo.district":district,
				"householdStaffVo.currentAddress":currentAddress,
				"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
				"householdStaffVo.otherAddress":otherAddress,
				"householdStaffVo.outReasonsId":outReasonsId,
				"householdStaffVo.outProvince":outProvince,
				"householdStaffVo.outCity":outCity,
				"householdStaffVo.outDistrict":outDistrict,
				"householdStaffVo.logoutDateStart":logoutDateStart,
				"householdStaffVo.logoutDateEnd":logoutDateEnd,
				"householdStaffVo.logoutReason":logoutReason
				}
		}else{
			initParam = {
				"orgId": getCurrentOrgId(),
				"householdStaffVo.name":name,
				"householdStaffVo.idCardNo":idCardNo,
				"householdStaffVo.gender":gender,
				"householdStaffVo.accountNumber":accountNumber,
				"householdStaffVo.workUnit":workUnit,
				"householdStaffVo.outGoneBoolean":outGoneBoolean,
				"householdStaffVo.telephone":telephone,
				"householdStaffVo.mobileNumber":mobileNumber,
				"householdStaffVo.schooling":schooling,
				"householdStaffVo.birthday":birthday,
				"householdStaffVo.endBirthday":endBirthday,
				"householdStaffVo.usedName":usedName,
				"householdStaffVo.career":career,
				"householdStaffVo.politicalBackground":politicalBackground,
				"householdStaffVo.maritalState":maritalState,
				"householdStaffVo.residenceType":residenceType,
				"householdStaffVo.residentStatus":residentStatus,
				"householdStaffVo.nation":nation,
				"householdStaffVo.faith":faith,
				"householdStaffVo.bloodType":bloodType,
				"householdStaffVo.email":email,
				"householdStaffVo.province":province,
				"householdStaffVo.city":city,
				"householdStaffVo.district":district,
				"householdStaffVo.currentAddress":currentAddress,
				"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
				"householdStaffVo.otherAddress":otherAddress,
				"householdStaffVo.isDeath":$("#_isDeath").val(),
				"householdStaffVo.outReasonsId":outReasonsId,
				"householdStaffVo.outProvince":outProvince,
				"householdStaffVo.outCity":outCity,
				"householdStaffVo.outDistrict":outDistrict,
				"householdStaffVo.logoutDateStart":logoutDateStart,
				"householdStaffVo.logoutDateEnd":logoutDateEnd,
				"householdStaffVo.logoutReason":logoutReason
				}
			}
	}else{
		if($("#_isDeath").val()=="-1"){
			initParam = {
				"orgId":  getCurrentOrgId(),
				"householdStaffVo.name":name,
				"householdStaffVo.idCardNo":idCardNo,
				"householdStaffVo.gender":gender,
				"householdStaffVo.accountNumber":accountNumber,
				"householdStaffVo.workUnit":workUnit,
				"householdStaffVo.outGoneBoolean":outGoneBoolean,
				"householdStaffVo.telephone":telephone,
				"householdStaffVo.mobileNumber":mobileNumber,
				"householdStaffVo.schooling":schooling,
				"householdStaffVo.birthday":birthday,
				"householdStaffVo.endBirthday":endBirthday,
				"householdStaffVo.usedName":usedName,
				"householdStaffVo.career":career,
				"householdStaffVo.politicalBackground":politicalBackground,
				"householdStaffVo.maritalState":maritalState,
				"householdStaffVo.residenceType":residenceType,
				"householdStaffVo.residentStatus":residentStatus,
				"householdStaffVo.nation":nation,
				"householdStaffVo.faith":faith,
				"householdStaffVo.bloodType":bloodType,
				"householdStaffVo.email":email,
				"householdStaffVo.province":province,
				"householdStaffVo.city":city,
				"householdStaffVo.district":district,
				"householdStaffVo.currentAddress":currentAddress,
				"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
				"householdStaffVo.otherAddress":otherAddress,
				"householdStaffVo.logout":$("#logout").val(),
				"householdStaffVo.outReasonsId":outReasonsId,
				"householdStaffVo.outProvince":outProvince,
				"householdStaffVo.outCity":outCity,
				"householdStaffVo.outDistrict":outDistrict,
				"householdStaffVo.logoutDateStart":logoutDateStart,
				"householdStaffVo.logoutDateEnd":logoutDateEnd,
				"householdStaffVo.logoutReason":logoutReason
				
				}
		}else{
			initParam = {
				"orgId": getCurrentOrgId(),
				"householdStaffVo.name":name,
				"householdStaffVo.idCardNo":idCardNo,
				"householdStaffVo.gender":gender,
				"householdStaffVo.accountNumber":accountNumber,
				"householdStaffVo.workUnit":workUnit,
				"householdStaffVo.outGoneBooleanBoolean":outGoneBoolean,
				"householdStaffVo.telephone":telephone,
				"householdStaffVo.mobileNumber":mobileNumber,
				"householdStaffVo.schooling":schooling,
				"householdStaffVo.birthday":birthday,
				"householdStaffVo.endBirthday":endBirthday,
				"householdStaffVo.usedName":usedName,
				"householdStaffVo.career":career,
				"householdStaffVo.politicalBackground":politicalBackground,
				"householdStaffVo.maritalState":maritalState,
				"householdStaffVo.residenceType":residenceType,
				"householdStaffVo.residentStatus":residentStatus,
				"householdStaffVo.nation":nation,
				"householdStaffVo.faith":faith,
				"householdStaffVo.bloodType":bloodType,
				"householdStaffVo.email":email,
				"householdStaffVo.province":province,
				"householdStaffVo.city":city,
				"householdStaffVo.district":district,
				"householdStaffVo.currentAddress":currentAddress,
				"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
				"householdStaffVo.otherAddress":otherAddress,
				"householdStaffVo.isDeath":$("#_isDeath").val(),
				"householdStaffVo.logout":$("#logout").val(),
				"householdStaffVo.outReasonsId":outReasonsId,
				"householdStaffVo.outProvince":outProvince,
				"householdStaffVo.outCity":outCity,
				"householdStaffVo.outDistrict":outDistrict,
				"householdStaffVo.logoutDateStart":logoutDateStart,
				"householdStaffVo.logoutDateEnd":logoutDateEnd,
				"householdStaffVo.logoutReason":logoutReason
				}
		}
	}
	$("#householdStaffList").setGridParam({
		url:'${path}/baseinfo/householdStaff/findHouseholdStaffByOrgId.action',
		datatype : 'json',
		page:1
	});
	$("#householdStaffList").setPostData(initParam);
	$("#householdStaffList").trigger("reloadGrid");
	$("#RESIDENTstatisticsDialog").dialog("close");
}

function afterLoad(){
	logOutFormatter();
}

function logOutFormatter(){
	var idCollection = new Array();
	idCollection=$("#householdStaffList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#householdStaffList").getRowData(idCollection[i]);
			if(ent.logOut==1||ent.logOut=='1'){
				$("#householdStaffList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
	}
}


function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#householdStaffList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("householdStaffList");
	var count = $("#householdStaffList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("householdStaffList", true);
	}else{
		jqGridMultiSelectState("householdStaffList", false);
	}
	if(selectedCounts==1){
		$("#view").buttonEnable();
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
		var selectedId = getSelectedIdLast();
		var householdStaff =  $("#householdStaffList").getRowData(selectedId);
		if(householdStaff.logOut==1 ||householdStaff.logOut=="1"){
			$("#cancelLogOut").buttonEnable();
			$("#isLogOut").buttonDisable();
		}else{
			$("#isLogOut").buttonEnable();
			$("#cancelLogOut").buttonDisable();
		}
	}else{
		$("#view").buttonDisable();
		$("#update").buttonDisable();
		var selectedIds = $("#householdStaffList").jqGrid("getGridParam", "selarrrow");
   		var cancelLogOut = 0;
   		var isLogOut = 0;
   		for(var i = 0;i<selectedIds.length;i++){
   			var row=$("#householdStaffList").getRowData(selectedIds[i]);
		   	if(row.logOut == 1 ||row.logOut == "1" ){
		   		cancelLogOut++;
		   	}else{
		   		isLogOut++;
		   	}
   		}
   		if(cancelLogOut>0&&isLogOut==0){
   			$("#cancelLogOut").buttonEnable();
			$("#isLogOut").buttonDisable();
   		}else if(cancelLogOut==0&&isLogOut>0){
   			$("#cancelLogOut").buttonDisable();
			$("#isLogOut").buttonEnable();
   		}else{
   			$("#cancelLogOut").buttonDisable();
			$("#isLogOut").buttonDisable();
   		}
	}
	if(isGrid()){
		$("#shift").buttonEnable();
	}else{
		$("#shift").buttonDisable();
	}
	if(selectedCounts==0){
		$("#shift").buttonDisable();
		$("#delete").buttonDisable();
		$("#cancelLogOut").buttonDisable();
		$("#isLogOut").buttonDisable();
	}
}

function getSelectedIds(){
	var selectedIds = getActualjqGridMultiSelectIds("householdStaffList");
	return selectedIds;
}


function search(orgId){
	 var conditions = $("#searchByCondition").val();
	 var initParam = {
		"orgId": orgId
	 }
	 if(conditions == '请输入姓名或身份证号码' || conditions==null) {

			initParam = {
					"orgId": orgId,
					"householdStaffVo.logout" : 0,
					"householdStaffVo.fastSearchKeyWords":""
				}
	 }else{
		 initParam = {
					"orgId": orgId,
					"householdStaffVo.logout" : 0,
					"householdStaffVo.fastSearchKeyWords":conditions
				}
	 }
	 $("#householdStaffList").setGridParam({
		url:"${path}/baseinfo/householdStaff/fastSearch.action",
		datatype: "json",
		page:1
		 });
	 $("#householdStaffList").setPostData(initParam);
	 $("#householdStaffList").trigger("reloadGrid");	
}

$("#refreshSearchKey").click(function(){$("#searchText").attr("value","请输入姓名或身份证号码");});
</script>
<div id="householdStaffListDiv">
	<div style="width: 100%;">
		<table id="householdStaffList"></table>
		<div id="householdStaffListPager"></div>
	</div>
	<div id="scanHeaderImage"></div>

<div style="display: none;">
	
		<span id="title"><s:property value="#request.name" /></span>
	
</div></div>