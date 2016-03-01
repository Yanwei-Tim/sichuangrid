<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content"> 
	<div id="search-condition-form" title="查询" class="container container_24">
		<div class="grid_3 lable-right">
			<label class="form-lbl">活动名称：</label>
		</div>
		<div class="grid_18">
			<input name="searchText" id="searchText" type="text" id="name" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="查询" id="fastSearch" />
		</div>
		<div class='clearLine' style="margin-top:10px;height:10px">&nbsp;</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="administrationRedEnvelopeForInboxList"> </table>
		<div id="administrationRedEnvelopeForInboxListPager"></div>
	</div>
</div>
<div id="administrationRedEnvelopeForInboxDialog"></div>
<script type="text/javascript">
function loadData(){
	$("#administrationRedEnvelopeForInboxList").setGridParam({		
		url:'${path}/redEnvelopeManage/findRedEnvelopeForInboxByPage.action',
		datatype: "json",
		page:1
	});
	var listParam = null;
	var condition = $("#searchText").val();
		listParam = {
			"redEnvelopeVo.act_name":condition,
			"fan.fanId":'<@s.property value="#parameters.fanId[0]"/>'	
			};
	$("#administrationRedEnvelopeForInboxList").setPostData(listParam);
	$("#administrationRedEnvelopeForInboxList").trigger("reloadGrid");
}
$("#fastSearch").click(function(){
	var condition = $("#searchText").val();
	loadData();	
});
$("#refreshsearchText").click(function(event){
	$("#searchText").attr("value","请输入活动名称");
});
$(document).ready(function(){
	$("#administrationRedEnvelopeForInboxList").jqGridFunction({
		datatype:"local",		
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"act_name",index:'act_name',sortable:true,label:'活动名称',align:'center',width:150},
	        {name:"send_name",index:'send_name',sortable:true,label:'商户名称',align:'center',width:150},
	        {name:"mch_id",index:'mch_id',sortable:true,label:'微信支付商户号',align:'center',width:150},
	        {name:"min_value",index:'min_value',sortable:true,label:'最小金额',align:'center',hidden:true,width:120,hidedlg:true},
	        {name:"max_value",index:'max_value',sortable:true,label:'最大金额',align:'center',hidden:true,width:120,hidedlg:true},
	        {name:'singleEnvelope_value',index:'singleEnvelope_value',sortable:true,label:'单个红包金额(单位：元)',width:150,align:'center',formatter:singleEnvelopeFormatter},
	        {name:"remark",index:'remark',sortable:true,label:'备注',align:'center',width:150},
	        {name:"wishing",index:'wishing',sortable:true,label:'祝福语',align:'center',width:150},
	        {name:"redEnvelopeType",index:'redEnvelopeType',sortable:true,label:'红包类型',align:'center',width:120,formatter:redEnvelopeTypeFormatter},
	        {name:'createDate',index:'createDate',sortable:true,label:'红包创建时间',width:150,align:'center'},
	        {name:'createUser',index:'createUser',sortable:true,label:'创建用户',width:150,align:'center'},
	        {name:"wxappid",index:'wxappid',sortable:true,label:'微信公众平台appID',align:'center',width:150},
	        {name:"weChatUserId",index:'weChatUserId',sortable:true,label:'微信公众平台(连接号)',align:'center',width:150}
		],
		multiselect:false
	});
	$("#administrationRedEnvelopeForInboxList").jqGrid('setFrozenColumns');
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
//金额格式化
function singleEnvelopeFormatter(el,options,rowData){
	if(rowData.singleEnvelope_value != '' && !isNaN(rowData.singleEnvelope_value)){
		return (rowData.singleEnvelope_value)/100;
	}else{
		return "";
	}
}
//发送红包
function sendRedEnvelope(openId){
	var selectedId = $("#administrationRedEnvelopeForInboxList").jqGrid("getGridParam", "selrow");
	var rowData = $("#administrationRedEnvelopeForInboxList").getRowData(selectedId);
	var redEnvelopeId = rowData.id;
	if(selectedId==null || selectedId=="" || selectedId==undefined){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		return;
	}else{
		//发送红包
		if(USER_ORG_ID != $("#currentOrgId").val()){
			$.messageBox({message:"只能对本级部门发送红包!",level:"warn"});
			return;
		}
		$.ajax({
			async: false,
			url:"${path}/redEnvelopeManage/sendRedEnvelope.action",
		   	data:{
		   		"re_openid":openId,
		   		"redEnvelopeId":redEnvelopeId
	        },
			success:function(responseData){
				if(responseData == "SUCCESS"){
					$.messageBox({message:"红包发送成功"});
				}else{
					if(responseData == null || responseData == "null"){
						$.messageBox({message:"红包发送失败,请检查微信支付商户号、API密钥配置是否正确",level: "error"});
						return;
					}
					$.messageBox({message:responseData+",红包发送失败",level: "error"});
				}
				$("#inboxDialog").dialog("close");
			}
		});
	}
}
</script>
