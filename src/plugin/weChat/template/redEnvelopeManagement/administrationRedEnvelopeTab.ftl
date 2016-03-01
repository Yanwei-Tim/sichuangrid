<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content"> 
	<div class="ui-corner-all contentNav" id="nav" >
		<input type="text" value="请输入活动名称" name="searchText" id="searchText" style="width:154px;"  class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入活动名称':this.value;" onfocus="value=(this.value=='请输入活动名称')?'':this.value;"/>
		<button id="refreshsearchText" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:10px;left:160px;cursor:pointer;outline: none;"></button>
		<a id="fastSearch" href="javascript:void(0)"><span>搜索</span></a>
		<@pop.JugePermissionTag ename="deleteRedEnvelope">
			<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
		</@pop.JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>	
		<@pop.JugePermissionTag ename="sendRedEnvelope">
			<a id="sendRedEnvelope" href="javascript:void(0)"><span>发放红包</span></a>
		</@pop.JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="administrationRedEnvelopeList"> </table>
		<div id="administrationRedEnvelopeListPager"></div>
	</div>
</div>
<div id="administrationRedEnvelopeDialog"></div>
<script type="text/javascript">
//显示当前 辖区
$("#thisCrumbs").show();
function loadData(){
	$("#administrationRedEnvelopeList").setGridParam({		
		url:'${path}/redEnvelopeManage/findRedEnvelopeByPage.action',
		datatype: "json",
		page:1
	});
	var listParam = null;
	var condition = $("#searchText").val();
	if(condition == '请输入活动名称' || condition == '') {
		listParam = {
			"redEnvelopeVo.org.id":$("#currentOrgId").val()				
		};
	}else{
		listParam = {
			"redEnvelopeVo.org.id":$("#currentOrgId").val(),
			"redEnvelopeVo.act_name":condition	
			};
	}
	
	$("#administrationRedEnvelopeList").setPostData(listParam);
	$("#administrationRedEnvelopeList").trigger("reloadGrid");
}
$("#fastSearch").click(function(){
	var condition = $("#searchText").val();
	if(condition == '请输入活动名称' || condition == '') {
		return;
	}
	loadData();	
});
$("#refreshsearchText").click(function(event){
	$("#searchText").attr("value","请输入活动名称");
});
$(document).ready(function(){
	$("#administrationRedEnvelopeList").jqGridFunction({
		datatype:"local",		
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operate",index:'operate',width:100,label:'操作',align:'center',formatter:operateFormatter},
	        {name:"act_name",index:'act_name',sortable:true,label:'活动名称',align:'center',width:150},
	        {name:"send_name",index:'send_name',sortable:true,label:'商户名称',align:'center',width:150},
	        {name:"mch_id",index:'mch_id',sortable:true,label:'微信支付商户号',align:'center',width:150},
	        {name:"redEnvelopeType",index:'redEnvelopeType',sortable:true,label:'红包类型',align:'center',width:120,formatter:redEnvelopeTypeFormatter},
	        {name:"min_value",index:'min_value',sortable:true,label:'最小金额',align:'center',hidden:true,width:120,hidedlg:true},
	        {name:"max_value",index:'max_value',sortable:true,label:'最大金额',align:'center',hidden:true,width:120,hidedlg:true},
	        {name:'singleEnvelope_value',index:'singleEnvelope_value',sortable:true,label:'单个红包金额(单位：元)',width:150,align:'center',formatter:singleEnvelopeFormatter},
	        {name:"remark",index:'remark',sortable:true,label:'备注',align:'center',width:150},
	        {name:"wishing",index:'wishing',sortable:true,label:'祝福语',align:'center',width:150},
	        {name:'createDate',index:'createDate',sortable:true,label:'红包创建时间',width:150,align:'center'},
	        {name:'createUser',index:'createUser',sortable:true,label:'创建用户',width:150,align:'center'},
	        {name:"wxappid",index:'wxappid',sortable:true,label:'微信公众平台appID',align:'center',width:150},
	        {name:"weChatUserId",index:'weChatUserId',sortable:true,label:'微信公众平台(连接号)',align:'center',width:150}
		],
		multiselect:true
	});
	$("#administrationRedEnvelopeList").jqGrid('setFrozenColumns');
	loadData();	
	
	
});
//刷新按钮事件
$("#refresh").click(function(){ 
	$("#searchText").attr("value","请输入活动名称");
	loadData();
});

