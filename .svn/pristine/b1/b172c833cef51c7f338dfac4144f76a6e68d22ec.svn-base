<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<input type="hidden" name="orgIds" id="orgIdForList" value="${partyOrgId}" class="form-txt" />
		<ul id="links"  style="display:none" />
		<pop:JugePermissionTag ename="addPartyOrgActivitys">
			<a id="addOrgActivity" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePartyOrgActivitys">
			<a id="updateOrgActivity" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPartyOrgActivitys">
			<a id="viewOrgActivity" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePartyOrgActivitys">
			<a id="deleteOrgActivity" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="getPartyOrgActivitys">
			<a id="searchOrgActivity" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadOrgActivity"  href="javascript:void(0)"><span>刷新</span></a>
		<%-- <pop:JugePermissionTag ename="importPartyOrgActivitys">
			<a id="importOrgActivity" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag> --%>
		<pop:JugePermissionTag ename="downloadPartyOrgActivitys">
			<a id="exportOrgActivity" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>

		<!--<pop:JugePermissionTag ename="anewPartyMemberInfos">
			<a id="reEmphasiseOrgActivity" href="javascript:void(0)"><span>取消注销</span></a>
		</pop:JugePermissionTag>-->
			<div style="position:absolute;right:10px;top:3px;white-space:nowrap;">
				<select id="yearActivity" name="" class="S_object">
					<pop:SysBeginUseYearOptionsTag/>
				</select>
			</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyOrgActivityList"> </table>
		<div id="partyOrgActivityListPager"></div>
	</div>
	<div id="partyOrgActivityDialog"></div>
	<div id="noticeDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="partyOrgActivityManagement">
		<span id="title"><s:property value="#request.name"/></span>
	</pop:JugePermissionTag>
</div>

