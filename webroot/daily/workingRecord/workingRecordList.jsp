<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
			<input type="text" value="请输入名称" name="searchText" id="searchText" style="width:150px;left:300px;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入名称':this.value;" onfocus="value=(this.value=='请输入名称')?'':this.value;"/>
			<button id="refreshsearchText" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:10px;left:160px;cursor:pointer;outline: none;"></button>
		
			<a id="fastSearch" href="javascript:void(0)"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchWorkingRecord,searchAreaWorkingRecord">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
			<span class="lineBetween"></span>
		<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
		<pop:JugePermissionTag ename="addWorkingRecord">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateWorkingRecord">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		</s:if>
		<pop:JugePermissionTag ename="viewWorkingRecord,viewAreaWorkingRecord">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
		<pop:JugePermissionTag ename="deleteWorkingRecord">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		</s:if>
		
			<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
		<pop:JugePermissionTag ename="synchronizeWorkingRecord">	
			<a id="synchronize" href="javascript:void(0)"><span>同步资料库</span></a>
		</pop:JugePermissionTag>
		</s:if>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="workingRecordList"></table>
		<div id="workingRecordListPager"></div>
	</div>
	<div id="workingRecordDialog"></div>
	<div id="ObjDialog"></div>
	<div id="fileSharingDailog"></div>
	<input id="orgId" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>" />
</div>

