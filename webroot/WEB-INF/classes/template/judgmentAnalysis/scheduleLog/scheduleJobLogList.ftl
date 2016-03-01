<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
        
         <div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入服务器名称" id="appName" class="searchtxt" onblur="value=(this.value=='')?'请输入服务器名称':this.value;" onfocus="value=(this.value=='请输入服务器名称')?'':this.value;"/>
				<button id="refreshSearchJobLogKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a id="fastSearchscheduleJobLog" href="javascript:void(0)"><span>搜索</span></a>		 
			<a id="viewJobLog" href="javascript:;"><span>查看</span></a>
			<a id="reloadJobLog" href="javascript:;"><span>刷新</span></a>
		</div>
        
        
        
   </div>
        

    <div class="">
		<table id="scheduleJobLogList"> </table>
		<div id="scheduleJobLogListPager"></div>
	</div>
   
    <div id="scheduleJobLogDialog" style="overflow-y:hidden"></div>

</div>

<script type="text/javascript">
        
   $(document).ready(function(){
        
        var jobLogcolModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},		         		      
		        {name:'appName',index:"appName",label:'运行服务器名称',sortable:true,width:120,align:"center"},
		        {name:'jobName',index:'jobName',label:'调度名称',sortable:true,width:120,align:"center"},
		        {name:'startTime',index:'startTime',label:'开始时间',sortable:true,width:120,align:"center"},    
		        {name:'endTime',index:'endTime',label:'结束时间',sortable:true,width:120,align:"center"},
		        {name:'exceptionNum',index:'exceptionNum',label:'异常数',sortable:true,width:120,align:"center"},
		        {name:'eachfethDate',index:'eachfethDate',label:'每次获取记录数',sortable:true,width:120,align:"center"},
		        {name:'listSize',index:'listSize',label:'返回结果数',sortable:true,width:120,align:"center"}
		         	  		        
			];
        
        $("#scheduleJobLogList").jqGridFunction({
			datatype: "local",
		   	colModel:jobLogcolModel,
			multiselect:true,
			showColModelButton:true,
			ondblClickRow:viewJobLogDialog		 
		});
		
		reloadJobLogList();
		
		function reloadJobLogList(){
		    var	postData = {"scheduleJobLog.scheduleLog.id":${id}};
			$("#scheduleJobLogList").setGridParam({
			url:'${path}/judgmentAnalysis/scheduleLogManage/findScheduleJobLogForPage.action',       
			datatype: "json",			 
			page:1
		    });		  
		    $("#scheduleJobLogList").setPostData(postData);  
		    $("#scheduleJobLogList").trigger("reloadGrid");
		}
		
	//刷新
	$("#reloadJobLog").click(function(){
		reloadJobLogList();
	});
 	 
	 //查看
	$("#viewJobLog").click(function(event){
	     viewJobLogDialog();	   
	 });
        
       $("#refreshSearchJobLogKey").click(function(){
			$("#appName").attr("value","请输入服务器名称");
		});
		
		$("#fastSearchscheduleJobLog").click(function(event){
			var appName = $("#appName").val();
		 	if(appName!=null&&"请输入服务器名称"!=appName){
		 	
				$("#scheduleJobLogList").setGridParam({
					url:'${path}/judgmentAnalysis/scheduleLogManage/findScheduleJobLogForPage.action',
					datatype: "json",
					postData: {
						"scheduleJobLog.appName":$("#appName").val()
					},
					page:1
				});
				$("#scheduleJobLogList").trigger("reloadGrid");
			}
		});   
        
        
        
        
    });
        
    //获取选中列的ID
	function getSelectedJobLogIds(){
		var selectedIds = $("#scheduleJobLogList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
    function viewJobLogDialog(){
	 
	  var selectedId = getSelectedJobLogIds();
	    
	    if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择要查看的信息！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条信息查看！"});
	 		return;
		}
		var scheduleJobLogCol =  $("#scheduleJobLogList").getRowData(selectedId);
	
		$("#scheduleJobLogDialog").createDialog({
			title:"查看日志",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/scheduleLogManage/dispatch.action?mode=viewJobLog',
			postData:{"id":scheduleJobLogCol.id},	
			buttons: {				 
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 
	 }    

</script>


