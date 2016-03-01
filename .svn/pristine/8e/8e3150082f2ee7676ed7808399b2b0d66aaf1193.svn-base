<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
<!-- 
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addNewSocietyFederation">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateNewSocietyFederation">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewNewSocietyFederation">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteNewSocietyFederation">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchNewSocietyFederation">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importNewSocietyFederation">
		<a id="import" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downNewSocietyFederation">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
   		</pop:JugePermissionTag>
   		 <pop:JugePermissionTag ename="shiftNewSocietyFederation">
			<a id="shift" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="abolishOtherLocale">
			<a id="isEmphasis" href="javascript:void(0)"><span>取消关注</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="anewOtherLocale">
			<a id="emphasis" href="javascript:void(0)"><span>重新关注</span></a>
		</pop:JugePermissionTag>
		<div style="float: right;white-space:nowrap;">
				<select id="isLock" name="user.lock">
						<option value="-1">全部</option>
	 					<option value="0" selected="selected">现在关注</option>
	 					<option value="1">曾经关注</option>
				</select>
			</div>
    </div>
-->
    <div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="newSocietyFederationList"></table>
		<div id="newSocietyFederationListPager"></div>
	</div>
	<div id="newSocietyFederationDialog"></div>
	<div id="noticeDialog"></div>
	<div id="searchDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
</div>

<div style="display: none;"><pop:JugePermissionTag
	ename="newSocietyFederationManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>

