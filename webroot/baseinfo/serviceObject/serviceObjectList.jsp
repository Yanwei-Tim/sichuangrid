<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% Long orgId =ThreadVariable.getSession().getOrganization().getId(); %>

<div class="content">
	<input id="orgId" type="hidden" value="<%=orgId %>" />
	<input id="_teamId_" type="hidden" value="${serviceObject.serviceTeamId}"/>
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
	    	<select name="conditonSelected" id="conditonSelected">
	       		<option value="idCardNo" selected="selected">身份证号码</option>
	       		<option value="name">姓名</option>
	    	</select>
	    	<input type="text" value="请输入<s:text name="list.search.button.name"/>条件" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入<s:text name="list.search.button.name"/>条件':this.value;" onfocus="value=(this.value=='请输入<s:text name="list.search.button.name"/>条件')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span><s:text name="list.search.button.name"/></span></a>
	</div>

	<div class="ui-corner-all" id="nav">
		<a id="addServiceObject" href="javascript:void(0)"><span>新增</span></a>
		<a id="searchServiceObject" href="javascript:void(0)"><span>查询</span></a>
		<a id="exportServiceObject" href="javascript:void(0)"><span>导出</span></a>
		<a id="viewServiceObject" href="javascript:void(0)"><span>查看</span></a>
		<a id="deleteServiceObject" href="javascript:void(0)"><span>移除</span></a>
		<a id="maintainServicer" href="javascript:void(0)"><span>维护服务成员</span></a>
		<a id="maintainRecordForObject" href="javascript:void(0)"><span>维护服务记录</span></a>
		
		<!-- <a id="reload" href="javascript:void(0)"><span>刷新</span></a> -->
	<pop:JugePermissionTag ename="addServiceObject">
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="deleteServiceObject">
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="searchServiceObject">
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="downloadServiceObject">
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="viewServiceObject">
</pop:JugePermissionTag>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
<table id="serviceObjectList"></table>
<div id="serviceObjectListPager"></div>
</div>
<div id="serviceObjectDialog"></div>
<div id="serviceObjectViewDlg"></div>
</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="ServiceObjectManagement">
	<span id="title"><s:property value="#request.name" /></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var dialogWidth=800;
var dialogHeight=480;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();

$(document).ready(function(){
	$("#serviceObjectList").jqGridFunction({
		datatype: "local",
		colNames:['sid','populationId','姓名','所属网格','性别','身份证号码','人员类型En','人员类型','服务者','服务记录','最新接受服务时间'],
	   	colModel:[
			{name:"sid",index:"sid",hidden:true,key:true,frozen:true},
	        {name:"populationId",index:"populationId",hidden:true,frozen:true},
	        {name:"name",index:'name',width:100,sortable:false},
	        {name:"organization.orgName",index:"organization.orgName",sortable:false},
		    {name:'gender',formatter:genderFormatter,sortable:true,width:70},
		    {name:'idCardNo',index:'idCardNo',width:130,formatter:idCardNoFont,sortable:false,hidden:true},
		    {name:'populationType',index:'populationType',hidden:true,width:70,sortable:false,frozen:true},
		    {name:'attentionPopulationTypeCname',index:'attentionPopulationTypeCname',width:70,sortable:false,frozen:true},
		    {name:'serviceMembers',index:'serviceMembers',formatter:servicerFont,sortable:false},
		    {name:'serviceRecord',index:'serviceRecord',width:100,sortable:false,frozen:true,hidden:true},
	        {name:'lastestServiceRecordDate',index:'lastestServiceRecordDate',sortable:false}
		],
		height:285,
		multiselect:true,
		ondblClickRow: doubleClickTable,
		onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
	  	onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}},
	  	loadComplete: function(data){if(afterLoad){afterLoad(data);}}
	});
	initParam = {"searchServiceObjectVo.serviceTeamId":$("#_teamId_").val()};
	$("#serviceObjectList").setGridParam({
		url:"${path}/baseinfo/serviceTeam/serviceObject/serviceObjectList.action",
		datatype: "json",
		page:1
	});
	$("#serviceObjectList").setPostData(initParam);
	$("#serviceObjectList").jqGrid('setFrozenColumns');
	$("#serviceObjectList").trigger("reloadGrid");
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","");
	});
