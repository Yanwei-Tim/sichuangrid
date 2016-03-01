<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style>
	.greenLimitWarn {
		width: 22px;
		height: 22px;
		display: block;
		margin: 0 auto;
		vertical-align: top;
		background: url(/resource/system/images/issue/icon_gLamp.png) no-repeat;
	}
	.yellowLimitWarn {
		width: 22px;
		height: 22px;
		display: block;
		margin: 0 auto;
		vertical-align: top;
		background: url(/resource/system/images/issue/icon_yLamp.png) no-repeat;
	}
	.redLimitWarn {
		width: 22px;
		height: 22px;
		display: block;
		margin: 0 auto;
		vertical-align: top;
		background: url(/resource/system/images/issue/icon_rLamp.png) no-repeat;
	}
</style>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<%-- 	<jsp:include page="/common/orgSelectedComponent.jsp"/> --%>
			<jsp:include page="/common/orgSelectedTaskListComponent.jsp"/>
		</div>
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
			    <input class="basic-input searchtxt" type="text" value="请输入地点或者姓名" name="searchText" id="searchText" maxlength="30" onblur="value=(this.value=='')?'请输入地点或者姓名':this.value;" onfocus="value=(this.value=='请输入地点或者姓名')?'':this.value;"/>
				<button id="refreshSearchText" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		
	<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	<pop:JugePermissionTag ename="addPropagandaAndVerification">
			<a id="addPropagandaAndVerification" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updatePropagandaAndVerification">
			<a id="updatePropagandaAndVerification" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="delPropagandaAndVerification">
			<a id="delPropagandaAndVerification" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>删除</span></a>
	</pop:JugePermissionTag>
	<a id="viewPropagandaAndVerification" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>查看</span></a>
	<pop:JugePermissionTag ename="updatePropagandaAndVerificationSignDetail">
			<a id="updatePropagandaAndVerificationSignDetail" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>签收</span></a>
	</pop:JugePermissionTag>

	<a id="refresh" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="propagandaAndVerificationList"></table>
		<div id="propagandaAndVerificationListPager"></div>
	</div>
	
	<div id="propagandaAndVerificationDialog" ></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="exceptionSituation" domainName="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SITUATION_TYPE" />
