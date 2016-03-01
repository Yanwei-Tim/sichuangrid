<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<div class="content">
	<div class="ui-corner-all" id="nav">
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		<div class="btnbanner btnbannerData">
			<select class="newChoosePeople" id="taskPloyType">
	        	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TASKPLOY_TYPE" />
	        </select>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入搜索关键字" id="searchText" maxlength="18" name="searchText"  class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入搜索关键字':this.value;" onfocus="value=(this.value=='请输入搜索关键字')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<pop:JugePermissionTag ename="searchTaskPloy">
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		</pop:JugePermissionTag>
        <pop:JugePermissionTag ename="addTaskPloy">
        	<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
        </pop:JugePermissionTag>
        
        <pop:JugePermissionTag ename="deleteTaskPloy">
        	<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
        </pop:JugePermissionTag>
         <pop:JugePermissionTag ename="refreshTaskPloy">
	    	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>刷新</span></a>
	    </pop:JugePermissionTag>
	</s:if>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
    	<table id="taskPloyList"></table>
    	<div id="taskPloyListPager"></div>
	</div>
	<div id="taskPloyDialog"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="taskPloyListManagement">
		<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
	//弹出框大小常量
	var dialogWidth=650;
	var dialogHeight=400;
	//标签生成jqGrid的formatter字段方法
	<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@TASKPLOY_TYPE" />
	//操作formatter方法
	function operateFormatter(el, options, rowData){
		var result="";
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		result = '<a href="javascript:updateTaskPloyById('+rowData.id+')"  title="修改" class="operationBtn newRevise">修改</a> <span class="operationBtn newSline">|</span> <a href="javascript:deleteTaskPloyById('+rowData.id+')"  title="删除"     class="operationBtn newDel">删除</a>';
		</s:if>
		return result;
	}
	function cnameFormatter(el,options,rowData){
		return '<a href="javascript:viewTaskPloyById('+rowData.id+')">'+rowData.cname+'</a>';
	}
	 //回车搜索事件
        $.showTxtValue('#searchText',{
		keyInputId:'#searchText',
		keyPressSearch:function(){
			fastSearch();
		}
	});
	function fastSearch(){	
			var type = $('#taskPloyType').val() || '';
			var key = $('#searchText').val() || '';
			if(key == '请输入搜索关键字'){
				key ='';
			}
			var initParam ={
				'taskPloy.type.id':type,
				'taskPloy.cname':key,
				'taskPloy.ename':key,
				'taskPloy.description':key
			};
		    $("#taskPloyList").setPostData(initParam);
			$("#taskPloyList").trigger("reloadGrid");
}
	//页面加载初始操作
	$(function(){
		$("#documentTitle").text(document.title);
		//初始化jqGrid表格
		$("#taskPloyList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		   	       {name:"id",label:"id",index:"id",sortable:false,hidden:true,hidedlg:true,key:true},
		   	       <s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
		   	      	 {name:'operation',label:"操作",formatter:operateFormatter,width:90,frozen:true,sortable:false},
		   	       </s:if>
			       {name:'cname',label:"中文名称",index:'cname',formatter:cnameFormatter,frozen:true,sortable:true,width:260},
			       {name:'ename',label:"英文名称",index:'ename',frozen:true,sortable:true,width:350},
			       {name:'type',label:"类型",index:'type',formatter:typeFormatter,frozen:true,sortable:true,width:120},
			       {name:'description',label:"策略描述",index:'description',frozen:true,sortable:true,width:300}
		   	],
		    <s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
				multiselect:true,
            </s:if>
		   	   ondblClickRow:viewTaskPloyById,
		   	   showColModelButton:false
		});
		
		//加载数据
		$("#taskPloyList").setGridParam({
			url:'${path}/task/taskPloyManage/taskPloyList.action',
			datatype: "json",
			page:1
		}).trigger("reloadGrid");
		
		//查看简介
		$("#introduction").click(function(){
			$("#taskPloyDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"查看简介",
				modal:true,
				url:"${path}/task/taskPloyManage/dispatch.action?mode=read",
				buttons:{
					"关闭":function(){
						$(this).dialog("close");
					}
				}
			});
		});
		
		//添加策略
		$("#add").click(function(){
			$("#taskPloyDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"新增策略",
				url:"${path}/task/taskPloyManage/dispatch.action?mode=add",
				buttons:{
					"确定":function(){
						$("#maintainForm").submit();
					},
					"关闭":function(){
						$(this).dialog("close");
					}
				}
			});
		});
		
		//修改策略
		$('#update').click(function(){
			 var id = $("#taskPloyList").getSelectedRowId() || false; //获取选中项id，多个选中时获取的是最后一个
			 if (id) {
				$.messageBox({
					level:'warn',
					message:'请先选择一个策略操作'
				});
				return;
			 }
			 updateTaskPloyById(id);
		});
		
		//批量删除
		$('#delete').click(function(){
			var ids = $("#taskPloyList").getSelectedRowIds() || [];//获取所有选中项
			if(ids.length <=0 ){
				 $.messageBox({
						level:'warn',
						message:'请先选择一个策略操作'
				});
				 return;
			}
			var taskPloys = '';
			for(var i = 0; i<ids.length; i++){
				if(taskPloys != '') taskPloys += '&';
				taskPloys += 'taskPloys['+i+'].id='+ids[i];
			}
			$.confirm({
				title:"删除确认",
				message:"策略删除后不可恢复，请确认是否删除?",
				width: 400,
				okFunc: function(){
					$.ajax({
						url:'${path}/task/taskPloyManage/deleteTaskPloys.action?'+taskPloys,
						type:'POST',
						dataType:'json',
						success : function(data){
							if(data){
								$.messageBox({
									message : "删除成功"
								});
								$("#taskPloyList").trigger("reloadGrid");
							}else{
								$.messageBox({
									message : data,
									level : "error"
								});
							}
						},
						error : function(){
							$.messageBox({
								message : "加载失败，请刷新页面！",
								level : "error"
							});
						}
					});
				}
			});
		});
		
		//搜索
		$('#fastSearchButton').click(fastSearch);
		
		
		//刷新
		$('#reload').click(function(){
			$('#taskPloyType').val('');
	        $("#taskPloyType").next(".ui-select").remove();
			$('#searchText').val('请输入搜索关键字');
		    $("#taskPloyList").setPostData({});
			$("#taskPloyList").trigger("reloadGrid");
		});
		
		// 下拉框选择
	    $("#taskPloyType").change(function(){
	        fastSearch();
	    });
	});
	
	//根据id修改
	function updateTaskPloyById(id){
		var taskPloy =  $("#taskPloyList").getRowData(id); //根据id获取jqGrid行数据
		$("#taskPloyDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"编辑策略:"+taskPloy.cname+'('+taskPloy.ename+')',
			url:"${path}/task/taskPloyManage/dispatch.action?mode=edit&taskPloy.id="+taskPloy.id,
			buttons:{
				"确定":function(){
					$("#taskPloyDlgForm form:visible").submit();
				},
				"关闭":function(){
					$(this).dialog("close");
				}
			}
		});
	}

	//根据id 删除
	function deleteTaskPloyById(id){
		$.confirm({
			title:"删除确认",
			message:"策略删除后不可恢复，请确认是否删除?",
			width: 400,
			okFunc: function(){
				$.ajax({
					url:'${path}/task/taskPloyManage/deleteTaskPloy.action?taskPloy.id='+id,
					type:'POST',
					dataType:'json',
					success : function(data){
						if(data){
							$.messageBox({
								message : "删除成功"
							});
							$("#taskPloyList").trigger("reloadGrid");
						}else{
							$.messageBox({
								message : data,
								level : "error"
							});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
			}
		});
	}
	
	//根据id查看
	function viewTaskPloyById(id){
		var taskPloy =  $("#taskPloyList").getRowData(id); //根据id获取jqGrid行数据
		$("#taskPloyDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看策略:"+taskPloy.cname+'('+taskPloy.ename+')',
			url:"${path}/task/taskPloyManage/dispatch.action?mode=view&taskPloy.id="+taskPloy.id,
			buttons:{
				"关闭":function(){
					$(this).dialog("close");
				}
			}
		});
	}
</script>