//红包类型格式化
function redEnvelopeTypeFormatter(el,options,rowData){
    if(rowData.redEnvelopeType==<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@SINGLEENVELOPE'/>){
        return "<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@SINGLEENVELOPENAME'/>";
    }else {
        return "<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@RANDOMENVELOPENAME'/>";
    }
}
//列表操作
function operateFormatter(el,options,rowData){
    return "<@pop.JugePermissionTag ename='updateRedEnvelope'><a href='javascript:;' onclick='updateRedEnvelopeConfiguration("+rowData.id+")'><span>修改</span></a> </@pop.JugePermissionTag>"
			+"<@pop.JugePermissionTag ename='deleteRedEnvelope'><a href='javascript:;' onclick='deleteRedEnvelopeConfiguration("+rowData.id+")'><span>删除</span></a> </@pop.JugePermissionTag>";
}
//金额格式化
function singleEnvelopeFormatter(el,options,rowData){
	if(rowData.singleEnvelope_value != '' && !isNaN(rowData.singleEnvelope_value)){
		return (rowData.singleEnvelope_value)/100;
	}else{
		return "";
	}
}
//选择发送红包粉丝
$("#sendRedEnvelope").click(function(){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门发放红包!",level:"warn"});
		return;
	}
	var selectedId= $("#administrationRedEnvelopeList").jqGrid("getGridParam", "selarrrow");
	if(selectedId==null || selectedId.length >1 || selectedId.length==0){
		 $.messageBox({level:'warn',message:"请选择一条记录进行操作！"});
	     return;
	}
	if(selectedId.length == 1){
		var rowData = $("#administrationRedEnvelopeList").getRowData(selectedId);
		var weChatUserId = rowData.weChatUserId;
		var wxappid = rowData.wxappid;
		var redEnvelopeId = rowData.id;
		$("#administrationRedEnvelopeDialog").createDialog({
			width: 820,
			height: 550,
			title:'选择发送红包粉丝',
			url:'${path}/hotModuel/redEnvelopeManagement/searchFunDlg.ftl?weChatUserId='+weChatUserId,
			buttons: {			
			   "发送" : function(){
			   		sendRedEnvelope(redEnvelopeId);
			   },
			   "关闭" : function(){
					$(this).dialog("close");
			   }
			}
		});
	}
});
$("#delete").click(function(){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门删除红包配置信息!",level:"warn"});
		return;
	}
	var selectedRowId= $("#administrationRedEnvelopeList").jqGrid("getGridParam", "selarrrow");
	if(null==selectedRowId || ""==selectedRowId){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	deleteRedEnvelopeConfiguration(selectedRowId);
});
function deleteRedEnvelopeConfiguration(rowid){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门删除红包配置信息!",level:"warn"});
		return;
	}
	if(rowid==null){
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"该红包配置信息删除后就无法还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/redEnvelopeManage/deleteRedEnvelopeConfigurationById.action?redEnvelopeIds="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#administrationRedEnvelopeList").trigger("reloadGrid");
					$.messageBox({message:"红包配置信息删除成功！"});
				}
			});
		}
	});
}
function updateRedEnvelopeConfiguration(rowid){
	if(USER_ORG_ID != $("#currentOrgId").val()){
		$.messageBox({message:"只能对本级部门修改红包配置信息!",level:"warn"});
		return;
	}
	if(rowid==null){
		return;
	}
	$("#administrationRedEnvelopeDialog").createDialog({
    	title:'修改红包配置',
    	width:1000,
		height:500,
		postData:{
			"redEnvelopeId":rowid
		},
		url:'${path}/redEnvelopeManage/dispatch.action',
		buttons: {
			"保存" : function(event){
				submitRedEnvelopeForm();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
	
}
</script>
