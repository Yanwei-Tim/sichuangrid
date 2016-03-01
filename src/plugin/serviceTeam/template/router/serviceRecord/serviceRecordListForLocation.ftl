<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>

<div class="content">
	<input type="hidden" id="name" value="${(name)!}">
	<div class="ui-corner-all contentNav" id="nav">
	<div class="userState">
		<select id="displayYear" name="displayYear" class="basic-input" >
		</select>
	</div>
	<span class="lineBetween "></span>
	<@pop.JugePermissionTag ename="searchServiceRecord">
		<a id="searchServiceRecord" href="javascript:void(0)"><span>高级搜索</span></a>
	</@pop.JugePermissionTag>
	<@pop.JugePermissionTag ename="addServiceRecord">
	<a id="addServiceRecord" href="javascript:void(0)"><span>新增</span></a>
	</@pop.JugePermissionTag>
	<a id="reloadServiceRecord"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceRecordList"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
	<div id="serviceRecordDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	loadDisplayYear();
	$("#serviceRecordList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:350,
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true},
			{name:'internalId',index:"id",sortable:false,hidden:true,frozen:true},
		    {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_ServiceRecord,width:80,align:"center"},
		    {name:'occurDate',index:"occurDate",label:'服务时间',sortable:true,width:100,align:"center"},
		    {name:'occurPlace',index:"occurPlace",label:'服务地点',sortable:true,width:150},
		    {name:'serviceJoiners',index:'serviceJoiners',label:'服务参与人',sortable:false,width:120},
		    {name:'serviceMembers',label:'记录所属人',sortable:false,width:120},
		    {name:'teamName',label:'所属服务团队',sortable:false, width:150,hidden:true},
		    {name:'recordType',label:'记录类型',formatter:typeFormatter_ServiceRecord,sortable:false, width:150,align:"center"},
		    {name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:200},
		    {name:'serviceRecordAttachments',label:'附件',sortable:false, width:200,formatter:formatterAttachFile}
		],
		loadComplete: afterLoad,
		ondblClickRow: viewServiceRecord
	});
	$("#serviceRecordList").jqGrid('setFrozenColumns');
	getServiceRecordList();
	//新增按钮事件
	$("#addServiceRecord").click(function(event){
		var objectNames=$("#name").val();
		$("#serviceRecordDialog").createDialog({
			title:"新增巡场情况",
			width: 600,
			height: 400,
			url:'${path}/plugin/serviceTeam/serviceRecord/dispatch.action?mode=add&dailogName=serviceRecordDialog&objectIds=${(id)!}&populationType=${(populationType)!}&objectNames='+encodeURIComponent(objectNames)+'&showRecordType='+${(showRecordType)!},
			buttons: {
				"保存并关闭" : function(event){
					$("#isSubmit").val("true");
		   			$("#serviceRecordForm").submit();
				},
				"保存并继续" : function(event){
		   			$("#isSubmit").val("false");
		   			$("#serviceRecordForm").submit();
				}
			}
		});
	});
	//快速过滤事件绑定
	$("#displayLevel").change(getServiceRecordList);
	$("#displayYear").change(getServiceRecordList);
	//刷新按钮事件绑定
	$("#reloadServiceRecord").click(function(event){
		$("#displayYear").empty();
		loadDisplayYear();
		getServiceRecordList();
	});
	function loadDisplayYear()
	{
		$.ajax({
			async: false,
			url: "${path}/plugin/serviceTeam/serviceRecord/findDisplayYear.action",
			data:{},
			success:function(yearList){
				for(var j = 0 ;j<=yearList.length;j++){
					var map = yearList[j];
					for(var i in map){ 
						$("#displayYear").append("<option value='"+i+"'>"+map[i]+"</option>");
					}
				}
			}
		});
	}
	//高级搜索对话框
	$("#searchServiceRecord").click(function(event){
		$("#serviceRecordDialog").createDialog({
			title:"巡场情况查询-请输入查询条件",
			width: 600,
			height: 300,
			url:'${path}/plugin/serviceTeam/serviceRecord/dispatch.action?mode=search&dailogName=serviceRecordDialog&objectIds=${(id)!}&populationType=${(populationType)!}&showRecordType=${(showRecordType)}',
			buttons: {
				"查询" : function(event){
					searchServiceRecords();
					$(this).dialog("close");
					$("#displayYear").html("");
					loadDisplayYear();
					$("#displayYear").append("<option selected='selected'>请选择</option>");
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}

		});
	});
});

	//列表显示（包括快速过滤）
	function getServiceRecordList(){
		$("#serviceRecordList").setGridParam({
			url:'${path}/plugin/serviceTeam/serviceRecord/findServiceRecords.action?objectIds=${(id)!}&populationType=${(populationType)!}',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#serviceRecordList").setPostData({
			"serviceRecordVo.organization.id":USER_ORG_ID,
			"serviceRecordVo.displayYear":$("#displayYear").val()
		});
		$("#serviceRecordList").trigger("reloadGrid"); 
	}
	
	//删除服务记录
	function deleteRecordOperator(selectedIds){
		var serviceRecord =  $("#serviceRecordList").getRowData(selectedIds);
		if(serviceRecord.internalId>USER_ORG_LEVEL){
			$.messageBox({level:'warn',message:"选中的巡场情况层级高于当前登录层级，无法对该巡场情况进行删除操作！"});
			return;
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:'${path}/plugin/serviceTeam/serviceRecord/deleteServiceRecords.action?mode=delete&recordIds='+selectedIds,
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删除巡场情况!"});
							$("#serviceRecordList").trigger("reloadGrid");
						}else{
							$.messageBox({
								message:"删除巡场情况出错!",
								level:"warn"
							});
						}
					}
				});
			}
		});
	}
	
	//高级搜索
	function searchServiceRecords()
	{
		$("#serviceRecordList").setGridParam({
			url:"${path}/plugin/serviceTeam/serviceRecord/findServiceRecords.action?objectIds=${(id)!}&populationType=${(populationType)!}",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchServiceRecordForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
	 		if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		$("#serviceRecordList").setPostData(
			$.extend(dataJson,{
				"serviceRecordVo.organization.id":getCurrentOrgId(),
				"serviceRecordVo.displayLevel":$("#displayLevel").val()
		}));
		$("#serviceRecordList").trigger("reloadGrid");
	}
	//修改服务记录
	function updateRecordOperator(selectedId){
		var serviceRecord =  $("#serviceRecordList").getRowData(selectedId);
		if(serviceRecord.internalId>USER_ORG_LEVEL){
			$.messageBox({level:'warn',message:"选中的巡场情况层级高于当前登录层级，无法对该巡场情况进行修改操作！"});
			return;
		}
		$("#serviceRecordDialog").createDialog({
			title:'修改巡场情况',
			width: 600,
			height: 400,
			url:'${path}/plugin/serviceTeam/serviceRecord/dispatch.action?mode=edit&dailogName=serviceRecordDialog&objectNames=${(name)!}&objectIds=${(id)!}&serviceRecord.id='+selectedId+'&showRecordType='+${(showRecordType)!},
			buttons: {
				"保存并关闭" : function(event){
					$("#serviceRecordForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}


	//加载完时执行的方法
	function afterLoad() {
		loadFiles();
	}
	//加载文件
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#serviceRecordList").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.id, url:'${path}/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId='+fn.id, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
	function formatterAttachFile(el,options,rowData){
		if(rowData.serviceRecordAttachments.length>0){
			$("#serviceRecordList").data(String(rowData.id), rowData.serviceRecordAttachments);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
	function viewServiceRecord(selectedId) {
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 400,
			title: '查看巡场情况信息',
			url:'${path}/plugin/serviceTeam/serviceRecord/dispatch.action?showRecordType=${(showRecordType)}&mode=view&serviceRecord.id='+selectedId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
</script>