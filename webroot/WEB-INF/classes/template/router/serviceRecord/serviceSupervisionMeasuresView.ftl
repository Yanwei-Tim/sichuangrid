<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>
<div class="content">
	<input type="hidden" id="name" value="${(name)!}">
	<div class="ui-corner-all contentNav" id="nav">
	<@pop.JugePermissionTag ename="addServiceRecord">
	<a id="addServiceRecord" href="javascript:void(0)"><span>新增</span></a>
	</@pop.JugePermissionTag>
	<a id="reloadServiceRecord"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceRecordList"></table>
		<div id="serviceRecordListPager"></div>
	</div>
	<div id="serviceRecordDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#serviceRecordList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:360,
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true},
		    {name:"operation",label:"操作",sortable:false,formatter: function (val, val2, val3){
		    	return "<a href='###' onclick='updateRecordOperator(\""+val3.id+"\")'>修改</a>&nbsp;&nbsp;<a href='###' onclick='deleteRecordOperator(\""+val3.id+"\")'>删除</a>";
		    },width:80,align:"center"},
		    {name:'upTime',index:"upTime",label:'时间',sortable:true,width:100,align:"center",formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		    {name:'supervisepersonnel',index:"supervisepersonnel",label:'监管人员',sortable:true,width:120},
		    {name:'superviseinfo',index:'superviseinfo',label:'监管措施',sortable:false,width:460,align:"center"}
		],
		ondblClickRow: viewServiceRecord
	});
	$("#serviceRecordList").jqGrid('setFrozenColumns');
	getServiceRecordList();
	//新增按钮事件
	$("#addServiceRecord").click(function(event){
		var objectNames=$("#name").val();
		$("#serviceRecordDialog").createDialog({
			title:"新增服务监管措施",
			width: 600,
			height: 300,
			url:'${path}/baseinfo/mentalPatientManage/editServiceSupervisionMeasures.action?population.id=${(id)!}&mode=add&serviceSupervisionMeasures.type=${module}',
			buttons: {
				"保存" : function(event){
		   			$("#serviceRecordForm").submit();
				},
				"关闭" : function(event){
		        	$(this).dialog("close");
				}
			}
		});
	});
	//刷新按钮事件绑定
	$("#reloadServiceRecord").click(function(event){
		getServiceRecordList();
	});
});
	//列表显示（包括快速过滤）
	function getServiceRecordList(){
		$("#serviceRecordList").setGridParam({
			url:'${path}/baseinfo/mentalPatientManage/serviceSupervisionMeasuresPatientList.action?population.id=${(id)!}&serviceSupervisionMeasures.type=${module}',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#serviceRecordList").trigger("reloadGrid"); 
	}
	
	//删除服务监管措施
	function deleteRecordOperator(selectedId){
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.post('${path}/baseinfo/mentalPatientManage/deleteServiceSupervisionMeasuresById.action?serviceSupervisionMeasures.id='+selectedId, function(data){
				    $.messageBox({message:"成功删除服务监管措施!"});
					$("#serviceRecordList").trigger("reloadGrid");
				});
			}
		});
	}
	
	//修改服务监管措施
	function updateRecordOperator(selectedId){
		$("#serviceRecordDialog").createDialog({
			title:'修改服务监管措施',
			width: 600,
			height: 300,
			url:'${path}/baseinfo/mentalPatientManage/editServiceSupervisionMeasures.action?mode=edit&serviceSupervisionMeasures.id='+selectedId,
			buttons: {
				"保存" : function(event){
					$("#serviceRecordForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	function viewServiceRecord(selectedId) {
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 300,
			title: '查看服务监管措施信息',
			url:'${path}/baseinfo/mentalPatientManage/editServiceSupervisionMeasures.action?mode=view&serviceSupervisionMeasures.id='+selectedId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
</script>