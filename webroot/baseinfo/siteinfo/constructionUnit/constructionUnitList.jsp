<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div class="btnbanner btnbannerData">
<div class="ui-widget autosearch">
	<input type="text"	value="请输入工程名称" name="searchText" id="searchText" maxlength="18"	class="searchtxt" 
	onblur="value=(this.value=='')?'请输入工程名称':this.value;"	
	onfocus="value=(this.value=='请输入工程名称')?'':this.value;" />
	<button id="refreshSearchKey"	class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
<a href="javascript:;" id="fastSearchButton"><span>查询</span></a>
</div>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addConstructionUnit">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateConstructionUnit">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewConstructionUnit">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteConstructionUnit">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchConstructionUnit">
			<a id="search" href="javascript:void(0)"><span>高级查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importConstructionUnit">
			<a id="import" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downConstructionUnit">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="shiftConstructionUnit"> 
			<a id="shift" href="javascript:void(0)"><span>转移</span></a>
	</pop:JugePermissionTag> 
		
		<pop:JugePermissionTag ename="abolishConstructionUnit">
			<a id="isEmphasis" href="javascript:void(0)"><span>取消关注</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="anewConstructionUnit">
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
    <div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="constructionUnitList"></table>
		<div id="constructionUnitListPager"></div>
	</div>
	<div id="constructionUnitDialog"></div>
	<div id="noticeDialog"></div>
	<div id="constructionUnitMaintanceDialog"></div>
</div>

<div style="display: none;"><pop:JugePermissionTag
	ename="constructionUnitManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
