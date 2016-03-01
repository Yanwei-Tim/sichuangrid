<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("supervisorPerson",request.getParameter("supervisorPerson"));%>
<%
	String moduleName = request.getParameter("moduleName");
	request.setAttribute("moduleName", moduleName);
	request.setAttribute("lowerCaseModuleName", moduleName
			.substring(0,1).toLowerCase()
			+ moduleName.substring(1));
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
%>
<div class="content">
<div class="ui-corner-all" id="nav">
<div class="btnbanner btnbannerData">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div class="ui-widget autosearch">
	<input class="basic-input" type="text" value="请输入${moduleCName }名称" name="searchText" id="searchText" maxlength="18" class="searchtxt"
		 style="width:180px;" onblur="value=(this.value=='')?'请输入${moduleCName }名称':this.value;" onfocus="value=(this.value=='请输入${moduleCName }名称')?'':this.value;" />
	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
</div>
<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
<pop:JugePermissionTag ename="search${moduleName}">
	<a id="search" href="javascript:void(0)"><span><strong
		class="ui-ico-cx"></strong>高级搜索</span></a>
</pop:JugePermissionTag>
<span class="lineBetween "></span>

<pop:JugePermissionTag
	ename="add${moduleName}">
	<a id="add" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="delete${moduleName}">
	<a id="delete" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>批量删除</span></a>
