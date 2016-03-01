<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<#assign moduleName=moduleName />
<#assign module=module />
<#assign lowerCaseModuleName="companyPlaceTemp"/>
<#assign upperCaseModuleName=moduleName?upper_case />
<#assign bigType=bigType />
<#assign powerModuleName=moduleName[0]?upper_case+moduleName?substring(1) />

<#if module=='NEWPUBLICPLACE'>
	<#assign moduleCName='公共活动场所' />
<#elseif module=='TRAFFICPLACE'>
	<#assign moduleCName='交通场所' />
<#elseif module=='ENTERTAINMENTPLACE'>
	<#assign moduleCName='娱乐场所' />
<#elseif module=='TRADEPLACE'>
	<#assign moduleCName='商贸场所' />
<#elseif module=='NEWINTERNETBAR'>
	<#assign moduleCName='网吧' />
<#elseif module=='ACCOMMODATIONSERVICESPLACE'>
	<#assign moduleCName='住宿服务场所' />
<#elseif module=='NEWFOODSERVICESPLACE'>
	<#assign moduleCName='餐饮服务场所' />
<#elseif module=='TRAVELINGPLACE'>
	<#assign moduleCName='旅游场所' />
<#elseif module=='CONSTRUCTIONPLACE'>
	<#assign moduleCName='施工场所' />
<#elseif module=='OTHERPLACE'>
	<#assign moduleCName='其他场所' />
<#elseif module=='PARTYGOVERNMENTORGANCOMPANY'>
	<#assign moduleCName='党政机关' />
<#elseif module=='NEWSCHOOLS'>
	<#assign moduleCName='学校' />
<#elseif module=='NEWHOSPITAL'>
	<#assign moduleCName='医院' />
<#elseif module=='NEWDANGEROUSCHEMICALSUNIT'>
	<#assign moduleCName='危化品存放单位' />
<#elseif module=='OTHERCOMPANY'>
	<#assign moduleCName='其他单位' />
</#if>

<div class="ui-corner-all contentNav" id="nav">
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
				<input type="text" class="basic-input" value="请输入场所名称或地址" id="searchCurrentlyAddress" class="searchtxt" maxlength="18" onblur="value=(this.value=='')?'请输入场所名称或地址':this.value;" onfocus="value=(this.value=='请输入场所名称或地址')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
	</div>
	<@s.if test='#parameters.mode[0].equals("claimList")'>
		<@pop.JugePermissionTag ename="claimSearch${moduleName}">
		<a id="search" href="javascript:void(0)"><span>搜索</span></a>
		</@pop.JugePermissionTag>
    	<span class="lineBetween "></span>
    	<@pop.JugePermissionTag ename="claimAdvancedSearch${moduleName}">
    	<a id="searchType" href="javascript:void(0)"><span>高级搜索</span></a>
    	</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="claim${moduleName},unClaim${moduleName},stepClaim${moduleName}">
			<a href="javascript:;" class="nav-dropdownBtn"><span>认领</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		</@pop.JugePermissionTag>
		<div class="nav-sub-buttons">
			<@pop.JugePermissionTag ename="claim${moduleName}">
				<a id="claim" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>认领</span></a>
			</@pop.JugePermissionTag>
			<#-- 村社区层级登录时，隐去分步认领功能 -->
			<#if 10!=USER_ORG_LEVEL>
			<@pop.JugePermissionTag ename="stepClaim${moduleName}">
				<a id="stepClaim" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>分步认领</span></a>
			</@pop.JugePermissionTag>
			</#if>
			<@pop.JugePermissionTag ename="unDoClaim${moduleName}">
				<a id="unClaim" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>撤销认领</span></a>
			</@pop.JugePermissionTag>
		</div>
		<@s.if test='@com.tianque.core.util.ThreadVariable@getUser().isAdmin()'>
			<a id="deleteForClaim" href="javascript:void(0)"><span>批量删除</span></a>
		</@s.if>
	</@s.if>
	<@s.else>
	<@pop.JugePermissionTag ename="importSearch${moduleName}">
		<a id="search" href="javascript:void(0)"><span>搜索</span></a>
		</@pop.JugePermissionTag>
    	<span class="lineBetween "></span>
    	<@pop.JugePermissionTag ename="importAdvancedSearch${moduleName}">
    	<a id="searchType" href="javascript:void(0)"><span>高级搜索</span></a>
    	</@pop.JugePermissionTag>
	<@pop.JugePermissionTag ename="import${moduleName}">
		<a id="import" href="javascript:void(0)"><span>导入</span></a>
	</@pop.JugePermissionTag>
	<@pop.JugePermissionTag ename="importDelete${moduleName}">
		<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
	</@pop.JugePermissionTag>
	</@s.else>
	<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>	
	<#-- 
	 -->
	<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName()=='admin'">
	<a id="testJob" href="javascript:void(0)"><span>JOB测试</span></a>
	</@s.if>
