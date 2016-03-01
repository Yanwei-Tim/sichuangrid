<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div class="container_24">
		<input type="hidden" id="selectHouseId" value="${houseInfo.encryptId} "/>
	    <input type="hidden" id="selectOrgId" value="${houseInfo.organization.id}" />
		<div class='clearLine'></div>
		<div class='grid_24'>
			<b>现有住户</b>
		</div>
		<div class='clearLine'></div>
		<div style='width:98%'>
			<table id="existedPopulations"></table>
		</div>
		<div class='grid_24'></div>
		<div class="btnbanner btnbannerData">
			<label>证件号码：</label>
			<div class="ui-widget autosearch">
				<input type="text" value="" id="certificateNo" maxlength="30"  />
			</div>
			<label>姓名：</label>
			<div class="ui-widget autosearch">
				<input type="text" value="" id="populationName" maxlength="30" />
			</div>
			
			 <label>户口号：</label>
				<div class="ui-widget autosearch">
					<input type="text" value="" id="accountNumber" maxlength="30" />
				</div>
			
			
			<div class='clearLine'></div>
			<div class="btnlist" id="nav">
			    <label class="form-lb1">性别：</label>
				<div class="ui-widget autosearch">
			    	<select  id="gender"  >
			   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" />
					</select>
	   			</div>
				<a href="javascript:;" id="searchActualPopulation"><span>搜索</span></a>
				<a href="javascript:;" id="reFresh"><span>刷新</span></a>
		<!-- 		<a href="javascript:;" id="addPersonnel"><span>新增</span></a> -->
				<ul class="heightAuto" id="personnelId">
					<li id='addHouseholdStaff'>户籍人口 </li>
					<li id='addFloatingPopulation'>流动人口</li>
					<li id='addUnsettledPopulation'>未落户人口</li>
					<li id='addOverseaPersonnel'>境外人口 </li>
				</ul>
			</div>
		</div>
		<div class='clearLine'></div>

		<div class='grid_24'>
			<b>可选住户</b>
		</div>
		<div class='clearLine'></div>
		<div style='width:98%'>
			<table id="appendPopulations" ></table>
			<div id="appendPopulationsPager"></div>
		</div>
	</div>
	<div class='clearLine'></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
