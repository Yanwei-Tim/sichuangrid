<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<div class="content" id="actualHousesListDiv">
<div style="clear: both;"></div>
<div style="width: 100%;">
<table id="actualHouseList"></table>
<div id="actualHouseListPager"></div>
</div>
<div id="actualHouseMaintanceDialog"></div>
<div id="noticeDialog"></div>
<div id="searchDialog"></div>
<div id="personInChargeDialog"></div>
<div id="floorpersonDialog"></div>
<div id="actualAppendPopulationDialog"></div>
</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="actualHouseManagement">
	<span id="title"><s:property value="#request.name" /></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
<pop:formatterProperty name="houseStructure" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<pop:formatterProperty name="houseSource" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" />
<pop:formatterProperty name="houseUses" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewActualHouse'><a href='javascript:;' onclick='viewHouseInfo("+rowData.id+")'><span>查看</span></a> </pop:JugePermissionTag>";
}

function memberNumFormatter(el,options,rowData){
	return "<a href='javascript:viewActualHousePopulation("+rowData.id+")'>"+rowData.memberNum+"</a>";
}
function isRentalhouseFormatter(el,options,rowData){
	if(true==rowData.isRentalHouse){
		return '是';
	}else{
		return '否';
	}
}
function viewActualHousePopulation(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 500,
		title:'查看居住人员信息',
		modal : true,
		//url:'${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseCode.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.houseCode='+houseInfo.houseCode,
		url:'${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.id='+houseInfo.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
var dialogWidth=760;
var dialogHeight=510;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();

function onOrgChanged(orgId,isgrid){
	if(isgrid)
		$("#addActualHouse").buttonEnable();
    else
    	$("#addActualHouse").buttonDisable();
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#actualHouseList").setGridParam({
		url:'${path}/baseinfo/actualHouseManage/houseInfoList.action',
		datatype: "json",
		page:1
	});
	$("#actualHouseList").setPostData({
		"orgId":function(){return currentOrgId;}
	});
	$("#actualHouseList").trigger("reloadGrid");
}

function getPostDataForList(){
	var condition = $("#searchByCondition").val();
	var initParam = {
		"orgId": getCurrentOrgId(),
		"searchHouseInfoVo.houseUses.id" : $("#houseUsesId").val()
	}
	if($("#houseIsRentalHouse").val()!=""){
		initParam = $.extend(initParam,{"searchHouseInfoVo.rentalHouse" : $("#houseIsRentalHouse").val()});
	}
	if(condition != '请输入房屋编号或房屋地址' || condition !=""){
		initParam = $.extend(initParam,{"searchHouseInfoVo.houseCodeAndAddress":condition});
	}
	return initParam;
}