var isgridBol = false;
var title=$("#title").html();
function onOrgChanged(orgId,isGrid){
	
	if(isGrid){
		$("#add").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
	
	isgridBol = isGrid;
	$("#constructionUnitList").setGridParam({
		url:'${path}/baseinfo/constructionUnitManage/constructionUnitList.action',
		datatype: "json",
		page:1
	});
	$("#constructionUnitList").setPostData({
		"organization.id":function(){
	    	return getCurrentOrgId();
		},
		"constructionUnit.isEmphasis":$("#isLock").val()
	});
	$("#constructionUnitList").trigger("reloadGrid");
}

$(document).ready(function(){
	//快速查询的刷新图标
	$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	
	$("#constructionUnitList").jqGridFunction({
		datatype:'local',
		colNames:['id','工程名称','工程地点','开发单位','开发单位联系人','开发单位联系人电话','总承包方','总承包方联系人','总承包方联系人电话',
		  		  '劳务方','劳务方联系人','劳务方联系人电话','工资支付日期','实际发放工资总额','应签订劳动合同人数','未签订劳动合同人数','从业人数',
		  		  '开工时间','所属网格','是否关注'],
		colModel:[{name:"id",index:'id',hidden:true},
		      	  {name:"projectName",index:'projectName',width:80},
		      	  {name:"projectAddress",index:'projectAddress',width:80},
		      	  {name:"developmentUnit",index:'developmentUnit',width:80},
		      	  {name:"developmentContactPerson",index:'developmentContactPerson',width:110},
		      	  {name:"developmentContactPersonPhone",index:'developmentContactPersonPhone',width:130},
		      	  {name:"contractor",index:'contractor',width:80},
		      	  {name:"contractorContactPerson",index:'contractorContactPerson',width:110},
		      	  {name:"contractorContactPersonPhone",index:'contractorContactPersonPhone',width:130},
		      	  {name:"laborer",index:'laborer',width:80},
		      	  {name:"laborerContactPerson",index:'laborerContactPerson',width:110},
		      	  {name:"laborerContactPersonPhone",index:'laborerContactPersonPhone',width:130},
		      	  {name:"salaryPayDate",index:'salaryPayDate',width:120,hidden:true},
		      	  {name:"actualTotalSalary",index:'actualTotalSalary',width:120,hidden:true},
		          {name:"shouldSignContractNumber",index:'shouldSignContractNumber',width:120,hidden:true},
		      	  {name:"notSignContractNumber",index:'notSignContractNumber',width:120,hidden:true},
		      	  {name:"workersNumber",index:'workersNumber',width:120,hidden:true},
		      	  {name:"startTime",index:'startTime',width:120,hidden:true},
		      	  {name:"organization.orgName",index:'orgInternalCode',width:200, hidden:true},
			      {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100}],    	 
		<pop:JugePermissionTag ename="viewConstructionUnit">
			ondblClickRow: viewConstructionUnit,
		</pop:JugePermissionTag>
		loadComplete:afterLoad,
		onSelectAll:function(aRowids,status){ 
		
		if(status){
			
	   		var selectedIds = $("#constructionUnitList").jqGrid("getGridParam", "selarrrow");
	   		for(var i = 0;i<=selectedIds.length;i++){
	   			var row=$("#constructionUnitList").getRowData(selectedIds[i]);
	   			if(row.isEmphasis==1){
	   				$("#emphasis").buttonEnable();
	   			}
	   			if(row.isEmphasis==0){
	   				$("#isEmphasis").buttonEnable();

	   			}
	   			if(selectedIds.length ==1){
			   		$("#view").buttonEnable();
					$("#update").buttonEnable();
				}
	   		if(selectedIds.length != 0){
			   	$("#delete").buttonEnable();
			   	if(isGrid()){
					$("#shift").buttonEnable();
			   	}
		   	}
	   		if(selectedIds.length >2){
   				$("#view").buttonDisable();
				$("#update").buttonDisable();
				$("#emphasis").buttonDisable();
				$("#isEmphasis").buttonDisable();
   			}
	    	}
	    	
	   	}else{$("#delete").buttonDisable();
	   	
	   	$("#shift").buttonDisable();
		$("#view").buttonDisable();
		$("#update").buttonDisable();
		$("#emphasis").buttonDisable();
		$("#isEmphasis").buttonDisable();
	   	}},
	   	
		multiselect:true,
		onSelectRow:selectRow
	});

	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}

	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	//新增
	$("#add").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#constructionUnitDialog").createDialog({
			width: 640,
			height: 500,
			title:'新增'+title+'信息',
			url:'${path}/baseinfo/constructionUnitManage/dispatch.action?mode=add&organization.id='+getCurrentOrgId(),
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

	//转移
	$("#shift").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#constructionUnitDialog").createDialog({
			width: 300,
			height: 300,
			title:"转移"+title+"信息",
			url:'${path}/baseinfo/constructionUnitManage/shiftDispatch.action?orgId='+getCurrentOrgId()+'&constructionUnitIds='+$("#constructionUnitList").jqGrid("getGridParam", "selarrrow"),
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

	//查看
	$("#view").click(function(event){
		viewConstructionUnit();
	});

	//取消关注
	$("#isEmphasis").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/constructionUnitManage/updateConstructionUnitById.action",
			data:{
				"constructionUnit.id":selectedId,
				"constructionUnit.isEmphasis":1
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#constructionUnitList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','#778899');
				}else{
					$("#constructionUnitList").delRowData(responseData.id);
				}
				$.messageBox({message:"取消关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	//重新关注
	$("#emphasis").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/constructionUnitManage/updateConstructionUnitById.action",
			data:{
				"constructionUnit.id":selectedId,
				"constructionUnit.isEmphasis":0
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#constructionUnitList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','black');
				}else{
					$("#constructionUnitList").delRowData(responseData.id);
				}
				$.messageBox({message:"重新关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});


	//删除
	$("#delete").click(function(event){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		var str = relatePlace(allValue);
		$.confirm({
			title:"确认删除",
			message:str,
			width:400,
			okFunc: deleteConstructionUnit
		});
	});

	//刷新
	$("#reload").click(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	//修改
	$("#update").click(function(event){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		$("#constructionUnitDialog").createDialog({
			width: 640,
			height: 500,
			title: '修改'+title+'信息',
			url:'${path}/baseinfo/constructionUnitManage/dispatch.action?mode=edit&constructionUnit.id='+selectedId,
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

	//高级查询
	$("#search").click(function(event){
		$("#constructionUnitDialog").createDialog({
			width: 640,
			height: 300,
			title:title+'查询-请输入查询条件',
			url:'${path}/baseinfo/constructionUnitManage/dispatch.action?mode=search&organization.id='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
				searchConstructionUnit();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});

	});
	//快速查询
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});

	//导入
	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=constructionUnit&dialog=noticeDialog&startRow=5&templates=CONSTRUCTIONUNIT',
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

	//导出
	$("#export").click(function(event){
		if ($("#constructionUnitList").getGridParam("records")>0){
			$("#constructionUnitMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出'+title+'信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'constructionUnitList',
					downloadUrl:'${path}/baseinfo/searchConstructionUnit/downloadConstructionUnit.action'
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

//查看
function viewConstructionUnit(){
	var selectedId = getSelectedIdLast();
 	if(null==selectedId){
  		return;
 	}
 	$("#constructionUnitDialog").createDialog({
 	 	width: 640,
 		height: 500,
 		title:'查看'+title+'信息',
 		url:'${path}/baseinfo/constructionUnitManage/dispatch.action?mode=view&constructionUnit.id='+selectedId,
 		buttons: {
 			"打印" : function(event){
	    		$(this).dialog("close");
	    		printConstructionUnit(selectedId);
			},
 	   		"关闭" : function(){
 	        	$(this).dialog("close");
 	   		}
 		}
 	});
 }

//查看中的打印
function printConstructionUnit(selectedId){
	var url='${path}/baseinfo/constructionUnitManage/dispatch.action?mode=print&constructionUnit.id='+selectedId;
	$("#constructionUnitDialog").createDialog({
		width: 800,
		height: 380,
		title: '打印'+title+'信息',
		modal: true,
		url: url,
		buttons: {
			"确定" : function(event){
				$("#constructionUnitPrint").printArea();
				$(this).dialog("close");
			},
	  		 "关闭" : function(){
	       		 $(this).dialog("close");
	  		 }
		}
	});
}

//删除
function deleteConstructionUnit(){
	var allValue  = setCompVal();
	if(allValue.length ==0){
		 return;
	}
	$.ajax({
		url:"${path}/baseinfo/constructionUnitManage/deleteConstructionUnitById.action?constructionUnitIds="+allValue,
		success:function(data){
			$("#constructionUnitList").trigger("reloadGrid");
		    $.messageBox({message:"已经成功删除该"+title+"信息!"});
		    $("input[role='checkbox']").attr("checked",false);
		    disableButtons();
		    checkExport();
		}
	});
}
//快速查询
function search(orgId){
	var conditions = $("#searchText").val();
	if(conditions == '请输入工程名称') return;
	var initParam = {
			"organization.id":orgId
		}
	if($("#isLock").val()==""){
		initParam = {
				 "organization.id":orgId,
				 "constructionUnitVo.projectName":conditions
			}
	}else{
		initParam = {
				 "organization.id":orgId,
				 "constructionUnitVo.projectName":conditions,
				 "constructionUnitVo.isEmphasis":$("#isLock").val()
			}
	}
	$("#constructionUnitList").setGridParam({
		url:'${path}/baseinfo/searchConstructionUnit/searchConstructionUnit.action',
		datatype: "json",
		page:1
	});
	$("#constructionUnitList").setPostData(initParam);
	$("#constructionUnitList").trigger("reloadGrid");	
}

//高级查询
function searchConstructionUnit(){
	var	projectNameCondition=$("#conditionProjectName").val();
	var	projectAddressCondition=$("#conditionProjectAddress").val();
	var	developmentUnitCondition=$("#conditionDevelopmentUnit").val();
	var	developmentContactPersonCondition=$("#conditionDevelopmentUnitContactPerson").val();
	var	developmentContactPersonPhoneCondition=$("#conditionDevelopmentUnitContactPersonPhone").val();
	var	contractorCondition=$("#conditionContractor").val();
	var	contractorContactPersonCondition=$("#conditionContractorContactPerson").val();
	var	contractorContactPersonPhoneCondition=$("#conditionContractorContactPersonPhone").val();
	var	laborerCondition=$("#conditionLaborer").val();
	var	laborerContactPersonCondition=$("#conditionLaborerContractorContactPerson").val();
	var	laborerContactPersonPhoneCondition=$("#conditionLaborerContractorContactPersonPhone").val();
	$("#constructionUnitList").setGridParam({
		url:'${path}/baseinfo/searchConstructionUnit/searchConstructionUnit.action',
		datatype: "json",
		page:1
	});
	$("#constructionUnitList").setPostData({
    	"organization.id":getCurrentOrgId(),
    	"constructionUnitVo.projectName":projectNameCondition,
    	"constructionUnitVo.projectAddress":projectAddressCondition,
    	"constructionUnitVo.developmentUnit":developmentUnitCondition,
    	"constructionUnitVo.developmentContactPerson":developmentContactPersonCondition,
    	"constructionUnitVo.developmentContactPersonPhone":developmentContactPersonPhoneCondition,
    	"constructionUnitVo.contractor":contractorCondition,
    	"constructionUnitVo.contractorContactPerson":contractorContactPersonCondition,
    	"constructionUnitVo.contractorContactPersonPhone":contractorContactPersonPhoneCondition,
    	"constructionUnitVo.laborer":laborerCondition,
    	"constructionUnitVo.laborerContactPerson":laborerContactPersonCondition,
    	"constructionUnitVo.laborerContactPersonPhone":laborerContactPersonPhoneCondition,
    	"constructionUnitVo.isEmphasis":$("#isLock").val()
   	});
	$("#constructionUnitList").trigger("reloadGrid");
}

function afterLoad(){
	disableButtons();
	checkExport();
	isEmphasisFormatter();
}
//检查导出按钮是否可用
function checkExport(){
	if($("#constructionUnitList").getGridParam("records")>0
			|| $("#constructionUnitList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
function disableButtons(){
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
	$("#isEmphasis").buttonDisable();
	$("#emphasis").buttonDisable();
	$("#shift").buttonDisable();
}

//选择记录
function selectRow(){
	//已选择的记录条数
	var selectedCounts = getActualjqGridMultiSelectCount("constructionUnitList");
	//记录总条数
	var count = $("#constructionUnitList").jqGrid("getGridParam","records");
	
	if(selectedCounts == count){
		//把第一个checkBox（全选）设置为选中状态
		jqGridMultiSelectState("constructionUnitList", true);
	}else{
		jqGridMultiSelectState("constructionUnitList", false);
	}
	//已被选中记录的id
	var selectedId = $("#constructionUnitList").getGridParam("selrow");
	
	//if(selectedId==null){
	//  return;
	//}
		//被选中的每条记录即实体对象
	var constructionUnit =  $("#constructionUnitList").getRowData(selectedId);
		//搜索按钮一直可用
		$("#search").buttonEnable();
		if(isGrid()){
			$("#shift").buttonEnable();
		}else{
			$("#shift").buttonDisable();
		}

		
		if(selectedCounts==0){
			disableButtons();
		}
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var constructionUnitNew = $("#constructionUnitList").getRowData(id);
			$("#view").buttonEnable();
			$("#update").buttonEnable();
			$("#delete").buttonEnable();
			//$("#shift").buttonEnable();
			if(constructionUnitNew.isEmphasis!=1){
				$("#isEmphasis").buttonEnable();
				$("#emphasis").buttonDisable();
			}else{
				$("#emphasis").buttonEnable();
				$("#isEmphasis").buttonDisable();
			}
		}
		if(selectedCounts>1){
			$("#view").buttonDisable();
			$("#update").buttonDisable();
			$("#emphasis").buttonDisable();
			$("#isEmphasis").buttonDisable();
			}
	
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#constructionUnitList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#constructionUnitList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}

function relatePlace(placeIds){
	var str="";
	$.ajax({
		async:false,
		url: "${path}/baseinfo/constructionUnitManage/hasRelatePlace.action?constructionUnitIds="+placeIds,
		success:function(responseData){
			if(responseData){
				str="有"+title+"被引用,要删除未被引用的"+title+"吗?一经删除将无法恢复!";
			}else{
				str="该"+title+"信息删除后,将无法恢复,您确认删除该"+title+"信息吗?";
			}
		}
	});
	return str;
}

function setCompVal(){
	var selectedIds = $("#constructionUnitList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#constructionUnitList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
</script>

