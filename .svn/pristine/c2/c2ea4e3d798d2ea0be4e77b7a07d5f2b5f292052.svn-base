<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav">
	<div class="btnbanner btnbannerData">
		
			<input type="text"
				value="请输入标题" name="searchText" id="searchText"
				class="searchtxt"
				onblur="value=(this.value=='')?'请输入标题':this.value;"
				onfocus="value=(this.value=='请输入标题')?'':this.value;" />
			<button id="refreshSearchKey_comment" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 210px; cursor: pointer; outline: none;"></button>
	
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchMyCommentLog">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
	</div>
	<span class="lineBetween"></span>
	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="myCommentList">
	</table>
	<div id="myCommentListPager"></div>
</div>
<div id="logViewDialog"></div>
<div id="searchDialog"></div>
<div id="peopleLogDialog"></div>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
function viewDialog(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#myCommentList").getRowData(selectedId);
	var id = rowData.logId;
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:dialogWidth,
		height:450,
		title:"查看日志信息",
		url:"${path}/peopleLog/commentLogManage/dispatchOperate.action?mode=view&peopleLog.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function viewMyCommentLog(id){
	viewDialog(id);
}
$(function(){
	//内置属性  直接调用里面的常量，在页面直接拿来用.可以自动取值
	<pop:formatterProperty name="peopleLog.logType" domainName="@com.tianque.domain.property.PropertyTypes@PEOPLELOG_LOGTYPE" />
	function titleFont(el,options,rowData){
		return "<pop:JugePermissionTag ename='viewMyCommentLog'><a href='javascript:viewMyCommentLog("+rowData.id+")'>"+rowData.peopleLog.title+"</a></pop:JugePermissionTag>";
	}
	var gridOption={
			colModel:[
						{name:'id',index:'id',sortable:false,hidden:true,frozen:true},
						{name:'peopleLog.title',index:'title',label:'标题',sortable:false,formatter:titleFont},
						{name:"peopleLog.organization.orgName",index:'peopleLog.organization.orgName',sortable:false,label:'所属区域',width:150},
						{name:'peopleLog.logType',index:'logType',label:'日志种类',sortable:false,formatter:logTypeFormatter},
						{name:'logId',index:'logId',hidden:true,sortable:true,hidedlg:true,label:'日志Id'},
						{name:'commentTime',index:'commentTime',sortable:true,label:'点评时间',align:'center'},
						{name:'comments',index:'comments',sortable:true,label:'点评内容',width:320}
					],
					autowidth: true
	};
	
	$("#myCommentList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	<pop:JugePermissionTag ename='viewMyCommentLog'>
			ondblClickRow: doubleClickTable,
  		</pop:JugePermissionTag>
		multiselect:false
	  	
	});
	jQuery("#myCommentList").jqGrid('setFrozenColumns');
	$("#myCommentList").setGridParam({
		url:"${path}/peopleLog/commentLogManage/myCommentList.action",
		datatype: "json",
		page:1
	});

	$("#myCommentList").trigger("reloadGrid");
	
	$("#refreshSearchKey_comment").click(function(){
		$("#searchText").attr("value","请输入标题");
	});

	function doubleClickTable(selectedId){
		var rowData = $("#myCommentList").getRowData(selectedId);
		var id = rowData.logId;
		if(id==null){
			 return;
		}
		$("#logViewDialog").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看日志信息",
			url:"${path}/peopleLog/commentLogManage/dispatchOperate.action?mode=view&peopleLog.id="+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	$("#fastSearchButton").click(function(){
		search();
	});

	function search(){
		var conditions = $("#searchText").val();
		if(conditions == '请输入标题'){
			return;
		}
		var initParam = {"searchPeopleLogVo.title":conditions};
		$("#myCommentList").setGridParam({
			url:"${path}/peopleLog/searchCommentLog/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#myCommentList").setPostData(initParam);
		$("#myCommentList").trigger("reloadGrid");
	}

	$("#search").click(function(event){
		$("#searchDialog").createDialog({
			width:650,
			height:330,
			title:'我的点评查询-请输入查询条件',
			url:'${path}/peopleLog/commentLogManage/dispatchOperate.action?mode=search',
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
		$("#myCommentList").setGridParam({
			url:"${path}/peopleLog/searchCommentLog/findCommentLogByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#myCommentList").setPostData($("#searchCommentLogForm").serializeObject());
	    $("#myCommentList").trigger("reloadGrid");
	}

	$("#reload").click(function(){
		$("#myCommentList").setGridParam({
			url:"${path}/peopleLog/commentLogManage/myCommentList.action",
			datatype: "json",
			page:1
		});
		
		$("#myCommentList").trigger("reloadGrid");

		$("#searchText").attr("value","请输入标题");

	});
});


</script>

