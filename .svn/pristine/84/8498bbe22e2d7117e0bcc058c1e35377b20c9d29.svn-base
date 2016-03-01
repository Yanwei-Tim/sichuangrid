<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
		<div class="content"> 
		<div class="ui-corner-all contentNav" id="nav" >
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<select class="basic-input" id="jobRunType">
	 					<option value="">全部</option>
	 					<option value="0" selected="selected">quartz</option>
	 					<option value="1">TBSchedule</option>
					</select>
					<select class="basic-input" id="jobEnable">
	 					<option value="">全部</option>
	 					<option value="true" selected="selected">启用</option>
	 					<option value="false">失效</option>
					</select>
					<input class="basic-input" type="text" value="请输入名称" id="name" class="searchtxt" onblur="value=(this.value=='')?'请输入名称':this.value;" onfocus="value=(this.value=='请输入名称')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
				<a id="fastSearchScheduleJob" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<span class="lineBetween "></span>
			<a id="add" href="javascript:void(0)"><span>新增</span></a>
			<a id="lookScheduleJob" href="javascript:void(0)"><span>查看</span></a>
			<a id="scheduleJobStart" href="javascript:void(0)"><span>启动</span></a>
			<a id="scheduleJobStop" href="javascript:void(0)"><span>停止</span></a>
			<a id="scheduleJobSync" href="javascript:void(0)"><span>同步</span></a>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="scheduleJobList"> </table>
			<div id="scheduleJobListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<div id="scheduleJobMaintanceDialog"></div>
<div id="viewScheduleJobMaintanceDialog"></div>
<script type="text/javascript">
function checkJobStatus(){
	var scheduleJobRows = $("#scheduleJobList").getRowData();
	var selectedRowIds = getSelectedIds();
	var ids = '';
	for(var i=0;i<scheduleJobRows.length;i++){
		$("#scheduleJobList").setRowData(scheduleJobRows[i].id, {'state': '检测中...'});
		if((","+selectedRowIds+",").indexOf(scheduleJobRows[i].id) != -1){
			$("#scheduleJobList").setSelection(scheduleJobRows[i].id);
		}
		ids += scheduleJobRows[i].id +",";
	}
	$.post('${path}/judgmentAnalysis/scheduleJob/scheduleJobIsStart.action?combinationIds='+ids, function (data){
		var statusHtml = '<img src="${path}/resource/judgmentAnalysis/img/job_error.png" title="错误"/>';
		var scheduleJobRows = $("#scheduleJobList").getRowData();
		for(var i=0;i<scheduleJobRows.length;i++){
			if((','+data).indexOf(','+scheduleJobRows[i].id+':') == -1){
				$("#scheduleJobList").setRowData(scheduleJobRows[i].id, {'state': statusHtml});
			}
		}
		if(data.indexOf("errorCode") != -1){
			$.messageBox({level:"error",message: data});			
	 		return;
		}
		var status = data==null? [] : data.split(",");
		for(var i=0;i<status.length;i++){
			var rowStatus = status[i].split(":");
			if(rowStatus.length == 2){
				if(rowStatus[1] == 0){
					statusHtml = '<img src="${path}/resource/judgmentAnalysis/img/job_start.png" title="正常"/>';
				}else if(rowStatus[1] == 1){
					statusHtml = '<img src="${path}/resource/judgmentAnalysis/img/job_stop.png" title="停止"/>';
				}
				$("#scheduleJobList").setRowData(rowStatus[0], {'state': statusHtml});
			}else{
				$("#scheduleJobList").setRowData(rowStatus[0], {'state': '检查失败'});
			}
		}
	});
}
$(document).ready(function(){
	$("#fastSearchScheduleJob").click(function(event){
		refresh();
	});

	$("#deleteScheduleJobById").click(function(){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		delScheduleJob(selectedRowId1);
	});

	$("#add").click(function(event){
		if($("#add").attr("disabled")){
			return ;
		}
		$("#scheduleJobMaintanceDialog").createDialog({
			width: 580,
			height: 350,
			title:'新增',
			url:'${path}/judgmentAnalysis/scheduleJob/addScheduleJobView.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#scheduleJobform").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#update").click(function(event){
		var selectedId=getSelectedIds();
		if(selectedId==null || '' == selectedId){
	 		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		updateScheduleJob(selectedId);
	});
	
	$("#scheduleJobStart").click(function (){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.post('${path}/judgmentAnalysis/scheduleJob/scheduleJobStart.action?combinationIds='+selectedId, function (data){
			if(data == 'true' || data === true){
	 			$.messageBox({level:"success",message:"操作成功！"});
			}else{
	 			$.messageBox({level:"error", message: data});
			}
			checkJobStatus();
		});
	});
	
	$("#scheduleJobStop").click(function (){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.post('${path}/judgmentAnalysis/scheduleJob/scheduleJobStop.action?combinationIds='+selectedId, function (data){
			if(data == 'true' || data === true){
	 			$.messageBox({level:"success",message:"操作成功！"});
			}else{
	 			$.messageBox({level:"error", message: data});
			}
			checkJobStatus();
		});
	});
	
	$("#scheduleJobSync").click(function (){
		$.confirm({
		title:"确认同步",
		message:"确认同步？",
		width: 300,
		okFunc: function(){
			$.post('${path}/judgmentAnalysis/scheduleJob/scheduleJobSync.action', function (data){
				if(data == 'true' || data === true){
		 			$.messageBox({level:"success",message:"操作成功！"});
				}else{
		 			$.messageBox({level:"error", message: data});
				}
				checkJobStatus();
			});
		}});
	});
	
	$("#lookScheduleJob").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewScheduleJobInfo(selectedId);
	});

	$("#scheduleJobList").jqGridFunction({
		url:'${path}/judgmentAnalysis/scheduleJob/findScheduleJobForPage.action',
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operation",index:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
	        {name:"name",index:'name',sortable:true,label:'名称',align:'center',width:120,formatter:nameFormatter},
      		{name:"cronExpression",index:"cronExpression",sortable:true,label:'执行时间',align:'center',width:100},
      		{name:"nextStartTime", sortable:true, label:'下次执行时间',align:'center',width:160},
	        {name:"runType",index:"runType",label:'运行方式',sortable:true,align:'center',width:100, formatter: function (val){return val == 1 ? 'TBSchedule' : (val == 0 ? 'quartz' : '配置错误')}},
	        {name:'currentCycle',label:'游标',width:80,align:'center',sortable:true},
	        {name:'beanName',label:'bean名称',width:252,align:'center',sortable:true},
	        {name:'state',label:'状态',width:80,align:'center',sortable: false},
	        {name:'enable',label:'是否启用',width:80,align:'center',sortable:true,formatter: function(el, options, rowData){
	        	if(rowData.enable){
	        		return "启用";
	        	}else{
	        		return "失效";
	        	}
	        }}
		],
		multiselect:true ,
		ondblClickRow: viewScheduleJobInfo,
		loadComplete: function (){
			checkJobStatus();
		}
	});
	
	$("#refresh").click(function(){
		$("#name").val('请输入名称');
		$("#jobEnable,#jobRunType").val('');
		refresh();
	});
	
	$("#refreshSearchKey").click(function(){
		$("#name").attr("value","请输入名称");
	});
	refresh();
});

