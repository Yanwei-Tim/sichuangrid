<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="ui-corner-all" id="nav">
	<div class="grid_2 lable-right">
		<label>发放成功时间：</label>
	</div>
	<div class="grid_3 form-left" style="padding-left:4px;">
		<input type="text" name="redEnvelopesPaymentRecords.send_time_start" class="form-txt" readonly id="send_time_start_id"/>
	</div>
	<div class="grid_2">
		<label>&nbsp;-&nbsp;</label>
	</div>
	<div class="grid_3 form-left">
		<input type="text" name="redEnvelopesPaymentRecords.send_time_end" class="form-txt" readonly  id="send_time_end_id"/>
	</div>
		<div class="grid_2">
				<label>&nbsp;&nbsp;</label>
		</div>
	<div style="margin-top:5px;">
	<a id="redEnvelopeRecordSearch" href="javascript:void(0)"><span>查询</span></a>			
	<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>	
	</div>
</div>
<div style="width: 100%">
	<table id="grantRedEnvelopeList"> </table>
	<div id="grantRedEnvelopeListPager"></div>
</div>
<div id="grantRedEnvelopeDialog"></div>
<script type="text/javascript">
//显示当前 辖区
$("#thisCrumbs").show();
function loadData(){
	$("#grantRedEnvelopeList").setGridParam({		
		url:'${path}/redEnvelopesPaymentRecordsManage/findRedEnvelopesPaymentRecordsByPage.action',
		datatype: "json",
		page:1
	});
	var listParam = null;
	listParam = {
		"redEnvelopesPaymentRecords.org.id":$("#currentOrgId").val()
		};	
	$("#grantRedEnvelopeList").setPostData(listParam);
	$("#grantRedEnvelopeList").trigger("reloadGrid");
}
$(document).ready(function(){
	$('#send_time_start_id').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose: function (selectedDate) {
        	$("#send_time_end_id").datepicker("option", "minDate", selectedDate);
        }
	})
	$('#send_time_end_id').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose:function(selectedDate) {
       		$("#send_time_start_id").datepicker("option", "maxDate",selectedDate);
    	}
	})
	$("#grantRedEnvelopeList").jqGridFunction({
		datatype:"local",		
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operate",index:'operate',width:100,label:'操作',align:'center',formatter:operateFormatter},
	        {name:"mch_billno",index:'mch_billno',sortable:true,label:'商户订单号',align:'center',width:200},
	        {name:"fanName",index:'fanName',sortable:true,label:'发送对象(粉丝昵称)',align:'center',width:200},
	        {name:'total_amount',index:'total_amount',sortable:true,label:'付款金额(单位：元)',width:150,align:'center',formatter:total_amountFormatter},
	        {name:'send_time',index:'send_time',sortable:true,label:'发放成功时间',width:150,align:'center'},
	        {name:"re_openid",index:'re_openid',sortable:true,label:'发送对象openid',align:'center',width:200},
	        {name:'send_listid',index:'send_listid',sortable:true,label:'微信单号',width:250,align:'center'},
	        {name:"mch_id",index:'mch_id',sortable:true,label:'微信支付商户号',align:'center',width:150},
	        {name:"wxappid",index:'wxappid',sortable:true,label:'微信公众平台appID',align:'center',width:150}
		],
		multiselect:false,
		ondblClickRow: viewRedEnvelopesPaymentRecords//选中一行
	});
	$("#grantRedEnvelopeList").jqGrid('setFrozenColumns');
	loadData();		
});

//点击查询
$("#redEnvelopeRecordSearch").click(function(){
	var listParam = {
		"redEnvelopesPaymentRecords.org.id":$("#currentOrgId").val(),
		"redEnvelopesPaymentRecords.send_time_start":$("#send_time_start_id").val(),
		"redEnvelopesPaymentRecords.send_time_end":$("#send_time_end_id").val()
	};
	$("#grantRedEnvelopeList").setPostData(listParam);
	$("#grantRedEnvelopeList").trigger("reloadGrid");
});
//刷新按钮事件
$("#refresh").click(function(){	
	$("#send_time_start_id").val("");
	$("#send_time_end_id").val("");
	loadData();
});
//金额格式化
function total_amountFormatter(el,options,rowData){
	if(rowData.total_amount != '' && !isNaN(rowData.total_amount)){
		return (rowData.total_amount)/100;
	}else{
		return "";
	}
}

//列表操作
function operateFormatter(el,options,rowData){
return "<@pop.JugePermissionTag ename='updateRedEnvelope'><a href='javascript:;' onclick='viewRecords("+rowData.id+")'><span>查看</span></a> </@pop.JugePermissionTag>"
}
function viewRedEnvelopesPaymentRecords(){
	var selectedId = $("#grantRedEnvelopeList").jqGrid("getGridParam", "selrow");
	viewRecords(selectedId);
}
function viewRecords(selectedId){
	if(selectedId==null || selectedId==""){
		$.messageBox({level:'warn',message:"请选择一条记录进行查看!"});
		return;
	}
	$("#grantRedEnvelopeDialog").createDialog({
		title:"查看红包发放记录详情",
		width: 750,
		height: 400,
		url:'${path}/redEnvelopesPaymentRecordsManage/viewRedEnvelopesPaymentRecords.action?id='+selectedId,
		buttons: {			
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

</script>
