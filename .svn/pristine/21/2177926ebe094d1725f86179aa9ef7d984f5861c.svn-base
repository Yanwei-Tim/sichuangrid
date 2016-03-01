<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType" value="<s:property value="#parameters.keyType"/>" />
<%@page import="com.tianque.baseInfo.youths.constants.YouthsType" %>
<%
String keyType = request.getParameter("keyType");
String moduleCName = "";
String moduleName = "";
String enameModuleName="";
if(keyType != null && !"".equals(keyType)){
	if(YouthsType.YOUNGSTERS.equals(keyType)) {
		moduleCName = YouthsType.YOUNGSTERS_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	} else if(YouthsType.YOUNGPIONEER.equals(keyType)) {
		moduleCName = YouthsType.YOUNGPIONEER_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	}else if(YouthsType.LEAGUEMEMBER.equals(keyType)) {
		moduleCName = YouthsType.LEAGUEMEMBER_NAME;
		moduleName = keyType.substring(0,1).toUpperCase()+keyType.substring(1);
		enameModuleName = moduleName;
	}
}
request.setAttribute("moduleName",moduleName);
request.setAttribute("moduleCName",moduleCName);
request.setAttribute("keyType",keyType);
request.setAttribute("enameModuleName",enameModuleName);
%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="${moduleName }" name="moduleName"/>
</jsp:include>
<input type="hidden" id="keyTypes" value="${keyType}" />
<div class="content" >
	<div style="width:100%;" >
		<table id="youthsList"> </table>
		<div id="youthsListPager"></div>
	</div>
	<div id="youthsDialog"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="youngstersManagement,youngpioneerManagement,leaguememberManagement">
		<span id="title">${ moduleCName}</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />

var title=$("#title").html();
var notExecute=new Array();

function search(orgId){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入姓名或身份证号码') return;
	var initParam = {
		"organizationId": orgId,
		"searchYouthsVo.keyType": $("#keyTypes").val(),
		"searchYouthsVo.logOut":0,
		"searchYouthsVo.isDeath":false,
		"searchYouthsVo.fastSearchKeyWords":condition
	}
	$("#youthsList").setGridParam({
		url:'${path}/baseinfo/youthsManage/findYouthsList.action',
		datatype: "json",
		page:1
	});
	$("#youthsList").setPostData(initParam);
	$("#youthsList").trigger("reloadGrid");
}

var dialogWidth=800;
var dialogHeight=600;
$(document).ready(function(){

	$("#youthsList").jqGridFunction({
		datatype: "local",
	   	colModel:[
   	        {name:"rowNum", index:"rowNum",sortable:false, hidden:true,frozen:true,key:true},//这里的rowNum表示行号没实际意义,注意解决id相同选择不到数据的问题
	        {name:"id", index:"id",sortable:false, hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	        {name:"actualPopulationType", index:"actualPopulationType",hidedlg:true,hidden:true},
	        {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
	        {name:"operator", index:'id', label:'操作',hidedlg:true,hidden:true,formatter:operateFormatter,width:90,frozen:true,sortable:false,align:'center' },
	        {name:"name", index:'name', label:'姓名',formatter:youthsNameFont,width:80,sortable:false,frozen:true },
	        {name:'gender',formatter:genderFormatter, label:'性别', align:'center',sortable:false, width:50},
	        {name:'idCardNo',index:'idCardNo', label:'身份证号码', align:'center', width:160,sortable:false,frozen:true},
	        {name:'currentAddress', sortable:false, label:'常住地址', width:200 },      
	        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:200},
	        
	        {name:"usedName", index:'usedName', label:'曾用名/别名', width:95,sortable:false,hidden:true},
	        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:200,hidden:true},
	        {name:'mobileNumber',sortable:false, label:'联系手机', width:100,hidden:true},
	        {name:'telephone', sortable:false, label:'固定电话', width:120,hidden:true},
	        {name:"birthday", index:'birthday', sortable:false,label:'出生日期',align:'center', width:80 ,hidden:true},
	        
	        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80, hidden:true },
	        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:150},
	        {name:"schooling",sortable:false,label:"文化程度",formatter:schoolingFormatter,width:80, hidden:true },
	        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80,hidden:true},
	        {name:"maritalState",sortable:false,label:"婚姻状况",formatter:maritalStateFormatter,align:'center',width:80, hidden:true },
	        
	        {name:"stature",sortable:false,label:"身高(cm)",width:80,align:'center',hidden:true },
	        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80, hidden:true },
	        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80, hidden:true },
	        {name:"email",sortable:false,label:"电子邮箱",width:120, hidden:true },
	        {name:'province',index:'province',sortable:false,label:'户籍地',formatter:householdRegisterFormatter,width:150,hidden:true},
	        
	        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150},
	        {name:'otherAddress', sortable:false, label:'临时居所', width:150,hidden:true},
	        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
	        {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80},
	        {name:'logoutDetail.logoutReason',sortable:false,hidden:true,hidedlg:true,width:80}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
	  	ondblClickRow:viewDialogForDbClik,
		loadComplete: afterLoad,
		onSelectRow: selectRow
	});
	
	if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			refreshPositiveInfoGrid();
		}else{
			search(getOrgIdForStat());
		}
	}
	
	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat){
			return orgIdForStat;
		}else{
			return getCurrentOrgId();
		}
	}
	
});
	
