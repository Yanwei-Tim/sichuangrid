<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
     <div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<s:include value="/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch" style="display: none" >
					<input class="basic-input" type="text" value="请输入分组名称" id="searchByGroupName"
						class="searchtxt" onblur="value=(this.value=='')?'请输入分组名称':this.value;"
						onfocus="value=(this.value=='请输入分组名称')?'':this.value;" />
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
	  </div>
	  
	  <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="weChatMassSend">
		<a id="weChatMassSend" href="javascript:void(0)"><span>群发微信</span> </a>
	</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="weChatResponseList">
		</table>
		<div id="weChatResponseListPager"></div>
	</div>
</div>
<div id="weChatResponseMaintanceDialog"></div>

<select id="redCuffTeamMemberSubTeamTypeHiden" style="display:none">
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"  />
</select>
<script type="text/javascript">
var currentOrgId=getCurrentOrgId();
$(function() {
	//快速检索(重置)
	$("#refreshSearchKey").click(function(){
		$("#searchByGroupName").attr("value","请输入分组名称");
		loadListData({"weChatResponse.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByGroupName=$("#searchByGroupName").val();
		if(searchByGroupName != null && "请输入分组名称" != searchByGroupName){
			var queryObj = {
					"weChatResponse.org.id" : getCurrentOrgId(),
		            //"weChatGroup.name":searchByGroupName
			};
			loadListData(queryObj);
		}
    });
	//刷新
	$("#refresh").click(function()
	{
		loadListData({"weChatResponse.org.id" : getCurrentOrgId()});
	});
	
	//群发微信
	$("#weChatMassSend").click(function()
	{
		$("#weChatResponseMaintanceDialog").createDialog({
			width: 700,
			height: 250,
			title:'群发微信',
			url:'${path}/weChatResponseManage/dispatch.action?mode=weChatMassSendDlg&weChatResponse.org.id='+currentOrgId,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(event){
		        	$(this).dialog("close");
		   		}
			}
		});
	})
	//加载数据
	function loadListData(queryObj)
	{
		$("#weChatResponseList").setGridParam({
			url :'${path}/weChatResponseManage/findWeChatResponse.action',
			datatype: "json",
			page:1
		});
		$("#weChatResponseList").setPostData(queryObj);
		$("#weChatResponseList").trigger("reloadGrid");
	}
		//列表显示
		$("#weChatResponseList").jqGridFunction({
			url :'${path}/weChatResponseManage/findWeChatResponse.action',
			datatype: "json",
			postData : {
				"weChatResponse.org.id" : getCurrentOrgId()
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "id", index : "id",label : 'id',frozen:true,hidden : true,sortable : false},
			             {name : "sendType", index : 'sendType',sortable : true,label : '红袖套类别',formatter:redCuffTeamMemberSubTeamTypeFormatter,align : 'center',width : 95},
			             {name : "wechatUserName",index : 'wechatUserName',sortable : true,label : '微信号',align : 'center',width : 90}, 
			             {name : "text",index : 'text',sortable : true,label : '素材描述',align : 'center',width : 120}, 
			             {name : "status",index : 'status',sortable : true,label : '状态',align : 'center',width : 50},
			             {name : "sentCount",index : 'sentCount',sortable : true,label : '发送成功数',align : 'center',width : 50},
			             {name : "errorCount",index : 'errorCount',sortable : true,label : '发送失败数',align : 'center',width : 50},
			             {name : "resultDate",index : 'resultDate',sortable : true,label : '发送结果时间',align : 'center',width : 50},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width : 90}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 140},
			             ]
		});

})
function redCuffTeamMemberSubTeamTypeFormatter(el,options,rowData){
	var typeValues = rowData.sendType.split(",");
	var res = "";
	for(var i=0;i<typeValues.length;i++){
		res += $("#redCuffTeamMemberSubTeamTypeHiden option[value="+typeValues[i].trim()+"]").text()+",";
	}
	return res;
}
	
</script>