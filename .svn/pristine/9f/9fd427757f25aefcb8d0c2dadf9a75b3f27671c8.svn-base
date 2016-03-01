<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/xichang/working/include/formatterAccount.jsp" />

<style type="text/css">
.myStyle {
	position: relative;
	left: 42%;
}
	.myStyle strong{
	    float:left;
	    margin-right: 3px;
	    width:18px;height:15px;
	    vertical-align:middle;
	    background: url('/resource/images/jingshi.png') 0 0 no-repeat;
	}

.myStyle .icon_blue  {background-position:0px 0px;}
.myStyle .icon_yellow{background-position:0px -20px;}
.myStyle .icon_orange{background-position:0px -40px;}
.myStyle .icon_red   {background-position:0px -60px;}
</style>
<div class="content">

	<div class="ui-corner-all cf" id="nav">
		<input id="orgId" name="orgId" type="hidden" />
		<div class="areaChoose">
			<input type="text" id="orgSelector"  class="form-txt" />
	 	</div>
		<span class="lineBetween "></span>
		<div class="btnbanner btnbannerData">
<%-- 			<jsp:include page="${path}/common/orgSelectedComponent.jsp" /> --%>
<!-- 			<div class="ui-widget autosearch"> -->
<!-- 				<input class="basic-input" type="text" value="请输入稳定工作台账编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;" -->
<!-- 					onblur="value=(this.value=='')?'请输入稳定工作台账编号':this.value;" onfocus="value=(this.value=='请输入稳定工作台账编号')?'':this.value;" /> -->
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
		<pop:JugePermissionTag ename="searchChildSteadyWork">
			<a id="searchChildSteadyWork" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()" >
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xg"></strong>修改</span></a>
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</s:if>
		<pop:JugePermissionTag ename="normalChildSteadyWork">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-ck"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="steadyWorkList">
		</table>
		<div id="steadyWorkListPager"></div>
	</div>
	<div id="steadyWorkDialog"></div>
	<div id="noticeDialog"></div>
	<div id="steadyWorkMaintanceDialog"></div>
	
</div>
<script type="text/javascript">
<pop:formatterProperty name="createTableType" domainName="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" />
var dialogWidth = 800;
var dialogHeight = 600;
function loadSteadyWorkData(orgId) {
	var initParam = {
			"state":0,
			"organization.id":orgId,
			"steadyWork.displayLevel":$("#displayLevel").val()
			
	}
	$("#steadyWorkList").setGridParam({
		url:"${path}/account/steadyWorkManage/findUnDoChlidSteadyWorkForPageByOrgInternalCode.action",
		datatype: "json",
		page:1
	});
	$("#steadyWorkList").setPostData(initParam);
	$("#steadyWorkList").trigger("reloadGrid");
}

function rendWarning(el, options, rowData){
	var iconArray = "";
	if(rowData.warningType != null){
		if(0 == rowData.warningType){
			iconArray += "<div class='myStyle'><strong class='icon_blue' title='1-2人且事态在可控范围'></strong></div>";
		}else if(1 == rowData.warningType){
			iconArray += "<div class='myStyle'><strong class='icon_yellow' title='涉及3-9人且事态恶化的可能性'></strong></div>";
		}else if(2 == rowData.warningType){
			iconArray += "<div class='myStyle'><strong class='icon_orange' title='涉及10-30人且事态有扩大趋势'></strong></div>";
		}else if(3 == rowData.warningType){
			iconArray += "<div class='myStyle'><strong class='icon_red' title='涉及30人以上且事态还有扩大趋势的及涉及人数虽较少，但如不及时采取措施事态将恶化'></strong></div>";
		}
	}
	return iconArray;
}

function nameFormatter(el, options, rowData){
	if(rowData.peopleInfos!=null){
		return rowData.peopleInfos[0].name;
	}
}

