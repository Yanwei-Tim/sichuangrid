<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div >
	<table id="toHouseholdStaffList" ></table>
</div>		
<script type="text/javascript">

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


function getSelectedIdsByTabId(name){
	var selectedIds = getActualjqGridMultiSelectIds(name);
	return selectedIds;
}

function isFloatingPopulation(idCardNo ,orgId){
	var isFloatingPopulation;
	$.ajax({
		async: false ,
		url:PATH+"/baseinfo/floatingPopulationManage/findFloatingPopulationByIdCardNoAndOrgId.action",
		data:{
			"population.idCardNo":idCardNo,
			"organizationId":orgId
		},
		success:function(population){
			if(population!=null&&population.isDeath=='true'){
				isFloatingPopulation=true;
				$.messageBox({message:"该人员已死亡，不能转流动人口！",level:"warn"});
			}else{ 
				isFloatingPopulation=false;
			}
		}
	});
	return isFloatingPopulation;
}

var massageInfo = "";
function deathFloatingPopulationPrompt(rowDatas){
	if(rowDatas == null || undefined == rowDatas.length || rowDatas.length <= 0){
		$.messageBox({message:"请选择数据!",level:"warn"});
		return;
	}
	massageInfo = "";
	var massage = "";
	var populationIds = "";
	for(var i = 0; i < rowDatas.length; i++){
		var idCardNo = rowDatas[i].idCardNo;
		if(rowDatas[i].death=="true"){
			massage += "<p>"+rowDatas[i].name+"，"+idCardNo+"</p>";
		}else{
			massageInfo += "<p>"+rowDatas[i].name+"，"+idCardNo+"。</p>"
			populationIds += (rowDatas[i].id+",");
		}
	}
	if(massage != null && massage != ""){
		$.messageBox({message:"以下人员死亡："+massage+"不能进行操作!",level:"warn"});
		return;
	}
	for(var i = 0; i < rowDatas.length; i++){
		var idCardNo = rowDatas[i].idCardNo;
		if(isFloatingPopulation(idCardNo ,rowDatas[i]["organization.id"])){
			return;
		}
	}
	if(populationIds == null || populationIds == "" || populationIds.length <= 0){
		$.messageBox({message:"请选择数据!",level:"warn"});
		return;
	}
	return populationIds;
}

function toHouseholdStaffs(){
	var selectedFloatingPopulationId = getSelectedIdsByTabId("toHouseholdStaffList");
	if(selectedFloatingPopulationId == null || undefined == selectedFloatingPopulationId.length || selectedFloatingPopulationId.length <= 0){
		$.messageBox({message:"请选择数据!",level:"warn"});
		return;
	}
	var seldatas = new Array();
	for(var i = 0; i < selectedFloatingPopulationId.length; i ++ ){
		var seldata = $("#toHouseholdStaffList").getRowData(selectedFloatingPopulationId[i]);
		seldatas[i] = seldata;
	}
	var populationIds = deathFloatingPopulationPrompt(seldatas);
	if(populationIds == null || populationIds == "" || populationIds.length <= 0){
		return;
	}
	$.confirm({
		title:"转为户籍人口提示",
		message:"是否把“流动人口：<div style='text-align:left'>"+massageInfo+"</div> 转为户籍人口？",
		okFunc: function(){
			$.dialogLoading("open");
			$.ajax({
				url:PATH+'/baseinfo/floatingPopulationManage/toHouseholdStaffByIds.action?populationIds='+populationIds,
				success:function(data){
					$.dialogLoading("close");
					$("#floatingPopulationList").trigger("reloadGrid");
					$("#toHouseholdStaffList").trigger("reloadGrid");
					if(data == null || data.toString() == "true"){
						$.messageBox({ message:"转为户籍人口成功!"});
						$("#toHouseholdStaffDialog").dialog("close");
					}else{
						var jsonData =  JSON.parse(data);
						var dataMassage = jsonData.message;
						if(dataMassage != null && dataMassage != ""){
							var idAndCard = dataMassage.substring(dataMassage.indexOf("{")+1,dataMassage.indexOf("}"));
							var idAndCards = idAndCard.split(";");
							for(var i = 0; i < idAndCards.length; i++){
								if(idAndCards[i] != null && idAndCards[i] != ""){
									var id = idAndCards[i].split("-")[0];
									$("#toHouseholdStaffList").find("#"+id).css("background", "#FF8888");
									$("#toHouseholdStaffList").setSelection(id,true);
								}
							}
						}
						$.messageBox({level:"error", message: "转移成功！以下标红人口转移失败！"});
					}
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown){
			    	$.dialogLoading("close");
			 	    alert("提交数据时发生错误");
		 	   	}
			});
		}
	});
}

$(document).ready(function(){
	jQuery("#toHouseholdStaffList").jqGridFunction({
			datatype: "local",
		 	colModel:[
				   {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
		   	       {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		   	       {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
			       {name:"name",index:'name',label:'姓名',sortable:false,width:100,frozen:true,formatter:nameFont},
			       {name:'gender',label:'性别',sortable:false,formatter:genderFormatter,align:'center',hidden:true,width:50},
			       {name:'idCardNo',label:'身份证号码',index:'idCardNo',width:160,align:'center',sortable:false,frozen:true},
			       {name:'organization.orgName',index:'organization.orgName',width:150,hidden:true,sortable:false},
			       {name:'usedName',index:'usedName',width:70,hidden:true,sortable:false},
			       {name:'mobileNumber',index:'mobileNumber',width:100,hidden:true, align:'center',sortable:false},
			       {name:'telephone',index:'telephone',width:120,hidden:true, align:'center',sortable:false},
			       {name:'birthday',index:'birthday',width:80,hidden:true,align:'center',sortable:false},
			       {name:'workUnit',label:'工作单位或就读学校',index:'workUnit',width:220,sortable:false},
			       {name:'currentAddress',label:'常住地址',index:'currentAddress',width:220,sortable:false},
			       {name:'relationShipWithHead',label:'与户主关系',index:'relationShipWithHead',width:150,hidden:true,formatter:relationShipWithHeadFormatter,sortable:false},
			       {name:'death',sortable:false,hidedlg:true,hidden:true,width:100},
			       {name:'updateDate',label:'数据更新时间',sortable:false, align:'center', width:160}
		   	],
		   	multiselect:true,
		   	onSelectAll:function(aRowids,status){},
		   	onSelectRow:function(){setHouseInfoOperateButton();},
		   	altclass:true
			});
	
	var selectedId = getSelectedIdsByTabId("floatingPopulationList");
	for(var i = 0; i < selectedId.length; i ++ ){
		var seldata = $("#floatingPopulationList").getRowData(selectedId[i]);
		$("#toHouseholdStaffList").addRowData(seldata.id,seldata);
		$("#toHouseholdStaffList").setSelection(seldata.id,true);
	}
});

function setHouseInfoOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("toHouseholdStaffList");
	var count = $("#toHouseholdStaffList").jqGrid("getGridParam","records");
	if(selectedCounts == count && count > 0){
		jqGridMultiSelectState("toHouseholdStaffList", true);
	}else{
		jqGridMultiSelectState("toHouseholdStaffList", false);
	}
}

</script>


