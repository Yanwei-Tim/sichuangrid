<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/xichang/working/include/formatterAccount.jsp" />

<style>
#orgSelector{
	width:138px
}
</style>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<input id="orgId" name="orgId" type="hidden" />
		<div class="grid_7 form-left">
			<input type="text" id="orgSelector"  class="form-txt" />
	 	</div>
	 	<span class="lineBetween "></span>
		<div class="btnbanner btnbannerData">
<%-- 			<jsp:include page="${path}/common/orgSelectedComponent.jsp" /> --%>
<!-- 			<div class="ui-widget autosearch"> -->
<!-- 				<input class="basic-input" type="text" value="请输入民生诉求的编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;" -->
<!-- 					onblur="value=(this.value=='')?'请输入民生诉求的编号':this.value;" onfocus="value=(this.value=='请输入民生诉求的编号')?'':this.value;" /> -->
<!-- 				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button> -->
<!-- 			</div> -->
			<div class="userState" id="fastSearchSelect">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>"  selected="selected">全部</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>">仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
				</select>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchDoneChlidPeopleAspiration">
			<a id="searchPeopleAspiration" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()" >
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xg"></strong>修改</span></a>
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</s:if>
		<pop:JugePermissionTag ename="viewDoneChlidPeopleAspiration">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-ck"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="peopleaspirationsList">
		</table>
		<div id="peopleaspirationsListPager"></div>
	</div>
	<div id="peopleaspirationsDialog"></div>
	<div id="noticeDialog"></div>
	<div id="peopleaspirationsMaintanceDialog"></div>
	
</div>
<script type="text/javascript">
<pop:formatterProperty name="createTableType" domainName="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" />

var dialogWidth = 800;
var dialogHeight = 600;
// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updatePeopleaspirations'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deletePeopleaspirations'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}


// 改变组织机构树时加载列表
function onOrgChanged(orgId,isgrid){
	search({
		"organization.id":orgId,
		"searchPeopleAspirationsVo.displayLevel":$("#displayLevel").val(),
		"searchPeopleAspirationsVo.serialNumber":''
	});
}



$(function(){
	if(isGrid()){
		$("#fastSearchSelect").hide();
	}
	var tree=$("#orgSelector").treeSelect({
		inputName:"orgId",
		changeFun:function(node,e){
			onOrgChanged(node.id,isGrid())
		}
	});
	function toggleButtonState(){
	  	var selectedIds=$("#peopleaspirationsList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}
	
	// 生成列表
	$("#peopleaspirationsList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"encryptId",index:'id',frozen:true,hidden:true},
// 	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
			{name:"earingWarn", index:'earingWarn',label:'预警',formatter:earingWarnFormatter,width:65,frozen:true,sortable:false,align:'center' },
			{name:'serialNumber', index:'serialNumber',label:'编号', width:200, sortable:true, align:'center', hidden:false}, 	
			{name:'organization.orgName',index:'orgId',label:'所属区域', width:350,hidden:false,sortable:true,align:'center'},
			{name:'name', index:'name',label:'有关人员', width:100, sortable:true, align:'center', hidden:false}, 
			{name:'appealContent', index:'appealContent',label:'情况描述', width:420, sortable:false, align:'center', hidden:false},
			{name:'createTableType', index:'createTableType',label:'建表类型', width:300,formatter:createTableTypeFormatter, sortable:true, align:'center', hidden:false},
			{name:'createDate', index:'createDate',label:'建表时间', width:200, sortable:true, align:'center', hidden:false},
			{name:'registrant', index:'registrant',label:'登记人', width:200, sortable:true, align:'center', hidden:false}
			//{name:'', index:'',label:'评价反馈', width:100, sortable:true, align:'center', hidden:false}	
	   	],
	  	multiselect:true,
	  	onSelectAll:function(data){
	  		toggleButtonState(data);
	  	},
    	loadComplete: function(data){
    		afterLoad(data);
    	},
	   	<pop:JugePermissionTag ename="viewDoneChlidPeopleAspiration">
	    ondblClickRow: viewPeopleaspirations,
	    </pop:JugePermissionTag>
		onSelectRow: function(data){
			toggleButtonState(data);
		}
	});
	jQuery("#peopleaspirationsList").jqGrid('setFrozenColumns');
	
	$("#fastSearchButton").click(function(){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入民生诉求的编号' || fastSearchVal==''){
			onOrgChanged($("#orgId").val(),isGrid());
			return;
		}
		search({
			"organization.id":$("#orgId").val(),
			"searchPeopleAspirationsVo.serialNumber":fastSearchVal
		});
	});
	$("#searchText").focus(function(){
        this.select();
	 });
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入民生诉求的编号");
	});
	$("#update").click(function(){
		var selectedIds = $("#peopleaspirationsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
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
		var selectedIds = $("#peopleaspirationsList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewPeopleaspirations(selectedId);
	});
	
	
	$("#reload").click(function(){
		$("#searchText").attr("value","请输入民生诉求的编号");
		onOrgChanged($("#orgId").val(),isGrid());
	});

	$("#searchPeopleAspiration").click(function(event){
		$("#peopleaspirationsDialog").createDialog({
			width:650,
			height:320,
			title:'民生诉求表查询-请输入查询条件',
 	 		url:'${path}/xichang/working/peopleAspiration/searchPeopleAspirationDlg.jsp?type=done&orgId='+$("#orgId").val()+"&displayLevel="+$("#displayLevel").val(),
			buttons: {
		   		"查询" : function(event){
					searchPeopleaspirations();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#evaluatePeopleAspiration").click(function(){
		
	});
	
	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		onOrgChanged($("#orgId").val(),isGrid());
	});
		
	
	
});

function viewPeopleaspirations(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#peopleaspirationsList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#peopleaspirationsDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看民生诉求台账信息",
		url:'${path}/account/evaluateFeedbacksManage/dispatchOperate.action?accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION"/>&mode=view&evaluateMode=view&accountId='+id+'&id='+id,		
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function updateOperator(selectedId){
	var ent =  $("#peopleaspirationsList").getRowData(selectedId);
	$("#peopleaspirationsDialog").createDialog({
		title:"修改民生诉求表信息",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/xichang/working/flow/accountLogsTab.jsp?id='+ent.encryptId+'&accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION"/>&mode=edit',
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
	var selectedIds=deleteOperatorByEncrypt("peopleaspirations",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/account/peopleAspirationManage/deletePeopleAspirationByIds.action',
				type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该民生诉求表信息!"});
					$("#peopleaspirationsList").trigger("reloadGrid");
				}
			});
		}
	});
}

function parseObj(strData) {
	return (new Function("return " + strData))();
}
function searchPeopleaspirations(){
	var formdata = $("#searchPeopleAspirationForm").serialize();
	if (formdata != '') {
		formdata = formdata.replace(/=/g, '":"');
		formdata = formdata.replace(/&/g, '","');
		formdata += '"';
	}
	formdata = decodeURIComponent('{"' + formdata + '}');
	search(parseObj(formdata));
}
function search(postData){
	$("#peopleaspirationsList").setGridParam({
		url:'${path}/account/peopleAspirationManage/findDonePeopleAspirationPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#peopleaspirationsList").setPostData($.extend(postData,{type:"chlid"}));
	$("#peopleaspirationsList").trigger("reloadGrid");
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#peopleaspirationsList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#peopleaspirationsList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>