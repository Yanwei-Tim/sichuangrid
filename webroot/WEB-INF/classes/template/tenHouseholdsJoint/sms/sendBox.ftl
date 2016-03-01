<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
	<div class="ui-corner-all" id="nav">
	    <div class="btnbanner btnbannerData">
         <@s.include value="/common/orgSelectedComponent.jsp"/>
        </div>
        <@pop.JugePermissionTag ename='searchSendBoxCondition'>
			<a id="searchSendMsgInfo"  href="javascript:void(0)"><span>查询</span></a>
		</@pop.JugePermissionTag>
        <a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="sendBoxleList"> </table>
		<div id="sendBoxleListPager"></div>
	</div>
	<div id="sendBoxDialog"></div>
</div>

<script type="text/javascript">
<@pop.formatterProperty name="messageType" domainName="@com.tianque.domain.property.PropertyTypes@MESSAGETYPE" />
$(document).ready(function(){
	var orgId=getCurrentOrgId();
	$("#sendBoxleList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/sendMsgInfo/findSendMsgInfos.action',
		datatype: "json",
		postData:{
			"messageInfoVo.orgId":orgId
		},
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true,sortable:false},
	   	    {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_sendBox,width:30,align:"center"},
	   	    {name:'organization.orgName',label:'所属网格' ,width:100},
	   	    {name:'user.name',label:'用户名称' ,width:50},
	   	    {name:'message.sendAddress',label:'发送手机' ,width:50},
	   	    {name:'user.helpLine',label:'接收手机' ,width:50},
	   	    {name:'team.teamName',label:'所属分组' ,width:'50px'},
	   	    {name:'team.houseMaster',label:'联户长' ,width:50},
	   	    {name:'message.messageType.id',label:'数字标记',formatter:messageTypeFormatter ,width:30,align:'center'},
	   	    {name:'sendDate',label:'发送时间' ,width:60}
		],
		shrinkToFit:true,
		showColModelButton:true
	});
	
	$("#refresh").click(function(){
		loadSendMsgInfoPageInfo({});
	});
	
	//操作
	function operateFormatter_sendBox(el,options,rowData){
		return "<@pop.JugePermissionTag ename='viewSendBoxCondition'><a href='javascript:;' onclick='viewTenHouseholdsJointMsg("+rowData.id+")'><span>查看</span></a></@pop.JugePermissionTag>";
	}
	function messageTypeFormatter(el,options,rowData){
		return messageTypeData[rowData.message.messageType.id];
	}
	
	function loadSendMsgInfoPageInfo(obj){
		$("#sendBoxleList").setGridParam({
			url:'${path}/tenHouseholdsJoint/sendMsgInfo/findSendMsgInfos.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#sendBoxleList").setPostData($.extend({
			"messageInfoVo.orgId":getCurrentOrgId()
		},obj));
		$("#sendBoxleList").trigger("reloadGrid"); 
	}
	
	$("#searchSendMsgInfo").click(function(){
		$("#sendBoxDialog").createDialog({
				width: 500,
				height: 300,
				title: '高级查询',
				url:'${path}/tenHouseholdsJoint/sendMsgInfo/dispatch.action?mode=search&messageInfoVo.orgId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(){
						loadSendMsgInfoPageInfo($("#searchMaintainForm").serializeObject());
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
});
function viewTenHouseholdsJointMsg(id){
	$("#receiveBoxDialog").createDialog({
		width:500,
		height:300,
		title:'查看短信信息',
		url:PATH+'/tenHouseholdsJoint/sendMsgInfo/dispatch.action?mode=view&id='+id,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>