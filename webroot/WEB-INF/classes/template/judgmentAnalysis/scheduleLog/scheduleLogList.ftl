<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
 
 
         <div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入任务名称" id="log_name" class="searchtxt" onblur="value=(this.value=='')?'请输入任务名称':this.value;" onfocus="value=(this.value=='请输入任务名称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a id="fastSearchscheduleLog" href="javascript:void(0)"><span>搜索</span></a>
			<a id="search" href="javascript:;"><span>高级搜索</span></a>
			<a id="view" href="javascript:;"><span>查看</span></a>
			<a id="reload" href="javascript:;"><span>刷新</span></a>
		</div>
         
   </div>

    <div class="">
		<table id="scheduleLogList"> </table>
		<div id="scheduleLogListPager"></div>
	</div>

    <div id="scheduleLogDialog" style="overflow-y:hidden"></div>
 
</div>

<script type="text/javascript">
        
   $(document).ready(function(){
        
        
        var colModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},		         		      
		        {name:'name',index:"name",label:'任务名称',sortable:true,width:120,align:"center"},
		        {name:'modelName',index:'modelName',label:'业务模型名',sortable:true,width:120,align:"center"},
		        {name:'tableName',index:'tableName',label:'维度表名',sortable:true,width:120,align:"center"},    
		        {name:'startTime',index:'startTime',label:'开始时间',sortable:true,width:120,align:"center"},
		        {name:'endTime',index:'endTime',label:'结束时间',sortable:true,width:120,align:"center"},
		        {name:'nextStartTime',nextStartTime:'type',label:'下次执行时间',sortable:true,width:120,align:"center"},
		        {name:'status',index:'status',label:'状态',sortable:true,width:120,align:"center",formatter:formatStatus},
		        {name:'createUser',index:'createUser',label:'创建人',sortable:true,width:120,align:"center"}, 
		        {name:'createDate',index:'createDate',label:'创建时间',sortable:true,width:120,align:"center"},    	  		        
			];
        
        $("#scheduleLogList").jqGridFunction({
			datatype: "local",
			postData:{
				"scheduleLog.startTime": function (){ return $("#startDate").val()},
				"scheduleLog.endTime":   function (){ return $("#endDate").val()},
				"scheduleLog.name":function (){ return $("#log_name").val()},
				"scheduleLog.modelName":  function (){ return $("#log_modelName").val()},
				"scheduleLog.status":  function (){ return $("#log_status").val()}
		    },
		   	colModel:colModel,
			multiselect:true,
			showColModelButton:true,
		    ondblClickRow:viewDialog		 
		});
		
		reloadList();
		
		function reloadList(){
		
		    var	postData = {};
			$("#scheduleLogList").setGridParam({
			url:'${path}/judgmentAnalysis/scheduleLogManage/findScheduleLogForPage.action',
			datatype: "json",
			page:1
		    });		  
		    $("#scheduleLogList").setPostData(postData);      
		    $("#scheduleLogList").trigger("reloadGrid");
		}
		
	function formatStatus(el,options,rowData){
	    var str="";
	    if(el==0){
	      str="进行中";
	    }else if(el==1){
	      str="失败";
	    }else if(el==2){
	      str="成功";
	    }
	    return str;
	}	
		
	//刷新
	$("#reload").click(function(){
		reloadList();
	});
 	 
 	  //查看
	$("#view").click(function(event){
	     viewDialog();	   
	 });
	 
	 //搜索
	$("#search").click(function(event){
	    $("#scheduleLogDialog").createDialog({
			title:"查询-请输入查询条件",
			width: 620,
			height: 240,
			url:'${path}/judgmentAnalysis/scheduleLogManage/dispatch.action?mode=search',
			buttons: {
				"查询" : function(event){
					searchPrimaryOrg();
					$(this).dialog("close");
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}
		});
	     
	 });
        
        
       $("#refreshSearchKey").click(function(){
			$("#log_name").attr("value","请输入任务名称");
		});
		
		$("#fastSearchscheduleLog").click(function(event){
			var log_name = $("#log_name").val();
		 	if(log_name!=null&&"请输入任务名称"!=log_name){
		 	
				$("#scheduleLogList").setGridParam({
					url:'${path}/judgmentAnalysis/scheduleLogManage/findScheduleLogForPage.action',
					datatype: "json",
					postData: {
						"scheduleLog.name":$("#log_name").val()
					},
					page:1
				});
				$("#scheduleLogList").trigger("reloadGrid");
			}
		});   
        
        
        
        
        
        
 });
        
    //获取选中列的ID
	function getSelectedIds(){
		var selectedIds = $("#scheduleLogList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
    function viewDialog(){
	 
	  var selectedId = getSelectedIds();
	    
	    if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择要查看的信息！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条信息查看！"});
	 		return;
		}
		var scheduleLogCol =  $("#scheduleLogList").getRowData(selectedId);
	
		$("#scheduleLogDialog").createDialog({
			title:"查看日志详情",
			width: 740,
			height: 620,		 
			url:'${path}/judgmentAnalysis/scheduleLogManage/dispatch.action?mode=listJobLog',
			postData:{"id":scheduleLogCol.id},	
			buttons: {				 
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 
	 }    
 

   function searchPrimaryOrg(){
 
        
		$("#scheduleLogList").setGridParam({
			url:"${path}/judgmentAnalysis/scheduleLogManage/findScheduleLogForPage.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		
		var data=$("#searchPrimaryOrgForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
	 		if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		 $("#scheduleLogList").setPostData(dataJson);
		 $("#scheduleLogList").trigger("reloadGrid");
	 
	 
}

</script>


