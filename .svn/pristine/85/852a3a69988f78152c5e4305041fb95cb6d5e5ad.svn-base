<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="dispatchOperate" namespace="/baseinfo/actualHouseManage" var="getActualHouseById" executeResult="false">
	<s:param name="houseInfo.id" value="#parameters.houseId[0]"></s:param>
	<s:param name="mode" value="'view'" ></s:param>
</s:action>
<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
	<div class="container_24">
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">房屋编号：</label>
		</div>
		<div class="grid_6" style="margin-top: 5px;">
			<input id="searchHouseCode" type="text" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">房屋地址：</label>
		</div>
		<div class="grid_6" style="margin-top: 5px;">
			<input id="searchHouseAddress" type="text" />
		</div>
		<div class="grid_3" style="line-height:20px;margin-left:10px;margin-top: 5px;">
			<input id="searchHouseInfoForPopulation" type="button" value="搜索"/>
			<input id="refreshHouseInfoForPopulation" type="button" value="刷新"/>
		</div>
		<div class='clearLine'></div>
		<div style='width:90%;margin:0 auto;'>
			<table id="houseInfoListForGrid" ></table>
			<div id="houseInfoListForGridPager"></div>
		</div>
	</div>
</div>

<script>
function addPopulationToActualHouse(houseId){
	var actualHouse = $("#houseInfoListForGrid").getRowData(houseId);
	$.ajax({
		url:'${path}/baseinfo/actualHouseManage/addHouseHasActualPopulationByEncrypt.action',
		data:{
			"houseHasActualPopulation.houseId":actualHouse.encryptId,
			"houseHasActualPopulation.populationId":'<s:property value="#parameters.populationId[0]"/>',
			"houseHasActualPopulation.populationType":'<s:property value="#parameters.populationType[0]"/>'
		},
		success:function(data){
			$("#existedPopulations").delRowData('<s:property value="#parameters.populationType[0]"/>_<s:property value="#parameters.populationId[0]"/>');
			$.messageBox({message:"人口信息已经成功更改房屋信息!"});
			$("#searchDialog").dialog('close');
		}
	});
}
$(document).ready(function(){
	function addPopulationToActualHouseFormatter(el, options, rowData){
		return '<a style="cursor:pointer" onclick=\'javascript:addPopulationToActualHouse("'+rowData.id+'")\'>添加</a>';
	}
	$("#houseInfoListForGrid").jqGridFunction({
		url:'${path}/baseinfo/actualHouseManage/houseInfoList.action?orgId=<s:property value="#getActualHouseById.houseInfo.organization.id"/>&houseId=<s:property value="#parameters.houseId[0]"/>',
		datatype: "json",
		page:1,
		height:140,
		width:500,
		showColModelButton:false,
		colNames:['id','encryptId','房屋编号','房屋地址','操作'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"encryptId",index:"encryptId",hidden:true,frozen:true},
	        {name:'houseCode',index:'houseCode',width:100,frozen:true},
	        {name:'address',index:'address',sortable:false,width:300},
	        {name:'name', index:'name', width:80, formatter: addPopulationToActualHouseFormatter}
		]
	});
	$("#searchHouseInfoForPopulation").click(function(){
		$("#houseInfoListForGrid").setGridParam({
			url:'${path}/baseinfo/actualHouseManage/searchHouseInfo.action?orgId=<s:property value="#getActualHouseById.houseInfo.organization.id"/>',
			datatype: "json",
			page:1
		});
		$("#houseInfoListForGrid").setPostData({
			"searchHouseInfoVo.houseCode":$("#searchHouseCode").val(),
	    	"searchHouseInfoVo.address":$("#searchHouseAddress").val(),
	    	"searchHouseInfoVo.id":<s:property value="#parameters.houseId[0]"/>
	   	});
		$("#houseInfoListForGrid").trigger("reloadGrid");
	});
	$("#refreshHouseInfoForPopulation").click(function(){
		$("#houseInfoListForGrid").setGridParam({
			url:'${path}/baseinfo/actualHouseManage/houseInfoList.action?orgId=<s:property value="#getActualHouseById.houseInfo.organization.id"/>&houseId=<s:property value="#parameters.houseId[0]"/>',
			datatype: "json",
			page:1
		});
		$("#searchHouseCode").val("");
		$("#searchHouseAddress").val("");
		$("#houseInfoListForGrid").trigger("reloadGrid");
	});

});
</script>