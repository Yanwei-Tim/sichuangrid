<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	    <input type="text" value="请输入身份证号码或姓名" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入身份证号码或姓名':this.value;" onfocus="value=(this.value=='请输入身份证号码或姓名')?'':this.value;"/>
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
</div>
<div class="content" >
	<input type="hidden" name="orgIds" id="orgIdForList" value="${partyOrgId}" class="form-txt" />
	<ul id="links"  style="display:none" />
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<pop:JugePermissionTag ename="addPartyMemberInfos">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePartyMemberInfos">
			<a id="updateMember" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPartyMemberInfos">
			<a id="viewMember" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePartyMemberInfos">
			<a id="deleteMember" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="getPartyMemberInfos">
			<a id="searchMember" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadMember"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importPartyMemberInfos">
			<a id="importMember" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downloadPartyMemberInfos">
			<a id="exportMember" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>

		<pop:JugePermissionTag ename="abolishPartyMemberInfos">
			<a id="cancelEmphasiseMember" href="javascript:void(0)"><span>注销</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="anewPartyMemberInfos">
			<a id="reEmphasise" href="javascript:void(0)"><span>取消注销</span></a>
		</pop:JugePermissionTag>
			<div style="white-space:nowrap;position:absolute;right:10px;top:0px;">
				<select id="isLock" name="" class="S_object">
						<option value="-1">全部</option>
	 					<option value="0" selected="selected">正常</option>
	 					<option value="1">已注销</option>
				</select>
			</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyMemberInfoList"> </table>
		<div id="partyMemberInfoListPager"></div>
	</div>
	<div id="partyMemberInfoDialog"></div>
	<div id="noticeDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="partyManagement">
		<span id="title"><s:property value="#request.name"/></span>
	</pop:JugePermissionTag>
</div>