</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="${lowerCaseModuleName}List"> </table>
		<div id="${lowerCaseModuleName}ListPager"></div>
	</div>
	<div id="${lowerCaseModuleName}Dialog"></div>
	<div id="noticeDialog"></div>
	<div id="${lowerCaseModuleName}MaintanceDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="searchDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
	<div id="resultListDialog">
		<input type="hidden" id="targetClaimOrgId" />
		<input type="hidden" id="claimOrgIdForResultList" />
		<div id="claimTabs" style="width:100%;">
			<ul>
				<li><a href="#successMsgDiv">认领成功</a></li>
				<li><a href="#claimErrorMsgDiv">认领失败</a></li>
			</ul>
			<div id="successMsgDiv">
				<table id="successMsg"></table>
			</div>
			<div id="claimErrorMsgDiv">
				<table id="claimErrorMsg"></table>
			</div>
		</div>
    </div>
    <input type="hidden" value="<@s.if test='#parameters.mode[0].equals("claimList")'>claim</@s.if><@s.else>import</@s.else>" id="dmType"/>
    <input type="hidden" value="${moduleCName!}" id="moduleCName"/>
    <input type="hidden" value="${lowerCaseModuleName!}" id="lowerCaseModuleName"/>
<script>
	//////////////认领重复数据//////////////