$(function (){
	if(isGrid()){
		$("#fastSearchSelect").hide();
	}
	var tree=$("#orgSelector").treeSelect({
		inputName:"orgId",
		changeFun:function(node,e){
			loadSteadyWorkData(node.id);
		}
	});
	function toggleButtonState(){
	  	var selectedIds=$("#steadyWorkList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}
	
	
	$("#steadyWorkList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"encryptId",index:'id',frozen:true,hidden:true},
	        {name:"earingWarn", index:'earingWarn',label:'预警',formatter:earingWarnFormatter,width:65,frozen:true,sortable:false,align:'center' },
	        {name:"serialNumber",sortable:true,label:'编号',width:200},
	        {name:"warningType",formatter:rendWarning,sortable:true,label:'预警级别',width:100},
      		{name:'organization.orgName',index:'orgId',label:'所属区域', width:350,hidden:false,sortable:true,align:'center'},
	        {name:'name',label:'有关人员',sortable:false,align:'center',width:100,formatter:nameFormatter},
	        {name:'steadyInfo',label:'情况描述',width:420},
	        {name:'createTableType', index:'createTableType',label:'建表类型', width:300,formatter:createTableTypeFormatter, sortable:true, align:'center', hidden:false},
			{name:'createDate', index:'createDate',label:'建表时间', width:200, sortable:true, align:'center', hidden:false},
			{name:'registrant', index:'registrant',label:'登记人', width:200, sortable:true, align:'center', hidden:false}
	       // {name:'lastLoginTime',label:'评价反馈',width:100,align:'center'}
		],
		loadComplete: function(data){
    		afterLoad(data);
    	},
		multiselect: true,
	 	onSelectAll:function(data){
	  		toggleButtonState(data);
	  	},
		<pop:JugePermissionTag ename="normalChildSteadyWork">
	    ondblClickRow: viewSteadyWork
	    </pop:JugePermissionTag>
	});
	jQuery("#steadyWorkList").jqGrid('setFrozenColumns');
	
	$("#fastSearchButton").click(function(){
		search($('#orgId').val());
	});
	$("#searchText").focus(function(){
        this.select();
	 });
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入稳定工作台账编号");
	});
	
	
	
	$("#update").click(function(){
		var selectedIds = $("#steadyWorkList").jqGrid("getGridParam", "selarrrow");
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
		var selectedIds = $("#steadyWorkList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewSteadyWork(selectedId);
	});
	
	
	$("#reload").click(function(){
		loadSteadyWorkData($('#orgId').val());
	});

	$("#searchChildSteadyWork").click(function(event){
		$("#steadyWorkDialog").createDialog({
			width:650,
			height:320,
			title:'稳定工作台账表查询-请输入查询条件',
 	 		url:'${path}/xichang/working/steadyWork/maintainSearchSteadyWorkDlg.jsp?orgId='+$('#orgId').val()+'&state='+0+"&displayLevel="+$("#displayLevel").val(),
			buttons: {
		   		"查询" : function(event){
					searchSteadyWork();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		loadSteadyWorkData($("#orgId").val());
	});
	
});


function parseObj(strData) {
	return (new Function("return " + strData))();
}
function searchSteadyWork(){
	var formdata = $("#searchSteadyWorkForm").serialize();
	if (formdata != '') {
		formdata = formdata.replace(/=/g, '":"');
		formdata = formdata.replace(/&/g, '","');
		formdata += '"';
	}
	formdata = decodeURIComponent('{"' + formdata + '}');
	$("#steadyWorkList").setGridParam({
		url:'${path}/account/steadyWorkManage/findUnDoChlidSteadyWorkForPageByCondition.action',
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#steadyWorkList").setPostData(parseObj(formdata));
	$("#steadyWorkList").trigger("reloadGrid");
	
}

function updateOperator(selectedId){
	var steadyWork =  $("#steadyWorkList").getRowData(selectedId);
	$("#steadyWorkDialog").createDialog({
		title:"修改稳定工作台账信息",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/xichang/working/flow/accountLogsTab.jsp?mode=edit&id='+steadyWork.encryptId+'&accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK"/>',
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
	var selectedIds=deleteOperatorByEncrypt("steadyWork",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/account/steadyWorkManage/deleteSteadyWorkByIds.action',
				type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
					if(data!=null) {
						$.messageBox({message:"已经成功删除该稳定工作台账信息!"});
						loadSteadyWorkData(USER_ORG_ID);
					} else {
						$.messageBox({message:data,level:"error"});
					}
				}
			});
		}
	});
}

function viewSteadyWork(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#steadyWorkList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#steadyWorkDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"查看稳定工作台账信息",
		url:'${path}/account/evaluateFeedbacksManage/dispatchOperate.action?accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK"/>&mode=view&evaluateMode=view&accountId='+id+'&id='+id,		
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function search(orgId){
	var fastSearchVal = $("#searchText").val();
	if(fastSearchVal == '请输入稳定工作台账编号' || fastSearchVal==''){
		loadSteadyWorkData(orgId);
		return;
	}
	
	var	postData = {
		 "organization.id":orgId,
		 "steadyWork.serialNumber":fastSearchVal,
		 "state":0
	}
	$("#steadyWorkList").setGridParam({
		url:'${path}/account/steadyWorkManage/findUnDoChlidSteadyWorkForPageByCondition.action',
		datatype: "json",
		page:1
	});
	$("#steadyWorkList").setPostData(postData);
	$("#steadyWorkList").trigger("reloadGrid");
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#steadyWorkList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}

function getSelectedIds(){
	var selectedIds = $("#steadyWorkList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>