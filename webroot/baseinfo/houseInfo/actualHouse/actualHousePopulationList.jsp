<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div class="container_24">
        <!--<s:iterator value="populationTotalInfos" var="infos" >
        	<s:if test="populationCount>0">
				<div class="grid_3 "><b><s:property value="type"/>：<s:property value="populationCount"/>人</b></div>
			</s:if>
        </s:iterator>-->
        <b><span id='noPopulationsView'><span id='floatPopulationsView'></span>&nbsp;&nbsp;&nbsp;<span id='householdPopulationsView'></span>&nbsp;&nbsp;&nbsp;<span id='overseaPersonnelView'></span>&nbsp;&nbsp;&nbsp;<span id='unsettledPopulationsView'></span></b>
	</div>
	<div class='clearLine'></div>
	<div style="width: 98%;">
		<table id="livingInHousePopulationListView"></table>
		<div id="livingInHousePopulationListViewPager"></div>
	</div>
	<div id="populationDialog"></div>
	<input type="hidden" id="baseUrl" value="${path} "/>
</div>
<script type="text/javascript">
function formatterId(el, options, rowData){
	return rowData.type.displayName +"_"+rowData.id;
}
$(document).ready(function(){
	$("#livingInHousePopulationListView").jqGridFunction({
		url:'${path}/baseinfo/actualHousePopulation/findLivingInHousePopulationInfos.action',
		postData:{
			"houseId":'${houseInfo.encryptId}'
		},
		datatype: "json",
		height:340,
   		colModel:[
        	{name:"id",index:"id",hidden:true,frozen:true, formatter: formatterId},
        	{name:'personName',index:"姓名",label:"姓名",width:50,frozen:true},
        	{name:'genderName',index:"houseOwner",label:"性别",width:50,frozen:true},
  			{name:"certificateNumber",label:"证件号码",width:150,frozen:true,key:true},
        	{name:'type.displayName',index:"houseArea",label:"实口人员类型",width:90},
        	{name:'householdPlace',index:"houseType",label:"户籍地",width:200},
        	{name:'nativePlaceAddress',index:"nativePlaceAddress",label:"户籍详址",width:200,sortable:false},
  			{name:"workCompany",index:"telephone",label:"工作单位或就读学校",width:200},
        	{name:'nationName',index:"houseArea",label:"民族",width:90,hidden:true,sortable:false},
        	{name:'educationName',index:"houseStructure",label:"文化程度",width:110,hidden:true,sortable:false},
        	{name:'professionName',index:"roomNumber",label:"职业",width:90,hidden:true,sortable:false},
  			{name:"death",hidden:true,hidedlg:true,label:"是否死亡",width:1},
  			{name:"active",hidden:true,hidedlg:true,label:"注销",width:1}
		],
//		ondblClickRow: viewHouseInfo,
		loadComplete: isEmphasisFormatter
	});

  	jQuery("#livingInHousePopulationListView").jqGrid('setFrozenColumns');




});
countPopulationInHouse();
function countPopulationInHouse(){
	$.ajax({
		url:'${path}/baseinfo/actualHousePopulation/countPopulationInHouse.action?houseId='+'${houseInfo.encryptId}&orgId=${houseInfo.organization.id}',
		success:function(data){
			var peopleTypeList = $("#livingInHousePopulationListView").getCol('type.displayName',false);
			var floatPopulations = 0;
			var householdPopulations = 0;
			//境外人员
			var overseaPersonnel = 0;
			//未落户人口
			var  unsettledPopulations = 0;
			for(var key in data){
				if(key=="floatingPopulation"){
					floatPopulations = data[key];
				}
				if(key=="householdStaff"){
					householdPopulations = data[key];
				}
				if(key=="overseaStaff"){
					overseaPersonnel = data[key];
				}
				if(key=="unsettledPopulation"){
					unsettledPopulations = data[key];
				}
				if(floatPopulations>0){
					$("#floatPopulationsView").html("流动人口："+floatPopulations+"人");
				}
				if(householdPopulations>0){
					$("#householdPopulationsView").html("户籍人口："+householdPopulations+"人");
				}
				if(overseaPersonnel>0){
					$("#overseaPersonnelView").html("境外人员："+overseaPersonnel+"人");
				}
				if(unsettledPopulations>0){
					$("#unsettledPopulationsView").html("未落户人口："+unsettledPopulations+"人");
				}
			}
			if(floatPopulations==0&&householdPopulations==0&&overseaPersonnel==0&&unsettledPopulations==0){
				$("#noPopulationsView").html("此房屋下无人员信息");
			}
        }
	});
}

function href2PopulationDetail(el,option,rowData){
	return "<a href='javascript:void(0)' onclick=viewPopulationDetail('"+rowData.type.displayName+"','"+rowData.type.viewPopulationUrl+"',"+rowData.id+")><span><strong class='ui-ico-cakan'></strong>查看</span></a>";
}

function viewPopulationDetail(populationType,url,id){
	$("#populationDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看'+populationType+'信息',
		url:$("#baseUrl").val()+url+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function isEmphasisFormatter(){
//	var idCollection = new Array();
//	idCollection=$("#houseInfoList").getDataIDs();
//		for(var i=0;i<idCollection.length;i++){
//			var ent =  $("#houseInfoList").getRowData(idCollection[i]);
//			if(ent.isEmphasis==1){
//			$("#"+idCollection[i]+"").css('color','#778899');
//		}
//	}
}
</script>