function compareRepeatClaimData(event,tempId){
		$("#noticeDialog").createDialog({
			width: 600,
			height: 530,
			title:"对比页面",
			url:'${path}/plugin/dataManage/${lowerCaseModuleName!}Manage/compareClaim.action?targetOrgId='+$("#targetClaimOrgId").val()+'&tempId='+tempId+'&type=${lowerCaseModuleName?replace("Temp","")!}',
			buttons:{
				"认领": function(event){
					if(!noConflict){
						$.messageBox({level:'warn',message:"请将冲突数据解决后再提交！"});
	 					return;
					}
					//这里提交数据，把所有class属性包含submitValue的input提交，其它不予理睬  
					subList = $(".submitValue");
					var datalist = {};
					for(var i=0;i<subList.length;i++){
						if($(subList[i]).attr("value")=="-1"||$(subList[i]).attr("value")==null||$(subList[i]).attr("value")=="null"){
							$(subList[i]).attr("value","");
						}
						datalist[$(subList[i]).attr("name")]=$(subList[i]).attr("value");
					}
					datalist["temp.organization.id"]=$("#targetClaimOrgId").val();
					$.ajax({ 
						async:false,
						type: "post", 
						url: "${path}/plugin/dataManage/${lowerCaseModuleName!}Manage/compareRepeatData.action?tempId="+tempId, 
						data: datalist,
						success: function(data){
							$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		        		   	 	$("#claimErrorMsg").delRowData(tempId);
								$.each(data.successList,function(i, n){
									$("#successMsg").addRowData(n.temp.id,n);
								});
			    			$.each(data.errorList,function(i, n){
			   	       			 $("#claimErrorMsg").addRowData(n.temp.id,n);
		   	       			});
		   	       			
			   	       		if($("#claimErrorMsg").getGridParam("records") > 0){
		          	   			 $("#claimTabs").tabFunction({selected:1});
		          	 		}else{
		          	    		$("#claimTabs").tabFunction({selected:0}); 
		             		}
						} 
					}); 
			    	$(this).dialog("close");
				},
			   	"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			}
		});
}
var claimOrgIdForGlobal = 0;
var isFromResultList = false;
function onOrgChanged(){
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}/plugin/dataManage/${lowerCaseModuleName}Manage/findTemps.action",
		datatype: "json",
		page:1
	});
	var initParam = {};
	var modes="";
	<@s.if test='#parameters.mode[0].equals("claimList")'>
	modes="claimList";
	</@s.if>
	<@s.else>
    modes="importList";
    </@s.else>
	if("请输入场所名称或地址"==$("#searchCurrentlyAddress").val()){
		initParam = {
				"searchVo.searchType":0,
				"mode":modes,
		    	"searchVo.dataState":<@s.property value="@com.tianque.plugin.dataManage.util.Constants$ClaimState@UNCLAIM"/>,
		    	"searchVo.classifiCationEn":"${module}"
		    
		};
	}else{
		initParam = {
				"searchVo.searchType":0,
				"mode":modes,
		    	"searchVo.searchArgs":$("#searchCurrentlyAddress").val(),
		    	"searchVo.classifiCationEn":"${module}"
		};
	}
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}
$(document).ready(function(){
	///////////////job测试//////////////
	$("#testJob").click(function(){
		$.ajax({
			url : '${path}/plugin/dataManage/jobTest/doJob.action',
			success : function(data){   
	        	alert("完成了");  
	    	}
	    });  
	});

	///////////////////////////////////////
	//高级搜索事件
	$("#searchType").click(function(){
		$("#searchDialog").createDialog({
			width: 600,
			height: 240,
			title:"${moduleCName!}查询--请输入查询条件",
			url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=search&from=${lowerCaseModuleName}',
			buttons:{
				"查询": function(event){
					searchFunction();
					$(this).dialog("close");
				},
				"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			}
		});
	});
	
	function searchFunction(){
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}/plugin/dataManage/${lowerCaseModuleName}Manage/findTemps.action",
			datatype: "json",
			page:1
		});
		var initParam = {};
		var modes="";
		<@s.if test='#parameters.mode[0].equals("claimList")'>
		modes="claimList";
		</@s.if>
		<@s.else>
	    modes="importList";
	    </@s.else>
	    
	    var data=$("#searchForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
	           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
	            dataJson[data[i].name] = data[i].value;
			}
		}
		$("#${lowerCaseModuleName}List").setPostData($.extend({"searchVo.searchType":0,"mode":modes,"searchVo.classifiCationEn":"${module}"},dataJson));
		$("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	
	$("#resultListDialog").hide();
	
	var lastGridOption = {
		colModel:[
			{name:"claimDetail.importDate",index:"importDate",width:140,label:"导入时间",sortable:true,align:"center"},
			<@s.if test='#parameters.mode[0].equals("claimList")'>
	        {name:"claimDetail.claimDate",index:"claimDate",width:140,label:"认领时间",sortable:true,align:"center"},
	        {name:"claimDetail.claimUserName",sortable:true,width:100,label:"认领人",hidden:true},
	        {name:"claimDetail.claimOrg.orgName",index:'claimOrgId',sortable:true,width:100,label:"认领人所在网格",hidden:true,hidedlg:true},
	        </@s.if>
	        {name:"claimDetail.claimState",sortable:false,width:100, formatter : claimStateFormatter,label:"认领状态",align:"center"},
	        {name:"claimDetail.importOrganization.id",hidden:true,hidedlg:true},
	        {name:"claimDetail.dispose",hidden:true,hidedlg:true},
			{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true,sortable:false},
	        {name:"claimDetail.claimAvailable",hidden:true,hidedlg:true}
		]
	};
	for(var i=0;i<lastGridOption.colModel.length;i++){
    	gridOption.colModel.splice(gridOption.colModel.length,0,lastGridOption.colModel[i]);
    }
	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		colNames: gridOption.colNames,
	  	multiselect:true,
	  	onSelectAll:function(aRowids,status){ },
      onSelectRow: selectRow,
      loadComplete: afterLoad,
	  <@pop.JugePermissionTag ename="claimView${moduleName},importView${moduleName}">
        	ondblClickRow: function(data, iRow,iCol,event){
        	    if(doubleClickTable){
            	   doubleClickTable(event,data);
            	}
        	}
	  </@pop.JugePermissionTag>
	});
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
	onOrgChanged();
	$("#reload").click(function(){
		$("#searchCurrentlyAddress").attr("value",getSearchName());
		onOrgChanged();
	});
	$("#search").click(function(){
		if(getSearchName()==$("#searchCurrentlyAddress").val()){
			return;
		}
		onOrgChanged();
	});
	$("#delete").click(function(e){
		deleteOperator(e);
	});
	
	$("#deleteForClaim").click(function(e){
		deleteOperator(e);
	});
	
	$("#view").click(function(){
		var selectedCounts = getActualjqGridMultiSelectCount("${lowerCaseModuleName}List");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedCounts>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
			return;
		}
	    doubleClickTable(event,selectedId);
		
	});
	
	
	$("#refreshSearchKey").click(function(){
		$("#searchCurrentlyAddress").attr("value",getSearchName());
	});
	function getImportTemplateKey(key) {
		var enterpriseTemplates='';
		if(key == 'NewPartyGovernmentOrganCompanyTemp'){
			enterpriseTemplates="NEWPARTYGOVERNMENTORGANCOMPANYTEMP";
		}else if(key == 'NewEducationCompanyTemp'){
			enterpriseTemplates="NEWEDUCATIONCOMPANYTEMP";
		}else if(key == 'NewMedicalHygieneCompanyTemp'){
			enterpriseTemplates="NEWMEDICALHYGIENECOMPANYTEMP";
		}else if(key == 'NewDangerousStoreCompanyTemp'){
			enterpriseTemplates="NEWDANGEROUSSTORECOMPANYTEMP";
		}else if(key == 'NewOtherCompanyTemp'){
			enterpriseTemplates="NEWOTHERCOMPANYTEMP";
		}else if(key=='NewPublicPlaceTemp'){
		    enterpriseTemplates="NEWPUBLICPLACETEMP";
		}else if(key=='NewTrafficPlaceTemp'){
		    enterpriseTemplates="NEWTRAFFICPLACETEMP";
		}else if(key=='NewEntertainmentPlaceTemp'){
		    enterpriseTemplates="NEWENTERTAINMENTPLACETEMP";
		}else if(key=='NewTradePlaceTemp'){
		    enterpriseTemplates="NEWTRADEPLACETEMP";
		}else if(key=='NewInternetServicesPlaceTemp'){
		    enterpriseTemplates="NEWINTERNETSERVICESPLACETEMP";
		}else if(key=='NewAccommodationServicesPlaceTemp'){
		    enterpriseTemplates="NEWACCOMMODATIONSERVICESPLACETEMP";
		}else if(key=='NewFoodServicesPlaceTemp'){
		    enterpriseTemplates="NEWFOODSERVICESPLACETEMP";
		}else if(key=='NewTravelingPlaceTemp'){
		    enterpriseTemplates="NEWTRAVELINGPLACETEMP";
		}else if(key=='NewConstructionPlaceTemp'){
		    enterpriseTemplates="NEWCONSTRUCTIONPLACETEMP";
		}else if(key=='NewOtherPlaceTemp'){
		    enterpriseTemplates="NEWOTHERPLACETEMP";
		}
		return enterpriseTemplates;
	}
	
	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:"${path}/common/import.jsp?isNew=1&dataType=${moduleName}&dialog=noticeDialog&startRow=${(importStartRow)!}&templates=" + getImportTemplateKey("${moduleName}")+"&listName=${lowerCaseModuleName}List",
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
	