var taskListTimeStandard;
var serverTime = <%=new java.util.Date().getTime()%>;
function getTaskListTimeStandardByItemName(){
	$.get(PATH+"/taskListTimeStandardManage/getTaskListTimeStandardByItemName.action",{'orgId':selectConfigTaskOrg(),'itemNameDictInternalId':<%=com.tianque.plugin.taskList.constant.TaskListItemNameInternalId.PROPAGANDA_AND_VERIFICATION%>},function(data){
		taskListTimeStandard = data[0];
		// 有配置的才显示亮牌
		if(taskListTimeStandard){
			$("#propagandaAndVerificationList").jqGrid("showCol", ["signDate"]);
		}
	});
}
$(document).ready(function(){
	getTaskListTimeStandardByItemName();
	var postData={
			"propagandaAndVerificationVo.organization.id":selectConfigTaskOrg()
        };
	if(isConfigTaskSelect()){
		$.extend(postData,{"propagandaAndVerificationVo.mode":"gridConfigTask","propagandaAndVerificationVo.funOrgId": $("#funOrgId").val()})
	}
	$("#propagandaAndVerificationList").jqGridFunction({
	   	url:'${path}/baseInfo/propagandaAndVerificationManage/searchPropagandaAndVerificationByVo.action',
	   	postData:postData ,
		datatype: "json",
	   	colModel:[
			{name:'id',index:'id',sortable:false,hidden:true,frozen:true,hidedlg:true,key:true},
	   	    {name:'organization.id',index:'organization.id',sortable:false,hidden:true,frozen:true,hidedlg:true},
		<pop:JugePermissionTag ename="updatePropagandaAndVerificationSignDetail">
			{name:'signDate',label:'签收督办',sortable:true,formatter:signSuperviseFormatter,align:'center',hidden:true,hidedlg:true,width:130},
		</pop:JugePermissionTag>
			{name:'name',label:'姓名',sortable:true,align:'center',width:130},
	   	 	{name:'idCard',label:'身份证号码',sortable:true,align:'center',width:130},
	   	 	{name:'phone',label:'电话号码',sortable:true,align:'center',width:130},
	   	    {name:'occurrenceDate',label:'时间',sortable:true,align:'center',width:150},
	   		{name:'address',label:'地点',sortable:true,align:'center',width:120},
	   		{name:'propaganda',label:'宣传',sortable:true,align:'center',formatter:propagandaFormatter,width:120},
			{name:'verificationReport',label:'核查申报',sortable:true,align:'center',formatter:verificationReportFormatter,width:120},
			{name:'ishandle',label:'是否签收',sortable:true,align:'center',formatter:ishandleFormatter,width:120},
			{name:'createDate',label:'创建时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120}
		],
	   	multiselect:true,
	    viewrecords:true,
	   	onSelectRow:function(){setOperateButton();},
	   	ondblClickRow:function(rowid){
	   		viewPropagandaAndVerification([rowid]);
	   	}
	});
	function signSuperviseFormatter(el,options,rowData){
		if(rowData.ishandle==1){
			return "-";
		}else{
			if(!taskListTimeStandard){
				return '<span title="未配置">err</span>';
			}
			var createDateStr =rowData.createDate;
			var useTime =(serverTime - Date.parse(createDateStr.replace(/-/g, "/")))/(1000*60*60);
			if(useTime>taskListTimeStandard.signRedLimit){
				return '<strong class="redLimitWarn" title="红牌超时"/>';
			}else if(useTime>taskListTimeStandard.signYellowLimit){
				return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
			}else{
				var title = "剩"+Math.ceil(taskListTimeStandard.signYellowLimit-useTime)+'小时时限';
				return '<strong class="greenLimitWarn" title="'+title+'"/>';
			}
		}
	}
	function propagandaFormatter(el,options,rowData){
		if(rowData.propaganda==1){
			return "是";
		}else if(rowData.propaganda==0){
			return "否";
		}
	}
	function verificationReportFormatter(el,options,rowData){
		if(rowData.verificationReport==1){
			return "是";
		}else if(rowData.verificationReport==0){
			return "否";
		}
	}
	function ishandleFormatter(el,options,rowData){
		if(rowData.ishandle==1){
			return "是";
		}else{
			<pop:JugePermissionTag ename="updatePropagandaAndVerificationSignDetail">
				return "<a href='javascript:updatePropagandaAndVerificationSignDetail("+rowData.id+")'><span style='color:#ff0000;'><strong class='ui-ico-xz'></strong>签收</span></a>";
			</pop:JugePermissionTag>
		}
		return "否";
	}
	var setOperateButton = function(){
		var selectedCounts = getActualjqGridMultiSelectCount("propagandaEducationList");
		var count = $("#propagandaAndVerificationList").jqGrid("getGridParam","records");
		if(selectedCounts == count && count > 0){
			jqGridMultiSelectState("propagandaEducationList", true);
		}else{
			jqGridMultiSelectState("propagandaEducationList", false);
		}
	}
});

$("#fastSearchButton").click(function(){
	if($("#searchText").val()=='请输入地点或者姓名'){

		return;
	}
	refreshList($("#searchText").val());
});

$("#refreshSearchText").click(function(){
	$("#searchText").attr("value","请输入地点或者姓名");
});

$("#refresh").click(function(){
	refreshList(null);
	$("#searchText").attr("value","请输入地点或者姓名");
});

$("#viewPropagandaAndVerification").click(function(){
	var selectedId =$("#propagandaAndVerificationList").jqGrid("getGridParam", "selarrrow");
	if(selectedId.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行查看！"});
 		return;
	}
	viewPropagandaAndVerification(selectedId);
});
function viewPropagandaAndVerification(selectedId){
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请至少一条数据再进行查看！"});
 		return;
	}
	var btn ={};
	btn["关闭"] =  function(){
		$(this).dialog("close");
	};
	$("#propagandaAndVerificationDialog").createDialog({
		width:550,
		height:400,
		title:'查看信息',
		url:'${path}/baseInfo/propagandaAndVerificationManage/dispathOperate.action?mode=view&propagandaAndVerification.id='+selectedId,
		buttons: btn
	});
}

