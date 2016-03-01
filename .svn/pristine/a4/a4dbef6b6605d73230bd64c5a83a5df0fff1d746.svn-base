<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<div class="ui-corner-all contentNav" id="nav"> 
	<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
	    <div class="userState">
			<select id="searchChild" name="searchChild" class="basic-input">
				<option value="true" >全部</option>
					<option value="false" selected="selected">仅显示本级</option>
			</select>
		</div>
		<pop:JugePermissionTag ename="searchWorkDiary">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
    </div>
	<span class="lineBetween "></span>
	<pop:JugePermissionTag ename="addWorkDiary">
		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteWorkDiary">
		<a id="delete"  href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="viewWorkDiary">
		<a id="view"  href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
	</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
</div>

<div style="clear: both;"></div>
<div style="width:100%;" >
	<table id="workDiaryList"> </table>
	<div id="workDiaryListPager"></div>
</div>
	
<div id="workDiaryDialog"></div>

<script type="text/javascript">	
<pop:formatterProperty name="diaryType" domainName="@com.tianque.domain.property.PropertyTypes@WORKDIARY_DIARY_TYPE" />

var dialogWidth=600;
var dialogHeight=500;

function onOrgChanged(){
	$("#workDiaryList").setGridParam({
		url:'${path}/dailyLog/workDiaryManage/findWorkDiaryForPageByOrgId.action',
		datatype:'json'
	});
	$("#workDiaryList").setPostData({
		"organization.id": getCurrentOrgId(),
		"page":1,
		"searchChild":$("#searchChild").val()
	});
	$("#workDiaryList").trigger("reloadGrid");
}

function operateFormatter(el,options,rowData){
	if(rowData.objectId == 0){
		return "<a href='javascript:;' onclick='updateWorkDiary(event,"+rowData.id+");'><span>修改</span></a> | <a href='javascript:;' onclick='deleteWorkDiary(event,"+rowData.id+")'><span>删除</span></a>";
	}else{
		return "";
	}
}

