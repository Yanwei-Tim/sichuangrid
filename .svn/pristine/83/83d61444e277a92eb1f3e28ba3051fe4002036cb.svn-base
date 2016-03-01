<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="reloadList" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span> </a>
	 </div>
	
		<div style="width:100%">
			<table id="orgChangeLogInfoList">
			</table>
			<div id="orgChangeLogInfoListPager">
			</div>
		</div>
	
</div>
<div id="orgChangeLogDlg"></div>
<script type="text/javascript" >
$(function(){
	$("#orgChangeLogInfoList").jqGridFunction({
		datatype: "local",
	   	colNames:['id','操作','执行状态','源OrgId','源部门名称','操作类型','目标OrgId','目标部门名称','执行操作时间'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'operation',index:'operation',label:"操作",sortable:false,formatter:operateFormatter_orgChangeLogInfo,width:"50px",align:"center"},
	   		{name:'isDeal',index:'isDeal',label:"执行状态",sortable:false,formatter:operateFormatter_isDeal,width:"50px",align:"center"},
	   		{name:'sourceOrgId',index:"sourceOrgId",label:"源OrgId",sortable:false,width:"50px"},
	   		{name:'sourceOrgName',index:'sourceOrgName',sortable:false},
	   		{name:'operateType',index:'operateType',label:"操作类型",sortable:false,formatter:operateFormatter_OperateType,width:"30px",align:"center"},
	   		{name:'targetorgId',index:"targetorgId",label:"目标OrgId",sortable:false,width:"50px"},
	   		{name:'targetOrgName',index:'targetOrgName',sortable:false},
	   		{name:'updateDate',index:'updateDate',sortable:false}
	   	],
	    shrinkToFit:true,
	    showColModelButton:false
	});
		$("#orgChangeLogInfoList").setGridParam({
			url:'${path}/orgchange/orgChangeInfoLogManage/findAllChangeInfo.action',
			datatype: "json",
			page:1
		});
		
		$("#orgChangeLogInfoList").setPostData({});
		$("#orgChangeLogInfoList").trigger("reloadGrid");	
		
	//执行状态
	function operateFormatter_isDeal(el,options,rowData){
		var isDeal= rowData.isDeal;
		if(isDeal==1 || isDeal=="1"){
			return "已执行";
		}else{
			return "未执行";
		}
	}	
		
	//操作
	function operateFormatter_orgChangeLogInfo(el,options,rowData){
		return "<a href='javascript:void(0)' onclick='viewChangeLogInfo("+rowData.id+")'><span>查看日志</span></a>";
	}
	//操作类型
	function operateFormatter_OperateType(el,options,rowData){
		var operateType = rowData.operateType;
		if(operateType==1 || operateType =='1'){
			return "迁移";
		}else{
			return "合并";
		}
	}
	
	$("#reloadList").click(function(){
		$("#orgChangeLogInfoList").setPostData({});
		$("#orgChangeLogInfoList").trigger("reloadGrid");
	});
	
});	


function viewChangeLogInfo(selectId){
	if(selectId==null){
		$.messageBox({
						message:"请选中一条记录再执行！",
						level:"warn"
					});
		return;
	}
	$("#orgChangeLogDlg").createDialog({
			width: 1100,
			height: 600,
			title:'详细日志',
			url:'${path}/orgchange/orgChangeInfoLogManage/dispatch.action?mode=viewLogInfo&orgChangeId='+selectId,
			buttons : {
					"关闭" : function(){
						$(this).dialog("close");
						}
					  }
	});
	
}

</script>