<script type="text/javascript">
	var dialogWidth=800;
	var dialogHeight=600;
	var isgridBol = false;

	// ------------------------------ party org activity search list and page layout start  ------------------------------
	function showDailyLogAttachFile(){
			$.each($(".popUpMore"), function(i, n){
				$.ajax({
					url:"${path}/baseinfo/partyOrgActivityManage/getPartyOrgActivityById.action?population.id="+$(n).attr("orgActivityId"),
					success:function(orgActivity){
						var popMoreData = [];
						for(var j = 0; j < orgActivity.activityAttachFile.length; j++){
							popMoreData[j]={id:orgActivity.activityAttachFile[j].id, url:'${path}/baseinfo/partyOrgActivityManage/downloadPartyOrgActivityFile.action?partyOrgActivityFile.id='+orgActivity.activityAttachFile[j].id, text:orgActivity.activityAttachFile[j].fileName,clickFun:function(){}};
						}
						$(n).popUpMore({data: popMoreData});
					}
				});
			});
		}
	$(document).ready(function() {
		$("#partyOrgActivityList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$("#nav").height()-283,
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen : true},
	  			{name:"activitySubject", index:"activitySubject",label:"活动主题",width:200, frozen : true},
	  			{name:"activityAddr", index:"activityAddr", label:"活动地点", width:200, align:"center"},
	  			{name:"activityDate", index:'activityDate', label:'活动时间', width:100, frozen : true},
	  		  	{name:"organizers", index:'organizers', label:'组织者', width:80, frozen : true},
	  		  	{name:"participants", index:'participants', label:'参与者', width:80, frozen : true},
	  			{name:"activityAttachFile", formatter:formatterAttachFile, label:'附件', width:150, frozen : true},
	  		  	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, hidden:true},
	  		  	{name:"activeContent", index:'activeContent', label:'活动内容', width:80 ,hidden:true}
		  	],
		  	multiselect:true,
		  	onSelectAll:toggleButtonState,
	    	loadComplete: function(){afterLoad();showDailyLogAttachFile();},
			<pop:JugePermissionTag ename="viewSpecialCareGroups">
	        	ondblClickRow: doubleClickTable,
			</pop:JugePermissionTag>
			onSelectRow:toggleButtonState
		});
	  	jQuery("#partyOrgActivityList").jqGrid('setFrozenColumns');
		$("#yearActivity").change(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		$("#viewOrgActivity").click(function() {
			doubleClickTable();
		});
		$("#reloadOrgActivity").click(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
			onOrgChanged(getCurrentOrgId(),isGrid());
			checkExport();
		}

		// ------------------------------ function processing start -------------------------------------------

		function formatterAttachFile(el,options,rowData){
			if(rowData.activityAttachFile.length>0){
				return "<div style='clear:both' align='center'><a href='javascript:;' id='orgActivity_"+rowData.id+"' style='color:#666666' class='popUpMore' orgActivityId='"+rowData.id+"' >附件>></a></div>";
			}
			return "";
		}

		$("#addOrgActivity").click(function() {
			if("disabled"==$("#addOrgActivity").attr("disabled")) {
				return;
			}
			$("#links").load("${path}/baseinfo/partyMemberInfoManage/findPartyOrgInfoByOrgId.action", {orgId: getCurrentOrgId()}, function(data){
				if(("null" == data || null == data) && getCurrentOrgId() == USER_ORG_ID) {
			 		$.notice({level:"warn", message:"请先编辑党组织信息"});
			 	} else {
					if (getCurrentOrgId() == null || getCurrentOrgId() == "") {
						$.notice({level:"warn", message:"请先选择一个部门"});
					} else {
						$("#partyOrgActivityDialog").createDialog({
							width:650,
							height:450,
							title:'新增党组织活动记录',
							url:'${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=add&orgId='+getCurrentOrgId(),
							buttons: {
						   		"保存" : function(event){
						   			$("#addPartyOrgActivityForm").submit();
					   			},
						   		"关闭" : function(){
						        	$(this).dialog("close");
						   		}
							}
						});
					}
			 	}
			});
		});

		$("#updateOrgActivity").click(function() {
			if("disabled"==$("#updateOrgActivity").attr("disabled")) {
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId == null) {
				 return;
			}
			$("#partyOrgActivityDialog").createDialog({
				width:650,
				height:450,
				title:'修改党组织活动记录',
				url:'${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=edit&orgId='+getCurrentOrgId()+'&population.id='+selectedId,
				buttons: {
			   		"保存" : function(event){
			   			$("#addPartyOrgActivityForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		$("#deleteOrgActivity").click(function() {
			if("disabled"==$("#deleteOrgActivity").attr("disabled")) {
				return;
			}
			var allValue = getSelectedIds();
			if(allValue.length ==0) {
				 return;
			}
			var str = "确定要删除吗?一经删除将无法恢复";
			$.confirm({
					title:"确认删除",
					message:str,
					okFunc: function() {
						$.ajax({
							url:"${path}/baseinfo/partyOrgActivityManage/deletePartyOrgActivity.action?deleteIds="+allValue,
							success:function(data){
								$("#partyOrgActivityList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除该党组织活动记录!"});
							    disableButtons();
							    checkExport();
						}
					});
				}
			});
		});
		function getSelectedIds() {
			var selectedIds = $("#partyOrgActivityList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}

		function doubleClickTable(){
			var selectedId = getSelectedIdLast();
			if(selectedId == null) {
				 return;
			}
			var partyOrgActivity=$("#partyOrgActivityList").getRowData(selectedId);
			$("#partyOrgActivityDialog").createDialog({
				width:650,
				height:450,
				title:"查看党组织活动记录",
				url:"${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=view&population.id="+selectedId,
				buttons: {
				   "关闭" : function() {
				        $(this).dialog("close");
				   }
				}
			});
		}

		$("#importOrgActivity").click(function(event) {
			if("disabled"==$("#importOrgActivity").attr("disabled")) {
				return;
			}
			$("#links").load("${path}/baseinfo/partyMemberInfoManage/findPartyOrgInfoByOrgId.action", {orgId: getCurrentOrgId()}, function(data){
				if(("null" == data || null == data) && getCurrentOrgId() == USER_ORG_ID) {
			 		$.notice({level:"warn", message:"请先编辑党组织信息"});
			 	} else {
					$("#noticeDialog").createDialog({
						width: 400,
						height: 230,
						title:"数据导入",
						url:"${path}/common/import.jsp?dataType=partyOrgActivity&dialog=noticeDialog&startRow=6&templates=PARTYORGACTIVITY",
						buttons:{
							"导入" : function(event) {
								$("#mForm").submit();
							},
						   	"关闭" : function() {
						    	$(this).dialog("close");
						   	}
						},
						shouldEmptyHtml:false
					});
			 	}
			});
		});

		$("#exportOrgActivity").click(function(event) {
			if("disabled"==$("#exportOrgActivity").attr("disabled")) {
				return;
			}
			if($("#partyOrgActivityList").getGridParam("records")>0) {
				$("#partyOrgActivityDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出党组织活动记录",
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'partyOrgActivityList',
						orgId:getCurrentOrgId(),
						downloadUrl:'${path}/baseinfo/partyOrgActivityManage/downloadPartyOrgActivity.action'
					},
					buttons: {
			   			"导出" : function(event){
			   				exceldownloadSubmitForm();
			        		$(this).dialog("close");
		   				},
			   			"关闭" : function(){
			        		$(this).dialog("close");
			   			}
					},
					shouldEmptyHtml:false
				});
			} else {

			}
		});

		function print(rowId) {
			$("#partyOrgActivityDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"打印党组织活动记录",
				url:"${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=print&partyOrgActivity.id="+rowId,
				buttons: {
					  "打印" : function(){
					$("#partyOrgActivityPrint").printArea();
		        	$(this).dialog("close");
			  	 },
				   "关闭" : function() {
				        $(this).dialog("close");
				   }
				}
			});
		}

		$("#searchOrgActivity").click(function(event) {
			$("#partyOrgActivityDialog").createDialog({
				width:650,
				height:280,
				title:'党组织活动记录查询-请输入查询条件',
				url:'${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
			   			searchPartyOrgActivity();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});

		});
		function searchPartyOrgActivity() {
			$("#partyOrgActivityList").setGridParam({
				url:"${path}/baseinfo/partyOrgActivityManage/searchPartyOrgActivity.action",
				datatype: "json",
				page:1
			});
			$("#partyOrgActivityList").setPostData($.extend({"searchPartyOrgActivityVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchPartyOrgActivityForm").serializeObject()));
			$("#partyOrgActivityList").trigger("reloadGrid");
		}

		$("#reloadOrgActivity").click(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		function getSelectedIdLast() {
			var selectedId;
			var selectedIds = $("#partyOrgActivityList").jqGrid("getGridParam", "selarrrow");
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
			}
			return selectedId;
		}
		// ------------------------------ function processing end -------------------------------------------
	})

	// serarch list
	function onOrgChanged(orgId,isgrid){
		onOrgChangedPartyOrgInfo(orgId,isgrid);
		//if(isgrid) {
			//$("#addOrgActivity").buttonDisable();
		//	return;
		//}
		isgridBol = isgrid;
		$("#partyOrgActivityList").setGridParam({
			url:"${path}/baseinfo/partyOrgActivityManage/partyOrgActivityList.action",
			datatype: "json",
			page:1
		});
		$("#partyOrgActivityList").setPostData({
			"orgId":orgId,
	    	"searchPartyOrgActivityVo.yearActivity":$("#yearActivity").val()
	   	});
		$("#partyOrgActivityList").trigger("reloadGrid");
		procButton(isgrid);
	}

	// page layout
	function toggleButtonState() {
	  	var selectedIds=$("#partyOrgActivityList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setDeleteButtonEnabled(selectedRowCount>0);
	  	setOtherButtonEnabled(selectedRowCount==1);
	  	if (selectedRowCount==1){
		  	toggleEmphasisButtonEnabled(selectedRowIsnotEmphasis($("#partyOrgActivityList").getRowData(selectedIds[0])));
		} else {
			disableEmphasisButton();
		}
	}
	function toggleEmphasisButtonEnabled(enabled) {
		if (enabled) {
			//$("#reEmphasiseOrgActivity").buttonEnable();
		} else {
			$("#cancelEmphasise").buttonEnable();
		}
	}
	function selectedRowIsnotEmphasis(partyOrgActivity) {
		return 	partyOrgActivity.isEmphasis==1;
	}

	function afterLoad(){
		isEmphasisFormatter();
		disableButtons();
		checkExport();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#partyOrgActivityList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#partyOrgActivityList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function disableButtons(){
	  	setDeleteButtonEnabled(false);
	  	setOtherButtonEnabled(false);
	  	disableEmphasisButton();
	}
	function setDeleteButtonEnabled(enabled) {
		if (enabled && getCurrentOrgId() == USER_ORG_ID) {
			$("#deleteOrgActivity").buttonEnable();
		}else{
			$("#deleteOrgActivity").buttonDisable();
		}
	}
	function setOtherButtonEnabled(enabled) {
		if (enabled) {
			if(getCurrentOrgId() == USER_ORG_ID) {
				$("#updateOrgActivity").buttonEnable();
			}
			$("#viewOrgActivity").buttonEnable();
		} else {
			$("#updateOrgActivity").buttonDisable();
			$("#viewOrgActivity").buttonDisable();
		}
	}
	function disableEmphasisButton() {
		$("#cancelEmphasise").buttonDisable();
		//$("#reEmphasiseOrgActivity").buttonDisable();
	}

	function checkExport() {
		if(($("#partyOrgActivityList").getGridParam("records") > 0
				|| $("#partyOrgActivityList").getGridParam("selrow") != null) && getCurrentOrgId() == USER_ORG_ID) {
			$("#exportOrgActivity").buttonEnable();
		} else {
			$("#exportOrgActivity").buttonDisable();

		}
	}

	// table list formatter
	function nameFont(el,options,rowData){
		if(rowData.death){
			return "<font color='red'>"+rowData.name+"</font>";
		}
		if(rowData.isEmphasis==1){
			return "<font color='#778899'>"+rowData.name+"</font>";
		}
		return "<font color='#000'>"+rowData.name+"</font>";
	}

	function idCardNoFont(el,options,rowData){
		if(rowData.isEmphasis==1) {
			return "<font color='#778899'>"+rowData.idCardNo+"</font>";
		}
		return "<font color='#000'>"+rowData.idCardNo+"</font>";
	}

	function procButton(isgrid) {
		if(isgrid || (getCurrentOrgId() != USER_ORG_ID)) {
			//$("#addOrgActivity").buttonDisable();
			$("#updateOrgActivity").buttonDisable();
			$("#deleteOrgActivity").buttonDisable();
			$("#exportOrgActivity").buttonDisable();
			$("#printOrgActivity").buttonDisable();
			$("#printOrgActivityDetail").buttonDisable();
		} else {
			$("#addOrgActivity").buttonEnable();
			$("#updateOrgActivity").buttonEnable();
			$("#deleteOrgActivity").buttonEnable();
			$("#exportOrgActivity").buttonEnable();
			$("#printOrgActivity").buttonEnable();
			$("#printOrgActivityDetail").buttonEnable();
		}
	}
	// ------------------------------ party org activity search list and page layout end  ------------------------------
</script>