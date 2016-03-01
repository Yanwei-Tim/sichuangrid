<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("supervisorPerson",request.getParameter("supervisorPerson"));
request.setAttribute("keyType",request.getParameter("keyType"));
%>

<%
	String moduleName = request.getParameter("moduleName");
	request.setAttribute("moduleName", moduleName);
	if("Enterprise".equalsIgnoreCase(moduleName)) {
		request.setAttribute("lowerCaseModuleName", "safetyProductionKey");
	} else {
		request.setAttribute("lowerCaseModuleName", moduleName
				.substring(0,1).toLowerCase()
				+ moduleName.substring(1));//将School转换成school
	}
	
	request.setAttribute("upperCaseModuleName", moduleName
			.toUpperCase());
	String listNameSpace = request.getParameter("listNameSpace");
	if (null == listNameSpace || "".equals(listNameSpace)) {
		listNameSpace = "/baseinfo/" + moduleName.substring(0,1).toLowerCase()
				+ moduleName.substring(1) + "Manage";
	}
	request.setAttribute("listNameSpace", listNameSpace);

	String searchNameSpace = request.getParameter("searchNameSpace");
	if (null == searchNameSpace || "".equals(searchNameSpace)) {
		searchNameSpace = "/baseinfo/search"+moduleName;
	}
	request.setAttribute("searchNameSpace", searchNameSpace);

	request.setAttribute("moduleCName", request
			.getParameter("moduleCName"));
	
	String enameModuleName = request.getParameter("enameModuleName");
	if("".equals(enameModuleName) || null==enameModuleName){
		request.setAttribute("enameModuleName",moduleName);
	}else{
		request.setAttribute("enameModuleName",enameModuleName);
	}
%>
<div class="content">
<div class="ui-corner-all" id="nav">
<s:if test="!'Dustbin'.equals(#request.moduleName)">
<div class="btnbanner btnbannerData">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div class="ui-widget autosearch">
	<input class="basic-input" type="text" value="请输入${moduleCName }名称" name="searchText" id="searchText" maxlength="18" class="searchtxt"
		style="width:180px;" onblur="value=(this.value=='')?'请输入${moduleCName }名称':this.value;" onfocus="value=(this.value=='请输入${moduleCName }名称')?'':this.value;" />
	<button id="refreshSearchKey"
	class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
</div>
<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
<pop:JugePermissionTag ename="search${enameModuleName}">
	<a id="search" href="javascript:void(0)"><span><strong
		class="ui-ico-cx"></strong>高级搜索</span></a>
</pop:JugePermissionTag>
<span class="lineBetween "></span>
 </s:if>

<pop:JugePermissionTag ename="add${enameModuleName}">
	<a id="add" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="delete${enameModuleName}">
	<a id="delete" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>批量删除</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag
		ename="import${enameModuleName},down${enameModuleName}">
