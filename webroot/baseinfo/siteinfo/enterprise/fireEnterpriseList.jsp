<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType"
value="<s:property value="#parameters.keyType"/>" />
<div class="content">
<div class="ui-corner-all" id="nav"><pop:JugePermissionTag
	ename="addEnterprise,addProtectionKey,addSafetyProductionKey,addFireSafetyKey,addSecurityKey">
	<a id="add" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag
	ename="updateEnterprise,updateProtectionKey,updateSafetyProductionKey,updateFireSafetyKey,updateSecurityKey">
	<a id="update" href="javascript:void(0)"><span><strong
		class="ui-ico-xg"></strong>修改</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag
	ename="viewEnterprise,viewSecurityKey,viewFireSafetyKey,viewSafetyProductionKey,viewProtectionKey">
	<a id="view" href="javascript:void(0)"><span><strong
		class="ui-ico-cakan"></strong>查看</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag
	ename="deleteEnterprise,deleteSecurityKey,deleteFireSafetyKey,deleteSafetyProductionKey,deleteProtectionKey">
	<a id="delete" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>删除</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag
	ename="searchEnterprise,searchSecurityKey,searchFireSafetyKey,searchSafetyProductionKey,searchProtectionKey">
	<a id="search" href="javascript:void(0)"><span><strong
		class="ui-ico-cx"></strong>查询</span></a>
</pop:JugePermissionTag> <a id="reload" href="javascript:void(0)"><span><strong
	class="ui-ico-refresh"></strong>刷新</span></a> <pop:JugePermissionTag
	ename="importEnterprise,importSecurityKey,importFireSafetyKey,importSafetyProductionKey,importProtectionKey">
	<a id="import" href="javascript:void(0)"><span><strong
		class="ui-ico-dr"></strong>导入</span></a>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="downEnterprise,downSecurityKey,downFireSafetyKey,downSafetyProductionKey,downProtectionKey">
	<a id="export" href="javascript:void(0)"><span><strong
		class="ui-ico-dc"></strong>导出</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="shiftEnterprise,shiftSecurityKey,shiftFireSafetyKey,shiftProtectionKey"> 
			<a id="shift" href="javascript:void(0)"><span>转移</span></a> 
	</pop:JugePermissionTag> 
		
<pop:JugePermissionTag ename="abolishSecurityKey,abolishFireSafetyKey,abolishSafetyProductionKey,abolishProtectionKey">
 	<a id="isEmphasis" href="javascript:void(0)"><span>取消关注</span></a>
 </pop:JugePermissionTag>
 <pop:JugePermissionTag ename="anewSecurityKey,anewFireSafetyKey,anewSafetyProductionKey,anewProtectionKey">
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
<table id="enterpriseList"></table>
<div id="enterpriseListPager"></div>
</div>
<div id="enterpriseDialog"></div>
<div id="noticeDialog"></div>
<div id="personInChargeDialog"></div>
<div id="floorpersonDialog"></div>


</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="enterpriseManagement">
	<span id="enterpriseKey"><s:property value="#request.name" /></span>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="protectionKeyManagement">
	<span id="protectionKey"><s:property value="#request.name" /></span>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="safetyProductionKeyManagement">
	<span id="safetyProductionKey"><s:property value="#request.name" /></span>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="fireSafetyKeyManagement">
	<span id="fireSafetyKey"><s:property value="#request.name" /></span>
