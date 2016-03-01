<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<!-- form id="maintainFormaaaaa" method="post" action="${path}/baseinfo/housePopulation/saveLivingPopulation.action" -->
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div class="container_24">
		<input type="hidden" id="selectHouseId" value="${houseId} "/>
		<!-- input type="hidden" id="selectOrgId" value="${houseOwnerOrgId}"/ -->
		<div class='clearLine'></div>
		<div class='grid_24'>
			<b>已住人员</b>
		</div>
		<div class='clearLine'></div>
		<div style='width:90%'>
			<table id="existedPopulations"></table>
		</div>
		<div class='clearLine'></div>
		<div class="btnbanner btnbannerData">
			<label>证件号码：</label>
			<div class="ui-widget autosearch">
				<input type="text" value="" id="certificateNo" maxlength="30"  />
			</div>
			<label>姓名：</label>
			<div class="ui-widget autosearch">
				<input type="text" value="" id="populationName" maxlength="30" />
			</div>
			<div class="btnlist" id="nav">
				<a href="javascript:;" id="searchActualPopulation"><span>搜索</span></a>
				<a href="javascript:;" id="addHouseholdStaff"><span>添加户籍人口</span> </a>
				<a href="javascript:;" id="addFloatingPopulation"><span>添加流动人口</span></a>
			</div>
		</div>
		<div class='clearLine'></div>
		
		<div class='grid_24'>
			<b>可选人员</b>
		</div>
		<div class='clearLine'></div>
		<div style='width:90%'>
			<table id="appendPopulations" ></table>
			<div id="appendPopulationsPager"></div>
		</div>
	</div>
	<div class='clearLine'></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

function deletePopulation(el,option,rowdata){
	return "<a href='javascript:;' onclick=deleteFromGrid('"+rowdata.uniqueId+"') >删除</a>";
}

function appendPopulation(el,option,rowdata){
	return "<a href='javascript:;' onclick=transferSearchToBindedGrid('"+rowdata.uniqueId+"') >入住</a>";
}

function deleteFromGrid(id){
	$("#existedPopulations").delRowData(id);
}

function transferSearchToBindedGrid(id){
	var data=$("#appendPopulations").getRowData(id);
	$("#appendPopulations").delRowData(id);
	$("#existedPopulations").addRowData(id,data);
}

function getHouseId(){
	return $("#selectHouseId").val()!=""?$("#selectHouseId").val():"-1";
}

function appendHouseholdStaffToExsistedGrid(data){
	var populationType=constructPopulateType("householdStaff","户籍人口");
	var rowdata =
	{
	        id : data.id,
	        uniqueId:populationType.catalog+"_"+data.id,
	        type:populationType,
	        certificateNumber : data.idCardNo,
	        personName : data.name,
	        genderName : genderFormatter(data.gender,null,data)
	};
	$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
//	if (isContainsPopulation(rowdata.uniqueId)=='not'){
//		alert(456);
	//	$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
//	}
}

function constructPopulateType(catalogName,populationName){
	var populationType={
			catalog:catalogName,
			displayName:populationName
		};
	return populationType;
}

function appendFloatingPopulationToExsistedGrid(data){
	var populationType=constructPopulateType("floatingPopulation","流动人口");
	var rowdata ={
	        id : data.id,
	        uniqueId:populationType.catalog+"_"+data.id,
	        type:populationType,
	        certificateNumber : data.idCardNo,
	        personName : data.name,
	        genderName : genderFormatter(data.gender,null,data)
	};
	$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
//	if (!isContainsPopulation(rowdata.uniqueId)){
//		$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
//	}
}

function isContainsPopulation(uniqueId){
//	alert(uniqueId);
	var rowdta=$("#existedPopulations").getRowData(uniqueId);
	//ttt=rowdta;
	if (rowdta==null||rowdta==undefined ){
		return 'not';
	}else{
		return 'yes';
	}
}

function populationBindHouse(parent){
	var allIds = $("#existedPopulations").getDataIDs();
	var params = '&bindingToHouseInfo=';
	for (var index=0;index<allIds.length;index++){
		params =params+allIds[index];
		if (index<allIds.length-1){
			params =params+',';
		}
	};
	$.ajax({
		url:"${path}/baseinfo/housePopulation/saveLivingPopulation.action?houseId="+getHouseId()+params,
		success:function(data){
			if (data){
				$.messageBox({message:"数据保存成功！"});
				parent.dialog("close");
			}else{
				$.messageBox({message:"数据保存时出错！"});
			}
	    }
    });
}


