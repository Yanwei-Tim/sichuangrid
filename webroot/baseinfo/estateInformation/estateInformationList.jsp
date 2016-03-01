<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addEstateInformation">
			<a id="add" href="#">新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateEstateInformation">
			<a id="update" href="#"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewEstateInformation">
			<a id="view" href="#"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteEstateInformation">
			<a id="delete" href="#"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchEstateInformation">
			<a id="search" href="#"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload"  href="#"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importEstateInformation">
			<a id="import" href="#"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downEstateInformation">
			<a id="export" href="#"><span>导出</span></a>
   		</pop:JugePermissionTag>

	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="estateInformationList"> </table>
		<div id="estateInformationListPager"></div>
	</div>
	<div id="estateInformationDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=490;
<pop:formatterProperty name="houseEquity" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_EQUITY" />
<pop:formatterProperty name="buildingStructure" domainName="@com.tianque.domain.property.PropertyTypes@BUILDING_STRUCTURE" />
<pop:formatterProperty name="structureYear" domainName="@com.tianque.domain.property.PropertyTypes@STRUCTURE_YEAR" />

function onOrgChanged(orgId){
	$("#estateInformationList").setGridParam({
		url:"${path}/baseinfo/estateInformationManage/estateInformationList.action",
		datatype: "json"
	});
	$("#estateInformationList").setPostData({
    	"ownerOrg.id":orgId,
    	"page":1,
    	"orgId":orgId
   	});
	$("#estateInformationList").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#estateInformationList").jqSubGrid({
		datatype: "local",
		colModel:[
	    	{name:"id", index:"id", hidden:true },
	    	{name:"organization.orgName", index:"a.orgInternalCode",label:"所属网格",width:150,align:"center"},
	    	{name:"proprietaryNo",index:"a.proprietaryNo", label:"房产证号",width:120,align:"center"},
	    	{name:"landPermit",index:"a.landPermit", label:"土地证号",width:120,align:"center"},
	    	{name:"houseEquity", index:"a.houseEquity",label:"住宅产权",width:80,sortable:false,formatter:houseEquityFormatter,align:"center"},
	    	{name:"coveredArea", label:"建筑面积",width:50,align:"center"},
			{name:"floorArea", label:"占地面积",width:80,align:"center"},
  			{name:"buildingStructure",label:"建筑结构",width:80,sortable:false, formatter:buildingStructureFormatter,align:"center"},
  			{name:"structureYear",label:"建构年代",width:100,sortable:false,formatter:structureYearFormatter,align:"center"}
	  	],
	  	sortname:"a.id",
    	loadComplete: afterLoad,
		<pop:JugePermissionTag ename="viewEstateInformation">
        	ondblClickRow: doubleClickTable,
		</pop:JugePermissionTag>
		onSelectRow:selectRow,
		gridComplete:function(){},
		subGridPostData:function(data){return {id:data.id}},
		subGridUrl:"${path}/baseinfo/estateInformationManage/dispatchOperate.action?mode=subInhabitant"
	});
	$("#add").click(function(){
		if (getCurrentOrgId()==null||getCurrentOrgId()==""){
			$.notice({level:"warn", message:"请先选择一个部门"});
		}else{
			$("#estateInformationDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"新增房产信息",
				url:"${path}/baseinfo/estateInformationManage/dispatchOperate.action?mode=add&ownerOrg.id="+getCurrentOrgId(),
				buttons: {
			   		"保存" : function(event){
			   			$("#maintainForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
	});
	$("#update").click(function(){
		var selectedId = $("#estateInformationList").getGridParam("selrow");
		if(selectedId==null){
			 return;
		}
		$("#estateInformationDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"修改房产信息",
			url:"${path}/baseinfo/estateInformationManage/dispatchOperate.action?mode=edit&estateInformation.id="+selectedId,
			buttons :{
				"保存" : function(){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#delete").click(function(){
		$.confirm({
				title:"确认删除",
				message:"房产信息一经删除，无法恢复，确定删除此房产信息吗?",
				okFunc: function() {
					$.ajax({
						url:"${path}/baseinfo/estateInformationManage/deleteEstateInformation.action?estateInformation.id="+$("#estateInformationList").getGridParam("selrow"),
						success:function(data){
							if(data == true){
								$("#estateInformationList").delRowData($("#estateInformationList").getGridParam("selrow"));
							    $.messageBox({message:"已经成功删除该房产信息!"});
							    disableButtons();
							}else{
								$.messageBox({message:"找不到要删除的房产信息，不能删除!"});
							}
					}
				});
			}
		});
	});
	$("#view").click(function(){
		doubleClickTable();
	});
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId());
	});
	$("#search").click(function(event){
		$("#estateInformationDialog").createDialog({
			width: 500,
			height: 250,
			title:"查询房产信息",
			url:'${path}/baseinfo/estateInformationManage/dispatchOperate.action?mode=search&ownerOrg.id='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
				    $("#searchEstateInformationForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#import").click(function(event){
		$("#estateInformationDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=estateInformation&dialog=estateInformationDialog&startRow=5&templates=ESTATEINFORMATION',
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

	$("#export").click(function(event){
		if ($("#estateInformationList").getGridParam("records")>0){
			$("#estateInformationDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出求职人员信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'estateInformationList',
					downloadUrl:'${path}/baseinfo/searchEstateInformation/downloadEstateInformation.action'
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
			$.messageBox({message:"没有要导出的数据!"});
		}
	});



	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId());
	}
})

function doubleClickTable(){
	var selectedId = $("#estateInformationList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	$("#estateInformationDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:"查看房产信息",
		url:"${path}/baseinfo/estateInformationManage/dispatchOperate.action?mode=view&estateInformation.id="+selectedId,
		buttons: {
		   "打印" : function(){
			inhabitantPrint(selectedId);
	  	   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function inhabitantPrint(selectedId){
	$("#estateInformationDialog").createDialog({
		width:850,
		height:dialogHeight,
		title:"打印房产信息",
		url:"${path}/baseinfo/estateInformationManage/housePropertyInfoPrint.action?estateInformation.id="+selectedId,
		buttons: {
			  "打印" : function(){
			$("#housePropertyInfoPrint").printArea();
        	$(this).dialog("close");
	  	 },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
}
function selectRow(){
	$("#view").buttonEnable();
	$("#update").buttonEnable();
	$("#delete").buttonEnable();
}

</script>