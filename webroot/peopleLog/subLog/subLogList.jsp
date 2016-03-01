<%@page import="com.tianque.core.util.ThreadVariable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<% Long userId =ThreadVariable.getSession().getUserId(); %>

<s:if test="!'Dustbin'.equals(#request.moduleName)">
<div id="nav">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	
	 <div class="btnbanner btnbannerData">
		<select id="isLock" name="" class="S_object">
			<option value="false" selected="selected">仅显示本级</option>
			<option value="true">全部</option>
		</select>
		<input type="text"
			value="请输入日志名称" name="searchText" id="searchText"
			class="searchtxt"
			onblur="value=(this.value=='')?'请输入日志名称':this.value;"
			onfocus="value=(this.value=='请输入日志名称')?'':this.value;" />
			<button id="refreshSearchKey_subLog" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 450px; cursor: pointer; outline: none;"></button>
	
		<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchSubLog">
		<a id="search" href="javascript:void(0)"  ><span><strong
			class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		
		<span class="lineBetween"></span>
		<pop:JugePermissionTag ename="commentLog">
		<a id="addComment" href="javascript:void(0)"><span><strong
			class="ui-ico-dc"></strong>点评</span></a>
		</pop:JugePermissionTag>
		
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
</div>
 </s:if>
<div style="width: 100%;">
	<table id="subLogList"></table>
	<div id="subLogListPager"></div>
</div>
<div id="subDialog"></div>
<div id="logViewDialog"></div>
<script type="text/javascript">

function onOrgChanged(orgId,isGrid){
	$("#subLogList").setGridParam({
		url:"${path}/peopleLog/peopleLogManage/subLogList.action",
		datatype: "json",
		page:1
	});

	var initParam = {
			"organizationId":orgId
		}
	if($("#isLock").val()=="false"){
		initParam = {
				"organizationId":orgId,
				"isPeer":false
			}
	}else{
		initParam = {
				"organizationId":orgId,
		    	"isPeer":true
			}
	}
	$("#subLogList").setPostData(initParam);
	$("#subLogList").trigger("reloadGrid");

}
//点评。。
function setCommentButtonEnabled(enabled){
	if (enabled){
		$("#addComment").buttonEnable();
	}
}