function updateOperator(event,selectedId){
		var selectedCounts = getActualjqGridMultiSelectCount("${lowerCaseModuleName}List");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedCounts>1){
			$.messageBox({level:'warn',message:"请选择一条未认领记录，再进行修改！"});
			 return;
		}
		var ent =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(ent.claimState==10||ent.claimState=='10'){
			 $.messageBox({level:'warn',message:"该${moduleCName}信息已经认领，不能修改!"});
			 return;
		}
		updateTempById(event,selectedId,"fromList");
	}
	
	
	function checkSelectNum(){
		var allValue =getSelectedIds();
		if(allValue.length ==0||allValue==""){
			$.messageBox({level:'warn',message:"请选择一条或多条未认领记录，再进行操作！"});
			 return true;
		}
	}
	
	function createClaimDlg(allValue,from){
		var title="";
		if("FROM_STEP"==from){
			title = "分步";
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 300,
			height: 300,
			title:title+"认领${moduleCName}信息",
			url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/claimDispatch.action?tempIds='+allValue+'&tempClassName=${lowerCaseModuleName}&from='+from+'&bigType=${bigType!}',
			buttons: {
				"确定" : function(event){
			      	$("#maintainClaimForm").submit();
			   	},
			   	"关闭" : function(){
			        $(this).dialog("close");
			   	}
			}
		});
	
	}
	$("#claim").click(function(){
		if(checkSelectNum()){
			return;
		}
		if (!isAllUnClaim()) {
			$.messageBox({level:'warn',message:"所选记录中有已认领记录！请选择一条或多条未认领记录，再进行认领！"});
			return;
		}
		if(!hasIdCardNo()){
			$.messageBox({level:'warn',message:"所选记录中有记录的身份证号码为空，请先完善身份证号码后，再进行认领！"});
			return;
		}
		
		createClaimDlg(getSelectedIds(),"FROM_CLAIM_TO_ORG");
	});
	
	function hasIdCardNo(){
		if('${lowerCaseModuleName}' == 'actualCompanyTemp') {
			return true;
		}
		var selectedIds = $("#${lowerCaseModuleName}List").getGridParam("selarrrow");
		for(var i = 0; i < selectedIds.length; i++){
			if($("#${lowerCaseModuleName}List").getRowData(selectedIds[i])["idCardNo"] == ''){
				return false;
			}
		}
		return true;
	}
	
	//分步认领
	$("#stepClaim").click(function(){
		if(checkSelectNum()){
			return;
		}
		if (!isAllUnClaim()) {
			$.messageBox({level:'warn',message:"所选记录中有已认领记录！请选择一条或多条未认领记录，再进行分布认领！"});
			return;
		}
		createClaimDlg(getSelectedIds(),"FROM_STEP");
	});
	////////////////////撤销认领/////////////////////
	$("#unClaim").click(function (){
		var allValue = getSelectedIds();
		if(allValue.length ==0||allValue==""){
			$.messageBox({level:'warn',message:"请选择一条或多条已认领记录，再进行撤销认领！"});
			 return;
		}
		if (!isAllClaim()) {
			$.messageBox({level:'warn',message:"所选记录中有未认领记录！请选择一条或多条已认领记录，再进行撤销认领！"});
			return;
		}
		$.confirm({
			title:"确认撤销认领",
			message:"确认撤销认领吗？一经撤销认领，基础数据相对应数据会被删除！",
			okFunc: function() {
				$.ajax({
					async:false,
					url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/updateTempForUnClaim.action?tempIds='+allValue,
					success:function(responseData){
						$("#${lowerCaseModuleName}").trigger("reloadGrid");
					    $.messageBox({message:"已经成功撤销认领${moduleCName}信息!"});
					    onOrgChanged();
					}
				});
		    }
	    });
	});
	
});
function isAllClaim(){
	var selectedIds = $("#${lowerCaseModuleName}List").getGridParam("selarrrow");
	for(var i = 0; i < selectedIds.length; i++){
		if($("#${lowerCaseModuleName}List").getRowData(selectedIds[i])["claimDetail.importOrganization.id"] == $("#${lowerCaseModuleName}List").getRowData(selectedIds[i])["organization.id"]){
			return false;
		}
	}
	return true;
}