function refreshPositiveInfoGrid() {
	var name=$("#youths\\.name").val();
	var idCardNo=$("#youths\\.idCardNo").val();
	var gender=$("#youths\\.gender\\.id").val();
	var usedName=$("#youths\\.usedName").val();
	var keyType=$("#keyTypes").val();
	var isDeathUser=$("#youths\\.isDeathUser").val();
	var isEmphasis=$("#youths\\.isEmphasis").val();
	var telephone=$("#youths\\.telephone").val();
	var mobileNumber=$("#youths\\.mobileNumber").val();
	var schooling=$("#youths\\.schooling\\.id").val();
	var beginAge=$("#youths\\.beginAge").val();
	var endAge=$("#youths\\.endAge").val();
	var nation=$("#youths\\.nation\\.id").val();
	var career=$("#youths\\.career\\.id").val();
	var maritalState=$("#youths\\.maritalState\\.id").val();
	var residenceType=$("#youths\\.residenceType\\.id").val();
	var faith=$("#youths\\.faith\\.id").val();
	var bloodType=$("#youths\\.bloodType\\.id").val();
	var email=$("#youths\\.email").val();
	var province=$("#youths\\.province").val();
	var city=$("#youths\\.city").val();
	var district=$("#youths\\.district").val();
	var currentAddress=$("#youths\\.currentAddress").val();
	var nativePlaceAddress=$("#youths\\.nativePlaceAddress").val();
	var otherAddress=$("#youths\\.otherAddress").val();
	var initParam = {
			"organizationId": getCurrentOrgId(),
			"searchYouthsVo.name":name,
			"searchYouthsVo.idCardNo":idCardNo,
			"searchYouthsVo.gender.id":gender,
			"searchYouthsVo.usedName":usedName,
			"searchYouthsVo.keyType":keyType,
			"searchYouthsVo.isDeathUser":isDeathUser,
			"searchYouthsVo.isEmphasis":isEmphasis,
			"searchYouthsVo.telephone":telephone,
			"searchYouthsVo.mobileNumber":mobileNumber,
			"searchYouthsVo.schooling.id":schooling,
			"searchYouthsVo.beginAge":beginAge,
			"searchYouthsVo.endAge":endAge,
			"searchYouthsVo.nation.id":nation,
			"searchYouthsVo.career.id":career,
			"searchYouthsVo.maritalState.id":maritalState,
			"searchYouthsVo.residenceType.id":residenceType,
			"searchYouthsVo.faith.id":faith,
			"searchYouthsVo.bloodType.id":bloodType,
			"searchYouthsVo.email":email,
			"searchYouthsVo.province":province,
			"searchYouthsVo.city":city,
			"searchYouthsVo.district":district,
			"searchYouthsVo.currentAddress":currentAddress,
			"searchYouthsVo.nativePlaceAddress":nativePlaceAddress,
			"searchYouthsVo.otherAddress":otherAddress
			}
		$("#youthsList").setGridParam({
			url:'${path}/baseinfo/youthsManage/findYouthsList.action',
			datatype: "json",
			page:1
		});
		$("#youthsList").setPostData(initParam);
		$("#youthsList").trigger("reloadGrid");
}

