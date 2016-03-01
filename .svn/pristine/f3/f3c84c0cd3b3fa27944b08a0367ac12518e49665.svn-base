<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="content">
 
   <div class="ui-corner-all contentNav" id="nav">
 
       <div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入名称" id="modelName" class="searchtxt" onblur="value=(this.value=='')?'请输入名称':this.value;" onfocus="value=(this.value=='请输入名称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a id="fastSearchbusinessModel" href="javascript:void(0)"><span>搜索</span></a>
		</div>
 
		<a id="add" href="javascript:;"><span>新增</span></a>
		<a id="update" href="javascript:;"><span>修改</span></a>
		<a id="delete" href="javascript:;"><span>删除</span></a>
        <a id="view" href="javascript:;"><span>查看</span></a>
        <a id="reload" href="javascript:;"><span>刷新</span></a>
        
   </div>

    <div class="">
		<table id="businessModelList"> </table>
		<div id="businessModelListPager"></div>
	</div>

    <div id="businessModelDialog" style="overflow-y:hidden"></div>

</div>

<script type="text/javascript">
        
   $(document).ready(function(){
        
        var colModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},		         		      
		        {name:'name',index:"name",label:'业务模块名称',sortable:true,width:200},
		        {name:'keyName',index:'keyName',label:'关键字',sortable:true,width:120},    
		        {name:'type',index:'type',label:'类型',sortable:true,width:70,align:"center"} 		        
			];
        
        $("#businessModelList").jqGridFunction({
			datatype: "local",
		   	colModel:colModel,
			multiselect:true,
			showColModelButton:true,
			ondblClickRow:viewDialog		 
		});
		
		reloadList();
		
		function reloadList(){
		    var	postData = {};
			$("#businessModelList").setGridParam({
			url:'${path}/judgmentAnalysis/businessModelManage/findBusinessModelForPage.action',
			datatype: "json",			 
			page:1
		    });		
		    $("#businessModelList").setPostData(postData);    
		    $("#businessModelList").trigger("reloadGrid");
		}
		
	//刷新
	$("#reload").click(function(){
		reloadList();
	});
		
        
    //新增按钮事件
	$("#add").click(function(event){
		$("#businessModelDialog").createDialog({
			title:"新增业务模块",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/businessModelManage/dispatch.action?mode=add',
			buttons: {
				"保存并关闭" : function(event){
					$("#isSubmit").val("true");
		   			$("#businessModelForm").submit();
		   			
				},
				"保存并继续" : function(event){
					$("#isSubmit").val("false");
		   			$("#businessModelForm").submit();
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
		var businessModelCol =  $("#businessModelList").getRowData(selectedId);
	
		$("#businessModelDialog").createDialog({
			title:"修改业务模块",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/businessModelManage/dispatch.action?mode=edit',
			postData:{"id":businessModelCol.id},	
			buttons: {
				"修改" : function(event){
					$("#isSubmit").val("true");
		   			$("#businessModelForm").submit();		   			
				},
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 });
	 
	 
	 //查看
	$("#view").click(function(event){
	     viewDialog();	   
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
					url:'${path}/judgmentAnalysis/businessModelManage/deleteBusinessModelByIds.action?ids='+selectedId,
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删除!"});
							$("#businessModelList").trigger("reloadGrid");
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
        
        $("#refreshSearchKey").click(function(){
			$("#modelName").attr("value","请输入名称");
		});
		
		$("#fastSearchbusinessModel").click(function(event){
			var modelName = $("#modelName").val();
		 	if(modelName!=null&&"请输入名称"!=modelName){
				$("#businessModelList").setGridParam({
					url:'${path}/judgmentAnalysis/businessModelManage/findBusinessModelForPage.action',
					datatype: "json",
					postData: {
						"businessModel.name":$("#modelName").val()
					},
					page:1
				});
				$("#businessModelList").trigger("reloadGrid");
			}
		});
        
    });
        
    //获取选中列的ID
	function getSelectedIds(){
		var selectedIds = $("#businessModelList").jqGrid("getGridParam", "selarrrow");
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
		var businessModelCol =  $("#businessModelList").getRowData(selectedId);
	
		$("#businessModelDialog").createDialog({
			title:"查看业务模块",
			width: 640,
			height: 320,
			url:'${path}/judgmentAnalysis/businessModelManage/dispatch.action?mode=view',
			postData:{"id":businessModelCol.id},	
			buttons: {				 
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	 
	 }    

</script>