function isAllUnClaim(){
	var selectedIds = $("#${lowerCaseModuleName}List").getGridParam("selarrrow");
	for(var i = 0; i < selectedIds.length; i++){
		if($("#${lowerCaseModuleName}List").getRowData(selectedIds[i])["claimDetail.claimState"] == '已认领'){
			return false;
		}
	}
	return true;
}

function viewDialog(event,id){
		$("#${lowerCaseModuleName}Dialog").createDialog({
		width:dialogWidth,
		height:580,
		title:"查看${moduleCName}信息",
		url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=view&id='+id+'&dailogName=${lowerCaseModuleName}Dialog',
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}



function doubleClickTable(event,selectedId){
	if(selectedId==null){
		 return;
	}
	viewDialog(event,selectedId);
}

function getSelectedIds(){
	var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function afterLoad(){
	disableButtons();
}

function disableButtons(){
	
}

function selectRow(){
	
}

//重复数据修改
function dispatchOperateOrClaimById(selectedId,idcardNo,claimOrgId){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		width:1000,
		height:550,
		title:"修改${moduleCName}",
		url:"${path}/dataManage/${lowerCaseModuleName}Manage/dispatchToMainTempAndTarget.action?mode=edit&tempId="+selectedId+"&idcardNo="+idcardNo+"&targetOrgId="+claimOrgId,
		buttons :{
			"保存并认领" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}


//修改
function updateTempById(event,id,from){
	var ent =  $("#${lowerCaseModuleName}List").getRowData(id);
	if(ent.claimState==<@s.property value="@com.tianque.plugin.dataManage.util.Constants$ClaimState@CLAIMED"/>){
		$.messageBox({level:'warn',message:"该${moduleCName}信息已经认领，不能修改!"});
		return;
	}
	if("FROM_LIST"==from){
		//$("#targetClaimOrgId").val(ent['organization.id']);
		$("#targetClaimOrgId").val("");
	}
	if("${bigType}"=="location"){
		locationDialog(event,id,from);
	}else if("${bigType}"=="dustbin"){
		dustbinDialog(event,id,from);
	}else if("${bigType}"=="builddatas"){
		builddatasDialog(event,id,from);
	}else if("${lowerCaseModuleName}"== "unsettledPopulationTemp"){
		unsettledPopulationDialog(event,id,from);
	}else if("${lowerCaseModuleName}"== "overseaPersonnelTemp"){
		overseaPersonnelTempDialog(event,id,from);
	}else if("${bigType}"=="house"){
		houseDialog(event,id,from);
	}else{
		populationDialog(event,id,from);
	}

}
function populationDialog(event,id,from){
$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"population",
			id:id
		},
		tabs:[
			{title:'基本信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateBase&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()},
			{title:'<#if moduleName=="HouseholdStaffTemp">户籍信息<#elseif moduleName=="FloatingPopulationTemp">流入信息<#else>业务信息</#if>',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateBusiness&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}

function houseDialog(event,id,from){
$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"location",
			id:id
		},
		tabs:[
			{title:'房屋信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateBaseHouse&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}
function locationDialog(event,id,from){
$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"location",
			id:id
		},
		tabs:[
			{title:'基本信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateLocation&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}
function dustbinDialog(event,id,from){
$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"location",
			id:id
		},
		tabs:[
			{title:'基本信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateDustbin&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}
function builddatasDialog(event,id,from){
	$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 600,
			height: 300,
			title:'修改${moduleCName}',
			url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=updateBuilddatas&id='+id+'&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val(),
			buttons:{
				"保存": function(event){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			}
		});
}
function unsettledPopulationDialog(event,id,from){
	$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"population",
			id:id
		},
		tabs:[
			{title:'基本信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/specialDispatch.action?mode=update&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}

function overseaPersonnelTempDialog(event,id,from){
$("#${lowerCaseModuleName}Dialog").createTabDialog({
		title:'修改${moduleCName}',
		saveButton:'exist',
		postData:{
			type:'${lowerCaseModuleName}',
			imageType:"population",
			id:id
		},
		tabs:[
			{title:'基本信息',url:'${path}/plugin/dataManage/${lowerCaseModuleName}Manage/dispatch.action?mode=update&dailogName=${lowerCaseModuleName}Dialog&tempClassName=${lowerCaseModuleName}&from='+from+'&targetOrgId='+$("#targetClaimOrgId").val()}
		],
		close : function(){
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}
	});
}
//删除
function deleteOperator(event,allValue){
	if(!allValue){
		var allValue = getSelectedIds();
	};
	if(allValue.length ==0){
		$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
		 return;
	}
	if(!isAllUnClaim()){
		$.messageBox({level:'warn',message:"所选记录中有可认领或已认领记录！请选择一条或多条可删除记录，再进行删除！"});
		 return;
	}
	var str="确定要删除吗?一经删除将无法恢复";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				async:false,
				url:"${path}/plugin/dataManage/${lowerCaseModuleName}Manage/deleteTempByIds.action",
				type:"post",
				data:{
					"tempIds":allValue+""
				},
				success:function(data){
					if(null==data){
				    	$.messageBox({message:"已经成功删除该${moduleCName}信息!"});
					    disableButtons();
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				    }else{
				    	$.messageBox({level: "error",message:data});
				    }
				}
			});
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	}
}
//认领提交后调用
function claimSubmitFun(data){
	warnClaimInfo(data);
	moveClaimList(data);
	successClaim(data);
	
}
//认领出现非重复的警告调用
function warnClaimInfo(data){
	if(data.claimState.errorType == "0"){
    	$.messageBox({level: "warn",message:data.claimState.errorInfo});
    }
    if (data.claimState.errorType == "2"){
    	$.messageBox({level: "error",message:data.claimState.errorInfo});
    }
    if(data.claimState.errorType == "3"){
    	$.messageBox({level: "warn",message:data.claimState.errorInfo});
    }
}
//认领出现重复的警告调用
function warnRepeatClaimInfo(data){
	if(data.claimState.errorType == "1"){
		$.confirm({
			title:"确认",
			message:"该身份证号码在"+$("#commonPopulationOrgName").val()+"的基础信息库中已存在,是否继续操作?",
			okFunc: function() {
			    dispatchOperateOrClaimById($("#populationId").val(),$("#idCardNo").val(), $("#populationOrganizationId").val());
		    },
			cancelFunc:function(){
				
			},
			close:function(){
				
			}
	    });
    }
}
//认领返回数据验证通过调用
function successClaim(data){
	if(data.claimState.successful){
	    $.messageBox({message:"修改并认领成功!"});
	    $("#${lowerCaseModuleName}Dialog").dialog("close");
    }
}
//批量认领返回数据验证通过调用
function moveClaimList(data){
	if (isFromResultList) {
        if(data.claimState.successful){
        	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
            $("#successMsg").addRowData(data.population.id,data,"first");
            $("#claimErrorMsg").delRowData(data.population.id);
        }
    }else{
    	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
    }
}

function getSearchName(){
	var searchName="请输入场所名称或地址";
	return searchName;
}

</script>