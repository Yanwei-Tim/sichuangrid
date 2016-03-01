<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" id="orgChangeId" value="${(orgChangeId)!}"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="reloadLogList" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span> </a>
	 </div>
	
		<div style="width:100%">
			<table id="LogInfoList">
			</table>
			<div id="LogInfoListPager">
			</div>
		</div>
	
</div>
<div id="orgChangeLogDetailedDlg"></div>
<script type="text/javascript" >
$(function(){
	$("#LogInfoList").jqGridFunction({
		datatype: "local",
		height:'380',
	   	colNames:['id','开始时间','结束时间','执行结果','操作类型','模块名称','表名','日志描述'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'startTime',index:'startTime',sortable:false,width:"60px",align:"center"},
	   		{name:'endTime',index:'endTime',sortable:false,width:"60px",align:"center"},
	   		{name:'logType',index:'logType',sortable:false,formatter:operateFormatter_logType,width:"60px",align:"center"},
	   		{name:'operateType',index:"operateType",formatter:operateFormatter_OperateType,sortable:false,width:"60px",align:"center"},
	   		{name:'moduleName',index:'moduleName',sortable:false,width:"60px"},
	   		{name:'tableName',index:'tableName',sortable:false,width:"60px"},
	   		{name:'description',index:'description',sortable:false,formatter:operateFormatter_description}
	   	],
	    shrinkToFit:true,
	    showColModelButton:false
	});
		$("#LogInfoList").setGridParam({
			url:'${path}/orgchange/orgChangeInfoLogManage/findOrgChangeLogInfoByChangeInfoId.action?orgChangeId='+$("#orgChangeId").val(),
			datatype: "json",
			page:1
		});
		
		$("#LogInfoList").setPostData({});
		$("#LogInfoList").trigger("reloadGrid");	
		
	//刷新
	$("#reloadLogList").click(function(){
	$("#LogInfoList").setPostData({});
		$("#LogInfoList").trigger("reloadGrid");	
	});	
		
	//操作详情截取
	function operateFormatter_description(el,options,rowData){
		var description=rowData.description;
		var sub_description="";
		if(description!=null && description.length!=""){
			if(description.length>20){
				sub_description=description.substring(0,20)+"....<a href='javascript:void(0)' onclick='viewDetailedLog("+rowData.id+")'>详情</a>"
			}
		}else{
			sub_description = description;
		}
		return sub_description;
	}	
	
	//操作
	function operateFormatter_logType(el,options,rowData){
		if(rowData.logType==1 || rowData.logType=='1'){
			return "执行成功";
		}else{
			return "执行失败";
		}
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
		$("#LogInfoList").setPostData({});
		$("#LogInfoList").trigger("reloadGrid");
	});
	
});	

	
function viewDetailedLog(selectId){
		if(selectId==null){
		$.messageBox({
						message:"请选中一条记录再执行！",
						level:"warn"
					});
		return;
		}
		$("#orgChangeLogDetailedDlg").createDialog({
				width: 400,
				height: 300,
				title:'详细日志',
				url:'${path}/orgchange/orgChangeInfoLogManage/findLogDetailedInfoById.action?logId='+selectId,
				buttons : {
						"关闭" : function(){
							$(this).dialog("close");
							}
						  }
		});
	}
</script>