<script type="text/javascript">
	var dialogWidth=800;
	var dialogHeight=600;
	var isgridBol = false;
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
	<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
	<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
	<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
	<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
	<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
	function isFlowPartyCard(el, options, rowData){
		if(1==rowData.isFlowPartyCard){
			return "是";
		}else{
			return "否";
		}
	}

	// ------------------------------ party member search list and page layout start  ------------------------------
	$(document).ready(function() {
		$("#partyMemberInfoList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$(".btnbanner").height()-$("#nav").height()-265,
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen : true,sortable:false},
	  			{name:"name", index:"name",label:"姓名",formatter:nameFont,width:100, frozen : true,sortable:true},
	  			{name:"gender", index:"gender", label:"性别", width:60, formatter:genderFormatter,align:"center",sortable:true},
	  			{name:"idCardNo", index:"idCardNo", label:"身份证号码", width:150, formatter:idCardNoFont, frozen : true,sortable:true},
	  			{name:"currentAddress", index:"currentAddress", label:"常住地址", width:150, frozen : true,sortable:true},
	  			{name:"province", index:"province", label:"户籍地", width:70,sortable:false, frozen : true,sortable:true},
	  			{name:"joinPartyDate", index:'joinPartyDate', label:'入党日期', width:100, frozen : true,sortable:true},
	  		  	{name:"isFlowPartyCard", index:'isFlowPartyCard', label:'是否流动党员', formatter:isFlowPartyCard, width:110, frozen : true,sortable:true},

	  		  	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, hidden:true,sortable:false},
	  		  	{name:"usedName", index:'usedName', label:'曾用名', width:80 ,hidden:true,sortable:true},
				{name:"workUnit",label:"工作单位或就读学校", width:200,sortable:true,hidden:true},
				{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true,sortable:true},
				{name:"stature",label:"身高",width:100,sortable:true,hidden:true},
				{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:true,hidden:true},
				{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:100,sortable:true,hidden:true},
				{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true,sortable:true},
				{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:true,hidden:true},
				{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,sortable:true,hidden:true},
				{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:true,hidden:true},
				{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:true,hidden:true},
				{name:"otherAddress",label:"临时居所",width:100,sortable:true,hidden:true},
				{name:"telephone",label:"联系电话", width:100,sortable:true,hidden:true},
				{name:"mobileNumber",label:"联系手机", width:100,sortable:true,hidden:true},
				{name:"email",label:"电子邮件", width:100,sortable:true,hidden:true},
				{name:"birthday",label:"出生日期", width:100,hidden:true,sortable:true},
				{name:"remark",sortable:true,label:"备注",hidden:true,width:100},
				{name:'isEmphasis',sortable:true,hidden:true,hidedlg:true,width:80}
		  	],
		  	multiselect:true,
		  	onSelectAll:togglePartyMemberButtonState,
	    	loadComplete: afterLoad,
			<pop:JugePermissionTag ename="viewPartyMemberInfos">
	        	ondblClickRow: doubleClickPartyMemberTable,
			</pop:JugePermissionTag>
			onSelectRow:togglePartyMemberButtonState
		});
	  	jQuery("#partyMemberInfoList").jqGrid('setFrozenColumns');
	  	var gridTimer;
		$(window).resize(function(){
			clearTimeout(gridTimer);
			gridTimer=setTimeout(function(){
				var gridHeight=$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$(".btnbanner").height()-$("#nav").height()-250;
				$("#partyMemberInfoList").jqGrid('setGridHeight',gridHeight);
			},300)
		})

		$("#isLock").change(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		$("#viewMember").click(function() {
			var selectedId = getSelectedIdLast();
			doubleClickPartyMemberTable(selectedId);
		});
		$("#fastSearchButton").click(function() {
			search(getCurrentOrgId());
		});

		$("#addMember").click(function() {
			if("disabled"==$("#addMember").attr("disabled")) {
				return;
			}
			$("#links").load("${path}/baseinfo/partyMemberInfoManage/findPartyOrgInfoByOrgId.action",
					{orgId: getCurrentOrgId()}, function(data){
				if(("null" == data || null == data) && getCurrentOrgId() == USER_ORG_ID) {
			 		$.notice({level:"warn", message:"请先编辑党组织信息"});
			 	} else {
					if (getCurrentOrgId() == null || getCurrentOrgId() == "") {
						$.notice({level:"warn", message:"请先选择一个部门"});
					} else {
						$("#partyMemberInfoDialog").createActualPopulationDialog({
							width: dialogWidth,
							height: dialogHeight,
							title:"新增党员信息",
							model :"add",
							data:[
								{title:'基本信息',url:'/baseinfo/partyMemberInfoManage/dispatchOperate.action?mode=add&dailogName=partyMemberInfoDialog&orgId='+getCurrentOrgId(),buttons:{next:true}},
								{title:'组织信息',url:'/baseinfo/partyMemberInfoManage/dispatchPartyMemberInfoOrg.action?mode=add&dailogName=partyMemberInfoDialog&orgId='+getCurrentOrgId(),buttons:{prev:true,save:true,saveContinue:true}}
							]
						});
					}
			 	}
			});
		});

		$("#updateMember").click(function() {
			if("disabled"==$("#updateMember").attr("disabled")) {
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId == null) {
				 return;
			}
			var ent =  $("#partyMemberInfoList").getRowData(selectedId);
			if(ent.isEmphasis==1||ent.isEmphasis=='1'){
			 	$.messageBox({message:"该党员信息已经注销，不能修改!"});
				 return;
			}
			$("#partyMemberInfoDialog").createActualPopulationDialog({
				width: dialogWidth,
				height: dialogHeight,
				title:"修改党员信息",
				model :"edit",
				data:[
					{title:'基本信息',url:'/baseinfo/partyMemberInfoManage/dispatchOperate.action?population.id='+selectedId+'&mode=edit&dailogName=partyMemberInfoDialog&orgId='+getCurrentOrgId(),buttons:{next:true}},
					{title:'组织信息',url:'/baseinfo/partyMemberInfoManage/dispatchPartyMemberInfoOrg.action?population.id='+selectedId+'&mode=edit&dailogName=partyMemberInfoDialog&orgId='+getCurrentOrgId(),buttons:{prev:true,save:true}}
				]
			});
		});

		$("#deleteMember").click(function() {
			if("disabled"==$("#deleteMember").attr("disabled")) {
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
							url:"${path}/baseinfo/partyMemberInfoManage/deletePartyMemberInfo.action?deleteIds="+allValue,
							success:function(data){
								$("#partyMemberInfoList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除该党员信息!"});
							    disableButtonspartyMember();
							    checkExport();
						}
					});
				}
			});
		});

		$("#importMember").click(function(event) {
			if("disabled"==$("#importMember").attr("disabled")) {
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
						url:"${path}/common/import.jsp?dataType=partyMemberInfo&dialog=noticeDialog&startRow=6&templates=PARTYMEMBERINFO&listName=partyMemberInfoList",
						buttons:{
							"导入" : function(event) {
								$("#mForm").submit();
							},
						   	"关闭" : function() {
						    	$(this).dialog("close");
						   	}
						},
						shouldEmptyHtml:false,
						close:function(event) {

						}
					});
					//$("#partyMemberInfoList").trigger("reloadGrid");
			 	}
			});
		});

		$("#exportMember").click(function(event) {
			if("disabled"==$("#exportMember").attr("disabled")) {
				return;
			}
			if($("#partyMemberInfoList").getGridParam("records")>0) {
				$("#partyMemberInfoDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出党员信息",
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'partyMemberInfoList',
						orgId:getCurrentOrgId(),
						downloadUrl:'${path}/baseinfo/partyMemberInfoManage/downloadPartyMemberInfo.action'
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
		$("#cancelEmphasiseMember").click(function() {
			if("disabled"==$("#cancelEmphasiseMember").attr("disabled")) {
				return;
			}
			var selectedId =getSelectedIdLast();
			if(selectedId == null || selectedRowIsnotEmphasis($("#partyMemberInfoList").getRowData(selectedId))) {
				return;
			}
			$.ajax({
				url:"${path}/baseinfo/partyMemberInfoManage/emphasisePartyMemberInfo.action",
				data:{
					"partyMemberInfo.id":selectedId,
					"partyMemberInfo.isEmphasis":1
				},
				success:function(responseData) {
					if($("#isLock").val()=="-1") {
						//$("#partyMemberInfoList").setRowData(responseData.id,responseData);
						$("#"+responseData.id+"").css('color','#778899');
					} else {
						$("#partyMemberInfoList").delRowData(responseData.id);
						$("#partyMemberInfoList").resetSelection();
					}
					$("#partyMemberInfoList").trigger("reloadGrid");
					$.messageBox({message:"注销成功！"});
					togglePartyMemberButtonState();
					checkExport();
				}
			});
		});

		$("#reEmphasise").click(function() {
			var selectedId = getSelectedIdLast();
			if(selectedId == null || !selectedRowIsnotEmphasis($("#partyMemberInfoList").getRowData(selectedId))) {
				return;
			}
			$.ajax({
				url:"${path}/baseinfo/partyMemberInfoManage/emphasisePartyMemberInfo.action",
				data:{
					"partyMemberInfo.id":selectedId,
					"partyMemberInfo.isEmphasis":0
				},
				success:function(responseData) {
					if($("#isLock").val()=="-1") {
						//$("#partyMemberInfoList").setRowData(responseData.id,responseData);
						$("#"+responseData.id+"").css('color','black');
					} else {
						$("#partyMemberInfoList").delRowData(responseData.id);
						$("#partyMemberInfoList").resetSelection();
					}
					$("#partyMemberInfoList").trigger("reloadGrid");
					$.messageBox({message:"取消注销成功！"});
					togglePartyMemberButtonState();
					checkExport();
				}
			});
		});

		$("#searchMember").click(function(event) {
			$("#partyMemberInfoDialog").createDialog({
				width:650,
				height:500,
				title:'党员查询-请输入查询条件',
				url:'${path}/baseinfo/partyMemberInfoManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
			   			searchPartyMemberInfo();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		$("#reloadMember").click(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});


		if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
			onOrgChanged(getCurrentOrgId(),isGrid());
			checkExport();
		}

		function search(orgId) {
			var conditions = $("#searchText").val();
			if(conditions == '请输入身份证号码或姓名') return;
			$("#partyMemberInfoList").setGridParam({
				url:"${path}/baseinfo/partyMemberInfoManage/searchPartyMemberInfo.action",
				datatype: "json",
				page:1
			});
			
				$("#partyMemberInfoList").setPostData({
					"orgId":orgId,
					"searchPartyMemberInfoVo.isEmphasis":$("#isLock").val(),
					"searchPartyMemberInfoVo.fastSearchKeyWords":conditions
				});
			
			$("#partyMemberInfoList").trigger("reloadGrid");
		 }


		function getSelectedIds() {
			var selectedIds = $("#partyMemberInfoList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}

		function doubleClickPartyMemberTable(selectedId){
			if(selectedId == null) {
				 return;
			}
			var partyMemberInfo=$("#partyMemberInfoList").getRowData(selectedId);
			$("#partyMemberInfoDialog").createDialog({
				width:dialogWidth,
				height:580,
				title:"查看党员信息",
				url:"${path}/baseinfo/partyMemberInfoManage/dispatchOperate.action?mode=view&population.id="+selectedId,
				buttons: {
				   "关闭" : function() {
				        $(this).dialog("close");
				   }
				}
			});
		}



		function print(rowId) {
			$("#partyMemberInfoDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"打印党员信息",
				url:"${path}/baseinfo/partyMemberInfoManage/dispatchOperate.action?mode=print&partyMemberInfo.id="+rowId,
				buttons: {
					  "打印" : function(){
					$("#partyMemberInfoPrint").printArea();
		        	$(this).dialog("close");
			  	 },
				   "关闭" : function() {
				        $(this).dialog("close");
				   }
				}
			});
		}


		function searchPartyMemberInfo() {
			$("#partyMemberInfoList").setGridParam({
				url:"${path}/baseinfo/partyMemberInfoManage/searchPartyMemberInfo.action",
				datatype: "json",
				page:1
			});
			$("#partyMemberInfoList").setPostData($.extend({"searchPartyMemberInfoVo.isEmphasis":$("#isLock").val(),orgId:getCurrentOrgId()},$("#searchPartyMemberInfoForm").serializeObject()));
			$("#partyMemberInfoList").trigger("reloadGrid");
		}


		function getSelectedIdLast() {
			var selectedId;
			var selectedIds = $("#partyMemberInfoList").jqGrid("getGridParam", "selarrrow");
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
		isgridBol = isgrid;
		$("#partyMemberInfoList").setGridParam({
			url:"${path}/baseinfo/partyMemberInfoManage/partyMemberInfoList.action",
			datatype: "json",
			page:1
		});
		$("#partyMemberInfoList").setPostData({
			"orgId":orgId,
	    	"partyMemberInfo.isEmphasis":$("#isLock").val(),
	    	"searchPartyMemberInfoVo.isEmphasis":$("#isLock").val()
	   	});
		$("#partyMemberInfoList").trigger("reloadGrid");
		procButton(isgrid);
	}

	// page layout
	function togglePartyMemberButtonState() {
	  	var selectedIds=$("#partyMemberInfoList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setPartyMemberDeleteButtonEnabled(selectedRowCount>0);
	  	setPartyMemberOtherButtonEnabled(selectedRowCount==1);
	  	if (selectedRowCount==1){
		  	toggleEmphasisButtonEnabled(selectedRowIsnotEmphasis($("#partyMemberInfoList").getRowData(selectedIds[0])));
		} else {
			disablePartyMemberEmphasisButton();
		}
	}
	function toggleEmphasisButtonEnabled(enabled) {
		if (enabled) {
			if(getCurrentOrgId() == USER_ORG_ID) {
				$("#reEmphasise").buttonEnable();
			}
		} else {
			if(getCurrentOrgId() == USER_ORG_ID) {
				$("#cancelEmphasiseMember").buttonEnable();
			}
		}
	}
	function selectedRowIsnotEmphasis(partyMemberInfo) {
		return 	partyMemberInfo.isEmphasis==1;
	}

	function afterLoad(){
		isEmphasisFormatter();
		disableButtonspartyMember();
		checkExport();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		//TODO
		idCollection=$("#partyMemberInfoList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#partyMemberInfoList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function disableButtonspartyMember(){
	  	setPartyMemberDeleteButtonEnabled(false);
	  	setPartyMemberOtherButtonEnabled(false);
	  	disablePartyMemberEmphasisButton();
	}
	function setPartyMemberDeleteButtonEnabled(enabled) {
		if (enabled && getCurrentOrgId() == USER_ORG_ID) {
			$("#deleteMember").buttonEnable();
		}else{
			$("#deleteMember").buttonDisable();
		}
	}
	function setPartyMemberOtherButtonEnabled(enabled) {
		if (enabled) {
			if(getCurrentOrgId() == USER_ORG_ID) {
				$("#updateMember").buttonEnable();
			}
			$("#viewMember").buttonEnable();
		} else {
			$("#updateMember").buttonDisable();
			$("#viewMember").buttonDisable();
		}
	}
	function disablePartyMemberEmphasisButton() {
		$("#cancelEmphasiseMember").buttonDisable();
		$("#reEmphasise").buttonDisable();
	}

	function checkExport() {
		if($("#partyMemberInfoList").getGridParam("records") > 0
				|| $("#partyMemberInfoList").getGridParam("selrow") != null) {
			if(getCurrentOrgId() == USER_ORG_ID) {
				$("#exportMember").buttonEnable();
			}
		} else {
			$("#exportMember").buttonDisable();

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
		if((getCurrentOrgId() != USER_ORG_ID)) {
			$("#addMember").buttonDisable();
			$("#updateMember").buttonDisable();
			$("#deleteMember").buttonDisable();
			$("#importMember").buttonDisable();
			$("#exportMember").buttonDisable();
			$("#printMember").buttonDisable();
			$("#printDetailMember").buttonDisable();
			$("#cancelEmphasiseMember").buttonDisable();
		} else {
			$("#addMember").buttonEnable();
			$("#updateMember").buttonEnable();
			$("#deleteMember").buttonEnable();
			$("#importMember").buttonEnable();
			$("#exportMember").buttonEnable();
			$("#printMember").buttonEnable();
			$("#printDetailMember").buttonEnable();
			$("#cancelEmphasiseMember").buttonEnable();
		}
	}
	$("#refreshOrgTree1").click(function(){
		$("#searchText").attr("value","");
	});

	// ------------------------------ party member info search list and page layout end  ------------------------------
</script>