$("#delPropagandaAndVerification").click(function(){
	delPropagandaAndVerificationList();
});

$("#addPropagandaAndVerification").click(function(){
	if (!isConfigTaskGrid()){
		$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
		return;
	}
	$("#propagandaAndVerificationDialog").createDialog({
		width:350,
		height:380,
		title:'添加信息',
		url:'${path}/baseInfo/propagandaAndVerificationManage/dispathOperate.action?mode=add&propagandaAndVerification.organization.id='+selectConfigTaskOrg(),
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();
			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
});

function refreshList(searchText){
	getTaskListTimeStandardByItemName();
	$("#propagandaAndVerificationList").setGridParam({
		url:'${path}/baseInfo/propagandaAndVerificationManage/searchPropagandaAndVerificationByVo.action',
		datatype: "json",
		page:1
	});
	var postData={
			"propagandaAndVerificationVo.organization.id":selectConfigTaskOrg(),
			"propagandaAndVerificationVo.fastSearchKeyWords":searchText
	};
	if(isConfigTaskSelect()){
		$.extend(postData,{"propagandaAndVerificationVo.mode":"gridConfigTask","propagandaAndVerificationVo.funOrgId": $("#funOrgId").val()})
	}
	$("#propagandaAndVerificationList").setPostData(postData);
	$("#propagandaAndVerificationList").trigger("reloadGrid");
}

function delPropagandaAndVerificationList(selectedId){
	if(selectedId==null || selectedId==undefined || selectedId==''){
		selectedId =$("#propagandaAndVerificationList").jqGrid("getGridParam", "selarrrow");
	}
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请至少一条数据再进行操作！"});
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，数据无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseInfo/propagandaAndVerificationManage/deletePropagandaAndVerification.action",
				type:"post",
				data:{
					"ids":selectedId+""
				},
				success:function(data){
					$("#propagandaAndVerificationList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该信息!"});
			    }
		    });
	   }
 	});
}

$("#updatePropagandaAndVerification").click(function(){
	var selectedIds =$("#propagandaAndVerificationList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
 		return;
	}
	updatePropagandaAndVerification(selectedIds[0]);
});
function updatePropagandaAndVerification(selectedId){
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	$("#propagandaAndVerificationDialog").createDialog({
		width:350,
		height:380,
		title:'修改信息',
		url:'${path}/baseInfo/propagandaAndVerificationManage/dispathOperate.action?mode=edit&propagandaAndVerification.id='+selectedId,
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

$("#updatePropagandaAndVerificationSignDetail").click(function(){
	var selectedIds =$("#propagandaAndVerificationList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
 		return;
	}
	updatePropagandaAndVerificationSignDetail(selectedIds[0]);
});
function updatePropagandaAndVerificationSignDetail(selectedId){
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	var rowData = $('#propagandaAndVerificationList').jqGrid('getRowData',selectedId);
	if(rowData.ishandle=='是'){
		$.messageBox({level:"warn",message:"该数据已经被签收！"});
 		return;
	}
	$("#propagandaAndVerificationDialog").createDialog({
		width:580,
		height:460,
		title:'签收',
		url:'${path}/baseInfo/propagandaAndVerificationManage/dispathOperate.action?mode=sign&propagandaAndVerification.id='+selectedId,
		buttons: {
			"签收" : function(){
				$("#maintainForm").submit();
			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>