function search(){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入房屋编号或房屋地址') return;
	$("#actualHouseList").setGridParam({
		url:'${path}/baseinfo/actualHouseManage/searchHouseInfo.action',
		datatype: "json",
		page:1
	});
	$("#actualHouseList").setPostData(getPostDataForList());
	$("#actualHouseList").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#houseUsesId,#houseIsRentalHouse").change(function(){
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/actualHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData(getPostDataForList());
		$("#actualHouseList").trigger("reloadGrid");
	});
	$("#actualHouseList").jqGridFunction({
		datatype: "local",
		height:$("#actualHousesListDiv").parent().height()-70,
		colModel:[
			        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
			        {name:"operator", index:'id', label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
			        //{name:'houseCode-Font',index:'houseCode',label:'房屋编号',formatter:houseCodeFont,width:120,sortable:true,frozen:true},
			        //{name:'houseCode',index:'houseCode',label:'房屋编号',width:100,hidden:true,frozen:true},
			        {name:'address',index:'address',label:'房屋地址',sortable:true,width:200},
			        {name:'name',index:'name',label:'产权人姓名',width:80,sortable:true,frozen:true},
			        {name:'certificateNumbe',index:'certificateNumbe',label:'产权人证件号码',sortable:true,width:200},
			        {name:'memberNum',index:'memberNum',label:'居住人数',sortable:false,formatter:memberNumFormatter,width:80,align:'center'},
			        {name:'houseSource', index:'houseSource',label:'房屋来源',sortable:true, width:80,hidden:true, formatter:houseSourceFormatter},
			        {name:"houseUses",index:'houseUses',label:'房屋用途',sortable:true, width:100,formatter:houseUsesFormatter},
			        {name:'isRentalHouse',index:'isRentalHouse',label:'是否出租房',sortable:true,width:100,align:"center",formatter:isRentalhouseFormatter},
			        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			        {name:'updateDate', label:'数据更新时间', width:150,sortable:true,align:"center"},
			        {name:'houseArea',index:'houseArea',label:'住房面积(㎡)',hidden:true,sortable:true,width:100},
			        {name:'houseStructure',index:'houseStructure',label:'住房结构',hidden:true,sortable:true,formatter:houseStructureFormatter,width:110},
			        {name:'houseOwner',index:'houseOwner',label:'代表人/业主',hidden:true,width:120,sortable:true,frozen:true},
		            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',hidden:true,sortable:false,width:230}
				],
		<pop:JugePermissionTag ename="viewActualHouse">
		ondblClickRow: viewHouseInfo,
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectAll:function(aRowids,status){ if(status) {
	   		var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	   		for(var i = 0;i<=selectedIds.length;i++){
	   			var row=$("#actualHouseList").getRowData(selectedIds[i]);
	   			if(selectedIds.length ==1){
			   		$("#viewActualHouse").buttonEnable();
			   		$("#mantanceLivinginHousePopulation").buttonEnable();
					$("#updateActualHouse").buttonEnable();
				}
		   		if(selectedIds.length != 0){
				   	$("#deleteActualHouse").buttonEnable();
			   	}
		   		if(selectedIds.length >2){
	   				$("#viewActualHouse").buttonDisable();
					$("#updateActualHouse").buttonDisable();
			   		$("#mantanceLivinginHousePopulation").buttonDisable();
	   			}
	    	}
	   	}else{
		   	$("#deleteActualHouse").buttonDisable();
	   		$("#viewActualHouse").buttonDisable();
			$("#updateActualHouse").buttonDisable();
	   		$("#mantanceLivinginHousePopulation").buttonDisable();
	   	}},
		multiselect:true,
		onSelectRow:function(){setHouseInfoOperateButton();}
	});
	jQuery("#actualHouseList").jqGrid('setFrozenColumns');
	
	
	if("${searchType}"=='fast'){
		search();
	}else if("${searchType}"=='advanced'){
		searchActualHouseInfo();
	}
	//$("#ActualHousestatisticsListDialog").dialog("close");
	/*function houseCodeFont(el,options,rowData){
		return "<a href='javascript:viewHouseInfo("+rowData.id+")'>"+rowData.houseCode+"</a>";
	}*/

$("#${moduleName}statisticsDialog").dialog("close");
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#mantanceLivinginHousePopulation").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var houseInfo =  $("#actualHouseList").getRowData(selectedId);
		$("#actualHouseMaintanceDialog").createDialog({
			width: 750,
			height: 570,
			title:'维护使用人信息',
			url:'${path}/baseinfo/actualHousePopulation/prepairMantanceLivingPopulation.action?houseInfo.houseCode='+houseInfo.houseCode+"&orgId="+houseInfo["organization.id"],
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#addActualHouse").click(function(event){
		if (!isgridBol){
			return;
		}
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			$("#actualHouseMaintanceDialog").createFrameDialog(
				{
					model :"add",
					title:"新增房屋信息",
					width: 800,
					height: 640,
					data:[
						{title:'房屋信息',url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?dailogName=actualHouseMaintanceDialog&mode=add&orgId='+currentOrgId,buttons:{save:true,saveContinue:true}}
					]
				}
			);
		}
	});

	$("#updateActualHouse").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var houseInfo =  $("#actualHouseList").getRowData(selectedId);
		$("#actualHouseMaintanceDialog").createFrameDialog({
			model :"edit",
			title:'修改房屋信息',
			width: 800,
			height: 650,
			data:[
				{title:'房屋信息',url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?dailogName=actualHouseMaintanceDialog&mode=edit&houseInfo.houseCode='+houseInfo.houseCode+"&houseInfo.organization.id="+houseInfo["organization.id"],buttons:{next:true}},
				{title:'出租房',url:'${path}/baseinfo/rentalHouseManage/dispatchOperate.action?dailogName=actualHouseMaintanceDialog&mode=edit&houseInfo.houseCode='+houseInfo.houseCode+"&houseInfo.organization.id="+houseInfo["organization.id"],buttons:{prev:true,save:true,saveContinue:true}}
			]
		});
	});

	$("#importActualHouse").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=actualHouseData&dialog=noticeDialog&startRow=6&templates=ACTUALHOUSE',
			buttons:{
					"导入" : function(event){
				      $("#mForm").submit();
				   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			},
			shouldEmptyHtml:false
		});
	});

	$("#searchActualHouse").click(function(event){
		$("#searchDialog").createDialog({
			width: 760,
			height: 400,
			datatype: "json",
			title:'查询房屋信息',
			url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
			buttons: {
				"查询" : function(event){
				searchActualHouseInfo();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	function searchActualHouseInfo() {
		
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/actualHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData($.extend({"searchHouseInfoVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchActualHouseForm").serializeObject()));
		$("#actualHouseList").trigger("reloadGrid");
		$("#ActualHousestatisticsDialog").dialog("close");
	}

	$("#deleteActualHouse").click(function(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.ajax({
			url:"${path}/baseinfo/actualHouseManage/houseHasActualPopulation.action?houseIds="+allValue,
			success:function(data){
				if(data){
					 $.messageBox({message:"房屋信息不能被删除，请先移除房屋中的人口信息!",level:'error'});
				}else{
					$.confirm({
						title:"确认删除",
						message:"确定要删除吗?一经删除将无法恢复",
						okFunc: function(){
							$.ajax({
								url:"${path}/baseinfo/actualHouseManage/deleteHouseInfo.action?houseIds="+allValue,
								success:function(data){
									$("#actualHouseList").trigger("reloadGrid");
								    $.messageBox({message:"已经成功删除该房屋信息!"});
								    disableButtons();
									checkExport();
							    }
						    });
					   }populationStat_stat
				 	});
				}
			}
		})
	});

	$("#viewActualHouse").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewHouseInfo(selectedId);
	});

	$("#reload").click(function(event){
		$("#conditionMark").val("fast_hosueCode");
		$("#searchByCondition").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#searchByConditionButton").click(search);

	$("#exportActualHouse").click(function(event){
        if ($("#actualHouseList").getGridParam("records")>0){
            $("#actualHouseMaintanceDialog").createDialog({
                width: 250,
                height: 200,
                title:'导出房屋信息',
                url:'${path}/common/exportExcel.jsp',
                postData:{
                    gridName:'actualHouseList',
                    orgId:getCurrentOrgId(),
                    downloadUrl:'${path}/baseinfo/actualHouseManage/downloadHouseInfo.action'
                },
                buttons: {
                    "导出" : function(event){
                        $("#exceldownload").submit();
                        $(this).dialog("close");
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                },
                shouldEmptyHtml:false
            });
        }else{
        }
    });

});
function afterLoad(){
	disableButtons();
	setHouseInfoOperateButton();
	checkExport();
}

function disableButtons(){
	$("#updateActualHouse").buttonDisable();
	$("#viewActualHouse").buttonDisable();
	$("#deleteActualHouse").buttonDisable();
	$("#mantanceLivinginHousePopulation").buttonDisable();
}
function checkExport(){
	if($("#actualHouseList").getGridParam("records")>0
			|| $("#actualHouseList").getGridParam("selrow")!=null){
		$("#exportActualHouse").buttonEnable();
	}else{
		$("#exportActualHouse").buttonDisable();

	}
}
function setHouseInfoOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("actualHouseList");
	var count = $("#actualHouseList").jqGrid("getGridParam","records");
	if(selectedCounts == count && count > 0){
		jqGridMultiSelectState("actualHouseList", true);
	}else{
		jqGridMultiSelectState("actualHouseList", false);
	}
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var houseInfoNew = $("#actualHouseList").getRowData(id);
			$("#updateActualHouse").buttonEnable();
			$("#viewActualHouse").buttonEnable();
			$("#deleteActualHouse").buttonEnable();
	   		$("#mantanceLivinginHousePopulation").buttonEnable();
		}else{
			$("#updateActualHouse").buttonDisable();
			$("#viewActualHouse").buttonDisable();
	   		$("#mantanceLivinginHousePopulation").buttonDisable();
		}
		if(selectedCounts==0){
			$("#deleteActualHouse").buttonDisable();
		}
}
function viewHouseInfo(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 650,
		title:'查看房屋信息',
		modal : true,
		url:'${path}/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseId='+houseInfo.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function printActualHouse(rowid){
	$("#actualHouseMaintanceDialog").createDialog({
		width: 790,
		height: dialogHeight,
		title: '打印房屋信息',
		modal: true,
		url: '${path}/baseinfo/actualHouseManage/dispatch.action?mode=print&houseInfo.id='+rowid,
		buttons: {
			"确定" : function(event){
				$("#houseInfoPrint").printArea();
				$(this).dialog("close");
			 },
	  		 "关闭" : function(){
	       		 $(this).dialog("close");
	  		 }
		}
	});
}

function setCompVal(){
	var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}

$("#refreshOrgTree1").click(function(){
	$("#searchByCondition").attr("value","");
});
</script>