<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	</pop:JugePermissionTag>
	<div class="nav-sub-buttons">
		 <pop:JugePermissionTag
		ename="import${enameModuleName}">
		<a id="import" href="javascript:void(0)"><span><strong
			class="ui-ico-dr"></strong>Excel导入</span></a>
		</pop:JugePermissionTag> <pop:JugePermissionTag ename="down${enameModuleName}">
		<a id="export" href="javascript:void(0)"><span><strong
			class="ui-ico-dc"></strong>导出Excel</span></a>
		</pop:JugePermissionTag>
	</div>
	<pop:JugePermissionTag ename="abolish${enameModuleName},anew${enameModuleName}">
	<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	</pop:JugePermissionTag>
	<div class="nav-sub-buttons">
	<pop:JugePermissionTag ename="abolish${enameModuleName}">
		<a id="isEmphasis" href="javascript:void(0)"><span><strong class="ui-ico-CancelAttention"></strong>取消关注</span></a>
	</pop:JugePermissionTag> <pop:JugePermissionTag ename="anew${enameModuleName}">
		<a id="emphasis" href="javascript:void(0)"><span><strong class="ui-ico-refocusOn"></strong>重新关注</span></a>
	</pop:JugePermissionTag>
	</div>
 	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
 	<pop:JugePermissionTag ename="serviceTeamMemberManagement">
		<a id="superviseServiceTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理治安负责人</span></a>
	</pop:JugePermissionTag>
 	<pop:JugePermissionTag ename="serviceRecordManagement">
		<a id="superviseRecord" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>管理巡场情况</span></a>
	</pop:JugePermissionTag>
	
	<pop:JugePermissionTag ename="transfer${enameModuleName}"> 
	  <a id="shift"  href="javascript:void(0)"   ><span>转移</span></a> 
	</pop:JugePermissionTag>
	
	
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
<table id="${lowerCaseModuleName}List">
</table>
<div id="${lowerCaseModuleName}ListPager"></div>
</div>
<div id="${lowerCaseModuleName}Dialog"></div>
<div id="noticeDialog"></div>
<div id="${lowerCaseModuleName}MaintanceDialog"></div>
<div id="helpPersonnelDialog"></div>
<div id="helpPrecordDialog"></div>
<div id="scanHeaderImage"></div>
<div id="supervisorMaintanceDialog"></div>
<input id="moduleForMember" type="hidden" value="${lowerCaseModuleName}"/>
</div>
<script>
var notExecute=new Array();
function onOrgChanged(orgId,isgrid){
	var initParam = {
			"orgId": orgId,
	    	"location.isEmphasis":0,
			"keyType":'${keyType}'
	}

	if('${moduleName}'=="School"){
		$.extend(initParam,{
			"school.chineseName" : " ",
		   	"school.kind.id" : " ",
		   	"school.type.id" : " ",
			"school.address" : " ",
			"school.isEmphasis" :$("#isLock").val()
		});
	}
	$("#${lowerCaseModuleName}List").setGridParam({
		url:"${path}${listNameSpace}/${lowerCaseModuleName}List.action",
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData(initParam);
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");
}

$(function(){

	$("#superviseServiceTeamMember").click(function(event){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length>1){
			$.messageBox({level : 'warn',message:"同时只能操作一条记录！"});
			 return;
		}
		if(selectedIds==null ||selectedIds =="" || selectedIds.length < 1){
			$.messageBox({level : 'warn',message:"请先选择一条记录，再进行操作！"});
			 return;
		}
		var selectedId = selectedIds;
		var rowData=$("#${lowerCaseModuleName}List").getRowData(selectedId);
		var locationName = getLocationName(rowData);
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:860,
			height:600,
			title:'${moduleCName}治安负责人情况',
			url:'/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?dailogName=${lowerCaseModuleName}Dialog&module=${lowerCaseModuleName}&id='+selectedId,
			postData:{"name":locationName},		
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});
	
	
	function toggleButtonState(){
	  	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setDeleteButtonEnabled(selectedRowCount>0);
	  	setOtherButtonEnabled(selectedRowCount==1);
	  	toggleEmphasisButton();
	}
	function selectedRowIsnotEmphasis(domain){
		return 	domain.isEmphasis==1;
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#${lowerCaseModuleName}List").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#${lowerCaseModuleName}List").getRowData(idCollection[i]);
				if(ent.isEmphasis==1||ent.isEmphasis=='1'||ent.isEmphasis=='true'||ent.isEmphasis==true){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}

	function afterLoad(){
		isEmphasisFormatter();
		disableButtons();
		changeColor();
	}

	function changeColor(){
		if(notExecute==null||notExecute.length==0){
			return;
		}
		for(var i=0;i<notExecute.length;i++){
			var rowData=$("#${lowerCaseModuleName}List").getRowData(notExecute[i]);
			$("#"+notExecute[i]).css('background','red');
			$("#${lowerCaseModuleName}List").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}

	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入${moduleCName}名称') return;
		var initParam = {
				"organizationId":orgId
			}
		if($("#isLock").val()==""){
			initParam = {
					 "organizationId":orgId,
					 "search${moduleName}Vo.unitName":conditions,
					 "orgId":orgId,
					 "search${moduleName}Vo.name":conditions
				}
		}else{
			initParam = {
		             "organizationId":orgId,
					 "search${moduleName}Vo.unitName":conditions,
					 "search${moduleName}Vo.isEmphasis":0,
					 "orgId":orgId,
					 "search${moduleName}Vo.name":conditions,
					 "${lowerCaseModuleName}.chineseName":conditions,
					 "${lowerCaseModuleName}.isEmphasis":0,
					 "search${moduleName}Vo.keyType":'${keyType}'
				}
		}
		var url="";
		if('${moduleName}'=="FireSafetyEnterprise"){
			url="${path}/baseinfo/searchSafetyProductionKey/fastSearch.action";
			$.extend(initParam,{
				"searchSafetyProductionKeyVo.name":conditions,
				"searchSafetyProductionKeyVo.isEmphasis" :0,
				"searchSafetyProductionKeyVo.keyType" :'${keyType}'
			});
		}
		else
			url="${path}${searchNameSpace}/fastSearch.action";
		$("#${lowerCaseModuleName}List").setGridParam({
			url:url,
			datatype: "json",
			page:1
		});
		$("#${lowerCaseModuleName}List").setPostData(initParam);
		$("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}

	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	multiselect:true,
	  	onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
    	loadComplete: function(data){if(afterLoad){afterLoad(data);}},
		<pop:JugePermissionTag ename="view${enameModuleName}">
        	ondblClickRow: doubleClickTable,
		</pop:JugePermissionTag>
		onSelectRow: selectRow
	});
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#add").click(function(){
		if (!isGrid() && '${moduleName}'!="NewSocietyOrganizations"){
			$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
			return;
		}
		if (getCurrentOrgId()==null||getCurrentOrgId()==""){
			$.notice({level:"warn", message:"请先选择一个部门"});
		}else{
			$("#${lowerCaseModuleName}Dialog").createTabDialog({
				title:"新增${moduleCName}",
				width: dialogWidth,
				height: dialogHeight,
				tabs:[
					{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId()+'&keyType=${keyType}'}
					/*<pop:JugePermissionTag ename="serviceTeamMemberManagement">
					,{title:'${supervisorPerson}',url:'/supervisorManage/supervisorInfoManage/dispatchSupervisorInfo.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&population.locationType='+$("#_locationType_").val(),buttons:{<pop:JugePermissionTag ename="serviceRecordManagement">next:true,</pop:JugePermissionTag> save:function(){$("#${lowerCaseModuleName}Dialog").dialog("close")}}}
					</pop:JugePermissionTag>
					<pop:JugePermissionTag ename="serviceRecordManagement">
					,{title:'巡场情况',url:'/baseinfo/serviceRecordManage/dispatchLocation.action?locationType='+$("#_locationType_").val(),buttons:{save:function(){$("#${lowerCaseModuleName}Dialog").dialog("close")}}}
					</pop:JugePermissionTag> */
				],
				close : function(){
					$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				}
			});
		}
	});
	$("#isLock").change(function(){
		$("#searchText").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	$("#searchText").focus(function(){
           this.select();
	 });
	$("#refreshSearchKey").click(function(){$("#searchText").attr("value","请输入${moduleCName }名称");});
	$("#update").click(function(){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){return;}
		var selectedId = getSelectedIdLast();

		if(selectedId==null){
			 return;
		}
		

		updateOperator(event,selectedId);
	});

	$("#isEmphasis").click(function(event){
			var selectedId =getSelectedIds();
			var isEmphasis=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再取消关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var row=$("#${lowerCaseModuleName}List").getRowData(selectedId[i]);
				if(row.isEmphasis==0||row.isEmphasis=="0"||row.isEmphasis=="false"||row.isEmphasis==false){
					isEmphasis.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
				}
			}
			selectedId=isEmphasis;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length==2){
				selectedIds=selectedId;
			}
			$("#${lowerCaseModuleName}Dialog").createDialog({
				width:450,
				height:210,
				title:'取消关注提示',
				modal:true,
				url:'${path}/baseinfo/commonPopulation/emphasiseConditionDlg.jsp?locationIds='+selectedIds+'&isEmphasis=true&dailogName=${lowerCaseModuleName}&type=attention&temp='+temp+"&orgId="+getCurrentOrgId(),
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
	});

	$("#emphasis").click(function(){
		var selectedId = getSelectedIds();
		var emphasis=new Array();
		var temp=new Array();
		if(selectedId == null || selectedId.length<=0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再重新关注！"});
			return;
		}
		for(var i=0;i<selectedId.length;i++){
			var row=$("#${lowerCaseModuleName}List").getRowData(selectedId[i]);
			if(row.isEmphasis==1||row.isEmphasis=="1" ||row.isEmphasis=="true" ||row.isEmphasis==true){
				emphasis.push(selectedId[i]);
			}else{
				temp.push(selectedId[i]);
			}
		}
		selectedId=emphasis;
		if(selectedId==null||selectedId.length==0){
			$.messageBox({level:'warn',message:"没有可重新关注的数据！"});
			return;
		}
		var selectedIds="";
		for(var i=0;i<selectedId.length;i++){
			selectedIds+=selectedId[i]+",";
		}
		$.ajax({
			url:"${path}${listNameSpace}/updateEmphasiseById.action",
			data:{
				"locationIds": selectedIds,
				"location.isEmphasis":false
			},
			success:function(responseData){
				if(null==temp || temp.length==0){
					$.messageBox({message:"${moduleCName}重新关注成功 ！ "});
				}else{
					$.messageBox({level:'warn',message:"除红色选中数据外,其余${moduleCName}重新关注成功 ！ "});
				}
				notExecute=temp;
				$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				toggleButtonState();
			}
		});
	});

	$("#delete").click(function(event){
		var allValue =  $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(event,allValue);
	});

	$("#view").click(function(){
		if($("#view").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewDialog(event,selectedId);
	});

	function doubleClickTable(selectedId){
		var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:600,
			title:"查看${moduleCName}信息",
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?supervisorName='+encodeURIComponent('${supervisorPerson}')+'&superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&mode=view&location.id='+id+'&locationType='+$("#_locationType_").val()+'&keyType=${keyType}&enterprise.id='+id,
			buttons: {
			   "打印" : function(){
				printChoose(selectedId);
	  	   	   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	 }

	$("#reload").click(function(){
		$("#searchText").attr("value","请输入${moduleCName }名称");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#search").click(function(event){
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:650,
			height:380,
			title:'${moduleCName}查询-请输入查询条件',
			url:'${path}${listNameSpace}/dispatchOperate.action?mode=search&keyType=${keyType}&organization.id='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
		   			if('${moduleName}'=="FireSafetyEnterprise" || '${moduleName}'=="Enterprise"){
		   				searchSafetyProductionKey();
		   			}else{
		   				search${moduleName}();
		   			}
		   				
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#import").click(function(event){
		var templates="";
		var dataType="";
		var startRow=5;
		if('${moduleName}'=="School"){
			dataType="schoolData";
			templates="SCHOOL";
		}else if('${moduleName}'=="OtherLocale"){
			dataType="otherlocale";
			templates="OTHERLOCALE";
		}else if('${moduleName}'=="SafetyProductionKey"||'${moduleName}'=="FireSafetyEnterprise" || '${moduleName}'=="Enterprise"||'${moduleName}'=="EnterpriseDown"){
			dataType=getDataType();
			templates=getEnterpriseTemplates();
			startRow=6;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:'${path}/common/import.jsp?isNew=1&dataType='+dataType+'&dialog=${lowerCaseModuleName}Dialog&startRow='+startRow+'&templates='+templates+'&enterpriseType=${keyType}&listName=${lowerCaseModuleName}List',
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
		var url="";
		if('${moduleName}'=="SafetyProductionKey"||'${moduleName}'=="FireSafetyEnterprise" || '${moduleName}'=="Enterprise"){
			url="${path}/baseinfo/searchSafetyProductionKey/downloadEnterprise.action";
		}else {
			url="${path}${searchNameSpace}/download${moduleName}.action";
		}
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0){ 
			$("#${lowerCaseModuleName}MaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出${moduleCName}信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'${lowerCaseModuleName}List',
					downloadUrl:url
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
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			return;
		}
	});
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});

	$("#superviseRecord").click(function(event){
		var selectedId = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedId.length==0){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
			return;
		}else if(selectedId.length>1){
			$.messageBox({level:'warn',message:"请选择单条记录进行操作！"});
			return;
		}
		var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId);
		var locationName = getLocationName(rowData);
	
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:900,
			height:560,
			title:'${moduleCName}巡场情况',
			url:'/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?dailogName=${lowerCaseModuleName}Dialog&populationType='+$("#_locationType_").val()+'&name='+encodeURIComponent(locationName)+'&id='+selectedId+'&showRecordType=1',
			postData:{"location.name":locationName},
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});

	$("#shift").click(function(event){
		if($("#shift").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedIdLast();
		if (!isGrid()){
			$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行转移！"});
			return;
		}
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
			return;
		}
		var rowData_Popu=$("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(rowData_Popu.isEmphasis==1){
			$.messageBox({level:'warn',message:"所选记录中存在已取消关注（或已注销、死亡）记录，无法转移！"});
			 return;
		}
		var locationIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 300,
			height: 300,
			title:"转移${moduleCName}信息",
			url:'${path}${listNameSpace}/shiftDispatch.action?orgId='+getCurrentOrgId()+'&keyType=${keyType}'+'&enterPriseIds='+locationIds+'&locationIds='+locationIds+'&otherLocaleIds='+locationIds,
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

	function selectRow(){
		var selectedId = $("#${lowerCaseModuleName}List").getGridParam("selrow");
		var selectedCounts = getActualjqGridMultiSelectCount("${lowerCaseModuleName}List");
		var count = $("#${lowerCaseModuleName}List").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("${lowerCaseModuleName}List", true);
		}else{
			jqGridMultiSelectState("${lowerCaseModuleName}List", false);
		}
// 		$("#delete").buttonEnable();
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var enterpriseNew = $("#${lowerCaseModuleName}List").getRowData(id);
			$("#view").buttonEnable();
			$("#update").buttonEnable();
// 			$("#superviseRecord").buttonEnable();
			if(enterpriseNew.isEmphasis!=1){
// 				$("#isEmphasis").buttonEnable();
// 				$("#emphasis").buttonDisable();
			}else{
// 				$("#emphasis").buttonEnable();
// 				$("#isEmphasis").buttonDisable();
			}
		}else if(selectedCounts>1){
			$("#view").buttonDisable();
			$("#update").buttonDisable();
// 			$("#emphasis").buttonEnable();
// 			$("#isEmphasis").buttonEnable();
// 			$("#superviseRecord").buttonDisable();
			}
		if(isGrid()){
// 			$("#shift").buttonEnable();
		}else{
// 			$("#shift").buttonDisable();
		}
		if(selectedCounts==0){
// 			$("#delete").buttonDisable();
// 			$("#shift").buttonDisable();
// 			$("#emphasis").buttonDisable();
// 			$("#isEmphasis").buttonDisable();
// 			$("#superviseRecord").buttonDisable();
		}
	}

	function searchSafetyProductionKey(){
		/*
		var conditionName = $("#conditionName").val();
		var conditionTypeId = $("#conditionTypeId").val();
		var conditionLegalPerson = $("#conditionLegalPerson").val();
		var conditionBusinessLicense = $("#conditionBusinessLicense").val();
		var conditionIndustryId = $("#conditionIndustryId").val();
		//面积区间
		var conditionMinArea = $("#conditionMinArea").val();
		var conditionMaxArea = $("#conditionMaxArea").val();
		
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
		*/
		// 是否危险企业（默认为否）
		var conditionIsRiskEnterprise = $("#conditionIsRiskEnterprise").val();

		var url="";
		if('${moduleName}'=="FireSafetyEnterprise" || '${moduleName}'=="Enterprise"){
			url="${path}/baseinfo/searchSafetyProductionKey/searchSafetyProductionKey.action";
		}else {
			url="${path}${searchNameSpace}/search${moduleName}.action";
		}
		$("#${lowerCaseModuleName}List").setGridParam({
			url:url,
			datatype: "json",
			page:1,
			mtype:"post"
		});
        //统统使用.serializeObject() 来设置
		var postData=$.extend({"orgId":getCurrentOrgId()},$("#searchEnterpriseForm").serializeObject());
		/*
		var postData={
				"orgId":getCurrentOrgId(),
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
				"enterpriseSearchCondition.keyType":'${keyType}',
				"enterpriseSearchCondition.isEmphasis":$("#isLock").val()
		};
		*/
		/** 是危险企业（默认为否） */
		if(conditionIsRiskEnterprise != ""){
			$.extend(postData,{"enterpriseSearchCondition.isRiskEnterprise":conditionIsRiskEnterprise});
		}
		$("#${lowerCaseModuleName}List").setPostData(postData);
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	 function searchSchool() {
			var schoolChineseName=$('#schoolChineseName').val();
			var schoolKindId=$('#conditionKindId').val();
			var schoolTypeId=$('#conditionTypeId').val();
			var schoolAddress=$('#schoolAddress').val();
			var schoolEnglishName = $("#schoolEnglishName").val();
			var schoolWebSite = $("#schoolWebSite").val();
			var schoolPresident = $("#schoolPresident").val();
			var schoolFax = $("#schoolFax").val();
			var schoolEmail = $("#schoolEmail").val();
			var schoolPersonLiable = $("#schoolPersonLiable").val();
			var schoolPersonLiableTelephone = $("#schoolPersonLiableTelephone").val();
			var schoolPersonLiableMobileNumber = $("#schoolPersonLiableMobileNumber").val();
			var schoolMinAtSchoolHeadcount = $("#schoolMinAtSchoolHeadcount").val();
			var schoolMaxAtSchoolHeadcount = $("#schoolMaxAtSchoolHeadcount").val();
			var hasServiceTeamMember = $("#searchHasServiceTeamMember").val();
			var hasServiceRecord = $("#searchHasServiceRecord").val();
			
			var postData={
				"orgId":getCurrentOrgId(),
				"school.chineseName" : schoolChineseName,
			   	"school.kind.id" : schoolKindId,
			   	"school.type.id" : schoolTypeId,
				"school.address" : schoolAddress,
				"school.englishName":schoolEnglishName,
				"school.webSite":schoolWebSite,
				"school.president":schoolPresident,
				"school.fax":schoolFax,
				"school.email":schoolEmail,
				"school.personLiable":schoolPersonLiable,
				"school.personLiableTelephone":schoolPersonLiableTelephone,
				"school.personLiableMobileNumber":schoolPersonLiableMobileNumber,
				"school.minAtSchoolHeadcount":schoolMinAtSchoolHeadcount,
				"school.maxAtSchoolHeadcount":schoolMaxAtSchoolHeadcount,
				"school.hasServiceTeamMember":hasServiceTeamMember,
				"school.hasServiceRecord":hasServiceRecord,
				"school.isEmphasis":$("#isLock").val()==1?true:false
			};
			if($("#isLock").val()==-1){
				var postData={
						"orgId":getCurrentOrgId(),
						"school.chineseName" : schoolChineseName,
					   	"school.kind.id" : schoolKindId,
					   	"school.type.id" : schoolTypeId,
						"school.address" : schoolAddress,
						"school.englishName":schoolEnglishName,
						"school.webSite":schoolWebSite,
						"school.president":schoolPresident,
						"school.fax":schoolFax,
						"school.email":schoolEmail,
						"school.personLiable":schoolPersonLiable,
						"school.personLiableTelephone":schoolPersonLiableTelephone,
						"school.personLiableMobileNumber":schoolPersonLiableMobileNumber,
						"school.minAtSchoolHeadcount":schoolMinAtSchoolHeadcount,
						"school.hasServiceTeamMember":hasServiceTeamMember,
						"school.hasServiceRecord":hasServiceRecord,
						"school.maxAtSchoolHeadcount":schoolMaxAtSchoolHeadcount
					};
			}

			$("#${lowerCaseModuleName}List").setGridParam({
					        url:'${path}${searchNameSpace}/search${moduleName}.action',
					        datatype: "json"
		 	});
		 	
			$("#${lowerCaseModuleName}List").setPostData(postData);
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}

	 function searchOtherLocale(){
			var	nameCondition=$("#conditionName").val();
			var	addressCondition=$("#conditionAddress").val();
			var	typeCondition=$("#conditionType").val();
			var contactPersonCondition=$("#conditionContactPerson").val();
			var telephoneCondition=$("#conditionTelephone").val();
			var mobileNumberCondition=$("#conditionMobileNumber").val();
			var hasServiceTeamMember = $("#searchHasServiceTeamMember").val();
			var hasServiceRecord = $("#searchHasServiceRecord").val();
			$("#${lowerCaseModuleName}List").setGridParam({
				url:'${path}${searchNameSpace}/search${moduleName}.action',
				datatype: "json",
				page:1
			});
			$("#${lowerCaseModuleName}List").setPostData({
				"orgId":getCurrentOrgId(),
		    	"organization.id":getCurrentOrgId(),
		    	"searchOtherLocaleVo.name":nameCondition,
		    	"searchOtherLocaleVo.address":addressCondition,
		    	"searchOtherLocaleVo.typeId":typeCondition,
		    	"searchOtherLocaleVo.contactPerson":contactPersonCondition,
		    	"searchOtherLocaleVo.telephone":telephoneCondition,
		    	"searchOtherLocaleVo.mobileNumber":mobileNumberCondition,
		    	"searchOtherLocaleVo.hasServiceTeamMember":hasServiceTeamMember,
				"searchOtherLocaleVo.hasServiceRecord":hasServiceRecord,
		    	"searchOtherLocaleVo.isEmphasis":$("#isLock").val()
		   	});
			$("#${lowerCaseModuleName}List").trigger("reloadGrid");
		}

	function getEnterpriseTemplates(){
		var enterpriseTemplates='';
		if('${keyType}' == 'enterpriseKey'){
			enterpriseTemplates="ENTERPRISEKEY";
		}else if('${keyType}' == 'protectionKey'){
			enterpriseTemplates="PROTECTIONKEY";
		}else if('${keyType}' == 'safetyProductionKey'){
			enterpriseTemplates="SAFETYPRODUCTIONKEY";
		}else if('${keyType}' == 'fireSafetyKey'){
			enterpriseTemplates="FIRESAFETYKEY";
		}else if('${keyType}' == 'securityKey'){
			enterpriseTemplates="SECURITYKEY";
		}else if('${keyType}' == 'enterpriseDownKey'){
			enterpriseTemplates="ENTERPRISEDOWNKEY";
		}
		return enterpriseTemplates;
	}

	function getDataType(){
		var dataType="";
		if('${keyType}' == 'enterpriseKey'){
			dataType="enterprise";
		}else if('${keyType}' == 'protectionKey'){
			dataType="protection";
		}else if('${keyType}' == 'safetyProductionKey'){
			dataType="safetyProduction";
		}else if('${keyType}' == 'fireSafetyKey'){
			dataType="fire";
		}else if('${keyType}' == 'securityKey'){
			dataType="security";
		}else	if('${keyType}' == 'enterpriseDownKey'){
			dataType="enterpriseDown";
		}
		return dataType;
	}

})

	function updateOperator(event,selectedId){
		var ent = $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(ent.isEmphasis==true||ent.isEmphasis=='true'){
			 $.messageBox({level : 'warn',message:"该${moduleCName}信息已经注销，不能修改!"});
			 return;
		}
		
	
		var encryptId=ent.encryptId;
		$("#${lowerCaseModuleName}Dialog").createTabDialog({
			title:"修改${moduleCName}",
			width: dialogWidth,
			height: dialogHeight,
			postData:{
				id : encryptId,
				mode:'edit'
			},
			tabs:[
					{title:'基本信息',url:'${listNameSpace}/dispatchOperateByEncrypt.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog'+'&keyType=${keyType}'}
					/* <pop:JugePermissionTag ename="serviceTeamMemberManagement">
					,{title:'${supervisorPerson}',url:'/supervisorManage/supervisorInfoManage/dispatchSupervisorInfo.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&location.id='+selectedId+'&population.locationType='+$("#_locationType_").val(),buttons:{<pop:JugePermissionTag ename="serviceRecordManagement">next:true,</pop:JugePermissionTag> save:function(){$("#${lowerCaseModuleName}Dialog").dialog("close")}}}
					</pop:JugePermissionTag>
					<pop:JugePermissionTag ename="serviceRecordManagement">
						,{title:'巡场情况',url:'/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?dailogName=${lowerCaseModuleName}Dialog&populationType='+$("#_locationType_").val()+'&name='+getLocationName(ent)+'&id='+selectedId}
					</pop:JugePermissionTag> */
				],
			close : function(){
				$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				}
		});
			var evt = event || window.event;
			if (window.event) { 
			evt.cancelBubble=true; 
			} else { 
			evt.stopPropagation(); 
			} 
	}

	function deleteOperator(event,allValue){
		var id=deleteOperatorByEncrypt("${lowerCaseModuleName}",allValue,"encryptId");
		var str = "该${moduleCName}信息删除后,将无法恢复,您确认删除该${moduleCName}信息吗?";
		$.confirm({
			title:"确认删除",
			message:str,
			width: 400,
			okFunc: function(){
				var url="";
				if('${moduleName}'=="FireSafetyEnterprise"){
					url="${path}/baseinfo/safetyProductionKeyManage/deleteEnterprise.action";
				}else {
					url="${path}${listNameSpace}/delete${moduleName}.action";
				}
				$.ajax({ 
					url:url,
					type:"post",
					data:{
						"locationIds":id
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
					    $("input[role='checkbox']").attr("checked",false);
					    disableButtons();
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
					}
				});
			}
		})
		var evt = event || window.event;
		if (window.event) { 
		evt.cancelBubble=true; 
		} else { 
		evt.stopPropagation(); 
		} 
	}

	function relatePlace(placeIds){
		var str="";
		var url="";
		if('${moduleName}'=="FireSafetyEnterprise"){
			url="${path}/baseinfo/safetyProductionKeyManage/hasRelatePlace.action?locationIds="+placeIds;
		}else {
			url="${path}${listNameSpace}/hasRelatePlace.action?locationIds="+placeIds;
		}
		$.ajax({
			async:false,
			url: url,
			success:function(responseData){
				if(responseData){
					str="有${moduleCName}被引用,要删除未被引用的${moduleCName}吗?一经删除将无法恢复!";
				}else{
					str="该${moduleCName}信息删除后,将无法恢复,您确认删除该${moduleCName}信息吗?";
				}
			}
		});
		return str;
	}

	function delete${moduleName}(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		var url="";
		if('${moduleName}'=="FireSafetyEnterprise"){
			url="${path}/baseinfo/safetyProductionKeyManage/deleteEnterprise.action?enterPriseIds="+allValue;
		}else {
			url="${path}${listNameSpace}/delete${moduleName}.action?enterPriseIds="+allValue+"&schoolIds="+allValue+"&otherLocaleIds="+allValue;
		}
		$.ajax({
			url:url,
			success:function(data){
			    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
			    $("input[role='checkbox']").attr("checked",false);
			    disableButtons();
				$("#${lowerCaseModuleName}List").trigger("reloadGrid");
			}
		});
	}

	function getSelectedIds(){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}


	function viewDialog(event,selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:600,
			title:"查看${moduleCName}信息",
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?superviceRecordName='
					+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')
					+'&mode=view&locationType='+$("#_locationType_").val()+'&location.id='+id
					+'&keyType=${keyType}',
			buttons: {
				"打印" : function(){
					printChoose(selectedId);
	  	   		},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
		var evt = event || window.event;
		if(typeof evt!="object"){return false;}
		if (window.event) { 
		evt.cancelBubble=true; 
		} else { 
		evt.stopPropagation(); 
		} 
	}
	
	function printChoose(selectedId){
		$("#printOptionsDialog").createDialog({
			width: 300,
			height: 200,
			title:'选择打印内容',
			modal : true,
			url:'${path}/baseinfo/commonPopulation/printTabChooseDlg.jsp',
			buttons: {
				"确定" : function(){
					print(selectedId);
		   		},
			   "关闭" : function(){	
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	var printTitleStr='';
	function print(selectedId){
		var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		printTitleStr='${moduleCName} — '+rowData.name;
		// add by zl at 2013/08/15 18:10
		if('school' == '${lowerCaseModuleName}'){
			printTitleStr='${moduleCName} — '+rowData.chineseName;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:600,
			title:'打印${moduleCName}信息',
			modal : true,
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?supervisorName='+encodeURIComponent('${supervisorPerson}')+'&superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&mode=print&location.id='+id+'&locationType='+$("#_locationType_").val()+'&keyType=${keyType}&enterprise.id='+id,
			buttons: {
				"打印" : function(){
					$("#printSpaceDiv").printArea();
		        	$(this).dialog("close");
		   		},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function print${moduleName}(selectedId,orgId){
		var url='${path}${listNameSpace}/dispatchOperate.action?mode=print&keyType=${keyType}&location.id='+selectedId+'&orgId=' + orgId;
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title: '打印${moduleCName}信息',
			modal: true,
			url: url,
			buttons: {
				"确定" : function(event){
					if('${moduleName}'=="Enterprise"||'${moduleName}'=="FireSafetyEnterprise")
						$("#enterprisePrint").printArea();
					else
						$("#${lowerCaseModuleName}Print").printArea();
					$(this).dialog("close");
				},
		  		 "关闭" : function(){
		       		 $(this).dialog("close");
		  		 }
			}
		});
	}

	function disableButtons(){
	  	setDeleteButtonEnabled(false);
	  	setOtherButtonEnabled(false);
	  	toggleEmphasisButton();
	}
	function setDeleteButtonEnabled(enabled){
// 		if (enabled){
// 			$("#delete").buttonEnable();
// 		}else{
// 			$("#delete").buttonDisable();
// 		}
	}

	function toggleEmphasisButton(){
		var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<=0){
// 			$("#emphasis").buttonDisable();
// 			$("#isEmphasis").buttonDisable();
			return;
		}
		if(selectedIds.length==1){
			var row=$("#${lowerCaseModuleName}List").getRowData(selectedIds[0]);
			if(row.isEmphasis==1){
// 				$("#emphasis").buttonDisable();
// 				$("#isEmphasis").buttonEnable();
			}else{
// 				$("#emphasis").buttonEnable();
// 				$("#isEmphasis").buttonDisable();
			}
			}
		if(selectedIds.length>1){
// 			$("#isEmphasis").buttonEnable();
// 			$("#emphasis").buttonEnable();
		}
	}

	function setOtherButtonEnabled(enabled){
		if (enabled){
			$("#update").buttonEnable();
			$("#view").buttonEnable();
// 			$("#shift").buttonEnable();
// 			$("#superviseRecord").buttonEnable();
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
// 			$("#shift").buttonDisable();
// 			$("#superviseRecord").buttonDisable();
		}
	}

</script>