$("#addServiceObject").click(function(event){
	$("#serviceObjectDialog").createDialog({
		width: 600,
		height: 435,
		title:"新增-自治组织服务对象",
		url:'${path}/baseinfo/serviceObject/serviceObjectDialog.jsp?serviceTeamId='+$("#_teamId_").val(),
		buttons: {
			"确定" : function(event){
				addServiceObject();
   				},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
  });
  $("#viewServiceObject").click(function(event){
	  var selectedId = getSelectedId();
	  if(selectedId==null||selectedId==""){
	 		return;
		}
	  viewDialog(selectedId);
	});	
  
	function doubleClickTable(selectedId){
		var rowData = $("#serviceObjectList").getRowData(selectedId);
		if(rowData==null){
		 	return;
		}
		$("#serviceObjectViewDlg").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看服务对象信息",
			url:"${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=view&populationId="+rowData.populationId+"&populationType="+rowData.populationType+"&serviceTeamId="+$("#_teamId_").val(),
			buttons: {
		  		 "关闭" : function(){
		        	$(this).dialog("close");
		   			}
			}
		});
	}
	
	$("#deleteServiceObject").click(function(){
		var selectedIds = getSelectedIds();
		if(selectedIds==null||selectedIds==""){
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:'${path}/baseinfo/serviceTeam/serviceObject/deleteServiceObject.action?selectedIds='+selectedIds+'&serviceObject.serviceTeamId='+$("#_teamId_").val(),
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删除服务对象!"});
							$("#serviceObjectList").trigger("reloadGrid");
						}else{
							$.messageBox({message:"删除失败，请清空组织成员，服务对象，服务记录后再删除组织!"});
						}
					}
				});
			}
		});
	});

	$("#maintainRecordForObject").click(function(){
		var serviceOjectValue = '';
		$.each($("#serviceObjectList").jqGrid("getGridParam", "selarrrow"),function(index,val){
				var row = $("#serviceObjectList").getRowData(val);
				serviceOjectValue = serviceOjectValue.concat(row.populationId + "-" + row.name + "-" + row.populationType).concat(",");		
		});
		serviceOjectValue = serviceOjectValue.substr(0, serviceOjectValue.length - 1);
		$("#serviceObjectDialog").createDialog({
			width: 600,
			height: 450,
			title: '维护服务记录',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=add&fromSource=fromObject&serviceRecord.manageTeam.id='+$("#_teamId_").val()+'&serviceRecord.serviceObjects='+serviceOjectValue,
			buttons: {
		   		"保存" : function(event){
					$("#serviceRecord_form").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	function viewDialog(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#serviceObjectList").getRowData(selectedId);
		if(rowData==null){
			 return;
		}
		$("#serviceObjectViewDlg").createDialog({
			width: 600,
			height: 450,
			title:"查看服务对象信息",
			url:"${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=view&populationId="+rowData.populationId+"&populationType="+rowData.populationType+"&serviceTeamId="+$("#_teamId_").val(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	$("#maintainServicer").click(function(event){
		var selectedIds =  getSelectedIds();
		if(selectedIds==null||selectedIds==""){
	 		return;
		}
		var orgId =$("#orgId").val();
		$("#serviceObjectDialog").createDialog({
			width: 850,
			height: 550,
			title:'维护成员信息-新增',
			url:'${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=deal&orgId='+orgId+'&serviceTeamId='+$("#_teamId_").val()+'&selectedIds='+selectedIds,
			buttons: {
				"确定" : function(){
					addServers();
		        	$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#exportServiceObject").click(function(event){
		if($("#serviceObjectList").getGridParam("records")>0){
			var postData = $("#serviceObjectList").getPostData();
			$("#serviceObjectList").setPostData(postData);
			$("#serviceObjectDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出服务成员信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'serviceObjectList',
					downloadUrl:'${path}/baseinfo/serviceTeam/searchServiceObject/downloadServiceObject.action'
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
			return;
		}
	});
	
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());

	});
	$("#searchServiceObject").click(function(){
		search(getCurrentOrgId());
		var orgId =$("#orgId").val();
		$("#serviceObjectDialog").createDialog({
			width: 600,
			height: 260,
			title:'查找服务对象',
			url:'${path}/baseinfo/serviceObject/searchServiceObjectDialog.jsp?orgId='+orgId+'&serviceTeamId='+$("#_teamId_").val(),
			buttons: {
		   		"查询" : function(event){
		   			searchServiceObject();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});

	});
});
function searchServiceObject(){
	var initParam=$("#searchServiceObjectForm").serializeArray();
	$("#serviceObjectList").setGridParam({
		url:'${path}/baseinfo/serviceTeam/searchServiceObject/searchServiceObjectPopulationInfo.action',
		datatype: "json",
		page:1
	});
	$("#serviceObjectList").setPostData(initParam);
	$("#serviceObjectList").trigger("reloadGrid");
}
function search(orgId){
	var condition = $("#searchText").val();
	var teamId = $("#_teamId_").val();
	if(condition == '请输入检索条件') return;
	var initParam = {
		"organizationId": orgId
	}
	if("idCardNo"==$("#conditonSelected").val()){
			initParam = {
					"searchServiceObjectVo.serviceTeamId": teamId,
					"searchServiceObjectVo.idCardNumber":condition
				}
	}else{
			initParam = {
					"searchServiceObjectVo.serviceTeamId": teamId,
					"searchServiceObjectVo.name":condition
				}
	}
	$("#serviceObjectList").setGridParam({
		url:'${path}/baseinfo/serviceTeam/searchServiceObject/searchServiceObjectPopulationInfo.action',
		datatype: "json",
		page:1
	});
	$("#serviceObjectList").setPostData(initParam);
	$("#serviceObjectList").trigger("reloadGrid");
}
function nameFont(el,options,rowData){
	if(rowData.death == true || rowData.death == "true"){
		return "<font color='red'>"+rowData.name+"</font>";
	}
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<font color='#778899'>"+rowData.name+"</font>";
	}
	return "<font color='#000'>"+rowData.name+"</font>";
}

function idCardNoFont(el,options,rowData){
	if(rowData.logOut==1||rowData.logOut=='1'){
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}else{
		return "<font color='#000'>"+rowData.idCardNo+"</font>";
	}
}
function servicerFont(el,options,rowData){
	if(rowData.length!=0){
		return "<a href='javascript:;' onclick=maintainServicer("+rowData.populationId+",'"+rowData.populationType+"') >详细</a>";
	}else{
		return "<div>"+"无"+"</div>";
	}
}
function maintainServicer(populationId,populationType){
	if((populationId==null||populationId=="")&&(populationType==null||populationType=="")){
 		return;
	}
	var orgId =$("#orgId").val();
	$("#serviceObjectDialog").createDialog({
		width: 850,
		height: 550,
		title:'维护成员信息',
		url:'${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=edit&orgId='+orgId+'&serviceTeamId='+$("#_teamId_").val()+'&serviceObject.populationId='+populationId+'&serviceObject.populationType='+populationType,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function toggleButtonState(){
  	var selectedIds=getSelectedIds();
  	var selectedRowCount=selectedIds.length;
  	setButtonEnabled(selectedRowCount==1);
  	setOtherButtonEnabled(selectedRowCount!=0);
}
function getSelectedIds(){
	var selectedIds=$("#serviceObjectList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function getSelectedId(){
	var selectedIdLast = null;
    var selectedIds = $("#serviceObjectList").jqGrid("getGridParam", "selarrrow");
    
    for(var i=0;i<selectedIds.length;i++){
		selectedIdLast = selectedIds[i];
   }
   return selectedIdLast;
}
function afterLoad(){
	setButtonEnabled();
	setOtherButtonEnabled();
}
function setButtonEnabled(enabled){
	if (enabled){
		$("#viewServiceObject").buttonEnable();
	}else{
		$("#viewServiceObject").buttonDisable();
	}
}
function setOtherButtonEnabled(enabled){
	if (enabled){
		$("#deleteServiceObject").buttonEnable();
		$("#maintainServicer").buttonEnable();
		$("#maintainRecordForObject").buttonEnable();
	}else{
		$("#deleteServiceObject").buttonDisable();
		$("#maintainServicer").buttonDisable();
		$("#maintainRecordForObject").buttonDisable();
	}
}

</script>