$(document).ready(function(){
	$("#existedPopulations").jqGridFunction({
		url:'${path}/baseinfo/housePopulation/findLivingInHousePopulationInfos.action',
		postData:{
			"houseId":function(){return $("#selectHouseId").val();}
			},
		datatype: "local",
		height:140,
		width:800,
   		colModel:[
        	{name:"uniqueId",hidden:true,hidedlg:true,key:true},
        	{name:"id",index:"id",hidden:true,hidedlg:true,key:false},
        	{name:'type.catalog',hidden:true,label:" ",width:80},
        	{name:'type.displayName',label:" ",width:80},
  			{name:"certificateNumber",label:"身份证号码",hidedlg:true,width:120},
        	{name:'personName',label:"姓名",hidedlg:true,width:100},
        	{name:'genderName',label:"性别",width:50},
//        	{name:'ownerOrgName',label:"所属网格",width:300},
        	{name:' ',label:" ",formatter:deletePopulation,width:50}
		]
	});

	$("#appendPopulations").jqGridFunction({
		url:'${path}/baseinfo/housePopulation/findNotLivingInHousePopulationInfos.action',
		postData:{
			"houseId":function(){return $("#selectHouseId").val();},
			"certificationNumber":function(){return $("#certificateNo").val();},
			"name":function(){return $("#populationName").val();}
			},
		datatype: "local",
		height:140,
		width:800,
   		colModel:[
   	        	{name:"uniqueId",hidden:true,hidedlg:true,key:true},
   	        	{name:"id",index:"id",hidden:true,hidedlg:true,key:false},
   	        	{name:'type.catalog',hidden:true,label:" ",width:80},
   	        	{name:'type.displayName',label:" ",width:80},
   	  			{name:"certificateNumber",label:"身份证号码",hidedlg:true,width:120},
   	        	{name:'personName',label:"姓名",hidedlg:true,width:100},
   	        	{name:'genderName',label:"性别",width:50},
//   	        	{name:'ownerOrgName',label:"所属网格",width:300},
   	        	{name:' ',label:" ",formatter:appendPopulation,width:50}
		]
	});
	$("#existedPopulations").setGridParam({
		datatype: "json",
		page:1
	});
	$("#existedPopulations").trigger("reloadGrid");

	$("#searchActualPopulation").click(function(){
		$("#appendPopulations").setGridParam({
			datatype: "json",
			page:1
		});
		$("#appendPopulations").trigger("reloadGrid");
	});

	$("#addHouseholdStaff").click(function(){
		var houseId=getHouseId();
		$("#appendPopulationDialog").createActualPopulationDialog({
			width: 800,
			height: 600,
			model :"add",
			title:"新增户籍人口",
			data:[
				   {title:'基本信息',url:'/baseinfo/householdStaff/dispath.action?dailogName=appendPopulationDialog&mode=add&actionName=householdStaffBaseInfo&population.houseId='+getHouseId(),buttons:{next:true}},
// 				   {title:'户籍信息',url:'/baseinfo/householdStaff/getHouseholdStaffById.action?mode=add&dailogName=appendPopulationDialog',buttons:{prev:true,next:true,save:function(data){appendHouseholdStaffToExsistedGrid(data);},saveContinue:function(data){appendHouseholdStaffToExsistedGrid(data);}}},
				   {title:'业务信息',url:'/baseinfo/commonPopulation/populationSpecializedInfo.jsp?mode=add&dailogName=appendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>',buttons:{prev:true,save:true,saveContinue:true}}
			]
		});
	});
	$("#addFloatingPopulation").click(function(){
		$("#appendPopulationDialog").createActualPopulationDialog({
			width: 800,
			height: 600,
			model :"add",
			title:"新增流动人口信息",
			data:[
				   {title:'基本信息',url:'/baseinfo/floatingPopulationManage/dispath.action?mode=add&dailogName=appendPopulationDialog',buttons:{next:true}}
// 				   {title:'住房信息',url:'/baseinfo/houseInfoForPopulation/dispathHouseInfoForPopulation.action?mode=add&dailogName=appendPopulationDialog',buttons:{prev:true,next:true}}
// 				   ,{title:'流入信息',url:'/baseinfo/floatingPopulationManage/dispathInflowingInfomation.action?mode=add&dailogName=appendPopulationDialog',buttons:{prev:true,next:true,save:true,saveContinue:true}}
				   <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">
				   ,{title:'业务信息',url:'/baseinfo/commonPopulation/populationSpecializedInfo.jsp?mode=add&dailogName=appendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>',buttons:{prev:true,save:true,saveContinue:true}}
					</pop:GlobalSettingTag>
			]
		});
	});
});

</script>