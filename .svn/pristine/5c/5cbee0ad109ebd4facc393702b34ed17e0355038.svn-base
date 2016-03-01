<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<input type="hidden" id="keyType" value="<s:property value="#parameters.keyType"/>" />
<div class="content">
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
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@FIRESAFETYKEY_KEY"/>'/>
<script type="text/javascript">
	<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@FIRESAFETY_TYPE" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	var isgridBol = false;
	var title="消防安全重点";
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

	//关注程度显示效果
	function attentionExtentColor(el,options,rowData){
		var displayName=attentionExtentFormatter(el,options,rowData);
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='中等')
			return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
	}

	function onOrgChangedForStat(orgId,isgrid){
		if(isgrid){
			$("#add").buttonEnable();
		}else{
			$("#add").buttonDisable();
			$("#update").buttonDisable();
		}
		isgridBol = isgrid;
		$("#enterpriseList").setGridParam({
			url:'${path}/baseinfo/fireSafetyEnterpriseManage/fireSafetyEnterpriseList.action',
			datatype: "json",
			page:1
		});
		keyTypeValue();
		var isEmphasis = '';
		if($("#isLock").val() == undefined || $("#isLock").val() == null ){
			isEmphasis = 0;
		}else{
			isEmphasis = $("#isLock").val();
		}
		$("#enterpriseList").setPostData({
				"ownerOrg.id":orgId,
				"orgId":orgId,
				"keyType":keyType,
				"enterprise.isEmphasis":0
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
				{name:"encryptId", index:"id", hidden:true,frozen:true },
		    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:50,frozen:true,sortable:false,align:'center' },
		    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
				{name:"name", index:"name", label: '消防安全重点名称',formatter:nameFormatter,sortable:true,width:180},
				{name:"address",index:"address",sortable:true,label:"治安重点地址",width:200},
				{name:"legalPerson", index:"legalPerson", label:'负责人',sortable:true,width:100},
				{name:"type", index:"type", label:'消防安全重点类型', formatter:typeFormatter,sortable:true,width:150},
				{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',sortable:false,width:180, hidden:true},
				{name:"riskEnterprise", sortable:false, label:'是否存在隐患',align:"center", formatter:isRiskEnterprise,sortable:false,width:100},
				{name:"hiddenCases", sortable:true, label:'隐患整改情况',width:100},
				
				{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
				{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
				{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
				{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
				{name:"telephone",sortable:true,label:"负责人联系电话",hidden:true,width:100},
				{name:"mobileNumber",sortable:true,label:"负责人手机号码",hidden:true,width:100},
				{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
			],
			ondblClickRow: viewEnterpriseInfo,
			multiselect:true,
			width:860,
		  	height:425,
			loadComplete: afterLoad
		});

		function operatorFormatter(el,options,rowData){
			return "<a href='javascript:viewEnterpriseInfo("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
		}
		function nameFormatter(el,options,rowData){
			return "<a href='javascript:viewEnterpriseInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
		}
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if('<s:property value="#parameters.isSearch"/>' == 1){
				searchEnterprise();
			}else if('<s:property value="#parameters.isSearch"/>' == 0){
				fastSearch();
			}else{
				onOrgChangedForStat(getOrgIdForStat(),isGrid());
			}
		}


		$("#search").click(function(event){
			getKeyTypeName();
			$("#enterpriseDialog").createDialog({
				width: 700,
				height: 500,
				title: title+"查询-请输入查询条件",
				url: "${path}/baseinfo/fireSafetyEnterpriseManage/dispatchOperate.action?mode=search&keyType="+keyType,
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

	});

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#enterpriseList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#enterpriseList").getRowData(idCollection[i]);
				if(ent.isEmphasis=="true"){
				$("#enterpriseList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}

	function afterLoad(){
		isEmphasisFormatter();
	}

	function viewEnterpriseInfo(selectedId){
		var rowData = $("#enterpriseList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			return;
		}
		getKeyTypeName();
		$("#enterpriseDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title: '查看'+title+'信息',
			modal: true,
			url: '${path}/baseinfo/fireSafetyEnterpriseManage/dispatchOperateByEncrypt.action?mode=view&locationType='+$("#_locationType_").val()+'&keyType='+keyType+'&location.id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function getPlaceName(){
		var placeName = "";
		$("#conditionName").length > 0?placeName = $("#conditionName").val():placeName = $("#searchByPlaceName").val();
		return placeName;
	}

	function fastSearch(){
		var initParam={
				"ownerOrg.id":getOrgIdForStat(),
				"orgId":getOrgIdForStat(),
				"enterpriseSearchCondition.keyType":$("#keyType").val(),
				"enterpriseSearchCondition.isEmphasis":0
				};
		if("请输入场所名称"!=$("#searchByPlaceName").val()){
			initParam={
					"ownerOrg.id":getOrgIdForStat(),
					"orgId":getOrgIdForStat(),
					"enterpriseSearchCondition.keyType":$("#keyType").val(),
					"enterpriseSearchCondition.name":$("#searchByPlaceName").val(),
					"enterpriseSearchCondition.isEmphasis":0
					};
			}
		$("#enterpriseList").setGridParam({
			url:"${path}/baseinfo/searchSafetyProductionKey/searchSafetyProductionKey.action",
			datatype:"json",
			page:1
		});
		$("#enterpriseList").setPostData(initParam);
		$("#enterpriseList").trigger("reloadGrid");
		}

	function searchEnterprise(){
		$("#enterpriseList").setGridParam({
			url:"${path}/baseinfo/searchSafetyProductionKey/searchSafetyProductionKey.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"orgId":getCurrentOrgId()},$("#searchEnterpriseForm").serializeObject());
		$("#enterpriseList").setPostData(postData);
		$("#enterpriseList").trigger("reloadGrid");
		$("#statisticsDialog").dialog("close");
	}
	
	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
		}
	}
</script>