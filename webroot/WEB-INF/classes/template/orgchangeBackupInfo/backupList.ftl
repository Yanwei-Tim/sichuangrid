<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="reloadBackupList" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span> </a>
	 </div>
	
		<div style="width:100%">
			<table id="BackupInfoList">
			</table>
			<div id="BackupInfoListPager">
			</div>
		</div>
	
</div>
<div id="orgChangeBackupDetailedDlg"></div>
<script type="text/javascript" >
$(function(){
	$("#BackupInfoList").jqGridFunction({
		datatype: "local",
		height:'380',
	   	colNames:['id','数据ID','迁移合并信息ID','操作表名','数据所在组织机构ID','详细信息'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'dataId',index:'dataId',sortable:false,width:"60px",align:"center"},
	   		{name:'orgChangeInfo.id',index:'orgChangeInfo.id',sortable:false,width:"60px",align:"center"},
	   		{name:'tableName',index:'tableName',sortable:false,width:"60px",align:"center"},
	   		{name:'organization.id',index:"organization.id",sortable:false,width:"60px",align:"center"},
	   		{name:'expansionData',index:'expansionData',sortable:false,formatter:operateFormatter_expansionData}
	   	],
	    shrinkToFit:true,
	    showColModelButton:false
	});
		$("#BackupInfoList").setGridParam({
			url:'${path}/orgchange/orgChangeInfoBackupManage/findAllBackupInfo.action',
			datatype: "json",
			page:1
		});
		
		$("#BackupInfoList").setPostData({});
		$("#BackupInfoList").trigger("reloadGrid");	
		
	//刷新
	$("#reloadBackupList").click(function(){
		$("#BackupInfoList").setPostData({});
		$("#BackupInfoList").trigger("reloadGrid");	
	});	
		
	//操作详情截取
	function operateFormatter_expansionData(el,options,rowData){
		var expansionData=rowData.expansionData;
		var sub_expansionData="";
		if(expansionData!=null && expansionData.length!=""){
			if(expansionData.length>20){
				sub_expansionData=expansionData.substring(0,20)+"....<a href='javascript:void(0)' onclick='viewDetailedLog("+rowData.id+")'>详情</a>";
			}else{
				sub_expansionData=expansionData;
			}
		}else{
			sub_expansionData = "";
		}
		return sub_expansionData;
	}	
	
	$("#reloadList").click(function(){
		$("#BackupInfoList").setPostData({});
		$("#BackupInfoList").trigger("reloadGrid");
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
		$("#orgChangeBackupDetailedDlg").createDialog({
			width: 400,
			height: 300,
			title:'详细信息',
			url:'${path}/orgchange/orgChangeInfoBackupManage/findBackupDetaileById.action?id='+selectId,
			buttons : {
				"关闭" : function(){
					$(this).dialog("close");
			}
		  }
		});
	}
</script>