<script type="text/javascript">
var dialogWidth=720;
var dialogHeight=500;
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" />
var isgridBol = false;
var title=$("#title").html();
function onOrgChanged(orgId,isgrid){
	if(isgrid){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
		$("#update").buttonDisable();
	}
	isgridBol = isgrid;
	$("#newSocietyFederationList").setGridParam({
		url:'${path}/baseinfo/newSocietyFederationManage/findNewSocietyFederationsForListPage.action',
		datatype: "json",
		page:1
	});
	$("#newSocietyFederationList").setPostData({
    	"organization.id":orgId,
    	"newSocietyFederation.isEmphasis":$("#isLock").val()
   	});
   	
	$("#newSocietyFederationList").trigger("reloadGrid");
}
$(document).ready(function(){
	$("#newSocietyFederationList").jqGridFunction({
		datatype: "local",
		colModel:[
	  		  {name:"id",index:'id',hidden:true},
	      	  {name:"name",index:'name',label:"名称",width:100},
	      	  {name:"type",index:'type',label:"类别",align:'center',formatter:typeFormatter,width:120},
	  	      {name:'organization.orgName',label:"所属区域",align:'center',sortable:false,width:180, hidden:true},
	      	  {name:"address",index:'address',label:"地址",align:'center',width:150},
	      	  {name:"legalPerson",index:'legalPerson',label:"负责人",align:'center',sortable:false,width:120},
	      	  {name:"legalPersonTelephone",index:'legalPersonTelephone',label:"联系电话",align:'center',sortable:false,width:120},
	      	  {name:"legalPersonMobileNumber",index:'legalPersonMobileNumber',label:"联系手机",align:'center',sortable:false,width:120},
	      	  {name:"majorDuty",index:"majorDuty",label:"主要职责",hidden:true,width:180},
	      	  {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100}
	        ],
		width:860,
		height:440,
     	loadComplete: afterLoad,
     	multiselect:false,
		onSelectAll:function(aRowids,status){ if(status) {
	   		var selectedIds = $("#newSocietyFederationList").jqGrid("getGridParam", "selarrrow");
	   		if(selectedIds.length != 0){
			   	$("#delete").buttonEnable();
			   	if(isGrid()){
					$("#shift").buttonEnable();
			   	}
		   	}
	   		if(selectedIds.length >1){
		   		$("#view").buttonDisable();
				$("#update").buttonDisable();
				$("#emphasis").buttonDisable();
				$("#isEmphasis").buttonDisable();
			}
	   	}else{$("#delete").buttonDisable();$("#shift").buttonDisable();}},
		multiselect:false,
		<pop:JugePermissionTag ename="viewNewSocietyFederation">
			ondblClickRow: viewNewSocietyFederation,
		</pop:JugePermissionTag>
		onSelectRow:selectRow
	});

	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		if('<s:property value="#parameters.isSearch"/>' == 1){
			searchNewSocietyFederation();
		}
	}
	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	
	$("#add").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#newSocietyFederationDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'新增'+title+'信息',
			url:'${path}/baseinfo/newSocietyFederationManage/dispatch.action?mode=add&organization.id='+getCurrentOrgId(),
			buttons: {
		   		"保存并关闭" : function(event){
				   			$("#maintainForm").submit();
				   			$("#isSubmit").val("true");
			   				},
			   	"保存并继续" : function(event){
				   			$("#maintainForm").submit();
				   			$("#isSubmit").val("false");
			   			},
				  "关闭" : function(){
				        	$(this).dialog("close");
				   		}
			}
		});
	});

	$("#shift").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#newSocietyFederationDialog").createDialog({
			width: 300,
			height: 300,
			title:"转移"+title+"信息",
			url:'${path}/baseinfo/newSocietyFederationManage/shiftDispatch.action?organization.id='+getCurrentOrgId()+'&newSocietyFederationIds='+$("#newSocietyFederationList").jqGrid("getGridParam", "selarrrow"),
			buttons: {
				"保存" : function(event){
			      $("#maintainShiftForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});
	$("#import").click(function(event){
		$("#newSocietyFederationDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=newSocietyFederationData&dialog=newSocietyFederationDialog&startRow=5&templates=NEWSOCIETYFEDERATION',
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
			if ($("#newSocietyFederationList").getGridParam("records")>0){
				$("#newSocietyFederationDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'newSocietyFederationList',
						downloadUrl:'${path}/baseinfo/searchNewSocietyFederation/downloadNewSocietyFederation.action'
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

	$("#update").click(function(event){
		var selectedId = $("#newSocietyFederationList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$("#newSocietyFederationDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title: '修改'+title+'信息',
			url:'${path}/baseinfo/newSocietyFederationManage/dispatch.action?mode=edit&newSocietyFederation.id='+selectedId,
			buttons: {
		   		"保存" : function(event){
	   				$("#maintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#delete").click(function(event){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.confirm({
			title:"确认删除",
			message:"该新"+title+"删除后,将无法恢复,您确认删除该"+title+"信息吗?",
			width:400,
			okFunc:deleteNewSocietyFederation
		});
	});

	$("#search").click(function(event){
		var query_orgInternalCode=$('#query_orgInternalCode').val();
		var query_name=$('#query_name').val();
	  	var query_legalPerson=$('#query_legalPerson').val();
	  	var query_typeId=$('#query_typeId').val();

		$("#searchDialog").createDialog({
			width: 600,
			height: 250,
			title:'查询'+title+'信息',
			url:'${path}/baseinfo/searchNewSocietyFederation/prepareSearchNewSocietyFederations.action?organization.id='+getCurrentOrgId(),
			buttons: {
				"查询" : function(event){
					searchNewSocietyFederation();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#isEmphasis").click(function(){
		var selectedId = $("#newSocietyFederationList").getGridParam("selrow");
		if(selectedId == null){
			return;
		}
		$.ajax({	
			url:"${path}/baseinfo/newSocietyFederationManage/updateNewSocietyFederationById.action",
			data:{
				"newSocietyFederation.id":selectedId,
				"newSocietyFederation.isEmphasis":1
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#newSocietyFederationList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','#778899');
				}else{
					$("#newSocietyFederationList").delRowData(responseData.id);
				}
				$.messageBox({message:"取消关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	$("#emphasis").click(function(){
		var selectedId = $("#newSocietyFederationList").getGridParam("selrow");
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/newSocietyFederationManage/updateNewSocietyFederationById.action",
			data:{
				"newSocietyFederation.id":selectedId,
				"newSocietyFederation.isEmphasis":0
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#newSocietyFederationList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','black');
				}else{
					$("#newSocietyFederationList").delRowData(responseData.id);
				}
				$.messageBox({message:"重新关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	$("#view").click(function(){
		viewNewSocietyFederation();
	});

	$("#reload").click(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
});

function viewNewSocietyFederation(){
	var selectedId = $("#newSocietyFederationList").getGridParam("selrow");
 	if(null==selectedId){
  		return;
 	}
 	$("#newSocietyFederationDialog").createDialog({
 	 	width: dialogWidth,
 		height: dialogHeight,
 		title:'查看'+title+'信息',
 		modal: true,
 		url:'${path}/baseinfo/newSocietyFederationManage/dispatch.action?mode=view&newSocietyFederation.id='+selectedId,
 		buttons: {
 	 		"打印":function(event){
 				$(this).dialog("close");
 				printNewSocietyFedration(selectedId,getCurrentOrgId());
 	 		},
 	   		"关闭" : function(){
 	 			$(this).dialog("close");
 	   		}
 		}
 	});
 }

function printNewSocietyFedration(selectedId,orgId){
	var url="${path}/baseinfo/newSocietyFederationManage/dispatch.action?mode=print&newSocietyFederation.id="+selectedId;
	$("#newSocietyFederationDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title: '打印'+title+'信息',
		url:url,
		buttons:{
			"确定":function(event){
				$("#newSocietyFederationPrint").printArea();
				$(this).dialog("close");
			},
			"关闭":function(){
				$(this).dialog("close");
			}
		}
	});
}

function deleteNewSocietyFederation(){
	var allValue = setCompVal();
	if(allValue.length ==0){
		 return;
	}
	$.ajax({
		url:"${path}/baseinfo/newSocietyFederationManage/deleteNewSocietyFederationById.action?newSocietyFederationIds="+allValue,
		success:function(data){
			$("#newSocietyFederationList").trigger("reloadGrid");
		    $.messageBox({message:"已经成功删除该"+title+"信息!"});
		    $("input[role='checkbox']").attr("checked",false);
		    disableButtons();
		    checkExport();
		}
	});
}
function getPlaceName(){
	var placeName = "";
	$("#query_name").length > 0?placeName = $("#query_name").val():placeName = $("#searchByPlaceName").val();
	return placeName;
}
function searchNewSocietyFederation(){
	var query_orgInternalCode=$('#query_orgInternalCode').val();
	var query_name=getPlaceName();
  	var query_legalPerson=$('#query_legalPerson').val();
  	var query_typeId=$('#query_typeId').val();

  	var query_address=$("#query_address").val();
  	var query_legalPersonTelephone=$("#query_legalPersonTelephone").val();
  	var query_legalPersonMobileNumber=$("#query_legalPersonMobileNumber").val();
  	var query_majorDuty=$("#query_majorDuty").val();
	$("#newSocietyFederationList").setGridParam({
		url:'${path}/baseinfo/searchNewSocietyFederation/searchNewSocietyFederations.action',
		datatype: "json",
		page:1
	});
	$("#newSocietyFederationList").setPostData({
		"organization.id":getCurrentOrgId(),
		"searchNewSocietyFederationVo.orgInternalCode":query_orgInternalCode,
		"searchNewSocietyFederationVo.name":query_name,
		"searchNewSocietyFederationVo.legalPerson":query_legalPerson,
		"searchNewSocietyFederationVo.typeId":query_typeId,
		"searchNewSocietyFederationVo.address":query_address,
		"searchNewSocietyFederationVo.legalPersonTelephone":query_legalPersonTelephone,
		"searchNewSocietyFederationVo.legalPersonMobileNumber":query_legalPersonMobileNumber,
		"searchNewSocietyFederationVo.majorDuty":query_majorDuty,
		"searchNewSocietyFederationVo.isEmphasis":0
   	});
	$("#newSocietyFederationList").trigger("reloadGrid");
	$("#statisticsDialog").dialog("close");
}

function afterLoad(){
	disableButtons();
	checkExport();
	isEmphasisFormatter();
}
function checkExport(){
	if($("#newSocietyFederationList").getGridParam("records")>0
			|| $("#newSocietyFederationList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
function disableButtons(){
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
	$("#shift").buttonDisable();
	$("#isEmphasis").buttonDisable();
	$("#emphasis").buttonDisable();
}

function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("newSocietyFederationList");
	var count = $("#newSocietyFederationList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("newSocietyFederationList", true);
	}else{
		jqGridMultiSelectState("newSocietyFederationList", false);
	}
	var selectedId = $("#newSocietyFederationList").getGridParam("selrow");
	if(selectedId==null){
	  return;
	}
	var newSocietyFederation =  $("#newSocietyFederationList").getRowData(selectedId);
		$("#search").buttonEnable();
		if(isGrid()){
			$("#shift").buttonEnable();
		}else{
			$("#shift").buttonDisable();
		}
		$("#delete").buttonEnable();
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var newSocietyFederationNew = $("#newSocietyFederationList").getRowData(id);
			$("#update").buttonEnable();
			$("#view").buttonEnable();
			if(newSocietyFederationNew.isEmphasis!=1){
				$("#isEmphasis").buttonEnable();
				$("#emphasis").buttonDisable();
			}else{
				$("#emphasis").buttonEnable();
				$("#isEmphasis").buttonDisable();
			}
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#emphasis").buttonDisable();
			$("#isEmphasis").buttonDisable();
		}
		if(selectedCounts==0){
			$("#delete").buttonDisable();
			$("#shift").buttonDisable();
		}
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#newSocietyFederationList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#newSocietyFederationList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}
function setCompVal(){
	var selectedIds = $("#newSocietyFederationList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#newSocietyFederationList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
</script>