</pop:JugePermissionTag><!-- 
<pop:JugePermissionTag ename="import${moduleName},down${moduleName}">
<a href="javascript:;" class="nav-dropdownBtn"><span>导入|导出</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
</pop:JugePermissionTag> 
	<div class="nav-sub-buttons">
	 <pop:JugePermissionTag
	ename="import${moduleName}">
	<a id="import" href="javascript:void(0)"><span><strong
		class="ui-ico-dr"></strong>Excel导入</span></a>
	</pop:JugePermissionTag> 
	<pop:JugePermissionTag ename="down${moduleName}">
		<a id="export" href="javascript:void(0)"><span><strong
			class="ui-ico-dc"></strong>导出Excel</span></a>
	</pop:JugePermissionTag>
	</div> -->
	<pop:JugePermissionTag ename="cancelAttended${moduleName},attended${moduleName}">
	<a href="javascript:;" class="nav-dropdownBtn"><span>设置状态</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
	</pop:JugePermissionTag>
	<div class="nav-sub-buttons">
	 <pop:JugePermissionTag ename="cancelAttended${moduleName}">
		<a id="cancelEmphasise" href="javascript:void(0)"><span><strong
			class="ui-ico-CancelAttention"></strong>取消关注</span></a>
	</pop:JugePermissionTag> <pop:JugePermissionTag ename="attended${moduleName}">
		<a id="reEmphasise" href="javascript:void(0)"><span><strong
			class="ui-ico-refocusOn"></strong>重新关注</span></a>
	</pop:JugePermissionTag>
	</div>

 	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
 	<pop:JugePermissionTag ename="praise${moduleName}">
		<a id="superviseServiceTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>投诉/表扬</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="view${moduleName}">
		<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
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
<div id="moveDataDialog"></div>
</div>
<script>
var notExecute=new Array();
function onOrgChanged(orgId,isgrid){

	var initParam = {
			"organizationId": orgId,
	    	"location.isEmphasis":false
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
	if('${moduleName}'=='ScenicTraffic'){
		$("#view").show();
	}else{
		$("#view").hide();
	}

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
				if(ent.isEmphasis==true || ent.isEmphasis=="true"){
				$("#${lowerCaseModuleName}List tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}
	function afterLoad(){
		isEmphasisFormatter();
		disableButtons();
		checkExport();
		changeColor();
	}

	function changeColor(){
		if(notExecute==null||notExecute.length==0){
			return;
		}
		for(var i=0;i<notExecute.length;i++){
			var rowData=$("#${lowerCaseModuleName}List").getRowData(notExecute[i]);
				//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.name+"</font></a>";
				//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.idCardNo+"</font></a>";
			$("#${lowerCaseModuleName}List tr[id="+notExecute[i]+"]").css('background','red');
			//$("#"+notExecute[i]+"a").css('color','red');
			$("#${lowerCaseModuleName}List").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}


	function search${moduleName}(){
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}${searchNameSpace}/find${moduleName}sByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		//fateson add 
		var postData=$.extend({"organizationId":getCurrentOrgId(),"orgId":getCurrentOrgId()},$("#search${moduleName}Form").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()});
		}
		$("#${lowerCaseModuleName}List").setPostData(postData);
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入${moduleCName}名称') return;
		var	initParam = {
				 "organizationId":orgId,
				 "search${moduleName}Vo.unitName":conditions,
				 "search${moduleName}Vo.isEmphasis":false
		}
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}${searchNameSpace}/fastSearch.action",
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
		<pop:JugePermissionTag ename="view${moduleName}">
        	ondblClickRow: doubleClickTable,
		</pop:JugePermissionTag>
		onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}toggleEmphasisButton();}
	});
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
		checkExport();
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
						model :"add",
						title:"新增${moduleCName}",
						width: dialogWidth,
						height: 600,
						tabs:[
							{title:'基本信息',url:'${listNameSpace}/dispatchOperate.action?mode=add&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId()}
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

	$("#cancelEmphasise").click(function(event){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId =getSelectedIds();
			var cancelEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行取消关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId[i]);
				if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
					cancelEmphasise.push(selectedId[i]);
					}else{
						temp.push(selectedId[i]);
						}
				}
			selectedId=cancelEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length=2){
				selectedIds=selectedId;
				}
			$("#${lowerCaseModuleName}Dialog").createDialog({
				width:450,
				height:210,
				title:'取消关注提示',
				modal:true,
				url:'${path}/baseinfo/commonPopulation/emphasiseConditionDlg.jsp?locationIds='+selectedIds+'&isEmphasis=true&dailogName=${lowerCaseModuleName}&temp='+temp+'&orgId='+getCurrentOrgId(),
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		//}
	});

	$("#reEmphasise").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		var selectedId = getSelectedIds();
		var reEmphasise=new Array();
		var temp=new Array();
		if(selectedId == null || selectedId.length<=0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再重新关注！"});
			return;
		}
		for(var i=0;i<selectedId.length;i++){
			var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedId[i]);
			if(rowData.isEmphasis==true||rowData.isEmphasis=="true"){
				reEmphasise.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
					}
			}
		selectedId=reEmphasise;
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
					$.messageBox({level:'warn',message:"除选中的红色数据外,其余${moduleCName}重新关注成功 ！ "});
				}
				notExecute=temp;
				$("#${lowerCaseModuleName}List").trigger("reloadGrid");
				toggleButtonState();
				checkExport();
			}
		});
	});
	$("#delete").click(function(event){
		var allValue = getSelectedIds();
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
		var selectedIds = getSelectedIds();
		if(selectedIds==null || selectedIds.length>1 || selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
			return;
		}
		view${moduleName}(selectedId);
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
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=view&id='+id+'&location.id='+id+'&locationType='+$("#_locationType_").val(),
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
			height:400,
			title:'${moduleCName}查询-请输入查询条件',
			url:'${path}${listNameSpace}/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
			buttons: {
		   		"查询" : function(event){
					search${moduleName}();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:"${path}/common/import.jsp?isNew=${param.isNew}&dataType=${lowerCaseModuleName}&dialog=noticeDialog&startRow=6&templates=${upperCaseModuleName}&listName=${lowerCaseModuleName}List",
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
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0){
			var postData = $("#${lowerCaseModuleName}List").getPostData();
			if($("#isLock").val()!=null && $("#isLock").val()!="" ){
				$("#${lowerCaseModuleName}List").setPostData($.extend(postData,{"search${moduleName}Vo.isEmphasis":$("#isLock").val()}));
			}
			$("#${lowerCaseModuleName}MaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出${moduleCName}信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'${lowerCaseModuleName}List',
					downloadUrl:'${path}${searchNameSpace}/download${moduleName}.action'
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
		if(rowData.isEmphasis == 'true'){
			$.messageBox({level:'warn',message:"该条数据已注销!"});
			return;
		}
		var praiseId = getLocationId(rowData);
		$("#${lowerCaseModuleName}Dialog").createDialog({
			title:"新增${moduleCName}投诉表扬",
			width: 650,
			height: 350,
			url:'${listNameSpace}/dispatchOperate.action?mode=praise&dailogName=${lowerCaseModuleName}Dialog&location.id='+praiseId,
			buttons:{
				"保存" : function(event){
					$("#praiseForm").submit();
				},
			   	"关闭" : function(){
			    	$(this).dialog("close");
			   	}
			}
		});
	});
 
})

	function updateOperator(event,selectedId){
		var ent =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(ent.isEmphasis==true||ent.isEmphasis=='true'){
			 $.messageBox({level:'warn',message:"该${moduleCName}信息已经取消关注，不能修改!"});
			 return;
		}
		
	var encryptId=ent.encryptId;
	
		$("#${lowerCaseModuleName}Dialog").createTabDialog({
					postData:{
					mode:'edit',
					id : encryptId
				},
				title:"修改${moduleCName}",
				width: dialogWidth,
				height: 600,
				tabs:[
					{title:'基本信息',url:'${listNameSpace}/dispatchOperateByEncrypt.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog'}
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
		var str="确定要删除吗?一经删除将无法恢复";
		$.confirm({
			title:"确认删除",
			message:str,
			okFunc: function() {
				$.ajax({
					url:"${path}${listNameSpace}/delete${moduleName}ByIds.action",
					type:"post",
					data:{
						"locationIds":id
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该${moduleCName}信息!"});
					    disableButtons();
					    checkExport();
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
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

	function setOtherButtonEnabled(enabled){
		if (enabled){
			$("#update").buttonEnable();
			//$("#view").buttonEnable();
			$("#shift").buttonEnable();
		}else{
			$("#update").buttonDisable();
			//$("#view").buttonDisable();
			$("#shift").buttonDisable();
		}
	}

	function toggleEmphasisButton(){
		var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length<1){
// 			$("#reEmphasise").buttonDisable();
// 			$("#cancelEmphasise").buttonDisable();
			return;
		}
		if(selectedIds.length==1){
			var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedIds[0]);
			if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
// 				$("#cancelEmphasise").buttonDisable();
// 				$("#reEmphasise").buttonEnable();
			}else{
// 				$("#cancelEmphasise").buttonEnable();
// 				$("#reEmphasise").buttonDisable();
				}
			}
		if(selectedIds.length>1){
// 			$("#reEmphasise").buttonEnable();
// 			$("#cancelEmphasise").buttonEnable();
			}
		/*var reEmphasis=false;
		var cancelEmphasis=false;
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#${lowerCaseModuleName}List").getRowData(selectedIds[i]);
			if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
				reEmphasis=true;
			}
			if(rowData.isEmphasis==true || rowData.isEmphasis=="true"){
				cancelEmphasis=true;
			}
		}
		$("#reEmphasise,#cancelEmphasise").buttonDisable();
		if (!reEmphasis || cancelEmphasis){
			$("#reEmphasise").buttonDisable();
		}else{
			$("#reEmphasise").buttonEnable();
		}
		if(!cancelEmphasis || reEmphasis){
			$("#cancelEmphasise").buttonDisable();
		}else{
			$("#cancelEmphasise").buttonEnable();
		}*/
	}

	function checkExport(){
// 		if($("#${lowerCaseModuleName}List").getGridParam("records")>0
// 				|| $("#${lowerCaseModuleName}List").getGridParam("selrow")!=null){
// 			$("#export").buttonEnable();
// 		}else{
// 			$("#export").buttonDisable();
// 		}
	}
	function getSelectedIds(){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function view${moduleName}(selectedId){
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
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=view&id='+id+'&locationType='+$("#_locationType_").val()+'&location.id='+id,
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
		printTitleStr='${moduleCName} — '+getLocationName(rowData);
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:600,
			title:'打印${moduleCName}信息',
			modal : true,
			url:'${path}${listNameSpace}/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=print&locationType='+$("#_locationType_").val()+'&location.id='+id,
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
</script>