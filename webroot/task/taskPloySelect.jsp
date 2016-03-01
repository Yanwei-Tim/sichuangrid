<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<div class="content">
	<div class="ui-corner-all" id="nav">
		<!-- 搜索数据 -->
		<div class="btnbanner btnbannerData">
			<select id="taskPloyType">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TASKPLOY_TYPE" />
			</select>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入搜索关键字" id="taskPloyKey" maxlength="50" class="searchtxt"
					onblur="value=(this.value=='')?'请输入搜索关键字':this.value;"
					onfocus="value=(this.value=='请输入搜索关键字')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<!-- 操作按钮 -->
		<a href="javascript:;" id="fastSearchButtonSelect"><span>搜索</span> </a>
		<a id="reloadSelect" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span> </a>
	</div>
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
		return '<a href="javascript:viewTaskPloyById('+rowData.id+')">查看</a>';
	}
	//描述截取 formatter方法
	function descFormatter(el,options,rowData){
		var desc = rowData.description;
		if (desc.length > 15) {
			return desc.substr(0,15) + '...';
		} else {
			return desc;
		}
	}
	//页面加载初始操作
	$(function(){
		//初始化jqGrid表格
		$("#taskPloyList").jqGridFunction({
			datatype: "local",
			colNames:['id','操作','中文名称','英文名称','类型','策略描述'],
		   	colModel:[
		   	       {name:"id",index:"id",sortable:false,hidden:true,hidedlg:true,key:true},
		   	       {name:'operation',formatter:operateFormatter,align:'center',frozen:true,sortable:false},
			       {name:'cname',index:'cname',frozen:true,align:'center',sortable:true},
			       {name:'ename',index:'ename',frozen:true,align:'center',sortable:true},
			       {name:'type',index:'type',formatter:typeFormatter,frozen:true,align:'center',sortable:true},
			       {name:'description',index:'description',formatter:descFormatter,align:'center',frozen:true,sortable:true}
		   	],
		   	multiselect:false,
			shrinkToFit:true,
			ondblClickRow:function(rowId){
				var taskPloy =  $('#taskPloyList').getRowData(rowId);
				$('#ployId').val(taskPloy.id);
				$('#ploySelect').attr('ployId',taskPloy.id).val(taskPloy.cname+'('+taskPloy.ename+')');
				$('#taskPloySelectDialog').dialog('close');
			}
		});
		
		//加载数据
		$("#taskPloyList").setGridParam({
			url:'${path}/task/taskPloyManage/taskPloyList.action',
			datatype: "json",
			page:1
		}).trigger("reloadGrid");
		
		//搜索
		$('#fastSearchButtonSelect').click(function(){
			var type = $('#taskPloyType').val() || '';
			var key = $('#taskPloyKey').val() || '';
			if(key == '请输入搜索关键字'){
				key ='';
			}
			var initParam ={
				'taskPloy.type.id':type,
				'taskPloy.cname':key,
				'taskPloy.ename':key,
				'task.description':key
			};
		    $("#taskPloyList").setPostData(initParam);
			$("#taskPloyList").trigger("reloadGrid");
		});
		
		//刷新
		$('#reloadSelect').click(function(){
			$('#taskPloyType').val('');
			$('#taskPloyKey').val('请输入搜索关键字');
		    $("#taskPloyList").setPostData({});
			$("#taskPloyList").trigger("reloadGrid");
		});
	});
	
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
