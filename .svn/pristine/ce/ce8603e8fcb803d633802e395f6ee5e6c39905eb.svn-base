<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="userState" id="fastSearchSelect">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>"  selected="selected">全部</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>">仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
				</select>
			</div>
		</div>
		<pop:JugePermissionTag ename="searchFourLevelPlatform">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addFourLevelPlatform">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增组织</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteFourLevelPlatform">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="exportFourLevelPlatform">
			<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="maintainPrimaryOrgMembersFourLevelPlatform">
			<a id="maintainPrimaryOrgMembers" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="fourlevelplatformList">
		</table>
		<div id="fourlevelplatformListPager"></div>
	</div>
	<div id="fourlevelplatformDialog"></div>
	<div id="noticeDialog"></div>
	<div id="fourlevelplatformMaintanceDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 320;
var count;
var internalId='';
var currentOrgId=getCurrentOrgId();
// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='editFourLevelPlatform'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteFourLevelPlatform'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}


// 改变组织机构树时加载列
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organization.id": orgId,
		"searchFourLevelPlatformVo.displayLevel":$("#displayLevel").val()
	}
	$("#fourlevelplatformList").setGridParam({
 		url:'${path}/fourLevelPlatformManage/findFourLevelPlatformPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#fourlevelplatformList").setPostData(initParam);
	$("#fourlevelplatformList").trigger("reloadGrid");
}

function nameFont(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewFourLevelPlatform'> <a href='javascript:;' onclick='viewFourlevelplatform("+rowData.id+");'></pop:JugePermissionTag>"+rowData.name+"<pop:JugePermissionTag ename='viewFourLevelPlatform'></a> </pop:JugePermissionTag>";
}

function viewMemberFormatter(el, options, rowData){
	if(rowData.memberNum==0){
		return "0";
	}else{
		return "<pop:JugePermissionTag ename='viewFourLevelPlatform'> <a href='javascript:;' onclick='viewFourlevelplatform("+rowData.id+");'><U><font color='#6633FF'></pop:JugePermissionTag>"+rowData.memberNum+"<pop:JugePermissionTag ename='viewFourLevelPlatform'></font></U></a> </pop:JugePermissionTag>";
	}
}


$(function(){
	if(isGrid()){
		$("#fastSearchSelect").hide();
	}
	function toggleButtonState(){
	  	var selectedIds=$("#fourlevelplatformList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}
	
	// 生成列
	$("#fourlevelplatformList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		sortname: "orgId",
		sortorder: "asc",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"organization.id",index:"orgId",hidden:true,hidedlg:true},
			{name:"encryptId",index:'id',frozen:true,hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	    	{name:'name-Font',index:'name',label:'平台名称',sortable:true,width:260,align:"center",formatter:nameFont},
	    	{name:'name', index:'name',sortable:false,hidden:true,hidedlg:true}, 	
	    	{name:'teamTypeName', index:'teamType',label:'组织类型', width:120, sortable:true, align:'center', hidden:false},
	    	{name:'memberNum',index:'memberNum',label:'成员数',sortable:true,width:60,align:"center",formatter:viewMemberFormatter},
	    	{name:'organization.orgName',index:'orgId',label:'所属区域',sortable:true,align:"center",width:360},
	    	{name:'fourthAccount',index:'fourthAccount',label:'参与管理数',sortable:false,width:70,align:"center"},
	    	{name:'remark', index:'remark',label:'备注', width:310, sortable:true, align:'center', hidden:false}	,
	    	//{name:'seq', index:'seq',label:'排序', width:100, sortable:true, align:'center', hidden:false}, 	
	    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
	    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true}, 
	    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
	    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true}
	   	],
	  	multiselect:true,
	  	onSelectAll:function(data){
	  		toggleButtonState(data);
	  	},
    	loadComplete: function(data){
    		afterLoad(data);
    	},
	   	<pop:JugePermissionTag ename="viewFourLevelPlatform">
	    ondblClickRow: viewFourlevelplatform,
	    </pop:JugePermissionTag>
		onSelectRow: function(data){
			toggleButtonState(data);
		}
	});
	jQuery("#fourlevelplatformList").jqGrid('setFrozenColumns');
	
	$("#searchText").focus(function(){
        this.select();
	 });
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入四级体系建设名称");
	});
	$("#add").click(function(){
		count = countFourLevelPlatform();
		if(count==1){
			$.messageBox({level:"warn",message:"该组织机构下已存在一条数据，不能进行新增！"});
			return;
		}
		if(!($("#currentOrgId").attr("orgLevelInternalId")<=<s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>)){
			$.messageBox({level:"warn",message:"请先选择县(区)及以下层级进行新增！"});
			return;
		}else{
			$("#fourlevelplatformDialog").createDialog({
				model :"add",
				title:"新增四级体系建设信息",
				width: dialogWidth,
				height: dialogHeight,
				url:'${path}/fourLevelPlatformManage/dispatchOperate.action?mode=add&organization.id='+getCurrentOrgId(),
				buttons: {
					"保存" : function(){
						$("#maintainForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
				
			});
		}
	});
	$("#update").click(function(){
		var selectedIds = $("#fourlevelplatformList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){return;}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			 return;
		}
		updateOperator(selectedId);
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(allValue);
	});
	$("#view").click(function(){
		if($("#view").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewFourlevelplatform(selectedId);
	});
	
	$("#export").click(function(event){
		if($("#fourlevelplatformList").getGridParam("records")>0){
			var postData = $("#fourlevelplatformList").getPostData();
			$("#fourlevelplatformList").setPostData($.extend(postData,{
									"searchFourLevelPlatformVo.orgId":getCurrentOrgId(),
									"searchFourLevelPlatformVo.displayLevel":$("#displayLevel").val()
									}));
			$("#fourlevelplatformDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出四级体系建设信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'fourlevelplatformList',
					downloadUrl:'${path}/fourLevelPlatformManage/downloadPrimaryOrg.action'
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
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});
	
	$("#reload").click(function(){
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
	});

	$("#search").click(function(event){
		$("#fourlevelplatformDialog").createDialog({
			width:600,
			height:240,
			title:'四级体系建设查询-请输入查询条件',
 	 		url:'${path}/baseinfo/fourLevelPlatformManage/searchFourLevelPlatformDlg.jsp?orgId='+getCurrentOrgId()+'&displayLevel='+$("#displayLevel").val(),
			buttons: {
		   		"查询" : function(event){
					searchFourlevelplatform();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	
	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	
	$("#maintainPrimaryOrgMembers").click(function(event){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条组织机构维护成员！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条组织机构维护成员信息！"});
	 		return;
		}
		
		//获取组织结构id
		var ent =  $("#fourlevelplatformList").getRowData(selectedId);
		var orgid=	ent["organization.id"];
		var detailName =ent.name;
		$("#fourlevelplatformDialog").createDialog({
			width: 850,
			height: 630,
			title:'维护成员信息',
			url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=maintainPrimaryOrgMembers&primaryMemberVo.primaryOrgId='+selectedId+"&name=GovernmentDepartment"+"&primaryMemberVo.org.id="+orgid+'&primaryMemberVo.isFourLevelPlatform=1&primaryOrgDetailName='+encodeURIComponent(detailName),
			buttons: {
				"关闭" : function(){
		        	$(this).dialog("close");
		        	$("#fourlevelplatformList").trigger("reloadGrid");
		   		}
			}
		});
	});
});

function viewFourlevelplatform(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#fourlevelplatformList").getRowData(selectedId);
	var encryptId = rowData.encryptId;
	if(encryptId==null){
		 return;
	}
	$("#fourlevelplatformDialog").createDialog({
		width:830,
		height:620,
		title:"查看四级体系建设信息",
		url:'${path}/fourLevelPlatformManage/dispatchOperateByEncrypt.action?mode=view&dailogName=fourlevelplatformDialog&fourLevelPlatform.id='+encryptId+'&fourLevelPlatform.organization.id='+rowData["organization.id"],
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateOperator(selectedId){
	var ent =  $("#fourlevelplatformList").getRowData(selectedId);
	var encryptId = ent.encryptId;
	$("#fourlevelplatformDialog").createDialog({
		model :"edit",
		title:"修改四级体系建设信息",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/fourLevelPlatformManage/dispatchOperateByEncrypt.action?mode=edit&fourLevelPlatform.id='+encryptId,
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}		
	});
}
function deleteOperator(allValue){
	var allValue=deleteOperatorByEncrypt("fourlevelplatform",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
 				url:'${path}/fourLevelPlatformManage/deleteFourLevelPlatformByIds.action',
 				type:"post",
 				data:{
 					"ids":allValue,
 					"isFourLevelPlatform":1
 				},
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除该四级体系建设信息!"});
						$("#fourlevelplatformList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"请先清空成员信息后再删除该组织!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}
function parseObj(strData) {
	return (new Function("return " + strData))();
}
function searchFourlevelplatform(){
	var formdata = $("#searchPlatformForm").serialize();
	if (formdata != '') {
		formdata = formdata.replace(/\+/g," "); 
		formdata = formdata.replace(/=/g, '":"');
		formdata = formdata.replace(/&/g, '","');
		formdata += '"';
	}
	formdata = decodeURIComponent('{"' + formdata + '}');
	$("#fourlevelplatformList").setGridParam({
		url:'${path}/fourLevelPlatformManage/findFourLevelPlatformPagerBySearchVo.action',
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#fourlevelplatformList").setPostData(parseObj(formdata));
	$("#fourlevelplatformList").trigger("reloadGrid");
}
function search(orgId){
	var fastSearchVal = $("#searchText").val();
	if(fastSearchVal == '请输入四级体系建设名称') return;
	var	postData = {
		 "organizationId":orgId
		// parameters
	}
	$("#fourlevelplatformList").setGridParam({
// 		url:'${path}/fourLevelPlatformManage/fastSearch.action',
		datatype: "json",
		page:1
	});
	$("#fourlevelplatformList").setPostData(postData);
	$("#fourlevelplatformList").trigger("reloadGrid");
}

function countFourLevelPlatform(){
	$.ajax({
		async:false,
		url: "${path}/fourLevelPlatformManage/countFourLevelPlatformByOrgId.action",
		data:{
			"fourLevelPlatform.organization.id":getCurrentOrgId()
		},
		success:function(data){
			count = data;
		}
	});
	return count;
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#fourlevelplatformList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#fourlevelplatformList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>