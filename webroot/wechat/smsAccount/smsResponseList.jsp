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
					<input class="basic-input searchtxt" type="text" value="请输入分组名称" id="searchByGroupName"
						 onblur="value=(this.value=='')?'请输入分组名称':this.value;"
						onfocus="value=(this.value=='请输入分组名称')?'':this.value;" />
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
	  </div>
	  <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="addSmsResponse">
		<a id="addSmsResponse" href="javascript:void(0)"><span>群发短信</span> </a>
	</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="smsResponseList">
		</table>
		<div id="smsResponseListPager"></div>
	</div>
</div>
<div id="smsResponseMaintanceDialog"></div>

<select id="redCuffTeamMemberSubTeamTypeHiden" style="display:none">
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"  />
</select>
<script type="text/javascript">
var currentOrgId=getCurrentOrgId();
$(function() {
	//快速检索(重置)
	$("#refreshSearchKey").click(function(){
		$("#searchByGroupName").attr("value","请输入分组名称");
		loadListData({"smsSendGroup.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByGroupName=$("#searchByGroupName").val();
		if(searchByGroupName != null && "请输入分组名称" != searchByGroupName){
			var queryObj = {
					"smsSendGroup.org.id" : getCurrentOrgId(),
		            //"weChatGroup.name":searchByGroupName
			};
			loadListData(queryObj);
		}
    });
	//刷新
	$("#refresh").click(function()
	{
		loadListData({"smsSendGroup.org.id" : getCurrentOrgId()});
	});
	
	$("#addSmsResponse").click(function()
	{
		$("#smsResponseMaintanceDialog").createDialog({
			width: 550,
			height: 550,
			title:'短信群发',
			url:'${path}/smsResponseManage/dispatch.action?mode=add&sendType=byRedCuffTeamType&orgId='+getCurrentOrgId(),
			buttons: {
		   		"发送" : function(event){
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
		$("#smsResponseList").setGridParam({
			url :'${path}/smsResponseManage/findSmsSendGroup.action',
			datatype: "json",
			page:1
		});
		$("#smsResponseList").setPostData(queryObj);
		$("#smsResponseList").trigger("reloadGrid");
	}
		//列表显示
		$("#smsResponseList").jqGridFunction({
			url :'${path}/smsResponseManage/findSmsSendGroup.action',
			datatype: "json",
			postData : {
				"smsSendGroup.org.id" : getCurrentOrgId()
			},
			sortname:"createDate",
			sortorder:'desc',
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "id", index : "id",label : 'id',frozen:true,hidden : true,sortable : false},
			             {formatter:detailFormatter,sortable : false,label : '详情',align : 'center',width : 30},
			             {name : "org.orgName", index : 'orgName',sortable : false,label : '组织名',align : 'center',width : 90},
			             {name : "senderAccountName",index : 'senderAccountName',sortable : true,label : '发送账户',align : 'center',width : 85}, 
			             {name : "senderUserName",index : 'senderUserName',sortable : true,label : '发送人',align : 'center',width : 85}, 
			             {name : "receiverRedCuffTeamType",index : 'receiverRedCuffTeamType',formatter:redCuffTeamMemberSubTeamTypeFormatter,sortable : false,label : '接收人(红袖套类别)',align : 'center',width : 120},
			             {name : "smsContent",index : 'smsContent',sortable : false,label : '内容',align : 'center',width : 120},
			             {name : "totalNumber",index : 'totalNumber',sortable : true,label : '发送人数',align : 'center',width : 50},
			             {name : "successNumber",index : 'successNumber',sortable : true,label : '成功人数',align : 'center',width : 50},
			             {name : "failNumber",index : 'failNumber',sortable : true,label : '失败人数',align : 'center',width : 50},
			             {name : "lastResultDate",index : 'lastResultDate',sortable : true,label : '状态更新时间',align : 'center',width : 50},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建用户',align : 'center',width : 50},
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 50},
			             ]
		});

})
function detailFormatter(el,options,rowData){
	var groupId = rowData.id;
	return "<a  href='javascript:void(0)' onclick='showSendResult("+groupId+")'><span>详情</span> </a>"
}
function showSendResult(groupId){
	$("#smsResponseMaintanceDialog").createDialog({
		width: 600,
		height: 570,
		title:'群发结果',
		url:'${path}/wechat/smsAccount/smsSendResultList.jsp?groupId='+groupId,
		buttons: {
	   		"关闭" : function(event){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function redCuffTeamMemberSubTeamTypeFormatter(el,options,rowData){
	var typeValues = rowData.receiverRedCuffTeamType?rowData.receiverRedCuffTeamType.split(","):[];
	var res = "";
	for(var i=0;i<typeValues.length;i++){
		res += $("#redCuffTeamMemberSubTeamTypeHiden option[value="+typeValues[i].trim()+"]").text()+",";
	}
	return res;
}
</script>