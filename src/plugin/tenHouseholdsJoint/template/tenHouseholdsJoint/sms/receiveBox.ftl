<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
	<div class="ui-corner-all" id="nav">
	    <div class="btnbanner btnbannerData">
         <@s.include value="/common/orgSelectedComponent.jsp"/>
        </div>
        <@s.if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()" >
			<a id="addReceiveMsgInfo"  href="javascript:void(0)"><span>模拟报警</span></a>
		</@s.if>
        <@pop.JugePermissionTag ename='searchReceiveBoxCondition'>
			<a id="searchReceiveMsgInfo"  href="javascript:void(0)"><span>查询</span></a>
		</@pop.JugePermissionTag>
        <a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="receiveBoxleList"> </table>
		<div id="receiveBoxleListPager"></div>
	</div>
	<div id="receiveBoxDialog"></div>
</div>

<script type="text/javascript">
<@pop.formatterProperty name="messageType" domainName="@com.tianque.domain.property.PropertyTypes@MESSAGETYPE" />
var receiveMsgInfoId;
$(document).ready(function(){
	var orgId=getCurrentOrgId();
	$("#receiveBoxleList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/receiveMsgInfo/findReceiveMsgInfos.action',
		datatype: "json",
		postData:{
			"messageInfoVo.orgId":orgId
		},
	   	colModel:[
	   	    {name:'id',hidden:true,sortable:false},
	   	    {name:'isDeal',hidden:true,sortable:false,frozen:true},
	   	    {name:'organization.id',hidden:true,sortable:false,frozen:true},
	   	 	{name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_receiveBox,width:60,align:"center"},
	   	    {name:'organization.orgName',label:'所属网格' ,width:100,align:'center'},
	   	    {name:'user.name',label:'用户名称' ,width:80},
	   	    {name:'message.sendAddress',label:'发送手机' ,width:70},
	   	    {name:'team.teamName',label:'所属分组' ,width:'80px'},
	   	    {name:'team.houseMaster',label:'联户长',width:60},
	   	    {name:'message.messageType.id',label:'数字标记',formatter:messageTypeFormatter,width:40,align:'center'},
	   	    {name:'sendDate',label:'发送时间',width:80}	,
	   	    {name:'isReport',label:'是否通知',formatter:isReportFormatter_receiveBox,width:40,align:'center'}	
		],
		shrinkToFit:true,
		showColModelButton:true
	});
	
	$("#refresh").click(function(){
		loadReceiveMsgInfoPageInfo({});
	});
	//操作
	function operateFormatter_receiveBox(el,options,rowData){
		<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgLevel().getInternalId() == @com.tianque.domain.property.OrganizationLevel@COUNTRY">
			return "<@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='deleteTenHouseholdsJointMsg("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>| <@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='viewTenHouseholdsJointMsg("+rowData.id+")'><span>查看</span></a></@pop.JugePermissionTag>";
		</@s.if>
		<@s.else>
			if(rowData.isDeal==true || rowData.isDeal=='true'){
				return "<span style='color:rgb(158, 132, 132);font-size: smaller;'>已受理</span> | <@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='deleteTenHouseholdsJointMsg("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>| <@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='viewTenHouseholdsJointMsg("+rowData.id+")'><span>查看</span></a></@pop.JugePermissionTag>";
			}
			return "<@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:' onclick='dealTenHouseholdsJointMsg("+rowData.id+")'><span>受理</span></a></@pop.JugePermissionTag> | <@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='deleteTenHouseholdsJointMsg("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>| <@pop.JugePermissionTag ename='searchReceiveBoxCondition'><a href='javascript:;' onclick='viewTenHouseholdsJointMsg("+rowData.id+")'><span>查看</span></a></@pop.JugePermissionTag>";
		</@s.else>
	}
	function isReportFormatter_receiveBox(el,options,rowData){
		return rowData.isReport?"是":"否";
	}
	function messageTypeFormatter(el,options,rowData){
		return messageTypeData[rowData.message.messageType.id];
	}
	
	function loadReceiveMsgInfoPageInfo(obj){
		$("#receiveBoxleList").setGridParam({
			url:'${path}/tenHouseholdsJoint/receiveMsgInfo/findReceiveMsgInfos.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#receiveBoxleList").setPostData($.extend({
			"messageInfoVo.orgId":getCurrentOrgId()
		},obj));
		$("#receiveBoxleList").trigger("reloadGrid"); 
	}
	
	$("#searchReceiveMsgInfo").click(function(){
		$("#receiveBoxDialog").createDialog({
				width: 500,
				height: 300,
				title: '高级查询',
				url:'${path}/tenHouseholdsJoint/receiveMsgInfo/dispatch.action?mode=search&messageInfoVo.orgId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(){
						loadReceiveMsgInfoPageInfo($("#searchMaintainForm").serializeObject());
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
	$("#addReceiveMsgInfo").click(function(){
		$("#receiveBoxDialog").createDialog({
				width: 500,
				height: 200,
				title: '新增告警',
				url:'${path}/tenHouseholdsJoint/sms/mainReceiveBoxDlg.ftl',
				buttons: {
					"保存" : function(){
						$("#maintainForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	})
});
function dealTenHouseholdsJointMsg(id){
	receiveMsgInfoId = id;
	var rowData = $("#receiveBoxleList").getRowData(id);
	
	$("#issueDialog").createDialog({
		width:840,
		height:570,
		title:'新增事件处理信息',
		url:PATH+'/issues/issueManage/dispatch.action?mode=addTenHJointIssue&keyId='+rowData["organization.id"]+'&id='+rowData["id"],
		buttons: {
	   		"保存" : function(event){
	   			$("#issueMaintainForm").submit();
	   		},
			"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function reloadIssue(){
	$.ajax({
		url:PATH+'/tenHouseholdsJoint/receiveMsgInfo/dealReceiveMsgInfo.action?id='+receiveMsgInfoId,
		success:function(data){
			onOrgChanged();
		}
	});
}

function onOrgChanged(){
$("#receiveBoxleList").setGridParam({
			url:'${path}/tenHouseholdsJoint/receiveMsgInfo/findReceiveMsgInfos.action',
			datatype: "json",
			page:1
		});
		$("#receiveBoxleList").setPostData({
			"messageInfoVo.orgId":getCurrentOrgId()
		});
	$("#receiveBoxleList").trigger("reloadGrid");	
}

function deleteTenHouseholdsJointMsg(id){
	$.confirm({
		title:"确认删除",
		message:"确认删除，数据一经删除，将不能恢复！",
		okFunc: function() {
			$.dialogLoading("open");
			$.ajax({
				url:PATH+'/tenHouseholdsJoint/receiveMsgInfo/deleteReceiveMsgInfo.action?id='+id,
				success:function(data){
					$.dialogLoading("close");
				    $.messageBox({message:"已经成功删除该信息!"});
					$("#receiveBoxleList").trigger("reloadGrid");
				}
			});
		}
	});
}
function viewTenHouseholdsJointMsg(id){
	$("#receiveBoxDialog").createDialog({
		width:500,
		height:300,
		title:'查看短信信息',
		url:PATH+'/tenHouseholdsJoint/receiveMsgInfo/dispatch.action?mode=view&id='+id,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>