</pop:JugePermissionTag> <pop:JugePermissionTag ename="securityKeyManagement">
	<span id="securityKey"><s:property value="#request.name" /></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
	<pop:formatterProperty name="industry" domainName="@com.tianque.domain.property.PropertyTypes@OCCUPATION" />
	<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" />
	var isgridBol = false;
	var title="企业";
	var keyType='';
	var keyTypeName='';
	var enterpriseTemplates='';

	function keyTypeValue(){
		if($("#keyType") != null && $("#keyType")[0] != null && $("#keyType")[0].value != ''){
			keyType = $("#keyType")[0].value;
		}
	}

	function getKeyTypeName(){
		keyTypeValue();
		if(keyType == 'enterpriseKey'){
			keyTypeName ='规上企业';
			title=$("#enterpriseKey").html();
		}else if(keyType == 'protectionKey'){
			keyTypeName ='重点保障';
			title=$("#protectionKey").html();
		}else if(keyType == 'safetyProductionKey'){
			keyTypeName ='安全生产重点';
			title=$("#safetyProductionKey").html();
		}else if(keyType == 'fireSafetyKey'){
			keyTypeName ='消防安全重点';
			title=$("#fireSafetyKey").html();
		}else if(keyType == 'securityKey'){
			keyTypeName ='治安重点';
			title=$("#securityKey").html();
		}
	}

	function getenterpriseTemplates(){
		keyTypeValue();
		if(keyType == 'enterpriseKey'){
			enterpriseTemplates="ENTERPRISEKEY";
		}else if(keyType == 'protectionKey'){
			enterpriseTemplates="PROTECTIONKEY";
		}else if(keyType == 'safetyProductionKey'){
			enterpriseTemplates="SAFETYPRODUCTIONKEY";
		}else if(keyType == 'fireSafetyKey'){
			enterpriseTemplates="FIRESAFETYKEY";
		}else if(keyType == 'securityKey'){
			enterpriseTemplates="SECURITYKEY";
		}
	}

	function isRiskEnterprise(el, options, rowData){
		if(true == rowData.riskEnterprise){
			return "是";
		}else{
			return "否";
		}
	}



	function onOrgChanged(orgId,isgrid){
		if(isgrid){
			$("#add").buttonEnable();
		}else{
			$("#add").buttonDisable();
			$("#update").buttonDisable();
		}
		isgridBol = isgrid;
		$("#enterpriseList").setGridParam({
			url:'${path}/baseinfo/enterpriseManage/enterpriseList.action',
			datatype: "json",
			page:1
		});
		keyTypeValue();
		$("#enterpriseList").setPostData({
				"ownerOrg.id":orgId,
				"keyType":keyType,
				"enterprise.isEmphasis":$("#isLock").val()
			});
		$("#enterpriseList").trigger("reloadGrid");
	}

	var dialogWidth=950;
	var dialogHeight=580;

	$(document).ready(function(){
		getenterpriseTemplates();

		$("#enterpriseList").jqGridFunction({
			datatype: "local",
			colModel:[
				{name:"id", index:"id", hidden:true },
				{name:"name", index:"name", label: '消防安全重点名称',width:150},
				{name:"type", index:"type", label:'消防安全重点类型', formatter:typeFormatter,width:100},
				{name:"businessLicense", sortable:false, label: '工商执照号码',width:100},
				{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:180, hidden:true},
				{name:"legalPerson", index:"legalPerson", label:'负责人',width:100},
				{name:"industry", index:"industry", label:'所属行业', formatter:industryFormatter,width:100},
				{name:"riskEnterprise", sortable:false, label:'是否存在隐患', formatter:isRiskEnterprise,width:100},
				{name:"hiddenCases", sortable:false, label:'隐患整改情况',width:100},
				{name:"employeeAmount",sortable:false,label:"员工数",hidden:true,width:100},
				{name:"partyMemberAmount",sortable:false,label:"党员数",hidden:true,width:100},
				{name:"areaString",sortable:false,label:"面积",hidden:true,width:100},
				{name:"fax",sortable:false,label:"传真",hidden:true,width:100},
				{name:"telephone",sortable:false,label:"负责人联系电话",hidden:true,width:100},
				{name:"mobileNumber",sortable:false,label:"负责人手机号码",hidden:true,width:100},
				{name:"address",sortable:false,label:"治安重点地址",hidden:true,width:100},
				{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
			],
			<pop:JugePermissionTag ename="viewEnterprise">
				ondblClickRow: viewEnterpriseInfo,
			</pop:JugePermissionTag>
			loadComplete: afterLoad,
			onSelectAll:function(aRowids,status){ if(status) {
		   		var selectedIds = $("#enterpriseList").jqGrid("getGridParam", "selarrrow");
		   		for(var i = 0;i<=selectedIds.length;i++){
		   			var row=$("#enterpriseList").getRowData(selectedIds[i]);
		   			if(row.isEmphasis==1){
		   				$("#emphasis").buttonEnable();
		   			}
		   			if(row.isEmphasis==0){
		   				$("#isEmphasis").buttonEnable();

		   			}
		   			if(selectedIds.length ==2){
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
			onSelectRow: selectRow
		});

		if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}

		$("#isLock").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		$("#add").click(function(){
			if (!isgridBol){
				return;
			}
			if(getCurrentOrgId() == null || getCurrentOrgId() == ""){
				$.notice({
					level:'warn',
					message:'请先选择一个部门'
				});
			}else{
				keyTypeValue();
				getKeyTypeName();
				$("#enterpriseDialog").createDialog({
					width: dialogWidth,
					height: dialogHeight,
					title:'新增'+title,
					url:'${path}/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&keyType='+keyType+'&ownerOrg.id='+getCurrentOrgId(),
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
			}
		});

		$("#view").click(function(){
			viewEnterpriseInfo();
		});

		$("#isEmphasis").click(function(){
			var selectedId = $("#enterpriseList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			$.ajax({
				url:"${path}/baseinfo/enterpriseManage/updateEmphasis.action",
				data:{
					"enterprise.id":selectedId,
					"enterprise.isEmphasis":1
				},
				success:function(responseData){
					if($("#isLock").val()=="-1"){
						$("#enterpriseList").setRowData(responseData.id,responseData);
						$("#"+responseData.id+"").css('color','#778899');
					}else{
						$("#enterpriseList").delRowData(responseData.id);
					}
					$.messageBox({message:"取消关注成功！"});
					disableButtons();
					checkExport();
				}
			});
		});

		$("#emphasis").click(function(){
			var selectedId = $("#enterpriseList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			$.ajax({
				url:"${path}/baseinfo/enterpriseManage/updateEmphasis.action",
				data:{
					"enterprise.id":selectedId,
					"enterprise.isEmphasis":0
				},
				success:function(responseData){
					if($("#isLock").val()=="-1"){
						$("#mentalPatientList").setRowData(responseData.id,responseData);
						$("#"+responseData.id+"").css('color','black');
					}else{
						$("#enterpriseList").delRowData(responseData.id);
					}
					$.messageBox({message:"重新关注成功！"});
					disableButtons();
					checkExport();
				}
			});
		});


		$("#update").click(function(){
			var selectedId = $("#enterpriseList").getGridParam("selrow");
			if(selectedId==null){
				return;
			}
			getKeyTypeName();
			$("#enterpriseDialog").createDialog({
				width: dialogWidth,
				height: dialogHeight,
				title: '修改'+title,
				modal: true,
				url: '${path}/baseinfo/enterpriseManage/dispatchOperate.action?mode=edit&keyType='+keyType+'&enterprise.id='+selectedId,
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

		$("#delete").click(function(){
			var allValue = setCompVal();
			if(allValue.length ==0){
				 return;
			}
			getKeyTypeName();
			var str = relatePlace(allValue);
			$.confirm({
				title:"确认删除",
				message:str,
				width: 400,
				okFunc: deleteEnterprise
			})
		});

		$("#reload").click(function(event){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		$("#search").click(function(event){
			getKeyTypeName();
			$("#enterpriseDialog").createDialog({
				width: 700,
				height: 450,
				title: title+"查询-请输入查询条件",
				url: "${path}/baseinfo/enterpriseManage/dispatchOperate.action?mode=search&keyType="+keyType,
				buttons: {
			   		"查询" : function(event){
			   			searchEnterprise();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		$("#import").click(function(event){
			$("#enterpriseDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:'${path}/common/import.jsp?dataType=enterprise&dialog=enterpriseDialog&startRow=6&templates='+enterpriseTemplates+'&enterpriseType='+keyType,
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

		$("#shift").click(function(event){
			if (!isgridBol){
				return;
			}
			$("#enterpriseDialog").createDialog({
				width: 300,
				height: 300,
				title:"转移"+title+"信息",
				url:'${path}/baseinfo/enterpriseManage/shiftDispatch.action?orgId='+getCurrentOrgId()+'&enterPriseIds='+$("#enterpriseList").jqGrid("getGridParam", "selarrrow"),
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

		$("#export").click(function(event){
			if ($("#enterpriseList").getGridParam("records")>0){
				$("#enterpriseDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:'${path}/common/exportExcel.jsp',
					postData:{
						gridName:'enterpriseList',
						downloadUrl:'${path}/baseinfo/searchEnterprise/downloadEnterprise.action'
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

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#enterpriseList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#enterpriseList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function afterLoad(){
		isEmphasisFormatter();
		disableButtons();
		checkExport();
	}
	function checkExport(){
		if($("#enterpriseList").getGridParam("records")>0
				|| $("#enterpriseList").getGridParam("selrow")!=null){
			$("#export").buttonEnable();
		}else{
			$("#export").buttonDisable();

		}
	}
	function selectRow(){
		var selectedId = $("#enterpriseList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		var enterprise =  $("#enterpriseList").getRowData(selectedId);
		var selectedCounts = getActualjqGridMultiSelectCount("enterpriseList");
		var count = $("#enterpriseList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("enterpriseList", true);
		}else{
			jqGridMultiSelectState("enterpriseList", false);
		}
		$("#delete").buttonEnable();
		$("#shift").buttonEnable();
		if(selectedCounts==1){
			$("#view").buttonEnable();
			$("#update").buttonEnable();
			if(enterprise.isEmphasis!=1){
				$("#isEmphasis").buttonEnable();
				$("#emphasis").buttonDisable();
			}else{
				$("#emphasis").buttonEnable();
				$("#isEmphasis").buttonDisable();
			}
		}else{
			$("#view").buttonDisable();
			$("#update").buttonDisable();
			$("#emphasis").buttonDisable();
			$("#isEmphasis").buttonDisable();
			}
		if(selectedCounts==0){
			$("#delete").buttonDisable();
			$("#shift").buttonDisable();
		}
		}

	function disableButtons(){
		$("#isEmphasis").buttonDisable();
		$("#emphasis").buttonDisable();
		$("#update").buttonDisable();
		$("#delete").buttonDisable();
		$("#view").buttonDisable();
		$("#shift").buttonDisable();
	}

	function viewEnterpriseInfo(){
		var selectedId = $("#enterpriseList").getGridParam("selrow");
		if(selectedId==null){
			return;
		}
		getKeyTypeName();
		$("#enterpriseDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title: '查看'+title+'信息',
			modal: true,
			url: '${path}/baseinfo/enterpriseManage/dispatchOperate.action?mode=view&keyType='+keyType+'&enterprise.id='+selectedId,
			buttons: {
				"打印" : function(event){
	        		$(this).dialog("close");
	        		printEnterprise(keyType,selectedId);
				},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function printEnterprise(keyType,selectedId){
		var url='${path}/baseinfo/enterpriseManage/dispatchOperate.action?mode=print&keyType='+keyType+'&enterprise.id='+selectedId;
		$("#enterpriseDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title: '打印'+title+'信息',
			modal: true,
			url: url,
			buttons: {
				"确定" : function(event){
					$("#enterprisePrint").printArea();
					$(this).dialog("close");
				},
		  		 "关闭" : function(){
		       		 $(this).dialog("close");
		  		 }
			}
		});
	}

	function relatePlace(placeIds){
		var str="";
		$.ajax({
			async:false,
			url: "${path}/baseinfo/enterpriseManage/hasRelatePlace.action?enterPriseIds="+placeIds,
			success:function(responseData){
				if(responseData){
					str="有场所被引用,要删除未被引用的场所吗?一经删除将无法恢复";
				}else{
					str="确定要删除吗?一经删除将无法恢复";
				}
			}
		});
		return str;
	}

	function setCompVal(){
		var selectedIds = $("#enterpriseList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function deleteEnterprise(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.ajax({
			url:"${path}/baseinfo/enterpriseManage/deleteEnterprise.action?enterPriseIds="+allValue,
			success:function(data){
				for(var ids=0;ids<data.length;ids++){
					$("#enterpriseList").delRowData(data[ids]);
				}
			    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    $("input[role='checkbox']").attr("checked",false);
			    disableButtons();
			    checkExport();
			}
		});
	}

	function searchEnterprise(){
		var conditionName = $("#conditionName").val();
		var conditionTypeId = $("#conditionTypeId").val();
		var conditionLegalPerson = $("#conditionLegalPerson").val();
		var conditionBusinessLicense = $("#conditionBusinessLicense").val();
		var conditionIndustryId = $("#conditionIndustryId").val();

		//面积区间
		var conditionMinArea = $("#conditionMinArea").val();
		var conditionMaxArea = $("#conditionMaxArea").val();
		// 是否危险企业（默认为否）
		var conditionIsRiskEnterprise = $("#conditionIsRiskEnterprise").val();
		//企业联系电话
		var conditionEnterpriseTelephone = $("#conditionEnterpriseTelephone").val();
		//党员数量区间
		var conditionMinPartyMemberAmount = $("#conditionMinPartyMemberAmount").val();
		var conditionMaxPartyMemberAmount = $("#conditionMaxPartyMemberAmount").val();
		//企业传真
		var conditionFax = $("#conditionFax").val();
		//员工数区间
		var conditionMinEmployeeAmount = $("#conditionMinEmployeeAmount").val();
		var conditionMaxEmployeeAmount = $("#conditionMaxEmployeeAmount").val();
		//法人手机号码
		var conditionMobileNumber = $("#conditionMobileNumber").val();
		//注册资金区间
		var conditionMinRegisteredCapital = $("#conditionMinRegisteredCapital").val();
		var conditionMaxRegisteredCapital = $("#conditionMaxRegisteredCapital").val();
		//法人联系电话
		var conditionTelephone = $("#conditionTelephone").val();
		//企业地址
		var conditionAddress = $("#conditionAddress").val();
		//整改情况
		var conditionHiddenRectification = $("#conditionHiddenRectification").val();

		var keyType=$("#keyType").val();
		$("#enterpriseList").setGridParam({
			url:"${path}/baseinfo/searchEnterprise/searchEnterprise.action",
			dataType:"json",
			page:1
		});
		var searchCondition={
				"ownerOrg.id":getCurrentOrgId(),
				"enterpriseSearchCondition.name":conditionName,
				"enterpriseSearchCondition.typeId":conditionTypeId,
				"enterpriseSearchCondition.legalPerson":conditionLegalPerson,
				"enterpriseSearchCondition.businessLicense":conditionBusinessLicense,
				"enterpriseSearchCondition.industryId":conditionIndustryId,
				"enterpriseSearchCondition.minArea":conditionMinArea,
				"enterpriseSearchCondition.maxArea":conditionMaxArea,
				"enterpriseSearchCondition.enterpriseTelephone":conditionEnterpriseTelephone,
				"enterpriseSearchCondition.minPartyMemberAmount":conditionMinPartyMemberAmount,
				"enterpriseSearchCondition.maxPartyMemberAmount":conditionMaxPartyMemberAmount,
				"enterpriseSearchCondition.fax":conditionFax,
				"enterpriseSearchCondition.minEmployeeAmount":conditionMinEmployeeAmount,
				"enterpriseSearchCondition.maxEmployeeAmount":conditionMaxEmployeeAmount,
				"enterpriseSearchCondition.mobileNumber":conditionMobileNumber,
				"enterpriseSearchCondition.minRegisteredCapital":conditionMinRegisteredCapital,
				"enterpriseSearchCondition.maxRegisteredCapital":conditionMaxRegisteredCapital,
				"enterpriseSearchCondition.telephone":conditionTelephone,
				"enterpriseSearchCondition.address":conditionAddress,
				"enterpriseSearchCondition.hiddenRectification":conditionHiddenRectification,
				"enterpriseSearchCondition.keyType":keyType,
				"enterpriseSearchCondition.isEmphasis":$("#isLock").val()
		};
		/** 是危险企业（默认为否） */
		if(conditionIsRiskEnterprise != ""){
			$.extend(searchCondition,{"enterpriseSearchCondition.isRiskEnterprise":conditionIsRiskEnterprise});
		}
		$("#enterpriseList").setPostData(searchCondition);
		$("#enterpriseList").trigger("reloadGrid");
	}
</script>