<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
 
       <div class="btnbanner btnbannerData">
         
             <div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入任务名称" id="job_name" class="searchtxt" onblur="value=(this.value=='')?'请输入任务名称':this.value;" onfocus="value=(this.value=='请输入任务名称')?'':this.value;"/>
				<button id="refreshSearchKeyJob" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a id="fastSearchscheduleJob" href="javascript:void(0)"><span>搜索</span></a>	
  		 
		  <a id="reloadJob" href="javascript:;"><span>刷新</span></a>        
       </div>
		
	  <div class='clearLine'>&nbsp;</div>
     
   </div>

    <div class="">
		<table id="scheduleJobList"> </table>
		<div id="scheduleJobListPager"></div>
	</div>

    <div id="scheduleJobDialog" style="overflow-y:hidden"></div>
 
</div>

<script type="text/javascript">
        
   $(document).ready(function(){
                    
        var jobColModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},		         		      
		        {name:'name',index:"name",label:'任务名字',sortable:true,width:120,align:"center"},
		        {name:'cronExpression',index:'cronExpression',label:'执行时间',sortable:true,width:120,align:"center"},
		        {name:'runType',index:'runType',label:'运行方式',sortable:true,width:120,align:"center", formatter: function (val){return val == 1 ? 'TBSchedule' : (val == 0 ? 'quartz' : '配置错误')}},    
		        {name:'businessModel.name',index:'businessModel.name',label:'业务模型',sortable:true,width:120,align:"center"},
		        {name:'enable',index:'enable',label:'是否启用',sortable:true,width:120,align:"center",formatter: function(el, options, rowData){
		        	if(rowData.enable){
		        		return "启用";
		        	}else{
		        		return "失效";
		        	}
		        }}		                	        
			];
        
        $("#scheduleJobList").jqGridFunction({
			datatype: "local",			 
		   	colModel:jobColModel,
			multiselect:true,
			showColModelButton:true,
			shrinkToFit:true,
			ondblClickRow:function(rowId){
				var scheduleJob =  $('#scheduleJobList').getRowData(rowId);
				$('#scheduleJobId').val(scheduleJob.id);
				$('#jobSelect').attr('scheduleJobId',scheduleJob.id).val(scheduleJob.name);
				$('#scheduleJobSelectDialog').dialog('close');
			}  
		});
		
		reloadJobList();
		
		function reloadJobList(){
		    var	postData = {};
			$("#scheduleJobList").setGridParam({
			url:'${path}/judgmentAnalysis/scheduleJob/findScheduleJobForPage.action',
			datatype: "json",
			page:1
		    });		    
		    $("#scheduleJobList").setPostData(postData);
		    $("#scheduleJobList").trigger("reloadGrid");
		}
		
	//刷新
	$("#reloadJob").click(function(){
		reloadJobList();
	});
  
	 
        $("#refreshSearchKeyJob").click(function(){
			$("#job_name").attr("value","请输入任务名称");
		});
		
		$("#fastSearchscheduleJob").click(function(event){
			var job_name = $("#job_name").val();
		 	if(job_name!=null&&"请输入任务名称"!=job_name){
		 	
				$("#scheduleJobList").setGridParam({
					url:'${path}/judgmentAnalysis/scheduleJob/findScheduleJobForPage.action',
					datatype: "json",
					postData: {
						"scheduleJob.name":$("#job_name").val()
					},
					page:1
				});
				$("#scheduleJobList").trigger("reloadGrid");
			}
		});   
        
        
 });
        
    //获取选中列的ID
	function getJobSelectedIds(){
		var selectedIds = $("#scheduleJobList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
    function viewJobDialog(){
	 
	  var selectedId = getJobSelectedIds();
	    
	    if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择要查看的信息！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条信息查看！"});
	 		return;
		}
		var scheduleJobCol =  $("#scheduleJobList").getRowData(selectedId);
	
		$("#scheduleJobDialog").createDialog({
			title:"查看任务",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/scheduleJobManage/dispatch?mode=view',
			postData:{"id":scheduleJobCol.id},	
			buttons: {				 
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 
	 }    

 



</script>


