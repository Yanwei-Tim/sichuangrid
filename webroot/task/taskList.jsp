
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<div class="content">
	<div class="ui-corner-all" id="nav">
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="搜索：任务名称..." id="searchText"  name="searchText" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'搜索：任务名称...':this.value;" onfocus="value=(this.value=='搜索：任务名称...')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		 <pop:JugePermissionTag ename="searchTask">
         	<a href="javascript:;" id="fastSearchButton" >搜索</a>
         </pop:JugePermissionTag>
        <pop:JugePermissionTag ename="addTask">
        	<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
        </pop:JugePermissionTag>
        
        <pop:JugePermissionTag ename="deleteTask">
        	<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
        </pop:JugePermissionTag>
        <a id="changeStatus" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>关闭/开启</span></a>
        <a id="reloadJobInstance" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>重建job</span></a>
         <pop:JugePermissionTag ename="refreshTask">
	    	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>刷新</span></a>
	    </pop:JugePermissionTag>
	</s:if>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
    	<table id="taskList"></table>
    	<div id="taskListPager"></div>
	</div>
	<div id="taskDialog"></div>
	<div id="taskPloySelectDialog"></div>
</div>



<div style="display: none;">
	<pop:JugePermissionTag ename="taskListManagement">
		<span id="title"><s:property value="#request.name" /> </span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
	//窗口宽度大小常量
	var dialogWidth=600;
	var dialogHeight=330;
	
	//操作 formatter
	function operateFormatter(el, options, rowData){
		var change="";
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		var change = '<a href="javascript:updateTaskById('+rowData.id+')"  title="修改" class="operationBtn newRevise">修改</a> <span class="operationBtn newSline">|</span> ';
		if(rowData.closed == 1){
			change = '<span class="operationBtn newRevise" title="修改">修改</span> <span class="operationBtn newSline">|</span> ';
		}
		change = change + '<a href="javascript:deleteTaskById('+rowData.id+')" title="删除" class="newDel">删除</a>'
		</s:if>
		return change;
	}
	     //回车搜索事件
    $.showTxtValue('#searchText',{
		keyInputId:'#searchText',
		keyPressSearch:function(){
			fastSearch();
		}
	});
	function fastSearch(){
		var name = $('#searchText').val();
		if(name == '搜索：任务名称...'){
			name ='';
		}
		var initParam ={
			 'task.name':name
		};
	    $('#taskList').setPostData(initParam);
		$('#taskList').trigger('reloadGrid');
	}
	//关闭 formatter
	function closedFormatter(el,options,rowData){
		if(rowData.closed == 1){
			return '开启';
		}else{
			return '关闭';
		}
	}
	
	//策略formmatter
	function ployFormatter(el,options,rowData){
		if(el){
			return '';
		}
		return el.cname+'('+el.ename+')';
	}
	
	//通用 formmatter
	function commonFormatter(el,options,rowData){
		if(el == ''){
			return '-';
		}else{
			return el;
		}
	}
	
	function nameFormatter(el,options,rowData){
		return '<a href="javascript:viewTaskById('+rowData.id+')">'+rowData.name+'</a>';
	}
	//初始化表单
	$(function(){
		$("#documentTitle").text(document.title);
		//表格 jqGrid初始化
		$('#taskList').jqGridFunction({
			datatype: 'local',
		   	colModel:[
		   	       {name:'id',index:'id',sortable:false,hidden:true,hidedlg:true,key:true},
		   	    	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		   	      		 {name:'operation',label:"操作",formatter:operateFormatter,width:90,align:'center',frozen:true,sortable:false},
		   	      	</s:if>
			       {name:'name',label:"名称",index:'name',formatter:nameFormatter,frozen:true,sortable:true,width:260},
			       {name:'taskGroup',label:"所在组",index:'taskGroup',frozen:true,sortable:true,width:320},
			       {name:'taskPloy',label:"策略",index:'taskPloy',formatter:ployFormatter,frozen:true,sortable:true,width:200},
			       {name:'config',label:"时间",index:'config',frozen:true,sortable:false,width:120},
			       {name:'closed',label:"状态",index:'closed',formatter:closedFormatter,frozen:true,sortable:true,width:80}
		   	],
		   	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		   		multiselect:true,
		   	</s:if>
	        altclass:true,
	        onSelectRow:selectRow,
	        onSelectAll:selectRow,
	        showColModelButton:false
		});
		
		//加载数据
		$('#taskList').setGridParam({
			url:'${path}/task/taskManage/taskList.action',
			datatype: "json",
			page:1
		}).trigger("reloadGrid");
		
		//添加任务
		$('#add').click(function(){
			$('#taskDialog').createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'新增任务',
				url:'${path}/task/taskManage/dispatch.action?mode=add',
				buttons:{
					'确定':function(){
						$('#maintainForm').submit();
					},
					'关闭':function(){
						$(this).dialog('close');
					}
				}
			});
		});
		
		//修改任务
		$('#update').click(function(){
			 var id = $('#taskList').getSelectedRowId();
			 if(id){
				 $.messageBox({
						level:'warn',
						message:'请先选择一个任务'
				});
				 return;
			 }
			 updateTaskById(id);
		});
		
		//批量删除任务
		$('#delete').click(function(){
			var ids = $('#taskList').getSelectedRowIds();
			if(ids.length <=0 ){
				 $.messageBox({
						level:'warn',
						message:'请先选择一个任务'
				});
				 return;
			}
			var tasks = '';
			for(var i=0; i<ids.length; i++){
				if(tasks != '') tasks += '&';
				tasks+='tasks['+i+'].id='+ids[i];
			}
			$.confirm({
				title:'删除确认',
				message:'任务删除后不可恢复，请确认是否删除?',
				width: 400,
				okFunc: function(){
					$.ajax({
						url:'${path}/task/taskManage/deleteTasks.action?'+tasks,
						type:'get',
						dataType:'json',
						success : function(data){
							if(data){
								$.messageBox({
									message : '删除成功'
								});
								$('#taskList').trigger('reloadGrid');
							}else{
								$.messageBox({
									message : data,
									level : 'error'
								});
							}
						},
						error : function(){
							$.messageBox({
								message : '加载失败，请刷新页面！',
								level : 'error'
							});
						}
					});
				}
			});
		});
		
		// 更改状态
		$("#changeStatus").click(function(){
		    var selectedIds = $("#taskList").jqGrid("getGridParam", "selarrrow");
		    if(selectedIds.length==0){
	            $.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
	            return;
	        }else if(selectedIds.length>1){
                $.messageBox({level:'warn',message:"每次只能操作一条数据！"});
                return;
	        }
		    changeStatusById(selectedIds);
		});
		
		
		//重建job
		$('#reloadJobInstance').click(function(){
			$.confirm({
				title:'重建job确认',
				message:'重建job功能会自动将所有状态开启的job重新运行（慎用），建议在系统第一次运行时执行此功能。请确认执行？',
				width: 400,
				okFunc: function(){
					$.ajax({
						url:'${path}/task/taskManage/reloadJobs.action',
						success:function(data){
							if(data){
								$.messageBox({message : '重建job成功'});
							}else{
								$.messageBox({level : 'error', message : data});
							}
						}
					});
				}
			})
		});
		
		
		
		//搜索
		$('#fastSearchButton').click(fastSearch);

		//刷新
		$('#reload').click(function(){
			$("#searchText").val('搜索：任务名称...');
		    $('#taskList').setPostData({});
			$('#taskList').trigger('reloadGrid');
		});
		
		
	});
	//根据id修改
	function updateTaskById(id){
		var task =  $('#taskList').getRowData(id);
		$('#taskDialog').createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'编辑任务:'+task.name,
			url:'${path}/task/taskManage/dispatch.action?mode=edit&task.id='+task.id,
			buttons:{
				'确定':function(){
					$('#taskDlgForm form:visible').submit();
				},
				'关闭':function(){
					$(this).dialog('close');
				}
			}
		});
	}
	
	//根据id 删除
	function deleteTaskById(id){
		$.confirm({
			title:'删除确认',
			message:'任务删除后不可恢复，请确认是否删除?',
			width: 400,
			okFunc: function(){
				$.ajax({
					url:'${path}/task/taskManage/deleteTask.action?task.id='+id,
					type:'POST',
					dataType:'json',
					success : function(data){
						if(data){
							$.messageBox({message : '删除成功'});
							$('#taskList').trigger('reloadGrid');
						}else{
							$.messageBox({
								message : data,
								level : 'error'
							});
						}
					},
					error : function(){
						$.messageBox({
							message : '加载失败，请刷新页面！',
							level : 'error'
						});
					}
				});
			}
		});
	}
	
	// 更改状态
	function changeStatusById(id){
		var rowData = $('#taskList').getRowData(id);
		var closedStr = rowData.closed;
		var closed = 1;
		if(rowData.closed == "开启"){
			closed = 0;
			closedStr = "关闭";
		}else{
			closedStr = "开启";
		}
		$.confirm({
			title:closedStr+'确认',
			message:'请确认是否要'+closedStr+'任务?',
			width: 400,
			okFunc: function(){
				$.ajax({
					url:'${path}/task/taskManage/changeTask.action?task.id='+id+'&task.closed='+closed,
					type:'post',
					dataType:'json',
					success : function(data){
						if(data){
							$.messageBox({
								message : closedStr+'成功'
							});
							$('#taskList').trigger('reloadGrid');
						}else{
							$.messageBox({
								message : data,
								level : 'error'
							});
						}
					},
					error : function(){
						$.messageBox({
							message : '加载失败，请刷新页面！',
							level : 'error'
						});
					}
				});
			}
		});
	}
	
	//根据id查看
	function viewTaskById(id){
		var task =  $("#taskList").getRowData(id); //根据id获取jqGrid行数据
		$("#taskDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看任务:"+task.name,
			url:"${path}/task/taskManage/dispatch.action?mode=view&task.id="+task.id,
			buttons:{
				"关闭":function(){
					$(this).dialog("close");
				}
			}
		});
	}

	function selectRow(){
	    var selectedIds = $("#taskList").jqGrid("getGridParam", "selarrrow");
	    if(selectedIds.length==0){
	        $("#delete,#changeStatus").addClass("disabled");
	        return ;
	    }else if(selectedIds.length==1){
	        $("#delete,#changeStatus").removeClass("disabled");
	        return ;
        }else if(selectedIds.length>1){
            $("#delete").removeClass("disabled");
            $("#changeStatus").addClass("disabled");
            return ;
        }
	}
</script>
