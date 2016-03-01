<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="center-left" style="top:22px;">
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<div class="ui-corner-all" id="nav"> 
			<div align="center">
				<label class="form-lbl"  id="traffic"></label>
			</div>
			<input type="hidden" id="telecomTraffic">
			<input type="hidden" id="mobileTraffic">
			<input type="hidden" id="chinaUnicomTraffic">
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="trafficList"> </table>
			<div id="trafficListPager"></div>
		</div>
	</div>
</div>
<div id="trafficDialog"></div>

<script type="text/javascript">
$.showTxtValue();
var tree;

function operatorFormatter(el,options,rowData){
	return "<a href='javascript:setOperator("+rowData.orgId+")'><span>设置流量</span></a>";
}

function telecomTrafficFormatter(el,options,rowData){
	return changeTraffic(rowData.telecomTraffic);
}

function mobileTrafficFormatter(el,options,rowData){
	return changeTraffic(rowData.mobileTraffic);
}

function chinaUnicomTrafficFormatter(el,options,rowData){
	return changeTraffic(rowData.chinaUnicomTraffic);
}

function changeTraffic(traffic){
	if(traffic==null){
		return "0";
	}else{
		return traffic;
	}
}

function setOperator(orgId){
	$("#trafficDialog").createDialog({
		width:240,
		height:200,
		title:'设置流量',
	 	url:'${path}/smstrafficmanageManage/dispatchOperate.action?mode=set&smstrafficmanage.orgId='+orgId,
		buttons: {
	   		"保存" : function(event){
	   			$("#maintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").outerHeight();
	$(".orgTree").height(centerHeight-70);

	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$("#user_autocomplete").removeAttr("userId");
			$("#user_autocomplete").val("");
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	var clearUserUserAutocomplete=true;

	function afterChangNode(node) {
		var id = node.attributes.id;
		loadNum(id);
		loadOrg(id);
	}
	
	function loadNum(orgId){
		$.ajax({
			async:false,
			url:'/smstrafficmanageManage/loadNum.action?organizationId='+orgId,
			success:function(data){
				var telecomTraffic; 
				var mobileTraffic; 
				var chinaUnicomTraffic; 
				
				
				if(data==null){
					telecomTraffic=0;
					mobileTraffic=0;
					chinaUnicomTraffic=0;
				}else{
					if(data.telecomTraffic!=null){
						telecomTraffic=data.telecomTraffic;
					}else{
						telecomTraffic=0;
					}
					if(data.mobileTraffic!=null){
						mobileTraffic=data.mobileTraffic;
					}else{
						mobileTraffic=0;
					}
					if(data.chinaUnicomTraffic!=null){
						chinaUnicomTraffic=data.chinaUnicomTraffic;
					}else{
						chinaUnicomTraffic=0;
					}
				}
				
				$("#telecomTraffic").val(telecomTraffic);
				$("#mobileTraffic").val(mobileTraffic);
				$("#chinaUnicomTraffic").val(chinaUnicomTraffic);
				
				$("#traffic").text("电信可用流量："+telecomTraffic+"条  移动可用流量："+ mobileTraffic+"条  联通可用流量："+chinaUnicomTraffic+"条  ");
			}
		})
	}
	
	function loadOrg(orgId){
		var initParam = {
			"organizationId": orgId
		}
		$("#trafficList").setGridParam({
		 	url:'${path}/smstrafficmanageManage/findSmstrafficmanageByOrgId.action',
			datatype: "json",
			page:1
		});
		$("#trafficList").setPostData(initParam);
		$("#trafficList").trigger("reloadGrid");
	}
	
	function toggleButtonState(){
	  	var selectedIds=$("#trafficList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}

	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}
	$.addClick(tree,afterChangNode);
	
	
	// 生成列表
	$("#trafficList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	    	{name:'orgId', index:'orgId',label:'所属网格', width:100, sortable:true, align:'center', hidden:true}, 	
	    	{name:'orgName', index:'orgName',label:'所属网格', width:100, sortable:true, align:'center', hidden:false},
	    	{name:'telecomTraffic', index:'telecomTraffic',label:'电信', formatter:telecomTrafficFormatter, width:100, sortable:true, align:'center', hidden:false},
	    	{name:'mobileTraffic', index:'mobileTraffic',label:'移动', formatter:mobileTrafficFormatter, width:100, sortable:true, align:'center', hidden:false},
	    	{name:'chinaUnicomTraffic', index:'chinaUnicomTraffic',label:'联通', formatter:chinaUnicomTrafficFormatter, width:100, sortable:true, align:'center', hidden:false}
	   	]
	});
	jQuery("#trafficList").jqGrid('setFrozenColumns');
	
	
	
});

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
</script>