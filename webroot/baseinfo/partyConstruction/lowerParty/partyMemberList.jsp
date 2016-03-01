<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入身份证号码或姓名" name="lowerMembersearchText" id="lowerMembersearchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入身份证号码或姓名':this.value;" onfocus="value=(this.value=='请输入身份证号码或姓名')?'':this.value;"/>
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="lowerMemberfastSearchButton"><span>检索</span></a>
</div>
<div class="content">
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<pop:JugePermissionTag ename="viewLowerPartyMemberInfos">
			<a id="viewLowerMember" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="getLowerPartyMemberInfos">
			<a id="searchLowerMember" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadLowerMember"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="downloadLowerPartyMemberInfos">
			<a id="exportLowerMember" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<!--
		<pop:JugePermissionTag ename="printPartyMemberInfos">
			<a id="printMember" href="javascript:void(0)"><span>打印列表</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="printdetailsPartyMemberInfos">
			<a id="printDetailMember" href="javascript:void(0)"><span>打印详情</span></a>
		</pop:JugePermissionTag>
		 -->

			<div style="position:absolute;right:10px;top:0px;white-space:nowrap;">
				<select id="lowerMemberIsLock" name="" class="S_object">
						<option value="-1">全部</option>
	 					<option value="0" selected="selected">正常</option>
	 					<option value="1">已注销</option>
				</select>
			</div>
	</div>

	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyMemberInfoList"></table>
		<div id="partyMemberInfoListPager"></div>
	</div>
	<div id="partyMemberInfoDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="partyMemberInfoManagement">
		<span id="title"><s:property value="#request.name" />
		</span>
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
	// ------------------------------ party member search list and page layout start  ------------------------------
	$(document).ready(function() {
		$("#partyMemberInfoList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$(".btnbanner").height()-$("#nav").height()-150,
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen : true},
	  			{name:"name", index:"name",label:"姓名",formatter:nameFont,width:100, frozen : true},
	  			{name:"gender", index:"gender", label:"性别", width:40, formatter:genderFormatter,align:"center"},
	  			{name:"idCardNo", index:"idCardNo", label:"身份证号码", width:150,  formatter:idCardNoFont, frozen : true},
	  			{name:"currentAddress", index:"currentAddress", label:"常住地址", width:150, frozen : true},
	  			{name:"province", index:"province", label:"户籍地", width:60,sortable:false, frozen : true},
	  			{name:"joinPartyDate", index:'joinPartyDate', label:'入党日期', width:100, frozen : true},
	  		  	{name:"isFlowPartyCard", index:'isFlowPartyCard', label:'是否流动党员', width:80, frozen : true},

	  		  	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, hidden:true},
	  		  	{name:"usedName", index:'usedName', label:'曾用名', width:80 ,hidden:true},
				{name:"workUnit",label:"工作单位或就读学校", width:200,sortable:false,hidden:true},
				{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true},
				{name:"stature",label:"身高",width:100,sortable:false,hidden:true},
				{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
				{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:100,sortable:false,hidden:true},
				{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
				{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
				{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,sortable:false,hidden:true},
				{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
				{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
				{name:"otherAddress",label:"临时居所",width:100,sortable:false,hidden:true},
				{name:"telephone",label:"联系电话", width:100,sortable:false,hidden:true},
				{name:"mobileNumber",label:"联系手机", width:100,sortable:false,hidden:true},
				{name:"email",label:"电子邮件", width:100,sortable:false,hidden:true},
				{name:"birthday",label:"出生日期", width:100,hidden:true},
				{name:"remark",sortable:false,label:"备注",hidden:true,width:100},
				{name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}
		  	],
		  	multiselect:true,
		  	onSelectAll:toggleLowerMemberButtonState,
	    	loadComplete: afterLowerMemberLoad,
			<pop:JugePermissionTag ename="viewLowerPartyMemberInfos">
	        	ondblClickRow: dbClickViewPartyMemberInfo,
			</pop:JugePermissionTag>
			onSelectRow:toggleLowerMemberButtonState
		});

	  	jQuery("#partyMemberInfoList").jqGrid('setFrozenColumns');
		$("#lowerMemberIsLock").change(function() {
			$("#searchText").attr("value","");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		$("#viewLowerMember").click(function(event){
			var allValue = getSelectedIds();
			if(allValue.length >1 || allValue.length==0){
				 return;
			}
			dbClickViewPartyMemberInfo(allValue);
		});

		if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
			onOrgChanged(getCurrentOrgId(),isGrid());
			checkLowerMemberExport();
		}

		// ------------------------------ function processing start -------------------------------------------
		$("#lowerMemberfastSearchButton").click(function() {
			search(getCurrentOrgId());
		});
		//快速检索
		function search(orgId) {
			var conditions = $("#lowerMembersearchText").val();

			if(conditions == '请输入身份证号码或姓名') return;
			$("#partyMemberInfoList").setPostData({
				"organizationId":orgId,
				"searchPartyMemberInfoVo.isEmphasis":$("#lowerMemberIsLock").val(),
				"searchPartyMemberInfoVo.fastSearchKeyWords":conditions
			});
			$("#partyMemberInfoList").setGridParam({
				url:"${path}/baseinfo/lowerMemberInfoManage/searchPartyMemberInfo.action",
				datatype: "json",
				page:1
			});
			
			$("#partyMemberInfoList").trigger("reloadGrid");
		 }

		//刷新
		$("#reloadLowerMember").click(function(event){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		function getSelectedIds() {
			var selectedIds = $("#partyMemberInfoList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}

		//显示详细信息的方法
		function dbClickViewPartyMemberInfo(rowid){
			if(rowid==null){
		 		return;
			}
			var memberInfo =  $("#partyMemberInfoList").getRowData(rowid);
			$("#partyMemberInfoDialog").createDialog({
				width: dialogWidth,
				height: dialogHeight,
				title:'查看党员信息',
				modal : true,
				url:'${path}/baseinfo/lowerMemberInfoManage/dispatchOperate.action?mode=view&population.id='+memberInfo.id,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});

		}

		//导出
		$("#exportLowerMember").click(function(event) {
			if($("#partyMemberInfoList").getGridParam("records")>0) {
				$("#partyMemberInfoDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出党员信息",
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'partyMemberInfoList',
						organizationId:getCurrentOrgId(),
						downloadUrl:'${path}/baseinfo/lowerMemberInfoManage/downloadPartyMemberInfo.action'
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


		$("#searchLowerMember").click(function(event) {
			$("#partyMemberInfoDialog").createDialog({
				width:650,
				height:500,
				title:'党员查询-请输入查询条件',
				url:'${path}/baseinfo/lowerMemberInfoManage/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
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

			function searchPartyMemberInfo() {
				$("#partyMemberInfoList").setGridParam({
					url:"${path}/baseinfo/lowerMemberInfoManage/searchPartyMemberInfo.action",
					datatype: "json",
					page:1
				});
				$("#partyMemberInfoList").setPostData($.extend({"searchPartyMemberInfoVo.isEmphasis":$("#lowerMemberIsLock").val(),organizationId:getCurrentOrgId()},$("#searchPartyMemberInfoForm").serializeObject()));
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
		$("#partyMemberInfoList").setGridParam({
			url:"${path}/baseinfo/lowerMemberInfoManage/getlowerMemberInfo.action",
			datatype: "json",
			page:1
		});
		$("#partyMemberInfoList").setPostData({
			"organizationId":orgId,
	    	"searchPartyMemberInfoVo.isEmphasis":$("#lowerMemberIsLock").val()
	   	});
		$("#partyMemberInfoList").trigger("reloadGrid");
	}

	// page layout
	function toggleLowerMemberButtonState() {
	  	var selectedIds=$("#partyMemberInfoList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;

	  	setLowerMemberOtherButtonEnabled(selectedRowCount==1);

	}
	function toggleEmphasisButtonEnabled(enabled) {
		if (enabled) {
			$("#reEmphasise").buttonEnable();
		} else {
			$("#cancelEmphasise").buttonEnable();
		}
	}
	function selectedRowIsnotEmphasis(partyMemberInfo) {
		return 	partyMemberInfo.isEmphasis==1;
	}

	function afterLowerMemberLoad(){
		isEmphasisFormatter()
		disableLowerMemberButtons();
		checkLowerMemberExport();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#partyMemberInfoList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#partyMemberInfoList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function disableLowerMemberButtons(){
	  	setLowerMemberOtherButtonEnabled(false);

	}

	function setLowerMemberOtherButtonEnabled(enabled) {
		if (enabled) {
			$("#viewLowerMember").buttonEnable();
		} else {
			$("#viewLowerMember").buttonDisable();
		}
	}


	function checkLowerMemberExport() {
		if($("#partyMemberInfoList").getGridParam("records") > 0
				|| $("#partyMemberInfoList").getGridParam("selrow") != null) {
			$("#exportLowerMember").buttonEnable();
		} else {
			$("#exportLowerMember").buttonDisable();

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


	function onOrgChangedPartyOrgInfo(orgId,isgrid){
		$.ajax({
			url:"${path}/baseinfo/partyOrgInfoManage/searchPartyOrgInfoById.action?orgId="+orgId,
			success:function(data){
				if(data){
					$.each(data, function(key, value) {
						if(null != value) {
							$("label[name='partyOrgInfos."+key+"']").text(value);
						} else {
							$("label[name='partyOrgInfos."+key+"']").text("");
						}
						//$("input[name='partyOrgInfos."+key+"']").val(value);
					});
				} else {
					//$("input[name^='partyOrgInfos.']:visible",$("#partyOrganizationform")).val("");
					$("label[name^='partyOrgInfos.']:visible",$("#partyOrganizationform")).text("");
				}
				$("#orgName_label").text(data.organization.orgName);
				if(window.tree !=undefined){
					$("#orgIdForParty").val($.getSelectedNode(tree).attributes.id);
					$("#orgIds").val($.getSelectedNode(tree).attributes.id);
				}
			}
		})
		if(getCurrentOrgId() != USER_ORG_ID) {
			$("#editOrganization").hide();
			return;
		} else {
			$("#editOrganization").show();
		}
	}
	// ------------------------------ party member info search list and page layout end  ------------------------------
</script>