function genderIdFormatter(el,options,rowData){
	var gender = genderFormatter(el,options,rowData);
	if(gender&&gender!="&nbsp;"){
		return gender;
	}
	if(rowData['genderId']||(rowData.gender&&rowData.genderId)){
		if(rowData["genderId"]){
			return genderData[rowData["genderId"]];
		}
		return genderData[rowData.genderId];
	}else{
		return "&nbsp;";
	}
}
function changeFromGrid(uniqueId){
	var data = $("#existedPopulations").getRowData(uniqueId);
	var populationIdAndType = getPopulationIdAndType(uniqueId);
	$("#searchDialog").createDialog({
		width: 600,
		height: 350,
		title:'变更地址',
		url:PATH+'/baseinfo/houseInfo/actualHousePopulation/searchHouseInfo.jsp?populationId='+populationIdAndType['houseHasActualPopulation.populationId']+'&houseId=${houseInfo.id}&populationType='+populationIdAndType['houseHasActualPopulation.populationType'],
		buttons:{
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function deletePopulation(el,option,rowdata){
	return "<a href='javascript:;' onclick=changeFromGrid('"+rowdata.uniqueId+"') >变更住址</a> | <a href='javascript:;' onclick=deleteFromGrid('"+rowdata.uniqueId+"') >移除</a>";
}

function appendPopulation(el,option,rowdata){
	return "<a href='javascript:;' onclick=transferSearchToBindedGrid(this,'"+rowdata.uniqueId+"') >添加</a>";
}
function getPopulationIdAndType(rowDataId){
	var populationIdAndType=rowDataId.split("_");
	return {
		'houseHasActualPopulation.populationId':populationIdAndType[1],
		'houseHasActualPopulation.populationType':populationIdAndType[0]
	};
}
function deleteFromGrid(id){
	$.dialogLoading("open");
	var populationIdAndType = getPopulationIdAndType(id);
	$.ajax({
		url:'${path}/baseinfo/actualHouseManage/deleteHouseHasActualPopulationByPopulationTypeAndHouseEncryptId.action',
		data:$.extend({"houseHasActualPopulation.houseId":'${houseInfo.encryptId}'},populationIdAndType),
		success:function(data){
			/*
			var data = $("#existedPopulations").getRowData(id);
			$("#existedPopulations").delRowData(id);
			$("#appendPopulations").addRowData(id,data,"first");
			*/
			$("#existedPopulations").trigger("reloadGrid");
			$("#appendPopulations").trigger("reloadGrid");
			$.messageBox({message:"人口信息已经成功移除出房屋!"});
			$.dialogLoading("close");
		},error:function(data){
			$.messageBox({message:"人口信息添加失败!"});
			$.dialogLoading("close");
		}
	});
}

function transferSearchToBindedGrid(that,id){
	$.dialogLoading("open");
	var populationIdAndType = getPopulationIdAndType(id);
	$.ajax({
		url:'${path}/baseinfo/actualHouseManage/addHouseHasActualPopulationByEncrypt.action',
		data:$.extend({"houseHasActualPopulation.houseId":'${houseInfo.encryptId}'},populationIdAndType),
		success:function(data){
			/*
			var data = $("#appendPopulations").getRowData(id);
			$("#appendPopulations").delRowData(id);
			$("#existedPopulations").addRowData(id,data,"first");
			*/
			$("#existedPopulations").trigger("reloadGrid");
			$("#appendPopulations").trigger("reloadGrid");
			$.messageBox({message:"人口信息已经成功添加进房屋!"});
			$.dialogLoading("close");
		},error:function(data){
			$.messageBox({message:"人口信息添加失败!"});
			$.dialogLoading("close");
		}
	});
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
}

function appendUnsettledPopulationToExsistedGrid(data){
	var populationType=constructPopulateType("unsettledPopulation","未落户人口");
	var rowdata ={
	        id : data.id,
	        uniqueId:populationType.catalog+"_"+data.id,
	        type:populationType,
	        certificateNumber:data.idCardNo,
	        personName:data.name
	};
	$("#existedPopulations").trigger("reloadGrid");
	//$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
}

function appendUOverseaPersonToExsistedGrid(data){
	var populationType=constructPopulateType("overseaStaff","境外人口");
	var rowdata ={
	        id : data.id,
	        uniqueId:populationType.catalog+"_"+data.id,
	        type:populationType,
	        certificateNumber:data.certificateNo,
	        personName:data.name
	};
	$("#existedPopulations").trigger("reloadGrid");
	//$("#existedPopulations").addRowData(rowdata.uniqueId,rowdata);
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
		url:"${path}/baseinfo/actualHousePopulation/saveLivingPopulation.action?houseId="+getHouseId()+params,
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
	// 现有住户
	$("#existedPopulations").jqGridFunction({
		url:'${path}/baseinfo/actualHousePopulation/findLivingInHousePopulationInfos.action',
		postData:{
			"houseId":function(){return $("#selectHouseId").val();}
		},
		datatype: "local",
		height:140,
		width:800,
   		colModel:[
        	{name:"uniqueId",hidden:true,hidedlg:true,key:true},
        	{name:"id",index:"id",hidden:true,hidedlg:true,key:false},
        	{name:'type.catalog',hidden:true,label:" ",width:100},
        	{name:'type.displayName',label:"人员类型",width:120},
        	{name:'personName',label:"姓名",hidedlg:true,width:120},
        	{name:"genderId", label:"性别",index:"gender",width:60, formatter:genderIdFormatter,align:"center"},
  			{name:"certificateNumber",label:"证件号码",hidedlg:true,width:180},
        	{name:' ',label:"操作",formatter:deletePopulation,width:150}
		]
	});

	// 可选住户
	$("#appendPopulations").jqGridFunction({
		url:'${path}/baseinfo/actualHousePopulation/findNotLivingInHousePopulationInfos.action',
		postData:{
			"houseId":function(){return $("#selectHouseId").val();},
			"certificationNumber":function(){return $("#certificateNo").val();},
			"name":function(){return $("#populationName").val();},
			"gender":function(){return $("#gender").val();},
			"orgId":function(){return $("#selectOrgId").val();},
			"accountNumber":function(){return $("#accountNumber").val();}
		},
		height:140,
		width:800,
   		colModel:[
   	        	{name:"uniqueId",hidden:true,hidedlg:true,key:true},
   	        	{name:"id",index:"id",hidden:true,hidedlg:true,key:false},
   	        	{name:'type.catalog',hidden:true,label:" ",hidedlg:true,width:100},
   	        	{name:'type.displayName',label:"人员类型",width:120},
   	        	{name:'personName',label:"姓名",width:120},
   	        	{name:"genderId", label:"性别",index:"gender",width:60, formatter:genderIdFormatter,align:"center"},
   	  			{name:"certificateNumber",label:"证件号码",width:180},
   	        	{name:'operator', index:"id",label:"操作",formatter:appendPopulation,width:150}
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

	$("#reFresh").click(function(){
		$("#certificateNo").attr("value","");
		$("#populationName").attr("value","");
		$("#gender").attr("value","");
		$("#accountNumber").attr("value","");
		$("#appendPopulations").setGridParam({
			datatype: "json",
			page:1
		});
		$("#appendPopulations").trigger("reloadGrid");
	});
	$("#addPersonnel").click(function(){
		$(document).unbind("click").bind("click",function(e){
			if(!($(e.target).is("#addPersonnel") || $(e.target).closest("#addPersonnel")[0])){
				$("#personnelId").hide();
			}
		});
		$("#personnelId li").hover(
			function(){
				$(this).addClass("hover");
			},
			function(){
				$(this).removeClass("hover");
			}
		).click(function(){
			$("#personnelId").hide();
		});
		$("#personnelId").css("display" ,"block");
	});
	$("#addHouseholdStaff").click(function(){
		var houseId=getHouseId();
		$("#actualAppendPopulationDialog").createActualPopulationDialog({
			width: 800,
			height: 600,
			model :"add",
			title:"新增户籍人口",
			data:[
				   {title:'基本信息',url:'/baseinfo/householdStaff/dispath.action?dailogName=actualAppendPopulationDialog&mode=add&actionName=householdStaffBaseInfo&population.houseCode=${houseInfo.houseCode}',buttons:{next:true}}
// 				   {title:'户籍信息',url:'/baseinfo/householdStaff/getHouseholdStaffById.action?mode=add&dailogName=actualAppendPopulationDialog',buttons:{prev:true,next:true,save:function(data){appendHouseholdStaffToExsistedGrid(data);},saveContinue:function(data){appendHouseholdStaffToExsistedGrid(data);}}}
				   <pop:JugePermissionTag ename="groupFamilyInfo">
				   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>',buttons:{prev:true,<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">next:true,</pop:GlobalSettingTag>save:function(data){appendHouseholdStaffToExsistedGrid(data);},saveContinue:function(data){appendHouseholdStaffToExsistedGrid(data);}}}
				   </pop:JugePermissionTag>
				   <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">
				   ,{title:'业务信息',url:'/baseinfo/commonPopulation/populationSpecializedInfo.jsp?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>',buttons:{prev:true,save:function(data){appendHouseholdStaffToExsistedGrid(data);},saveContinue:function(data){appendHouseholdStaffToExsistedGrid(data);}}}
				   </pop:GlobalSettingTag>			
			]
		});
	});
	$("#addFloatingPopulation").click(function(){
		$("#actualAppendPopulationDialog").createActualPopulationDialog({
			width: 800,
			height: 600,
			model :"add",
			title:"新增流动人口",
			data:[
				   {title:'基本信息',url:'/baseinfo/floatingPopulationManage/dispath.action?mode=add&dailogName=actualAppendPopulationDialog&population.houseCode=${houseInfo.houseCode}',buttons:{next:true}},
// 				   {title:'流入信息',url:'/baseinfo/floatingPopulationManage/dispathInflowingInfomation.action?mode=add&dailogName=actualAppendPopulationDialog',buttons:{prev:true,next:true,save:function(data){appendFloatingPopulationToExsistedGrid(data);},saveContinue:function(data){appendFloatingPopulationToExsistedGrid(data);}}}
				   <pop:JugePermissionTag ename="groupFamilyInfo">
				   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>',buttons:{prev:true,<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">next:true,</pop:GlobalSettingTag>save:function(data){appendFloatingPopulationToExsistedGrid(data);},saveContinue:function(data){appendFloatingPopulationToExsistedGrid(data);}}}
				   </pop:JugePermissionTag>
				   <pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@CAN_MAINTAIN_BUSINESS_POPULATION" value="true">
				   ,{title:'业务信息',url:'/baseinfo/commonPopulation/populationSpecializedInfo.jsp?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>',buttons:{prev:true,save:function(data){appendFloatingPopulationToExsistedGrid(data);},saveContinue:function(data){appendFloatingPopulationToExsistedGrid(data);}}}
				   </pop:GlobalSettingTag>
			]
		});
	});
	$("#addUnsettledPopulation").click(function(event){
		$("#actualAppendPopulationDialog").createActualPopulationDialog({
			title:"新增未落户人口",
			width: 800,
			height: 600,
			model :"add",
			data:[
				   {title:'基本信息',url:'/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>&orgId='+getCurrentOrgId()+'&unsettledPopulation.houseId='+getHouseId(),buttons:{next:true,save:function(data){appendUnsettledPopulationToExsistedGrid(data);},saveContinue:function(data){appendUnsettledPopulationToExsistedGrid(data);}}}
				   <pop:JugePermissionTag ename="groupFamilyInfo">
				   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>',buttons:{prev:true,save:function(data){appendUnsettledPopulationToExsistedGrid(data);},saveContinue:function(data){appendUnsettledPopulationToExsistedGrid(data);}}}
				   </pop:JugePermissionTag>
				   
			]
		});
	  });
	$("#addOverseaPersonnel").click(function(event){
		$("#actualAppendPopulationDialog").createActualPopulationDialog({
			width: 800,
			height: 600,
			title:"新增境外人员信息",
			model :"add",
			data:[
				   {title:'基本信息',url:'/baseinfo/overseaPersonnelManage/dispatch.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>&overseaPersonnel.houseId='+getHouseId(),buttons:{next:true,save:function(data){appendUOverseaPersonToExsistedGrid(data);},saveContinue:function(data){appendUOverseaPersonToExsistedGrid(data);}}}
				   <pop:JugePermissionTag ename="groupFamilyInfo">
				   ,{title:'家庭信息',url:'/baseinfo/familyInfo/getGroupFamilyByPopulationId.action?mode=add&dailogName=actualAppendPopulationDialog&populationType=<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>',buttons:{prev:true,save:function(data){appendUOverseaPersonToExsistedGrid(data);},saveContinue:function(data){appendUOverseaPersonToExsistedGrid(data);}}}
				   </pop:JugePermissionTag>
			]
		});
	});
})
</script>