<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content"> 
	<div class="ui-corner-all contentNav" id="nav" >
		<input type="text" value="标题" name="searchText" id="searchText" style="width:154px;"  class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'标题':this.value;" onfocus="value=(this.value=='标题')?'':this.value;"/>
		<button id="refreshsearchText" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:10px;left:160px;cursor:pointer;outline: none;"></button>
		<a id="fastSearch" href="javascript:void(0)"><span>搜索</span></a>
		<@pop.JugePermissionTag ename="addTemplateMessageRecord">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="deleteTemplateMessageRecord">
			<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
		</@pop.JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>	
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="messageTemplateList"> </table>
		<div id="messageTemplateListPager"></div>
	</div>
</div>
<div id="messageTemplateDialog"></div>
<script type="text/javascript">
//显示当前 辖区
$("#thisCrumbs").show();
function loadData(){
	$("#messageTemplateList").setGridParam({		
		url:'${path}/messageTemplateManage/findTemplateMessageRecordByPage.action',
		datatype: "json",
		page:1
	});
	var listParam = null;
	var condition = $("#searchText").val();
	if(condition == '标题' || condition == '') {
		listParam = {
			"templateMessageRecord.org.id":$("#currentOrgId").val()				
		};
	}else{
		listParam = {
			"templateMessageRecord.org.id":$("#currentOrgId").val(),
			"templateMessageRecord.title":condition	
			};
	}
	
	$("#messageTemplateList").setPostData(listParam);
	$("#messageTemplateList").trigger("reloadGrid");
}
$("#fastSearch").click(function(){
	var condition = $("#searchText").val();
	if(condition == '标题' || condition == '') {
		return;
	}
	loadData();	
});
$("#refreshsearchText").click(function(event){
	$("#searchText").attr("value","标题");
});
$(document).ready(function(){
	$("#messageTemplateList").jqGridFunction({
		datatype:"local",		
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operate",index:'operate',width:100,label:'操作',align:'center',formatter:operateFormatter},
	        {name:"templateId",index:'templateId',sortable:true,label:'模板ID',align:'center',width:250},
	        {name:"templateNum",index:'templateNum',sortable:true,label:'编号',align:'center',width:150},
	        {name:"title",index:'title',sortable:true,label:'标题',align:'center',width:150},
	        {name:"primaryIndustry",index:'primaryIndustry',sortable:true,label:'一级行业',align:'center',width:150},
	        {name:"twoStageIndustry",index:'twoStageIndustry',sortable:true,label:'二级行业',align:'center',width:150},
	        {name:"weChatUserId",index:'weChatUserId',sortable:true,label:'微信公众平台(连接号)',align:'center',width:150},
	        {name:'org.orgName',index:"orgId",label:'所属部门',sortable:true,width:150,align:"center"},
	        {name:"createDate",index:'createDate',sortable:true,label:'创建时间',align:'center',width:150}
		],
		multiselect:true
	});
	$("#messageTemplateList").jqGrid('setFrozenColumns');
	loadData();	
});
//列表操作
function operateFormatter(el,options,rowData){
    return "<@pop.JugePermissionTag ename='updateTemplateMessageRecord'><a href='javascript:;' onclick='updateTemplateMessageRecord("+rowData.id+")'><span>修改</span></a> </@pop.JugePermissionTag>"
			+"<@pop.JugePermissionTag ename='deleteTemplateMessageRecord'><a href='javascript:;' onclick='deleteTemplateMessageRecord("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>";
}
$("#add").click(function(){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门进行新增!",level:"warn"});
		return;
	}
	$("#messageTemplateDialog").createDialog({
		width:700,
		height:350,
		title:'新增消息模板',
		url:'${path}/messageTemplateManage/dispatch.action?mode=add',
		buttons :{
			"保存" : function(){
				$("#messageTemplateForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
});
$("#delete").click(function(){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门信息进行删除!",level:"warn"});
		return;
	}
	var selectedRowId= $("#messageTemplateList").jqGrid("getGridParam", "selarrrow");
	if(null==selectedRowId || ""==selectedRowId){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	deleteTemplateMessageRecord(selectedRowId);
});
function deleteTemplateMessageRecord(rowid){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门信息进行删除!",level:"warn"});
		return;
	}
	if(rowid==null){
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"该信息删除后就无法还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/messageTemplateManage/deleteTemplateMessageRecordById.action?templateMessageRecordIds="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#messageTemplateList").trigger("reloadGrid");
					$.messageBox({message:"消息模板删除成功！"});
				}
			});
		}
	});
}
function updateTemplateMessageRecord(rowid){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门修改消息模板!",level:"warn"});
		return;
	}
	if(rowid==null){
		return;
	}
	$("#messageTemplateDialog").createDialog({
    	title:'修改消息模板',
    	width:700,
		height:350,
		postData:{
			"templateMessageRecord.id":rowid
		},
		url:'${path}/messageTemplateManage/dispatch.action?mode=edit',
		buttons: {
			"保存" : function(event){
				$("#messageTemplateForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
//刷新按钮事件
$("#refresh").click(function(){ 
	$("#searchText").attr("value","标题");
	loadData();
});
</script>
