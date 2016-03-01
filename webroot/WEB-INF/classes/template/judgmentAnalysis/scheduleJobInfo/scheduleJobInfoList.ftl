<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
   
     <div class="btnbanner btnbannerData">
       <div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入组织机构分组" id="jobInfo_name" class="searchtxt" onblur="value=(this.value=='')?'请输入组织机构分组':this.value;" onfocus="value=(this.value=='请输入组织机构分组')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchscheduleJobInfo" href="javascript:void(0)"><span>搜索</span></a>
	  </div>		
	 
	    <a id="search" href="javascript:;"><span>高级搜索</span></a>
        <a id="add" href="javascript:;"><span>新增</span></a>
		<a id="update" href="javascript:;"><span>修改</span></a>
		<a id="delete" href="javascript:;"><span>删除</span></a>
        <a id="reload" href="javascript:;"><span>刷新</span></a>
        <a id="view" href="javascript:;"><span>查看</span></a>
   </div>

    <div class="">
		<table id="scheduleJobInfoList"> </table>
		<div id="scheduleJobInfoListPager"></div>
	</div>

    <div id="scheduleJobInfoDialog" style="overflow-y:hidden"></div>
    <div id="scheduleJobSelectDialog"></div>
    
</div>

<script type="text/javascript">
        
   $(document).ready(function(){
                    
        var colModel=[
		        {name:"id",index:"id",label:'aaaa',sortable:false,width:120},		         		      
		        {name:'groupName',index:"groupName",label:'组织机构分组',sortable:true,width:120,align:"center"},
		        {name:'saveFlag',index:'saveFlag',label:'是否保存',sortable:true,width:120,align:"center",formatter:formatSaveFlag},
		        {name:'batchNum',index:'batchNum',label:'批量数',sortable:true,width:120,align:"center"},    
		        {name:'type',index:'type',label:'执行类型',sortable:true,width:120,align:"center",formatter:formatType},
		        {name:'sqlType',index:'sqlType',label:'SQL类型',sortable:true,width:120,align:"center",formatter:formatSqlType},
		        {name:'ordered',index:'ordered',label:'执行顺序',sortable:true,width:120,align:"center"},
		        {name:'createDate',index:'createDate',label:'创建时间',sortable:true,width:120,align:"center"}		        	        
			];
        
        $("#scheduleJobInfoList").jqGridFunction({
			datatype: "local",
		   	colModel:colModel,
			multiselect:true,
			showColModelButton:true,
			ondblClickRow:viewDialog		 
		});
		
		reloadList();
		
		function reloadList(){
		    var	postData = {};
			$("#scheduleJobInfoList").setGridParam({
			url:'${path}/judgmentAnalysis/scheduleJobInfoManage/findScheduleJobInfoForPage.action',
			datatype: "json",
			page:1
		    });	
		    $("#scheduleJobInfoList").setPostData(postData);   	    
		    $("#scheduleJobInfoList").trigger("reloadGrid");
		}
		
	function formatSaveFlag(el,options,rowData){
	    var str="";
	    if(el==0){
	      str="不保存";
	    }else if(el==1){
	      str="保存";
	    }
	    return str;
	}
	
	function formatType(el,options,rowData){
	    var str="";
	    if(el==1){
	      str="JAVA";
	    }else if(el==0){
	      str="SQL";
	    }
	    return str;
	
	}
	
	function formatSqlType(el,options,rowData){
	    var str="";
	    if(el==1){
	      str="新增";
	    }else if(el==2){
	      str="查询";
	    }else if(el==3){
	      str="修改";
	    }else if(el==4){
	      str="删除";
	    }
	    return str;
	
	}	
		
	$("#refreshSearchKey").click(function(){
			$("#jobInfo_name").attr("value","请输入组织机构分组");
	});	
		
	$("#fastSearchscheduleJobInfo").click(function(event){
			var jobInfo_name = $("#jobInfo_name").val();
		 	if(jobInfo_name!=null&&"请输入组织机构分组"!=jobInfo_name){
		 	
				$("#scheduleJobInfoList").setGridParam({
					url:'/judgmentAnalysis/scheduleJobInfoManage/findScheduleJobInfoForPage.action',
					datatype: "json",
					postData: {
						"scheduleJobInfo.groupName":$("#jobInfo_name").val()
					},
					page:1
				});
				$("#scheduleJobInfoList").trigger("reloadGrid");
			}
		});  	
		
		
	//刷新
	$("#reload").click(function(){
		reloadList();
	});
 
    //搜索
	$("#search").click(function(event){
	    $("#scheduleJobInfoDialog").createDialog({
			title:"查询-请输入查询条件",
			width: 620,
			height: 240,
			url:'${path}/judgmentAnalysis/scheduleJobInfoManage/dispatch.action?mode=search',
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
	  
	 
	//新增按钮事件
	$("#add").click(function(event){
		$("#scheduleJobInfoDialog").createDialog({
			title:"新增任务",
			width: 640,
			height: 360,
			url:'${path}/judgmentAnalysis/scheduleJobInfoManage/dispatch.action?mode=add',
			buttons: {
				"保存" : function(event){				 
		   			$("#scheduleJobInfoForm").submit();		   			
				},
				"关闭" : function(event){
					 $(this).dialog("close");
				}
			}
		});
	 });
	 
	//修改按钮事件
	$("#update").click(function(event){
	
	    var selectedId = getSelectedIds();
	    
	    if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择要修改的信息！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条信息修改！"});
	 		return;
		}
		var jobInfoModelCol =  $("#scheduleJobInfoList").getRowData(selectedId);
		$("#scheduleJobInfoDialog").createDialog({
			title:"修改",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/scheduleJobInfoManage/dispatch.action?mode=edit',
			postData:{"id":jobInfoModelCol.id},	
			buttons: {
				"修改" : function(event){					 
		   			$("#scheduleJobInfoForm").submit();		   			
				},
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 });
        
    //删除
	$("#delete").click(function(event){
	
	    var selectedId = getSelectedIds();
	    
	    if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择要删除的信息！"});
	 		return;
		}
 	
		 $.confirm({
		    title:"确认删除",
		    message:"确定要删除吗?",
		    okFunc: function(){
		    
			    $.ajax({
					url:'${path}/judgmentAnalysis/scheduleJobInfoManage/deleteScheduleJobInfoById.action?id='+selectedId,
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删除!"});
							$("#scheduleJobInfoList").trigger("reloadGrid");
						}else{
							$.messageBox({
								message:"删除失败,请重新删除!",
								level:"warn"
							});
						}
					}
				});
			
		    }
		 });
		 
		 
	 });
        
     //查看
	 $("#view").click(function(event){
	     viewDialog();	   
	 });
        
 });
        
    //获取选中列的ID
	function getSelectedIds(){
		var selectedIds = $("#scheduleJobInfoList").jqGrid("getGridParam", "selarrrow");
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
		var scheduleJobInfoCol =  $("#scheduleJobInfoList").getRowData(selectedId);
	
		$("#scheduleJobInfoDialog").createDialog({
			title:"查看任务",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/scheduleJobInfoManage/dispatch.action?mode=view',
			postData:{"id":scheduleJobInfoCol.id},	
			buttons: {				 
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 
	 }    

 
    function searchPrimaryOrg(){
         
		$("#scheduleJobInfoList").setGridParam({
			url:"${path}/judgmentAnalysis/scheduleJobInfoManage/findScheduleJobInfoForPage.action",
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
		 $("#scheduleJobInfoList").setPostData(dataJson);
		 $("#scheduleJobInfoList").trigger("reloadGrid");
	 
	 
}



</script>


