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
        <b><span id='noPopulations'>&nbsp;&nbsp;<span id='floatPopulations'></span>&nbsp;&nbsp;<span id='householdPopulations'></span>&nbsp;&nbsp;<span id='unsettledPopulations'></span>&nbsp;&nbsp;<span id='overseaPersonnels'></span></b>
	</div>
	<div class='clearLine'></div>
	<div style="width: 100%;">
		<table id="livingInHousePopulationList"></table>
	</div>
	<div id="populationDialog"></div>
	<input type="hidden" id="baseUrl" value="${path} "/>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#livingInHousePopulationList").jqGridFunction({
		url:'${path}/baseinfo/actualHousePopulation/findLivingInHousePopulationInfos.action',
		postData:{
			"houseId":'${houseInfo.id}'
		},
		datatype: "json",
		height:200,
   		colModel:[
			{name:"sid",index:'sid',width:50,frozen:true,hidedlg:true,sortable:false,hidden:true,key:true},
        	{name:"id",index:"id",hidden:true,frozen:true},
  			{name:" ",label:"",width:30,frozen:true,formatter:href2PopulationDetail},
  			{name:"certificateNumber",label:"证件号码",sortable:false,width:180,frozen:true},
        	{name:'personName',index:"姓名",label:"姓名",sortable:false,width:120,frozen:true},
        	{name:'genderName',index:"houseOwner",label:"性别",sortable:false,width:80,frozen:true},
        	{name:'type.displayName',index:"houseArea",label:"人员类型",sortable:false,width:90},
        	{name:'nationName',index:"houseArea",label:"民族",sortable:false,width:90},
        	{name:'householdPlace',index:"houseType",label:"户籍地",sortable:false,width:200},
        	{name:'educationName',index:"houseStructure",label:"文化程度",sortable:false,width:110},
        	{name:'professionName',index:"roomNumber",label:"职业",sortable:false,width:90,align:"right"},
  			{name:"workCompany",index:"telephone",label:"工作单位或就读学校",sortable:false,width:300},
  			{name:"death",hidden:true,hidedlg:true,label:"是否死亡",width:1},
  			{name:"active",hidden:true,hidedlg:true,label:"注销",width:1}
		],
//		ondblClickRow: viewHouseInfo,
		gridComplete:function(){
			var peopleTypeList = $("#livingInHousePopulationList").getCol('type.displayName',false);
			var floatPopulations = 0;
			var householdPopulations = 0;
			var unsettledPopulations = 0;
			var overseaPersonnels = 0;
			for( var i=0;i<peopleTypeList.length;i++){
				if(peopleTypeList[i]=="流动人口"){
					floatPopulations = floatPopulations + 1;
				}
				if(peopleTypeList[i]=="户籍人口"){
					householdPopulations = householdPopulations + 1;
				}
				if(peopleTypeList[i]=="未落户人口"){
					unsettledPopulations = unsettledPopulations + 1;
				}
				if(peopleTypeList[i]=="境外人员"){
					overseaPersonnels = overseaPersonnels + 1;
				}
			}
			if(floatPopulations>0){
				$("#floatPopulations").html("流动人口："+floatPopulations+"人");
			}
			if(householdPopulations>0){
				$("#householdPopulations").html("户籍人口："+householdPopulations+"人");
			}
			if(unsettledPopulations>0){
				$("#unsettledPopulations").html("未落户人口："+unsettledPopulations+"人");
			}
			if(overseaPersonnels>0){
				$("#overseaPersonnels").html("境外人员："+overseaPersonnels+"人");
			}
			if($("#livingInHousePopulationList").getGridParam("records")==0){
				$("#noPopulations").html("此房屋下无人员信息");
			}
		}
	});

  	jQuery("#livingInHousePopulationList").jqGrid('setFrozenColumns');
  	

});

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

</script>