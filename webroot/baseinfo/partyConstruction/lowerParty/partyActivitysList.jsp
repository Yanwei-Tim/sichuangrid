<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<pop:JugePermissionTag ename="viewLowerPartyOrgActivitys">
			<a id="viewLowerActivity" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="getLowerPartyOrgActivitys">
			<a id="searchLowerActivity" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadLowerActivitys"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="downloadLowerPartyOrgActivitys">
			<a id="exportLowerAvtivitys" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<!--
		<pop:JugePermissionTag ename="printPartyActivityInfos">
			<a id="printActivity" href="javascript:void(0)"><span>打印列表</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="printdetailsPartyActivityInfos">
			<a id="printDetailActivity" href="javascript:void(0)"><span>打印详情</span></a>
		</pop:JugePermissionTag>
		 -->

			<div style="position:absolute;right:10px;top:3px;white-space:nowrap;">
				<select id="lowerActivityYear" name="activityYear" class="S_object">
						<pop:SysBeginUseYearOptionsTag/>
				</select>
			</div>
	</div>

	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyActivityInfoList"></table>
		<div id="partyActivityInfoListPager"></div>
	</div>
	<div id="partyActivityInfoDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="lowerPartyOrgActivityManagement">
		<span id="title"><s:property value="#request.name" />
		</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
	var dialogWidth=800;
	var dialogHeight=600;
	var isgridBol = false;
	// ------------------------------ party Activity search list and page layout start  ------------------------------
	$(document).ready(function() {
		$("#partyActivityInfoList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$(".btnbanner").height()-$("#nav").height()-150,
			colModel:[
				{name:"id", index:"id", hidden:true,frozen : true},
				{name:"activitySubject", index:"activitySubject",label:"活动主题",width:200, frozen : true},
				{name:"activityAddr", index:"activityAddr", label:"活动地点", width:200, align:"center"},
				{name:"activityDate", index:'activityDate', label:'活动时间', width:100, frozen : true},
				{name:"organizers", index:'organizers', label:'组织者', width:80, frozen : true},
				{name:"participants", index:'participants', label:'参与者', width:80, frozen : true},
				{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, hidden:true},
				{name:"activeContent", index:'activeContent', label:'活动内容', width:80 ,hidden:true},
				{name:"activityAttachFile", formatter:formatterAttachFile, label:'附件', width:150, frozen : true}
		  	],
		  	multiselect:true,
		  	onSelectAll:toggleButtonState,
	    	loadComplete: afterLoad,
			<pop:JugePermissionTag ename="viewLowerPartyOrgActivitys">
	        	ondblClickRow: dbClickViewPartyActivityInfo,
			</pop:JugePermissionTag>
			onSelectRow:toggleButtonState
		});

	  	jQuery("#partyActivityInfoList").jqGrid('setFrozenColumns');

		$("#viewLowerActivity").click(function(event){
			var allValue = getSelectedIds();
			if(allValue.length >1 || allValue.length==0){
				 return;
			}
			dbClickViewPartyActivityInfo(allValue);
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
		
		//刷新
		$("#reloadLowerActivitys").click(function(event){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		function getSelectedIds() {
			var selectedIds = $("#partyActivityInfoList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}

		//显示详细信息的方法
		function dbClickViewPartyActivityInfo(rowid){
			if(rowid==null){
		 		return;
			}
			var ActivityInfo =  $("#partyActivityInfoList").getRowData(rowid);
			$("#partyActivityInfoDialog").createDialog({
				width: 650,
				height: 450,
				title:'查看党组织活动记录',
				url:'${path}/baseinfo/partyOrgActivityManage/dispatchOperate.action?mode=view&population.id='+ActivityInfo.id,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});

		}

		//导出
		$("#exportLowerAvtivitys").click(function(event) {
			if($("#partyActivityInfoList").getGridParam("records")>0) {
				$("#partyActivityInfoDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出下辖党组织活动记录",
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'partyActivityInfoList',
						orgId:getCurrentOrgId(),
						downloadUrl:'${path}/baseinfo/lowerPartyActivityController/downloadPartyOrgActivity.action'
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

		//查询按钮
		$("#searchLowerActivity").click(function(event) {
			$("#partyActivityInfoDialog").createDialog({
				width:650,
				height:265,
				title:'党组织活动记录查询',
				url:'${path}/baseinfo/lowerPartyActivityController/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
			   			searchLowerPartyActivity();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		//查询方法
		function searchLowerPartyActivity() {
			$("#partyActivityInfoList").setGridParam({
				url:"${path}/baseinfo/lowerPartyActivityController/searchPartyActivity.action",
				datatype: "json",
				page:1
			});
			$("#partyActivityInfoList").setPostData($.extend({"searchPartyOrgActivityVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchLowerPartyActivityForm").serializeObject()));
			$("#partyActivityInfoList").trigger("reloadGrid");
		}

		$("#lowerActivityYear").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});


		function getSelectedIdLast() {
			var selectedId;
			var selectedIds = $("#partyActivityInfoList").jqGrid("getGridParam", "selarrrow");
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
		var activityYear =  $("#lowerActivityYear").val();
		$("#partyActivityInfoList").setGridParam({
			url:"${path}/baseinfo/lowerPartyActivityController/getlowerActivitys.action",
			datatype: "json",
			page:1
		});
		$("#partyActivityInfoList").setPostData({
			"orgId":orgId,
			"searchPartyOrgActivityVo.yearActivity":activityYear
	   	});
		$("#partyActivityInfoList").trigger("reloadGrid");
	}

	// page layout
	function toggleButtonState() {
	  	var selectedIds=$("#partyActivityInfoList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;

	  	setOtherButtonEnabled(selectedRowCount==1);

	}
	function toggleEmphasisButtonEnabled(enabled) {
		if (enabled) {
			$("#reEmphasise").buttonEnable();
		} else {
			$("#cancelEmphasise").buttonEnable();
		}
	}
	function selectedRowIsnotEmphasis(partyActivityInfo) {
		return 	partyActivityInfo.isEmphasis==1;
	}

	function afterLoad(){
		disableButtons();
		checkExport();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#partyActivityInfoList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#partyActivityInfoList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function disableButtons(){
	  	setOtherButtonEnabled(false);

	}

	function setOtherButtonEnabled(enabled) {
		if (enabled) {
			$("#viewLowerActivity").buttonEnable();
		} else {
			$("#viewLowerActivity").buttonDisable();
		}
	}


	function checkExport() {
		if($("#partyActivityInfoList").getGridParam("records") > 0
				|| $("#partyActivityInfoList").getGridParam("selrow") != null) {
			$("#exportLowerAvtivitys").buttonEnable();
		} else {
			$("#exportLowerAvtivitys").buttonDisable();

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
	// ------------------------------ party Activity info search list and page layout end  ------------------------------
</script>