$(document).ready(function(){

	$("#searchChild").change(function(){
		onOrgChanged();
	});
	
	$("#workDiaryList").jqGridFunction({
		datatype:'local',
		colModel:[
	        {name:"id", index:"id", hidden:true},
	        {name:"objectId", index:"objectId", hidden:true,hidedlg:true},
	        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true},
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
	        {name:"diaryType", index:'diaryType',sortable:true, formatter:diaryTypeFormatter, label:'日志类型', width:120 },
	        {name:'workTime', index:'workTime',sortable:true, label:'工作时间', width:100,align:"center"},
	        {name:'workUserName', sortable:true, label:'工作人员', width:90},
	        {name:'workPlace', sortable:true, label:'地点', width:200},
	        {name:'organization.orgName',label:"所属网格",index:'organization.orgName',sortable:false, width:200},
	        {name:'workContent', sortable:false, label:'工作内容', width:360}
		],
		multiselect:true,
		<pop:JugePermissionTag ename="viewWorkDiary">
		ondblClickRow: function(id){if(ondblClick){ondblClick(id);}},
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectRow: selectRow
	});

	onOrgChanged();

	$("#add").click(function(event){
		if(getCurrentOrgId() == '<s:property value="#loginAction.user.organization.id"/>'){
			$("#add").buttonEnable();
		}else{
			$.messageBox({message:"当前用户只能操作所在层级的组织！",level: "warn"});
			return;
		}
		$("#workDiaryDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'新增工作日志信息',
			url:'${path}/dailyLog/workDiaryManage/dispatch.action?mode=add',
			buttons: {
				"保存" : function(event){
	   			$("#maintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#update").click(function(){
		var selectedId = $("#workDiaryList").getGridParam("selrow");
		var rowData = $("#workDiaryList").getRowData(selectedId);
		if(selectedId == null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行修改！"});
			return;
		}
		updateWorkDiary(event,selectedId);
	});

	$("#view").click(function(event){
		var selectedId = $("#workDiaryList").getGridParam("selrow");
		var selectedIds = $("#workDiaryList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewWorkDiary(event,selectedId);
	});

	$("#delete").click(function(event){
		var selectedIds = $("#workDiaryList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedIds || ""==selectedIds){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
	 		return;
		}
		deleteWorkDiary(event,selectedIds);
	});

	$("#reload").click(function(){
		$("#searchChild").attr("value",false);
		reloadWorkDiarys();
	});

	$("#search").click(function(event){
		$("#workDiaryDialog").createDialog({
			width: 440,
			height: 280,
			title:'工作日志查询-请输入查询条件',
			url:'${path}/dailyLog/workDiaryManage/dispatch.action?mode=search',
			buttons: {
		   		"查询" : function(event){
		   			searchWorkDiary();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});

	});
});
function ondblClick(id){
	$("#workDiaryDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看工作日志信息',
		modal : true,
		url:'${path}/dailyLog/workDiaryManage/dispatch.action?mode=view&workDiary.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}			
	});
}
function updateWorkDiary(event,selectedId){
	var workDiary =  $("#workDiaryList").getRowData(selectedId);
	if(workDiary.diaryType!="其他"){
		$.messageBox({level:'warn',message:"该类型日志不允许修改！"});
		return;
	}
	$("#workDiaryDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'修改工作日志信息',
		modal:true,
		url:'${path}/dailyLog/workDiaryManage/dispatch.action?mode=edit&workDiary.id='+selectedId,
		buttons: {
			"保存" : function(event){
   			$("#maintainForm").submit();
   			},
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

function viewWorkDiary(event,id){
	$("#workDiaryDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看工作日志信息',
		modal : true,
		url:'${path}/dailyLog/workDiaryManage/dispatch.action?mode=view&workDiary.id='+id,
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

function deleteWorkDiary(event,selectedIds){
	if(typeof selectedIds === 'number'){
		var workDiary =  $("#workDiaryList").getRowData(selectedIds);
		if(workDiary.diaryType!="其他"){
			$.messageBox({level:'warn',message:"只可删除日志类型为其他的数据！"});
			return;
		}
	}else{
		for(var i=0;i<selectedIds.length;i++){
			var workDiary =  $("#workDiaryList").getRowData(selectedIds[i]);
			if(workDiary.diaryType!="其他"){
				$.messageBox({level:'warn',message:"只可删除日志类型为其他的数据！"});
				return;
			}
		}
	}
	$.confirm({
		title:"确认删除",
		message:"该工作日志信息删除后,将无法恢复,您确认删除该工作日志信息吗?",
		width:400,
		okFunc:function(){
			$.ajax({
				url:"${path}/dailyLog/workDiaryManage/deleteWorkDiaryById.action",
				type:"post",
				data:{
					"mode":"delete",
					"selectedIds":selectedIds+""
				},
				success:function(responseData){
					if (responseData>0){
					    $.messageBox({message:"已经成功删除该工作日志信息!"});
						$("#workDiaryList").trigger("reloadGrid");
					}else{
						$.messageBox({ message:"该工作日志已被别的模块所使用，不能删除!",level: "warn"	});
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

function reloadWorkDiarys() {
	onOrgChanged();
}

function searchWorkDiary(){
	var	workUserNameCondition=$("#conditionWorkUserName").val();
	var	workPlaceCondition=$("#conditionWorkPlace").val();
	var	diaryTypeCondition=$("#conditionDiaryType").val();
	var workTimeStartCondition=$("#conditionWorkTimeStart").val();
	var workTimeEndCondition=$("#conditionWorkTimeEnd").val();
	var workContent= $("#conditionworkContent").val();
	$("#workDiaryList").setGridParam({
		url:'${path}/dailyLog/searchWorkDiary/searchWorkDiary.action',
		datatype: "json",
		page:1
	});
	$("#workDiaryList").setPostData({
		"searchWorkDiaryVo.organization.id" : getCurrentOrgId(),
    	"searchWorkDiaryVo.workUser":workUserNameCondition,
    	"searchWorkDiaryVo.workPlace":workPlaceCondition,
    	"searchWorkDiaryVo.diaryType":diaryTypeCondition,
    	"searchWorkDiaryVo.workTimeStart":workTimeStartCondition,
    	"searchWorkDiaryVo.workTimeEnd":workTimeEndCondition,
    	"searchChild":$("#searchChild").val(),
    	"searchWorkDiaryVo.workContent":workContent
   	});
	$("#workDiaryList").trigger("reloadGrid");
}

function afterLoad(){}

function selectRow(){}

</script>