<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content" >
	<div style="width: 100%;">
		<table id="tencentUserList"></table>
	<div id="tencentUserListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	$("#tencentUserList").jqGridFunction({
		url:'${path}/redEnvelopeManage/findTencentUserList.action',
		datatype: "json",
		postData: {
			"redEnvelope.org.id":getCurrentOrgId()
		},
		colNames:['tencentUserId','微信连接号','appID','appSecret','微信昵称'],
		colModel:[
			{name:"tencentUserId",sortable:false,hidden:true,hidedlg:true},
			{name:'weChatUserId',sortable:false,width:150,align:"center"},
			{name:'appId',sortable:false,width:150,align:"center"},
			{name:'appSecret',sortable:false,width:250,align:"center"},
			{name:'name',sortable:false,width:150,align:"center"}
		],
		multiselect:false,
		sortname:'TENCENT_USER_ID'
	});
	
});

//填充微信公众平台appID
function fillWxappid(){
	var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selrow");
	var rowData = $("#tencentUserList").getRowData(selectedId);
	var weChatUserIdStr = rowData.weChatUserId;
	var appIdStr = rowData.appId;
	if(selectedId==null || selectedId=="" || selectedId==undefined){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		return;
	}else{
		$("#weChatUserId_Id").val(weChatUserIdStr);
		$("#redEnvelopeWxappid_Id").val(appIdStr);
		$("#redEnvelopeDialog").dialog("close");
	}
}	

//填充微信公众平台号
function fillWeChatUserId(){
	var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selrow");
	var rowData = $("#tencentUserList").getRowData(selectedId);
	var weChatUserIdStr = rowData.weChatUserId;
	if(selectedId==null || selectedId=="" || selectedId==undefined){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		return;
	}else{
		$("#weChatUserId_Id").val(weChatUserIdStr);
		$("#messageTemplateFormDialog").dialog("close");
	}
}

</script>