function viewSubLog(id){
	if(id==null){
 		return;
	}
	var rowData=  $("#subLogList").getRowData(id);
	$("#logViewDialog").createDialog({
		width:dialogWidth,
		height:450,
		title:"查看日志信息",
		url:"${path}/peopleLog/commentLogManage/dispatchOperateByEncrypt.action?mode=view&peopleLog.id="+rowData.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function titleFont(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewSubLog'><a href='javascript:viewSubLog("+rowData.id+")'>"+rowData.title+"</a></pop:JugePermissionTag>";
}
//查看日志信息。。
$(function(){
<pop:formatterProperty name="logType" domainName="@com.tianque.domain.property.PropertyTypes@PEOPLELOG_LOGTYPE" />
var gridOption={
	colModel:[
        {name:"id",index:"id",sortable:false,hidden:true},
        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
        {name:"userId",id:"userId",hidden:true,sortable:true,hidedlg:true},
	    {name:"title",id:"logTitle",label:'标题',sortable:true,width:250,formatter:titleFont},
	    {name:"organization.orgName",index:'organization.orgName',sortable:false,label:'所属区域',align:'left',width:150},
	    {name:"logType",label:'日志种类',align:'left',sortable:true,width:80,formatter:logTypeFormatter},
	    {name:"createDate",label:'创建时间',align:'center',width:150,formatter:'date',sortable:true,formatoptions:{newformat: 'Y-m-d'}},
	    {name:"commentNums",label:'点评数',sortable:true,align:'center',width:150}
	]
};
$("#refreshSearchKey_subLog").click(function(){
	$("#startDate").attr("value","");
	$("#endDate").attr("value","");
	$("#searchText").attr("value","请输入日志名称");
	$("#endDate").datepicker("option", "minDate",'+0d');
	$("#startDate").datepicker("option", "maxDate",'+0d');
});
var dialogWidth=800;
var dialogHeight=600;

$("#search").click(function(event){

	$("#subDialog").createDialog({
		width:650,
		height:300,
		title:'下辖日志查询-请输入查询条件',
		url:'${path}/peopleLog/peopleLogManage/dispatchAction.action?mode=search',
		buttons: {
	   		"查询" : function(event){
	   			searchCommentLog();
	        	$(this).dialog("close");
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
	});

function searchCommentLog(){
	$("#subLogList").setGridParam({
		url:"${path}/peopleLog/searchCommentLog/findSubLogByQueryCondition.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	  $("#subLogList").setPostData($.extend({"searchPeopleLogVo.isPeer":$('#isLock').val(),"orgId":getCurrentOrgId()},$("#searchSubLogForm").serializeObject()));
    $("#subLogList").trigger("reloadGrid");
}

$("#subLogList").jqGridFunction({
	mtype:'post',
	datatype: "local",
	colModel: gridOption.colModel,
  	multiselect:true,
  	ondblClickRow: doubleClickTable
  //	onSelectRow: function(data){if(toggleButtonState){toggleButtonState(data);}},
  	//onSelectAll:function(data){if(toggleButtonState){toggleButtonState(data);}},
  	//loadComplete:function(data){if(toggleButtonState){toggleButtonState(data);}}
});
if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
	onOrgChanged(getCurrentOrgId(),false);
}

$('#startDate').datePicker({
	yearRange: '1900:2030',
	dateFormat:'yy-mm-dd',
	maxDate:'+0d',
	onClose: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endDate").datepicker("option", "minDate",date);
		}else{
			$("#endDate").datepicker("option", "minDate",'+0d');
		}
	}
});

$('#endDate').datePicker({
	yearRange: '1900:2030',
	dateFormat:'yy-mm-dd',
	maxDate:'+0d',
	onClose: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#startDate").datepicker("option", "maxDate",date);
		}else{
			$("#startDate").datepicker("option", "maxDate",'+0d');
		}
	}
});
	
	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),false);
	});
	
	function toggleButtonState(){
	  	var selectedIds=$("#subLogList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setCommentButtonEnabled(selectedRowCount==1);
	
	  	if(selectedIds.length==1){
	  		var rowData=$("#subLogList").getRowData(selectedIds);
	  		if(rowData.userId==<%=userId%>){
	  			$("#addComment").buttonEnable();
			}
	  	}
	}
	
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),false);
		$("#startDate").val("");
		$("#endDate").val("");
		$("#searchText").attr("value","请输入日志名称");
		$("#endDate").datepicker("option", "minDate",'+0d');
		$("#startDate").datepicker("option", "maxDate",'+0d');
	});
	
	$("#fastSearch").click(function(){
		search();
	});

	function search(){
		var conditions = $("#searchText").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(conditions == "请输入日志名称"){
			 conditions="";
		}
		var initParam = {
				"searchPeopleLogVo.title":conditions,
				"searchPeopleLogVo.startDate":startDate,
				"searchPeopleLogVo.endDate":endDate,
				"searchPeopleLogVo.isPeer":$('#isLock').val(),
				"orgId":getCurrentOrgId()
			}
		$("#subLogList").setGridParam({
			url:"${path}/peopleLog/searchCommentLog/quickSearch.action",
			datatype: "json",
			page:1
		});
		$("#subLogList").setPostData(initParam);
		$("#subLogList").trigger("reloadGrid");
	}
	
	//点评日志
	$("#addComment").click(function(){
		var selectedId=getSelectedIds();
		if(selectedId == null){
			$.messageBox({level:"warn",
				message:'只能选中一条日志进行点评！'});
			return;
		}
		if(selectedId.length!=1){
			$.messageBox({level:"warn",
				message:'请选择一条日志进行点评！'});
			return;
			}
		var rowData=$("#subLogList").getRowData(selectedId);
		if(rowData.userId==<%=userId%>){
			$.messageBox({level:"warn",
				message:'不能点评自己的日志！'});
			return;
			}
		var rowData=  $("#subLogList").getRowData(selectedId);
		
		$("#subDialog").createDialog({
			width:dialogWidth,
			height:420,
			title:"评论日志",
			url:"${path}/peopleLog/peopleLogManage/dispatchActionByEncrypt.action?mode=add&dailogName=subDialog&logId="+rowData.encryptId,
			buttons: {
				"保存":function(){
					$("#commentForm").submit();
					$("#peopleLogList").trigger("reloadGrid");
				},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
	})
	});

	$("#view").click(function(event){
		if($("#view").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedId();
		if(selectedId==null){
	 		return;
		}
		viewDialog(selectedId);
	});


	function viewDialog(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#subLogList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#logViewDialog").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看日志信息",
			url:"${path}/peopleLog/commentLogManage/dispatchOperateByEncrypt.action?mode=view&peopleLog.id="+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}


	function doubleClickTable(selectedId){
		var rowData = $("#subLogList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#logViewDialog").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看日志信息",
			url:"${path}/peopleLog/commentLogManage/dispatchOperateByEncrypt.action?mode=view&peopleLog.id="+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
});
	function getSelectedIds(){
		var selectedIds=$("#subLogList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
		}

	function getSelectedId(){
		var selectedIdLast = null;
	    var selectedIds = $("#subLogList").jqGrid("getGridParam", "selarrrow");

	    for(var i=0;i<selectedIds.length;i++){
			selectedIdLast = selectedIds[i];
	   }
	   return selectedIdLast;
	}
	
	


</script>