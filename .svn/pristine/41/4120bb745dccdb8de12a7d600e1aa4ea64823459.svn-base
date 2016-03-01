<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content" >
	<div id="search-condition-form" title="查询" class="container container_24">
		<div class="grid_3 lable-right">
			<label class="form-lbl">粉丝昵称：</label>
		</div>
		<div class="grid_18">
			<input name="name" type="text" id="name" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="查询" id="fastSearchFan" />
		</div>
		<div class='clearLine' style="margin-top:10px;height:10px">&nbsp;</div>
	</div>
	<div style="width: 100%;">
		<table id="funList"></table>
	<div id="funListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	
	$("#funList").jqGridFunction({
		url:'${path}/redEnvelopeManage/findFanListByWeChatUserIdForPage.action',
		datatype: "json",
		postData: {
			"fan.weChatUserId" : '<@s.property value="#parameters.weChatUserId[0]"/>'
		},
		colNames:['fanId','粉丝号','粉丝昵称','备注名','公众号'],
		colModel:[
			{name:"fanId",sortable:false,hidden:true,hidedlg:true},
			{name:'openId',sortable:false,width:250,align:"center"},
			{name:'name',sortable:false,width:150,align:"center"},
			{name:'nickName',sortable:false,width:150,align:"center"},
			{name:'weChatUserId',sortable:false,width:150}
		],
		multiselect:false,
		sortname:'FAN_ID'
	});
	
});

function sendRedEnvelope(redEnvelopeId){
	var selectedId = $("#funList").jqGrid("getGridParam", "selrow");
	var rowData = $("#funList").getRowData(selectedId);
	var openIdStr = rowData.openId;
	var weChatUserId = rowData.weChatUserId;
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
		   		"re_openid":openIdStr,
		   		"weChatUserId":weChatUserId,
		   		"redEnvelopeId":redEnvelopeId
	        },
			success:function(responseData){
				if(responseData == "SUCCESS"){
					$.messageBox({message:"红包发送成功"});
					$("li[role='tab']").each(function(){		
						if($(this).text()=="发放记录"){
							$(this).find('a').click();
						}		
					});						
				}else{
					if(responseData == null || responseData == "null"){
						$.messageBox({message:"红包发送失败,请检查微信支付商户号、API密钥配置是否正确",level: "error"});
						return;
					}
					$.messageBox({message:responseData+",红包发送失败",level: "error"});
				}
				$("#administrationRedEnvelopeDialog").dialog("close");
			}
		});
	}
}	

	$("#fastSearchFan").click(function(event){
		var name = $("#name").val();
		$("#funList").setGridParam({
			url:'${path}/redEnvelopeManage/findFanListByWeChatUserIdForPage.action',
			datatype: "json",
			page:1
		});
		var searchCondition={
			"fan.name":name,
			"fan.weChatUserId" : '<@s.property value="#parameters.weChatUserId[0]"/>'
		};
		$("#funList").setPostData(searchCondition);
		$("#funList").trigger("reloadGrid");
	});

</script>