function afterLoad(){
	logOutFormatter();
}

function changeColor(){
	if(notExecute==null||notExecute.length==0){
		return;
	}
	for(var i=0;i<notExecute.length;i++){
		var rowData=$("#youthsList").getRowData(notExecute[i]);
		$("#"+notExecute[i]).css('background','red');
		$("#youthsList").setSelection(notExecute[i])
	}
	notExecute.splice(0,notExecute.length);
}

function logOutFormatter(){
	var idCollection = new Array();
	idCollection=$("#youthsList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#youthsList").getRowData(idCollection[i]);
			if(ent.logOut==1||ent.logOut=='1'){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
	}
}

function checkExport(){
}
function disableButtons(){
}
function selectRow(){
	var count = $("#youthsList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("youthsList");
	if(selectedCounts == count){
		jqGridMultiSelectState("youthsList", true);
	}else{
		jqGridMultiSelectState("youthsList", false);
	}
}
function viewDialog(event,rowNum){
	if(rowNum==null){
		return;
	}
	var rowData =  $("#youthsList").getRowData(rowNum);
	var rowid=rowData.encryptId;
	var actualPopulationType = rowData.actualPopulationType;
	var url;
	if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@HOUSEHOLDSTAFF_KEY"/>'){
		url='${path}/baseinfo/householdStaff/dispathByEncrypt.action';
	}else if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@FLOATINGPOPULATION_KEY"/>'){
		url='${path}/baseinfo/floatingPopulationManage/dispathByEncrypt.action';
	}else if(actualPopulationType =='<s:property value="@com.tianque.core.util.BaseInfoTables@UNSETTEDPOPULATION_KEY"/>'){
		url='${path}/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action';
	}		
	$("#youthsDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看'+title+'信息',
		url:url+'?mode=view&id='+rowid,
		buttons: {
			"打印" : function(){
				printChoose(rowid,url);
	  	   	},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function searchYouths(){
	$("#youthsList").setGridParam({
		url:"${path}/baseinfo/youthsManage/searchYouthsList.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	var data=$("#searchYouthsForm").serializeArray();
	var dataJson={};
	for(i=0;i<data.length;i++){
		 if (dataJson[data[i].name]) {
           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
		} else {
            dataJson[data[i].name] = data[i].value;
		}
	}
	$("#youthsList").setPostData($.extend({"organizationId":getCurrentOrgId()},dataJson));
    $("#youthsList").trigger("reloadGrid");
}

function youthsNameFont(el,options,rowData){
	if(null == rowData.name) {
		return "&nbsp;&nbsp;"
	}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
		return '<a href="javascript:;"  onclick="viewDialog(event,'+rowData.rowNum+')"><font color="red">'+rowData.name+'</font></a>';
	}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
		return '<a href="javascript:;"  onclick="viewDialog(event,'+rowData.rowNum+' )"><font color="#778899">'+rowData.name+'</font></a>';
	}
	return '<a href="javascript:;" onclick="viewDialog(event,'+rowData.rowNum+')"   ><font color="#6633FF">'+rowData.name+'</font></a>';
}
function printChoose(rowid,url){
	$("#printOptionsDialog").createDialog({
		width: 300,
		height: 200,
		title:'选择打印内容',
		modal : true,
		url:'${path}/baseinfo/commonPopulation/printTabChooseDlg.jsp',
		buttons: {
			"确定" : function(){
				print(rowid,url);
	   		},
		   "关闭" : function(){	
		        $(this).dialog("close");
		   }
		}
	});
}

var printTitleStr='';
function print(rowid,url){
	printTitleStr=title;
	if(rowid==null){
 		return;
	}
	$("#youthsDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'打印'+title+'信息',
		modal : true,
		url:url+'?mode=print&id='+rowid,
		buttons: {
			  "打印" : function(){
				$("#printSpaceDiv").printArea();
	        	$(this).dialog("close");
	   		},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function viewDialogForDbClik(rowNum){
	viewDialog(event,rowNum);
}

</script>