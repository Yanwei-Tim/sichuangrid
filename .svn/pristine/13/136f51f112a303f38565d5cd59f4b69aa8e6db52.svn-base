<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
		<div class="content"> 
		<div class="ui-corner-all contentNav" id="nav" >
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入调度名称" id="jobName" class="searchtxt" onblur="value=(this.value=='')?'请输入调度名称':this.value;" onfocus="value=(this.value=='请输入调度名称')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
				<a id="fastSearchScheduleJobException" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<span class="lineBetween "></span>
				<a id="lookScheduleJobException" href="javascript:void(0)"><span>查看</span></a>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="scheduleJobExceptionList"> </table>
			<div id="scheduleJobExceptionListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<div id="scheduleJobExceptionMaintanceDialog"></div>
<div id="viewScheduleJobExceptionMaintanceDialog"></div>
<script type="text/javascript">

$(document).ready(function(){

	$("#fastSearchScheduleJobException").click(function(event){
		var jobName = $("#jobName").val();
	 	if(jobName!=null&&"请输入调度名称"!=jobName){
			$("#scheduleJobExceptionList").setGridParam({
				url:'${path}/judgmentAnalysis/scheduleJobException/findScheduleJobExceptionForPage.action',
				datatype: "json",
				postData: {
					"scheduleJobException.jobName":$("#jobName").val()
				},
				page:1
			});
			$("#scheduleJobExceptionList").trigger("reloadGrid");
		}
	});
	
	$("#lookScheduleJobException").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewScheduleJobExceptionInfo(selectedId);
	});
	function getSelectedIds(){
		var selectedIds = $("#scheduleJobExceptionList").getGridParam("selrow");
		return selectedIds;
	}
	

	$("#scheduleJobExceptionList").jqGridFunction({
		url:'${path}/judgmentAnalysis/scheduleJobException/findScheduleJobExceptionForPage.action',
		datatype: "json",
		postData: {},
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"appName",index:'appName',sortable:true,label:'运行服务器名称',align:'center',width:260},
      		{name:"jobName",index:"jobName",sortable:true,label:'调度名称',align:'center',width:165},
	        {name:'modelName',label:'业务模型名称',width:120,align:'center',sortable:true},
	        {name:'tableName',label:'维度表名',width:130,align:'center',sortable:true},
	        {name:'errorTime',label:'异常时间',width:130,align:'center',sortable:true}
		],
		multiselect:false,
		ondblClickRow: viewScheduleJobExceptionInfo
	});
	
});

function viewScheduleJobExceptionInfo(rowid){
	if(rowid==null){
 		return;
	}
	var scheduleJobException =  $("#scheduleJobExceptionList").getRowData(rowid);
	$("#viewScheduleJobExceptionMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'查看',
		modal : true,
		url:'${path}/judgmentAnalysis/scheduleJobException/viewScheduleJobException.action?mode=view&scheduleJobException.id='+scheduleJobException.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

$("#refresh").click(function(){
	$("#jobName").val('请输入调度名称');
	refresh();
})
function refresh(){
	var	postData = {
	}
	$("#scheduleJobExceptionList").setGridParam({
		url:'${path}/judgmentAnalysis/scheduleJobException/findScheduleJobExceptionForPage.action',
		datatype: "json",
		page:1
	});
	$("#scheduleJobExceptionList").setPostData(postData);
	$("#scheduleJobExceptionList").trigger("reloadGrid");
}
$("#refreshSearchKey").click(function(){
	$("#jobName").attr("value","请输入调度名称");
});

</script>