function getSelectedIds(){
	var selectedIds = $("#scheduleJobList").getGridParam("selarrrow");
	return selectedIds;
}

function operateFormatter(el,options,rowData){
	return "<a href='javascript:updateScheduleJob("+rowData.id+")'><span>修改</span></a>&nbsp;&nbsp;<a href='javascript:delScheduleJob("+rowData.id+")'><span>删除</span></a>";
}

function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewScheduleJobInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
} 

function viewScheduleJobInfo(rowid){
	if(rowid==null){
 		return;
	}
	var scheduleJob =  $("#scheduleJobList").getRowData(rowid);
	$("#viewScheduleJobMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'查看',
		modal : true,
		url:'${path}/judgmentAnalysis/scheduleJob/viewScheduleJob.action?mode=view&scheduleJob.id='+scheduleJob.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateScheduleJob(rowid){
	if(rowid==null){
		return;
	}
	var scheduleJob =  $("#scheduleJobList").getRowData(rowid);
	$("#scheduleJobMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'修改',
		url:'${path}/judgmentAnalysis/scheduleJob/updateScheduleJobView.action?mode=edit&scheduleJob.id='+scheduleJob.id,
		buttons: {
	   		"保存" : function(event){
		   		$("#scheduleJobform").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function delScheduleJob(rowid){
	if(rowid==null){
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"删除后就不能还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/judgmentAnalysis/scheduleJob/deleteScheduleJobById.action?id="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#scheduleJobList").trigger("reloadGrid");
					$.messageBox({message:"删除成功！"});
				}
			});
		}
	});
}

function refresh(){
	var	postData = {
		'scheduleJob.runType': $("#jobRunType").val(),
		'scheduleJob.name': $("#name").val() == '请输入名称' ? '' : $("#name").val(),
		'scheduleJob.enable': $("#jobEnable").val()
	}
	if( $("#jobEnable").val() == ''){
		delete postData['scheduleJob.enable'];
	}
	$("#scheduleJobList").setGridParam({
		url:'${path}/judgmentAnalysis/scheduleJob/findScheduleJobForPage.action',
		datatype: "json",
		page:1
	});
	$("#scheduleJobList").setPostData(postData);
	$("#scheduleJobList").trigger("reloadGrid");
}

</script>