<script type="text/javascript">
<pop:formatterProperty name="dailyLogType" domainName="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE" />
var dialogWidth=800;
var dialogHeight=480;
var globalFormatterArrary=new Array();
$(document).ready(function(){
	<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
		var _currentOrgId = $("#orgId").val();
	</s:if>
	<s:if test="#parameters.menuType[0].equals('areaWorkingRecordManagement')">
		var _currentOrgId = $("#currentOrgId").val();
	</s:if>
	//'内容',{name:'content',hidden:true,sortable:false,width:150},
	$("#workingRecordList").jqGridFunction({
		url:"${path}/workingRecord/workingRecordManage/workingRecordList.action",
		datatype: "json",
		page:1,
		postData:{
			"organization.id":_currentOrgId,
	    	"dailyDirectoryId":dailyDirectoryId,
	    	"displayLevel":$("#displayLevel").val()
		},
		colNames:['id','encryptId','文件号','名称','类型',"附件",'主题','时间','参加人员','地点','创建时间','时效性'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:'fileNo',index:'fileNo',sortable:true,width:180},
	        {name:'name',sortable:true,width:180},
	        {name:'dailyLogType',index:'dailyLogType',formatter:dailyLogTypeFormatter,sortable:true,width:200},
	  		{name:"dailyLogAttachFile",sortable:false,width:80,formatter:formatterAttachFile},
	        {name:'subject',hidden:false,sortable:false,width:200},
	  		{name:"dealDate",hidden:false,sortable:false,align:"center",width:100},
	        {name:'participant',hidden:true,sortable:false,width:100},
	        {name:'proceedSite',hidden:true,sortable:false,width:150},
	        {name:'createDate',hidden:false,sortable:false,align:"center",width:150},
	        {name:'expiredEntering',hidden:false,sortable:false,width:150,formatter:expiredEnteringFormatter}
		],
		multiselect:true,
		scrollrow:true,
		showColModelButton:true,
		viewrecords: true,
		loadComplete: afterLoadFun,
		onSelectRow: showAttachFun,
		ondblClickRow: viewFun,
		gridComplete:afterGridComplete
	});
	$("#globalDailyYear").click(function(){
		$("#globalOrgBox").createDialog({
			url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id=${param.orgId}',
			width:550,
			height:'auto',
			draggable:false,
			title:'辖区选择',
			buttons: {
				"确定" : function(event){
					var selectInput=$("#orgSelectInput");
					var orgLevelInternalId=selectInput.attr("orgLevelInternalId");
					var text=selectInput.attr("text");
					$("#currentOrgId").attr({
						"orgLevelInternalId":orgLevelInternalId,
						text:text,
						value:selectInput.val()
					});
					
					if(USER_ORG_ID==selectInput.val()){
						$.messageBox({message:"请选择一个下辖的层级！",level:'warn'});
						return false;
					}
					var curMenu=$("#accordion a.cur");
					var baseUrl=curMenu.attr("baseUrl");
					var leaderUrl=curMenu.attr("leaderUrl");
					baseUrl = baseUrl+"?currentOrganization.id="+selectInput.val();
					<%-- 此函数在baseJs.jsp 中 --%>
					baseLoad(curMenu,baseUrl,leaderUrl);
				},
				"取消" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	if($("#currentOrgId").attr("text")){
		$("#contentDiv").find("#globalOrgBtnMod").find("span").text($("#currentOrgId").attr("text"));
	}
	
	<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
	$("#add").click(function(event){
		addFun();
	});
	$("#update").click(function(event){
		updateFun();
	});
	$("#delete").click(function(event){
		deleteFun();
	});
	</s:if>
	$("#search").click(function(){
		searchFun();
	});
	$("#view").click(function(event){
		var selectedIds=$("#workingRecordList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var rowData=  $("#workingRecordList").getRowData(selectedIds);
		
		$("#workingRecordDialog").createDialog({
			width:900,
			height:480,
			title:'查看工作台账',
			modal : true,
			url:"${path}/workingRecord/workingRecordManage/dispatchOperateByEncrypt.action?mode=view&dailyDirectory.id="+dailyDirectoryId+"&workingRecord.id="+rowData.encryptId,
			buttons :{
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#reload").click(function(){
		$("#searchText").val('请输入名称');
		loadListAtFirst();
	})
	$("#fastSearch").click(function(){
		fastSearchFun();
	});
	$("#refreshsearchText").click(function(){
		$("#searchText").val('请输入名称');
	});
	$("#synchronize").click(function(){
		synchronizeFun();
	});
	function loadListAtFirst(){
		var	postData = {
			"organization.id":_currentOrgId,
	    	"dailyDirectoryId":dailyDirectoryId,
	    	"displayLevel":$("#displayLevel").val()
		}
		$("#workingRecordList").setGridParam({
			url:"${path}/workingRecord/workingRecordManage/workingRecordList.action",
			datatype: "json",
			page:1
		});
		$("#workingRecordList").setPostData(postData);
		$("#workingRecordList").trigger("reloadGrid");
	}
	function loadList(){
		$("#workingRecordList").trigger("reloadGrid");
	}
	function formatterAttachFile(el,options,rowData){
		if(rowData.dailyLogAttachFile.length>0){
			return "<div style='clear:both' align='center'><a href='javascript:void(0);' id='workingRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' workingRecordId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
	function addFun(){
		var selectedNode = $.getSelectedNode(accountTree);
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"请选择底层目录!",level : "warn"});
			return ;
		}else{
			$("#workingRecordDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'新增台账',
				modal : true,
				url:"${path}/workingRecord/workingRecordManage/dispatchOperate.action?mode=add&dialogName=workingRecordDialog&dailyDirectory.id="+dailyDirectoryId,
				buttons :{
					"保存" : function(){
						$("#workingRecordForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
	}
	function updateFun(){
		var selectedIds=$("#workingRecordList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		var rowData=  $("#workingRecordList").getRowData(selectedIds);
		$("#workingRecordDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'修改台账信息',
			url:"${path}/workingRecord/workingRecordManage/dispatchOperateByEncrypt.action?mode=edit&dialogName=workingRecordDialog&workingRecord.id="+rowData.encryptId,
			buttons : {
				"保存" : function(){
					$("#workingRecordForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function viewFun(selectedId){
		var rowData=  $("#workingRecordList").getRowData(selectedId);
		$("#workingRecordDialog").createDialog({
			width:900,
			height:480,
			title:'查看工作台账',
			modal : true,
			url:"${path}/workingRecord/workingRecordManage/dispatchOperateByEncrypt.action?mode=view&dailyDirectory.id="+dailyDirectoryId+"&workingRecord.id="+rowData.encryptId,
			buttons :{
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function deleteFun(){
		var selectedIds=$("#workingRecordList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		$.confirm({
			title:"确认删除",
			message:'台账删除后将无法恢复,确认删除吗?',
			width: 400,
			okFunc: function(){
				deleteWorkingRecord();
			}
		});
	}
	function deleteWorkingRecord(){
		var selectedIds=$("#workingRecordList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		var allValue=deleteOperatorByEncrypt('workingRecord',selectedIds,'encryptId');
		$.ajax({
			url:'${path}/workingRecord/workingRecordManage/deleteWorkingRecordByIdsByEncrypt.action',
			type:'post',
			data:{
				'ids':allValue
				},
			success:function(data){
				if(data){
					$.messageBox({ message:"成功删除台账!"});
					loadList();
					return;
				}else{
					$.messageBox({ message:"考核评估已参考其中的台账，请勿删除！",level: "warn"});
				}
	        }
		});
	}
	function searchFun(){
		$("#workingRecordDialog").createDialog({
			width:500,
			height:300,
			title:'查询工作台账',
			url:"${path}/workingRecord/workingRecordManage/dispatchOperate.action?mode=search&dialogName=workingRecordDialog",
			buttons : {
				"查询" : function(){
					searchWorkingRecord();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function searchWorkingRecord(){
		var postData = {
			"searchWorkingRecordVo.orgId":_currentOrgId,
			"searchWorkingRecordVo.dailyDirectoryId":dailyDirectoryId,
			"searchWorkingRecordVo.displayLevel":$("#displayLevel").val(),
			"searchWorkingRecordVo.name":$("#searchVoName").val(),
			"searchWorkingRecordVo.workingRecordType":$("#workingRecordType").val(),
			"searchWorkingRecordVo.createDateBegin":$("#createDateBegin").val(),
			"searchWorkingRecordVo.createDateEnd":$("#createDateEnd").val(),
			"searchWorkingRecordVo.hasAttach":$("#hasAttach").val(),
			"searchWorkingRecordVo.proceedSite":$("#workingRecordProceedSite").val(),
			"searchWorkingRecordVo.subject":$("#workingRecordSubject").val(),
			"searchWorkingRecordVo.workingUnit":$("#workingRecordWorkingUnit").val()
		}
	    $("#workingRecordList").setGridParam({
			url:"${path}/workingRecord/workingRecordManage/findWorkingRecordsByQueryCondition.action",
			datatype: "json",
			page:1
		});
		$("#workingRecordList").setPostData(postData);
		$("#workingRecordList").trigger("reloadGrid");
	}
	function fastSearchFun(){
		var condition = $("#searchText").val();
		if(condition == '请输入名称') {
			return;
		}
		var	postData = {
			"searchWorkingRecordVo.orgId":_currentOrgId,
			"searchWorkingRecordVo.dailyDirectoryId":dailyDirectoryId,
			"searchWorkingRecordVo.displayLevel":$("#displayLevel").val(),
			"searchWorkingRecordVo.hasAttach":-1,
			"searchWorkingRecordVo.name":condition
		}
		$("#workingRecordList").setGridParam({
			url:"${path}/workingRecord/workingRecordManage/findWorkingRecordsByQueryCondition.action",
			datatype: "json",
			page:1
		});
		$("#workingRecordList").setPostData(postData);
		$("#workingRecordList").trigger("reloadGrid");
	}
	function synchronizeFun(){
		var selectedIds=$("#workingRecordList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==0){
			$.messageBox({message:"请选择要同步的台账!",level : "warn"});
			return ;
		}
		var allValue=deleteOperatorByEncrypt('workingRecord',selectedIds,'encryptId');
		$("#fileSharingDailog").createDialog({
			width:650,
			height:500,
			title:'同步资料库',
			url:'${path}/workingRecord/workingRecordManage/dispatchOperateByEncrypt.action?mode=sharing&ids='+allValue+"&identification=workingRecord",
			buttons :{
				"保存" : function(){
					loadList();
					$("#fileSharing-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function afterLoadFun(){
		showAttachFun();
		changeColorFun();
	}
	function showAttachFun(){
		$(".tip-yellowsimple").remove();
		$.each($(".popUpMore"), function(i, n){
			$.ajax({
				url:"${path}/workingRecord/workingRecordManage/getWorkingRecordById.action?workingRecord.id="+$(n).attr("workingRecordId"),
				success:function(data){
					var popMoreData = [];
					if(data != null){
						for(var j = 0; j < data.dailyLogAttachFile.length; j++){
							popMoreData[j]={
								id:data.dailyLogAttachFile[j].id, 
								url:'${path}/workingRecord/workingRecordManage/downloadDailyLogAttachFile.action?dailyLogAttachFile.id='+data.dailyLogAttachFile[j].id, 
								text:data.dailyLogAttachFile[j].fileName,
								clickFun:function(){}
							};
						}
					}
					$(n).popUpMore({data: popMoreData});
				}
			});
		});
	}
	function changeColorFun(){
		<s:if test="#parameters.menuType[0].equals('myWorkingRecordManagement')">
		var records = $("#workingRecordList").getGridParam("records");
		if(accountTree.getSelectionModel().getSelectedNode().attributes.require==1 && accountTree.getSelectionModel().getSelectedNode().leaf){
			if(records>0){
				$(accountTree.getSelectionModel().getSelectedNode().ui.textNode).find("span").css("color","black");
			}else if(records==0){
				$(accountTree.getSelectionModel().getSelectedNode().ui.textNode).find("span").css("color","red");
			}
		}
		</s:if>
	}
	function expiredEnteringFormatter(cellvalue, options, rowObject){
		if(cellvalue=='1'){
			globalFormatterArrary.push(options.rowId);
			return "过期录入";
		}else{
			return "有效录入";
		}
	}
	function afterGridComplete(){
		var rowId;
		for(var i=0;i<globalFormatterArrary.length;i++){
		  rowId=globalFormatterArrary[i];
		 $("#workingRecordList>tbody>tr[id='"+rowId+"']").css('color','#f60')
		}
		globalFormatterArrary=new Array();
	}
	/*
	function showDailyLogAttachFile(){
		$.each($(".popUpMore"), function(i, n){
			$.ajax({
				url:"${path}/workingRecord/workingRecordManage/getWorkingRecordById.action?workingRecord.id="+$(n).attr("workingRecordId"),
				success:function(workingRecord){
					var popMoreData = [];
					if(workingRecord != null){
						for(var j = 0; j < workingRecord.dailyLogAttachFile.length; j++){
							popMoreData[j]={
								id:workingRecord.dailyLogAttachFile[j].id, 
								url:'${path}/workingRecord/workingRecordManage/downloadDailyLogAttachFile.action?dailyLogAttachFile.id='+workingRecord.dailyLogAttachFile[j].id, 
								text:workingRecord.dailyLogAttachFile[j].fileName,
								clickFun:function(){}
							};
						}
					}
					//console.log(popMoreData[0]);
					$(n).popUpMore({data: popMoreData});
				}
			});
		});
	}
	*/
});
</script>